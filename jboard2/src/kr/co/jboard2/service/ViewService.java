package kr.co.jboard2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.BoardDAO;
import kr.co.jboard2.vo.BoardVO;
import kr.co.jboard2.vo.FileVO;

public class ViewService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		String seq = request.getParameter("seq");
		String pg  = request.getParameter("pg");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = null;
		FileVO fvo = null;
		List<BoardVO> comments = null;
		
		try {
			vo = dao.view(seq);
			comments = dao.commentList(seq);
			
			// 파일첨부 여부확인
			if(vo.getFile() == 1) {
				fvo = dao.getFile(vo.getSeq());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("vo", vo);
		request.setAttribute("pg", pg);
		request.setAttribute("comments", comments);
		request.setAttribute("fvo", fvo);
		
		return "/view.jsp";
	}

}









