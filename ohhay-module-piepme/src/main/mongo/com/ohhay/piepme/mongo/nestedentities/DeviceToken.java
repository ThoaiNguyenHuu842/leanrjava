package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

/**
 * @author ThoaiNH
 * create Apr 8, 2017
 * token info send to info center
 */
public class DeviceToken implements Serializable{
	private String token;
	private String device;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	@Override
	public String toString() {
		return "DeviceToken [token=" + token + ", device=" + device + "]";
	}
	
}
