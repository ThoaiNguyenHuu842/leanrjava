package com.ohhay.web.core.entities.mongo.weblanguagechild;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;

@Document(collection = QbMongoCollectionsName.C100C)
public class C100CMG extends OhhayWebLanguageBase implements Serializable{
	public C100CMG() {
		// TODO Auto-generated constructor stub
	}
	public C100CMG(C100CMG c100mg) {
		super();
		this.languageID = c100mg.languageID;
		this.cv101 = c100mg.cv101;
		this.webID = c100mg.webID;
		this.liC110mgs = c100mg.liC110mgs;
		this.fo100 = c100mg.getFo100();
	}
	public C100CMG(T100CMG t100cmg) {
		super();
		this.languageID = t100cmg.getLanguageID();
		this.cv101 = t100cmg.getCv101();
		this.webID = t100cmg.getWebID();
		this.liC110mgs = t100cmg.getLiC110mgs();
		this.fo100 = t100cmg.getFo100();
	}
	public C100CMG(A100CMG a100Cmg) {
		super();
		this.languageID = a100Cmg.getLanguageID();
		this.cv101 = a100Cmg.getCv101();
		this.webID = a100Cmg.getWebID();
		this.liC110mgs = a100Cmg.getLiC110mgs();
		this.fo100 = a100Cmg.getFo100();
	}
}
