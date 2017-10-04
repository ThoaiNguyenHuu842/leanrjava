package com.ohhay.core.mysql.service;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.Q100Piepme;

/**
 * @author ThoaiNH
 * create 09/09/2014
 * user mysql service
 */
/**
 * @author ThoaiNH
 * create May 16, 2017
 */
/**
 * @author ThoaiNH
 * create May 16, 2017
 */
public interface Q100Service {
	/**
	 * @param fq100
	 * @param qv101
	 * @param pvLogin
	 * @return
	 */
	List<Q100> getListQ100(int fq100, String qv101, String pvLogin);
	/**
	 * @param pnPQ100
	 * @param pvQV101
	 * @param pvQV102
	 * @param pdQD103
	 * @param pnFO100
	 * @param pvLogin
	 * @return
	 */
	int qhayInsertTabQ100(int pnPQ100,String pvQV101,String pvQV102,Date pdQD103,int pnFO100,String pvLogin);
	/**
	 * @param pvQv101
	 * @param pvQv102
	 * @param pvLogin
	 * @return
	 */
	Q100 qhayCheckTabQ100(String pvQv101, String pvQv102, String pvLogin);
	/**
	 * @param pvQv101
	 * @param pvQv102
	 * @param pvLogin
	 * @return
	 */
	int qhayToolsUpdateTabQ100pwd(String pvQv101, String pvQv102, String pvLogin);
	/**
	 * @param pnFO100
	 * @param pdQD104
	 * @param pvVV503
	 * @param pvLogin
	 * @return
	 */
	int qhayToolsUpdateTabQ100pkg(int pnFO100, Date pdQD104, String pvVV503, String pvLogin);
	/**
	 * @param pvOV102
	 * @param pvLogin
	 * @return
	 */
	String qhayToolsUpdatetabq100reset(String pvOV102,String pvLOGIN);
	/**
	 * @param pvOV102
	 * @param pvQV102
	 * @param pvOV061
	 * @param pvCHKEY
	 * @param pvLogin
	 * @return
	 */
	int qhayToolsUpdatetabq100repwd(String pvOV102, String pvQV102, String pvOV061, String pvCHKEY, String pvLOGIN);
	/**
	 * @param pvQv101
	 * @param pvQv102
	 * @param pvQv110
	 * @param pvLogin
	 * @return
	 */
	Q100 qhayCheckTabQ100Code(String pvQv101, String pvQv102, String pvQv110, String pvLogin);
	/**
	 * check user login on EVO, login success if email confirmed
	 * @param pvQv101
	 * @param pvQv102
	 * @param pvLogin
	 * @return
	 */
	Q100 qbonCheckTabQ100(String pvQv101, String pvQv102, String src, String pvLogin);
	/**
	 * @param uiid
	 * @param fo100
	 * @return
	 */
	Q100Piepme qhayCheckTabQ100Piepme(String uiid, int fo100);
	/**
	 * @param qv110
	 * @return
	 */
	int getValTabQ100Piepme(String qv110);
	/**
	 * @param pnFO100
	 * @param pvNV107
	 * @param pvNV112
	 * @param pdND117
	 * @param pvNV118
	 * @param pvNV119
	 * @param pvNV126
	 * @param pvNV128
	 * @param pdND129
	 * @param pvVV503
	 * @param pvLOGIN
	 * @return
	 */
	int udpateTabQ100Piep(int pnFO100, String pvNV107, String pvNV112, Date pdND117, String pvNV118, String pvNV119,  String pvNV126, String pvNV128, Date pdND129, String pvVV503, String pvLOGIN);
	/**
	 * @param uiid
	 * @param fo100
	 * @return
	 */
	Q100Piepme qhayCheckTabQ100PiepCa(String uiid, int fo100);
	/**
	 * @param uiid
	 * @param fo100
	 * @return
	 */
	Q100Piepme qhayCheckTabQ100PiepDn(String uiid, int fo100);
}
