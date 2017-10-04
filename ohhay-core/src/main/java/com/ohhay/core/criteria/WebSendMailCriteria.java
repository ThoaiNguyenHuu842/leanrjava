package com.ohhay.core.criteria;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ThoaiNH
 * date create: 03/08/2015
 * info chuc nang web send mail
 */
public class WebSendMailCriteria implements Serializable {
	 private String userSend;//ho ten nguoi gui
	 private String emailSend;//mail nguoi gui
	 private String contentSend;//noi dung gui
	 private String emailReceive;//email revice chính chủ set
	 private String fo100; //fo100AESEncrypted cua chính chủ
	public String getUserSend() {
		return userSend;
	}
	public void setUserSend(String userSend) {
		this.userSend = userSend;
	}
	public String getEmailSend() {
		return emailSend;
	}
	public void setEmailSend(String emailSend) {
		this.emailSend = emailSend;
	}
	public String getContentSend() {
		return contentSend;
	}
	public void setContentSend(String contentSend) {
		this.contentSend = contentSend;
	}
	public String getEmailReceive() {
		return emailReceive;
	}
	public void setEmailReceive(String emailReceive) {
		this.emailReceive = emailReceive;
	}
	public String getFo100() {
		return fo100;
	}
	public void setFo100(String fo100) {
		this.fo100 = fo100;
	}
	@Override
	public String toString() {
		return "WebSendMailCriteria [userSend=" + userSend + ", emailSend="
				+ emailSend + ", contentSend=" + contentSend + ", emailReceive="
				+ emailReceive + ", fo100=" + fo100 + "]";
	}
	
}
