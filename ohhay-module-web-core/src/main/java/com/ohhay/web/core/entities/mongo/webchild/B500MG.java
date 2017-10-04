package com.ohhay.web.core.entities.mongo.webchild;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebChildBase;
/*
 * webinaris website
 */
@Document(collection = QbMongoCollectionsName.B500)
public class B500MG extends OhhayWebChildBase implements Serializable{

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
