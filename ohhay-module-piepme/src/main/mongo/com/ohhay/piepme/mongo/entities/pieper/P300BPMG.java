package com.ohhay.piepme.mongo.entities.pieper;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01Sum;

/**
 * MONGODB COLLECTION - Commercial PIEPER
 * @author ThoaiNH
 * create Mar 13, 2017
 * 
 */
@Document(collection = QbMongoCollectionsName.P300B)
public class P300BPMG extends Pieper{
	@Transient
	private int pn307;//pieper type 0: Normal, 1: Hot, 2: New, 3: Near You
	@Transient
	private R100PSta01Sum sta; //statistic info
	@Field("PRO_STT")
	private int promotionStt; //0 or 1
	@Field("FT300")
	private int ft300;
	@Field("REPORT_STT")
	private int reportStt; //trang thai co nguoi reach moi de dong bo qua mysql
	@Field("TOTAL_REACHS")
	private int totalReachs; //tong so nguoi reach pieper
	@Transient
	private double distance;
	public int getPn307() {
		return pn307;
	}

	public void setPn307(int pn307) {
		this.pn307 = pn307;
	}

	public R100PSta01Sum getSta() {
		return sta;
	}

	public void setSta(R100PSta01Sum sta) {
		this.sta = sta;
	}

	public int getPromotionStt() {
		return promotionStt;
	}

	public void setPromotionStt(int promotionStt) {
		this.promotionStt = promotionStt;
	}

	public int getFt300() {
		return ft300;
	}

	public void setFt300(int ft300) {
		this.ft300 = ft300;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getReportStt() {
		return reportStt;
	}

	public void setReportStt(int reportStt) {
		this.reportStt = reportStt;
	}

	public int getTotalReachs() {
		return totalReachs;
	}

	public void setTotalReachs(int totalReachs) {
		this.totalReachs = totalReachs;
	}
	
	
}
