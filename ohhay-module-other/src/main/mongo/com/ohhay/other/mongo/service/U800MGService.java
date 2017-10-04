package com.ohhay.other.mongo.service;

import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.criteria.DomainCriteria;
import com.ohhay.other.entities.mongo.domain.topic.U810MG;

/**
 * @author ThoaiNH
 *
 */
public interface U800MGService {
	/**
	 * @param fo100
	 * @param u810Pos
	 * @param uv811
	 * @param uv812
	 * @param un813
	 * @return
	 */
	int insertTabU800(int fo100, int u810Pos, String uv811, String uv812, int un813);
	/**
	 * @param uv811
	 * @return
	 */
	U810MG checkTabDomain(String uv811);
	/**
	 * @param domainIndex
	 * @param mn814
	 * @param fo100
	 * @return
	 */
	int changeDomainOhhayFunction(int domainIndex, int mn814, int fo100);
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
	 * @return 1111: can add domain function, -1111: can't add domain function
	 */
	String checkRightAddDomain(AuthenticationRightInfo authenticationRightInfo);
}
