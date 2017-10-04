package com.ohhay.web.lego.entities.mongo.base.component;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH
 * create Oct 13, 2015
 *  * update 22/01/2015: add attribute widgetId
 */
public class Component implements Serializable{
	@Transient
	private String stt = WebRule.WEB_PRO_STT_NO_CHANGE;
	@Id
	protected long id;//component id
	@Field("WEBID")
	protected long webId;
	@Field("FO100")
	protected int fo100;
	@Field("X")
	private int x;
	@Field("Y")
	private int y;
	@Field("W")
	private int w;
	@Field("H")
	private int h;
	@Field("BOX_ID")
	private long boxId;
	@Field("TYPE")
	private String type;
	@Field("DATA")
	private String data;
	@Field("ONLOAD_EFFECT")
	private String onLoadEffect;
	@Field("CSS")
	private String css;
	@Field("BGCSS")
	private String bgCss;//background cua box
	@Field("BG_VID")
	private BgWebBase bgVid;
	@Field("NAME")
	private String name;
	@Field("WIDGET_ELE_ID")
	private String widgetEleId;
	@Field("MDATA")
	private CMobileData mData;
	@Field("GRID_INDEX")
	private int gridIndex;//index of grid in responsive section containing his component 
	@Field("CL946")
	private Date cl946;//update date
	@Field("CL948")
	private Date cl948;//create date
	@Field("FE900")
	private int fe900;
	public Component() {
		// TODO Auto-generated constructor stub
	}
	
	public Component(Component component) {
		super();
		this.stt = component.getStt();
		this.id = component.getId();
		this.webId = component.getWebId();
		this.fo100 = component.getFo100();
		this.x = component.getX();
		this.y = component.getY();
		this.w = component.getW();
		this.h = component.getH();
		this.boxId = component.getBoxId();
		this.type = component.getType();
		this.data = component.getData();
		this.onLoadEffect = component.getOnLoadEffect();
		this.css = component.getCss();
		this.bgVid = new BgWebBase(component.getBgVid());
		this.name = component.getName();
		this.mData = new CMobileData(component.getmData());
		this.cl946 = new Date();
		this.cl948 = new Date();
		this.widgetEleId = component.getWidgetEleId();
		this.bgCss = component.getBgCss();
		this.gridIndex = component.getGridIndex();
	}
	
	
	public String getBgCss() {
		return bgCss;
	}

	public void setBgCss(String bgCss) {
		this.bgCss = bgCss;
	}

	public String getWidgetEleId() {
		return widgetEleId;
	}

	public void setWidgetEleId(String widgetEleId) {
		this.widgetEleId = widgetEleId;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWebId() {
		return webId;
	}
	public void setWebId(long webId) {
		this.webId = webId;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
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
	public long getBoxId() {
		return boxId;
	}
	public void setBoxId(long boxId) {
		this.boxId = boxId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public BgWebBase getBgVid() {
		return bgVid;
	}
	public void setBgVid(BgWebBase bgVid) {
		this.bgVid = bgVid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CMobileData getmData() {
		return mData;
	}
	public void setmData(CMobileData mData) {
		this.mData = mData;
	}
	public Date getCl946() {
		return cl946;
	}
	public void setCl946(Date cl946) {
		this.cl946 = cl946;
	}
	public Date getCl948() {
		return cl948;
	}
	public void setCl948(Date cl948) {
		this.cl948 = cl948;
	}
	public String getOnLoadEffect() {
		return onLoadEffect;
	}
	public void setOnLoadEffect(String onLoadEffect) {
		this.onLoadEffect = onLoadEffect;
	}

	public int getGridIndex() {
		return gridIndex;
	}

	public void setGridIndex(int gridIndex) {
		this.gridIndex = gridIndex;
	}

	public int getFe900() {
		return fe900;
	}

	public void setFe900(int fe900) {
		this.fe900 = fe900;
	}

	@Override
	public String toString() {
		return "Component [stt=" + stt + ", id=" + id + ", webId=" + webId
				+ ", fo100=" + fo100 + ", x=" + x + ", y=" + y + ", w=" + w
				+ ", h=" + h + ", boxId=" + boxId + ", type=" + type + ", data="
				+ data + ", onLoadEffect=" + onLoadEffect + ", css=" + css
				+ ", bgCss=" + bgCss + ", bgVid=" + bgVid + ", name=" + name
				+ ", widgetEleId=" + widgetEleId + ", mData=" + mData
				+ ", gridIndex=" + gridIndex + ", cl946=" + cl946 + ", cl948="
				+ cl948 + ", fe900=" + fe900 + "]";
	}
	
}
