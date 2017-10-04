package com.ohhay.core.mysql.dao;

import java.util.List;

import com.ohhay.core.entities.M350;

public interface M350Dao {
	int sendMailTabM350Confirm(int pnFO100, String ov102, String pvMV367, String pvMV375, String pvMESSA, String pvLogin);
	List<M350> listOfTabM350Send(String pvLogin);
	int sendMailTabM350Check(String email, String pvLogin);
}
