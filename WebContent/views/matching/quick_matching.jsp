<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>quick_matching</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />
<link rel="stylesheet" href="/resources/css/matching/quick_matching.css" />
</head>
<body>
	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<!-- 빠른매칭 버튼 클릭 후 이동하는 페이지 화면 -->
		<div id="quick_matching_wrap">
			<div
				style="width: 60%; display: inline-block; height: 500px; padding-top: 40px;">
				<div id="success_matching" style="height: 700px;">
					<h2>넷플릭스 프리미엄 파티 매칭 성공!</h2>
					<p>하단에 있는 아이디와 비밀번호를 사용해주세요!</p><br><br> 
					<img src="../../resources/img/congratulations.png">
					<div class="party_leader">
						<p>
							<b>최학준</b>님의 파티
						</p>
						<p style="color: rgb(105, 105, 105);">파티 생성일 : 2020년 11월 9일</p>
					</div>
					<br> <br>
					<div>
						<p>
							아이디 : <b>chlgkrws</b>
						</p>
						<p>
							비밀번호 : <b>gkr981</b>
						</p>
					</div>
					<br>
					<button type="button" id="go_home" onclick="go_home()">홈으로</button><br> <br> 
					<small>*요금 정산은 매월 20일을 기준으로 계산됩니다*</small>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	function go_home() {
		alert("언제나 NetShared를 사랑해주셔서 감사합니다")
		location.href = '/index.jsp';
	}
</script>
</html>