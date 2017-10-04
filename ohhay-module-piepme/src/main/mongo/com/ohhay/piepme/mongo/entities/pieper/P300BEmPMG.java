package com.ohhay.piepme.mongo.entities.pieper;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author TuNt
 * create date Jun 28, 2017
 * ohhay-module-piepme
 */
@Document(collection = QbMongoCollectionsName.P300B)
public class P300BEmPMG extends P300BPMG{
	@Transient
	private String tv311;
	@Transient
	private String tv321;
	@Transient
	private String tv333;
	@Transient
	protected String[] otagAd;

	public String[] getOtagAd() {
		return otagAd;
	}

	public void setOtagAd(String[] otagAd) {
		this.otagAd = otagAd;
	}

	public String getTv311() {
		return tv311;
	}

	public void setTv311(String tv311) {
		this.tv311 = tv311;
	}

	public String getTv321() {
		return tv321;
	}

	public void setTv321(String tv321) {
		this.tv321 = tv321;
	}

	public String getTv333() {
		return tv333;
	}

	public void setTv333(String tv333) {
		this.tv333 = tv333;
	}
	
}
