package com.projects.je25th.UfMm.dto;

public class MemoHasHashtag {

	private int idx;
	private int userIdx;
	private int memoIdx;
	private int hashtagIdx;
	
	public MemoHasHashtag() {
		
	}
	
	public MemoHasHashtag(int userIdx, int memoIdx, int hashtagIdx) {
		this.userIdx = userIdx;
		this.memoIdx = memoIdx;
		this.hashtagIdx = hashtagIdx;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getMemoIdx() {
		return memoIdx;
	}
	public void setMemoIdx(int memoIdx) {
		this.memoIdx = memoIdx;
	}
	public int getHashtagIdx() {
		return hashtagIdx;
	}
	public void setHashtagIdx(int hashtagIdx) {
		this.hashtagIdx = hashtagIdx;
	}
	
}
