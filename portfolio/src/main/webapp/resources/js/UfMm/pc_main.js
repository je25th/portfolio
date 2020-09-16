/**
 * pc 버전 main
 */

var page = 1;
var scrollpage = 1;

//아티클 정렬
var memoContainerNum = 1;

loadmemo();

function loadmemo() {
	//URL, SEND, FUC, fuc_PAR, HttpMethod
	ajax_getJson(URL_MAIN + '/' + page, null, displayMemolist, null, 'GET');
	
	if(location.pathname == URL_SEARCH) {
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_SEARCH + "/" + location.search.replace("?keyword=", "") + "/" + page , null, displayMemolist, null, 'GET');
	}
	else if(location.pathname == URL_SERACH_HASHTAG) {
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_SERACH_HASHTAG + "/" + location.search.replace("?hashtag=", "") + "/" + page , null, displayMemolist, null, 'GET');
	
	}
	else if(location.pathname == URL_STAR) {
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_STAR + '/' + page, null, displayMemolist, null, 'GET');
	}
}

//메모 화면에 뿌리기
function displayMemolist (dom, parsedJSON) {
    var memo = parsedJSON;
    var condiv = document.getElementById("article-container1");
    var condiv2 = document.getElementById("article-container2");
    
    //page 카운트 업
    if(memo.length > 0)
    	page++;
    
    //console.log(condiv);
    memo.forEach(function(data) {
    	//star
        var staroff = "offcolor";
        if(data.star == "1")
            staroff = "";
        
        //wowpoint
        var hashtagli = "";
        if(data.wowpoint == "1")
        	hashtagli = '<span class="wowpoint-wrap"><i class="fas fa-exclamation"></i></span>';
        else if(data.wowpoint == "2")
        	hashtagli = '<span class="wowpoint-wrap"><i class="fas fa-exclamation"></i><i class="fas fa-exclamation"></i></span>';
        else if(data.wowpoint == "3")
        	hashtagli = '<span class="wowpoint-wrap"><i class="fas fa-exclamation"></i><i class="fas fa-exclamation"></i><i class="fas fa-exclamation"></i></span>';
        
        //hashtag
        var contentli = data.hashtag.split('#')
        contentli.forEach(function(tag) {
            if(tag != "")
                hashtagli += '<span class="hashtag-wrap">#' + tag + '</span>';
        });

        //뿌림
        if(memoContainerNum == 1) {
			condiv.innerHTML += '<article id="' + data.idx + '" class="article-wrap">' +
									'<div class="top-wrap">땡땡땡메뉴버튼</div>' +
									'<div class="hashtag-container">' +
										hashtagli +
			                        '</div>' +
			                        '<div class="title-wrap">' +
			                        	data.title +
			                        '</div>' +
			                        '<div class="content-wrap">' +
			                        	data.content +
			                        '</div>' +
			                        '<div class="bottom-wrap">' +
			                        	'<span class="mdate">' + data.mdate + '</span>' +
			                        	'<span> | </span><span class="star ' + staroff + '"><i class="icon-star"></i></span>' +
			                        '</div>' +
		                        '</article>';
			
			memoContainerNum = 2;
        }
        else {
			condiv2.innerHTML += '<article id="' + data.idx + '" class="article-wrap">' +
									'<div class="top-wrap">땡땡땡메뉴버튼</div>' +
									'<div class="hashtag-container">' +
										hashtagli +
						            '</div>' +
						            '<div class="title-wrap">' +
						            	data.title +
						            '</div>' +
						            '<div class="content-wrap">' +
						            	data.content +
						            '</div>' +
						            '<div class="bottom-wrap">' +
						            	'<span class="mdate">' + data.mdate + '</span>' +
						            	'<span> | </span><span class="star ' + staroff + '"><i class="icon-star"></i></span>' +
						            '</div>' +
						        '</article>';
			
			memoContainerNum = 1;
}
    });
}
