package com.ohhay.core.criteria;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ThoaiNH
 * date create: 08/07/2015
 */
public class TemplatePublishCriteria implements Serializable {
	//@NotEmpty(message="{setting.temlate.image.message_error}")
	private String imgBase64;
	private String imgMobiBase64;
	private int fa900;
	private int fa950;//category
	private int an402;
	@NotEmpty(message="{setting.temlate.name.message_error}")
	private String av403;//template name
	public String getImgMobiBase64() {
		return imgMobiBase64;
	}
	public void setImgMobiBase64(String imgMobiBase64) {
		this.imgMobiBase64 = imgMobiBase64;
	}
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	public int getFa900() {
		return fa900;
	}
	public void setFa900(int fa900) {
		this.fa900 = fa900;
	}
	public int getFa950() {
		return fa950;
	}
	public void setFa950(int fa950) {
		this.fa950 = fa950;
	}
	public String getAv403() {
		return av403;
	}
	public void setAv403(String av403) {
		this.av403 = av403;
	}
	public int getAn402() {
		return an402;
	}
	public void setAn402(int an402) {
		this.an402 = an402;
	}
	@Override
	public String toString() {
		return "TemplatePublishCriteria [fa900=" + fa900 + ", fa950=" + fa950 + ", an402=" + an402 + ", av403=" + av403
				+ "]";
	}

	
	
}
