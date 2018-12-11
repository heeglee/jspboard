package kr.co.jboard2.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> instances = new HashMap<>();

	@Override				
	public void init(ServletConfig config) throws ServletException {
		// 컨트롤러 초기화 작업
		
		// commandURI.properties 파일경로 추출
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF")+"/commandURI.properties"; 
		
		// properties 객체 생성
		// Map<String, String> map = new HashMap<>();
		Properties prop = new Properties(); // map과 동일한 자료구조 컬렉션
		FileInputStream fis = null;
		
		try {
			// commandURI.properties 파일과 입력스트림 연결
			fis = new FileInputStream(path);
			
			// 입력스트림으로 commandURI.properties 데이터 읽어 들이기
			prop.load(fis);			
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			if(fis != null) {
				try {
					fis.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}			
		}
		
		// 모델클래스 객체를 생성해서 properties에 저장
		Iterator it = prop.keySet().iterator();
		
		while(it.hasNext()) {
			
			String k = it.next().toString();
			String v = prop.getProperty(k);
			
			try {
				// prop 객체에 저장된 문자열정보를 가지고 해당 패키지에 있는 클래스를 객체로 생성
				Class<?> obj = Class.forName(v);
				Object instance = obj.newInstance();
				
				// 생성된 객체를 instances 객체에 저장
				instances.put(k, instance);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	private void requestProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = null;
		
		// ch18/hello.do
		String root = request.getContextPath();
		String uri = request.getRequestURI();
		String command = uri.substring(root.length());
		
		CommandAction instance = (CommandAction) instances.get(command);
		view = instance.requestProc(request, response);
		
		if(view.startsWith("redirect:")) {
			String action = view.substring(9);
			response.sendRedirect(action);
		}else if(view.startsWith("json:")){
			// json:{'result':'1'}
			String json = view.substring(5);
			PrintWriter out = response.getWriter();
			out.print(json);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
}
