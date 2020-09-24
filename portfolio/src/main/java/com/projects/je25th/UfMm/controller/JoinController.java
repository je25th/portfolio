package com.projects.je25th.UfMm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.je25th.UfMm.dto.User;
import com.projects.je25th.UfMm.service.UserService;

@Controller
@RequestMapping("/unfolded-memo/join")
public class JoinController {

	@Autowired
	UserService userService;
	
	@PostMapping
	public String join(@RequestParam(value="id")String id, @RequestParam(value="pw")String pw, 
			@RequestParam(value="email")String email, @RequestParam(value="nickname")String nickname) {
		System.out.println("[UfMm] join Post");
		
		User user = new User();
		user.setJdate(new Date());
		user.setId(id);
		user.setPw(pw);
		user.setEmail(email);
		user.setNickname(nickname);
		
		boolean r = userService.addUser(user);
		
		return "redirect:/unfolded-memo/login";
		
//		List<Boolean> result = new ArrayList<>();
//		result.add(r);
//		AjaxJson send = new AjaxJson(true, result);
//		
//		return send;
	}
}
