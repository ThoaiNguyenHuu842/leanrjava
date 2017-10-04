package com.ohhay.piepme.mongo.entities.blockaccount;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create May 11, 2017
 */
@Document(collection = QbMongoCollectionsName.B100P)
public class B100PPMG extends B100PBaseMG {

	public B100PPMG(int id, int fo100, int fo100p, Date bd108) {
		super(id, fo100, fo100p, bd108);
		// TODO Auto-generated constructor stub
	}

	public B100PPMG() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
