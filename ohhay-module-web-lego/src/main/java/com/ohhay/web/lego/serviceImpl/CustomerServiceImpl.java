package com.ohhay.web.lego.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.E150D;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.other.entities.O100;
import com.ohhay.other.mysql.dao.E100Dao;
import com.ohhay.other.mysql.dao.E150Dao;
import com.ohhay.other.mysql.dao.O100Dao;
import com.ohhay.web.lego.entities.mongo.web.E150MG;
import com.ohhay.web.lego.entities.mongo.web.E160MG;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.CustomerService;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThoaiNH create Feb 25, 2016 all function of designer packet
 */
@Service(value = SpringBeanNames.SERVICE_NAME_CUSTOMER)
@Scope("prototype")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private O100Dao o100Dao;
	@Autowired
	private E100Dao e100Dao;
	@Autowired
	private E150Dao e150Dao;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	SequenceService sequenceService;

	@Override
	public List<O100> ohayListTabO100D(int fo100, String ev151, String pvSEARC, int pnOFFSET, int pnLIMIT,
			String pvLogin) {
		// TODO Auto-generated method stub
		return o100Dao.ohayListTabO100D(fo100, ev151, pvSEARC, pnOFFSET, pnLIMIT, pvLogin);
	}

	@Override
	public int insertTabE100(int pnFO100C, int pnFO100D, String pvLogin) {
		// TODO Auto-generated method stub
		return e100Dao.insertTabE100(pnFO100C, pnFO100D, pvLogin);
	}

	@Override
	public int insertTabE150(int pnPE150, String pvEV151, String pvEV152, String pvEV153, String pvEV154, int pnFE100,
			String pvLOGIN) {
		// TODO Auto-generated method stub
		return e150Dao.insertTabE150(pnPE150, pvEV151, pvEV152, pvEV153, pvEV154, pnFE100, pvLOGIN);
	}

	@Override
	public int shareForDes(int fo100c, int fo100d, long webID, String boxIDPattern, String note, String pvLogin) {
		// TODO Auto-generated method stub
		// int fe100 = insertTabE100(fo100c, fo100d, pvLogin);
		// insertTabE150(0, String.valueOf(webID), boxIDPattern, null, note,
		// fe100, pvLogin);
		// int webIdNew = webLegoService.createBytemplate(fo100d, (int)webID,
		// false);
		// int kq2 = templateService.updateOneField(fo100d, E400MG.class,
		// webIdNew, QbMongoFiledsName.EN402, 3, null);
		// return kq2;
		E150MG e150mg = new E150MG();
		e150mg.setFo100c(fo100c);
		e150mg.setFo100d(fo100d);
		e150mg.setEv151(note);
		e150mg.setFe400(webID);
		e150mg.setEv152("NEW");
		e150mg.setEl156(new Date());
		e150mg.setEl158(new Date());
		try {
			e150mg.setId((int) sequenceService.getNextSequenceId(fo100c, QbMongoCollectionsName.E150));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int kq = templateService.saveDocument(fo100c, e150mg, QbMongoCollectionsName.E150);
		return ((kq == 1) ? e150mg.getId() : kq);

	}

	@Override
	public List<E150D> listOfTabE150C(int fo100, String ev151, String pvLogin) {
		// TODO Auto-generated method stub
		return e150Dao.listOfTabE150C(fo100, ev151, pvLogin);
	}

	@Override
	public int stornoTabE150(int fe150, String pvLogin) {
		// TODO Auto-generated method stub
		return e150Dao.stornoTabE150(fe150, pvLogin);
	}

	@Override
	public int confirmTabE150(int fo100Cus, int fo100Des, int pe150, int fe400Cus, String pvLogin) {
		// TODO Auto-generated method stub
		E400MG e400mgBackup = templateService.findDocument(fo100Des, E400MG.class,
				new QbCriteria(QbMongoFiledsName.FE400, fe400Cus), new QbCriteria(QbMongoFiledsName.EV406, "PENDING"));
		// update customer site
		webLegoService.replaceWebToWeb(fo100Cus, fo100Des, fe400Cus, (int) e400mgBackup.getId());
		// update backup sttt
		templateService.updateOneField(fo100Des, E400MG.class, QbMongoFiledsName.EV406, "APPROVED", null,
				new QbCriteria(QbMongoFiledsName.FE400, fe400Cus), new QbCriteria(QbMongoFiledsName.EV406, "PENDING"));
		return e150Dao.confirmTabE150(pe150, pvLogin);
	}

	@Override
	public int applySiteOfDes(int fo100Cus, int fo100Des, int fe400Cus, int fe400Des, int fe150) {
		// TODO Auto-generated method stub
		try{
			webLegoService.replaceWebToWeb(fo100Cus, fo100Des, fe400Cus, fe400Des);
			templateService.updateOneField(fo100Cus, E150MG.class, fe150, QbMongoFiledsName.EV152, E150MG.CON, null);
			templateService.updateOneField(fo100Cus, E150MG.class, fe150, QbMongoFiledsName.EL160, new Date(), null);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int rejectSiteOfDes(int fo100, int fe150, String ev161) {
		// TODO Auto-generated method stub
		try{
			E150MG e150mg = templateService.findDocumentById(fo100, fe150, E150MG.class);
			e150mg.setEv152(E150MG.DEC);
			e150mg.setEl156(new Date());
			E160MG e160mg = new E160MG();
			e160mg.setFe200(e150mg.getE200mg().getId());
			e160mg.setFe400(e150mg.getFe400d());
			e160mg.setEl168(new Date());
			e160mg.setEv161(ev161);
			List<E160MG> listE160 = e150mg.getListRejection();
			if(listE160 == null){
				listE160 = new ArrayList<E160MG>(); 
			}
			listE160.add(e160mg);
			e150mg.setListRejection(listE160);
			return templateService.saveDocument(fo100, e150mg, QbMongoCollectionsName.E150);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
