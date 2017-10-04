package com.ohhay.web.core.entities.mongo.webbase;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * update: 30/06/2015 them cv115 luu postion css
 */
public class C110MG {
	@Field("CV111")
	private String cv111;
	@Field("CV112")
	private String cv112;
	@Field("CV113")
	private String cv113;//tracking object name
	@Field("CN114")
	private int cn114;//tracking active status: 1 = active, 0 = deactive
	@Field("CV115")
	private String cv115;//position css
	@Field("CV116")
	private String cv116;//more info of text element ex: email form send mail info
	@Field("CV117")
	private String cv117;//more info of image element ex: url link
	@Field("FC920")
	private int fc920;
	@Field("FC850")
	private int fc850;
	@Field("CD215")
	private Date cd215;
	public C110MG() {
		// TODO Auto-generated constructor stub
	}
	public C110MG(String cv111, String cv112, String cv113, int cn114, String cv115, String cv116, String cv117,  int fc920, int fc850) {
		super();
		this.cv111 = cv111;
		this.cv112 = cv112;
		this.cv113 = cv113;
		this.cn114 = cn114;
		this.cv115 = cv115;
		this.cv116 = cv116;
		this.cv117 = cv117;
		this.fc920 = fc920;
		this.fc850 = fc850;
	}

	public String getCv111() {
		return cv111;
	}
	public void setCv111(String cv111) {
		this.cv111 = cv111;
	}
	public String getCv112() {
		return cv112;
	}
	public void setCv112(String cv112) {
		this.cv112 = cv112;
	}
	public int getFc920() {
		return fc920;
	}
	public void setFc920(int fc920) {
		this.fc920 = fc920;
	}
	public int getFc850() {
		return fc850;
	}
	public void setFc850(int fc850) {
		this.fc850 = fc850;
	}
	public String getCv113() {
		return cv113;
	}
	public void setCv113(String cv113) {
		this.cv113 = cv113;
	}
	public int getCn114() {
		return cn114;
	}
	public void setCn114(int cn114) {
		this.cn114 = cn114;
	}
	public String getCv115() {
		return cv115;
	}
	public void setCv115(String cv115) {
		this.cv115 = cv115;
	}
	public String getCv116() {
		return cv116;
	}
	public void setCv116(String cv116) {
		this.cv116 = cv116;
	}
	public Date getCd215() {
		return cd215;
	}
	public void setCd215(Date cd215) {
		this.cd215 = cd215;
	}
	public String getCv117() {
		return cv117;
	}
	public void setCv117(String cv117) {
		this.cv117 = cv117;
	}
	
}
