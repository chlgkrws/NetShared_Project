<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MyPage</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />
<link rel="stylesheet" href="/resources/css/home/my_page.css" />
</head>
<body>
	<c:set var="user" value="${userVO}"></c:set>

	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<!-- 내 정보를 볼 수 있는 페이지 -->
		<div id="my_page_container">
			<h2>개인 정보 관리</h2>

			<br>
			<br>

			<div>
				<img src="../../resources/img/share.png" style="width: 120px;" />
			</div>

			<br>

			<h3 style="font-weight: 500;">${user.userName }님</h3>
			<p style="margin-top: 30px; margin-bottom: 20px;">NetShared의 계정
				정보를 확인하고 안전하게 관리해주세요</p>

			<br>

			<hr style="width: 80%;">

			<br>

			<!-- 개인 정보 -->
			<div id="my_page_content">
				<div class="my_page_left">
					<h3 style="font-weight: 500;">전화번호</h3>
				</div>
				<div class="my_page_right">
					<h3>${user.phone}</h3>
				</div>
				<div class="my_page_left">
					<h3 style="font-weight: 500;">계좌번호</h3>
				</div>
				<div class="my_page_right">
					<h3>${user.account }</h3>
				</div>
				<div class="my_page_left">
					<h3 style="font-weight: 500;">비밀번호</h3>
				</div>
				<div class="my_page_right">
					<h3>ㆍㆍㆍㆍㆍㆍㆍ</h3>
				</div>
			</div>

			<hr style="width: 80%;">

			<h3 style="text-decoration: underline; color: rgb(189, 189, 189);">
				<a href='javascript:void(0);' onclick="wouldDelete();">회원 탈퇴</a>
			</h3>
		</div>
	</div>
</body>

<script type="text/javascript">
	function wouldDelete() {
		if (confirm("정말 탈퇴하시겠습니까?")) {
			location.href = 'withdrawuser.do';
		} else {
			return;
		}
	}
</script>
</html>