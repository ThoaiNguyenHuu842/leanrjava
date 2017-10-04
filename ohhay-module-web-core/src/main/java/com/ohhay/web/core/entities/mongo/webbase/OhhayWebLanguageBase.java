package com.ohhay.web.core.entities.mongo.webbase;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class OhhayWebLanguageBase {
	@Field("LANGUAGEID")
	protected String languageID;
	@Field("CV101")
	protected String cv101;
	@Field("WEBID")
	protected int webID;
	@Field("FO100")
	protected int fo100;
	@Field("PROPERTIES")
	protected List<C110MG> liC110mgs;
	public String getLanguageID() {
		return languageID;
	}
	public void setLanguageID(String languageID) {
		this.languageID = languageID;
	}
	public String getCv101() {
		return cv101;
	}
	public void setCv101(String cv101) {
		this.cv101 = cv101;
	}
	public int getWebID() {
		return webID;
	}
	public void setWebID(int webID) {
		this.webID = webID;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public List<C110MG> getLiC110mgs() {
		return liC110mgs;
	}
	public void setLiC110mgs(List<C110MG> liC110mgs) {
		this.liC110mgs = liC110mgs;
	}
	@Override
	public String toString() {
		return "C100CMG [languageID=" + languageID + ", cv101=" + cv101
				+ ", webID=" + webID + ", fo100=" + fo100 + ", liC110mgs="
				+ liC110mgs + ", getLanguageID()=" + getLanguageID()
				+ ", getCv101()=" + getCv101() + ", getWebID()=" + getWebID()
				+ ", getFo100()=" + getFo100() + ", getLiC110mgs()="
				+ getLiC110mgs() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
