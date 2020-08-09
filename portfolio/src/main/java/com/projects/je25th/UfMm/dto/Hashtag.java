package com.projects.je25th.UfMm.dto;

import java.util.ArrayList;

public class Hashtag {
	
	private int idx = -1;
	private int userIdx;
	private String hashtag;
	private int count = 1;
	private ArrayList<Integer> memo_has_hashtag_idx = new ArrayList<>();
	
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
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<Integer> getMemo_has_hashtag_idx() {
		return memo_has_hashtag_idx;
	}
	public void setMemo_has_hashtag_idx(ArrayList<Integer> memo_has_hashtag_idx) {
		this.memo_has_hashtag_idx = memo_has_hashtag_idx;
	}
	
	public boolean isIdxExist() {
		return this.idx==-1? false:true; 
	}
	
}
