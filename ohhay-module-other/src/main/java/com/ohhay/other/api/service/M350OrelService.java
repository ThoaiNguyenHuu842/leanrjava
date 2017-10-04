package com.ohhay.other.api.service;

/**
 * @author ThoaiNH
 * create 08/12/2014
 * use orel send mail webserice
 */
public interface M350OrelService {

	/** 
	 * @param pnFO100 702
	 * @param ov102 mail nguoi nhan
	 * @param pvMV366 oohhay@queenb.vn (mail nguoi gui)
	 * @param pvMV367 ho ten gui nguoi 
	 * @param pvMV368 type ssl
	 * @param pvMV369 smtp
	 * @param pvMV370 port
	 * @param pvMV371 password email
	 * @param pvMV375 tieu de can gui
	 * @param pvMESSA noi dung can gui
	 * @param pvLogin
	 * @return
	 */
	int sendMailTabM350Topic(int pnFO100, String ov102, String pvMV366, String pvMV367, String pvMV368, String pvMV369, 
			String pvMV370, String pvMV371, String pvMV375, String pvMESSA, String pvLogin);
	/**
	 * @param pnFO100
	 * @param ov102
	 * @param pvMV367
	 * @param pvMV375 
	 * @param pvMESSA
	 * @param pvLogin
	 * @return
	 */
	int sendMailTabM350Shop(int pnFO100,String ov102,String pvMV367,  String pvMV375, String pvMESSA, String pvLogin);
	
	
	/**
	 * @param email
	 * @param pass
	 * @param host
	 * @param style
	 * @param port
	 * @return
	 */
	int checkEmail(String email, String pass, String host, String style, String post);
}
