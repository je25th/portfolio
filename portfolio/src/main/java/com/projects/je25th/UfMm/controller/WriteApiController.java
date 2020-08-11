package com.projects.je25th.UfMm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.Hashtag;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.service.MemoService;
import com.projects.je25th.UfMm.service.MyUtill;

@RestController
public class WriteApiController {

	@Autowired
	MemoService memoService;
	
	@PostMapping("UnfoldedMemo/gethashtag.rest")
	public AjaxJson sendAllHashtag(HttpSession session) {
		System.out.println("[UfMm] gethashtag.rest Post");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		List<Hashtag> result = memoService.getAllHashtag(authInfo.getIdx());
		AjaxJson send = new AjaxJson(result==null? false : true, result);
		return send;
	}
	
	@PutMapping("UnfoldedMemo/writememo.rest")
	public AjaxJson writeMemo(@RequestBody Memo memo, HttpSession session) {//json으로 요청받은 것을 Memo 형식으로 변환하여 가져옴
		System.out.println("[UfMm] writememo.rest Put");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		memo.setUser_idx(authInfo.getIdx());
		for(int i=0; i<memo.getHashtaglist().size(); i++) {
			memo.getHashtaglist().get(i).setUserIdx(authInfo.getIdx());
		}
		AjaxJson send = new AjaxJson(memoService.writeMemo(memo), null);
		return send;
	}
}
