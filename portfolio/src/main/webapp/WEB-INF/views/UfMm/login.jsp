<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/UfMm/style.css">
    <style type="text/css">
    .wrap {
    	position: fixed;
    	width: 100%;
    	height: 100%;
    }
    
    .centerbox {
    	box-sizing: border-box;
    	width: 250px;
    	margin: 0 auto;
    	padding: 30px 20px;
    	
		background-color: white;
    	border: 1px solid lightgray;
    	
		position: absolute;
	    top: 50%;
	    left: 50%;
	    transform: translate(-50%,-100%);
	    -webkit-transform: translate(-50%,-100%);
    }
    
    .login {
    	width: 100%;
    	height: 30px;
    	margin: 0 0 10px 0;
    	
    	outline: none;
    }
    
    </style>
	<title>login</title>
</head>
<body>
<%
if(session.getAttribute("user_idx") != null) {
	session.removeAttribute("user_idx");
	//session.invalidate();
	System.out.println("logout!");
}
%>
<div class='wrap'>
	<div class='centerbox'>
		<form name="insert" action="./login" method="post">
			<input type="text" class="login" name="id" maxlength="10" value="testid"><br>
			<input type="password" class="login" name="pw" maxlength="20" value="1234"><br>
			<input type="submit" class="login" value="로그인">
		</form>
	</div>
</div>
</body>
</html>