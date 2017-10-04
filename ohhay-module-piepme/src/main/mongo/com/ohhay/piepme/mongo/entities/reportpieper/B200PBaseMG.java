package com.ohhay.piepme.mongo.entities.reportpieper;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create May 11, 2017
 * user reportss a PIEPER
 */
public class B200PBaseMG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("PIEPER_ID")
	private int pieperId;
	@Field("FO100P")
	private int fo100P;//pieper owner
	@Field("BD208")
	protected Date bd208;//created date
	@Transient
	private N100SummaryInfo n100SummaryInfo;
	public B200PBaseMG(){}
	public B200PBaseMG(int id, int fo100, int pieperId, int fo100p, Date bd208) {
		super();
		this.id = id;
		this.fo100 = fo100;
		this.pieperId = pieperId;
		fo100P = fo100p;
		this.bd208 = bd208;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getPieperId() {
		return pieperId;
	}
	public void setPieperId(int pieperId) {
		this.pieperId = pieperId;
	}
	public int getFo100P() {
		return fo100P;
	}
	public void setFo100P(int fo100p) {
		fo100P = fo100p;
	}
	public Date getBd208() {
		return bd208;
	}
	public void setBd208(Date bd208) {
		this.bd208 = bd208;
	}
	public N100SummaryInfo getN100SummaryInfo() {
		return n100SummaryInfo;
	}
	public void setN100SummaryInfo(N100SummaryInfo n100SummaryInfo) {
		this.n100SummaryInfo = n100SummaryInfo;
	}
}
