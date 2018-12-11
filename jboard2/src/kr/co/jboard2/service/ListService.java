package kr.co.jboard2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.controller.CommandAction;
import kr.co.jboard2.dao.BoardDAO;
import kr.co.jboard2.vo.BoardVO;

public class ListService implements CommandAction {

	@Override
	public String requestProc(HttpServletRequest request, HttpServletResponse response) {
		
		String pg = request.getParameter("pg");
		
		if(pg == null) {
			pg = "1";
		}
		
		BoardDAO dao = BoardDAO.getInstance();
		try {
			
			int start = dao.getStart(pg);			
			List<BoardVO> list = dao.list(start);
			
			// 페이지 계산
			int total = dao.getTotalCount();
			int page = dao.getPage(total);
			int startNum = total - start;
			
			// 페이지 그룹 계산
			int currentPage = Integer.parseInt(pg);
			int groupCurrnt = (int) Math.ceil(currentPage/10.0); 
			int groupStart = (groupCurrnt - 1) * 10 + 1;
			int groupEnd = (groupCurrnt * 10);			
			if(groupEnd > page) {
				groupEnd = page;
			}
			
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			request.setAttribute("startNum", startNum);
			request.setAttribute("pg", pg);
			request.setAttribute("groupCurrent", groupCurrnt);
			request.setAttribute("groupStart", groupStart);
			request.setAttribute("groupEnd", groupEnd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/list.jsp";
	}

}