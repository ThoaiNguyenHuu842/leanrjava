package com.ohhay.web.core.entities.mongo.weblanguage;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;

/**
 * @author ThoaiNH
 * create Oct 29, 2014
 */
@Document(collection = QbMongoCollectionsName.L100)
public class L100MG extends OhhayWebLanguageBase implements Serializable{
	public L100MG() {
		// TODO Auto-generated constructor stub
	}
	public L100MG(L100MG l100mg) {
		super();
		this.languageID = l100mg.getLanguageID();
		this.cv101 = l100mg.getCv101();
		this.webID = l100mg.getWebID();
		this.fo100 = l100mg.getFo100();
		this.liC110mgs = l100mg.getLiC110mgs();
	}
	public L100MG(A100MG a100mg) {
		super();
		this.languageID = a100mg.getLanguageID();
		this.cv101 = a100mg.getCv101();
		this.webID = a100mg.getWebID();
		this.fo100 = a100mg.getFo100();
		this.liC110mgs = a100mg.getLiC110mgs();
	}
}
