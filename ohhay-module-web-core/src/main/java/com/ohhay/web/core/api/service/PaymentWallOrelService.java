package com.ohhay.web.core.api.service;

import java.util.Date;

public interface PaymentWallOrelService {
	String getPayMentWidget(int fo100, String widgetType, String skuId,  String userAddress, Date userBirthDate, 
			  String userCity, String userCountry, String userZip, String userFisrtName,
			  String userLastName, String userState, String userSex, String userName, String userEmail);
}
