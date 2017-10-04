package com.ohhay.web.core.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
/**
 * @author ThongQB
 * date create: 20/07/2015
 * update - 23/07/2015 - CRAZY-HORSE: add an105
 * category web template
 */
@SuppressWarnings("serial")
@Document(collection = QbMongoCollectionsName.A950)
public class A950MG implements Serializable, Comparable<A950MG>{
	@Id
	private int id;
	
	@Field("AV101")
	private String av101;//category name language en
	
	@Field("AN102")
	private int an102;
	
	@Field("AV103")
	private String av103;//category name language vi
	
	@Field("AV104")
	private String av104;//category name language de
	
	@Field("AN105")
	private int an105; //total template
	public A950MG() {
		// TODO Auto-generated constructor stub
	}
	
	public A950MG(A950MG a950) {
		this.id = a950.getId();
		this.av101 = a950.getAv101();
		this.an102 = a950.getAn102();
		this.av103 = a950.getAv103();
		this.av104 = a950.getAv104();
		this.an105 = a950.getAn105();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAv101() {
		return av101;
	}

	public void setAv101(String av101) {
		this.av101 = av101;
	}

	public int getAn102() {
		return an102;
	}

	public void setAn102(int an102) {
		this.an102 = an102;
	}

	public String getAv103() {
		return av103;
	}

	public void setAv103(String av103) {
		this.av103 = av103;
	}

	public String getAv104() {
		return av104;
	}

	public void setAv104(String av104) {
		this.av104 = av104;
	}
	
	public int getAn105() {
		return an105;
	}

	public void setAn105(int an105) {
		this.an105 = an105;
	}

	public String getLabel() {
		if(MVCResourceBundleUtil.getCurrentLocale().getLanguage().equals("vi"))
			return av103;
		else if(MVCResourceBundleUtil.getCurrentLocale().getLanguage().equals("en"))
			return av101;
		else
			return av104;
	}
	
	@Override
	public String toString() {
		return "A950MG [id=" + id + ", av101=" + av101 + ", an102=" + an102
				+ ", av103=" + av103 + ", av104=" + av104 + ", an105=" + an105
				+ "]";
	}

	@Override
	public int compareTo(A950MG arg0) {
		// TODO Auto-generated method stub
		if(this.an105 > arg0.an105)
			return -1;
		else if(this.an105 < arg0.an105)
			return 1;
		else
			return 0;
	}
}
