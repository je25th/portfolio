package com.projects.je25th.UfMm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.Hashtag;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.dto.User;
import com.projects.je25th.UfMm.service.MemoService;

@RestController
public class WriteApiController {

	@Autowired
	MemoService memoService;
	
	@Autowired
	User user;
	
	@PostMapping("UnfoldedMemo/gethashtag.rest")
	public AjaxJson sendAllHashtag() {
		System.out.println("[UfMm] gethashtag.rest Post");
		
		List<Hashtag> result = memoService.getAllHashtag(user.getIdx());
		AjaxJson send = new AjaxJson(result==null? false : true, result);
		return send;
	}
	
	@PutMapping("UnfoldedMemo/writememo.rest")
	public AjaxJson writeMemo(@RequestBody Memo memo) {//json으로 요청받은 것을 Memo 형식으로 변환하여 가져옴
		System.out.println("[UfMm] writememo.rest Put");
		
		if(memo !=null)
			System.out.println(memo.getTitle());
		
		AjaxJson send = new AjaxJson(memoService.writeMemo(), null);
		return send;
	}
}
