<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>write</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />
<link rel="stylesheet" href="/resources/css/board/post.css" />
<link rel="stylesheet" href="/resources/css/board/community.css" />
</head>
<body>
	<!-- 컨트롤러에서 받아온 값 변수로 저장 -->
	<c:set var="board" value="${board }"></c:set>
	<c:set var="id" value='<%=session.getAttribute("id")%>'></c:set>

	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<div id="post_container">
			<div class="post_content">
				<h2 style="margin-top: 60px; margin-bottom: 30px;">작성글 상세보기</h2>
			</div>

			<!-- 세션의 아이디와 게시글을 쓴 유저의 아이디가 같으면 수정/삭제 출력 -->
			<c:if test="${id eq board.userId}">
				<div style="margin-bottom: 10px; height: 30px">
					<div class="modify_delete">
						<form action="/boardDelete.do" method="get"
							onsubmit="return delete_post();">
							<input type="hidden" name="boardId" value='${board.boardId }'>
							<button type="submit" id="delete" class="post_list">삭제</button>
						</form>
					</div>
					<div class="modify_delete">
						<button type="button" id="write_content" class="post_rec">수정</button>
					</div>
				</div>
			</c:if>

			<!-- EL로 게시글 데이터 출력 -->
			<hr class="content_boundary">
			<div style="display: inline-block; width: 95%;">
				<div class="post_content" style="margin-top: 30px;">
					<h3>${board.title }</h3>
					<p style="font-size: 16px; margin-top: 30px; margin-bottom: 30px;">
						<b>작성자</b> &nbsp; ${board.writer } &nbsp; I &nbsp; <b>등록일</b>
						&nbsp; ${board.createdTime } &nbsp;I &nbsp; <b>추천수</b> &nbsp;
						${board.recommend } &nbsp;I &nbsp; <b>별점</b> &nbsp;
						${board.starRate }점 &nbsp;I &nbsp; <b>장르</b> &nbsp; ${board.genre }
					</p>
					<hr style="margin-bottom: 30px;">
					<pre style="font-weight: 600; font-size: 25px;"><c:out value="${board.content }" escapeXml="false">
					</c:out>
					</pre>
				</div>
			</div>
			<hr class="content_boundary" style="margin-bottom: 20px;">
			<div style="float: right;">
				<div class="modify_delete">
					<button type="button" class="post_list"
						onclick="location.href='/boardList.do?num=1'">목록</button>
					<div class="modify_delete"></div>
				</div>
				<div class="modify_delete">
					<!-- 자신 게시물이나, 로그인이 안되어있으면 추천버튼 비활성화 -->
					<c:if test="${id ne board.userId }">
						<c:if test="${id ne null }">
							<form action="likeUp.do" method="post" style="height: 30px;">
								<input type="hidden" name="id" value="${id }" /> <input
									type="hidden" name="boardId" value="${board.boardId }" />
								<button type="submit" class="post_rec">추천</button>
							</form>
						</c:if>
					</c:if>
				</div>
				<br> <br> <br> <br>
			</div>
		</div>
	</div>
	<!-- 글작성 모달창  -->
	<%@include file="../include/write_post.jsp"%>
</body>

<!--게시물 삭제 이벤트 -->
<script type="text/javascript">
	function delete_post() {
		if (confirm("정말 삭제 하시겠습니까?")) {
			return true;
		} else {
			return false;
		}
	}
</script>
</html>