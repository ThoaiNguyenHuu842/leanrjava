package com.ohhay.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ohhay.core.criteria.AccountEVORegisCriteria;
import com.ohhay.core.criteria.AccountPiepmeRegisCriteria;
import com.ohhay.core.criteria.AccountRegisCriteria;
import com.ohhay.core.entities.mongo.profile.M900MG;

/**
 * @author ThoaiNH
 * date create: 17/02/2015
 * create account in app, web...>> very important service
 */
public interface CreateAccountService {
	/**
	 * @deprecated
	 * @param accountRegisCriteria
	 * @return
	 */
	public M900MG createAccount(AccountRegisCriteria accountRegisCriteria);
	/**
	 * @deprecated
	 * create 24/07/2015 - ThoaiNH - quy trinh tao web moi
	 * @param accountRegisCriteria
	 * @return
	 */
	public M900MG createAccountA900(AccountRegisCriteria accountRegisCriteria);
	/**
	 * @param accountRegisCriteria
	 * @return -1: mail sai dinh dang, -2: chua nhap ho ten, -3: password confirm chua dung, -4: password sai dinh dang, -5: mail da ton tai, -6: loi khac, 1: thanh cong
	 */
	public int validateAccountWeb(AccountRegisCriteria accountRegisCriteria);
	/**
	 * date create: 14/12/2015
	 * create EVO account
	 * @param accountRegisCriteria
	 * @return
	 */
	public M900MG createEVOAccount(AccountEVORegisCriteria accountRegisCriteria, HttpServletRequest request, String currentLanguage);
	/**
	 * @param accountRegisCriteria
	 * @return -1: mail sai dinh dang, -2: chua nhap ho ten, -3: password confirm chua dung, -4: password sai dinh dang, -5: mail da ton tai, -6: loi khac, 1: thanh cong
	 */
	public int validateEVOAccount(AccountEVORegisCriteria accountRegisCriteria);
	/**
	 * create 14/12/2015 - ThoaiNH - tao web EVO
	 * @param accountRegisCriteria
	 * @param response
	 * @param currentLang: user current language use in BONEVO
	 * @return
	 */
	int createAccountWebEVO(AccountEVORegisCriteria accountRegisCriteria, HttpServletResponse response, HttpServletRequest request, String currentLang);
	/**
	 * @param m900mg
	 * @return
	 */
	int sendMailRegisSuccess(M900MG m900mg);
	/**
	 * @deprecated
	 * create new PIEPME account, new BONEVO account
	 * @param accountPiepmeRegisCriteria4
	 * @return
	 */
	int createAccountPiepmeNew(AccountPiepmeRegisCriteria accountPiepmeRegisCriteria);
	/**
	 * @deprecated
	 * create new PIEPME account with BONEVO account
	 * @param fo100
	 * @param mv926
	 * @return
	 */
	int createAccountPiepmeWithExistsingBonevo(int fo100, String mv926);
	/**
	 * @deprecated
	 * @param email
	 * @param uuid
	 * @param nickName
	 * @param securityNumber
	 * @return po100 created
	 */
	int createAccountPiepme(String email, String uuid, String nickName, String securityNumber);
	/**
	 * create 03/02/2017
	 * @param uuid
	 * @param nickName
	 * @param securityNumber
	 * @return
	 */
	int createAccountPiepmeV2(String uuid, String nickName, String securityNumber, String affPiepmeId, String nv002);
	/**
	 * cap nhat thong tin doanh nghiep, tao rieng tk doanh nghiep
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
	int confirmEnterpriseAccount(int fo100, int fo100c);
}	
