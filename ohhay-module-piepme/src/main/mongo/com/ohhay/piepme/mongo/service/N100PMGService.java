package com.ohhay.piepme.mongo.service;

import java.util.List;

import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.piepme.mongo.entities.pieper.N100CAFMG;
import com.ohhay.piepme.mongo.entities.pieper.N100Status2;
import com.ohhay.piepme.mongo.entities.pieper.N100Status3;
import com.ohhay.piepme.mongo.nestedentities.DeviceToken;
import com.ohhay.piepme.mongo.nestedentities.K100DetailPMG;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create May 30, 2016
 * update 15/06/2017: auto create COM channel for enterprise account when account is confirmed
 * user PIEPME info
*/
public interface N100PMGService {
	/**
	 * @deprecated
	 * @param name
	 * @param uuid
	 * @return
	 */
	N100PMG register(String name, String uuid);
	/**
	 * @deprecated
	 * @param name
	 * @param uuid
	 * @return
	 */
	N100PMG login(int fo100, String uuid);
	/**
	 * login piepme cho ca nhan
	 * @param nickName
	 * @param digits
	 * @param uuid
	 * @return
	 */
	N100PMG loginV2(String nickName, String digits, String uuid);
	/**
	 * @deprecated
	 * @param m900mg
	 * @param mail
	 * @param qv101
	 * @param fo100
	 * @return
	 */
	String sendMailConfirmNewAccount(String mail);
	/**
	 * @deprecated
	 * @param m900mg
	 * @param mail
	 * @param qv101
	 * @param fo100
	 * @return
	 */
	String sendMailConfirmPiepmeAccount(M900MG m900mg, String mail, String qv101, int fo100);
	/**
	 * @param fo100
	 * @param nickName
	 * @param digits
	 * @return
	 */
	List<N100PMG> listOfTabN100(int fo100, String nickName, String digits);
	/**
	 * @param fo100
	 * @param nv106
	 * @return
	 */
	int updateNV106(int fo100, String nv106);
	/**
	 * @param fo100
	 * @param nv107
	 * @return
	 */
	int updateNV107(int fo100, String nv107);
	/**
	 * @param fo100
	 * @param nv108
	 * @return
	 */
	int updateNV108(int fo100, String nv108);
	/**
	 * @param fo100S fo100s  array 1723##qb##1724
	 * @return
	 */
	List<DeviceToken> listOfTabN100Token(String fo100S);	
	/**
	 * @param nickName
	 * @param securityNumber
	 * @return
	 */
	N100PMG getN100ByNV101(String nickName, String securityNumber);
	/**
	 * @param uuid
	 * @param fo100
	 * @return
	 */
	int updateUUID(String uuid, String nv002, int fo100, int fp150);
	/**
	 * @param search
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<N100PMG> listOfTabN100Sug(String search, int offset, int limit);
	/**
	 * @param uuid
	 * @return
	 */
	N100PMG getN100ByUUID(String uuid);
	/**
	 * @param nickName
	 * @return
	 */
	String randomSecurityNumber(String nickName, int count);
	/**
	 * @param fo100
	 * @param kv101
	 * @param kv102
	 * @param kv103
	 * @param kv106
	 * @param kv107
	 * @param licenseImgs
	 * @param affPiepmeID
	 * @return
	 */
	int updateK100(int fo100, String kv101, String kv102, String kv103, String kv106, String kv107, List<String> licenseImgs, String affPiepmeID);
	/**
	 * @param fo100 user login 
	 * @param fo100b enterprise
	 * @return
	 */
	K100DetailPMG getBusinessInfo(int fo100, int fo100b);
	/**
	 * @param fo100 enterprise
	 * @param fo100c administrator
	 * @return
	 */
	int rejectK100(int fo100, int fo100c, String kv112);
	/**
	 * @param fo100c
	 * @param av101
	 * @param av102
	 * @param av103
	 * @param av104
	 * @param av107
	 * @param ad110String
	 * @return
	 */
	int updateA100(int fo100, String av101, String av102, String av103,
					String av104, String av107, String ad110String, String av111);
	/**
	 * @param fo100
	 * @param fo100c
	 * @return
	 */
	int confirmA100(int fo100, int fo100c);
	/**
	 * @param fo100
	 * @param fo100c
	 * @return
	 */
	
	int rejectA100(int fo100, int fo100c);
	
	
	/**
	 * @param fo100
	 * @param latitude
	 * @param longitude
	 * @param addressFullName
	 */
	int updateLocation(int fo100,double latitude,double longitude,String addressFullName);
	
	/**
	 * @param fo100
	 * @param latitude
	 * @param longitude
	 * @param addressFullName
	 */
	int updateLocationWhenOnline(int fo100,double latitude,double longitude,String addressFullName);
	/**
	 * @param fo100
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	N100Status2 listOfTabN100Loc(int fo100,double latitude,double longitude, int radius);
	/**
	 * @param fo100
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	N100Status2 listOfTabN100LocR(int fo100, int radius);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int updateTabN100REG(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param pnLat
	 * @param pnLong
	 * @param pnRadius
	 * @return
	 */
	N100Status3 listOfTabN100LocV2(int fo100, double latitude, double longitude, int radius);
	/**
	 * @param fo100
	 * @param pnRadius
	 * @return
	 */
	N100Status3 listOfTabN100LocRV2(int fo100, int radius);
	/**
	 * @param nickName
	 * @param digits
	 * @param uuid
	 * @return
	 */
	N100PMG loginV3(String nickName, String digits, String uuid);
	/**
	 * @param nickName
	 * @param digits
	 * @param uuid
	 * @return
	 */
	N100PMG loginEnterprise(String nickName, String digits, String uuid);
	/**
	 * @param fo100Login
	 * @param fo100Remove
	 * @return
	 */
	int removeAccount(int fo100Login, int fo100Remove);
	
	/**
	 * @param fo100
	 * @return
	 */
	List<N100CAFMG> listOfTabN100OCaf(int fo100);
	/**
	 * @param fo100
	 * @param pvActiv
	 * @param pvLogin
	 * @return
	 */
	int upgradeTabV220(int fo100,String pvActiv, String pvLogin);
	/**
	 * @param fo100
	 * @param kv101
	 * @param kv102
	 * @param kv103
	 * @param kv106
	 * @param kv107
	 * @param licenseImgs
	 * @param affPiepmeID
	 * @param uuid
	 * @return
	 */
	int updateK100V2(int fo100, String kv101, String kv102, String kv103, String kv106, String kv107, List<String> licenseImgs, String affPiepmeID, String uuid,  double latitude, double longitude, String addressFullName);
	/**
	 * @deprecated
	 * @param fo100
	 * @param fo100c
	 * @return
	 */
	int confirmK100(int fo100, int fo100c);
	/**
	 * @param fo100
	 * @param fo100e
	 * @return
	 */
	double getDistanceFromEnterprise(int fo100, int fo100e);
}
