package com.ohhay.piepme.mongo.nestedentities;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author ThoaiNH
 * create Jul 7, 2017
 */
public class COMInfo implements Serializable{
	@Field("COLOR_CODE")
	String colorCode;
	@Field("CHANNEL_TIT")
	String channelTit;
	@Field("SHOP_TIT")
	String shopTit;
	@Field("SHOP_STT")
	int shopStt;
	public COMInfo(String colorCode, String channelTit, String shopTit,
			int shopStt) {
		super();
		this.colorCode = colorCode;
		this.channelTit = channelTit;
		this.shopTit = shopTit;
		this.shopStt = shopStt;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getChannelTit() {
		return channelTit;
	}
	public void setChannelTit(String channelTit) {
		this.channelTit = channelTit;
	}
	public String getShopTit() {
		return shopTit;
	}
	public void setShopTit(String shopTit) {
		this.shopTit = shopTit;
	}
	public int getShopStt() {
		return shopStt;
	}
	public void setShopStt(int shopStt) {
		this.shopStt = shopStt;
	}
	
}
