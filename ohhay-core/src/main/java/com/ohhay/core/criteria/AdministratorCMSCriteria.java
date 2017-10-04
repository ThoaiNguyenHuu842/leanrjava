package com.ohhay.core.criteria;

import java.io.Serializable;
import java.util.List;

/**
 * @author ThoaiVt
 * create date Mar 3, 2016
 * ohhay-core
 */
public class AdministratorCMSCriteria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String ev401;
	private int en402;
	private String ev403;
	private List<Integer> listCategory;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEv401() {
		return ev401;
	}
	public void setEv401(String ev401) {
		this.ev401 = ev401;
	}
	public int getEn402() {
		return en402;
	}
	public void setEn402(int en402) {
		this.en402 = en402;
	}
	public String getEv403() {
		return ev403;
	}
	public void setEv403(String ev403) {
		this.ev403 = ev403;
	}
	
	public List<Integer> getListCategory() {
		return listCategory;
	}
	public void setListCategory(List<Integer> listCategory) {
		this.listCategory = listCategory;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AdministratorCMSCriteria [id=" + id + ", ev401=" + ev401 + ", en402=" + en402 + ", ev403=" + ev403
				+ ", listCategory=" + listCategory + "]";
	}
	
	
}
