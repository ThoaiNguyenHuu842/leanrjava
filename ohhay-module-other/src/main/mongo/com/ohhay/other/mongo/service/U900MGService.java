package com.ohhay.other.mongo.service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.criteria.DomainCriteria;
import com.ohhay.other.entities.mongo.domain.U910MG;

/**
 * @author ThoaiNH
 *
 */
public interface U900MGService {
	/**
	 * @param fo100
	 * @param u910Pos
	 * @param uv911
	 * @param uv912
	 * @param un913
	 * @return
	 */
	int insertTabU900(int fo100, int u910Pos, String uv911, String uv912, int un913);
	/**
	 * @param uv911
	 * @return
	 */
	U910MG checkTabDomain(String uv911);
	/**
	 * @param domainIndex
	 * @param mn914
	 * @param fo100
	 * @return
	 */
	int changeDomainOhhayFunction(int domainIndex, int mn914, int fo100);
	/**
	 * update: 07/07/2015 check right when add domain
	 * @param ov101
	 * @param domainCriteria
	 * @param fo100
	 * @return
	 */
	int saveDomain(String ov101, DomainCriteria domainCriteria, int fo100, AuthenticationRightInfo authenticationRightInfo, boolean appCall);
	/**
	 * @param domainIndex
	 * @param fo100
	 * @return
	 */
	int deleteDomain(int domainIndex, int fo100);
	/**
	 * @param authenticationRightInfo
	 * @return 
	 * 		ApplicationConstant.RE_VAILD_RIGHT: can add new domain
	 * 		other value: error message (can not add new domain) 
	 */
	String checkRightAddDomain(AuthenticationRightInfo authenticationRightInfo, int fo100);
}
