package com.ohhay.core.utils;

/**
 * @author ThoaiNH
 * create 11/11/2014
 * ajax return object
 */
public class JacksonResponse {
	public static final String AJAX_SUCCESS = "SUCCESS";
	public static final String AJAX_ERROR = "ERROR";
	private String status = null;
	private Object result = null;
	private Object result2 = null;
	private String resultString = null;
	private String mesg = null;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	public static String getAjaxSuccess() {
		return AJAX_SUCCESS;
	}
	public static String getAjaxError() {
		return AJAX_ERROR;
	}
	public String getMesg() {
		return mesg;
	}
	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	public Object getResult2() {
		return result2;
	}
	public void setResult2(Object result2) {
		this.result2 = result2;
	}
	
}
