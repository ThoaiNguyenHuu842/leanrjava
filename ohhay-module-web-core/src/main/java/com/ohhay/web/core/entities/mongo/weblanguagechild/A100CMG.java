package com.ohhay.web.core.entities.mongo.weblanguagechild;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;

@Document(collection = QbMongoCollectionsName.A100C)
public class A100CMG extends OhhayWebLanguageBase implements Serializable{
	public A100CMG() {
		// TODO Auto-generated constructor stub
	}
	public A100CMG(C100CMG c100mg) {
		super();
		this.languageID = c100mg.getLanguageID();
		this.cv101 = c100mg.getCv101();
		this.webID = c100mg.getWebID();
		this.liC110mgs = c100mg.getLiC110mgs();
		this.fo100 = c100mg.getFo100();
	}
	public A100CMG(T100CMG t100cmg) {
		super();
		this.languageID = t100cmg.getLanguageID();
		this.cv101 = t100cmg.getCv101();
		this.webID = t100cmg.getWebID();
		this.liC110mgs = t100cmg.getLiC110mgs();
		this.fo100 = t100cmg.getFo100();
	}
}
