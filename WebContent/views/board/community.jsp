<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.it1457.vo.BoardVO"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Community</title>
<link rel="stylesheet" href="/resources/css/home/home.css" />
<link rel="stylesheet" href="/resources/css/board/community.css" />
</head>
<body>
	<!-- 컨트롤러에서 보내준 페이지네이션 정보와 session값 -->
	<c:set var="page" value="${page }" />
	<c:set var="session_id" value='<%=session.getAttribute("id")%>'></c:set>
	<c:set var="session_name" value='<%=session.getAttribute("username")%>'></c:set>

	<div id="frame">
		<!-- 헤더 정보 -->
		<%@include file="../include/header.jsp"%>

		<br> <br> <br>

		<!-- 게시물 정보 -->
		<div id="community_container">
			<div id="table1" style="display: block;">
				<table class="board_table">
					<tr style="border: 1px solid gray; height: 50px;">
						<td class="board_num">No</td>
						<td class="board_title">제목</td>
						<td class="board_writer">작성자</td>
						<td class="board_time">작성 시간</td>
						<td class="board_recommend">추천</td>
					</tr>
					<!-- 게시물 목록 -->
					<c:forEach var="list" items="${list }">
						<tr class="page_one">
							<td style="height: 50px;"><c:out value="${list.boardId }"></c:out></td>
							<td><a href="/boardView.do?boardId=${list.boardId}">${list.title}</a></td>
							<td>${list.writer }</td>
							<td>${list.createdTime }</td>
							<td>${list.recommend }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<!-- 페이지네이션 -->
		<div style="display: inline-block; text-align: center; width: 100%">
			<c:if test="${page.prev }">
				<span>[<a href="/boardList.do?num=${page.startPageNum - 1}">이전</a>]
				</span>
			</c:if>
			<c:forEach begin="${page.startPageNum }" end="${page.endPageNum}"
				var="num">
				<span> <c:if test="${select != num}">
						<a href="/boardList.do?num=${num}">${num}</a>
					</c:if> <c:if test="${select == num}">
						<b>${num}</b>
					</c:if>
				</span>
			</c:forEach>
			<c:if test="${page.next }">
				<span>[<a href="/boardList.do?num=${page.endPageNum + 1}">다음</a>]
				</span>
			</c:if>
		</div>

		<!-- 세션이 있을 때만 글 작성 버튼 표시 -->
		<c:if test="${session_id != null}">
			<div id="do_write">
				<button type="button" id="write_content">글작성</button>
			</div>
		</c:if>

		<br> <br> <br> <br>
	</div>

	<!-- 글작성 모달창  -->
	<%@include file="../include/write_post.jsp"%>
</body>
</html>