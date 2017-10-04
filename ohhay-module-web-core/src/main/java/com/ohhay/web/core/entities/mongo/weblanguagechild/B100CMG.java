package com.ohhay.web.core.entities.mongo.weblanguagechild;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;

@Document(collection = QbMongoCollectionsName.B100C)
public class B100CMG extends OhhayWebLanguageBase implements Serializable{
	public B100CMG() {
		// TODO Auto-generated constructor stub
	}
	public B100CMG(B100CMG c100mg) {
		super();
		this.languageID = c100mg.languageID;
		this.cv101 = c100mg.cv101;
		this.webID = c100mg.webID;
		this.liC110mgs = c100mg.liC110mgs;
		this.fo100 = c100mg.getFo100();
	}
}
