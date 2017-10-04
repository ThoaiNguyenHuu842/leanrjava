package com.ohhay.core.criteria;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ThoaiNH
 * create 09/10/2014
 * parameter pass to change password service
 */
public class ChangePassCriteria implements  Serializable{
	@NotBlank(message="{setting.changepassword.message_error1}")
	private String qv102Old;
	@NotBlank(message="{setting.changepassword.message_error2}")
	private String qv102New;
	@NotBlank(message="{setting.changepassword.message_error3}")
	private String qv102ReNew;
	public String getQv102Old() {
		return qv102Old;
	}
	public void setQv102Old(String qv102Old) {
		this.qv102Old = qv102Old;
	}
	public String getQv102New() {
		return qv102New;
	}
	public void setQv102New(String qv102New) {
		this.qv102New = qv102New;
	}
	public String getQv102ReNew() {
		return qv102ReNew;
	}
	public void setQv102ReNew(String qv102ReNew) {
		this.qv102ReNew = qv102ReNew;
	}
	
}
