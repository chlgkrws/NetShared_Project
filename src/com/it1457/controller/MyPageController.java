package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.UserService;
import com.it1457.vo.UserVO;

public class MyPageController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userId = (String) request.getSession().getAttribute("id");

			UserService service = UserService.getInstance();
			UserVO user = service.userSearch(userId);

			// 핸드폰 번호 가리기
			user.setPhone(encryptPhone(user.getPhone()));

			// 계좌 번호 가리기
			user.setAccount(encryptAccount(user.getAccount()));

			request.setAttribute("userVO", user);
			HttpUtil.forward(request, response, "/views/home/my_page.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MyPageController 에러");
			HttpUtil.redirect(request, response, "/index.jsp");
		}
	}

	// 핸드폰 번호 가리기
	public String encryptPhone(String phone) {
		if (phone.length() == 11) { // 가운데 변수가 4자리일 때
			phone = phone.substring(0, 7); // 0101111
			phone = phone.substring(0, 3) + "-" + phone.substring(3); // 010-1111
		} else {
			phone = phone.substring(0, 6); // 010111
			phone = phone.substring(0, 3) + "-" + phone.substring(3);
		}
		phone += "-****";

		return phone;
	}

	// 계좌 번호 가리기
	public String encryptAccount(String account) { // 1234567890 -> 123*****90
		int len = account.length();
		int partLen = len / 2;
		int subLen = partLen / 2;

		String pattern = account.substring(partLen - subLen, partLen + subLen + 1);
		account = account.replace(pattern, "*".repeat(partLen));

		return account;
	}

}
