<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
    
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/UfMm/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/iconset/MFGLabs/mfglabs_iconset.css">
    <link href="${pageContext.request.contextPath}/resources/iconset/fontawesome-free-5.13.0-web/css/all.css" rel="stylesheet">
    
	<title>unfolded memo</title>
</head>
<body>
<div class="container">
    <div id="momo-container" class="momo-container">
    </div>

	<nav id='btn-home' class='btn-home hide'><span class='btn btn-icon'><i class="icon-home"></i></span></nav>
    <nav class="btn-wrap">
        <ul class='display-inline'>
            <li id='btn-menu' class="btn">
                <span class='btn-icon'><i class="icon-reorder"></i></span>
            </li>
            <li id='btn-check' class='btn hide'>
                <span class='btn-icon'><i class="icon-check"></i></span>
            </li>
            <li id='btn-search' class='btn hide'>
                <span class='btn-icon'><i class="icon-magnifying"></i></span>
            </li>
            <li id='btn-star' class='btn hide'>
                <span class='btn-icon'><i class="icon-star"></i></span>
            </li>
            <li id='btn-box' class='btn hide'>
                <span class='btn-icon'><i class='icon-file_open'></i></span>
            </li>
            <li id='btn-logout' class='btn hide'> 
                <span class='btn-icon'><i class="icon-signout"></i></span>	
            </li>
            <li id='btn-write' class='btn hide'>
                <span class='btn-icon'><i class="icon-plus"></i></span>	
            </li>
        </ul>
    </nav>
    
    <!-- 서치 팝업메뉴 시작 -->
    <div id='search-popup' class='popup-wrap hide'>
	    <div class="popup-mask"></div>
	    <div id="popup-content" class="popup-search">
	    	<ul id="search-popup-close" class="long-close-btn-wrap">
	    		<li class="long-close-btn"></li>
	    	</ul>
	    	<div class="popup-search-inner-wrap">
		    	 <form id="menu_textbox" action="/unfolded-memo/search">
	            	<!-- <span id='hashtag-mode' class='hashtag-icon hide'><i class='icon-hashtag'></i></span>
	            	<span id='keyword-mode' class=''>k</span> -->
		            <input type='text' name='keyword' id='search_textbox' class='popup-textbox' placeholder="검색어">
	                <button type="submit"><span class="popup-search-btn"><i class="icon-magnifying"></i></span></button>
	             </form>
	             <div>
		             <span class="popup-search-title">내 해쉬태그 목록</span>
		             <ul id="search-hashtag-list"></ul>
	             </div>
	             <div>
		             <span class="popup-search-title">검색기록</span>
		             <ul id="search-cookies-list"></ul>
	             </div>
	    	</div>
	    </div>
    </div>
    <!-- 서치 팝업메뉴 끝 -->
    
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
<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/search.js"></script>
<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/menu.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/UfMm/mainjs.js"></script>


</body>
</html>