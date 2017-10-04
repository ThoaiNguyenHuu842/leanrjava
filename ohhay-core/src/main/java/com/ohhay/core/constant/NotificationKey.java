package com.ohhay.core.constant;

/**
 * @author ThoaiNH
 * create Dec 13, 2016
 * key of notification
 */
public enum NotificationKey {
	//customer sent request to designer
	BONEVO_CUS_SEND_REQUEST_DES("BONEVO_CUS_SEND_REQUEST_DES"),
	//designer confirmed request
	BONEVO_DES_CONFIRMED_REQUEST("BONEVO_DES_CONFIRMED_REQUEST"),
	//designer reject cus
	BONEVO_DES_REJECT_CUS("BONEVO_DES_REJECT_CUS"),
	//designer sent a design to customer
	BONEVO_DES_SEND_DESIGN("BONEVO_DES_SEND_DESIGN"),
	//customer applied design
	BONEVO_CUS_APPLY_DESIGN("BONEVO_CUS_APPLY_DESIGN"),
	//customer rejected design
	BONEVO_CUS_REJECT_DESIGN("BONEVO_CUS_REJECT_DESIGN"),
	//key type notifi
	BONEVO_NOTIFICATION_KEY("BONEVO_NOTIFICATION_KEY");
	private String key;
	
	NotificationKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
