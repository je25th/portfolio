<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/UfMm/pc_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/iconset/MFGLabs/mfglabs_iconset.css">
    <link href="${pageContext.request.contextPath}/resources/iconset/fontawesome-free-5.13.0-web/css/all.css" rel="stylesheet">
    
	<title>unfolded memo</title>
</head>
<body>
<div class="container">
	<aside class="aside">
		<nav class="nav"><ul>
			<li class="on"><i class="icon-home"></i></li>
			<li><i class="icon-check"></i></li>
			<li><i class="icon-star"></i></li>
			<li><i class='icon-file_open'></i></li>
			<li class="logout"><i class="icon-signout"></i></li>
		</ul></nav>
		<div class="search-container">
			<div class="wrap"><input type="text" class="textbox"><button><i class="icon-magnifying"></i></button></div>
			<div class="wrap">
				<span class="title">태그목록</span>
				<span class="hashtag-wrap">#해쉬태그</span><span class="hashtag-wrap">#해쉬태그</span>
			</div>
		</div>
	</aside><div id="momo-container" class="momo-container"><div id="article-container1" class="article-container"><article class="article-wrap">
																<div class="hashtag-container"><span class="hashtag-wrap">#</span></div>
																<div class="title-wrap"><input type="text"></div>
																<div class="content-wrap"><textarea></textarea></div>
																<div class="bottom-wrap">완료</div>
															</article>
    </div></div>
    <div id="article-container2" class="article-container"></div>
</div>

<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/common.js"></script>
<script type="text/Javascript" charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/UfMm/url.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/UfMm/pc_main.js"></script>

</body>
</html>