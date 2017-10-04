package com.ohhay.piepme.mongo.entities.other;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
/**
 * @author ThoaiNH
 * create May 11, 2017
 * system info
 */
@Document(collection = "S250")
public class S250PMG {
	@Id
	private int id;
	@Field("MAP_LANG")
	private Map<String, String> mapLang;
	@Field("FO100_QBS")
	private ArrayList<Integer> fo100QBS;
	@Field("SN251")
	private int sn251;//mongo server time zone
	@Field("SV252")
	private String sv252;//mongo server locale
	@Field("SN253")
	private int sn253;//android current version
	@Field("SN254")
	private int sn254;//android-se current version
	@Field("SN255")
	private int sn255;//ios current version
	@Field("SN256")
	private int sn256;//ios-se current version
	public Map<String, String> getMapLang() {
		return mapLang;
	}

	public void setMapLang(Map<String, String> mapLang) {
		this.mapLang = mapLang;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getFo100QBS() {
		return fo100QBS;
	}

	public void setFo100QBS(ArrayList<Integer> fo100qbs) {
		fo100QBS = fo100qbs;
	}

	public int getSn251() {
		return sn251;
	}

	public void setSn251(int sn251) {
		this.sn251 = sn251;
	}

	public int getSn253() {
		return sn253;
	}

	public void setSn253(int sn253) {
		this.sn253 = sn253;
	}

	public int getSn254() {
		return sn254;
	}

	public void setSn254(int sn254) {
		this.sn254 = sn254;
	}

	public String getSv252() {
		return sv252;
	}

	public void setSv252(String sv252) {
		this.sv252 = sv252;
	}

	public int getSn255() {
		return sn255;
	}

	public void setSn255(int sn255) {
		this.sn255 = sn255;
	}

	public int getSn256() {
		return sn256;
	}

	public void setSn256(int sn256) {
		this.sn256 = sn256;
	}
}
