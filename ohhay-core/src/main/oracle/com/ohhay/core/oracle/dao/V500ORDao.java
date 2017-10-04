package com.ohhay.core.oracle.dao;

import java.sql.Date;

import com.ohhay.core.entities.oracle.V500OR;

public interface V500ORDao {
	int updateTabV500EVO(String pvNV106, Date pdND114, double pnNN115, String pvNV126, String pvNV127, String pvNV128, Date pvND129, String pvNV130, String pvPROID);
	java.util.List<V500OR> listOfTabV500(String pvVV503, String pvNV752, String pvLogin);
	int updateTabV500Vnd(double pnNN115,String pvNV126,String pvPROID,String pvLogin);
}
