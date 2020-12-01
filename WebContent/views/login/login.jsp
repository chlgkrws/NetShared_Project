<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/home/home.css" />

<title>login</title>
</head>
<body>
	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<br> <br> <br>

		<!-- 로그인 액션 -->
		<div id="login_login">
			<div
				style="padding-left: 50px; padding-right: 50px; padding-top: 10px;">
				<div>
					<p style="font-weight: 600; font-size: 30px;">로그인</p>
				</div>
				<div class="sign_up_div">
					<p>ID</p>
				</div>
				<form action="/userlogin.do" method="post">
					<input class="login_input" type="text" id="id" name="id"
						placeholder="아이디를 입력하세요." />
					<div class="sign_up_div">
						<p>PASSWORD</p>
					</div>
					<input class="login_input" type="password" name="password"
						placeholder="비밀번호를 입력하세요." style="margin-bottom: 30px" />
					<p style="margin-bottom: 25px">${error}</p>
					<button type="submit" id="click_login" class="login_button"
						style="background-color: rgb(133, 172, 255);">로그인</button>
				</form>
				<button type="button" class="login_button"
					onclick="location.href='/views/login/sign_up.jsp'"
					style="background-color: rgb(213, 218, 218); color: rgb(75, 75, 75); margin-top: 18px">회원가입</button>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script>
		$(document).ready(function() {

		});
	</script>
</body>
</html>