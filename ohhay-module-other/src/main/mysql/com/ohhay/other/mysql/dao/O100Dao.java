package com.ohhay.other.mysql.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.other.entities.O100;
import com.ohhay.other.entities.O100Das;

/**
 * @author ThoaiNH
 * create Feb 25, 2014
 */
public interface O100Dao {
	/**
	 * @param po100
	 * @param ov101
	 * @param ov102
	 * @param ov103
	 * @param qv102
	 * @param fk100
	 * @param fn750
	 * @param fc500
	 * @param pnFC800
	 * @param fd000
	 * @param pvLogin
	 * @param uri
	 * @return
	 */
	int ohayInserttabO100(int po100, String ov101, String ov102, String ov103, String qv102, int fk100, int fn750, int fc500, int pnFC800, int fd000, String pvLogin, String uri);
	/**
	 * @param ov103
	 * @param pvSearch
	 * @param pvLogin
	 * @return
	 */
	List<O100> qhayListTabQ100(String ov103, String pvSearch, String pvLogin);
	/**
	 * @param pvOV102
	 * @param pnFO100
	 * @param pvLogin
	 * @return
	 */
	String updateTabO100Confirm(String pvOV102, int pnFO100, String pvLogin );
	/**
	 * @param po100
	 * @param pvLogin
	 * @return
	 */
	int stornoTabO100(int po100, String pvLogin);
	/**
	 * @param po100
	 * @param pvLogin
	 * @return
	 */
	int stornoTabO100Center(int po100, String pvLogin);
	/**
	 * @param po100
	 * @param pvOV101
	 * @param pvOV102
	 * @param pvOV103
	 * @param pvOV108
	 * @param pvQV102
	 * @param pnFK100
	 * @param pnFN750
	 * @param pnFD000
	 * @param pvLOGIN
	 * @param uri
	 * @return
	 */
	int ohayInsertTabO100Evo(int po100, String pvOV101, String pvOV102, String pvOV103,  String pvOV108, String pvQV102, int pnFK100, int pnFN750, int pnFD000, String pvLOGIN, String uri);
	/**
	 * @param fo100
	 * @param ev151
	 * @param pvSEARC
	 * @param pnOFFSET
	 * @param pnLIMIT
	 * @param pvLogin
	 * @return
	 */
	List<O100> ohayListTabO100D(int fo100, String ev151,  String pvSEARC, int pnOFFSET, int pnLIMIT, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int updateTabO100D(int fo100, String pvLogin);
	/**
	 * @param po100
	 * @param pvOV101
	 * @param pvOV102
	 * @param pvOV103
	 * @param pdOD107
	 * @param pvOV108
	 * @param pvNV109
	 * @param pvNV112
	 * @param pdOD114
	 * @param pnON115
	 * @param pvOV116
	 * @param pvOV117
	 * @param pvOV118
	 * @param pdOD119
	 * @param pvOV120
	 * @param pvOV121
	 * @param pvOV122
	 * @param pvOV123
	 * @param pvOV124
	 * @param pvOV125
	 * @param pvOV126
	 * @param pvOV127
	 * @param pvQV102
	 * @param pnFK100
	 * @param pnFN750
	 * @param pnFD000
	 * @param pvLOGIN
	 * @param uri
	 * @return
	 */
	int ohayInsertTabO100Bon(int po100, String pvOV101, String pvOV102, String pvOV103, Date pdOD107, String pvOV108, String pvNV109, String pvNV112, Date pdOD114,
							double pnON115, String pvOV116, String pvOV117, String pvOV118, Date pdOD119, String pvOV120,
							String pvOV121, String pvOV122, String pvOV123, String pvOV124, String pvOV125, String pvOV126, String pvOV127,  
							String pvQV102, int pnFK100, int pnFN750, int pnFD000, String pvLOGIN, String uri);
	/**
	 * @param pvLogin
	 * @return
	 */
	List<O100> listOfTabO100Del(String pvLogin);
	/**
	 * @param pvSEARC
	 * @param pnPAYME
	 * @param pdFRDAT
	 * @param pdTODAT
	 * @param pnOFFSET
	 * @param pnLIMIT
	 * @param pnSORT
	 * @param pnDIRECTION
	 * @param pvLOGIN
	 * @return
	 */
	List<O100Das> getListAccount(String pvSEARC, int pnPAYME, Date pdFRDAT, Date pdTODAT, int pnOFFSET, int pnLIMIT,
			int pnSORT, int pnDIRECTION, String pvLOGIN);
	/**
	 * @param pnPO100
	 * @param pvOV101
	 * @param pvOV102
	 * @param pvOV103
	 * @param pvOV110
	 * @param pvNV101
	 * @param pnFN750
	 * @param pnFK100
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabO100Piepme(int pnPO100, String pvOV101, String pvOV102, String pvOV103, String pvOV110, String pvNV101, int pnFN750, int pnFK100, String pvLOGIN, String uri);
	/**
	 * @param pvOV110
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int updateTabO100Key(String pvOV110, String pvOV116, int fo100, String pvLogin);
}