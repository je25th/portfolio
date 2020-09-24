package com.projects.je25th.UfMm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.User;
import com.projects.je25th.UfMm.service.UserService;

@RestController
@RequestMapping("/unfolded-memo/join")
public class JoinApiController {

	@Autowired
	UserService userService;
	
	@GetMapping("/check/id")
	public AjaxJson checkId(@RequestParam(value="id")String id) {
		System.out.println("[UfMm] join/check/id?id=" + id + " Get");
		
		List<Boolean> result = new ArrayList<>();
		result.add(userService.checkEnableId(id));
		AjaxJson send = new AjaxJson(true, result);
		
		return send;
	}
	
	@GetMapping("/check/email")
	public AjaxJson checkEmail(@RequestParam(value="email")String email) {
		System.out.println("[UfMm] join/check/email?email=" + email + " Get");
		
		List<Boolean> result = new ArrayList<>();
		result.add(userService.checkEnableEmail(email));
		AjaxJson send = new AjaxJson(true, result);
		
		return send;
	}
}
