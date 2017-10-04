package com.ohhay.other.mysql.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.oracle.dao.N100ORDao;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.O100;
import com.ohhay.other.entities.O100Das;
import com.ohhay.other.mysql.dao.O100Dao;
import com.ohhay.other.mysql.service.O100Service;
@Service(value = SpringBeanNames.SERVICE_NAME_O100)
@Scope("prototype")
public class O100ServiceImpl implements O100Service {
	private static Logger log = Logger.getLogger(O100Service.class);
	@Autowired
	@Qualifier(SpringBeanNames.REPOSITORY_NAME_O100)
	private O100Dao o100Dao;
	@Autowired
	private TemplateService templateService;
	@Override
	public List<O100> qhayListTabQ100(String ov103, String pvSearch,
			String pvLogin) {
		// TODO Auto-generated method stub
		return o100Dao.qhayListTabQ100(ov103, pvSearch, pvLogin);
	}


	@Override
	public int ohayInserttabO100(int po100, String ov101, String ov102, String ov103, String qv102, int fk100, int fn750, int fc500, int pnFC800,int fd000, String pvLogin, String uri) {
		// TODO Auto-generated method stub
		return o100Dao.ohayInserttabO100(po100, ov101, ov102, ov103, qv102, fk100, fn750, fc500, pnFC800, fd000,pvLogin, uri);
	}
	@Override
	public String updateTabO100Confirm(String pvOV102, int pnFO100, String pvLogin) {
		// TODO Auto-generated method stub
		return o100Dao.updateTabO100Confirm(pvOV102, pnFO100, pvLogin);
	}


	@Override
	public int stornoTabO100(int po100, String pvLogin) {
		// TODO Auto-generated method stub
		return o100Dao.stornoTabO100(po100, pvLogin);
	}


	@Override
	public int stornoTabO100Center(int po100, String pvLogin) {
		// TODO Auto-generated method stub
		return o100Dao.stornoTabO100Center(po100, pvLogin);
	}


	@Override
	public int ohayInsertTabO100Evo(int po100, String pvOV101, String pvOV102, String pvOV103, String pvOV108,  String pvQV102, int pnFK100, int pnFN750, int pnFD000, String pvLOGIN, String uri) {
		// TODO Auto-generated method stub
		return o100Dao.ohayInsertTabO100Evo(po100, pvOV101, pvOV102, pvOV103, pvOV108, pvQV102, pnFK100, pnFN750, pnFD000, pvLOGIN, uri);
	}


	@Override
	public int removeAccount(int po100) {
		// TODO Auto-generated method stub
		try {
			M900MG m900mg = templateService.findDocumentById(po100, po100, M900MG.class);
			stornoTabO100(po100, "");
			M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
			m900mgService.storNotTabM900(po100);
			// remove data center
			m900mgService.storNotTabM900Center(po100);
			stornoTabO100Center(po100, "");
			//remove oracle data
			N100ORDao n100Dao = (N100ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_N100OR);
			n100Dao.stornoTabN100(m900mg.getFn100(), m900mg.getMv903Decrypt());
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}


	@Override
	public int ohayInsertTabO100Bon(int po100, String pvOV101, String pvOV102, String pvOV103, Date pdOD107, String pvOV108, String pvNV109, String pvNV112, Date pdOD114, double pnON115, String pvOV116, String pvOV117, String pvOV118, Date pdOD119, String pvOV120, String pvOV121, String pvOV122, String pvOV123, String pvOV124, String pvOV125, String pvOV126, String pvOV127, String pvQV102, int pnFK100, int pnFN750, int pnFD000, String pvLOGIN, String uri) {
		// TODO Auto-generated method stub
		return o100Dao.ohayInsertTabO100Bon(po100, pvOV101, pvOV102, pvOV103, pdOD107, pvOV108, pvNV109, pvNV112, pdOD114, pnON115, pvOV116, pvOV117, pvOV118, pdOD119, pvOV120, pvOV121, pvOV122, pvOV123, pvOV124, pvOV125, pvOV126, pvOV127, pvQV102, pnFK100, pnFN750, pnFD000, pvLOGIN, uri);
	}


	@Override
	public List<O100Das> getListAccount(String pvSEARC, int pnPAYME, Date pdFRDAT, Date pdTODAT, int pnOFFSET, int pnLIMIT,
			int pnSORT, int pnDIRECTION, String pvLOGIN) {
		// TODO Auto-generated method stub
		return o100Dao.getListAccount(pvSEARC, pnPAYME, pdFRDAT, pdTODAT, pnOFFSET, pnLIMIT, pnSORT, pnDIRECTION, pvLOGIN);
	}


	@Override
	public List<O100> listOfTabO100Del(String pvLogin) {
		// TODO Auto-generated method stub
		return o100Dao.listOfTabO100Del(pvLogin);
	}


	@Override
	public int insertTabO100Piepme(int pnPO100, String pvOV101, String pvOV102, String pvOV103, String pvOV110, String pvNV101, int pnFN750, int pnFK100, String pvLOGIN, String uri) {
		// TODO Auto-generated method stub
		return o100Dao.insertTabO100Piepme(pnPO100, pvOV101, pvOV102, pvOV103, pvOV110, pvNV101, pnFN750, pnFK100, pvLOGIN, uri);
	}


	@Override
	public int updateTabO100Key(String pvOV110, String pvOV116, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return o100Dao.updateTabO100Key(pvOV110, pvOV116, fo100, pvLogin);
	}
	
}
