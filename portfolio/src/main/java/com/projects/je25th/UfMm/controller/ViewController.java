package com.projects.je25th.UfMm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.dto.User;
import com.projects.je25th.UfMm.service.MemoService;
import com.projects.je25th.UfMm.service.MyUtill;

@Controller
@RequestMapping("UnfoldedMemo/view")
public class ViewController {

	@Autowired
	MemoService memoService;
	
	@GetMapping("/{memoIdx}")
	public String view(@PathVariable(name="memoIdx")int memoIdx, ModelMap model, HttpSession session) {
		System.out.println("[UfMm] view Get");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		Memo memo = memoService.viewMemoByMemoIdx(authInfo.getIdx(), memoIdx);
		model.addAttribute("memo", memo);
		
		return "UfMm/write";
	}
}
