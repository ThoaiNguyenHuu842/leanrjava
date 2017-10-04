package com.ohhay.web.lego.entities.mongo.topic;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.lego.entities.mongo.base.web.OhhayLegoWebBase;


/**
 * @author ThoaiNH
 * create Nov 19, 2015
 */
@Document(collection = QbMongoCollectionsName.ET400)
public class ET400MG extends OhhayLegoWebBase implements Serializable{
	@Field("FM150")
	private int fm150;

	public int getFm150() {
		return fm150;
	}

	public void setFm150(int fm150) {
		this.fm150 = fm150;
	}
	
}
