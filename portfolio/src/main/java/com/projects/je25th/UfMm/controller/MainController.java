package com.projects.je25th.UfMm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "UnfoldedMemo/main")
public class MainController {

	@GetMapping
	public String main() {
		System.out.println("[UfMm] main Get");
		
		//TODO :: 로그인 체크(세션에 없을 시 로그인페이지로 리다이렉트)
		
		return "UfMm/main";
	}
	
	@PostMapping
	public String getMemoList() {
		System.out.println("[UfMm] main Post");
		
		//TODO :: 로그인 체크(세션에 없을 시 로그인페이지로 리다이렉트)
		
		//TODO :: json으로 메모 리스트를 보내기
		
		return "";
	}
}
