package com.projects.je25th.UfMm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.service.MemoService;
import com.projects.je25th.UfMm.service.MyUtill;

@RestController
@RequestMapping("unfolded-memo")
public class MemoApiController {

	@Autowired
	MemoService memoService;
	
	@PostMapping("/write")
	public AjaxJson writeMemo(@RequestBody Memo memo, HttpSession session) {//json으로 요청받은 것을 Memo 형식으로 변환하여 가져옴
		System.out.println("[UfMm] write Post");
		
		//user_idx 세팅
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		memo.setUser_idx(authInfo.getIdx());
		for(int i=0; i<memo.getHashtaglist().size(); i++) {
			memo.getHashtaglist().get(i).setUserIdx(authInfo.getIdx());
		}
		
		AjaxJson send = new AjaxJson(memoService.writeMemo(memo), null);
		return send;
	}
	
	@DeleteMapping("/delete/{memoIdx}")
	public AjaxJson deleteMemo(@PathVariable(name="memoIdx") int memoIdx, HttpSession session) {
		System.out.println("[UfMm] delete/" + memoIdx + " Delete");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		int result = memoService.deleteMemo(authInfo.getIdx(), memoIdx);
		
		AjaxJson send = new AjaxJson(result>0? true : false, null);
		return send;
	}

	@PutMapping("/modify/{memoIdx}")
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
	
	@PatchMapping("/star/{memoIdx}")
	public AjaxJson starToggle(@PathVariable(name="memoIdx") int memoIdx, HttpSession session) {
		System.out.println("[UfMm] star/" + memoIdx + " Patch");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		int result = memoService.starToggle(authInfo.getIdx(), memoIdx);
		AjaxJson send = new AjaxJson(result!=-1? true:false, null);
		
		return send;
	}
	
}
