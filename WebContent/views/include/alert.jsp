<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>alert</title>
</head>
<body>
	<%
		String msg = (String) request.getAttribute("msg");
		String path = (String) request.getAttribute("path");
	  	out.println("<script>alert('" +msg+ "');  location.replace('"+path+"');</script>");
	%>
</body>
</html>