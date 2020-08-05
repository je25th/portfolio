<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
int id = (int)request.getAttribute("id");
String userAgent = (String)request.getAttribute("userAgent");
String path = (String)request.getAttribute("path");
%> 

id : <%=id %> <br>
user_agent : <%=userAgent %><br>
path : <%=path %><br>

</body>
</html>