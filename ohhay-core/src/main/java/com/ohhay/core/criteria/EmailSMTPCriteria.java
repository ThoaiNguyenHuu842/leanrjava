package com.ohhay.core.criteria;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Tang Hiep
 * create 03/08/2014
 * dung o chuc nang setting, nhap stmp
 */
public class EmailSMTPCriteria implements Serializable{
	@NotEmpty(message="Vui lòng nhập đủ thông tin")
	private String nv116;
	@NotEmpty(message="Vui lòng nhập đủ thông tin")
	private String nv117;
	@NotEmpty(message="Vui lòng nhập đủ thông tin")
	private String nv118;
	@NotEmpty(message="Vui lòng nhập đủ thông tin")
	private String nv119;
	@Email(message="{setting.n950.email}")
	private String nv120;
	public String getNv116() {
		return nv116;
	}
	public void setNv116(String nv116) {
		this.nv116 = nv116;
	}
	public String getNv117() {
		return nv117;
	}
	public void setNv117(String nv117) {
		this.nv117 = nv117;
	}
	public String getNv118() {
		return nv118;
	}
	public void setNv118(String nv118) {
		this.nv118 = nv118;
	}
	public String getNv120() {
		return nv120;
	}
	public void setNv120(String nv120) {
		this.nv120 = nv120;
	}
	public String getNv119() {
		return nv119;
	}
	public void setNv119(String nv119) {
		this.nv119 = nv119;
	}
	
}
