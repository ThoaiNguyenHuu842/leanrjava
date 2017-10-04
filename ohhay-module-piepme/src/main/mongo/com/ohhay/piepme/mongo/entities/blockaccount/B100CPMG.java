package com.ohhay.piepme.mongo.entities.blockaccount;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author TuNt
 * create date Jun 7, 2017
 * ohhay-module-piepme
 */
@Document(collection = QbMongoCollectionsName.B100C)
public class B100CPMG extends B100PBaseMG {
	public B100CPMG(int id, int fo100, int fo100p, Date bd108) {
		super(id, fo100, fo100p, bd108);
		// TODO Auto-generated constructor stub
	}

	public B100CPMG() {
		super();
	}
}
