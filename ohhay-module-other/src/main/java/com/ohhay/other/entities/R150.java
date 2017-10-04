package com.ohhay.other.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Types;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbMapping;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.core.utils.MVCResourceBundleUtil;

public class R150 implements Serializable{
	@QbMapping(name = "pr150", typeMapping = Types.INTEGER)
	private int pr150;
	@QbMapping(name = "fo100s", typeMapping = Types.INTEGER)
	private int fo100s;
	@QbMapping(name = "rv151", typeMapping = Types.CHAR)
	private String rv151;
	
	@QbMapping(name = "rv152", typeMapping = Types.CHAR)
	private String rv152;
	
	@QbMapping(name = "rv153", typeMapping = Types.CHAR)
	private String rv153;
	
	@QbMapping(name = "rd154", typeMapping = Types.TIMESTAMP)
	private Timestamp rd154;
	
	@QbMapping(name = "rv155", typeMapping = Types.CHAR)
	private String rv155;
	
	@QbMapping(name = "rl166", typeMapping = Types.TIMESTAMP)
	private Timestamp rl166;

	@QbMapping(name = "rl167", typeMapping = Types.CHAR)
	private String rl167;

	@QbMapping(name = "nv109", typeMapping = Types.CHAR)
	private String nv109;
	
	@QbMapping(name = "FULLNAME", typeMapping = Types.CHAR)
	private String fullname;
	
	@QbMapping(name = "REFID", typeMapping = Types.INTEGER)
	private int refid;
	
	@QbMapping(name = "OV101", typeMapping = Types.CHAR)
	private String ov101;
	private String timeinfo;
	private String urlAvarta;
	private String rd154str;
	public int getPr150() {
		return pr150;
	}

	public void setPr150(int pr150) {
		this.pr150 = pr150;
	}

	public String getRv151() {
		return rv151;
	}

	public void setRv151(String rv151) {
		this.rv151 = rv151;
	}

	public String getRv152() {
		return rv152;
	}

	public void setRv152(String rv152) {
		this.rv152 = rv152;
	}

	public String getRv153() {
		return rv153;
	}

	public void setRv153(String rv153) {
		this.rv153 = rv153;
	}

	
	public Timestamp getRd154() {
		return rd154;
	}

	public void setRd154(Timestamp rd154) {
		this.rd154 = rd154;
	}


	public String getRl167() {
		return rl167;
	}

	public void setRl167(String rl167) {
		this.rl167 = rl167;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Timestamp getRl166() {
		return rl166;
	}

	public void setRl166(Timestamp rl166) {
		this.rl166 = rl166;
	}

	public String getTimeinfo() {
		if(rd154!= null){
			timeinfo = DateHelper.convertDateToTimeLine(rd154);
		}
		return timeinfo;
	}

	public void setTimeinfo(String timeinfo) {
		this.timeinfo = timeinfo;
	}

	public String getNv109() {
		return nv109;
	}

	public void setNv109(String nv109) {
		this.nv109 = nv109;
	}

	public int getFo100s() {
		return fo100s;
	}

	public void setFo100s(int fo100s) {
		this.fo100s = fo100s;
	}

	public String getUrlAvarta() {
		String urlAvarta;
		if (nv109 != null && nv109.length() > 0)
		{
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(fo100s, fo100s, M900MG.class, QbMongoFiledsName.REGION);
			urlAvarta = m900mg.getImgLinkCloudFront()+nv109;
		}
		else
			urlAvarta = ApplicationConstant.CONTEXT_PATH+"html/images/44.png";
		return urlAvarta;
	}

	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}

	public String getRd154str() {
		if(rd154!=null){
			rd154str = DateHelper.formatDateLong(rd154);
		}
		return rd154str;
	}
	
	public void setRd154str(String rd154str) {
		this.rd154str = rd154str;
	}
	public String getRd154Timeline(){
		return DateHelper.convertDateToTimeLine(rd154);
	}
	public String getRd154Dmy(){
		return DateHelper.formatDateShort(rd154);
	}

	public String getRv155() {
		return rv155;
	}

	public void setRv155(String rv155) {
		this.rv155 = rv155;
	}

	public int getRefid() {
		return refid;
	}

	public void setRefid(int refid) {
		this.refid = refid;
	}
	
	public String getOv101() {
		return ov101;
	}

	public void setOv101(String ov101) {
		this.ov101 = ov101;
	}

	public String getMessage()
	{
		if(rv155.equals("VOTE"))
			return MVCResourceBundleUtil.getResourceBundle("nod.vote");
		else if(rv155.equals("SHAT"))
			return MVCResourceBundleUtil.getResourceBundle("nod.sharetopic");
		else
			return MVCResourceBundleUtil.getResourceBundle("nod.shareweb");
	}
}
