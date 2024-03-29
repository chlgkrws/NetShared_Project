<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/home/home.css" />
<title>signUp</title>
</head>
<body>
	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>
		
		<br>

		<!-- 회원 가입 창 -->
		<div id="login_sign_up">
			<div style="width: 400px; margin: 0 auto;">
				<div>
					<p
						style="font-weight: 600; font-size: 30px; color: rgb(82, 82, 82);">회원가입</p>
				</div>
				<form action="/userinsert.do" method="post"
					onsubmit="return sign_up();">
					<div class="sign_up_div">
						<p>아이디</p>
					</div>
					<input type="text" id="id" name="id" class="sign_up_input"
						style="ime-mode: disabled;" placeholder="아이디를 입력하세요. (영문 / 6~12자)" />

					<div class="sign_up_div">
						<p>비밀번호</p>
					</div>
					<input type="password"
						placeholder="비밀번호를 입력하세요. (영문 / 특수문자 / 숫자 포함 8~20자)" id="password"
						name="password" class="sign_up_input" />

					<div class="sign_up_div">
						<p>비밀번호 확인</p>
					</div>
					<input type="password" placeholder="비밀번호를 한번 더 입력하세요"
						id="password_valid" name="password_valid" class="sign_up_input" />

					<div class="sign_up_div">
						<p>이름</p>
					</div>
					<input type="text" placeholder="이름을 입력하세요." id="username"
						name="username" class="sign_up_input" />

					<!-- <div class="sign_up_div">
						<p>주민등록번호</p>
					</div>
					<input type="text" placeholder="주민등록번호 앞자리 ex) 951007 "
						id="identity" name="identity" class="sign_up_input" /> -->

					<div class="sign_up_div">
						<p>핸드폰 번호</p>
					</div>
					<input type="text" placeholder="ex)01012349876" id="phone"
						name="phone" class="sign_up_input" />

					<div class="sign_up_div">
						<p>계좌번호</p>
					</div>
					<input type="text" placeholder="-없이, 413049294239" id="account"
						name="account" class="sign_up_input" /> <br> <br>

					<button type="submit" class="login_button"
						style="background-color: rgb(238, 238, 238); width: 200px; color: rgb(92, 91, 91);">가입</button>
				</form>
				<br> <br>
			</div>
		</div>
	</div>
</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

	/*회원 정보 유효성 검사 */
	function sign_up() {
		var id = document.getElementById("id").value;
		var password = document.getElementById("password").value;
		var password_valid = document.getElementById("password_valid").value;
		var username = document.getElementById("username").value;
		//var identity = document.getElementById("identity").value;
		var phone = document.getElementById("phone").value;
		var account = document.getElementById("account").value;
		
		//정규표현식
		var idReg = /^[a-z][a-z0-9]{5,11}$/g;							
		var num = password.search(/[0-9]/g);						
		var eng = password.search(/[a-z]/ig);
		var spe = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		var usernameReg = /^[가-힣]{2,4}$/g;								
		//var identityReg = /^[0-9]{6}$/g;	
		var phoneReg = /^01[016][0-9]{3,4}[0-9]{4}$/g;
		var accountReg = /^[0-9]{5,30}$/g;									
		
		
		if(!idReg.test(id)){
			alert("유효하지 않은 아이디입니다.");
			return false;
		}
		

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
		
		
		if(!usernameReg.test(username)){
			alert("이름을 확인해주세요.");
			return false;
		}
		
		if(!phoneReg.test(phone)){
			alert("핸드폰 번호를 확인해주세요.");
			return false;
		}
		
		
		/* if(!identityReg.test(identity)){
			alert("주민등록번호 앞자리 6자리를 적어주세요.");
			return false;
		} */
		
		alert("계정이 생성되었습니다!")
		
		return true;
	}
</script>
</html>