package com.ohhay.piepme.mongo.entities.reportpieper;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author TuNt
 * create date Jun 7, 2017
 * ohhay-module-piepme
 */
@Document(collection = QbMongoCollectionsName.B200C)
public class B200CPMG extends B200PBaseMG implements Serializable{
	public B200CPMG(){}
	public B200CPMG(int id, int fo100, int pieperId, int fo100p, Date bd208) {
		super(id, fo100, pieperId, fo100p, bd208);
		// TODO Auto-generated constructor stub
	}
}
