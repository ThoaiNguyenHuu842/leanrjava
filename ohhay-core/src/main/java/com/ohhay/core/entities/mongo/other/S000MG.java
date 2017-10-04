package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiVt
 * date 26/10/2016
 */
@Document(collection = QbMongoCollectionsName.S000)
public class S000MG implements Serializable{
	/**
	 * @author ThoaiVt
	 * date 26/10/2016
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Field("SN001")
	private int sn001;//on-off logger

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSn001() {
		return sn001;
	}

	public void setSn001(int sn001) {
		this.sn001 = sn001;
	}
	
	
}
