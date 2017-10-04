package com.ohhay.piepme.mongo.entities.blockaccount;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * DN phan quyen cho CA
 * @author ThoaiNH
 * create Aug 29, 2017
 */
@Document(collection = "Q175")
public class Q175PMG implements Serializable{
	@Id
	private int id;
	@Field("FO100")
	private int fo100;//DN
	@Field("FO100F")
	private int fo100f;//CA
	@Field("MAP_DECENTRAL")
	private Map<String, Integer> mapDecentral;
	@Field("QL176")
	private Date ql176;//update date
	@Field("QL178")
	private Date ql178;//create date
	public Q175PMG(){}
	public Q175PMG(int id, int fo100, int fo100f,
			Map<String, Integer> mapDecentral, Date ql176, Date ql178) {
		super();
		this.id = id;
		this.fo100 = fo100;
		this.fo100f = fo100f;
		this.mapDecentral = mapDecentral;
		this.ql176 = ql176;
		this.ql178 = ql178;
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
	public int getFo100f() {
		return fo100f;
	}
	public void setFo100f(int fo100f) {
		this.fo100f = fo100f;
	}
	public Map<String, Integer> getMapDecentral() {
		return mapDecentral;
	}
	public void setMapDecentral(Map<String, Integer> mapDecentral) {
		this.mapDecentral = mapDecentral;
	}
	public Date getQl176() {
		return ql176;
	}
	public void setQl176(Date ql176) {
		this.ql176 = ql176;
	}
	public Date getQl178() {
		return ql178;
	}
	public void setQl178(Date ql178) {
		this.ql178 = ql178;
	}
	
}
