package com.ohhay.web.mongo.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.DomainCriteria;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.C400;
import com.ohhay.web.core.entities.mongo.web.A950MG;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.L400MG;
import com.ohhay.web.core.entities.mongo.web.T400MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.webchild.T500MG;
import com.ohhay.web.core.mongo.dao.A950MGDao;
import com.ohhay.web.core.mongo.dao.WebToolDao;
import com.ohhay.web.core.mongo.service.A950MGService;
import com.ohhay.web.core.mongo.service.WebToolService;
import com.ohhay.web.core.mysql.dao.L400Dao;
import com.ohhay.web.core.mysql.dao.T400Dao;
@Service(value = SpringBeanNames.SERVICE_NAME_WEBTOOLS)
@Scope("prototype")
public class WebToolServiceImpl implements WebToolService {
	@Autowired
	TemplateService templateService;
	@Autowired
	WebToolDao webToolDao;
	@Override
	public List<String> getAllWebUrls(int fo100) {
		// TODO Auto-generated method stub
		List<String> listUrls = new ArrayList<String>();
		C400MG c400mg = templateService.findDocument(fo100, C400MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		listUrls.add(c400mg.getUrl());
		List<L400MG> listL400MGs = templateService.findDocuments(fo100, L400MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		for(L400MG l400mg: listL400MGs)
			listUrls.add(l400mg.getUrl());
		List<C500MG> listC500MGs = templateService.findDocuments(fo100, C500MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		for(C500MG c500mg: listC500MGs)
			listUrls.add(c500mg.getUrl());
		return listUrls;
	}
	@Override
	public int getTotalWebLink(int fo100) {
		// TODO Auto-generated method stub
		return webToolDao.getTotalWebOfUser(fo100);
	}
	@Override
	public List<String> getAllWebLink(int fo100) {
		// TODO Auto-generated method stub
		List<String> listUrls = new ArrayList<String>();
		C400MG c400mg = templateService.findDocument(fo100, C400MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		listUrls.add(c400mg.getUrl());
		List<L400MG> listL400MGs = templateService.findDocuments(fo100, L400MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		for(L400MG l400mg: listL400MGs)
			listUrls.add(l400mg.getUrl());
		List<C500MG> listC500MGs = templateService.findDocuments(fo100, C500MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		for(C500MG c500mg: listC500MGs)
			listUrls.add(c500mg.getUrl());
		T400MG t400mg = templateService.findDocument(fo100, T400MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		listUrls.add(t400mg.getUrl());
		List<T500MG> listT500MGs = templateService.findDocuments(fo100, T500MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		for(T500MG t500mg: listT500MGs)
			listUrls.add(t500mg.getUrl());
		return listUrls;
	}
}
