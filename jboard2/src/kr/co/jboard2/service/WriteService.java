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
			
			// ÷�ε� ������ ������ upload ���͸��� ��������
			// ����ȯ�� : $WORKSPACE\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\jboard2\... 
			// ���󼭹� : /home/heeg/jboard2/upload
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
				
				// ������ ÷������ ���
				if(file != null) {
					// ���ϸ� �����ϱ�
					String newName = makeUUID(file, uid);
					updateFileName(path, file, newName);	
					
					// ���̺� �������ϸ�� �����ϸ��� ����
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
	}// requestProc ��
	
	public String makeUUID(String file, String uid) {
		
		// Ȯ���� ���ϱ� �������.xlsx
		int idx = file.lastIndexOf(".");
		String ext = file.substring(idx);
		
		// ���� ��¥ ���ϱ�
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String now = sdf.format(date);
		
		// ���ϸ� ����� 180928101716_chhak.xlsx
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
			// ���ϸ� ������ �������� ���
			// ��Ʈ�������� ���� ���� ���Ͼ��� �۾�
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
