package com.ohhay.web.lego.entities.mongo.base.box;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Dec 7, 2015
 */
public class BMobileData implements Serializable{
	@Field("H")
	private int h;
	@Field("HIDE")
	private boolean hide;
	@Field("BGCSS")
	private String bgCss;
	@Field("GRID_BGCSS")
	private String gridBgCss;
	@Field("CSS")
	private String css;
	@Field("GRID_CSS")
	private String gridCss;//css cua grid box
	public BMobileData() {
		// TODO Auto-generated constructor stub
	}
	public BMobileData(BMobileData bMobileData) {
		super();
		if(bMobileData != null){
			this.h = bMobileData.getH();
			this.hide = bMobileData.isHide();
			this.bgCss = bMobileData.getBgCss();
			this.gridBgCss = bMobileData.getGridBgCss();
			this.css = bMobileData.getCss();
			this.gridCss = bMobileData.getGridCss();
		}
	}
	
	public String getGridCss() {
		return gridCss;
	}
	public void setGridCss(String gridCss) {
		this.gridCss = gridCss;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getGridBgCss() {
		return gridBgCss;
	}

	public void setGridBgCss(String gridBgCss) {
		this.gridBgCss = gridBgCss;
	}

	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public boolean isHide() {
		return hide;
	}
	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public String getBgCss() {
		return bgCss;
	}

	public void setBgCss(String bgCss) {
		this.bgCss = bgCss;
	}
	@Override
	public String toString() {
		return "BMobileData [h=" + h + ", hide=" + hide + ", bgCss=" + bgCss
				+ ", gridBgCss=" + gridBgCss + ", css=" + css + ", gridCss="
				+ gridCss + "]";
	}
	
}
