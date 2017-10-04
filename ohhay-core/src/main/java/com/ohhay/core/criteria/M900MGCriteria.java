package com.ohhay.core.criteria;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 16/09/2014
 */
public class M900MGCriteria implements Serializable{
	private String mv901;//ho, first name
	private String mv902;//ten, last name
	//@Pattern(regexp="([0-9]{2})//([0-9]{2})\([0-9]{4})", message="Vui lòng nhập đúng định dạng ngày (mm/dd/yyyy)")
	private String md904String;//birthday
	private String mv905;// M: nam, F:nu
	private int mn906;//1 public, 0 private (for phone number)
	private int mn912;//1 public, 0 private (for email)
	private int jobVal;
	private String jobLabel;
	private String language;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public int getJobVal() {
		return jobVal;
	}
	public void setJobVal(int jobVal) {
		this.jobVal = jobVal;
	}
	public String getJobLabel() {
		return jobLabel;
	}
	public void setJobLabel(String jobLabel) {
		this.jobLabel = jobLabel;
	}
	public String getMv901() {
		return mv901;
	}
	public void setMv901(String mv901) {
		this.mv901 = mv901;
	}
	public String getMv902() {
		return mv902;
	}
	public void setMv902(String mv902) {
		this.mv902 = mv902;
	}
	public String getMd904String() {
		return md904String;
	}
	public void setMd904String(String md904String) {
		this.md904String = md904String;
	}
	public String getMv905() {
		return mv905;
	}
	public void setMv905(String mv905) {
		this.mv905 = mv905;
	}
	public int getMn906() {
		return mn906;
	}
	public void setMn906(int mn906) {
		this.mn906 = mn906;
	}
	public int getMn912() {
		return mn912;
	}
	public void setMn912(int mn912) {
		this.mn912 = mn912;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "mv901:"+mv901+", mv902:"+mv902+", mv903:"+", md904String:"+md904String+", mv905:"+mv905+", mn906:"+mn906+", mn912:"+mn912+", language:"+language;
	}
}
