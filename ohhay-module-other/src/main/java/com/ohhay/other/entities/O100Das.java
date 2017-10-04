package com.ohhay.other.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.ohhay.base.dao.QbMapping;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiVt
 * create date Mar 28, 2016
 * ohhay-module-other
 */
public class O100Das implements Serializable{
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@QbMapping(name = "po100", typeMapping = Types.INTEGER)
	private int po100;
	@QbMapping(name = "ov101", typeMapping = Types.CHAR)
	private String ov101;
	@QbMapping(name = "ov102", typeMapping = Types.CHAR)
	private String ov102;
	@QbMapping(name = "ov103", typeMapping = Types.CHAR)
	private String ov103;
	@QbMapping(name = "ov104", typeMapping = Types.CHAR)
	private String ov104;
	@QbMapping(name = "od114", typeMapping = Types.DATE)
	private Date od114;
	@QbMapping(name = "on115", typeMapping = Types.INTEGER)
	private int on115;
	@QbMapping(name = "ov120", typeMapping = Types.CHAR)
	private String ov120;
	@QbMapping(name = "ov121", typeMapping = Types.CHAR)
	private String ov121;
	@QbMapping(name = "ov122", typeMapping = Types.CHAR)
	private String ov122;
	@QbMapping(name = "ov123", typeMapping = Types.CHAR)
	private String ov123; 
	@QbMapping(name = "ov125", typeMapping = Types.CHAR)
	private String ov125; 
	@QbMapping(name = "ov116", typeMapping = Types.CHAR)
	private String ov116; 
	@QbMapping(name = "ov117", typeMapping = Types.CHAR)
	private String ov117; 
	@QbMapping(name = "ov118", typeMapping = Types.CHAR)
	private String ov118; 
	@QbMapping(name = "od119", typeMapping = Types.DATE)
	private Date od119;
	@QbMapping(name = "ov130", typeMapping = Types.CHAR)
	private Date ov130;
	@QbMapping(name = "ov131", typeMapping = Types.CHAR)
	private Date ov131;
	@QbMapping(name = "ov132", typeMapping = Types.CHAR)
	private String ov132;
	@QbMapping(name = "rowss", typeMapping = Types.INTEGER)
	private int rowss;
	@QbMapping(name="ol146",typeMapping=Types.DATE)
	private Date ol146;
	@QbMapping(name="od132",typeMapping=Types.DATE)
	private Date od132;
	public int getPo100() {
		return po100;
	}
	public void setPo100(int po100) {
		this.po100 = po100;
	}
	public String getOv101() {
		return ov101;
	}
	public void setOv101(String ov101) {
		this.ov101 = ov101;
	}
	public String getOv102() {
		return ov102;
	}
	public void setOv102(String ov102) {
		this.ov102 = ov102;
	}
	public String getOv103() {
		return ov103;
	}
	public void setOv103(String ov103) {
		this.ov103 = ov103;
	}
	public String getOv104() {
		return ov104;
	}
	public void setOv104(String ov104) {
		this.ov104 = ov104;
	}
	public Date getOd114() {
		return od114;
	}
	public void setOd114(Date od114) {
		this.od114 = od114;
	}
	public int getOn115() {
		return on115;
	}
	public void setOn115(int on115) {
		this.on115 = on115;
	}
	public String getOv120() {
		return ov120;
	}
	public void setOv120(String ov120) {
		this.ov120 = ov120;
	}
	public String getOv121() {
		return ov121;
	}
	public void setOv121(String ov121) {
		this.ov121 = ov121;
	}
	public String getOv122() {
		return ov122;
	}
	public void setOv122(String ov122) {
		this.ov122 = ov122;
	}
	public String getOv123() {
		return ov123;
	}
	public void setOv123(String ov123) {
		this.ov123 = ov123;
	}
	public String getOv125() {
		return ov125;
	}
	public void setOv125(String ov125) {
		this.ov125 = ov125;
	}
	public String getOv116() {
		return ov116;
	}
	public void setOv116(String ov116) {
		this.ov116 = ov116;
	}
	public String getOv117() {
		return ov117;
	}
	public void setOv117(String ov117) {
		this.ov117 = ov117;
	}
	public String getOv118() {
		return ov118;
	}
	public void setOv118(String ov118) {
		this.ov118 = ov118;
	}
	public Date getOd119() {
		return od119;
	}
	public void setOd119(Date od119) {
		this.od119 = od119;
	}
	public Date getOv130() {
		return ov130;
	}
	public void setOv130(Date ov130) {
		this.ov130 = ov130;
	}
	public Date getOv131() {
		return ov131;
	}
	public void setOv131(Date ov131) {
		this.ov131 = ov131;
	}
	public String getOv132() {
		return ov132;
	}
	public void setOv132(String ov132) {
		this.ov132 = ov132;
	}
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	
	public Date getOl146() {
		return ol146;
	}
	public void setOl146(Date ol146) {
		this.ol146 = ol146;
	}
	public Date getOd132() {
		return od132;
	}
	public void setOd132(Date od132) {
		this.od132 = od132;
	}
	public M900MG getUser(){
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		M900MG m900mg = templateService.findDocumentById(po100, po100, M900MG.class);
		return m900mg;
	}
	@Override
	public String toString() {
		return "O100Das [po100=" + po100 + ", ov101=" + ov101 + ", ov102=" + ov102 + ", ov103=" + ov103 + ", ov104="
				+ ov104 + ", od114=" + od114 + ", on115=" + on115 + ", ov120=" + ov120 + ", ov121=" + ov121 + ", ov122="
				+ ov122 + ", ov123=" + ov123 + ", ov125=" + ov125 + ", ov116=" + ov116 + ", ov117=" + ov117 + ", ov118="
				+ ov118 + ", od119=" + od119 + ", ov130=" + ov130 + ", ov131=" + ov131 + ", ov132=" + ov132 + ", rowss="
				+ rowss + ", ol146=" + ol146 + ", od132=" + od132 + "]";
	}
	
}
