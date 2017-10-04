package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create May 8, 2017
 */
public class PieperImg implements Serializable{
	@Field("URL")
	private String imgLink;
	@Field("DES")
	private String description;
	@Field("RATIO")
	private double ratio;
	public PieperImg(String imgLink, String description, double ratio) {
		super();
		this.imgLink = imgLink;
		this.description = description;
		this.ratio = ratio;
	}
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
