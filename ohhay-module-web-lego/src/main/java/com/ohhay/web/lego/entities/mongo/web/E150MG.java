package com.ohhay.web.lego.entities.mongo.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.mongo.profile.M900MG;

/**
 * @author ThoaiNH
 * create Nov 17, 2016
 * relationship between customer - designer - web
 */
/**
 * @author TuNt
 * create date Dec 5, 2016
 * ohhay-module-web-lego
 */
@Document(collection = QbMongoCollectionsName.E150)
public class E150MG implements Serializable{
	public final static String WAI = "WAI";
	public final static String NEW = "NEW";
	public final static String DEC = "DEC";
	public final static String CON = "CON";
	@Id
	private int id;
	@Field("FO100C")
	private int fo100c;//fo100 customer
	@Field("FO100D")
	private int fo100d;//fo100 designer
	@Field("FE400")
	private long fe400;//web id
	@Field("FE400D")
	private long fe400d;//web id clone for designer
	@Field("FE920s")
	private List<Integer> listFE920s;//array section id
	@Field("E200")
	private E200MG e200mg;//backup designer submitted
	@Field("EV151")
	private String ev151;//note
	@Field("EV152")
	private String ev152;//status: NEW, DEC, WAI, CON
	@Field("EA153")
	private List<E160MG> listRejection;//history of customer rejection
	@Field("EL156")
	private Date el156;//update date
	@Field("EL158")
	private Date el158;//created date
	@Field("EL159")
	private Date el159;//designer confirm date (start date)
	@Field("EL160")
	private Date el160;//customer confirmed date (finish date)
	@Transient()
	private E400MG e400c;
	@Transient()
	private int totalSites;
	@Transient()
	private String el159String;
	@Transient()
	private M900MG m900c;
	@Transient
	private List<Status> status;
	@Transient
	private String el160String;
	@Transient
	private int totalCus;
	
	public M900MG getM900c() {
		return m900c;
	}
	public void setM900c(M900MG m900c) {
		this.m900c = m900c;
	}
	public String getEl159String() {
		return el159String;
	}
	public void setEl159String(String el159String) {
		this.el159String = el159String;
	}
	public int getTotalSites() {
		return totalSites;
	}
	public void setTotalSites(int totalSites) {
		this.totalSites = totalSites;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFo100c() {
		return fo100c;
	}
	public void setFo100c(int fo100c) {
		this.fo100c = fo100c;
	}
	public int getFo100d() {
		return fo100d;
	}
	public void setFo100d(int fo100d) {
		this.fo100d = fo100d;
	}
	
	public long getFe400() {
		return fe400;
	}
	public void setFe400(long fe400) {
		this.fe400 = fe400;
	}
	public long getFe400d() {
		return fe400d;
	}
	public void setFe400d(long fe400d) {
		this.fe400d = fe400d;
	}
	public List<Integer> getListFE920s() {
		return listFE920s;
	}
	public void setListFE920s(List<Integer> listFE920s) {
		this.listFE920s = listFE920s;
	}
	public E200MG getE200mg() {
		return e200mg;
	}
	public void setE200mg(E200MG e200mg) {
		this.e200mg = e200mg;
	}
	public String getEv151() {
		return ev151;
	}
	public void setEv151(String ev151) {
		this.ev151 = ev151;
	}
	public String getEv152() {
		return ev152;
	}
	public void setEv152(String ev152) {
		this.ev152 = ev152;
	}
	public Date getEl156() {
		return el156;
	}
	public void setEl156(Date el156) {
		this.el156 = el156;
	}
	public Date getEl158() {
		return el158;
	}
	public void setEl158(Date el158) {
		this.el158 = el158;
	}
	public Date getEl159() {
		return el159;
	}
	public void setEl159(Date el159) {
		this.el159 = el159;
	}
	public Date getEl160() {
		return el160;
	}
	public void setEl160(Date el160) {
		this.el160 = el160;
	}
	
	public E400MG getE400c() {
		return e400c;
	}
	public void setE400c(E400MG e400c) {
		this.e400c = e400c;
	}
	public List<Status> getStatus() {
		return status;
	}
	public void setStatus(List<Status> status) {
		this.status = status;
	}
	public List<E160MG> getListRejection() {
		return listRejection;
	}
	public void setListRejection(List<E160MG> listRejection) {
		this.listRejection = listRejection;
	}
	public String getEl160String() {
		return el160String;
	}
	public void setEl160String(String el160String) {
		this.el160String = el160String;
	}
	
	public int getTotalCus() {
		return totalCus;
	}
	public void setTotalCus(int totalCus) {
		this.totalCus = totalCus;
	}
	@Override
	public String toString() {
		return "E150MG [id=" + id + ", fo100c=" + fo100c + ", fo100d=" + fo100d + ", fe400=" + fe400 + ", fe400d="
				+ fe400d + ", listFE920s=" + listFE920s + ", e200mg=" + e200mg + ", ev151=" + ev151 + ", ev152=" + ev152
				+ ", listRejection=" + listRejection + ", el156=" + el156 + ", el158=" + el158 + ", el159=" + el159
				+ ", el160=" + el160 + ", e400c=" + e400c + ", totalSites=" + totalSites + ", el159String="
				+ el159String + ", m900c=" + m900c + ", status=" + status + ", el160String=" + el160String
				+ ", totalCus=" + totalCus + "]";
	}
	
}
