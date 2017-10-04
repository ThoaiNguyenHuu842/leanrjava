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
 * @author ThoaiNH
 * create Feb 25, 2014
 */
public class O100 implements Serializable{
	@QbMapping(name = "pn100", typeMapping = Types.INTEGER)
	private int pn100;
	@QbMapping(name = "nv101", typeMapping = Types.CHAR)
	private String nv101;
	@QbMapping(name = "nv102", typeMapping = Types.CHAR)
	private String nv102;
	@QbMapping(name = "nv103", typeMapping = Types.CHAR)
	private String nv103;
	@QbMapping(name = "nv104", typeMapping = Types.CHAR)
	private String nv104;
	@QbMapping(name = "nv105", typeMapping = Types.CHAR)
	private String nv105;
	@QbMapping(name = "nl145", typeMapping = Types.CHAR)
	private String nl145;
	@QbMapping(name = "nl146", typeMapping = Types.DATE)
	private Date nl146;
	@QbMapping(name = "nl147", typeMapping = Types.CHAR)
	private String nl147;
	@QbMapping(name = "nl148", typeMapping = Types.DATE)
	private Date nl148;
	@QbMapping(name = "fo100", typeMapping = Types.INTEGER)
	private int fo100;
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
	@QbMapping(name = "fc400", typeMapping = Types.INTEGER)
	private int fc400;
	@QbMapping(name = "statu", typeMapping = Types.INTEGER)
	private int statu;
	public M900MG getProfile(){
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		M900MG m900mg = templateService.findDocumentById(po100, po100, M900MG.class);
		return m900mg;
		
	}
	public int getPn100() {
		return pn100;
	}
	public void setPn100(int pn100) {
		this.pn100 = pn100;
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
	public String getNv103() {
		return nv103;
	}
	public void setNv103(String nv103) {
		this.nv103 = nv103;
	}
	public String getNv104() {
		return nv104;
	}
	public void setNv104(String nv104) {
		this.nv104 = nv104;
	}
	public String getNv105() {
		return nv105;
	}
	public void setNv105(String nv105) {
		this.nv105 = nv105;
	}
	public String getNl145() {
		return nl145;
	}
	public void setNl145(String nl145) {
		this.nl145 = nl145;
	}
	public Date getNl146() {
		return nl146;
	}
	public void setNl146(Date nl146) {
		this.nl146 = nl146;
	}
	public String getNl147() {
		return nl147;
	}
	public void setNl147(String nl147) {
		this.nl147 = nl147;
	}
	public Date getNl148() {
		return nl148;
	}
	public void setNl148(Date nl148) {
		this.nl148 = nl148;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
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
	public int getFc400() {
		return fc400;
	}
	public void setFc400(int fc400) {
		this.fc400 = fc400;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
}
