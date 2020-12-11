<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkPassword</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />
</head>
<body>
	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<br> <br> <br>

		<!-- 로그인 액션 -->
		<div id="check_password">
			<div
				style="padding-left: 50px; padding-right: 50px; padding-top: 10px;">
				<div>
					<p style="font-weight: 600; font-size: 25px; margin-bottom: 40px">비밀번호를 입력해주세요.</p>
				</div>
				<div class="sign_up_div">
				</div>
				<form action="/checkpassword.do" method="post">
					<input type="hidden" id="userid" name='userid' value='<%=session.getAttribute("id")%>'>
					<input class="login_input" type="password" id="password" name="password"
						placeholder="비밀번호" />
					<div class="sign_up_div" style="margin: 30px;">
					</div>
				
					<button type="submit" id="click_login" class="login_button"
						style="background-color: rgb(133, 172, 255);">확인</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>