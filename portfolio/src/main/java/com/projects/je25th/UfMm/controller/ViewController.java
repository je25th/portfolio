package com.projects.je25th.UfMm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.dto.User;
import com.projects.je25th.UfMm.service.MemoService;

@Controller
@RequestMapping("UnfoldedMemo/view")
public class ViewController {

	@Autowired
	MemoService memoService;
	
	@Autowired
	User user;
	
	@GetMapping("/{memoIdx}")
	public String view(@PathVariable(name="memoIdx")int memoIdx, ModelMap model) {
		System.out.println("[UfMm] view Get");
		
		Memo memo = memoService.viewMemoByMemoIdx(user.getIdx(), memoIdx);
		model.addAttribute("memo", memo);
		
		return "UfMm/write";
	}
}
