package com.it1457.controller;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController  extends HttpServlet{
 	private static final long serialVersionUID = 1L; 
	String charset = null;
	HashMap<String, Controller> controllerList = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {			//서블릿이 실행될 때 요청 url에 따라 Controller를 나눠줌.
		charset = config.getInitParameter("charset");
		controllerList = new HashMap<String, Controller>();
		controllerList.put("/userinsert.do", new SignUpController());
		controllerList.put("/userlogin.do", new LoginController());
		controllerList.put("/boardlist.do", new BoardListController());
		controllerList.put("/boardview.do", new BoardViewController());
		controllerList.put("/boardpost.do", new BoardPostController());
		controllerList.put("/boarddelete.do", new BoardDeleteController());
		controllerList.put("/boardwrite.do", new BoardWriteController());
		controllerList.put("/mypage.do", new MyPageController());
		controllerList.put("/withdrawuser.do", new WithdrawalUserController());
		controllerList.put("/likeup.do", new LikeUpController());
		controllerList.put("/matchingmaster.do", new MatchingMasterController());
		controllerList.put("/checkpassword.do", new CheckPasswordController());
		controllerList.put("/modifyinfo.do", new ModifyInfoController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		Controller subController = controllerList.get(path);
		subController.execute(req, resp);
	}

	
	
}
