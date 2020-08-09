package com.projects.je25th.UfMm.dto;

import java.util.List;
import java.util.Date;

public class Memo {

	private int idx;
	private int userIdx;
	private String title;
	private String content;
	private String hashtag;
	private String colorbar;
	private int wowpoint;
	private int star;
	private Date wdate;
	private Date mdate;
	private int fold;
	private int box;
	private List<Hashtag> hashtaglist;
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getUserIdx() {
		return userIdx;
	}

	public void setUser_idx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getColorbar() {
		return colorbar;
	}

	public void setColorbar(String colorbar) {
		this.colorbar = colorbar;
	}

	public int getWowpoint() {
		return wowpoint;
	}

	public void setWowpoint(int wowpoint) {
		this.wowpoint = wowpoint;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public int getFold() {
		return fold;
	}

	public void setFold(int fold) {
		this.fold = fold;
	}

	public int getBox() {
		return box;
	}

	public void setBox(int box) {
		this.box = box;
	}

	public List<Hashtag> getHashtaglist() {
		return hashtaglist;
	}

	public void setHashtaglist(List<Hashtag> hashtaglist) {
		this.hashtaglist = hashtaglist;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		if(hashtaglist != null) {
			for(int i=0; i<hashtaglist.size(); i++)
			str +=  "idx:" + hashtaglist.get(i).getIdx() + ", hashtag:" + hashtaglist.get(i).getHashtag() + "\n";
		}
		return str;
	}
	
}
