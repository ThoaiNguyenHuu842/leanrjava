package com.ohhay.web.core.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
/*
 * webinaris website
 */
@Document(collection = QbMongoCollectionsName.W900)
public class W400MG extends OhhayWebBase implements Serializable{
	@Field("WV403")
	private String wv403;//key webinaris

	public String getWv403() {
		return wv403;
	}

	public void setWv403(String wv403) {
		this.wv403 = wv403;
	}

	@Override
	public String getFriendlyUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
