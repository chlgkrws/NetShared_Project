package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.UserService;
import com.it1457.vo.UserVO;

public class ModifyInfoController implements Controller{
	//회원정보 수정
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId= (String) request.getParameter("userid");
		String password = (String) request.getParameter("password");
		String phone = (String) request.getParameter("phone");
		String account = (String) request.getParameter("account");
		
		UserVO user = new UserVO();
		user.setId(userId);
		user.setPassword(password);
		user.setPhone(phone);
		user.setAccount(account);
		
		UserService service = UserService.getInstance();
		
		int updateCheck = service.updateInfo(user);
		
		if(updateCheck == 1) {
			HttpUtil.alertToJsp(request, response, "views/login/logout.jsp", "회원수정을 성공하셨습니다!");
			//업데이트 성공
		}else {
			HttpUtil.alertToJsp(request, response, "views/login/logout.jsp", "회원수정을 실패하셨습니다.");
		}
	}

}
