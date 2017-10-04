package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create: 13/08/2015
 * topic profile info
 */
public class M960MG implements Serializable{
	@NotEmpty
	@Pattern(regexp="[a-zA-Z0-9-.]+" , message ="Dữ liệu nhập không hợp lệ")
	@Field("MV961")
	private String mv961;//key friendly link
	@NotEmpty
	@Field("MV962")
	private String mv962;//web title
	@NotEmpty
	@Field("MV963")
	private String mv963;//web description
	@Field("MV964")
	private String mv964;//img
	@Field("MV965")
	private String mv965;//cover image
	@Field("MV966")
	private String mv966;//cover description
	@Field("MN967")
	private int mn967;//layout topic
	@Field("MV967") 
	private String mv967;// back home
	@Transient
	private String imgBase64;//img
	
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	public String getMv961() {
		return mv961;
	}
	public void setMv961(String mv961) {
		this.mv961 = mv961;
	}
	public String getMv962() {
		return mv962;
	}
	public void setMv962(String mv962) {
		this.mv962 = mv962;
	}
	public String getMv963() {
		return mv963;
	}
	public void setMv963(String mv963) {
		this.mv963 = mv963;
	}
	public String getMv964() {
		return mv964;
	}
	public void setMv964(String mv964) {
		this.mv964 = mv964;
	}
	public String getMv965() {
		return mv965;
	}
	public String getMv967() {
		return mv967;
	}
	public void setMv967(String mv967) {
		this.mv967 = mv967;
	}
	public void setMv965(String mv965) {
		this.mv965 = mv965;
	}
	public String getMv966() {
		return mv966;
	}
	public void setMv966(String mv966) {
		this.mv966 = mv966;
	}
	public int getMn967() {
		return mn967;
	}
	public void setMn967(int mn967) {
		this.mn967 = mn967;
	}
	
	@Override
	public String toString() {
		return "M960MG [mv961=" + mv961 + ", mv962=" + mv962 + ", mv963=" + mv963 + ", mv964=" + mv964 + ", mv965="
				+ mv965 + ", mv966=" + mv966 + ", mn967=" + mn967 + ", mv967=" + mv967 + ", imgBase64=" + imgBase64
				+ "]";
	}
	
}
