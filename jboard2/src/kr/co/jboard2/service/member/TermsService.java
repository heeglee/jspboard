package kr.co.jboard2.service.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.MemeberDAO;
import kr.co.jboard2.utils.DBConfig;
import kr.co.jboard2.utils.SQL;
import kr.co.jboard2.vo.TermsVO;

public class TermsService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		MemeberDAO dao = MemeberDAO.getInstance();
		TermsVO vo = null;
		
		try {
			vo = dao.terms();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("vo", vo);
		
		return "/terms.jsp";
	}
}
