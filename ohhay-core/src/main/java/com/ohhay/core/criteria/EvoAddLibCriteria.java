package com.ohhay.core.criteria;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ThoaiNH
 * create Jan 19, 2016
 * add section to lib
 */
public class EvoAddLibCriteria implements Serializable {
	private int pe920;
	private  int libType;
	private  String otags;
	@NotBlank(message="{datl.error1}")
	private  String title;
	private String imgBase64;
	private  int editAble;
	public int getPe920() {
		return pe920;
	}
	public void setPe920(int pe920) {
		this.pe920 = pe920;
	}
	public int getLibType() {
		return libType;
	}
	public void setLibType(int libType) {
		this.libType = libType;
	}
	public String getOtags() {
		return otags;
	}
	public void setOtags(String otags) {
		this.otags = otags;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getEditAble() {
		return editAble;
	}
	public void setEditAble(int editAble) {
		this.editAble = editAble;
	}
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	@Override
	public String toString() {
		return "EvoAddLibCriteria [pe920=" + pe920 + ", libType=" + libType
				+ ", otags=" + otags + ", title=" + title + ", editAble="
				+ editAble + "]";
	}
	
}
