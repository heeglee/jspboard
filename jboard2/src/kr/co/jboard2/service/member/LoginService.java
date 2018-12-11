package kr.co.jboard2.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.MemeberDAO;
import kr.co.jboard2.vo.MemberVO;

public class LoginService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		String method = request.getMethod();
		if(method.equals("POST")) {
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberVO vo = null;
			MemeberDAO dao = MemeberDAO.getInstance();
			try {
				vo = dao.login(id, pw);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			if(vo != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", vo);
				session.setAttribute("login", true);
				
				return "redirect:/jboard2/list.do";				
			} else {
				return "redirect:/jboard2/member/login.do?result=fail";
			}
			
		} else {
			String result = request.getParameter("result");
			request.setAttribute("result", result);
			
			return "/login.jsp";	
		}
	}
}
