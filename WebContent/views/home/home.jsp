<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />

</head>

<body>
	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<br> <br> <br>

		<div id="container">
			<div class="left">
				<img id="home_netflex" src="/resources/img/netflex.png"> <br>
				<br>
				<p>새로운 넷플릭스 계정 공유를 경험 해보세요!</p>
				<p>일반 매칭은 파티장과 매칭을 시켜주는 서비스입니다.</p>
				<p>
					빠른 매칭은 파티원을 빠르게 구하고 싶어하는 파티장님들이<br> 올려놓은 게시물이에요:)
				</p>
				<br>
				<button type="button" class='radius_button'
					onclick="location.href='/views/matching/normal_matching.jsp'"
					style="width: 150px; height: 50px;">일반 매칭</button>
			</div>
			<!-- left 끝 -->

			<div class="right">
				<p
					style="font-size: 20px; font-weight: 600; color: rgb(70, 70, 70);">현재
					대기 중인 빠른매칭</p>
				<div id="board" style="text-align: center;">

					<!-- 빠른 매칭 예시(미완성) -->
					<%-- 					<%
						for (int i = 0; i < 7; i++) {
						out.print("<div class='matching_content'>" + "	<div style='float: left; width: 20%; line-height: 40px;'>"
						+ "		<p>chlgkr**</p>" + "	</div>" + "	<div style='float: left; width: 20%; line-height: 40px;'>"
						+ "		<p style='font-size: 13px;'>최소 2개월</p>" + "	</div>"
						+ "	<div style='float: left; width: 20%; line-height: 40px;'>"
						+ "		<p style='font-size: 13px;'>20% DC</p>" + "	</div>"
						+ "	<div style='float: left; width: 20%; line-height: 40px;'>" + "		<p style='font-size: 13px;'>1명</p>"
						+ "	</div>" + "	<div style='float: left; width: 20%; line-height: 40px;'>"
						+ "		<button class='participate_button' type='button'"
						+ "			onclick='go_quick_matching()'>참여하기</button>" + "	</div>" + "</div>");
					}
					%> --%>


					<!-- 실제 모델이 될 부분 -->
					<div class="matching_content">
						<div style="float: left; width: 20%; line-height: 40px;">
							<p>chlgkr**</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">최소 2개월</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">20% DC</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">1명</p>
						</div>
						<div style="float: right; width: 20%; line-height: 40px;">
							<button class="participate_button" type="button"
								onclick="go_quick_matching()">참여하기</button>
						</div>
					</div>
					<div class="matching_content">
						<div style="float: left; width: 20%; line-height: 40px;">
							<p>fwqf**</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">최소 1개월</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">20% DC</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">2명</p>
						</div>
						<div style="float: right; width: 20%; line-height: 40px;">
							<button class="participate_button" type="button"
								onclick="go_quick_matching()">참여하기</button>
						</div>
					</div>
					<div class="matching_content">
						<div style="float: left; width: 20%; line-height: 40px;">
							<p>gkr**</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">최소 1개월</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">15% DC</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">2명</p>
						</div>
						<div style="float: right; width: 20%; line-height: 40px;">
							<button class="participate_button" type="button"
								onclick="go_quick_matching()">참여하기</button>
						</div>
					</div>
					<div class="matching_content">
						<div style="float: left; width: 20%; line-height: 40px;">
							<p>gpgp**</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">최소 3개월</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">15% DC</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">1명</p>
						</div>
						<div style="float: right; width: 20%; line-height: 40px;">
							<button class="participate_button" type="button"
								onclick="go_quick_matching()">참여하기</button>
						</div>
					</div>
					<div class="matching_content">
						<div style="float: left; width: 20%; line-height: 40px;">
							<p>pepe**</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">최소 1개월</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">30% DC</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">3명</p>
						</div>
						<div style="float: right; width: 20%; line-height: 40px;">
							<button class="participate_button" type="button"
								onclick="go_quick_matching()">참여하기</button>
						</div>
					</div>
					<div class="matching_content">
						<div style="float: left; width: 20%; line-height: 40px;">
							<p>chr**</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">최소 2개월</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">15% DC</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">2명</p>
						</div>
						<div style="float: right; width: 20%; line-height: 40px;">
							<button class="participate_button" type="button"
								onclick="go_quick_matching()">참여하기</button>
						</div>
					</div>
					<div class="matching_content">
						<div style="float: left; width: 20%; line-height: 40px;">
							<p>chr**</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">최소 4개월</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">30% DC</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">2명</p>
						</div>
						<div style="float: right; width: 20%; line-height: 40px;">
							<button class="participate_button" type="button"
								onclick="go_quick_matching()">참여하기</button>
						</div>
					</div>
					<div class="matching_content">
						<div style="float: left; width: 20%; line-height: 40px;">
							<p>ch**</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">최소 1개월</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">15% DC</p>
						</div>
						<div style="float: left; width: 20%; line-height: 40px;">
							<p style="font-size: 13px;">3명</p>
						</div>
						<div style="float: right; width: 20%; line-height: 40px;">
							<button class="participate_button" type="button"
								onclick="go_quick_matching()">참여하기</button>
						</div>
					</div>

				</div>
			</div>
			<!-- right 끝 -->
		</div>

		<br> <br> <br> <br>
		<hr style="width: 90%;">
		<br> <br> <br> <br>

		<!-- 서비스 장점 소개 -->
		<div id="sub_container">
			<div class="sub_left">
				<div class="sub_img">
					<img src="/resources/img/money.png" width="200px">
					<h3>혼자보다 더 싸게!</h3>
					<p>
						여러사람과 함께 보면서<br> 더 저렴한 가격으로 넷플릭스를 만나보세요!
					</p>
				</div>
			</div>
			<div style="float: left; width: 1px;">
				<hr style="width: 1; height: 350px;">
			</div>
			<div class="sub_right">
				<div class="sub_img">
					<img src="/resources/img/kakaoPay.png" width="200px">
					<h3>간편한 결제시스템!</h3>
					<p>
						카카오 페이를 이용한<br> 간편 결제시스템으로 시간 절약!
					</p>
				</div>
			</div>
		</div>
		<!-- 서브 컨테이너 끝  -->

		<br> <br> <br> <br> <br>

		<!-- 메인 푸터 -->
		<%@ include file="../include/home_footer.jsp"%>
	</div>
</body>
<script type="text/javascript">
	function go_quick_matching() {
		if (confirm("chlgkr**님의 빠른 매칭을 이용하시겠습니까?\n빠른매칭을 이용할 경우, 바로 매칭이 되므로\n신중히 선택해주십시오")) {
			location.href = '/views/matching/quick_matching.jsp';
		} else {
			return false;
		}
	}
</script>
</html>