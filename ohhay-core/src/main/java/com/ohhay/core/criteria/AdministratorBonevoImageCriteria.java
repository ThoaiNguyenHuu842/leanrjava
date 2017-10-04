package com.ohhay.core.criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ThoaiVt
 * create date Apr 12, 2016
 * ohhay-core
 */
public class AdministratorBonevoImageCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String pv501;//image
	private String pv502;// image thumbnail
	private Date pd506;
	private Date pd508;
	private String ext;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPv501() {
		return pv501;
	}
	public void setPv501(String pv501) {
		this.pv501 = pv501;
	}
	public String getPv502() {
		return pv502;
	}
	public void setPv502(String pv502) {
		this.pv502 = pv502;
	}
	public Date getPd506() {
		return pd506;
	}
	public void setPd506(Date pd506) {
		this.pd506 = pd506;
	}
	public Date getPd508() {
		return pd508;
	}
	public void setPd508(Date pd508) {
		this.pd508 = pd508;
	}
	
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	@Override
	public String toString() {
		return "AdministratorBonevoImageCriteria [id=" + id + ", pv501=" + pv501 + ", pv502=" + pv502 + ", pd506="
				+ pd506 + ", pd508=" + pd508 + "]";
	}
	
	
}
