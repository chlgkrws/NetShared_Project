<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Intro</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/home/home.css" />
<link rel="stylesheet" href="/resources/css/intro/intro.css" />
</head>
<body>

	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<div id="intro_container">
			<br> <br> <br> <br>
			<div class="intro_content">
				<div class="intro_left" style="text-align: right;">
					<img src="../../resources/img/selfphone.jpg"
						style="text-align: right;" width="350px" />
				</div>
				<div class="intro_right"
					style="text-align: left; padding-left: 50px;">
					<h3>NetShared란?</h3>
					<hr>
					<p>
						우리나라 주요 OTT(영상을 제공하는 서비스) 플랫폼인 넷플릭스를 사용하고 싶지만 높은 구독료 때문에 지인들과 함께
						나눠 내는 사람들이 많다.<br> 4명 기준 구독료에 1, 2명이 없어 돈을 더 많이 지불하거나 모든 요금을
						내고 혼자 보는 사용자들이 있다. <br>NetShared는 이런 걱정들을 싹 다 없애줄 수 있는 계정 공유
						사이트이다.
					</p>
				</div>
			</div>
			
			<br> <br> <br> <br>
			
			<div class="intro_content">
				<div class="intro_left" style="text-align: right;">
					<img src="../../resources/img/netflex_pay.png"
						style="text-align: right;" width="350px" />
				</div>
				<div class="intro_right"
					style="text-align: left; padding-left: 50px;">
					<h3>3600원으로 UHD 감상!</h3>
					<hr>
					<p>
						넷플릭스는 프리미엄 멤버쉽(14,500원)부터 UHD를<br> 제공한다. NetShared를 이용하게 되면
						14,500원이 아닌<br> 3600원으로 UHD 화질을 경험할 수 있다!<br>
					</p>
				</div>
			</div>
		</div>

	</div>
</body>
</html>