package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.OhhayDefaultData;

/**
 * @author TuNt
 * create date Nov 25, 2016
 * ohhay-core
 */
public class M900DesMG implements Serializable{
	private int id;
	private String nv100;
	private String mv903;
	private String mv905;
	private String mv907;
	private String mv908;
	private LanguageMG rLanguage;
	private int totalStie;
	private String md904String;
	private String ml948String;
	private String region;
	private String urlAvarta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getNv100() {
		return nv100;
	}
	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}
	public String getMv903() {
		return mv903;
	}
	public void setMv903(String mv903) {
		this.mv903 = mv903;
	}
	public String getMv905() {
		return mv905;
	}
	public void setMv905(String mv905) {
		this.mv905 = mv905;
	}
	public String getMv907() {
		return mv907;
	}
	public void setMv907(String mv907) {
		this.mv907 = mv907;
	}
	public LanguageMG getrLanguage() {
		return rLanguage;
	}
	public void setrLanguage(LanguageMG rLanguage) {
		this.rLanguage = rLanguage;
	}
	public int getTotalStie() {
		return totalStie;
	}
	public void setTotalStie(int totalStie) {
		this.totalStie = totalStie;
	}
	public String getMd904String() {
		return md904String;
	}
	public void setMd904String(String md904String) {
		this.md904String = md904String;
	}
	public String getMl948String() {
		return ml948String;
	}
	public void setMl948String(String ml948String) {
		this.ml948String = ml948String;
	}
	public String getMv908() {
		return mv908;
	}
	public void setMv908(String mv908) {
		this.mv908 = mv908;
	}
	public String getMv903Decrypt() {
		try {
			String st = AESUtils.decrypt(mv903);
			return st;
		} catch (Exception ex) {
			return null;
		}
	}
	public String getUrlAvarta() {
		if (mv908 != null && mv908.length() > 0)
			urlAvarta = getImgLink() + mv908;
		else
		{
			if(mv905.toLowerCase().equals("m"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_M;
			else if(mv905.toLowerCase().equals("f"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_F;
			else
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_N;
		}
		return urlAvarta;
	}
	public String getImgLink()
	{
		if(region != null)
			return "https://"+region.toLowerCase()+"-oohhay.s3.amazonaws.com/"+id+"/";
		else
			return "https://oohhay.s3.amazonaws.com/"+id+"/";
	}
	@Override
	public String toString() {
		return "M900DesMG [id=" + id + ", nv100=" + nv100 + ", mv903=" + mv903 + ", mv905=" + mv905 + ", mv907=" + mv907
				+ ", mv908=" + mv908 + ", rLanguage=" + rLanguage + ", totalStie=" + totalStie + ", md904String="
				+ md904String + ", ml948String=" + ml948String + ", region=" + region + "]";
	}
	
}
