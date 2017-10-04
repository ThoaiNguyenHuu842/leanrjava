package com.ohhay.other.entities.mongo.showtime;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
/*
 * lich  hoc truc tuyen cua user
 */
@Document(collection = QbMongoCollectionsName.W150)
public class W150MG implements Serializable{
	@Id
	private long id;
	@Field("HV101")
	private String hv101;//md5 phone
	@Field("PART")
	private List<W160MG> listW160mgs;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHv101() {
		return hv101;
	}
	public void setHv101(String hv101) {
		this.hv101 = hv101;
	}
	public List<W160MG> getListW160mgs() {
		return listW160mgs;
	}
	public void setListW160mgs(List<W160MG> listW160mgs) {
		this.listW160mgs = listW160mgs;
	}
	
	
}
