package com.ohhay.other.mysql.service;

import java.sql.Date;
import java.util.List;

import com.ohhay.other.entities.R150;

/**
 * @author ThoaiNH
 * create 12/12/2014
 * news of date service
 */
public interface R150Service {
	int getRowTabR150(int pnFO100, Date pdRD154, String pvLOGIN);
	List<R150> rhayReportTabR150(int pnFO100, Date pdRD154, int pnOFFSET, int pnLIMIT, String pvLOGIN);
	List<R150> rhayReportTabR150hist(int pnFO100, int pnOFFSET, int pnLIMIT, String pvLOGIN);
	int updateTabR150(int pr150, String rv153, String pvLogin);
	int acceptShare(int pr150, int fm150, int fo100, String pvlogin);
	int denyShare(int pr150, int fm150, int fo10, String pvLogin);
}
