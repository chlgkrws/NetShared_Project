<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Matching</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />
<script type="text/javascript" src="/resources/js/matching/matching.js"></script>
<style>
body {
	background-image: url('../../resources/img/luxury.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<!-- 배경 이미지 -->
	<div id="background-image"></div>

	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<!-- 빠른 매칭 페이지 -->
		<br> <br>
		<div id="matching_container">
			<h2 style="color: rgb(73, 73, 73);">빠른 매칭</h2>
			<hr style="width: 50%;">
			<p style="color: rgb(77, 77, 77);">
				빠른 매칭은 빠르게 인원을 충당하고 싶은<br> 파티장님이 파티원들의 일부 요금을<br> 정해진 몇 개월
				동안 대신 지불하는 시스템입니다.
			</p>

			<br> <br>
			<div id="quick_matching">
				<br>
				<div>
					<h4 style="color: rgb(100, 100, 100);">빠른 매칭 신청서</h4>
				</div>
				<br>
				<div class="matching_apply">
					<p>최소 개월 수 :</p>
					<input type="text" id="least_val" placeholder="2"
						style="width: 20px; display: inline-block;" />
				</div>
				<div class="matching_apply">
					<input type="text" id="dc_count" placeholder="10"
						style="width: 20px; display: inline-block;" />
					<p>% off the price</p>
				</div>

				<div class="matching_apply">
					<input type="text" id="how_many_people" placeholder="3"
						style="width: 20px; display: inline-block;" />
					<p>명 까지 참여가능</p>
				</div>
				<br>
				<button type="submit" class="radius_button"
					onclick="quick_matching()"
					style="font-size: 10px; width: 100px; height: 30px;">신청하기
				</button>
			</div>
		</div>
		<div style="margin-top: 10px; margin-bottom: 50px;">
			<small>주의사항 : 빠른매칭을 신청한 파티장님들은 다음 정산 시 <br> 신청하신
				Discount Percentage 만큼 적게 입금 됩니다.
			</small>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

	<script type="text/javascript">
		/* 빠른 매칭 신청서 유효성 검사 */
		function quick_matching() {
			var least_val = document.getElementById("least_val").value;
			var dc_count = document.getElementById("dc_count").value;
			var hmp = document.getElementById("how_many_people").value;

			if (least_val == "") {
				least_val = 2;
			}

			if (dc_count == "") {
				dc_count = 10;
			}

			if (hmp == "") {
				hmp = 2;
			}

			if (least_val > 12) {
				alert("12개월 보다 낮게 설정해주세요.");
				return false;
			}

			if (dc_count > 50) {
				alert("DC를 50%보다 낮게 설정해주세요.");
				return false;
			}

			if (hmp > 3) {
				alert("3명보다 적게 설정해주세요.");
				return false;
			}

			if (confirm(least_val + "개월 동안 " + dc_count + "% 할인된 금액을 최대 " + hmp
					+ "인 참여로 신청할까요?"))

				alert("빠른매칭 신청 성공!\n파티원들이 모이면 알림드릴게요:)");
			location.href = '/index.jsp';
		}
	</script>
</body>
</html>