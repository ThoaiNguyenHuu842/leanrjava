package com.ohhay.core.criteria;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ThoaiVT	
 * date create: 1/9/2015
 */
public class TemplateChildsPublishCriteria implements Serializable {
	private String imgBase64;
	private String imgMobiBase64;
	private int fa500;
	
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	public String getImgMobiBase64() {
		return imgMobiBase64;
	}
	public void setImgMobiBase64(String imgMobiBase64) {
		this.imgMobiBase64 = imgMobiBase64;
	}
	public int getFa500() {
		return fa500;
	}
	public void setFa500(int fa500) {
		this.fa500 = fa500;
	}
	
}
