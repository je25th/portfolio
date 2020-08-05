<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
textarea {
    resize: none;
    overflow: hidden;
    width: 100%;
    max-width: 300px;
    border: 1;
    
	outline: 0;
}
</style>

<title>Insert title here</title>
</head>
<body>

<div>
	<textarea id="chat-room"></textarea>
</div>
<div>
	<input type="text" id="chat-w"/>
	<button id="chat-s">보내기</button>
</div>

</body>

<script>
document.getElementById("chat-s").addEventListener("click", function(e) {
	document.getElementById("chat-room").value += document.getElementById("chat-w").value;
	ajax_getJson('./chatss', 'send=10');
});

function ajax_getJson(URL, SEND, FUC, fuc_PAR) {
	var oReq = new XMLHttpRequest();
  
	oReq.onreadystatechange = function(event) {
      if(oReq.readyState == 4 && oReq.status == 200) {//서버처리끝 && 호출성공
    	  console.log(oReq.responseText);
    	  var pJSON = JSON.parse(oReq.responseText);
       	  if(!pJSON.success) {
       		  console.log("ajax fail!");
       		  return;
       	  }
       	  if(FUC != null) {
       		  FUC(fuc_PAR, pJSON.result);
       	  }
      	
      	  console.log("ajax complete");
      }
      else {
          console.log("ajax Not Responding...");
      }
      
  	}
  
  oReq.open('POST', URL, true);
  oReq.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  oReq.send(SEND);
}
</script>

</html>