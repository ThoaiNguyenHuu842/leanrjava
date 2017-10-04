package com.ohhay.core.oracle.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V500OR;
import com.ohhay.core.oracle.dao.V500ORDao;
import com.ohhay.core.oracle.service.V500ORService;
@Service(value = SpringBeanNames.SERVICE_NAME_V500OR)
@Scope("prototype")
public class V500ORServiceImpl implements V500ORService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V500OR)
	private V500ORDao v500orDao;
	@Override
	public List<V500OR> listOfTabV500(String pvVV503, String pvNV752, String pvLogin) {
		// TODO Auto-generated method stub
		return v500orDao.listOfTabV500(pvVV503, pvNV752, pvLogin);
	}
	@Override
	public int updateTabV500EVO(String pvNV106, Date pdND114, double pnNN115, String pvNV126, String pvNV127, String pvNV128, Date pvND129, String pvNV130, String pvPROID) {
		// TODO Auto-generated method stub
		return v500orDao.updateTabV500EVO(pvNV106, pdND114, pnNN115, pvNV126, pvNV127, pvNV128, pvND129, pvNV130, pvPROID);
	}
	@Override
	public int updateTabV500Vnd(double pnNN115, String pvNV126, String pvPROID, String pvLogin) {
		// TODO Auto-generated method stub
		return v500orDao.updateTabV500Vnd(pnNN115, pvNV126, pvPROID, pvLogin);
	}
	
}
