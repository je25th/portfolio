package com.projects.je25th.UfMm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.projects.je25th.UfMm.dao.HashtagDao;
import com.projects.je25th.UfMm.dao.MemoDao;
import com.projects.je25th.UfMm.dao.MemoHasHashtagDao;
import com.projects.je25th.UfMm.dto.Hashtag;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.dto.MemoHasHashtag;

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
	
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public boolean writeMemo(Memo memo) {
		boolean result = true;
		
		try {
			//1. memo insert
			memo.setWdate(new Date());
			int memoIdx = memoDao.insert(memo);
			memo.setIdx(memoIdx);
			
			//2. hashtag / memo_has_hashtag
			int hashtagcount = memo.getHashtaglist().size();
			for(int i=0; i<hashtagcount; i++) {
				//2-1. hashtag update/insert
				if(memo.getHashtaglist().get(i).isIdxExist()) {
					//count up
					hashtagDao.countUp(memo.getHashtaglist().get(i).getIdx());
				}
				else {
					//new hashtag -> insert 
					memo.getHashtaglist().get(i).setIdx( hashtagDao.insert(memo.getHashtaglist().get(i)) );
				}
				
				//2-2. memo_has_hashtag insert
				memoHasHashtagDao.insert(new MemoHasHashtag(memo.getUserIdx()
															, memo.getIdx()
															, memo.getHashtaglist().get(i).getIdx()));
			}
		} catch(Exception e) {
			//이 트랜잭션을 롤백하겠다
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = false;
		}
		
		return result;
	}
	
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteMemo(int userIdx, int memoIdx) {
		int result = 0;
		try {
			//1. 해쉬태그 목록 가져오기
			List<MemoHasHashtag> mhh = memoHasHashtagDao.selectByMemoIdx(userIdx, memoIdx);
			//2. 메모 삭제
			result = memoDao.deleteByMemoIdx(userIdx, memoIdx);
			//3. 해쉬태그 카운트 다운
			for(int i=0; i<mhh.size(); i++) {
				hashtagDao.countDown(mhh.get(i).getHashtagIdx());
			}
		} catch(Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			//result = 0;
		}
		return result;
	}
	
	@Transactional(readOnly=false)
	public Memo modifyMemo(Memo memo) {
		memo.setMdate(new Date());
		memoDao.update(memo);
		
		return memo;
	}
	
}
