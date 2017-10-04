package com.ohhay.web.core.entities.mongo.weblanguage;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;

@Document(collection = QbMongoCollectionsName.C100)
public class C100MG extends OhhayWebLanguageBase implements Serializable{
	public C100MG() {
		// TODO Auto-generated constructor stub
	}
	public C100MG(T100MG t100mg) {
		super();
		this.languageID = t100mg.getLanguageID();
		this.cv101 = t100mg.getCv101();
		this.webID = t100mg.getWebID();
		this.fo100 = t100mg.getFo100();
		this.liC110mgs = t100mg.getLiC110mgs();
	}
	public C100MG(C100MG c100mg) {
		super();
		this.languageID = c100mg.languageID;
		this.cv101 = c100mg.cv101;
		this.webID = c100mg.webID;
		this.fo100 = c100mg.getFo100();
		this.liC110mgs = c100mg.liC110mgs;
	}
	public C100MG(A100MG a100mg) {
		super();
		this.languageID = a100mg.getLanguageID();
		this.cv101 = a100mg.getCv101();
		this.webID = a100mg.getWebID();
		this.fo100 = a100mg.getFo100();
		this.liC110mgs = a100mg.getLiC110mgs();
	}
	
}
