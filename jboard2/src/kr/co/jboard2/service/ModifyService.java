package kr.co.jboard2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.BoardDAO;
import kr.co.jboard2.vo.BoardVO;

public class ModifyService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		if(request.getMethod().equals("GET")) {
			// get요청
			String seq = request.getParameter("seq");
			String pg = request.getParameter("pg");
			
			BoardDAO dao = BoardDAO.getInstance();
			BoardVO vo = null;
			
			try {
				vo = dao.view(seq);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("vo", vo);
			request.setAttribute("pg", pg);
			
			return "/modify.jsp";
		}else {
			// post요청
			String seq = request.getParameter("seq");
			String pg = request.getParameter("pg");
			String cate = request.getParameter("cate");
			String title = request.getParameter("subject");
			String content = request.getParameter("content");
			String uid = request.getParameter("uid");
			String regip = request.getRemoteAddr();
			
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			vo.setCate(cate);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUid(uid);
			vo.setRegip(regip);
			
			BoardDAO dao = BoardDAO.getInstance();
			
			try {
				dao.modify(vo);				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect:/jboard2/view.do?pg="+pg+"&seq="+seq;			
		}
		
		
	}

}
