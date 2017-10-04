package com.ohhay.piepme.mongo.entities.reportpieper;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create May 11, 2017
 * user report public PIEPER
 */
@Document(collection = QbMongoCollectionsName.B200P)
public class B200PPMG extends B200PBaseMG implements Serializable{
	public B200PPMG(){}
	public B200PPMG(int id, int fo100, int pieperId, int fo100p, Date bd208) {
		super(id, fo100, pieperId, fo100p, bd208);
		// TODO Auto-generated constructor stub
	}
	
}
