package kr.co.jboard2.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.BoardDAO;
import kr.co.jboard2.vo.BoardVO;
import kr.co.jboard2.vo.MemberVO;

public class WriteService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		String method = request.getMethod();
		
		if(method.equals("POST")) {
			
			// 첨부된 파일이 있으면 upload 디렉터리에 파일저장
			// 개발환경 : $WORKSPACE\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\jboard2\... 
			// 리얼서버 : /home/heeg/jboard2/upload
			String path = request.getServletContext().getRealPath("/upload");
			int maxSize = 1024*1024*10; // 10MB
			MultipartRequest mr = null;
			
			try {
				mr = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			} catch (IOException e1) {
				e1.printStackTrace();
			}               
			
			String cate = mr.getParameter("cate");
			String title = mr.getParameter("subject");
			String content = mr.getParameter("content");
			String uid = mr.getParameter("uid");
			String file = mr.getFilesystemName("file");
			String regip = request.getRemoteAddr();
			
			BoardVO vo = new BoardVO();
			vo.setCate(cate);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUid(uid);
			vo.setRegip(regip);
			
			if(file != null) {
				vo.setFile(1);
			}else {
				vo.setFile(0);
			}
			
			BoardDAO dao = BoardDAO.getInstance();
			
			try {
				int seq = dao.write(vo);
				
				// 파일을 첨부했을 경우
				if(file != null) {
					// 파일명 수정하기
					String newName = makeUUID(file, uid);
					updateFileName(path, file, newName);	
					
					// 테이블에 원본파일명과 새파일명을 저장
					dao.insertFile(seq, file, newName);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			return "redirect:/jboard2/list.do";
		}else {
			/*
			HttpSession session = request.getSession();
			MemberVO member = (MemberVO) session.getAttribute("member");
			
			if(member == null) {
				
			}else {
				
			}
			*/			
			return "/write.jsp";
		}
	}// requestProc 끝
	
	public String makeUUID(String file, String uid) {
		
		// 확장자 구하기 저녁수업.xlsx
		int idx = file.lastIndexOf(".");
		String ext = file.substring(idx);
		
		// 현재 날짜 구하기
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String now = sdf.format(date);
		
		// 파일명 만들기 180928101716_chhak.xlsx
		String fileName = now+"_"+uid+ext;
		
		return fileName;
	}
	
	public void updateFileName(String path, String oldName, String newName) throws Exception {
		
		long fileSize = 0;
		byte[] buf = new byte[1024];
		
		FileInputStream input = null;
		FileOutputStream output = null;
		
		File oldFile = new File(path+"/"+oldName);
		File newFile = new File(path+"/"+newName);
		
		if(!oldFile.renameTo(newFile)) {
			// 파일명 변경이 실패했을 경우
			// 스트림연결을 통한 직접 파일쓰기 작업
			input = new FileInputStream(oldFile);
			output = new FileOutputStream(newFile);
			
			int read = 0;
			while(true) {
				read = input.read(buf, 0, buf.length);				
				if(read == -1) {
					break;
				}				
				output.write(buf, 0, read);				
			}
			input.close();
			output.close();
			oldFile.delete();
		}
	}
	
	
	

}
