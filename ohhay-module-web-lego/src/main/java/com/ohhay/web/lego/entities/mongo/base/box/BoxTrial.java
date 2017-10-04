package com.ohhay.web.lego.entities.mongo.base.box;

import java.io.Serializable;
import java.util.Date;

import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH
 * create Dec 28, 2015
 */
public class BoxTrial implements Serializable{
	private String stt = WebRule.WEB_PRO_STT_NEW;
	protected String id;//box id
	protected long webId;
	protected int fo100;
	private int h;//chieu cao theo don vi o grid
	private int index;
	private String data;
	private String css;
	private String bgCss;
	private String gridCss;
	private String gridBgCss;
	private String type;//footer, body, header
	private BgWebBase bgVid;
	private BgWebBase gridBgVid;
	private String name;
	private BMobileData mData;
	private Date bl946;
	private Date bl948;
	public BoxTrial() {
		// TODO Auto-generated constructor stub
	}
	
	public BoxTrial(Box box) {
		super();
		this.webId = box.getWebId();
		this.fo100 = box.getFo100();
		this.h = box.getH();
		this.index = box.getIndex();
		this.data = box.getData();
		this.css = box.getCss();
		this.bgCss = box.getBgCss();
		this.gridCss = box.getGridCss();
		this.gridBgCss = box.getGridBgCss();
		this.type = box.getType();
		this.bgVid = box.getBgVid();
		this.gridBgVid = box.getGridBgVid();
		this.name = box.getName();
		this.mData = box.getmData();
		this.bl946 = box.getBl946();
		this.bl948 = box.getBl948();
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

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public String getBgCss() {
		return bgCss;
	}

	public void setBgCss(String bgCss) {
		this.bgCss = bgCss;
	}

	public String getGridCss() {
		return gridCss;
	}

	public void setGridCss(String gridCss) {
		this.gridCss = gridCss;
	}

	public String getGridBgCss() {
		return gridBgCss;
	}

	public void setGridBgCss(String gridBgCss) {
		this.gridBgCss = gridBgCss;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BgWebBase getBgVid() {
		return bgVid;
	}

	public void setBgVid(BgWebBase bgVid) {
		this.bgVid = bgVid;
	}

	public BgWebBase getGridBgVid() {
		return gridBgVid;
	}

	public void setGridBgVid(BgWebBase gridBgVid) {
		this.gridBgVid = gridBgVid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BMobileData getmData() {
		return mData;
	}

	public void setmData(BMobileData mData) {
		this.mData = mData;
	}

	public Date getBl946() {
		return bl946;
	}

	public void setBl946(Date bl946) {
		this.bl946 = bl946;
	}

	public Date getBl948() {
		return bl948;
	}

	public void setBl948(Date bl948) {
		this.bl948 = bl948;
	}

	@Override
	public String toString() {
		return "BoxTrial [stt=" + stt + ", id=" + id + ", webId=" + webId
				+ ", fo100=" + fo100 + ", h=" + h + ", index=" + index
				+ ", data=" + data + ", css=" + css + ", bgCss=" + bgCss
				+ ", gridCss=" + gridCss + ", gridBgCss=" + gridBgCss
				+ ", type=" + type + ", bgVid=" + bgVid + ", gridBgVid="
				+ gridBgVid + ", name=" + name + ", mData=" + mData + ", bl946="
				+ bl946 + ", bl948=" + bl948 + "]";
	}
	
}
