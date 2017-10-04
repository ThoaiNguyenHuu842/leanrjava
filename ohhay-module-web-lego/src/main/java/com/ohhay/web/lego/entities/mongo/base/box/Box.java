package com.ohhay.web.lego.entities.mongo.base.box;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH
 * create Oct 13, 2015
 * update 23/12/2015: store background css and grid background css
 * update 12/01/2015: add a box to library (private or public)
 * update 22/01/2015: add attribute widgetName
 */
public class Box implements Serializable, Comparable<Box>{
	@Transient
	private String stt = WebRule.WEB_PRO_STT_NO_CHANGE;
	@Transient
	private String bl949String;
	@Id
	protected long id;//box id
	@Field("FE920")
	protected long fe920;//refId cua orgrinal section trong library
	@Field("WEBID")
	protected long webId;
	@Field("FO100")
	protected int fo100;
	@Field("H")
	private int h;//chieu cao theo don vi o grid
	@Field("INDEX")
	private int index;//thu tu box trong cac box thuoc web
	@Field("WIDGET_NAME")
	private String widgetName; //name of widget (EX:regis_webinaris null if this box is not a widget)
	@Field("DATA")
	private String data;//data of this box
	@Field("CSS")
	private String css;//css cua box
	@Field("BGCSS")
	private String bgCss;//background cua box
	@Field("GRID_CSS")
	private String gridCss;//css cua grid box
	@Field("GRID_BGCSS")
	private String gridBgCss;//background cua grid box
	@Field("TYPE")
	private String type;//type of box: footer, body, lib (from other section) or header 
	@Field("BG_VID")
	private BgWebBase bgVid;//background video data
	@Field("GRID_BG_VID")
	private BgWebBase gridBgVid;//background video data
	@Field("NAME")
	private String name;//box name
	@Field("MDATA")
	private BMobileData mData;//mobile data of box
	@Field("LIB_TYPE")
	private int libType;//0 - not on lib, 1 - private lib, 2 - public lib, 3 - create from reference lib
	@Field("EDITABLE")
	private int editAble;//0 - yes, 1 - no
	@Field("APPR_STT")
	private int apprStt;//0 - pending, 1 - approved, -1 - disapprove
	@Field("LIB_TITLE")
	private String libTitle;
	@Field("LIB_THUMB")
	private String libThumb;
	@Field("BL946")
	private Date bl946;// ngay update
	@Transient
	private String bl946String;
	@Field("BL948")
	private Date bl948;//ngay tao
	@Field("BL949")
	private Date bl949;//ngay cho vao lib
	@Field("BL950")
	private Date bl950;//ngay approve (disapprove)
	@Field("BL951")
	private Date bl951;//ngay remove khoi library
	@Field("OTAGS")
	private List<String> otags;//hash tags
	@Field("LIB_STT")
	private String libStt;//trang thai cua follow only library: UPDATED or NEW or '' or unset -> thay doi o pc
	@Field("LIB_STT_MO")
	private String libSttMo;//trang thai cua follow only library: UPDATED or NEW or '' or unset -> thay doi o mobile
	@Field("TOTAL_ADDED")
	private int totalAdded;
	@Field("RE_TYPE")
	private int reType;//responsive section type (1,2,3,4)
	public Box() {
		// TODO Auto-generated constructor stub
	}
	public Box(Box box) {
		super();
		this.stt = box.getStt();
		this.id = box.getId();
		this.webId = box.getWebId();
		this.fo100 = box.getFo100();
		this.h = box.getH();
		this.index = box.getIndex();
		this.data = box.getData();
		this.widgetName = box.getWidgetName();
		this.css = box.getCss();
		this.bgCss = box.getBgCss();
		this.gridCss = box.getGridCss();
		this.gridBgCss = box.getGridBgCss();
		this.type = box.getType();
		this.bgVid = box.getBgVid();
		this.gridBgVid = box.getGridBgVid();
		this.name = box.getName();
		this.mData = box.getmData();
		this.bl946 = new Date();
		this.bl948 = new Date();
		this.reType = box.getReType();
		//neu tham chieu den mot library thi van luu lai
		if(box.getLibType() == 3)
		{
			this.libType = box.getLibType();
			this.fe920 = box.getFe920();
		}
	}
	
	public String getWidgetName() {
		return widgetName;
	}
	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}
	public int getApprStt() {
		return apprStt;
	}
	public void setApprStt(int apprStt) {
		this.apprStt = apprStt;
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
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getGridCss() {
		return gridCss;
	}
	public void setGridCss(String gridCss) {
		this.gridCss = gridCss;
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
	
	public BMobileData getmData() {
		return mData;
	}
	public void setmData(BMobileData mData) {
		this.mData = mData;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getBgCss() {
		return bgCss;
	}
	public void setBgCss(String bgCss) {
		this.bgCss = bgCss;
	}
	public String getGridBgCss() {
		return gridBgCss;
	}
	public void setGridBgCss(String gridBgCss) {
		this.gridBgCss = gridBgCss;
	}
	
	public int getLibType() {
		return libType;
	}
	public void setLibType(int libType) {
		this.libType = libType;
	}
	public Date getBl949() {
		return bl949;
	}
	public void setBl949(Date bl949) {
		this.bl949 = bl949;
	}
	public Date getBl950() {
		return bl950;
	}
	public void setBl950(Date bl950) {
		this.bl950 = bl950;
	}
	public List<String> getOtags() {
		return otags;
	}
	public void setOtags(List<String> otags) {
		this.otags = otags;
	}
	
	public long getFe920() {
		return fe920;
	}
	public void setFe920(long fe920) {
		this.fe920 = fe920;
	}
	public String getBl946String() {
		return bl946String;
	}
	public void setBl946String(String bl946String) {
		this.bl946String = bl946String;
	}
	public String getBl949String() {
		return bl949String;
	}
	public void setBl949String(String bl949String) {
		this.bl949String = bl949String;
	}
	public Date getBl951() {
		return bl951;
	}
	public void setBl951(Date bl951) {
		this.bl951 = bl951;
	}
	
	public String getLibTitle() {
		return libTitle;
	}
	public void setLibTitle(String libTitle) {
		this.libTitle = libTitle;
	}
	public int getEditAble() {
		return editAble;
	}
	public void setEditAble(int editAble) {
		this.editAble = editAble;
	}
	
	public String getLibThumb() {
		return libThumb;
	}
	public void setLibThumb(String libThumb) {
		this.libThumb = libThumb;
	}
	
	public String getLibStt() {
		return libStt;
	}
	public void setLibStt(String libStt) {
		this.libStt = libStt;
	}
	
	public String getLibSttMo() {
		return libSttMo;
	}
	public void setLibSttMo(String libSttMo) {
		this.libSttMo = libSttMo;
	}
	
	public int getTotalAdded() {
		return totalAdded;
	}
	public void setTotalAdded(int totalAdded) {
		this.totalAdded = totalAdded;
	}
	
	public int getReType() {
		return reType;
	}
	public void setReType(int reType) {
		this.reType = reType;
	}
	@Override
	public int compareTo(Box arg0) {
		// TODO Auto-generated method stub
		if(this.getIndex() > arg0.getIndex())
			return 1;
		else if(this.getIndex() < arg0.getIndex())
			return -1;
		else
			return 0;
	}
	@Override
	public String toString() {
		return "Box [stt=" + stt + ", bl949String=" + bl949String + ", id=" + id
				+ ", fe920=" + fe920 + ", webId=" + webId + ", fo100=" + fo100
				+ ", h=" + h + ", index=" + index + ", widgetName=" + widgetName
				+ ", data=" + data + ", css=" + css + ", bgCss=" + bgCss
				+ ", gridCss=" + gridCss + ", gridBgCss=" + gridBgCss
				+ ", type=" + type + ", bgVid=" + bgVid + ", gridBgVid="
				+ gridBgVid + ", name=" + name + ", mData=" + mData
				+ ", libType=" + libType + ", editAble=" + editAble
				+ ", apprStt=" + apprStt + ", libTitle=" + libTitle
				+ ", libThumb=" + libThumb + ", bl946=" + bl946
				+ ", bl946String=" + bl946String + ", bl948=" + bl948
				+ ", bl949=" + bl949 + ", bl950=" + bl950 + ", bl951=" + bl951
				+ ", otags=" + otags + ", libStt=" + libStt + ", libSttMo="
				+ libSttMo + ", totalAdded=" + totalAdded + ", reType=" + reType
				+ "]";
	}
	
}
