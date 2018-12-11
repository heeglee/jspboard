package kr.co.jboard2.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.MemeberDAO;

public class CheckService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		String check = request.getParameter("check");
		String value = request.getParameter("value");
		
		MemeberDAO dao = MemeberDAO.getInstance();
		int result = 0;
		
		try {
			result = dao.checkUser(check, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = "{\"result\":"+result+"}";
		return "json:"+json;
	}

}








