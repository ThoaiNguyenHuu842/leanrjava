package com.ohhay.web.core.entities.mongo.webbase;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * date created 24/06/2015
 * update: 11/08/2015 add cv204 for background repeat element
 */
public class OhhayWebShortBase {
	@Field("REFID")
	protected String refid;//md5(webid + languageCode)
	@Field("WEBID")
	protected int webId;
	@Field("LANGUAGE_CODE")
	protected String languageCode;
	@Field("FO100")
	protected int fo100;
	@Field("FC800")
	protected int fc800;
	@Field("CV201")
	protected String cv201;//header
	@Field("CV202")
	protected String cv202;//body
	@Field("CV203")
	protected String cv203;//color
	@Field("CV204")
	protected String cv204;//background repeat
	@Field("VISIBLE")
	protected int visible;//an: -1, hien: 0
	@Field("JS_LIBS")
	protected Set<OhhayLibraryJS> setOhhayLibraryJS;
	@Field("IS_NEW")
	protected int isNew;//1: chua publish vao web short, 0: da publish
	@Field("INFO")
	protected N950MG n950mg;
	public String getRefid() {
		return refid;
	}
	public void setRefid(String refid) {
		this.refid = refid;
	}
	public int getWebId() {
		return webId;
	}
	public void setWebId(int webId) {
		this.webId = webId;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getCv201() {
		return cv201;
	}
	public void setCv201(String cv201) {
		this.cv201 = cv201;
	}
	public String getCv202() {
		return cv202;
	}
	public void setCv202(String cv202) {
		this.cv202 = cv202;
	}
	public String getCv203() {
		return cv203;
	}
	public void setCv203(String cv203) {
		this.cv203 = cv203;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public Set<OhhayLibraryJS> getSetOhhayLibraryJS() {
		return setOhhayLibraryJS;
	}
	public void setSetOhhayLibraryJS(Set<OhhayLibraryJS> setOhhayLibraryJS) {
		this.setOhhayLibraryJS = setOhhayLibraryJS;
	}
	public int getFc800() {
		return fc800;
	}
	public void setFc800(int fc800) {
		this.fc800 = fc800;
	}
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public N950MG getN950mg() {
		return n950mg;
	}
	public void setN950mg(N950MG n950mg) {
		this.n950mg = n950mg;
	}
	public String getCv204() {
		return cv204;
	}
	public void setCv204(String cv204) {
		this.cv204 = cv204;
	}
	
}
