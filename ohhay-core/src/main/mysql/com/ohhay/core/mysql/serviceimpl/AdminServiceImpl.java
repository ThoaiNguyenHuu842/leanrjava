package com.ohhay.core.mysql.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.BonEvoAccount;
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.core.mysql.dao.AdminDao;
import com.ohhay.core.mysql.service.AdminService;

@Service(value = SpringBeanNames.SERVICE_NAME_ADMIN)
@Scope("prototype")
public class AdminServiceImpl implements AdminService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_ADMIN)
	private AdminDao adminDao;

	@Override
	public int adminSetNewTemplate(int pnFID00, String pvHERKU, int pnFC400, String pvLOGIN) {
		// TODO Auto-generated method stub
		return adminDao.adminSetNewTemplate(pnFID00, pvHERKU, pnFC400, pvLOGIN);
	}

	@Override
	public String getIpOfLocation(String ip) {
		// TODO Auto-generated method stub
		return adminDao.getIpOfLocation(ip);
	}

	@Override
	public List<ChartItemInfo2> reportWebDaily(Date pdDATEF, Date pdDATET, String pvLogin) {
		// TODO Auto-generated method stub
		return adminDao.reportWebDaily(pdDATEF, pdDATET, pvLogin);
	}

	@Override
	public int adminUpdateTabO100(int po100, int fc800, int fd000, String pvLogin) {
		// TODO Auto-generated method stub
		return adminDao.adminUpdateTabO100(po100, fc800, fd000, pvLogin);
	}

	@Override
	public List<BonEvoAccount> listOfTabAccounts(int fo100, String qv101, String pvLogin) {
		// TODO Auto-generated method stub
		return adminDao.listOfTabAccounts(fo100, qv101, pvLogin);
	}
	

}
