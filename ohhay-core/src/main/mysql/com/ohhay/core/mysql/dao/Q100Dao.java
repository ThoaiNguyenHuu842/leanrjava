package com.ohhay.core.mysql.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.Q100Piepme;

/**
 * @author ThoaiNH
 * create Mar 30, 2015
 */
public interface Q100Dao {
	List<Q100> getListQ100(int fq100, String qv101, String pvLogin);
	int qhayInsertTabQ100(int pnPQ100,String pvQV101,String pvQV102,Date pdQD103,int pnFO100,String pvLogin);
	Q100 qhayCheckTabQ100(String pvQv101, String pvQv102, String pvLogin);
	Q100 qbonCheckTabQ100(String pvQv101, String pvQv102, String src, String pvLogin);
	Q100 qhayCheckTabQ100Code(String pvQv101, String pvQv102, String pvQv110, String pvLogin);
	int qhayToolsUpdateTabQ100pwd(String pvQv101, String pvQv102, String pvLogin);
	int qhayToolsUpdateTabQ100pkg(int pnFO100, Date pdQD104, String pvVV503, String pvLogin);
	String qhayToolsUpdatetabq100reset(String pvOV102,String pvLOGIN);
	int qhayToolsUpdatetabq100repwd(String pvOV102, String pvQV102, String pvOV061, String pvCHKEY, String pvLOGIN);
	Q100Piepme qhayCheckTabQ100Piepme(String pvQV102, int fo100, String pvLogin);
	int getValTabQ100Piepme(String qv110);
	int udpateTabQ100Piep(int pnFO100, String pvNV107, String pvNV112, Date pdND117, String pvNV118, String pvNV119,  String pvNV126, String pvNV128, Date pdND129, String pvVV503, String pvLOGIN);
	int updateTabQ100Busi(int fo100, String pvLogin);
	int getTabQ100PiepId(String qv112, String pvLogin);
	int updateTabQ100Regi(int fo100, String piepmeID, String pvLogin);
	String getValTabQv112(int fo100, String pvLogin);
	Q100Piepme qhayCheckTabQ100PiepDn(String pvQv110,int fo100,String pvLogin);
	Q100Piepme qhayCheckTabQ100PiepCa(String pvQv110,int fo100,String pvLogin);
	
}