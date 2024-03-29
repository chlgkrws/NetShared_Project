<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyInfo</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />
</head>
<body>
	<c:set var="user" value="${user }" />
	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<br> <br> <br>

		<!-- 로그인 액션 -->
		<div id="modify_info">
			<div
				style="padding-left: 50px; padding-right: 50px; padding-top: 10px;">
				<div>
					<p style="font-weight: 600; font-size: 25px; margin-bottom: 40px">개인정보
						수정</p>
				</div>
				<div class="sign_up_div"></div>
				<form action="/modifyinfo.do" method="post" onsubmit="return sign_up();">
					<input type="hidden" id="userid" name='userid'
						value='<%=session.getAttribute("id")%>'> 
					<input
						class="modify_input" type="password" id="password" name="password"
						placeholder="비밀번호" /> 
					<input class="modify_input" type="password"
						id="passwordval" name="passwordval" placeholder="비밀번호 확인" /> 
					<input
						class="modify_input" type="text" id="phone" name="phone"
						value="${user.phone }" placeholder="핸드폰 번호" /> 
					<input
						class="modify_input" type="text" id="account" name="account"
						value="${user.account }" placeholder="계좌번호" />

					<div class="sign_up_div" style="margin: 30px;"></div>

					<button type="submit" id="click_login" class="login_button"
						style="background-color: rgb(133, 172, 255);">확인</button>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">

	/*회원 정보 유효성 검사 */
	function sign_up() {
		var password = document.getElementById("password").value;
		var password_valid = document.getElementById("passwordval").value;
		var phone = document.getElementById("phone").value;
		var account = document.getElementById("account").value;
		
		//정규표현식
		var num = password.search(/[0-9]/g);						
		var eng = password.search(/[a-z]/ig);
		var spe = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		var phoneReg = /^01[016][0-9]{3,4}[0-9]{4}$/g;
		var accountReg = /^[0-9]{5,30}$/g;									
		
		

		if(password.length < 8 || password.length > 20){
		 	alert("8자리 ~ 20자리 이내로 입력해주세요.");
			return false;
		}else if(password.search(/\s/) != -1){
		 	alert("비밀번호는 공백 없이 입력해주세요.");
	  		return false;
		}else if(num < 0 || eng < 0 || spe < 0 ){
		  	alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		  	return false;
		}
		
		if(password != password_valid){
			alert("비밀번호가 같은지 확인해주세요.");
			return false;
		}
		
		
		if(!phoneReg.test(phone)){
			alert("핸드폰 번호를 확인해주세요.");
			return false;
		}
		
		return true;
	}
</script>
</body>
</html>