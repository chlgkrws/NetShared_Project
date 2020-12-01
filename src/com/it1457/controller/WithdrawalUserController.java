package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.UserService;

public class WithdrawalUserController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = (String) request.getSession().getAttribute("id");
			
			UserService service = UserService.getInstance();
			int check = service.withdrawUser(id);

			// 1 : 회원탈퇴가 됐을 때
			if (check == 1) {
				HttpUtil.redirect(request, response, "/views/login/logout.jsp");
			} else {
				HttpUtil.alertToJsp(request, response, "views/home/my_page.jsp", "회원 탈퇴에 실패했습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("WithdrawalUserController 에러");
			HttpUtil.redirect(request, response, "/index.jsp");
		}
	}

}
