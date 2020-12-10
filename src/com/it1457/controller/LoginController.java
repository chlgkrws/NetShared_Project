package com.it1457.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.UserService;
import com.it1457.vo.UserVO;

public class LoginController implements Controller {

	/* 로그인 처리 컨트롤러 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			UserService service = UserService.getInstance();
			UserVO userVO = service.userSearch(id);

			if (!userVO.isWouldUYN()) {
				/*
				 * request.setAttribute("error", "아이디와 비밀번호를 확인해주세요.");
				 * HttpUtil.forward(request, response, "/views/login/login.jsp");
				 */
				HttpUtil.alertToJsp(request, response, "/views/login/login.jsp", "아이디와 비밀번호를 확인해주세요.");
				return;
			}
			if (userVO.getPassword().equals(password)) {
				request.setAttribute("userVO", userVO);
				HttpUtil.forward(request, response, "views/login/login_session.jsp");
				return;
			} else {
				HttpUtil.alertToJsp(request, response, "/views/login/login.jsp", "아이디와 비밀번호를 확인해주세요.");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LoginController 에러");
			HttpUtil.alertToJsp(request, response, "/views/login/login.jsp", "아이디와 비밀번호를 확인해주세요.");
			return;
		}
	}
}
