package com.ohhay.web.core.entities.mongo.weblanguage;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;

@Document(collection = QbMongoCollectionsName.W100)
public class W100MG extends OhhayWebLanguageBase implements Serializable{
	public W100MG() {
		// TODO Auto-generated constructor stub
	}
	public W100MG(W100MG c100mg) {
		super();
		this.languageID = c100mg.languageID;
		this.cv101 = c100mg.cv101;
		this.webID = c100mg.webID;
		this.liC110mgs = c100mg.liC110mgs;
		this.fo100 = c100mg.getFo100();
	}
}
