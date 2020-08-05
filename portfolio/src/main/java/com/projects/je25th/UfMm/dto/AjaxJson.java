package com.projects.je25th.UfMm.dto;

import java.util.List;

public class AjaxJson {

	private boolean success;
	private List<?> result;

	public AjaxJson(boolean success, List<?> result) {
		this.success = success;
		this.result = result;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}
	
}
