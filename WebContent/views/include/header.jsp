<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nav</title>
</head>
<body>
	<!-- 네비게이션 바 -->
	<header style="margin-bottom: 80px;">
		<div id="header">
			<!-- NetShared -->
			<div id="logo" style="float: left; width: 33%; line-height: 80px;">
				<div>
					<a href="/index.jsp" style="float: left; margin-left: 10%;">NetShared</a>
				</div>
				<!-- style="float:left; width: 10%; line-height: 80px;" -->
			</div>
			
			<!-- 소개 커뮤니티 빠른 매칭 -->
			<div id="contents"
				style="float: left; width: 33%; line-height: 80px;">
				<div>
					<a href="/views/home/page_intro.jsp"
						style="float: left; width: 33%; line-height: 80px;">소개</a>
				</div>
				<div>
					<a href="/boardlist.do?num=1"
						style="float: left; width: 33%; line-height: 80px;">커뮤니티</a>
				</div>
				<div>
					<a href="/views/matching/matching.jsp"
						style="float: left; width: 33%; line-height: 80px;">빠른매칭</a>
				</div>
			</div>

			<!-- 로그인, 로그아웃, 마이페이지 -->
			<%
				if (session.getAttribute("id") == null) {
			%>
			<div id="login" style="float: right; width: 33%; line-height: 80px;">
				<div>
					<a href="/views/login/login.jsp"
						style="font-size: 15px; float: right; margin-right: 10%;">로그인</a>
				</div>
			</div>
			<%
				} else {
			%>
			<div id="login" style="float: right; width: 33%; line-height: 80px;">
				<div>
					<a href="/views/login/logout.jsp" id="logout"
						style="font-size: 10px; float: right; margin-right: 15px;">로그아웃</a>
					<a href="/mypage.do"><img src="/resources/img/myPage.png"
						style="width: 60px; float: right; margin-right: 2%; margin-top: 18px;"></a>
					<a style="font-size: 15px; float: right; margin-right: 15px;"><%=session.getAttribute("username")%>님
						안녕하세요 </a>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</header>
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script>
		$(document).ready(function() {

		});
	</script>
</body>
</html>