<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/UfMm/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/UfMm/popup.css">
    <style type="text/css">
    body {
    	background: seashell;
    }
    
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
    
    .btn {
    	width: 100%;
    	height: 30px;
    	margin: 0 0 10px 0;
    	
    	outline: none;
    }
    
    a.join {
    	cursor: pointer;
    	
    	text-decoration: underline;
    	font-size: 14px;
    	color: gray;
    }
    
    form input {
    	box-sizing: border-box;
    	
    	margin : 3px 0;
    	padding: 5px 3px;
    	width: 100%;
    	
    	font-size: 16px;
    	
    	border: 1px solid lightgray;
    }
    
    .join-section {
    	padding: 5px 0px;
    }
    
    .section-name {
    	display: block;
    	
    	padding-bottom: 3px;
    	
    	font-size: 13px;
    	color: gray;
    }
    
    .section-check {
    	display: block;
    	
    	font-size: 13px;
    	text-align: right;
    	
    	color: red;
    }
    
    .check-ok {
    	color: green;
    }
    
    .section-btn {
    	margin-top: 15px;
    	
    	width: 100%;
    	height: 40px;
    	
    	outline: none;
    	border: none;
    	
    	background-color: #5882FA;
    	color: white;
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
			<input type="text" name="id" maxlength="10" value="testid"><br>
			<input type="password" name="pw" maxlength="20" value="1234"><br>
			<input type="submit" class="btn" value="로그인">
		</form>
		<a id="join-btn" class="join"><b>회원가입</b></a>
	</div>
</div>

	
<div id='join' class='popup-wrap hide'>
	<div class='popup-mask'></div>
	<div class='popup-contents'>
		<form name="join" action="./join" method="post">
			<div class="join-section">
				<label class="section-name"><b>이메일 주소</b></label>
				<input type="text" name="email" id="join-email" class="join">
				<label id="label-email" class="section-check">이메일을 입력해주세요.</label>
			</div>
			<div class="join-section">
				<label class="section-name"><b>아이디</b></label>
				<input type="text" name="id" id="join-id" class="join">
				<label id="label-id" class="section-check">아이디를 입력해주세요.</label>
			</div>
			<div class="join-section">
				<label class="section-name"><b>비밀번호</b></label>
				<input type="password" name="pw" id="join-pw" class="join"><br>
				<label id="label-pw" class="section-check">비밀번호를 입력해주세요.</label>
			</div>
			<div class="join-section">
				<label class="section-name"><b>닉네임</b></label>
				<input type="text" name="nickname" id="join-nickname" class="join"><br>
				<label id="label-nickname" class="section-check">닉네임을 입력해주세요.</label>
			</div>
			<input type="submit" class="section-btn" value="회원가입">
		</form>
	</div>
</div>

<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/common.js"></script>
<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/url.js"></script>
<script>

//회원가입 팝업 띄움
document.getElementById("join-btn").addEventListener("click", function(){
	console.log("click");
	popupOpen();
});
//닫기
document.querySelector(".popup-mask").addEventListener("click", function() {
	popupClose();
});

function popupOpen() {
    //마스크 켬
    var popup = document.getElementById("join");
    popup.classList.add("popup-show");
    popup.classList.remove("hide");
    
    console.log("open");
}

function popupClose() {
    //마스크
    var popup = document.getElementById("join");
    popup.classList.remove("popup-show");
    popup.classList.add("hide");
    
    console.log("close");
}


//중복확인
document.getElementById("join-email").addEventListener("keyup", function(e) {
	var label = document.getElementById("label-email");
	var order = 0;
	switch(order) {
	case 0 :
		if(e.srcElement.textLength == 0) {
			checkLabelToRed(label);
			label.textContent = "이메일을 입력해주세요.";
			break;
		}
		else
			order++;
	case 1 ://중간에 @가 들어가야함
		var chk = e.srcElement.value.indexOf("@");
		if(chk < 1 || chk+2 > e.srcElement.textLength) {
			checkLabelToRed(label);
			label.textContent = "잘못된 이메일 형식입니다.";
			
			break;
		}
		else
			order++;
	case 2 :
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_JOIN + "/check/email?email=" + e.srcElement.value, null, checkEmail, label, 'GET');
		break;
	}	
});
document.getElementById("join-id").addEventListener("keyup", function(e) {
	var label = document.getElementById("label-id");
	
	var order = 0;
	switch(order) {
	case 0 :
		if(e.srcElement.textLength == 0) {
			checkLabelToRed(label);
			label.textContent = "아이디를 입력해주세요.";
			break;
		}
		else
			order++;
	case 1 :
		if(e.srcElement.textLength < 4 || e.srcElement.textLength > 10) {
			checkLabelToRed(label);
			label.textContent = "아이디는 4자 이상 10자 이내로 입력해주세요.";
			
			break;
		}
		else
			order++;
	case 2 :
		var chk = true;
		for(var i=0; i<e.srcElement.textLength; i++) {
			//아스키코드로 변환
			//숫자 48~57
			//소문자 97~122
			if(e.srcElement.value[i].charCodeAt() < 48 
					|| (e.srcElement.value[i].charCodeAt() > 57 && e.srcElement.value[i].charCodeAt() < 97)
					|| e.srcElement.value[i].charCodeAt() > 122) {
				console.log(e.srcElement.value[i].charCodeAt());
				checkLabelToRed(label);
				label.textContent = "아이디에는 영문(소문자)과 숫자만 사용가능합니다.";
				
				chk = false;
				break;
			}
		}
		if(!chk)
			break;
		else
			order++;
	case 3 :
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_JOIN + "/check/id?id=" + e.srcElement.value, null, checkId, label, 'GET');
		break;
	}
});
document.getElementById("join-pw").addEventListener("keyup", function(e) {
	var label = document.getElementById("label-pw");
	
	var order = 0;
	switch(order) {
	case 0 :
		if(e.srcElement.textLength == 0) {
			checkLabelToRed(label);
			label.textContent = "비밀번호를 입력해주세요.";
			break;
		}
		else
			order++;
	case 1 :
		if(e.srcElement.textLength < 4) {
			checkLabelToRed(label);
			label.textContent = "비밀번호는 4자 이상으로 입력해주세요.";
			
			break;
		}
		else
			order++;
	case 2 :
		var chk = true;
		for(var i=0; i<e.srcElement.textLength; i++) {
			//아스키코드로 변환
			//숫자 48~57
			//소문자 97~122
			if(e.srcElement.value[i].charCodeAt() < 48 
					|| (e.srcElement.value[i].charCodeAt() > 57 && e.srcElement.value[i].charCodeAt() < 97)
					|| e.srcElement.value[i].charCodeAt() > 122) {
				console.log(e.srcElement.value[i].charCodeAt());
				checkLabelToRed(label);
				label.textContent = "비밀번호에는 영문(소문자)과 숫자만 사용가능합니다.";
				
				chk = false;
				break;
			}
		}
		if(!chk)
			break;
		else
			order++;
	case 3 :
		checkLabelToGreen(label);
		label.textContent = "사용 가능한 비밀번호";
		break;
	}
});
document.getElementById("join-nickname").addEventListener("keyup", function(e) {
	var label = document.getElementById("label-nickname");
	
	//닉네임은 10자 이내료 입력해주세요.
	var order = 0;
	switch(order) {
	case 0 :
		if(e.srcElement.textLength == 0) {
			checkLabelToRed(label);
			label.textContent = "닉네임을 입력해주세요.";
			break;
		}
		else
			order++;
	case 1 :
		if(e.srcElement.textLength > 10) {
			checkLabelToRed(label);
			label.textContent = "닉네임은 10자 이내로 입력해주세요.";
			
			break;
		}
		else
			order++;
	case 2 :
		checkLabelToGreen(label);
		label.textContent = "사용 가능한 닉네임";
		break;
	}
});

function checkEmail(label, parsedJSON) {
	var result = parsedJSON[0];
	console.log(parsedJSON[0]);
	if(result) {
		checkLabelToGreen(label);
		label.textContent = "사용 가능한 이메일";
	}
	else {
		checkLabelToRed(label);
		label.textContent = "사용할 수 없는 이메일입니다.";//중복이메일
	}
}
function checkId(label, parsedJSON) {
	var result = parsedJSON[0];
	console.log(parsedJSON[0]);
	if(result) {
		checkLabelToGreen(label);
		label.textContent = "사용 가능한 아이디";
	}
	else {
		checkLabelToRed(label);
		label.textContent = "사용할 수 없는 아이디입니다.";//중복아이디
	}
}

function checkLabelToGreen(label) {
	if(!label.classList.contains("check-ok"))
    	label.classList.add("check-ok");
}
function checkLabelToRed(label) {
	label.classList.remove("check-ok");
}

</script>

</body>
</html>