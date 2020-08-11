package com.projects.je25th.UfMm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.service.UserService;

@Controller
@RequestMapping("/UnfoldedMemo/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	@GetMapping//get 방식
	public String login() {
		System.out.println("[UfMm] login Get");
		
		//일단 로그아웃
		userService.logout();
		
		return "UfMm/login";
	}
	
	@PostMapping
	public String submit(@RequestParam(value="id")String id, @RequestParam(value="pw")String pw, HttpSession session) {
		System.out.println("[UfMm] login Post");
		
		//아이디 비번 체크
		AuthInfo authInfo = userService.login(id, pw);
		if(authInfo != null) {
			//세션
			session.setAttribute("authInfo", authInfo);
			return "redirect:/UnfoldedMemo/main";
		}
		
		return "redirect:/UnfoldedMemo/login";
	}
	
}
