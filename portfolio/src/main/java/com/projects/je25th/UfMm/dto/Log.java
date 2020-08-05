package com.projects.je25th.UfMm.dto;

import java.util.Date;

public class Log {

	private Long idx;
	private String ip;
	private String method;
	private Date regdate;
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "Log [id=" + idx + ", ip=" + ip + ", method=" + method + ", regdate=" + regdate + "]";
	}
	
}
