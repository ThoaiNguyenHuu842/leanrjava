package com.ohhay.core.oracle.service;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.oracle.N100AddOR;
import com.ohhay.core.entities.oracle.N100OCAF;
import com.ohhay.core.entities.oracle.N100OR;

/**
 * @author ThoaiNH
 * date create 16/06/2015
 */
public interface N100ORService {
	/**
	 * update: 08/07/2015
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<N100OR> listOfTabN100OH(int offset, int limit, String pvLogin);
	/**
	 * @param pvNV101
	 * @param pvNV102
	 * @param pvNV103
	 * @param pvNV104
	 * @param pvNV105
	 * @param pvNV106
	 * @param pvNV107
	 * @param pdND108
	 * @param pvNV109
	 * @param pvNV112
	 * @param pdND114
	 * @param pnNN115
	 * @param pvNV119
	 * @param pvNV126
	 * @param pvNV127
	 * @param pvNV128
	 * @param pvND129
	 * @param pvNV130
	 * @param pnFK100
	 * @param pvLOGIN
	 * @return
	 */
	int insertTabN100EVO(String pvNV101, String pvNV102, String pvNV103, String pvNV104, String pvNV105, 
		    String pvNV106, String pvNV107, Date pdND108, String pvNV109, String pvNV112, Date pdND114, double pnNN115, String pvNV119,
		    String pvNV126, String pvNV127, String pvNV128, Date pvND129, String pvNV130, String pvNV131, Date pdND132, 
		    String pvNV133, String pvNV134, String pvNV135, String pvNV136, String pvNV137, String pvNV138, String pvNV139,
		    int fo100, int pnFK100, String pvLOGIN);
	/**
	 * @param pnPN100
	 * @param pvNV101 : fmame
	 * @param pvNV102 : lname
	 * @param pvNV103 : fname k dau
	 * @param pvNV104 : lname k dau
	 * @param pvNV105 : gioi tinh (M:F)
	 * @param pvNV106 : public email
	 * @param pvNV107 : public phone
	 * @param pdND108 : birthdat
	 * @param pvNV109 : url
	 * @param pnFO100 : url
	 * @param pnFK100 : 1
	 * @param pvLOGIN  
	 * @return piepme id
	 */
	int insertTabN100(int pnPN100, String pvNV101, String pvNV102, String pvNV103, String pvNV104, String pvNV105, 
   		   String pvNV106, String pvNV107, Date pdND108, String pvNV109, String pvNV119, int pnFO100, int pnFK100, String pvLOGIN);
	String insertTabN100PIE(String pvNV101, String pvNV102, String pvNV106, int pnFO100, int pnFK100, String pvLogin);

	/**
	 * @param pvNV126
	 * @param pvLOGIN
	 * @return
	 */
	List<N100OR> listOfTabN100AFF(String pvNV126,int offset,int limit,String pvLOGIN);  
	/**
	 * @param pvNV126
	 * @param pvLOGIN
	 * @return
	 */
	List<N100OR> listOfTabN100CUS(String pvNV126,int offset,int limit,String pvLOGIN);  
	/**
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<N100OR> listOfTabN100OPIE(int offset, int limit, String pvLogin);  
	
	/**
	 * @param pvNV107
	 * @param pnFO100
	 * @param pvLOGIN
	 * @return
	 */
	int updateTabN100BUS(int pnFO100, String pvNV107,String pvLOGIN);

	/**
	 * @param pnFO100
	 * @param pvLOGIN
	 * @return
	 */
	int updateTabN100AFF(int pnFO100,String pvLOGIN);

	/**
	 * @param pvNV126
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<N100OR> listOfTabN100IWA(String pvNV126,int offset, int limit, String pvLogin);
	/**
	 * @param pnFO100
	 * @param pvLOGIN
	 * @return
	 */
	int updateTabN100IWA(int pnFO100,String pvLOGIN);

	/**
	 * @param pdND108
	 * @param pvNV109
	 * @param pvNV127
	 * @param pvNV130
	 * @param pvNV131
	 * @param pvLogin
	 * @return
	 */
	int updateTabN100ADD(String nv101, String nv102, Date pdND108, String pvNV109,  String pvNV127, String pvNV130, String pvNV131, int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	List<N100AddOR> listOfTabN100OADD(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int updateTabN100REG(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int updateTabN100IMM(int fo100, String pvLogin);
	/**
	 * @param nv126
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<N100OR> listOfTabN100RIM(String nv126, int offset, int limit, String pvLogin);
	/**
	 * @param nv126
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<N100OR> listOfTabN100IMM(String nv126, int offset, int limit, String pvLogin);
	/**
	 * @deprecated
	 * @param fo100
	 * @param pvLogin
	 * @return
	 */
	int confirmTabN100IMM(int fo100, String pvLogin);
	/**
	 * @param fo100
	 * @param offset
	 * @param limit
	 * @param pvLogin
	 * @return
	 */
	List<N100OCAF> listOfTabN100OCAF(int fo100,int offset, int limit, String pvLogin);
}
