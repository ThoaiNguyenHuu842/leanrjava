package com.ohhay.piepme.mongo.entities.pieper;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.nestedentities.N100Confirm;
import com.ohhay.piepme.mongo.nestedentities.R100PSta01Sum;

/**
 * MONGODB COLLECTION - PIEPER public
 * @author ThoaiNH
 * create Sep 19, 2016
 * 
 */
@Document(collection = QbMongoCollectionsName.P300P)
public class P300PPMG extends Pieper{
	@Transient
	private int pn307;//pieper type 0: Normal, 1: Hot, 2: New, 3: Near You
	@Transient
	private R100PSta01Sum sta; //statistic info
	@Field("STT")
	private String stt; //not exists, "CON" or "REJ"
	/*
	 * (trang thai user login da danh gia pieper hay chua): 
		- null( user khong duoc quyen danh gia)
		- REJ (user da tu choi)
		- CON(user da xac nhan)
		- NOT_REACT(user chua danh gia)
	 */
	private String userStt;	//not exists, "CON" or "REJ"
	@Field("PA317")
	private List<N100Confirm> pa317; 
	@Field("TOTAL_REPORTS")
	private int totalReports;
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

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getUserStt() {
		return userStt;
	}

	public void setUserStt(String userStt) {
		this.userStt = userStt;
	}

	public List<N100Confirm> getPa317() {
		return pa317;
	}

	public void setPa317(List<N100Confirm> pa317) {
		this.pa317 = pa317;
	}

	public int getTotalReports() {
		return totalReports;
	}

	public void setTotalReports(int totalReports) {
		this.totalReports = totalReports;
	}
}
