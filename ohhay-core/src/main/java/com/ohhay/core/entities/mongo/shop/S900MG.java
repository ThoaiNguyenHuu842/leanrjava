package com.ohhay.core.entities.mongo.shop;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;

/**
 * @author ThoaiNH
 * create Apr 25, 2016
 * shop info
 */
@Document(collection = QbMongoCollectionsName.S900)
public class S900MG implements Serializable{
	@Id 
	private long id;
	@Field("FO100")
	private int fo100;
	@Field("SV901")
	private String sv901;//shop name	
	@Field("SV902")
	private String sv902;//street address
	@Field("SV903")
	private String sv903;//city
	@Field("SV904")
	private String sv904;//phone number
	@Field("SV905")
	private String sv905;//country
	@Field("SV906")
	private String sv906;//zip code
	@Field("SV907")
	private String sv907;//background url
	@Field("SV908")
	private String sv908;//shop introductory
	@Field("LOC")
	private GeoDataPointMG location;//shop location
	@Field("SL946")
	private Date sl946;
	@Field("SL948")
	private Date sl948;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getSv901() {
		return sv901;
	}
	public void setSv901(String sv901) {
		this.sv901 = sv901;
	}
	public String getSv902() {
		return sv902;
	}
	public void setSv902(String sv902) {
		this.sv902 = sv902;
	}
	public String getSv903() {
		return sv903;
	}
	public void setSv903(String sv903) {
		this.sv903 = sv903;
	}
	public String getSv904() {
		return sv904;
	}
	public void setSv904(String sv904) {
		this.sv904 = sv904;
	}
	public String getSv905() {
		return sv905;
	}
	public void setSv905(String sv905) {
		this.sv905 = sv905;
	}
	public String getSv906() {
		return sv906;
	}
	public void setSv906(String sv906) {
		this.sv906 = sv906;
	}
	public String getSv907() {
		return sv907;
	}
	public void setSv907(String sv907) {
		this.sv907 = sv907;
	}
	public String getSv908() {
		return sv908;
	}
	public void setSv908(String sv908) {
		this.sv908 = sv908;
	}
	public Date getSl946() {
		return sl946;
	}
	public void setSl946(Date sl946) {
		this.sl946 = sl946;
	}
	public Date getSl948() {
		return sl948;
	}
	public void setSl948(Date sl948) {
		this.sl948 = sl948;
	}
	public GeoDataPointMG getLocation() {
		return location;
	}
	public void setLocation(GeoDataPointMG location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "S900MG [id=" + id + ", fo100=" + fo100 + ", sv901=" + sv901
				+ ", sv902=" + sv902 + ", sv903=" + sv903 + ", sv904=" + sv904
				+ ", sv905=" + sv905 + ", sv906=" + sv906 + ", sv907=" + sv907
				+ ", sv908=" + sv908 + ", location=" + location + ", sl946="
				+ sl946 + ", sl948=" + sl948 + "]";
	}
	
}
