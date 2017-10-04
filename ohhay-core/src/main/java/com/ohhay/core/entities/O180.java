package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Types;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.dao.QbMapping;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;

/**
 * @author ThoaiNH
 * create 10/10/2014
 * map list people bookmark me 
 */
public class O180 implements Serializable {
	@QbMapping(name = "FO100P", typeMapping = Types.INTEGER)
	private int fo100p;
	@QbMapping(name = "OL196", typeMapping = Types.TIMESTAMP)
	private Timestamp ol196;
	@QbMapping(name = "ROWSS", typeMapping = Types.INTEGER)
	private int rowss;
	@QbMapping(name = "NV101", typeMapping = Types.CHAR)
	private String nv101;
	@QbMapping(name = "NV102", typeMapping = Types.CHAR)
	private String nv102;
	@QbMapping(name = "NV107", typeMapping = Types.CHAR)
	private String nv107;
	@QbMapping(name = "NV109", typeMapping = Types.CHAR)
	private String nv109;
	public int getFo100p() {
		return fo100p;
	}
	public void setFo100p(int fo100p) {
		this.fo100p = fo100p;
	}
	public Timestamp getOl196() {
		return ol196;
	}
	public void setOl196(Timestamp ol196) {
		this.ol196 = ol196;
	}
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public String getOl916String() {
		String sDate = DateHelper.formatDateLong(ol196);
		if(sDate != null)
			return sDate;
		else
			return "";
	}
	public String getOl916TimeLine(){
		return DateHelper.convertDateToTimeLine(ol196);
	}
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}
	public String getNv102() {
		return nv102;
	}
	public void setNv102(String nv102) {
		this.nv102 = nv102;
	}
	public String getNv107() {
		return nv107;
	}
	public void setNv107(String nv107) {
		this.nv107 = nv107;
	}
	public String getNv109() {
		return nv109;
	}
	public void setNv109(String nv109) {
		this.nv109 = nv109;
	}
	public String getUserNameToShow() {
		String name = AESUtils.decrypt(nv101)+" "+AESUtils.decrypt(nv102);
		if(name !=null && name.trim().length()>0)
			return name;
		return nv107;
	}
	public String getUrlAvarta() {
		String urlAvarta;
		if (nv109 != null && nv109.length() > 0)
		{
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(0, fo100p, M900MG.class, QbMongoFiledsName.REGION);
			urlAvarta = m900mg.getImgLinkCloudFront()+nv109;
		}
		else
			urlAvarta = ApplicationConstant.CONTEXT_PATH+"html/images/44.png";
		return urlAvarta;
	}
	public String getOl196TimeLine(){
		return DateHelper.convertDateToTimeLine(ol196);
	}
	public String getOl946String() {
		String sDate = DateHelper.formatDateLong(ol196);
		if(sDate != null)
			return sDate;
		else
			return "";
	}
}
