package com.ohhay.web.core.history;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 29/10/2014
 * edited element info
 */
public class WebEditedInfo implements Serializable {
	private int type;//1: update c100, 2:update color, 3:update google map, 4: update element position, 5: update background repeat 
	private Object value;
	private int webId;
	private int extend;
	private int indexProperty;
	private String languageCode;
	public WebEditedInfo(int type, Object value, int webId, int extend,
			int indexProperty, String languageCode) {
		super();
		this.type = type;
		this.value = value;
		this.webId = webId;
		this.extend = extend;
		this.indexProperty = indexProperty;
		this.languageCode = languageCode;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getWebId() {
		return webId;
	}

	public void setWebId(int webId) {
		this.webId = webId;
	}

	public int getExtend() {
		return extend;
	}

	public void setExtend(int extend) {
		this.extend = extend;
	}

	public int getIndexProperty() {
		return indexProperty;
	}

	public void setIndexProperty(int indexProperty) {
		this.indexProperty = indexProperty;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "type:"+type+", value:"+value+", webId:"+webId
				+", extend:"+extend
				+", indexProperty:"+indexProperty
				+", languageCode:"+languageCode;
	}
}
