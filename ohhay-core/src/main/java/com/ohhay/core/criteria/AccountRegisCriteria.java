package com.ohhay.core.criteria;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @deprecated
 * @author ThoaiNH
 * date create: 09/06/2015
 * parameter pass to create account service
 */
public class AccountRegisCriteria implements Serializable{
	@NotBlank(message="{setting.warnning.fname_error}")
	private String fName;
	@NotBlank(message="{setting.warnning.lname_error}")
	private String lName;
	@Pattern(regexp=".*@.*", message="{setting.warnning.email_error}")
	private String email;
	@Pattern(regexp="\\d{8}|\\d{9}|\\d{10}|\\d{11}", message="{home.message.invaild_3}")
	private String phone;
	@NotBlank(message="{setting.warnning.password_error}")
	private String passWord;
	@NotBlank(message="{setting.warnning.confirmpassword_error}")
	private String rePassWord;
	private String countryCode;
	private String languageCode;
	private String languageName;
	private String region;
	private int templateId;
	private int fd000;
	private String jobName;
	private String gender;
	public AccountRegisCriteria(){}
	public AccountRegisCriteria(String fName, String lName, String email,
			String phone, String passWord, String rePassWord,
			String countryCode, String languageCode, String languageName,
			String region, int templateId, int fd000, String jobName,
			String gender) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.phone = phone;
		this.passWord = passWord;
		this.rePassWord = rePassWord;
		this.countryCode = countryCode;
		this.languageCode = languageCode;
		this.languageName = languageName;
		this.region = region;
		this.templateId = templateId;
		this.fd000 = fd000;
		this.jobName = jobName;
		this.gender = gender;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getFd000() {
		return fd000;
	}
	public void setFd000(int fd000) {
		this.fd000 = fd000;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRePassWord() {
		return rePassWord;
	}
	public void setRePassWord(String rePassWord) {
		this.rePassWord = rePassWord;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "AccountRegisCriteria [fName=" + fName + ", lName=" + lName
				+ ", email=" + email + ", phone=" + phone + ", passWord="
				+ passWord + ", rePassWord=" + rePassWord + ", countryCode="
				+ countryCode + ", languageCode=" + languageCode
				+ ", languageName=" + languageName + ", region=" + region
				+ ", templateId=" + templateId + ", fd000=" + fd000
				+ ", jobName=" + jobName + ", gender=" + gender + "]";
	}
	
}
