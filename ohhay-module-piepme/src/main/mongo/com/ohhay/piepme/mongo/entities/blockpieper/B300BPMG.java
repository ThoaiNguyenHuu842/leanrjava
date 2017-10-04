package com.ohhay.piepme.mongo.entities.blockpieper;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
/**
 * @author ThoaiNH
 * create May 11, 2017
 */
@Document(collection = QbMongoCollectionsName.B300B)
public class B300BPMG extends B300PBaseMG{
	public B300BPMG(){}
	public B300BPMG(int id, int fo100, int pieperId, int fo100p, Date bd308) {
		super(id, fo100, pieperId, fo100p, bd308);
		// TODO Auto-generated constructor stub
	}

}
