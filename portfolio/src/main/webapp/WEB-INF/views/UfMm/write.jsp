<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.projects.je25th.UfMm.dto.Memo"%>
<%@ page import="com.projects.je25th.UfMm.dto.Hashtag"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
    
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/UfMm/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/iconset/MFGLabs/mfglabs_iconset.css">
    <link href="${pageContext.request.contextPath}/resources/iconset/fontawesome-free-5.13.0-web/css/all.css" rel="stylesheet">
    
	<title>write</title>
</head>
<body>

<% 
Memo memoData = null;
if(request.getAttribute("memo") != null) {
    memoData = (Memo)request.getAttribute("memo");
}
%>

<div class="container">
    <header class="write-top">
        <button id='cancel' class='btn-default write-btn write-btn-cancel'><i class='icon-arrow_left'></i></button>
        <button id='finish' class='btn-default write-btn write-btn-finish'>수정</button>
    </header>
    <div class="write-content">
        <div class="write-content-title">
            <textarea id="title" name="title" class="textarea-title title" style="height: 20px;" readonly><%=(memoData!=null)?memoData.getTitle():""%></textarea>
        </div>
        <div class="write-content-textarea">
            <textarea id="content" name="content" class="textarea-content content" style="height: 300px;" readonly><%=(memoData!=null)?memoData.getContent():""%></textarea>
        </div>
        <div class="write-content-add">
            <div id="hashtag-textbox" class="add-hashtage hide">
                <i class="icon-hashtag write-tagicon"></i> 
                <input type="text" id="search-hashtag" class="hashtag write-search-hashtag">
                <button id="add-hashtag" class='btn-default hide'>추가</button>
            </div>
            <ul id="selected-hashtag-list">
	            <%
		        if(memoData != null) {
		        %>
		        	<li class='inline wicon'><span id="star"><i class='icon-star memo-star<%=(memoData.getStar()==0)? " offcolor" : ""%>'></i></span></li>
		        <%
		        	String wowhtml = "";
		        	for(int i=0; i<memoData.getWowpoint(); i++) {
		        		wowhtml += "<i class='fas fa-exclamation'></i>";
		        	}
		        	if(memoData.getWowpoint() > 0) {
		        %>
		        	<li class="inline wicon"><span id="wowpoint" class="memo-wowpoint"><%=wowhtml%></span></li>
		        <%
		        	}
		        	
		        	int size = memoData.getHashtaglist().size();
		            for(int i=0; i<size; i++) {
		        %>
	            	<li id='selected_id<%=memoData.getHashtaglist().get(i).getIdx()%>' class='hashtag hashtag-search inline'>#<%=memoData.getHashtaglist().get(i).getHashtag()%></li>
	            <%
		            }
	            }
	            %>
            </ul>
            <ul id="hashtag-list"></ul>
            <ul id="hashtag-search-list" class="hide"></ul>
        </div>
    </div>
    <footer class='mdate write-etc'>
    	<%=(memoData != null)? memoData.getMdate().toString() : ""%>
    	<button id='delete' class='btn-default write-btn write-btn-delete'><i class='icon-trash_can'></i></button>
    </footer>
    <!-- 삭제확인 팝업 시작 -->
    <div id='delete-popup' class='popup-wrap hide'>
	    <div class='popup-mask'></div>
	    <div class='popup-delete'>
	    	<div class='popup-delete-info'>
	    		<div class='info-content'>
		    		<p class='bold'>메모삭제</p>
		    		<p>삭제하시겠습니까?</p>
	    		</div>
	    	</div>
	    	<div class='popup-delete-btn'>
	    		<button id='popup-btn-cancel' class='btn-default popup-delete-btn-cancel'>취소</button>
	    		<button id='popup-btn-delete' class='btn-default popup-delete-btn-delete'>삭제</button>
	    	</div>
	    </div>
    </div>
    <!-- 삭제확인 팝업 끝 -->
</div>

<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/common.js"></script>
<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/url.js"></script>
<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/write.js"></script>

</body>
</html>