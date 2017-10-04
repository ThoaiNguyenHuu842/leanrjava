package com.ohhay.base.mysql.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.SpringBeanNames;
import com.ohhay.base.entities.R100CentP;
import com.ohhay.base.entities.R100CentP2017Dash;
import com.ohhay.base.entities.R100CentP2017Eng;
import com.ohhay.base.entities.R100CentP2017Lifo;
import com.ohhay.base.entities.R100CentP2017Piep;
import com.ohhay.base.entities.R100CentP2017Revi;
import com.ohhay.base.mysql.dao.R100CentDao;
import com.ohhay.base.mysql.service.R100CentService;
/**
 * @author ThoaiNH
 * create Feb 15, 2016
 */

@Service(value = SpringBeanNames.SERVICE_NAME_R100CENT)
@Scope("prototype")
public class R100CentServiceImpl implements R100CentService{
	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_R100CENT)
	private R100CentDao r100CentDao;
	
	@Override
	public int insertTabR100(String pvRV101, String pvRV103, String pvRV105, int pnFP100, int pnFO100V, int pnFO100K,
			String pvLOGIN) {
		// TODO Auto-generated method stub
		return r100CentDao.insertTabR100(pvRV101, pvRV103, pvRV105, pnFP100, pnFO100V, pnFO100K, pvLOGIN);
	}

	@Override
	public  List<R100CentP> listOfTabR100Pie(int pnFP100, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Pie(pnFP100, pvLogin);
	}

	@Override
	public List<R100CentP> listOftabR100Dev(int fo100k, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOftabR100Dev(fo100k, pvLogin);
	}

	@Override
	public List<R100CentP> listOfTabR100Wee(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Wee(fo100, pdFRDAT, pdTODAT, pvLogin);
	}

	@Override
	public List<R100CentP> listOfTabR100Hrs(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Hrs(fo100, pdFRDAT, pdTODAT, pvLogin);
	}

	@Override
	public List<R100CentP> listOfTabR100Vlf(Date pdFRDAT, Date pdTODAT, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Vlf(pdFRDAT, pdTODAT, limit, pvLogin);
	}

	@Override
	public List<R100CentP> listOfTabR100Det(int fo100, Date pdFRDAT, Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Det(fo100, pdFRDAT, pdTODAT, pvLogin);
	}

	@Override
	public List<R100CentP> listOfTabR100Sen(Date pdFRDAT, Date pdTODAT, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Sen(pdFRDAT, pdTODAT, limit, pvLogin);
	}

	@Override
	public List<R100CentP> listOfTabR100Vie(int fo100, Date pdFRDAT, Date pdTODAT, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Vie(fo100, pdFRDAT, pdTODAT, limit, pvLogin);
	}

	@Override
	public int insertTabR1002017(String pvRV101, String pvRV103, String pvRV105, String pvRV106, int pnRN107, int pnFP100, int pnFO100V, int pnFO100K, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.insertTabR1002017(pvRV101, pvRV103, pvRV105, pvRV106, pnRN107, pnFP100, pnFO100V, pnFO100K, pvLogin);
	}

	@Override
	public List<R100CentP2017Dash> listOfTabR100Dash(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Dash(fo100, pvLogin);
	}

	@Override
	public List<R100CentP2017Eng> listOfTabR100Eng(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Eng(fo100, offset, limit, pvLogin);
	}

	@Override
	public List<R100CentP2017Revi> listOfTabR100Revi(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Revi(fo100, offset, limit, pvLogin);
	}

	@Override
	public List<R100CentP2017Lifo> listOfTabR100Lifo(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Lifo(fo100, offset, limit, pvLogin);
	}

	@Override
	public List<R100CentP2017Piep> listOfTabR100Piep(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.listOfTabR100Piep(fo100, pvLogin);
	}

	@Override
	public int insertTabR1002017dis(String pvRV101, String pvRV103, String pvRV105, String pvRV106, int pnRN107, double pnRN109, int pnFP100, int pnFO100V, int pnFO100K, String pvLogin) {
		// TODO Auto-generated method stub
		return r100CentDao.insertTabR1002017dis(pvRV101, pvRV103, pvRV105, pvRV106, pnRN107, pnRN109, pnFP100, pnFO100V, pnFO100K, pvLogin);
	}


}
