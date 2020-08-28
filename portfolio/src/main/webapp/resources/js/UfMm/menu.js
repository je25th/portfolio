/**
 * main.jsp의 메뉴 관련
 */
var menu_wrap = document.querySelector(".btn-wrap");
var menu = menu_wrap.firstElementChild; 

//메뉴 클릭 이벤트
function menuClickEvt() {
    var li = menu.children;
    li[0].addEventListener("click", function() {
        menu_open();

        lastScrollTop = window.pageYOffset || document.documentElement.scrollTop;
    });
    li[1].addEventListener("click", function() {
        menu_check();
    });
    li[2].addEventListener("click", function() {
        menu_search();
    });
    li[3].addEventListener("click", function() {
        menu_star();
    });
    li[4].addEventListener("click", function() {
        menu_box();
    });
    li[5].addEventListener("click", function() {
        menu_logout();
    });
    li[6].addEventListener("click", function() {
        menu_write();
    });
}

function select_menu(n) {
	var li_num;
	switch(n) {
		case "menu" :
			li_num = 0;
			break;
		case "check" :
			li_num = 1;
			break;
		case "search" :
			li_num = 2;
			break;
		case "star" :
			li_num = 3;
			break;
		case "box" :
			li_num = 4;
			break;
		case "logout" :
			li_num = 5;
			break;
		case "write" :
			li_num = 6;
			break;
	}
	
	return li_num;
}

function hide_menu(n) {
	var li_num = select_menu(n);
	
	var li = menu.children;
    for(var i=0; i<li.length; i++)
    {
    	if(li_num == i) {
    		li[i].classList.add(hide_btn_class);
    		continue;
		}
    	
        li[i].classList.remove(hide_btn_class);
    }
}

function show_menu(n, n2) {
	var li_num = select_menu(n);
	var li_num2 = select_menu(n2);
	
	var li = menu.children;
    for(var i=0; i<li.length; i++)
    {
    	if(li_num == i || li_num2 == i) {
    		li[i].classList.remove(hide_btn_class);
    		continue;
		}
    	
        li[i].classList.add(hide_btn_class);
    }
}

function menu_open() {
    if(menu_wrap.style.width === wid) return;

    menu_wrap.style.width = wid;

    hide_menu("menu");
}

function menu_close() {
    if(menu_wrap.style.width === ori_wid) return;

    menu_wrap.style.width = ori_wid;

    show_menu("menu");
}

function menu_search() {
    if(menu_wrap.style.width === ori_wid) return;

    if(!document.getElementById("popup-content").classList.contains("popup-show")) {
	    //팝업 열기
    	searchPopupOpen();
    	//메뉴는 닫기
    	menu_close();
    }
}

function menu_star() {
    if(menu_wrap.style.width === ori_wid) return;

    window.location.href = URL_STAR;
}

function menu_check() {
    if(menu_wrap.style.width === ori_wid) return;

}

function menu_logout() {
    if(menu_wrap.style.width !== wid) return;

    window.location.href = URL_LOGIN;
}

function menu_box() {
    if(menu_wrap.style.width !== wid) return;
	
}

function menu_write() {
    if(menu_wrap.style.widthh === ori_wid) return;
    
    //console.log("write");
    window.location.href = URL_WRITE;
    //window.location.replace("./write");//이건 뒤로가기 누르면 이상한것같음
}