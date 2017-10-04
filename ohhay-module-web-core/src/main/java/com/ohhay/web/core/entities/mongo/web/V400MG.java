package com.ohhay.web.core.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
/*
 * video marketing website
 */
@Document(collection = QbMongoCollectionsName.V900)
public class V400MG extends OhhayWebBase implements Serializable{

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
