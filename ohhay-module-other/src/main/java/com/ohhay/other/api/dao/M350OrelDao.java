package com.ohhay.other.api.dao;

public interface M350OrelDao {
	int sendMailTabM350Topic(int pnFO100, String ov102, String pvMV366, String pvMV367, String pvMV368, String pvMV369, 
			String pvMV370, String pvMV371, String pvMV375, String pvMESSA, String pvLogin);
	int sendMailTabM350Shop(int pnFO100,String ov102,String pvMV367,  String pvMV375, String pvMESSA, String pvLogin);
	int checkEmail(String email, String pass, String host, String style,
			String post);
}
