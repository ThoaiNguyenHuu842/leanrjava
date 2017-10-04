package com.ohhay.piepme.mongo.entities.blockaccount;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create May 11, 2017
 * user blocks an account
 */
public class B100PBaseMG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("FO100P")
	private int fo100P;//user is blocked
	@Field("BD108")
	protected Date bd108;//created date
	@Transient
	private N100SummaryInfo n100SummaryInfo;
	public B100PBaseMG(int id, int fo100, int fo100p, Date bd108) {
		super();
		this.id = id;
		this.fo100 = fo100;
		fo100P = fo100p;
		this.bd108 = bd108;
	}
	public B100PBaseMG() {
		super();
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
	public int getFo100P() {
		return fo100P;
	}
	public void setFo100P(int fo100p) {
		fo100P = fo100p;
	}
	public Date getBd108() {
		return bd108;
	}
	public void setBd108(Date bd108) {
		this.bd108 = bd108;
	}
	public N100SummaryInfo getN100SummaryInfo() {
		return n100SummaryInfo;
	}
	public void setN100SummaryInfo(N100SummaryInfo n100SummaryInfo) {
		this.n100SummaryInfo = n100SummaryInfo;
	}
	@Override
	public String toString() {
		return "B100PBaseMG [id=" + id + ", fo100=" + fo100 + ", fo100P=" + fo100P + ", bd108=" + bd108 + ", n100SummaryInfo=" + n100SummaryInfo + "]";
	}
	
}
