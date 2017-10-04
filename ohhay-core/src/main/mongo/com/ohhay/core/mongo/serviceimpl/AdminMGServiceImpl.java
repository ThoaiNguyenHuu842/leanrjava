package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.dao.AdminMGDao;
import com.ohhay.core.mongo.service.AdminMGService;
@Service(value = SpringBeanNames.SERVICE_NAME_ADMINMG)
@Scope("prototype")
public class AdminMGServiceImpl implements AdminMGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_ADMINMG)
	AdminMGDao adminMGDao;

	@Override
	public int getNewWebChildCn806(int fo100) {
		// TODO Auto-generated method stub
		return adminMGDao.getNewWebChildCn806(fo100);
	}

	@Override
	public String getThumbnailOfWeb(int fo100, int webId, String type) {
		// TODO Auto-generated method stub
		return adminMGDao.getThumbnailOfWeb(fo100, webId, type);
	}

	@Override
	public String getUserColor(int fo100) {
		// TODO Auto-generated method stub
		return adminMGDao.getUserColor(fo100);
	}

	@Override
	public int checkWebShortExists(int fo100, String webType, String refId) {
		// TODO Auto-generated method stub
		return adminMGDao.checkWebShortExists(fo100, webType, refId);
	}

	@Override
	public List<LanguageMG> getUserLanguage(int fo100) {
		// TODO Auto-generated method stub
		return adminMGDao.getUserLanguage(fo100);
	}

	@Override
	public List<ComtabItem> getAllCoponent(int fc800) {
		// TODO Auto-generated method stub
		return adminMGDao.getAllCoponent(fc800);
	}

	@Override
	public int getNewWebDraftChildCn806(int fo100) {
		// TODO Auto-generated method stub
		return adminMGDao.getNewWebDraftChildCn806(fo100);
	}
}
