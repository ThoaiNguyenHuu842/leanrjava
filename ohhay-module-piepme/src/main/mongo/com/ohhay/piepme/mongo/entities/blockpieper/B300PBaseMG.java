package com.ohhay.piepme.mongo.entities.blockpieper;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create May 11, 2017
 * user blocks a pieper
 */
public class B300PBaseMG implements Serializable{

	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("PIEPER_ID")
	private int pieperId;
	@Field("FO100P")
	private int fo100P;//pieper owner
	@Field("BD308")
	protected Date bd308;//created date
	public B300PBaseMG(){}
	public B300PBaseMG(int id, int fo100, int pieperId, int fo100p, Date bd308) {
		super();
		this.id = id;
		this.fo100 = fo100;
		this.pieperId = pieperId;
		fo100P = fo100p;
		this.bd308 = bd308;
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
	public Date getBd308() {
		return bd308;
	}
	public void setBd308(Date bd308) {
		this.bd308 = bd308;
	}
}
