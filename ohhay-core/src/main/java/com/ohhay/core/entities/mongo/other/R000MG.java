package com.ohhay.core.entities.mongo.other;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * @author ThoaiNH
 * create Apr 22, 2016
 */
@Document(collection = QbMongoCollectionsName.R000)
public class R000MG {
	@Field("RV001")
	private String rv001;//type of log
	@Field("RV002")
	private String rv002;//log message
	@Field("RD008")
	private Date rd008;//date create
	public String getRv001() {
		return rv001;
	}
	public void setRv001(String rv001) {
		this.rv001 = rv001;
	}
	public String getRv002() {
		return rv002;
	}
	public void setRv002(String rv002) {
		this.rv002 = rv002;
	}
	public Date getRd008() {
		return rd008;
	}
	public void setRd008(Date rd008) {
		this.rd008 = rd008;
	}
	@Override
	public String toString() {
		return "R000MG [rv001=" + rv001 + ", rv002=" + rv002 + ", rd008="
				+ rd008 + "]";
	}
}
