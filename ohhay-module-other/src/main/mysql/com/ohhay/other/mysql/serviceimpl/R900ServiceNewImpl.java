package com.ohhay.other.mysql.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.other.mysql.dao.R900DaoNew;
import com.ohhay.other.mysql.service.R900ServiceNew;
@Service(value = SpringBeanNames.SERVICE_NAME_R900_NEW)
@Scope("prototype")
public class R900ServiceNewImpl implements R900ServiceNew{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R900_NEW)
	private R900DaoNew r900DaoNew;

	@Override
	public List<ChartItemInfo2> reportTabR950V(int pnFo100, int pnRN905, int pnRN906, int pnINTER, Date pdFRDAT, Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.reportTabR950V(pnFo100, pnRN905, pnRN906, pnINTER, pdFRDAT, pdTODAT, pvLogin);
	}

	@Override
	public List<ChartItemInfo2> reportTabR950B(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.reportTabR950B(pnFO100, pnRN905, pnRN906, pvRV951, pnINTER, pdFRDAT, pdTODAT, pvLogin);
	}

	@Override
	public List<ChartItemInfo2> reportTabR950D(int pnFO100, int pnRN905, int pnRN906,  String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.reportTabR950D(pnFO100, pnRN905, pnRN906, pvRV951, pnINTER, pdFRDAT, pdTODAT, pvLogin);
	}

	@Override
	public List<ChartItemInfo2> reportTabR950L(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.reportTabR950L(pnFO100, pnRN905,pnRN906, pvRV951, pnINTER, pdFRDAT, pdTODAT, pvLogin);
	}

	@Override
	public List<ChartItemInfo2> reportTabR950S(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.reportTabR950S(pnFO100, pnRN905, pnRN906, pvRV951, pnINTER, pdFRDAT, pdTODAT, pvLogin);
	}

	@Override
	public List<ChartItemInfo2> reportTabR950J(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.reportTabR950J(pnFO100, pnRN905, pnRN906, pvRV951, pnINTER, pdFRDAT, pdTODAT, pvLogin);
	}


	@Override
	public List<ChartItemInfo2> reportTabR950U(int pnFO100, int pnRN905, int pnRN906, String pvRV951, int pnINTER, Date pdFRDAT,
			Date pdTODAT, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.reportTabR950U(pnFO100, pnRN905, pnRN906, pvRV951, pnINTER, pdFRDAT, pdTODAT, pvLogin);
	}

	

	@Override
	public int statGetCountTabR950(String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.statGetCountTabR950(pvLogin);
	}
	
	

	@Override
	public <T> List<T> cronjReportTabR900B(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.cronjReportTabR900B(mapping, pvLogin);
	}

	@Override
	public <T> List<T> cronjReportTabR900D(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.cronjReportTabR900D(mapping, pvLogin);
	}

	@Override
	public <T> List<T> cronjReportTabR900L(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.cronjReportTabR900L(mapping, pvLogin);
	}

	@Override
	public <T> List<T> cronjReportTabR900S(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.cronjReportTabR900S(mapping, pvLogin);
	}

	@Override
	public <T> List<T> cronjReportTabR900U(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.cronjReportTabR900U(mapping, pvLogin);
	}

	@Override
	public <T> List<T> cronjReportTabR900J(Class<T> mapping, String pvLogin) {
		// TODO Auto-generated method stub
		return r900DaoNew.cronjReportTabR900J(mapping, pvLogin);
	}
}