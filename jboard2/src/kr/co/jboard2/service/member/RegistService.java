package kr.co.jboard2.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.MemeberDAO;
import kr.co.jboard2.vo.MemberVO;

public class RegistService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		String method = request.getMethod();
		
		if(method.equals("GET")) {
			return "/register.jsp";
		} else {
			
			String uid = request.getParameter("id");
		 	String pass = request.getParameter("pw1");
		 	String name = request.getParameter("name");
		 	String nick = request.getParameter("nick");
		 	String email = request.getParameter("email");
		 	String hp = request.getParameter("hp");
		 	String zip = request.getParameter("zip");
		 	String addr1 = request.getParameter("addr1");
		 	String addr2 = request.getParameter("addr2");
		 	String regip = request.getRemoteAddr();
			
			MemberVO vo = new MemberVO();
			vo.setUid(uid);
			vo.setPass(pass);
			vo.setName(name);
			vo.setNick(nick);
			vo.setEmail(email);
			vo.setHp(hp);
			vo.setZip(zip);
			vo.setAddr1(addr1);
			vo.setAddr2(addr2);
			vo.setRegip(regip);
			
			MemeberDAO dao = MemeberDAO.getInstance();
			try {
				dao.register(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect:/jboard2/member/login.do";
		}
	}
}
