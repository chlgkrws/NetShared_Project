package com.it1457.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//중복 되는 page이동 코드를 모듈화
public class HttpUtil {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println("forward 오류 : " + ex);
		}
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			response.sendRedirect(path);
		} catch (Exception ex) {
			System.out.println("redirect 오류 : " + ex);
		}
	}
	
	//이벤트로 alert이 필요할 때, msg와 path를 담은 request와 함께 forword
	public static void alertToJsp(HttpServletRequest request, HttpServletResponse response, String path, String msg) {
		try {
			request.setAttribute("msg", msg);
			request.setAttribute("path", path);
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/include/alert.jsp");
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println("redirect 오류 : " + ex);
		}
	}
}
