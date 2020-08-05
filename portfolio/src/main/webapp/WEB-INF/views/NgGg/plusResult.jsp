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
int value1 = (int)request.getAttribute("value1");
int value2 = (int)request.getAttribute("value2");
int result = (int)request.getAttribute("result");
%> 

<%=value1%> 더하기 <%=value2%> (은/는) <%=result%> 입니다.

</body>
</html>