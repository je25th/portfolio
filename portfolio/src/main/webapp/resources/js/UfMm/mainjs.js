/**
 * main
 */
var ori_wid = "50px";
var wid = "90%";

var hide_btn_class = "hide";

var lastScrollTop = 0;//메뉴닫기할때 필요

var page = 1;
var scrollpage = 1;

//삭제버튼 보관버튼용
var SelectedArticle = null;

addMenuEvt();
loadmemo();

function loadmemo() {
	if(location.pathname == URL_MAIN) {//.search("modify")
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_MAIN + '/' + page, null, displayMemolist, null, 'GET');
	}
	else if(location.pathname == URL_SEARCH) {
		//홈 버튼(main으로)
		var home = document.getElementById("btn-home");
		home.addEventListener("click", function() {
			window.location.href = URL_MAIN;
		});
		//뒤로가기 버튼 켬
		home.classList.remove(hide_btn_class);
		
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_SEARCH + "/" + location.search.replace("?keyword=", "") + "/" + page , null, displayMemolist, null, 'GET');
	}
	else if(location.pathname == URL_SERACH_HASHTAG) {
		//홈 버튼(main으로)
		var home = document.getElementById("btn-home");
		home.addEventListener("click", function() {
			window.location.href = URL_MAIN;
		});
		//뒤로가기 버튼 켬
		home.classList.remove(hide_btn_class);
		
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_SERACH_HASHTAG + "/" + location.search.replace("?hashtag=", "") + "/" + page , null, displayMemolist, null, 'GET');
	
	}
	else if(location.pathname == URL_STAR) {
		//뒤로가기 버튼(main으로)
		var home = document.getElementById("btn-home");
		home.addEventListener("click", function() {
			window.location.href = URL_MAIN;
		});
		//뒤로가기 버튼 켬
		home.classList.remove(hide_btn_class);
		
		//URL, SEND, FUC, fuc_PAR, HttpMethod
		ajax_getJson(URL_STAR + '/' + page, null, displayMemolist, null, 'GET');
	}
}

//스위프 삭제 보관함 버튼 숨기기
function article_btn_close(article) {
	if(article == null)
		return false;
	
	if(article.firstElementChild.childNodes[2].style.transform != "none") {
		article.firstElementChild.childNodes[2].style.transform = "none";
		article.firstElementChild.childNodes[0].style.width = "0";
		article.firstElementChild.childNodes[0].childNodes.forEach(function(item) {
			item.classList.add(hide_btn_class);
		});
		article.firstElementChild.childNodes[1].style.width = "0";
		article.firstElementChild.childNodes[1].childNodes.forEach(function(item) {
			item.classList.add(hide_btn_class);
		});
		
		SelectedArticle = null;
		
		return true;
	}
	
	return false;
}

function addMenuEvt() {
	//각종 클릭 이벤트 등록
	tapEvt(function(e) {
		var p = e.target;
		
		//팝업 버튼
		//찐삭제버튼 클릭
		if(p.id == "popup-btn-delete") {
			if(SelectedArticle == null) return;
			
			//URL, SEND, FUC, fuc_PAR, HttpMethod
			ajax_getJson(URL_DELETE + '/' + SelectedArticle.id, null, function(){
				SelectedArticle.parentElement.removeChild(SelectedArticle);
				
				//팝업닫기
				deletePopupClose();
				SelectedArticle = null;
			    //article_btn_close(SelectedArticle);
			}, null, 'DELETE');
			//SelectedArticle.firstElementChild.firstElementChild.style.transform = "translate3d(-100%, 0px, 0px)";
			//SelectedArticle.parentElement.removeChild(SelectedArticle);
			
		    return;
		}
		//취소버튼 클릭
		if(p.id == "popup-btn-cancel" || p.id == "popup-btn-delete") {
			deletePopupClose();
		    article_btn_close(SelectedArticle);
		}
		
		//서치 팝업 닫기
		if(p == document.getElementById("search-popup").firstElementChild) {
			searchPopupClose()
			menu_close();
		    
		    return;
		}
		
		//삭제 팝업 닫기
		if(p == document.getElementById("delete-popup").firstElementChild) {			
			deletePopupClose();
		    article_btn_close(SelectedArticle);
		    
		    return;
		}
		
		//console.log(p);
		while(p.parentElement != null) {
			p = p.parentElement;
			
			//상세페이지로
			if(p.localName == "article") {
				//스위프 삭제버튼
				if(e.target.className == "swipe-delete" || (e.target.parentElement != null && e.target.parentElement.className == "swipe-delete")) {
					deletePopupOpen();
					console.log(p.id + " delete");
					SelectedArticle = p;
					
					return;
				}
				//스위프 보관함버튼
				if(e.target.className == "swipe-box" || (e.target.parentElement != null && e.target.parentElement.className == "swipe-box")) {
					SelectedArticle = p;
					return;
				}
				
				//스위프 버튼 끔
				if(article_btn_close(SelectedArticle)) return;
				
				var memo_idx = p.getAttribute("id");
			    window.location.href = URL_VIEW + '/' + memo_idx;
			    
				break;
			}
			
			//별 토글
			if(p.className == "starwrap") {
				var staricon = p.firstElementChild;
				var starvalue = "1";//현재상태 off
				if(staricon.classList.toggle("offcolor"))
					starvalue = "0";
				//URL, SEND, FUC, fuc_PAR, HttpMethod
				ajax_getJson(URL_STAR + '/' + p.offsetParent.id, null, null, null, 'PATCH');
				
				return;
			}
			
			//해쉬태그 선택 -> 검색창
			if(hashtagListClickEvt(p))
				return;
		}
		
		
	}, function(e) {//right
		//console.log(e);//parentElement
		var p = e.target;
		while(p.parentElement != null) {
			p = p.parentElement;
			if(p.localName == "article") {
				if(article_btn_close(p)) return;
				
				//memo-inner
				p.firstElementChild.childNodes[2].style.transform = "translate3d(+30%, 0px, 0px)";
				//swipe-box
				p.firstElementChild.childNodes[0].style.width = "30%";
				p.firstElementChild.childNodes[0].childNodes.forEach(function(item) {
					item.classList.remove(hide_btn_class);
				});
				SelectedArticle = p;
				
				break;
			}
		}
		
	}, function(e) {//left
		//console.log(e);
		var p = e.target;
		while(p.parentElement != null) {
			p = p.parentElement;
			if(p.localName == "article") {
				if(article_btn_close(p)) return;
				
				//memo-inner
				p.firstElementChild.childNodes[2].style.transform = "translate3d(-30%, 0px, 0px)";
				//swipe-delete
				p.firstElementChild.childNodes[1].style.width = "30%";
				p.firstElementChild.childNodes[1].childNodes.forEach(function(item) {
					item.classList.remove(hide_btn_class);
				});
				SelectedArticle = p;
				
				break;
			}
		}
	});
	
	//스크롤
	window.addEventListener("scroll", function(e) {
        //메모 불러오기
		var scrollHeight = e.target.scrollingElement.scrollHeight;
		var nowscroll = e.target.scrollingElement.scrollTop + e.target.scrollingElement.clientHeight;
		if(scrollHeight - nowscroll < 50 && scrollpage+1 == page) {
			scrollpage++;
			loadmemo();
		}
		
		//메뉴 닫기 
		var scrollTop = e.target.scrollingElement.scrollTop;
		if(Math.abs(scrollTop-lastScrollTop) < 50) return;

        lastScrollTop = scrollTop;
        menu_close();
        
        //스위프 보관함 삭제버튼 닫기
        article_btn_close(SelectedArticle);
		
//		console.log("---------------------------------");
//		console.log("clientHeight:" + e.target.scrollingElement.clientHeight);
//		console.log("scrollHeight:" + e.target.scrollingElement.scrollHeight);
//		console.log("scrollTop:" + e.target.scrollingElement.scrollTop);
//		console.log("clientHeight + scrollTop:" + (e.target.scrollingElement.scrollTop + e.target.scrollingElement.clientHeight));
	})
	
	//서치 팝업 텍스트박스 이벤트
	searchBoxKeyupEvt();
	
	//서치 팝업 닫기
	//는 위에 있음
	
	//메뉴 클릭 이벤트
	menuClickEvt();
}

function deletePopupOpen() {
    //마스크 켬
    var popup = document.getElementById("delete-popup");
    popup.classList.add("popup-show");
    popup.classList.remove(hide_btn_class);
    //팝업창 켬
//    popup = document.getElementById(popupwindow);
//    popup.classList.add("popup-show");
}

function deletePopupClose() {
    //마스크
    var popup = document.getElementById("delete-popup");
    popup.classList.remove("popup-show");
    popup.classList.add(hide_btn_class);
    //팝업창
//    popup = document.getElementById("popup-content");
//    popup.classList.remove("popup-show");
}

function addEvt_star(id, func) {
	var icon = document.getElementById(id);//.querySelector(".memo-bottom");//starwrap");
	icon.addEventListener("click", func);
}

//*********************
//여기부터 화면에 뿌리는 함수들 모음
//*********************

//메모 화면에 뿌리기
function displayMemolist (dom, parsedJSON) {
	//var memojson = document.getAttribute("memolist");
	//console.log(parsedJSON);
    var memo = parsedJSON;
    var condiv = document.getElementById("momo-container");
//    //일단 다지움
//    condiv.innerHTML = "";
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
        	hashtagli = "<li class='memo-hashtag memo-wowpoint'><i class='fas fa-exclamation'></i></li>";
        else if(data.wowpoint == "2")
        	hashtagli = "<li class='memo-hashtag memo-wowpoint'><i class='fas fa-exclamation'></i><i class='fas fa-exclamation'></i></li>";
        else if(data.wowpoint == "3")
        	hashtagli = "<li class='memo-hashtag memo-wowpoint'><i class='fas fa-exclamation'></i><i class='fas fa-exclamation'></i><i class='fas fa-exclamation'></i></li>";
        
        //hashtag
        var contentli = data.hashtag.split('#')
        contentli.forEach(function(tag) {
            if(tag != "")
                hashtagli += '<li class="memo-hashtag">#' + tag + '</li>';
        });
        //뿌림
		condiv.innerHTML += '<article id="' + data.idx + '" class="memo">' +
							'<div class="memo-swipe-wrap">' +
							'<div class="swipe-box" style="width: 0"><i class="icon-file_open swipe-icon ' + hide_btn_class +'"></i><span class="swipe-icon icon-caption ' + hide_btn_class +'">box</span></div>' +
							'<div class="swipe-delete" style="width: 0"><i class="icon-trash_can swipe-icon ' + hide_btn_class +'"></i><span class="swipe-icon icon-caption ' + hide_btn_class +'">delete</span></div>' +
	                            '<div class="momo-inner" style="transform: none;">' +
	                                '<div class="hashtag memo-top">' +
	                                    '<div class="memo-imp">'+'</div>' +
	                                    '<div class="slider">' + 
	                                        '<ul class="memo-hashtag-slider">' +
	                                            hashtagli +
	                                        '</ul>' +
	                                    '</div>' +
	                                '</div>' +
	                                '<div class="memo-title title">' +
	                                    '<span>' + data.title + '</span>' +
	                                '</div>' +
	                                '<div class="memo-content content">' +
	                                    '<span>' + data.content + '</span>' +
	                                '</div>' +
	                                '<div class="memo-bottom">' +
	                                    '<span class="mdate memo-date">' + data.mdate + '</span><span class="mdate space">|</span>' + 
	                                    '<span class="starwrap"><i class="icon-star memo-star ' + staroff +'"></i></span>' +
	                                '</div>' +
	                            '</div>' +
	                        '</div>' +
	                        '<div class="memo-border"></div>'
	                        '</article>';
    });
}