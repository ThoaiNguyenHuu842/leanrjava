package com.ohhay.base.wsclient;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create 12/11/2015
 * parameter pass to webservice
 */
public class QbWsClientParam implements Serializable{
	private String key;
	private Object value;
	
	public QbWsClientParam(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
