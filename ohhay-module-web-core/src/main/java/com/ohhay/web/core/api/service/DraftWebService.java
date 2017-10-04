package com.ohhay.web.core.api.service;

/**
 * @author ThoaiNH
 * create 29/07/2015
 * all service for draft function
 */
public interface DraftWebService {
	/** apply draft version to home version 
	 * @param fo100
	 * @param qv101
	 * @return
	 * @deprecated
	 */
	int applyNewTemplate(int fo100, String qv101);
	/**
	 * @param fo100
	 * @param fc800
	 * @param fd000
	 * @param ov101
	 * @return
	 * @deprecated
	 */
	int changeTemplate(int fo100, int fc800, int fd000, String ov101);
	/**
	 * - create 29/07/2015 - ThoaiNH - thay doi template theo quy trinh moi
	 * @param fo100
	 * @param fa900
	 * @param ov101
	 * @return
	 */
	int changeTemplateA900(int fo100, int fa900, String ov101);
	/** - create 29/07/2015 - ThoaiNH - apply template theo quy trinh moi
	 * @param fo100
	 * @param qv101
	 * @return
	 */
	int applyNewTemplateA900(int fo100, String qv101);
	/**
	 * create 11/09/2015
	 * @param ft500
	 * @param fo100
	 * @param ov101
	 * @return
	 */
	int copyWebChild(int ft500, int fo100, String ov101);
}
