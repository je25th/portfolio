package com.projects.je25th.UfMm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.Memo;
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
	public AjaxJson deleteMemo(@PathVariable(name="memoIdx") int memoIdx, HttpSession session) {
		System.out.println("[UfMm] delete/" + memoIdx + " Delete");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		int result = memoService.deleteMemo(authInfo.getIdx(), memoIdx);
		
		AjaxJson send = new AjaxJson(result>0? true : false, null);
		return send;
	}
	
	@PutMapping("UnfoldedMemo/modify/{memoIdx}")
	public AjaxJson modify(@PathVariable(name="memoIdx")int memoIdx, @RequestBody Memo memo, HttpSession session) {
		System.out.println("[UfMm] modify/" + memoIdx + " Put");
		
		//memo_idx 세팅
		memo.setIdx(memoIdx);
		
		//user_idx 세팅
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		memo.setUser_idx(authInfo.getIdx());
		for(int i=0; i<memo.getHashtaglist().size(); i++) {
			memo.getHashtaglist().get(i).setUserIdx(authInfo.getIdx());
		}
		
		AjaxJson send = new AjaxJson(memoService.modifyMemo(memo), null);
		return send;
	}
}
