package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

import com.ohhay.core.entities.mongo.profile.M900MG;

/**
 * @author ThoaiNH 
 * create Dec 14, 2016 
 * mapping information PIEPME message conversation
 */
public class P300ConInfo implements Serializable {
	private int fp300;
	private String pv301;
	private String pv302;
	private String nv100;
	private String urlAvatar;
	private String pd308;
	private int isNew; //1:has new message
	private int fo100f;//fo100 ban be
	private int fo100;//fo100 request tu web service
	private int fo100Owner;//chu pieper moi nhat
	private int pn303;
	public int getFp300() {
		return fp300;
	}

	public void setFp300(int fp300) {
		this.fp300 = fp300;
	}

	public String getPv301() {
		return pv301;
	}

	public void setPv301(String pv301) {
		this.pv301 = pv301;
	}

	public String getNv100() {
		return nv100;
	}

	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}

	public String getUrlAvatar() {
		return urlAvatar;
	}

	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}

	public String getPd308() {
		return pd308;
	}

	public void setPd308(String pd308) {
		this.pd308 = pd308;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getFo100f() {
		return fo100f;
	}

	public void setFo100f(int fo100f) {
		this.fo100f = fo100f;
	}

	public String getPv302() {
		return pv302;
	}

	public void setPv302(String pv302) {
		this.pv302 = pv302;
	}

	public int getFo100() {
		return fo100;
	}

	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}

	public int getPn303() {
		return pn303;
	}

	public void setPn303(int pn303) {
		this.pn303 = pn303;
	}

	public int getFo100Owner() {
		return fo100Owner;
	}

	public void setFo100Owner(int fo100Owner) {
		this.fo100Owner = fo100Owner;
	}
	
	
}
