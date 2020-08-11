package com.projects.je25th.UfMm.dto;

public class Hashtag {
	
	private int idx = -1;
	private int userIdx;
	private String hashtag;
	private int count = 1;
	
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
	
	public boolean isIdxExist() {
		return this.idx==-1? false:true; 
	}
	
}
