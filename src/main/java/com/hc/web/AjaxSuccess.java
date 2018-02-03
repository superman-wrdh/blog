package com.hc.web;

public final class AjaxSuccess extends AjaxResult<String> {
	private int returnCode;

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
}