<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write/modify</title>
</head>
<body>
	<!-- 글쓰기 모달창 -->
	<div class="replyModal">
		<div class="modalContent">
			<form action="/boardwrite.do" method="post">
				<div id="title"
					style="height: 110px; padding: 10px; background-color: rgb(201, 255, 234);">
					<div style="width: 10%;">
						<h3
							style="margin: 0px; margin-bottom: 10px; color: rgb(88, 88, 88); font-size: 18px;">제목</h3>
					</div>
					<div class="info_content" style="width: 97%;">
						<input type="text" placeholder="" name="title"
							style="width: 100%; height: 20px; margin: 0px; padding: 0px; padding-top: 3px;"
							value="${board.title }" />
					</div>
					<div id="write_info">
						<div class="write_left">
							<div class="info_content">
								<p>별점</p>
							</div>
							<div class="info_content">
								<select name="star">
									<option value="5">★★★★★</option>
									<option value="4">★★★★</option>
									<option value="3">★★★</option>
									<option value="2">★★</option>
									<option value="1">★</option>
								</select>
							</div>
						</div>
						<div class="write_right">

							<div class="info_content">
								<p>장르</p>
							</div>
							<select name="genre" autofocus="${board.genre }">
								<option value="액션">액션</option>
								<option value="범죄">범죄</option>
								<option value="SF">SF</option>
								<option value="코미디">코미디</option>
								<option value="로맨스">로맨스</option>
								<option value="기타">기타</option>
							</select>
						</div>
					</div>
				</div>
				<div>
					<textarea class="modal_repCon" name="modal_content">${board.content }</textarea>
				</div>
				<div>
					<input type="hidden" name="id" value="${session_id }"> <input
						type="hidden" name="username" value="${session_name }"> <input
						type="hidden" name="valid" value="${board.valid }" /> <input
						type="hidden" name="boardId" value="${board.boardId }" /> <input
						type="hidden" name="boardId" value="${board.writer }" />
					<button type="submit" class="modal_write_btn">작성</button>
					<button type="button" class="modal_cancel">취소</button>
				</div>
			</form>
		</div>
		<div class="modalBackground"></div>
	</div>
</body>

<!-- 모달창 버튼 리스너/작성, 취소 -->
<script type="text/javascript">
	$(document).on("click", "#write_content", function() {
		$(".replyModal").attr("style", "display:block;");
	});

	$(".modal_cancel").click(function() {
		$(".replyModal").attr("style", "display:none;");
	});
</script>
</html>