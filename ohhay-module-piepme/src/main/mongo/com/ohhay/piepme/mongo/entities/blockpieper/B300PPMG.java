package com.ohhay.piepme.mongo.entities.blockpieper;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create May 11, 2017
 */
@Document(collection = QbMongoCollectionsName.B300P)
public class B300PPMG extends B300PBaseMG{
	public B300PPMG(){}
	public B300PPMG(int id, int fo100, int pieperId, int fo100p, Date bd308) {
		super(id, fo100, pieperId, fo100p, bd308);
		// TODO Auto-generated constructor stub
	}

}
