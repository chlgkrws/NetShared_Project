<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Matching</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />
<link rel="stylesheet"
	href="/resources/css/matching/normal_matching.css" />
<script type="text/javascript" src="/resources/js/matching/matching.js"></script>
</head>
<body>
	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<!-- 메인 -> 일반매칭 버튼 후 출력되는 화면 -->
		<br> <br> <br>
		<div id="normal_matching_container">
			<div id="matching_count">
				<div style="margin: 0 auto; line-height: 100px;">
					<h3
						style="margin: 0px; font-weight: 500; color: rgb(105, 105, 105);">이제까지
						매칭된 인원은 1524명 입니다</h3>
				</div>
			</div>
			<br> <br>
			<div id="matching_area">
				<div id="in_matching_area">
					<div>
						<h3
							style="text-align: left; padding-left: 30px; color: rgb(78, 78, 78); margin-top: 30px;">매칭
							시작하기</h3>
					</div>
					<div id="match" style="margin-top: 70px;">
						<img src="../../resources/img/netflex.png" style="width: 150px;" />
					</div>
					<div id="matching_price_info">
						<div
							style="float: left; line-height: 60px; padding-left: 70px; font-weight: 600;">
							<p>4인 기준</p>
						</div>
						<div
							style="float: right; line-height: 60px; padding-right: 60px; font-weight: 600;">
							<p>매월 3,650원</p>
						</div>
					</div>
					<form action="/matchingmaster.do" method="get"
						onsubmit="return match_click();">
						<div style="width: 60%; display: inline-block; margin-top: 30px;">
							<div class="matching_left">
								<button class="matching_button" id="button1" type="button"
									style="width: 170px; height: 50px;"
									onmouseover="matching_button_on(this)"
									onmouseout="matching_button_out(this)"
									onclick="matching_button_click(this)">파티원</button>
							</div>
							<div class="matching_right">
								<button class="matching_button" id="button2" type="button"
									style="width: 170px; height: 50px;"
									onmouseover="matching_button_on(this)"
									onmouseout="matching_button_out(this)"
									onclick="matching_button_click(this)">파티장</button>
							</div>
							<input type="hidden" id="ismember" name="ismember" value="yes" />
							<input type="hidden" id="session_id" name="session_id"
								value="<%=session.getAttribute("id")%>" />
							<input type="hidden" id="headCount" name="headCount"/>
							<ul id="numberOfMember">
	<!-- 							<li><button type="button" id="HCButton1"
										onclick="hc_button_click(this)"
										onmouseover="hc_button_on(this)"
										onmouseout="hc_button_out(this)">1인</button></li> -->
								<li><button type="button" id="HCButton2"
										onclick="hc_button_click(this)"
										onmouseover="hc_button_on(this)"
										onmouseout="hc_button_out(this)">2인</button></li>
								<li><button type="button" id="HCButton3"
										onclick="hc_button_click(this)"
										onmouseover="hc_button_on(this)"
										onmouseout="hc_button_out(this)">3인</button></li>
								<li><button type="button" id="HCButton4"
										onclick="hc_button_click(this)"
										onmouseover="hc_button_on(this)"
										onmouseout="hc_button_out(this)">4인</button></li>
							</ul>
							<div style="margin-top: 30px; display: block;">
								<button type="submit" class="match_click">매칭 시작</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<br> <br> <br>
		</div>
	</div>
</body>
</html>