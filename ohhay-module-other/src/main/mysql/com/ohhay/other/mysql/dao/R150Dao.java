package com.ohhay.other.mysql.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.other.entities.R150;

public interface R150Dao {
	int getRowTabR150(int pnFO100, Date pdRD154, String pvLOGIN);
	List<R150> rhayReportTabR150(int pnFO100, Date pdRD154, int pnOFFSET, int pnLIMIT, String pvLOGIN);

	List<R150> rhayReportTabR150hist(int pnFO100, int pnOFFSET, int pnLIMIT, String pvLOGIN);
	
	int updateTabR150(int pr150, String rv153, String pvLogin);
}
