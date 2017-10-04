package com.ohhay.core.entities;

/**
 * @author ThoaiNH
 * create 11/09/2014
 */
public class User {
	private String phone;
	private String password;
	private int remember;//1:remember, 0: no remember
	private String src;
	public int getRemember() {
		return remember;
	}
	public void setRemember(int remember) {
		this.remember = remember;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	@Override
	public String toString() {
		return "User [phone=" + phone + ", password=" + password + ", remember="
				+ remember + ", src=" + src + "]";
	}
	
}
