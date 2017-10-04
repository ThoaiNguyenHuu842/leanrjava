package com.ohhay.other.api.dao;

/**
 * @author ThoaiNH
 * create Jan 23, 2016
 * service insert KUB b100 when create landing page
 */
public interface B100KubDao {
	/**
	 * @param pnFK100 
	 * @param pnPB100
	 * @param pvBV101 'ten du an'
	 * @param pvBV102 'ten template'
	 * @param pvBV103 'url'
	 * @param pvBV104 'html source'
	 * @param pvBV105 'EVO ID'
	 * @param pvBV106 'ID template'
	 * @param pnFB050 'ID DU AN'
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabB100(int pnFK100, int pnPB100, String pvBV101, String pvBV102, String pvBV103, 
						String pvBV104, String pvBV105, String pvBV106, int pnFB050, String pvLOGIN);
}
