<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		RequestDispatcher rd = request.getRequestDispatcher("./views/home/home.jsp");
		rd.forward(request, response);
	%>
</body>
</html>