package com.ohhay.piepme.mongo.entities.interaction;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * MONGODB COLLECTION - piepme group
 * @author ThoaiNH
 * create Aug 9, 2016
 * PiepMe group
 */
@Document(collection = QbMongoCollectionsName.G100)
public class G100PMG implements Serializable{
	public static String TYPE_COM = "COM";
	@Id
	private int id;
	@Field("FO100")
	private int fo100;//user create
	@Field("FO100R")
	private List<Integer> listFO100R;//friends in group
	@Field("GV101")
	private String gv101;//group name
	@Field("GV102")
	private String gv102;//group icon
	@Field("TYPE")
	private String type;//type of group ("COM" = group khach hang than thiet)
	@Field("GD168")
	private Date gd168;
	@Field("GD166")
	private Date gd166;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public List<Integer> getListFO100R() {
		return listFO100R;
	}
	public void setListFO100R(List<Integer> listFO100R) {
		this.listFO100R = listFO100R;
	}
	public Date getGd168() {
		return gd168;
	}
	public void setGd168(Date gd168) {
		this.gd168 = gd168;
	}
	public Date getGd166() {
		return gd166;
	}
	public void setGd166(Date gd166) {
		this.gd166 = gd166;
	}
	public String getGv101() {
		return gv101;
	}
	public void setGv101(String gv101) {
		this.gv101 = gv101;
	}
	public String getGv102() {
		return gv102;
	}
	public void setGv102(String gv102) {
		this.gv102 = gv102;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "G100PMG [id=" + id + ", fo100=" + fo100 + ", listFO100R=" + listFO100R + ", gv101=" + gv101 + ", gv102=" + gv102 + ", type=" + type + ", gd168="
				+ gd168 + ", gd166=" + gd166 + "]";
	}
	
}
