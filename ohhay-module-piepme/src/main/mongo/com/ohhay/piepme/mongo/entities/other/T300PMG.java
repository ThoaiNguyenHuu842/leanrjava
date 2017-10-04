package com.ohhay.piepme.mongo.entities.other;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.piepme.mongo.nestedentities.Otag;

/**
 * MONGODB COLLECTION - channel default
 * @author ThoaiNH
 * create Mar 31, 2017
 */
@Document(collection = QbMongoCollectionsName.T300)
public class T300PMG implements Serializable{
	public static final int FT300_EM = 2;
	public static final int FT300_RE = 4;
	@Id
	private int id;
	@Field("FO100")
	private int fo100;
	@Field("TV301")
	private String tv301;//name
	@Field("TV301_KEY_LANG")
	private String tv301KeyLang;//key lang
	@Field("TV302")
	private String tv302;//thumbnail
	@Field("TL346")
	private Date tl346;//update date
	@Field("TL348")
	private Date tl348;//created date
	@Field("OTAGS")
	private List<Otag> listOtags;//otags
	@Field("INDEX")
	private int index;
	@Transient
	private int totalPieper;
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
	public String getTv301() {
		return tv301;
	}
	public void setTv301(String tv301) {
		this.tv301 = tv301;
	}
	public Date getTl346() {
		return tl346;
	}
	public void setTl346(Date tl346) {
		this.tl346 = tl346;
	}
	public Date getTl348() {
		return tl348;
	}
	public void setTl348(Date tl348) {
		this.tl348 = tl348;
	}
	public String getTv302() {
		return tv302;
	}
	public void setTv302(String tv302) {
		this.tv302 = tv302;
	}
	public List<Otag> getListOtags() {
		return listOtags;
	}
	public void setListOtags(List<Otag> listOtags) {
		this.listOtags = listOtags;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getTotalPieper() {
		return totalPieper;
	}
	public void setTotalPieper(int totalPieper) {
		this.totalPieper = totalPieper;
	}
	public String getTv301KeyLang() {
		return tv301KeyLang;
	}
	public void setTv301KeyLang(String tv301KeyLang) {
		this.tv301KeyLang = tv301KeyLang;
	}
		
}
