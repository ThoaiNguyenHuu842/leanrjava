package com.ohhay.core.mongo.service;

import java.util.List;

import com.ohhay.core.criteria.ChangePassCriteria;
import com.ohhay.core.criteria.M900MGCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900DesMG;
import com.ohhay.core.entities.mongo.profile.M900MG;

/**
 * @author ThoaiNH
 * create 20/12/2014
 * user info service
 */
public interface M900MGService {
	/**
	 * @param limit
	 * @return
	 */
	List<M900MG> findM900All(int limit);
	/**
	 * @param limit
	 * @return
	 */
	List<M900MG> findM900Index(int limit);
	int getMaxId();
	/**
	 * @param email: real email, not AES encrypt
	 * @return false if email not exists
	 */
	boolean emailIsExists(String email);
	/**
	 * @param mv907
	 * @return
	 */
	int checkTabPrivacy(String mv907);
	/**
	 * @param m900mg
	 */
	void loadHistory(M900MG m900mg);
	/**
	 * @param avartaBase64
	 * @param m900mg
	 * @return
	 */
	int saveAvarta(String avartaBase64, M900MG m900mg);
	/**
	 * @param changePassCiteria
	 * @param qv102
	 * @param qv101
	 * @return
	 */
	int changePassWord(ChangePassCriteria changePassCiteria, String qv102, String qv101);
	/**
	 * @param m900mgCriteria
	 * @param q100
	 * @return
	 */
	int saveAccount(M900MGCriteria m900mgCriteria, Q100 q100);
	/**
	 * @param fieldName
	 * @param value
	 * @param q100
	 * @return
	 */
	int saveAccountOneField(String fieldName, Object value, Q100 q100);
	/**
	 * @param fo100
	 * @param qv101
	 * @param qv102
	 * @param passwordConfirm
	 * @param newEmail
	 * @return
	 */
	int changeEmail(int fo100, String qv101,String qv102, String passwordConfirm, String newEmail);
	/**
	 * @param fo100
	 * @param qv101
	 * @param qv102
	 * @param passwordConfirm
	 * @param newEmail
	 * @return
	 */
	int sendEmailAgain(int fo100, String qv101,String qv102, String passwordConfirm, String newEmail);
	@Deprecated
	int getMaxIndexM940(int fo100);
	@Deprecated
	int getMaxIdM940(int fo100);
	@Deprecated
	int changeM940Index(int fo100, int id, int newIndex);
	/**
	 * @param hv101
	 * @return
	 */
	M900MG loadUserProfile(String hv101);
	/**
	 * @param mv903
	 * @return
	 */
	M900MG loadUserProfileMerian(int fo100);
	/**
	 * @param bgBase64
	 * @param defaultImg
	 * @param m900mg
	 * @return
	 */
	int saveBackgroundTopic(String bgBase64, String defaultImg, M900MG m900mg);
	/**
	 * @param mv907
	 * @return 1 if phone is exists, 0 if not exists
	 */
	int checkUserExist(String mv907);
	/**
	 * @param po100
	 * @return -1: mv923 invalid format, -2: has exsists, 0: other error, 1: success
	 */
	int storNotTabM900(int po100);
	/**
	 * @param po100
	 * @param mv923
	 * @return
	 */
	int saveMV923(int po100, String mv923);
	/**
	 * remove account in db center
	 * @param po100
	 * @return
	 */
	int storNotTabM900Center(int po100);
	/**
	 * @param m900mg
	 * @param mail
	 * @param qv101
	 * @param fo100
	 * @return
	 */
	int sendMailConfirmEVOAccount(M900MG m900mg, String mail, String qv101, int fo100, String passForDigi);
	
	/**
	 * ThoaiVt 
	 * 05/03/2016
	 * load account
	 */
	List<M900MG> getListAccount(String content,int fo100,int offset,int limit);
	/**
	 * @param fo100
	 * @param fe400
	 * @param pvSearch
	 * @param offset
	 * @param limit
	 * @return
	 * Tunt 17/11/2016
	 * get list designer
	 */
	List<M900MG> listOfTabDesigner(int fo100, int fe400, String pvSearch, int offset, int limit);
	
	/**
	 * @param fo100
	 * @return
	 * Tunt 17/11/2016
	 * get info designer
	 */
	M900DesMG listOfTabDesignerOne(int fo100);
}
