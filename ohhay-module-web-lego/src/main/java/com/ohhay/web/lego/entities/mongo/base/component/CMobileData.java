package com.ohhay.web.lego.entities.mongo.base.component;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Dec 7, 2015
 * mobile data of component
 */
public class CMobileData implements Serializable{
	@Field("X")
	private int x;
	@Field("Y")
	private int y;
	@Field("W")
	private int w;
	@Field("H")
	private int h;
	@Field("DATA")
	private String data;
	@Field("HIDE")
	private boolean hide;
	@Field("CSS")
	private String css;
	public CMobileData() {
		// TODO Auto-generated constructor stub
	}
	
	public CMobileData(CMobileData cMobileData) {
		super();
		if(cMobileData != null){
			this.x = cMobileData.getX();
			this.y = cMobileData.getY();
			this.w = cMobileData.getW();
			this.h = cMobileData.getH();
			this.data = cMobileData.getData();
			this.hide = cMobileData.isHide();
			this.css = cMobileData.getCss();
		}
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isHide() {
		return hide;
	}
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	@Override
	public String toString() {
		return "CMobileData [x=" + x + ", y=" + y + ", w=" + w + ", h=" + h
				+ ", data=" + data + ", hide=" + hide + ", css=" + css + "]";
	}
	
}
