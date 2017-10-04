package com.ohhay.piepme.mongo.entities.pieper;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author : ThoaiVt
 * @created : 12-07-2017
 */
@Document(collection = QbMongoCollectionsName.N100)
public class N100CAFMG {
	@Field("NV101")
	private String nv101;
	@Field("NV106")
	private String nv106;
	@Field("NV107")
	private String nv107;
	@Field("FO100")
	private int fo100;
	@Transient
	private String piepmeId;
	public String getNv101() {
		return nv101;
	}
	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}
	public String getNv106() {
		return nv106;
	}
	public void setNv106(String nv106) {
		this.nv106 = nv106;
	}
	public String getNv107() {
		return nv107;
	}
	public void setNv107(String nv107) {
		this.nv107 = nv107;
	}
	
	public String getPiepmeId() {
		return piepmeId;
	}
	public void setPiepmeId(String piepmeId) {
		this.piepmeId = piepmeId;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	@Override
	public String toString() {
		return "N100CafMG [nv101=" + nv101 + ", nv106=" + nv106 + ", nv107=" + nv107 + ", fo100=" + fo100
				+ ", piepmeId=" + piepmeId + "]";
	}
	
	
}
