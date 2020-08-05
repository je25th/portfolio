<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.projects.je25th.NgGg.dto.Guestbook"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 목록</title>
</head>
<body>
<%
List<Guestbook> gb = (List<Guestbook>)request.getAttribute("list");
List<Integer> psl = (List<Integer>)request.getAttribute("pageStartList");
%>
	<h1>방명록</h1>
	<br> 방명록 전체 수 : <%=request.getAttribute("count") %>
	<br>
	<br>

	<%for(int i=0; i<gb.size(); i++) { %>

		<%=gb.get(i).getIdx() %><br>
		<%=gb.get(i).getName() %><br>
		<%=gb.get(i).getContent() %><br>
		<%=gb.get(i).getRegdate() %><br>

	<%} %>
	<br>

	<%for(int i=0; i<psl.size(); i++) { %>
		<a href="list?start=<%=psl.get(i)%>"><%=(psl.get(i) + 1)/5 + 1%></a>&nbsp; &nbsp;
	<%} %>

	<br>
	<br>
	<form method="post" action="write">
		name : <input type="text" name="name"><br>
		<textarea name="content" cols="60" rows="6"></textarea>
		<br> <input type="submit" value="등록">
	</form>
</body>
</html>