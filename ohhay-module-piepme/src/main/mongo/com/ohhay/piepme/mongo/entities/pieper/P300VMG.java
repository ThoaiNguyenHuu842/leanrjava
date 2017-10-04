package com.ohhay.piepme.mongo.entities.pieper;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.nestedentities.V300PMG;

/**
 * MONGODB COLLECTION - VOUCHER OWNER INFO
 * @author ThoaiNH
 * create Jun 30, 2017
 * 
 */
@Document(collection = QbMongoCollectionsName.P300V)
public class P300VMG extends P300MMG{
	@Field("V300")
	private V300PMG v300;
	@Transient
	private int totalUsed;
	public V300PMG getV300() {
		return v300;
	}

	public void setV300(V300PMG v300) {
		this.v300 = v300;
	}

	public int getTotalUsed() {
		return totalUsed;
	}

	public void setTotalUsed(int totalUsed) {
		this.totalUsed = totalUsed;
	}
}
