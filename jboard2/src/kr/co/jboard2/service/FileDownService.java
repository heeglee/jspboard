package kr.co.jboard2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.BoardDAO;
import kr.co.jboard2.vo.FileVO;

public class FileDownService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		String parent = request.getParameter("parent");
		
		BoardDAO dao = BoardDAO.getInstance();
		FileVO vo = null;
		try {
			vo = dao.getFile(Integer.parseInt(parent));
			
			// 다운로드 카운트 +1
			
			
			// 파일명 원복
			String path = request.getServletContext().getRealPath("/upload");
			File file = new File(path+"/"+vo.getNewName());
			String oldName = new String(vo.getOldName().getBytes("UTF-8"), "iso-8859-1");
			
			if(file.exists()) {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename="+oldName+";");
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "private");
				
				byte b[] = new byte[1024];
				
				FileInputStream in = new FileInputStream(file);
				ServletOutputStream out = response.getOutputStream();
				
				BufferedInputStream bis = new BufferedInputStream(in);    
				BufferedOutputStream bos = new BufferedOutputStream(out);
				
				int read = 0;
				while(true) {
					read = bis.read(b);
					if(read == -1) {
						break;
					}
					bos.write(b, 0, read);
				}
				bos.flush();
				bos.close();
				bis.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
