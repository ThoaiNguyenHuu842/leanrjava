package com.ohhay.web.core.entities.mongo.webbase;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Jan 5, 2015
 * web SEO info
 */
public class N950MG implements Serializable {
	@Field("NV951")
	private String nv951;//so nha, duong
	@Field("NV952")
	private String nv952;//phuong
	@Field("NV953")
	private String nv953;//quan
	@Field("NV954")
	private String nv954;//zip code
	@Field("NV955")
	private String nv955;//quoc gia
	@Field("NV956")
	private String nv956;//sdt
	@Field("NV957")
	private String nv957;//email
	@Field("NV958")
	private String nv958;//dia chi full
	@Field("NN960")
	private double nn960;//la
	@Field("NN961")
	private double nn961;//log
	@Field("NV962")
	private String nv962;//web icon
	@Field("NV963")
	private String nv963;//web title
	@Field("NV964")
	private String nv964;//web description
	@Field("NV965")
	private String nv965;//web thumbnail
	@Field("NV966")
	private String nv966;//friendly url
	public N950MG() {
		// TODO Auto-generated constructor stub
		this.nv951 = "";
		this.nv952 = "";
		this.nv953 ="";
		this.nv954 = "";
		this.nv955 = "";
		this.nv956 = "";
		this.nv957 = "";
		this.nv958 = "";
		this.nn960 = 0;
		this.nn961 = 0;
		this.nv962 = "";
		this.nv963 = "";
		this.nv964 = "";
		this.nv965 = "";
	}
	
	public N950MG(N950MG n950mg) {
		super();
		this.nv951 = n950mg.getNv951();
		this.nv952 = n950mg.getNv952();
		this.nv953 = n950mg.getNv953();
		this.nv954 = n950mg.getNv954();
		this.nv955 = n950mg.getNv955();
		this.nv956 = n950mg.getNv956();
		this.nv957 = n950mg.getNv957();
		this.nv958 = n950mg.getNv958();
		this.nn960 = n950mg.getNn960();
		this.nn961 = n950mg.getNn961();
		this.nv962 = n950mg.getNv962();
		this.nv963 = n950mg.getNv963();
		this.nv964 = n950mg.getNv964();
		this.nv965 = n950mg.getNv965();
	}

	public String getNv951() {
		return nv951;
	}

	public void setNv951(String nv951) {
		this.nv951 = nv951;
	}

	public String getNv952() {
		return nv952;
	}

	public void setNv952(String nv952) {
		this.nv952 = nv952;
	}

	public String getNv953() {
		return nv953;
	}

	public void setNv953(String nv953) {
		this.nv953 = nv953;
	}

	public String getNv954() {
		return nv954;
	}

	public void setNv954(String nv954) {
		this.nv954 = nv954;
	}

	public String getNv955() {
		return nv955;
	}

	public void setNv955(String nv955) {
		this.nv955 = nv955;
	}

	public String getNv956() {
		return nv956;
	}

	public void setNv956(String nv956) {
		this.nv956 = nv956;
	}

	public String getNv957() {
		return nv957;
	}

	public void setNv957(String nv957) {
		this.nv957 = nv957;
	}

	public String getNv958() {
		return nv958;
	}

	public void setNv958(String nv958) {
		this.nv958 = nv958;
	}
	public double getNn960() {
		return nn960;
	}

	public void setNn960(double nn960) {
		this.nn960 = nn960;
	}

	public double getNn961() {
		return nn961;
	}

	public void setNn961(double nn961) {
		this.nn961 = nn961;
	}

	public String getNv962() {
		return nv962;
	}

	public void setNv962(String nv962) {
		this.nv962 = nv962;
	}

	public String getNv963() {
		return nv963;
	}

	public void setNv963(String nv963) {
		this.nv963 = nv963;
	}

	public String getNv964() {
		return nv964;
	}

	public void setNv964(String nv964) {
		this.nv964 = nv964;
	}

	public String getNv965() {
		return nv965;
	}

	public void setNv965(String nv965) {
		this.nv965 = nv965;
	}

	public String getNv966() {
		return nv966;
	}

	public void setNv966(String nv966) {
		this.nv966 = nv966;
	}
	
}
