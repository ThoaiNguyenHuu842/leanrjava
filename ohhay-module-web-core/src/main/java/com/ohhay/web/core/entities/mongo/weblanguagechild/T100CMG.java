package com.ohhay.web.core.entities.mongo.weblanguagechild;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;

@Document(collection = QbMongoCollectionsName.T100C)
public class T100CMG extends OhhayWebLanguageBase implements Serializable{
	public T100CMG() {
		// TODO Auto-generated constructor stub
	}
	public T100CMG(T100CMG t100mg) {
		super();
		this.languageID = t100mg.languageID;
		this.cv101 = t100mg.cv101;
		this.webID = t100mg.webID;
		this.liC110mgs = t100mg.liC110mgs;
		this.fo100 = t100mg.getFo100();
	}
	public T100CMG(C100CMG c100mg) {
		super();
		this.languageID = c100mg.getLanguageID();
		this.cv101 = c100mg.getCv101();
		this.webID = c100mg.getWebID();
		this.liC110mgs = c100mg.getLiC110mgs();
		this.fo100 = c100mg.getFo100();
	}
	public T100CMG(A100CMG a100Cmg) {
		super();
		this.languageID = a100Cmg.getLanguageID();
		this.cv101 = a100Cmg.getCv101();
		this.webID = a100Cmg.getWebID();
		this.liC110mgs = a100Cmg.getLiC110mgs();
		this.fo100 = a100Cmg.getFo100();
	}
	@Override
	public String toString() {
		return "T100CMG [languageID=" + languageID + ", cv101=" + cv101
				+ ", webID=" + webID + ", fo100=" + fo100 + ", liC110mgs="
				+ liC110mgs + ", getLanguageID()=" + getLanguageID()
				+ ", getCv101()=" + getCv101() + ", getWebID()=" + getWebID()
				+ ", getFo100()=" + getFo100() + ", getLiC110mgs()="
				+ getLiC110mgs() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
