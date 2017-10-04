package com.ohhay.core.oracle.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.oracle.N100AddOR;
import com.ohhay.core.entities.oracle.N100OCAF;
import com.ohhay.core.entities.oracle.N100OR;

/**
 * @author ThoaiNH
 * create Jul 28, 2014
 */
public interface N100ORDao {
	int insertTabN100(int pnPN100, String pvNV101, String pvNV102, String pvNV103, String pvNV104, String pvNV105, 
					      		   String pvNV106, String pvNV107, Date pdND108, String pvNV109,String pvNV119, int pnFO100, int pnFK100, String pvLOGIN);
	List<N100OR> listOfTabN100OH(int offset, int limit, String pvLogin);
	int insertTabN100EVO(String pvNV101, String pvNV102, String pvNV103, String pvNV104, String pvNV105, 
		    String pvNV106, String pvNV107, Date pdND108, String pvNV109, String pvNV112, Date pdND114, double pnNN115, String pvNV119,
		    String pvNV126, String pvNV127, String pvNV128, Date pvND129, String pvNV130, String pvNV131, Date pdND132, 
		    String pvNV133, String pvNV134, String pvNV135, String pvNV136, String pvNV137, String pvNV138, String pvNV139,
		    int fo100, int pnFK100, String pvLOGIN);
	int stornoTabN100(int pn100, String pvLogin);
	String insertTabN100PIE(String pvNV101, String pvNV102, String pvNV106, int pnFO100, int pnFK100, String pvLogin);
	int updateTabN100BUS(int pnFO100,String pvNV107,String pvLOGIN);
	List<N100OR> listOfTabN100AFF(String pvNV126,int offset,int limit,String pvLOGIN);  
	List<N100OR> listOfTabN100CUS(String pvNV126,int offset,int limit,String pvLOGIN);  
	List<N100OR> listOfTabN100OPIE(int offset, int limit, String pvLogin);  
	int updateTabN100AFF(int pnFO100,String pvLOGIN);
	List<N100OR> listOfTabN100IWA(String pvNV126,int offset, int limit, String pvLogin);
	int updateTabN100IWA(int pnFO100,String pvLOGIN);
	int updateTabN100ADD(String nv101, String nv102, Date pdND108, String pvNV109,  String pvNV127, String pvNV130, String pvNV131, int fo100, String pvLogin);
	List<N100AddOR> listOfTabN100OADD(int fo100, String pvLogin);
	int updateTabN100REG(int fo100, String pvLogin);
	int updateTabN100IMM(int fo100, String pvLogin);
	List<N100OR> listOfTabN100RIM(String nv126, int offset, int limit, String pvLogin);
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
	
	/**
	 * @param fo100
	 * @param pvNV107
	 * @param pvLogin
	 * @return
	 */
	int updateTabN100CODE(int fo100,String pvNV107,String pvLogin);
}

