package com.ohhay.web.core.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
/*
 * bsell website
 */
@Document(collection = QbMongoCollectionsName.B900)
public class B400MG extends OhhayWebBase implements Serializable{
	@Field("BV403")
	private String bv403;//key bsell
	public String getBv403() {
		return bv403;
	}
	public void setBv403(String bv403) {
		this.bv403 = bv403;
	}
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
