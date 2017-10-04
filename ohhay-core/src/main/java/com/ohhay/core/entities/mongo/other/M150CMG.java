package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 *
 */
@Document(collection = QbMongoCollectionsName.M150C)
public class M150CMG implements Serializable{
	@Id
	private int id;
	
	@Field("FO100")
	private int fo100;
	
	@Field("FM150")
	private int fm150; //id topic
	
	@Field("COMMENT")
	private String comment; //id topic
	
	@Field("MN165")
	private int mn165; //
	
	@Field("MN166")
	private int mn166; //
	
	@Field("MD176")
	private Date md176; //
	
	@Field("MD178")
	private Date md178; //

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

	public int getFm150() {
		return fm150;
	}

	public void setFm150(int fm150) {
		this.fm150 = fm150;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getMn165() {
		return mn165;
	}

	public void setMn165(int mn165) {
		this.mn165 = mn165;
	}

	public int getMn166() {
		return mn166;
	}

	public void setMn166(int mn166) {
		this.mn166 = mn166;
	}

	public Date getMd176() {
		return md176;
	}

	public void setMd176(Date md176) {
		this.md176 = md176;
	}

	public Date getMd178() {
		return md178;
	}

	public void setMd178(Date md178) {
		this.md178 = md178;
	}

}
