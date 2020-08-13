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
	
	@Transactional(rollbackFor={Exception.class})
	public boolean writeMemo(Memo memo) {
		boolean result = true;
		
		try {
			//1. memo insert
			memo.setWdate(new Date());
			memo.setMdate(memo.getWdate());
			System.out.println(memo.getMdate());
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
			System.out.println(e);
			//이 트랜잭션을 롤백하겠다
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = false;
		}
		
		return result;
	}
	
	@Transactional(rollbackFor={Exception.class})
	public int deleteMemo(int userIdx, int memoIdx) {
		int result = 0;
		try {
			//1. 해쉬태그 목록 가져오기
			List<MemoHasHashtag> mhh = memoHasHashtagDao.selectByMemoIdx(userIdx, memoIdx);
			//2. 메모 삭제(memo_has_hashtag는 DBMS가 알아서 삭제하도록 설정해둠(외래키))
			result = memoDao.deleteByMemoIdx(userIdx, memoIdx);
			//3. 해쉬태그 카운트 다운
			for(int i=0; i<mhh.size(); i++) {
				hashtagDao.countDown(mhh.get(i).getHashtagIdx());
			}
		} catch(Exception e) {
			System.out.println(e);
			//throw new RuntimeException(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = 0;
		}
		return result;
	}
	
	@Transactional(rollbackFor={Exception.class})
	public boolean modifyMemo(Memo memo) {
		boolean result = true;
		
		try {
			//1. 메모내용 update
			memo.setMdate(new Date());
			memoDao.update(memo);
			
			//2. memo_has_hashtag selectByMemoIdx로 구 해쉬태그목록 가져옴
			List<MemoHasHashtag> beforMhh = memoHasHashtagDao.selectByMemoIdx(memo.getUserIdx(), memo.getIdx());
			
			//3. 구 해쉬태그목록 중 뉴 해쉬태크목록에 없는 것을 카운트 다운하고, memo_has_hashtag에서 삭제
			for(int i=0; i<beforMhh.size(); i++) {
				//3-1. 구 해쉬태그목록 중 뉴 해쉬태크목록에 없는 것을 찾음
				boolean isExist = false;
				for(int j=0; j<memo.getHashtaglist().size(); j++) {
					if(beforMhh.get(i).getHashtagIdx() == memo.getHashtaglist().get(j).getIdx()) {
						isExist = true;
						break;
					}
				}
				
				if(!isExist) {
					//3-2. 찾은것을 카운트다운함
					hashtagDao.countDown(beforMhh.get(i).getHashtagIdx());
					//3-3. memo_has_hashtag에서 삭제함
					memoHasHashtagDao.deleteByMemoHasHashtagIdx(beforMhh.get(i).getIdx());
				}
			}
			
			//4. 뉴 해쉬태그목록 중 구 해쉬태그목록에 없는 것을 insert하거나 카운트 업하고, memo_has_hashtag에 추가함
			for(int i=0; i<memo.getHashtaglist().size(); i++) {
				//4-1. 뉴 해쉬태그목록 중 구 해쉬태그목록에 없는 것을 찾음
				boolean isExist = false;
				for(int j=0; j<beforMhh.size(); j++) {
					if(memo.getHashtaglist().get(i).getIdx() == beforMhh.get(j).getHashtagIdx()) {
						isExist = true;
						break;
					}
				}
				
				if(!isExist) {
					//4-2. 찾은것을 카운트업하거나 추가
					if(memo.getHashtaglist().get(i).isIdxExist()) {
						hashtagDao.countUp(memo.getHashtaglist().get(i).getIdx());
					} else {
						System.out.println(memo.getHashtaglist().get(i).getUserIdx());
						memo.getHashtaglist().get(i).setIdx( hashtagDao.insert(memo.getHashtaglist().get(i)) );
					}
					//4-3. memo_has_hashtag에 추가함
					memoHasHashtagDao.insert(new MemoHasHashtag(memo.getUserIdx(), memo.getIdx(), memo.getHashtaglist().get(i).getIdx()));
				}
			}
		} catch(Exception e) {
			System.out.println(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result = false;
		}
		
		return result;
	}
	
}
