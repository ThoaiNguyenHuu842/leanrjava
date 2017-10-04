package com.ohhay.web.core.api.dao;

import java.util.Date;

public interface PaymentWallOrelDao {
	String getPayMentWidget(String pmwuser, String widgetType, String pmwProductName, String pmwPrice, String pmwNgoaite, String pmwRecurring, String packetName, String userAddress, Date userBirthDate, 
			  String userCity, String userCountry, String userZip, String userFisrtName,
			  String userLastName, String userState, String userSex, String userName, String userEmail);
}
