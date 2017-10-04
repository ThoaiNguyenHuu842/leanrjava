package com.ohhay.web.lego.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.E100D;
import com.ohhay.core.entities.E150D;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.other.mysql.dao.E100Dao;
import com.ohhay.other.mysql.dao.E150Dao;
import com.ohhay.other.mysql.dao.O100Dao;
import com.ohhay.web.lego.entities.mongo.web.E150MG;
import com.ohhay.web.lego.entities.mongo.web.E200MG;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.DesignerService;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH
 * create Feb 25, 2016
 * all function of designer packet
 */
@Service(value = SpringBeanNames.SERVICE_NAME_DESIGNER)
@Scope("prototype")
public class DesignerServiceImpl implements DesignerService{
	@Autowired
	private WebLegoService webLegoService;
	@Autowired
	private O100Dao o100Dao;
	@Autowired
	private E100Dao e100Dao;
	@Autowired
	private E150Dao e150Dao;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SequenceService SequenceService;
	@Override
	public int updateTabO100D(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return o100Dao.updateTabO100D(fo100, pvLogin);
	}

	@Override
	public List<E100D> listOfTabE100D(int pnFO100, String pvLogin) {
		// TODO Auto-generated method stub
		return e100Dao.listOfTabE100D(pnFO100, pvLogin);
	}

	@Override
	public List<E150D> listOfTabE150D(int pnFE100, String pvLogin) {
		// TODO Auto-generated method stub
		return e150Dao.listOfTabE150D(pnFE100, pvLogin);
	}

	@Override
	public int finishTabE150(int fo100Des, int pe150, int fe400Cus, int fe400Backup, String pvLogin) {
		// TODO Auto-generated method stub
		//chuyen trang thai nhung backup cu thanh new
		templateService.updateOneField(fo100Des, E400MG.class, QbMongoFiledsName.EV406, "", null, 
										new QbCriteria(QbMongoFiledsName.FE400, fe400Cus),
										new QbCriteria(QbMongoFiledsName.EN402,4));
		//cap nhat trang thai backup
		templateService.updateOneField(fo100Des, E400MG.class, fe400Backup, QbMongoFiledsName.EV406 , "PENDING", null);
		return e150Dao.finishTabE150(pe150, String.valueOf(fe400Backup), pvLogin);
	}

	@Override
	public int updateVersionSite(int fo100, int fe400c) {
		// TODO Auto-generated method stub
		templateService.removeDocuments(fo100, E400MG.class, new QbCriteria(QbMongoFiledsName.FE400, fe400c),
				new QbCriteria(QbMongoFiledsName.EN402,3));
		int webIdNew = webLegoService.createBytemplate(fo100, fe400c, false);
		templateService.updateOneField(fo100, E400MG.class, webIdNew, QbMongoFiledsName.EN402, 3, null);
		templateService.updateOneField(fo100, E150MG.class, QbMongoFiledsName.FE400D, webIdNew, null, new QbCriteria(QbMongoFiledsName.FE400, fe400c));
		return webIdNew;
	}

	@Override
	public String submitToCustomer(int fo100, int fe400s, String ev202, int fe150) {
		// TODO Auto-generated method stub
		try {
			//clone site
			int webId = webLegoService.createBytemplate(fo100, fe400s, false);
			//Tạo E200MG luu xuống mongoDB
			E200MG e200mg = new E200MG();
			e200mg.setFo100(fo100);
			e200mg.setEl206(new Date());
			e200mg.setEl208(new Date());
			e200mg.setEl209(new Date());
			e200mg.setEv202(ev202);
			e200mg.setEv201("NEW");
			e200mg.setFe400(webId);
			e200mg.setFe150(fe150);
			e200mg.setId((int) SequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E200));
			templateService.saveDocument(fo100, e200mg, QbMongoCollectionsName.E200);
			templateService.updateOneField(fo100, E150MG.class, QbMongoFiledsName.EV152, "WAI", null, new QbCriteria(QbMongoFiledsName.ID, fe150));
			templateService.updateOneField(fo100, E150MG.class, QbMongoFiledsName.E200, e200mg, null, new QbCriteria(QbMongoFiledsName.ID, fe150));
			SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM/yyyy hh:mm")); 
			String el208String = dateFormat.format(e200mg.getEl208());
			return el208String;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
