<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 로그아웃 후 세션 초기화 -->
	<%
		session.setAttribute("id", null);
		session.setAttribute("username",null);
		
		response.sendRedirect("../../index.jsp");
	/* 	RequestDispatcher rd = request.getRequestDispatcher("../../index.jsp");
		rd.forward(request, response); */
	%>
	<input type="text" id="error" style="display: hidden" value="<%request.getAttribute("error");%>"/>
		
	<script type="text/javascript">
		var error = document.getElementById("error").value;
		
		if(error != null){
			alert(error);
		}
		
		if(error == null){
			alert(error);
		}
	</script>
</body>
</html>