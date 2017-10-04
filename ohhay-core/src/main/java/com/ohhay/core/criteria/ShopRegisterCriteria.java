package com.ohhay.core.criteria;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ThoaiNH
 * create Apr 25, 2016
 */
public class ShopRegisterCriteria implements Serializable{
	@NotEmpty(message="Shop name must not be empty")
	private String shopName;
	@NotEmpty(message="Street address must not be empty")
	private String streetAddress;
	@NotEmpty(message="City must not be empty")
	private String city;
	@NotEmpty(message="Phone number name must not be empty")
	private String phoneNumber;
	@NotEmpty(message="Country must not be empty")
	private String country;
	@NotEmpty(message="Zip code must not be empty")
	private String zipCode;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "ShopRegisterCriteria [shopName=" + shopName + ", streetAddress="
				+ streetAddress + ", city=" + city + ", phoneNumber="
				+ phoneNumber + ", country=" + country + ", zipCode=" + zipCode
				+ "]";
	}
	
}
