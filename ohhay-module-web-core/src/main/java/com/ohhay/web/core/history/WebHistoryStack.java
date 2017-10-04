package com.ohhay.web.core.history;

import java.io.Serializable;
import java.util.Stack;

/**
 * @author ThoaiNH
 * create 20/12/2014
 * stack history
 */
public class WebHistoryStack implements Serializable{
	private String key;//md5(extend+webid+languagecode)
	private java.util.Stack<WebEditedInfo> stackUndo;
	private java.util.Stack<WebEditedInfo> stackRedo;
	private WebEditedInfo newestInfo;
	public WebHistoryStack() {
		// TODO Auto-generated constructor stub
		stackUndo = new Stack<WebEditedInfo>();
		stackRedo = new Stack<WebEditedInfo>();
	}
	public void pushToReDoStack(WebEditedInfo info)
	{
		stackRedo.push(info);
	}
	public void pushToUnDoStack(WebEditedInfo info)
	{
		stackUndo.push(info);
	}
	public java.util.Stack<WebEditedInfo> getStackUndo() {
		return stackUndo;
	}
	public void setStackUndo(java.util.Stack<WebEditedInfo> stackEditedInfos) {
		this.stackUndo = stackEditedInfos;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public java.util.Stack<WebEditedInfo> getStackRedo() {
		return stackRedo;
	}
	public void setStackRedo(java.util.Stack<WebEditedInfo> stackRedo) {
		this.stackRedo = stackRedo;
	}
	public WebEditedInfo getNewestInfo() {
		return newestInfo;
	}
	public void setNewestInfo(WebEditedInfo newestInfo) {
		this.newestInfo = newestInfo;
	}	
	
}
