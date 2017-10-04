package com.ohhay.piepme.mongo.entities.blockpieper;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author TuNt
 * create date Jun 7, 2017
 * ohhay-module-piepme
 */
@Document(collection = QbMongoCollectionsName.B300C)
public class B300CPMG extends B300PBaseMG{
	public B300CPMG(){}
	public B300CPMG(int id, int fo100, int pieperId, int fo100p, Date bd308) {
		super(id, fo100, pieperId, fo100p, bd308);
		// TODO Auto-generated constructor stub
	}
}
