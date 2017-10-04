package com.ohhay.other.entities;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Z000 implements Serializable{
	private int pz000;
	@NotBlank(message="{home.message.invaild_1}")
	private String zv001;
	@NotBlank(message="{home.message.invaild_2}")
	private String zv002;
	@Pattern(regexp="\\d{8}|\\d{9}|\\d{10}|\\d{11}", message="{home.message.invaild_3}")
	private String zv003;
	@Pattern(regexp=".*@.*", message="{home.message.invaild_4}")
	private String zv004;

	public String getZv001() {
		return zv001;
	}
	public void setZv001(String zv001) {
		this.zv001 = zv001;
	}
	public String getZv002() {
		return zv002;
	}
	public void setZv002(String zv002) {
		this.zv002 = zv002;
	}
	public String getZv003() {
		return zv003;
	}
	public void setZv003(String zv003) {
		this.zv003 = zv003;
	}
	public String getZv004() {
		return zv004;
	}
	public void setZv004(String zv004) {
		this.zv004 = zv004;
	}
	public int getPz000() {
		return pz000;
	}
	public void setPz000(int pz000) {
		this.pz000 = pz000;
	}
	
}
