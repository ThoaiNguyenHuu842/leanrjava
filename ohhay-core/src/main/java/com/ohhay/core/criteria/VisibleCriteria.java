package com.ohhay.core.criteria;

public class VisibleCriteria {
	private int extend;
	private int webId;
	//trang thai hien tai
	private int visible;
	//element or part super id
	private String superID;
	private int childID;
	public int getChildID() {
		return childID;
	}
	public void setChildID(int childID) {
		this.childID = childID;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getSuperID() {
		return superID;
	}
	public void setSuperID(String superID) {
		this.superID = superID;
	}
	public int getWebId() {
		return webId;
	}
	public void setWebId(int webId) {
		this.webId = webId;
	}
	public int getExtend() {
		return extend;
	}
	public void setExtend(int extend) {
		this.extend = extend;
	}
	
}
