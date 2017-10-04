package com.ohhay.web.mongo.core.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.ChartPieAnalyst;
import com.ohhay.web.core.mongo.dao.E400MGDao;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.web.E400MG;

/**
 * @author ThoaiNH
 * create Jan 15, 2016
 */
@Service(value = SpringBeanNames.SERVICE_NAME_E400MG)
@Scope("prototype")
public class E400MGServiceImpl implements E400MGService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_E400MG)
	E400MGDao e400mgDao;
	@Override
	public int updateSiteName(int fo100, int pe400, String ev405) {
		// TODO Auto-generated method stub
		return e400mgDao.updateSiteName(fo100, pe400, ev405);
	}
	/*
	 * ThoaiVt test
	 * create date 20/02/2016
	 * (non-Javadoc)
	 * @see com.ohhay.web.core.mongo.service.E400MGService#getListTemplateCMS()
	 */
	@Override
	public List<E400MG> getListTemplateCMS(int fo100,int offset,int limit) {
		// TODO Auto-generated method stub
		return e400mgDao.getListTemplateCMS(fo100, offset, limit);
	}
	@Override
	public List<E400MG> getListMysite(int fo100, String pvSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		return e400mgDao.getListMysite(fo100, pvSearch, offset, limit);
	}
	@Override
	public List<ChartPieAnalyst> getChartData(int fo100) {
		// TODO Auto-generated method stub
		return e400mgDao.getDataHightChart(fo100);
	}
	@Override
	public List<ChartPieAnalyst> getCharOfWebid(int fo100,int webid) {
		// TODO Auto-generated method stub
		return e400mgDao.getCharOfWebid(fo100, webid);
	}
	@Override
	public E400MG getStatisticWeb(int fo100) {
		// TODO Auto-generated method stub
		return e400mgDao.getStatisticWeb(fo100);
	}
	@Override
	public List<E400MG> getListAllsite(int fo100, int offset, int limit, String pvSearch) {
		// TODO Auto-generated method stub
		return e400mgDao.getListAllsite(fo100,offset,limit, pvSearch);
	}
	@Override
	public int getTotalHiddenComponent(int fo100,int idWeb) {
		// TODO Auto-generated method stub
		return e400mgDao.getTotalHiddenComponent(fo100,idWeb);
	}
	@Override
	public String listOfTabE400Graph(int fo100) {
		// TODO Auto-generated method stub
		return e400mgDao.listOfTabE400Graph(fo100);
	}
	
	
}
