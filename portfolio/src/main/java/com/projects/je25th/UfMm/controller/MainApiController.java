package com.projects.je25th.UfMm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.dto.User;
import com.projects.je25th.UfMm.service.MemoService;
import com.projects.je25th.UfMm.service.MyUtill;

@RestController//JSON 형식으로 응답 
public class MainApiController {

	@Autowired
	MemoService memoService;
	
	@PostMapping("UnfoldedMemo/main.rest")
	public AjaxJson viewMemoList(@RequestParam(value="page")int page, HttpSession session) {
		System.out.println("[UfMm] main.rest Post");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		//json으로 메모 리스트를 보내기
		List<Memo> result = memoService.viewMemoListByUserIdx(authInfo.getIdx(), page);
		AjaxJson send = new AjaxJson(result==null? false : true, result);
		return send;
	}
	
	@DeleteMapping("UnfoldedMemo/delete/{memoIdx}")
	public AjaxJson deleteMemo(@PathVariable(name="memoIdx")int memoIdx, HttpSession session) {
		System.out.println("[UfMm] delete Delete");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		int result = memoService.deleteMemo(authInfo.getIdx(), memoIdx);
		
		AjaxJson send = new AjaxJson(result>0? true : false, null);
		return send;
	}
}
