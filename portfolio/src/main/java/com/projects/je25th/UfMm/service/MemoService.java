package com.projects.je25th.UfMm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projects.je25th.UfMm.dao.HashtagDao;
import com.projects.je25th.UfMm.dao.MemoDao;
import com.projects.je25th.UfMm.dao.MemoHasHashtagDao;
import com.projects.je25th.UfMm.dto.Hashtag;
import com.projects.je25th.UfMm.dto.Memo;

@Service
public class MemoService {

	@Autowired
	MemoDao memoDao;
	
	@Autowired
	HashtagDao hashtagDao;
	
	@Autowired
	MemoHasHashtagDao memoHasHashtagDao;
	
	@Transactional(readOnly=true)
	public List<Memo> viewMemoListByUserIdx(int userIdx, int page) {
		//TODO :: memo에 memo_has_hashtag를 memo.idx=memo_has_hashtag로 left join
		/*
		 * SELECT memo.idx, memo.user_idx, memo.hashtag, hashtag.hashtag, title,
		 * content, colorbar, wowpoint, star, wdate, mdate, fold, box FROM memo LEFT
		 * JOIN memo_has_hashtag ON memo.idx=memo_has_hashtag.memo_idx INNER JOIN
		 * hashtag ON memo_has_hashtag.hashtag_idx=hashtag.idx WHERE memo.user_idx=2;
		 */
		return memoDao.selectByUserIdx(userIdx, page);
	}
	
	@Transactional(readOnly=true)
	public Memo viewMemoByMemoIdx(int userIdx, int memoIdx) {
		Memo result = memoDao.selectByMemoIdx(userIdx, memoIdx);
		if(result != null) {
			result.setHashtaglist(hashtagDao.selectByMemoIdx(userIdx, memoIdx));
		}
		
		return result;
	}
	
	@Transactional(readOnly=true)
	public List<Hashtag> getAllHashtag(int userIdx) {
		return hashtagDao.selectByUserIdx(userIdx);
	}
	
	@Transactional(readOnly=false)
	public boolean writeMemo() {
		//TODO :: 1. memo 테이블에 메모 인서트 -> 자동 입력된 memo_idx 가져오기 
		//        2. hashtag 테이블에 해쉬태그 인서트 -> 자동 입력된 hashtag_idx 가져오기
		//        3. 위의 idx들을 memo_has_hashtag 테이블애 인서트
		
		return false;
	}
	
	@Transactional(readOnly=false)
	public void deleteMemo() {
		//TODO :: 
	}
	
	@Transactional(readOnly=false)
	public void modifyMemo() {
		//TODO :: 
	}
	
}
