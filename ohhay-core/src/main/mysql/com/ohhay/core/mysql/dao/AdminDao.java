package com.ohhay.core.mysql.dao;

import java.sql.Date;
import java.util.List;

import com.ohhay.core.entities.BonEvoAccount;
import com.ohhay.core.entities.ChartItemInfo2;

public interface AdminDao {
	int adminSetNewTemplate(int pnFID00, String pvHERKU, int pnFC400, String pvLOGIN);
	String getIpOfLocation(String ip);
	List<ChartItemInfo2> reportWebDaily(Date pdDATEF, Date pdDATET, String pvLogin); 
	int adminUpdateTabO100(int po100, int fc800, int fd000, String pvLogin);
	List<BonEvoAccount> listOfTabAccounts(int fo100, String qv101, String pvLogin);
}
