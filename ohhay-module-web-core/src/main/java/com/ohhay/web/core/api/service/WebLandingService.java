package com.ohhay.web.core.api.service;

import java.util.List;

import com.ohhay.core.entities.ComtabItem;

/**
 * @author ThoaiNH
 * create 12/12/2014
 * landing page service
 */
public interface WebLandingService {
	/**
	 * @param po100
	 * @param ov101
	 * @param fc800
	 * @return
	 */
	int createLandingPage(int po100, String ov101, int fc800);
	/**
	 * @param po100
	 * @param ov101
	 * @param templateId
	 * @return
	 */
	int createLandingPageByA900(int po100, String ov101, int templateId);
	/**
	 * @return
	 */
	List<ComtabItem> loadListLandingTemplate(int fa950);
	/**
	 * create 29/10/2015
	 */
	int copyWebLanding(int fl400, int fo100, String ov101);
}
