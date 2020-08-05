package com.projects.je25th.UfMm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.dto.User;
import com.projects.je25th.UfMm.service.MemoService;

@RestController//JSON 형식으로 응답 
public class MainApiController {

	@Autowired
	MemoService memoService;
	
	@Autowired
	User user;
	
	@PostMapping("UnfoldedMemo/main.rest")
	public AjaxJson viewMemoList(@RequestParam(value="page")int page) {
		System.out.println("[UfMm] main.rest Post");
		
		//TODO :: 로그인 체크(세션에 없을 시 로그인페이지로 리다이렉트)
		
		//json으로 메모 리스트를 보내기
		List<Memo> result = memoService.viewMemoListByUserIdx(user.getIdx(), page);
		AjaxJson send = new AjaxJson(result==null? false : true, result);
		return send;
	}
	
	@DeleteMapping("UnfoldedMemo/delete/{memoIdx}")
	public void deleteMemo() {
		System.out.println("[UfMm] delete Delete");
		//TODO ::
	}
}
