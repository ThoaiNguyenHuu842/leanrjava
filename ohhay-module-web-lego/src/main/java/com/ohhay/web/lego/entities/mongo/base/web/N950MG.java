package com.ohhay.web.lego.entities.mongo.base.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH create Jan 5, 2015 web SEO info
 */
public class N950MG implements Serializable {
	@Field("NV951")
	private String nv951;// title
	@Field("NV952")
	private String nv952;// description
	@Field("NV953")
	private String nv953;// feature image
	@Field("NV954")
	private String nv954;// fav icon
	@Field("NV955")
	private String nv955;// tracking script
	public N950MG() {
		// TODO Auto-generated constructor stub
	}
	public N950MG(N950MG n950mg){
		if(n950mg != null){
			this.nv951 = n950mg.getNv951();
			this.nv952 = n950mg.getNv952();
			this.nv953 = n950mg.getNv953();
			this.nv954 = n950mg.getNv954();
		}
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
	@Override
	public String toString() {
		return "N950MG [nv951=" + nv951 + ", nv952=" + nv952 + ", nv953="
				+ nv953 + ", nv954=" + nv954 + ", nv955=" + nv955 + "]";
	}
	
}
