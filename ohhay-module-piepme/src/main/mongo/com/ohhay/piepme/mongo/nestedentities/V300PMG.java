package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * thong tin voucher tu tuc
 * @author ThoaiNH
 * create Jun 30, 2017
 */
public class V300PMG implements Serializable{
	public static String VV308_DOJ = "DOJ";
	public static String VV307_ACTIVE = "A";
	public static String VV307_INACTIVE = "I";
	public static String VV310_SENT = "SENT";
	@Field("VD303")
	private Date vd303;//tu ngay
	@Field("VD304")
	private Date vd304;//den ngay
	@Field("VV307")
	private String vv307;// I (Inactive) hoac A (Active)
	@Field("VN309")
	private int vn309;//duoc phep su dung
	@Field("FV300OR")
	private int fv300OR;
	
	/**   
	-- >> DOJ = Download and Join; 
    -- >> F1M = Follow trong thang
    -- >> F3M = Follow 3 thang
    -- >> F6M = Follow 6 thang
    -- >> F1Y = Follow 1 nam
    -- >> F0M = Follow tat ca
	 */
	@Field("VV308")
	private String vv308;//doi tuong mua hang
	@Field("VV310")
	private String vv310;//trang thai da gui voucher cho khach hang (="SENT")
	@Transient
	private String vd303Str;//tu ngay
	@Transient
	private String vd304Str;//den ngay
	
	public V300PMG() {
		super();
	}
	public V300PMG(Date vd303, Date vd304, int vn309, String vv308, int fv300OR) {
		super();
		this.vd303 = vd303;
		this.vd304 = vd304;
		this.vn309 = vn309;
		this.vv308 = vv308;
		this.fv300OR = fv300OR;
		this.vv307 = VV307_INACTIVE;
	}
	public Date getVd303() {
		return vd303;
	}
	public void setVd303(Date vd303) {
		this.vd303 = vd303;
	}
	public Date getVd304() {
		return vd304;
	}
	public void setVd304(Date vd304) {
		this.vd304 = vd304;
	}
	public int getVn309() {
		return vn309;
	}
	public void setVn309(int vn309) {
		this.vn309 = vn309;
	}
	public String getVv308() {
		return vv308;
	}
	public void setVv308(String vv308) {
		this.vv308 = vv308;
	}
	public int getFv300OR() {
		return fv300OR;
	}
	public void setFv300OR(int fv300or) {
		fv300OR = fv300or;
	}
	public String getVv307() {
		return vv307;
	}
	public void setVv307(String vv307) {
		this.vv307 = vv307;
	}
	public String getVd303Str() {
		return vd303Str;
	}
	public void setVd303Str(String vd303Str) {
		this.vd303Str = vd303Str;
	}
	public String getVd304Str() {
		return vd304Str;
	}
	public void setVd304Str(String vd304Str) {
		this.vd304Str = vd304Str;
	}
	public String getVv310() {
		return vv310;
	}
	public void setVv310(String vv310) {
		this.vv310 = vv310;
	}

}
