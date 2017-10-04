package com.ohhay.web.core.entities.mongo.weblanguage;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;
@Document(collection = QbMongoCollectionsName.A100)
public class A100MG extends OhhayWebLanguageBase implements Serializable{
	public A100MG() {
		// TODO Auto-generated constructor stub
	}
	public A100MG(L100MG c100mg) {
		super();
		this.languageID = c100mg.getLanguageID();
		this.cv101 = c100mg.getCv101();
		this.webID = c100mg.getWebID();
		this.fo100 = c100mg.getFo100();
		this.liC110mgs = c100mg.getLiC110mgs();
	}
}
