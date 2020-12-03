package com.it1457.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.vo.BoardVO;
import com.it1457.vo.UserVO;

public class FrontController  extends HttpServlet{
 	private static final long serialVersionUID = 1L; 
	String charset = null;
	HashMap<String, Controller> controllerList = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {			//서블릿이 실행될 때 요청 url에 따라 Controller를 나눠줌.
		charset = config.getInitParameter("charset");
		controllerList = new HashMap<String, Controller>();
		controllerList.put("/userInsert.do", new SignUpController());
		controllerList.put("/userlogin.do", new LoginController());
		controllerList.put("/boardList.do", new BoardListController());
		controllerList.put("/boardView.do", new BoardViewController());
		controllerList.put("/boardPost.do", new BoardPostController());
		controllerList.put("/boardDelete.do", new BoardDeleteController());
		controllerList.put("/boardWrite.do", new BoardWriteController());
		controllerList.put("/myPage.do", new MyPageController());
		controllerList.put("/withdrawUser.do", new WithdrawalUserController());
		controllerList.put("/likeUp.do", new LikeUpController());
		controllerList.put("/matchingmaster.do", new MatchingMasterController());
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
