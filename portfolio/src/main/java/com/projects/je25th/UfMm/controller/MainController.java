package com.projects.je25th.UfMm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.Hashtag;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.service.MemoService;
import com.projects.je25th.UfMm.service.MyUtill;

@Controller
@RequestMapping(path = "unfolded-memo")
public class MainController {
	
	@Autowired
	MemoService memoService;

	@GetMapping("/main")
	public String main() {
		System.out.println("[UfMm] main Get");
		
		return "UfMm/main";
	}

	@GetMapping("/star")
	public String star() {
		System.out.println("[UfMm] star Get");
		
		return "UfMm/main";
	}
	
	@GetMapping("/write")
	public String write() {
		System.out.println("[UfMm] write Get");
		
		return "UfMm/write";
	}
	
	@GetMapping("/view/{memoIdx}")
	public String view(@PathVariable(name="memoIdx")int memoIdx, ModelMap model, HttpSession session) {
		System.out.println("[UfMm] view/" + memoIdx + " Get");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		Memo memo = memoService.viewMemoByMemoIdx(authInfo.getIdx(), memoIdx);
		model.addAttribute("memo", memo);
		
		return "UfMm/write";
	}
	
	@GetMapping("/modify/{memoIdx}")
	public String modify(@PathVariable(name="memoIdx")int memoIdx, ModelMap model, HttpSession session) {
		System.out.println("[UfMm] modify/" + memoIdx + " Get");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		Memo memo = memoService.viewMemoByMemoIdx(authInfo.getIdx(), memoIdx);
		model.addAttribute("memo", memo);
		
		return "UfMm/write";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(required=false) String keyword, HttpSession session) {
		System.out.println("[UfMm] search?keyword=" + keyword + " Get");
		
		System.out.println(keyword);
		
		return "UfMm/main";
	}
	
	@GetMapping("/search/hashtag")
	public String hashtag(@RequestParam(required=false) String hashtag, HttpSession session) {
		System.out.println("[UfMm] search/hashtag?hashtag=" + hashtag + " Get");
		
		System.out.println(hashtag);
		
		return "UfMm/main";
	}
	
	@GetMapping("/test")
	public String test() {
		System.out.println("[UfMm] main Get");
		
		return "UfMm/pc/pc_main";
	}
}
