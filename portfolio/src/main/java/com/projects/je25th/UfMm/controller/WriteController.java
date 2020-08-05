package com.projects.je25th.UfMm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("UnfoldedMemo/write")
public class WriteController {

	@GetMapping
	public String write() {
		System.out.println("[UfMm] write Get");
		
		return "UfMm/write";
	}
}
