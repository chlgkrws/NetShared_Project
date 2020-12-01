package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.UserService;
import com.it1457.vo.UserVO;

public class SignUpController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserVO userVO = new UserVO(); // UserVO에 회원가입창에서 받아온 데이터들을 넣음

			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String userName = request.getParameter("username");
			String identity = request.getParameter("identity");
			String phone = request.getParameter("phone");
			String account = request.getParameter("account");

			userVO.setId(id);
			userVO.setPassword(password);
			userVO.setUserName(userName);
			userVO.setIdentity(identity);
			userVO.setPhone(phone);
			userVO.setAccount(account);

			UserService service = UserService.getInstance();
			service.userInsert(userVO);
			request.setAttribute("id", id);

			HttpUtil.forward(request, response, "/index.jsp"); // index.jsp로 이동
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SignUpController 에러");
		}

	}

}
