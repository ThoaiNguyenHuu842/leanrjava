package com.ohhay.web.lego.entities.mongo.web;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.lego.entities.mongo.base.component.Component;
/**
 * @author ThoaiNH
 * create Oct 13, 2015
 */
@Document(collection = QbMongoCollectionsName.E900)
public class E900MG extends Component implements Serializable{
	@Field("EV901")
	private String ev901;//title
	@Field("EV902")
	private String ev902;//thumbnail
	@Field("EV903")
	private String ev903;//type = "LIB"
	@Field("EL949")
	private Date el949;//date add to library
	@Field("FO100A")
	private int fo100A;//user add to lib
	@Field("LIB_SORT")
	private int libSort;//user add to lib
	public E900MG() {
		// TODO Auto-generated constructor stub
	}
	
	public String getEv901() {
		return ev901;
	}

	public void setEv901(String ev901) {
		this.ev901 = ev901;
	}

	public String getEv902() {
		return ev902;
	}

	public void setEv902(String ev902) {
		this.ev902 = ev902;
	}

	public String getEv903() {
		return ev903;
	}

	public void setEv903(String ev903) {
		this.ev903 = ev903;
	}

	public Date getEl949() {
		return el949;
	}

	public void setEl949(Date el949) {
		this.el949 = el949;
	}
	


	public int getFo100A() {
		return fo100A;
	}

	public void setFo100A(int fo100a) {
		fo100A = fo100a;
	}

	public int getLibSort() {
		return libSort;
	}

	public void setLibSort(int libSort) {
		this.libSort = libSort;
	}

	@Override
	public String toString() {
		return "E900MG [ev901=" + ev901 + ", ev902=" + ev902 + ", ev903=" + ev903 + ", el949=" + el949 + ", fo100A="
				+ fo100A + "]";
	}

	public E900MG(E900MG e){
		super(e);
	}
}
