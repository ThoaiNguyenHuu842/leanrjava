package com.ohhay.web.core.entities.mongo.webbase;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create: 05/09/2014
 * box info
 * - update:18/08/2015 add cd925 field 
 * - update 01/09/2015 add cv927 field
 */
public class C920MG implements Serializable, Comparable<C920MG> {
	@Transient
	private int type;
	@Transient
	private int realIndex;//readl index cua box, luu cho truong hop sort
	@Field("FC920")
	private int fc920;
	@Field("CV921")
	private String cv921;
	@Field("CV922")
	private String cv922;
	@Field("CV923")
	private String cv923;
	@Field("CN924")
	private int cn924;
	@Field("CV802")
	private String cv802;// template
	@Field("CD925")
	private java.util.Date cd925;
	@Field("CV927")
	private String cv927;// box type (update: 01/09/2015)
	@Field("FC820")
	private int fc820;
	@Field("ORIGINAL")
	private int original;//not original: -1, original: 0
	@Field("VISIBLE")
	private int visible;//an: -1, hien: 0
	@Field("ELEME")
	private List<C900MG> listC900mg;
	@Field("GMAP")
	private G920MG g920mg;
	public C920MG(){
		super();
	}
	
	public C920MG(C920MG c920mg) {
		super();
		this.realIndex = c920mg.getRealIndex();
		this.fc920 = c920mg.getFc920();
		this.cv921 = c920mg.getCv921();
		this.cv922 = c920mg.getCv922();
		this.cv923 = c920mg.getCv923();
		this.cn924 = c920mg.getCn924();
		this.cv802 = c920mg.getCv802();
		this.cv927 = c920mg.getCv927();
		this.fc820 = c920mg.getFc820();
		this.original = c920mg.getOriginal();
		this.visible = c920mg.getVisible();
		this.listC900mg = new ArrayList<C900MG>(c920mg.getListC900mg());
		try{
			this.g920mg = c920mg.getG920mg();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public int getFc920() {
		return fc920;
	}

	public void setFc920(int fc920) {
		this.fc920 = fc920;
	}

	public List<C900MG> getListC900mg() {
		return listC900mg;
	}

	public void setListC900mg(List<C900MG> listC900mg) {
		this.listC900mg = listC900mg;
	}

	public String getCv921() {
		return cv921;
	}

	public void setCv921(String cv921) {
		this.cv921 = cv921;
	}

	public String getCv923() {
		return cv923;
	}

	public void setCv923(String cv923) {
		this.cv923 = cv923;
	}

	public String getCv802() {
		return cv802;
	}

	public void setCv802(String cv802) {
		this.cv802 = cv802;
	}

	public int getCn924() {
		return cn924;
	}

	public void setCn924(int cv924) {
		this.cn924 = cv924;
	}
	
	public String getCv922() {
		return cv922;
	}

	public void setCv922(String cv922) {
		this.cv922 = cv922;
	}
	public int getRealIndex() {
		return realIndex;
	}
	public void setRealIndex(int realIndex) {
		this.realIndex = realIndex;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public int getFc820() {
		return fc820;
	}
	public void setFc820(int fc820) {
		this.fc820 = fc820;
	}
	
	public G920MG getG920mg() {
		return g920mg;
	}
	public void setG920mg(G920MG g920mg) {
		this.g920mg = g920mg;
	}
	
	public int getOriginal() {
		return original;
	}
	public void setOriginal(int original) {
		this.original = original;
	}
	

	public java.util.Date getCd925() {
		return cd925;
	}

	public void setCd925(java.util.Date cd925) {
		this.cd925 = cd925;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getCv927() {
		return cv927;
	}

	public void setCv927(String cv927) {
		this.cv927 = cv927;
	}

	@Override
	public String toString() {
		return "C920MG [realIndex=" + realIndex + ", fc920=" + fc920
				+ ", cv921=" + cv921 + ", cv922=" + cv922 + ", cv923=" + cv923
				+ ", cn924=" + cn924 + ", cv802=" + cv802 + ", cd925=" + cd925
				+ ", fc820=" + fc820 + ", original=" + original + ", visible="
				+ visible + ", listC900mg=" + listC900mg + ", g920mg=" + g920mg
				+ "]";
	}

	@Override
	public int compareTo(C920MG o) {
		// TODO Auto-generated method stub
		if (this.cn924 == o.getCn924())
			return 0;
		else if (this.cn924 > o.getCn924())
			return 1;
		else
			return -1;
	}	
}
