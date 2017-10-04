package com.ohhay.piepme.mongo.entities.interaction;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;

/**
 * MONGODB COLLECTION - friend info
 * @author ThoaiNH
 * create Jan 3, 2017
 */
@Document(collection = QbMongoCollectionsName.C100)
public class C100PMG implements Serializable{
	public static final String AC = "AC";
	public static final String NC = "NC";
	@Id
	private int id;
	@Field("FO100")
	private int fo100;// send request user
	@Field("FO100F")
	private int fo100f;// receive request user
	@Field("CD168")
	private Date cd168;// date created
	@Field("CD166")
	private Date cd166;// date confirm
	@Field("CV101")
	private String cv101;// status friends (NC: not confirm, AC: accept)
	@Field("CV102")
	private String cv102;// db name of fo100
	@Field("CV102F")
	private String cv102f;// db name of fo100f
	@Field("CN103")
	private int cn103;// is secure contact of FO100
	@Field("CN103F")
	private int cn103f;// is secure contact of FO100F
	@Field("CV104")
	private String cv104;// key02 of fo100
	@Field("CV104F")
	private String cv104f;//key02 of fo100f
	@Transient
	private String nv101;
	@Transient
	private String nv102;
	@Transient
	private List<G100PMG> group;
	@Transient
	protected String urlAvarta;
	@Transient
	protected String nickName;
	@Transient
	protected int nn119;
	@Transient
	protected String kv105; //"CA" or "DN"
	@Transient
	protected long dateDelete;//thoi gian xoa
	public List<G100PMG> getGroup() {
		return group;
	}

	public void setGroup(List<G100PMG> group) {
		this.group = group;
	}

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

	public int getFo100f() {
		return fo100f;
	}

	public void setFo100f(int fo100f) {
		this.fo100f = fo100f;
	}

	public Date getCd168() {
		return cd168;
	}

	public void setCd168(Date cd168) {
		this.cd168 = cd168;
	}

	public String getCv101() {
		return cv101;
	}

	public void setCv101(String cv101) {
		this.cv101 = cv101;
	}

	public String getNv101() {
		return nv101;
	}

	public void setNv101(String nv101) {
		this.nv101 = nv101;
	}

	public String getNv102() {
		return nv102;
	}

	public void setNv102(String nv102) {
		this.nv102 = nv102;
	}

	public Date getCd166() {
		return cd166;
	}

	public void setCd166(Date cd166) {
		this.cd166 = cd166;
	}

	public String getUrlAvarta() {
		return urlAvarta;
	}

	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}

	public String getCv102() {
		return cv102;
	}

	public void setCv102(String cv102) {
		this.cv102 = cv102;
	}

	public String getCv102f() {
		return cv102f;
	}

	public void setCv102f(String cv102f) {
		this.cv102f = cv102f;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getCn103() {
		return cn103;
	}

	public void setCn103(int cn103) {
		this.cn103 = cn103;
	}

	public int getCn103f() {
		return cn103f;
	}

	public void setCn103f(int cn103f) {
		this.cn103f = cn103f;
	}

	public int getNn119() {
		return nn119;
	}

	public void setNn119(int nn119) {
		this.nn119 = nn119;
	}

	public String getCv104() {
		return cv104;
	}

	public void setCv104(String cv104) {
		this.cv104 = cv104;
	}

	public String getCv104f() {
		return cv104f;
	}

	public void setCv104f(String cv104f) {
		this.cv104f = cv104f;
	}

	public String getKv105() {
		return kv105;
	}

	public void setKv105(String kv105) {
		this.kv105 = kv105;
	}

	public long getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete(long dateDelete) {
		this.dateDelete = dateDelete;
	}
	
}
