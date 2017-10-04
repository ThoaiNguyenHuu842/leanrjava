package com.ohhay.other.mysql.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.N100;

public interface N100Dao {
	int nhayUpdateTabN100(int pnFO100, String pvNV101, String pvNV102,String pvNV103, String pvNV104,
			String pvNV105,String pvNV106,String pvNV107,Date pdND108,String pvNV109, int pnNN110, int pnNN111, int pnFD000, String pvLOGIN);
	int nhayUpdateTabN100Smtp(String pvNV116,String pvNV117,String pvNV118,String pvNV119,String pvNV120, int pnFO100, String pvLOGIN);
	int getValTabN100(int po100, String pvLogin);
	List<N100> nhayListOfTabN100Smtp(int fo100, String pvLogin);
}
