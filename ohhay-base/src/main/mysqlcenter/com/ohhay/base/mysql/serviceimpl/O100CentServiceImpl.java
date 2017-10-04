package com.ohhay.base.mysql.serviceimpl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.mysql.dao.O100CentDao;
import com.ohhay.base.mysql.service.O100CentService;
import com.ohhay.base.util.AESCenterUtil;
@Service(value = SpringBeanNames.SERVICE_NAME_O100CENT)
@Scope("prototype")
public class O100CentServiceImpl implements O100CentService{
	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_O100CENT)
	private O100CentDao o100CentDao;
	@Override
	public int insertTabO100(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvLogin) {
		// TODO Auto-generated method stub
		return o100CentDao.insertTabO100(pnFO100, pvOV101, pvOV102, pvOV103, pvOV104, pvLogin);
	}
	@Override
	public String getValTabO100Web(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(o100CentDao.getValTabO100Web(fo100, pvLogin), fo100);
	}
	@Override
	public String getValTabO100Topic(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(o100CentDao.getValTabO100Topic(fo100, pvLogin), fo100);
	}
	@Override
	public String getValTabO100MySql(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(o100CentDao.getValTabO100MySql(fo100, pvLogin), fo100);
	}
	@Override
	public String getMoTabO100MySql(String ov101, String ov061, String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(o100CentDao.getMoTabO100MySql(ov101, ov061, pvLogin), ov101);
	}
	@Override
	public int insertTabO100EVO(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV105, String pvLogin) {
		// TODO Auto-generated method stub
		return o100CentDao.insertTabO100EVO(pnFO100, pvOV101, pvOV102, pvOV103, pvOV104, pvOV105, pvLogin);
	}
	@Override
	public String ohayInsertTabO100Bon(int po100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV105, Date pdOD114, double pnON115, String pvOV116, String pvOV117, String pvOV118, Date pdOD119, String pvOV130, String pvOV131, Date pdOD132, String pvLOGIN) {
		// TODO Auto-generated method stub
		return o100CentDao.ohayInsertTabO100Bon(po100, pvOV101, pvOV102, pvOV103, pvOV104, pvOV105, pdOD114, pnON115, pvOV116, pvOV117, pvOV118, pdOD119, pvOV130, pvOV131, pdOD132, pvLOGIN);
	}
	@Override
	public String getValTabO100Shop(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(o100CentDao.getValTabO100Shop(fo100, pvLogin), fo100);
	}
	@Override
	public String getValTabO100Piepme(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return AESCenterUtil.decrypt(o100CentDao.getValTabO100Piepme(fo100, pvLogin), fo100);
	}
	@Override
	public int insertTabO100Piep(int pnFO100, String pvOV101, String pvOV102, String pvOV103, String pvOV104, String pvOV110, String pvLOGIN) {
		// TODO Auto-generated method stub
		return o100CentDao.insertTabO100Piep(pnFO100, pvOV101, pvOV102, pvOV103, pvOV104, pvOV110, pvLOGIN);
	}
	@Override
	public int updateTabO100Piepme(int pnFO100, String pvOV126, String pvLOGIN) {
		// TODO Auto-generated method stub
		return o100CentDao.updateTabO100Piepme(pnFO100, pvOV126, pvLOGIN);
	}
	
}
