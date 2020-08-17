/**
 * 서치 팝업에 관련된 함수들
 */

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

