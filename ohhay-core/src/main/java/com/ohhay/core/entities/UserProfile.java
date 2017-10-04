package com.ohhay.core.entities;

import java.io.Serializable;
import java.util.List;

import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.shop.J910MG;
import com.ohhay.core.entities.mongo.videomarketing.V910MG;

/**
 * @author ThoaiNH
 * created: 05/08/2015
 * user profile send to app via webservice
 */
public class UserProfile implements Serializable{
	private M900MG m900mg;
	private List<V910MG> listV910mgs;//danh sach web video markeing
	private J910MG j910mg;//shop online
	private List<LanguageMG> listC500mg;//danh sach ngon ngu
	public M900MG getM900mg() {
		return m900mg;
	}
	public void setM900mg(M900MG m900mg) {
		this.m900mg = m900mg;
	}
	public List<V910MG> getListV910mgs() {
		return listV910mgs;
	}
	public void setListV910mgs(List<V910MG> listV910mgs) {
		this.listV910mgs = listV910mgs;
	}
	public J910MG getJ910mg() {
		return j910mg;
	}
	public void setJ910mg(J910MG j910mg) {
		this.j910mg = j910mg;
	}
	public List<LanguageMG> getListC500mg() {
		return listC500mg;
	}
	public void setListC500mg(List<LanguageMG> listC500mg) {
		this.listC500mg = listC500mg;
	}
	
}	
