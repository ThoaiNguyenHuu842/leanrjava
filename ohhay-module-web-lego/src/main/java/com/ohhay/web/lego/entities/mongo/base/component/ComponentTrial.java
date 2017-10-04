package com.ohhay.web.lego.entities.mongo.base.component;

import java.io.Serializable;
import java.util.Date;

import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH
 * create Dec 28, 2015
 */
public class ComponentTrial implements Serializable{
	private String stt = WebRule.WEB_PRO_STT_NEW;
	protected String id;//component id
	protected long webId;
	protected int fo100;
	private int x;
	private int y;
	private int w;
	private int h;
	private String boxId;
	private String type;
	private String data;
	private String onLoadEffect;
	private String css;
	private String bgCss;//background cua box
	private BgWebBase bgVid;
	private String name;
	private CMobileData mData;
	private Date cl946;//update date
	private Date cl948;//create dat
	public ComponentTrial(Component component) {
		super();
		this.webId = component.getWebId();
		this.fo100 = component.getFo100();
		this.x = component.getX();
		this.y = component.getY();
		this.w = component.getW();
		this.h = component.getH();
		this.type = component.getType();
		this.data = component.getData();
		this.onLoadEffect = component.getOnLoadEffect();
		this.css = component.getCss();
		this.bgCss = component.getBgCss();
		this.bgVid = new BgWebBase(component.getBgVid());
		this.name = component.getName();
		this.mData = new CMobileData(component.getmData());
		this.cl946 = component.getCl946();
		this.cl948 = component.getCl948();
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
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
	public String getOnLoadEffect() {
		return onLoadEffect;
	}
	public void setOnLoadEffect(String onLoadEffect) {
		this.onLoadEffect = onLoadEffect;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
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
	public String getBgCss() {
		return bgCss;
	}
	public void setBgCss(String bgCss) {
		this.bgCss = bgCss;
	}
	@Override
	public String toString() {
		return "ComponentTrial [stt=" + stt + ", id=" + id + ", webId=" + webId
				+ ", fo100=" + fo100 + ", x=" + x + ", y=" + y + ", w=" + w
				+ ", h=" + h + ", boxId=" + boxId + ", type=" + type + ", data="
				+ data + ", onLoadEffect=" + onLoadEffect + ", css=" + css
				+ ", bgCss=" + bgCss + ", bgVid=" + bgVid + ", name=" + name
				+ ", mData=" + mData + ", cl946=" + cl946 + ", cl948=" + cl948
				+ "]";
	}
}
