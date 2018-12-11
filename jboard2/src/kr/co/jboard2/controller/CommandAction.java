package kr.co.jboard2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {

	public String requestProc(HttpServletRequest request, HttpServletResponse response);
	
}
