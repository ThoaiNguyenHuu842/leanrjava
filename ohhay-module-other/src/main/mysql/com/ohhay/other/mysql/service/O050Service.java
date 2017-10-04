package com.ohhay.other.mysql.service;

/**
 * @author ThoaiNH
 * create 28/11/2014
 * otp code confirm service
 */
public interface O050Service {
	/**
	 * @param pvOV051
	 * @param pvOV061
	 * @param pvLogin
	 * @return
	 */
	String ohayInsertTabO050(String pvOV051, String pvOV061, String pvLogin);
	/**
	 * @param pvOV051
	 * @param pvOV054
	 * @param pvOV061
	 * @param pvLogin
	 * @return
	 */
	int ohayConfirmTabO050(String pvOV051, String pvOV054, String pvOV061, String pvLogin);
	/**
	 * @param phone
	 * @param countryCode
	 * @return 0: success, -1: phone format invaild, -2: phone exists, -3: had tried 3 time -> wait after 24h,  other value is nexmo error status code
	 */
	int sendSMSCodeRegister(String phone, String countryCode);
}
