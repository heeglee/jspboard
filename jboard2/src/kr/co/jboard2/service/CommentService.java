package kr.co.jboard2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.BoardDAO;
import kr.co.jboard2.vo.BoardVO;

public class CommentService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		String pg = request.getParameter("pg");
		String parent = request.getParameter("parent");
		String cate = request.getParameter("cate");
		String comment = request.getParameter("comment");
		String uid = request.getParameter("uid");
		String regip = request.getRemoteAddr();
		
		BoardVO vo = new BoardVO();
		vo.setParent(Integer.parseInt(parent));
		vo.setCate(cate);
		vo.setContent(comment);
		vo.setUid(uid);
		vo.setRegip(regip);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		try {
			dao.commentWrite(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/jboard2/view.do?pg="+pg+"&seq="+parent;
	}
}
