package com.ohhay.web.core.mongo.service;

import java.util.List;

import com.ohhay.core.criteria.ChartPieAnalyst;
import com.ohhay.web.lego.entities.mongo.base.web.OhhayLegoWebBase;
import com.ohhay.web.lego.entities.mongo.web.E400MG;

/**
 * @author ThoaiNH
 * create Jan 15, 2016
 */
public interface E400MGService {
	int updateSiteName(int fo100, int pe400, String ev405);
	List<E400MG> getListTemplateCMS(int fo100,int offset,int limit);
	List<E400MG> getListMysite(int fo100, String pvSearch, int offset, int limit);
	List<E400MG> getListAllsite(int fo100,int offset,int limit, String pvSearch);
	List<ChartPieAnalyst> getChartData(int fo100);
	List<ChartPieAnalyst> getCharOfWebid(int fo100,int webid);
	E400MG getStatisticWeb(int fo100);
	int getTotalHiddenComponent(int fo100,int idWeb);
	String listOfTabE400Graph(int fo100);
}
