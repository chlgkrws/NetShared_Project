package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.UserService;
import com.it1457.vo.UserVO;

public class CheckPasswordController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId= (String)request.getParameter("userid");
		String password= (String)request.getParameter("password");
		
		UserService service = UserService.getInstance();
		UserVO user = service.userSearch(userId);
		
		if(user.getPassword().equals(password)) {
			request.setAttribute("user", user);
			HttpUtil.forward(request, response, "views/login/modify_info.jsp");
			return;
		}
		
		HttpUtil.alertToJsp(request, response, "views/login/check_password.jsp", "비밀번호가 틀렸습니다.");
	}

}
