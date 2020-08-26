/**
 * main.jsp의 서치 팝업에 관련된 함수들
 */

//태그 서치 텍스트박스 이벤트
function searchBoxKeyupEvt() {
	document.getElementById("search_textbox").addEventListener("keyup", function(e) {
		var str = e.srcElement.value;
		
//		//모드체인지
////		//해쉬태그 모드
//		if(str == "#"){
//			console.log("hashtag_search_mode");
//			e.srcElement.value = "";
//			document.getElementById("hashtag-mode").classList.remove("hide");
//			document.getElementById("keyword-mode").classList.add("hide");
////			document.getElementById("keyword_search_mode").classList.add(hide_btn_class);
////			document.getElementById("hashtag_search_mode").classList.remove(hide_btn_class);
////			
//			return;
//		}
////		//키워드 모드
//		else if(str == "k" || str == "K") {
//			e.srcElement.value = "";
//			document.getElementById("hashtag-mode").classList.add("hide");
//			document.getElementById("keyword-mode").classList.remove("hide");
////			document.getElementById("keyword_search_mode").classList.remove(hide_btn_class);
////			document.getElementById("hashtag_search_mode").classList.add(hide_btn_class);
////			
//			return;
//		}
		
		//태그 목록 검색
		//findHashtagList(str);
	});
}

//검색버튼 클릭
function searchBtnClickEvt() {
	document.getElementById("search_textbox").addEventListener("keyup", function(e) {
		
	});
}

//해쉬태그를 클릭하면 바로 그 해쉬태그로 검색됨
function hashtagListClickEvt() {
	
}

//태그 목록 검색
function findHashtagList(str) {
	var par = "";
	if(str.length > 0)
		par = "hashtag=" + str;
	console.log(par);
	var ul = document.getElementById("search-hashtag-list");
	//URL, SEND, FUC, fuc_PAR, HttpMethod
    ajax_getJson(URL_HASHTAG_ALL + "?" + par, null, displayHashtag, ul, 'GET');
}

function searchPopupOpen() {
	//태그 목록 가져오기
	findHashtagList("");
    //마스크 켬
    var popup = document.getElementById("search-popup");
    popup.classList.add("popup-show");
    popup.classList.remove(hide_btn_class);
    //팝업창 켬
//    popup = document.getElementById(popupwindow);
//    popup.classList.add("popup-show");
}

function searchPopupClose() {
    //마스크
    var popup = document.getElementById("search-popup");
    popup.classList.remove("popup-show");
    popup.classList.add(hide_btn_class);
    //팝업창
//    popup = document.getElementById("popup-content");
//    popup.classList.remove("popup-show");
}

//해쉬태그
function displayHashtag (ul, parsedJSON) {
	var hashtagList = parsedJSON;
	var h;
	ul.innerHTML = "";
	hashtagList.forEach(function(data) {
		ul.innerHTML += "<li id='" + hashtagId + data.idx + "' class='hashtag hashtag-unselected inline'>#" + data.hashtag + 
						"<span class='hashtag_count'>| " + data.count + "</span></li>";
	});
}

/**
 * 여기부터 폐기
//or 버튼을 누르면 str이 orDiv에 추가
function clickOrBtn(isHashtag, str, orDiv) {
	addSearchKeyword(isHashtag, str, orDiv);
}

function clickAndBtn(isHashtag, str, andDiv) {
	addSearchKeyword(isHashtag, str, andDiv);
}

function clickNoBtn(isHashtag, str, noDiv) {
	addSearchKeyword(isHashtag, str, noDiv);
}

function addSearchKeyword(isHashtag, str, div) {
	var tag = '';
	
	if(isHashtag) {
		//해쉬태그
		tag = "<span class='search-hashtag'>" + str + "</span>";
	}
	else {
		//키워드
		tag = "<span class='search-keyword'>" + str + "</span>";
	}
	
	//추가
	div.innerHTML += tag;
}
 */
