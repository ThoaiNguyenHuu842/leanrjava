package com.ohhay.web.core.api.service;

import java.util.List;

import com.ohhay.core.criteria.TemplateChildsPublishCriteria;
import com.ohhay.core.criteria.TemplatePublishCriteria;
import com.ohhay.web.core.entities.mongo.web.A400MG;

/**
 * @author ThoaiNH
 * date create: 08/04/2015
 */
public interface A900MGService {
	/**
	 * @param templatePublishCriteria
	 * @return
	 */
	int publishTemplate(TemplatePublishCriteria templatePublishCriteria);
	/**
	 * @param fo100
	 * @param ov101
	 * @param fc800
	 * @return
	 */
	int publishTemplateChilds(TemplateChildsPublishCriteria templateChildsPublishCriteria);
	/** 
	 * ThoaiVT
	 * create 1/9/2015
	 * @param fa500
	 * @param imageBase64
	 * @param imageBase64Mobi
	 * @return
	 */
	int createA900(int fo100, String ov101, int fc800);
	/**
	 * @param fa900
	 * @return
	 */
	int removeA900(int fa900);
	/**
	 * @author THOAIVT
	 * create 29/08/2015
	 * @param fo100
	 * @param fa950
	 * @param an402
	 * @return
	 */
	List<A400MG> getListTemplate(int pnFA950,int pnAN402,int offset,int limit);
}
