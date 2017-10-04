package com.ohhay.core.entities.mongo.other;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.mongo.profile.M960MG;

/**
 * @author ThoaiNH
 * create Apr 11, 2016
 */
@Document(collection = QbMongoCollectionsName.M200)
public class M200MG {
	@Id
	private int id;
	
	@Field("MV201")
	private String mv201;//description
	
	@Field("MV202")
	private String mv202;//cover
	
	@Field("MV203")
	private String mv203;//url
	
	@Field("MV204")
	private String mv204;
	
	@Field("FO100")
	private int fo100;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMv201() {
		return mv201;
	}

	public void setMv201(String mv201) {
		this.mv201 = mv201;
	}

	public String getMv202() {
		return mv202;
	}

	public void setMv202(String mv202) {
		this.mv202 = mv202;
	}

	public String getMv203() {
		return mv203;
	}

	public void setMv203(String mv203) {
		this.mv203 = mv203;
	}

	public int getFo100() {
		return fo100;
	}

	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}

	public String getMv204() {
		return mv204;
	}

	public void setMv204(String mv204) {
		this.mv204 = mv204;
	}

	@Override
	public String toString() {
		return "M200MG [id=" + id + ", mv201=" + mv201 + ", mv202=" + mv202
				+ ", mv203=" + mv203 + ", mv204="+mv204+", fo100=" + fo100 + "]";
	}
	
	
	
}
