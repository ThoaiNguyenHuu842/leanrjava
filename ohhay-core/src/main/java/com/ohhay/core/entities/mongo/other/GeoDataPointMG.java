package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Aug 26, 2016
 * Mapping MongoDB GeoJSON Point- a geospatial data interchange format based on JavaScript Object Notation (JSON).
 */
public class GeoDataPointMG implements Serializable{
	@Field("type")
	private String type;
	@Field("coordinates")
	private List<Double> coordinates;
	@Field("address_full_name")
	private String addressFullName;
	
	public GeoDataPointMG(double longitude, double latitude ,String addressFullName) {
		super();
		this.type = "Point";
		this.coordinates = new ArrayList<>();
		coordinates.add(longitude);
		coordinates.add(latitude);
		this.addressFullName = addressFullName;
	}
	public GeoDataPointMG() {
		super();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Double> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
	
	public String getAddressFullName() {
		return addressFullName;
	}


	public void setAddressFullName(String addressFullName) {
		this.addressFullName = addressFullName;
	}


	@Override
	public String toString() {
		return "GeoDataPointMG [type=" + type + ", coordinates=" + coordinates + ", addressFullName=" + addressFullName
				+ "]";
	}
}
