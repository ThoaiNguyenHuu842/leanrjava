package com.ohhay.core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Types;

import com.mysql.jdbc.Blob;
import com.ohhay.base.dao.QbMapping;

/**
 * @author Phongdt
 * date create 31/07/2015
 */
public class M300 implements Serializable{
	@QbMapping(name = "NV120", typeMapping = Types.CHAR)
	private String email;
	@QbMapping(name = "MV302", typeMapping = Types.CHAR)
	private String host;
	@QbMapping(name = "MV304", typeMapping = Types.CHAR)
	private  String style;
	@QbMapping(name = "MV303", typeMapping = Types.CHAR)
	private String port;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
