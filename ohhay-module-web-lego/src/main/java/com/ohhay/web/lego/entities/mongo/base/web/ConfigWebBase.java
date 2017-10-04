package com.ohhay.web.lego.entities.mongo.base.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Oct 31, 2015
 * update 09/07/2016, add field IMG_LAZY_LOAD
 */
public class ConfigWebBase implements Serializable{
	@Field("W")
	private int w;
	@Field("MGL")
	private int mgL;
	@Field("MGT")
	private int mgT;
	@Field("MGB")
	private int mgB;
	@Field("IMG_LAZY_LOAD")
	private int imgLazy;
	public ConfigWebBase() {
		// TODO Auto-generated constructor stub
	}
	
	public ConfigWebBase(ConfigWebBase configWebBase) {
		super();
		this.w = configWebBase.getW();
		this.mgL = configWebBase.getMgL();
		this.mgT = configWebBase.getMgT();
		this.mgB = configWebBase.getMgB();
		this.imgLazy = configWebBase.getImgLazy();
	}

	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getMgL() {
		return mgL;
	}
	public void setMgL(int mgL) {
		this.mgL = mgL;
	}
	public int getMgT() {
		return mgT;
	}
	public void setMgT(int mgT) {
		this.mgT = mgT;
	}
	public int getMgB() {
		return mgB;
	}
	public void setMgB(int mgB) {
		this.mgB = mgB;
	}

	public int getImgLazy() {
		return imgLazy;
	}

	public void setImgLazy(int imgLazy) {
		this.imgLazy = imgLazy;
	}
	
}
