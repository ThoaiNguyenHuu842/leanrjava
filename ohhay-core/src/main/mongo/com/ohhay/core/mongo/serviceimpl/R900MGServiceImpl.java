package com.ohhay.core.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.O180;
import com.ohhay.core.entities.mongo.history.R900MG;
import com.ohhay.core.entities.mongo.history.R920MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.dao.R900MGDao;
import com.ohhay.core.mongo.service.R900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mysql.service.O180Service;
import com.ohhay.core.utils.ApplicationHelper;
@Service(value = SpringBeanNames.SERVICE_NAME_R900MG)
@Scope("prototype")
public class R900MGServiceImpl implements R900MGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R900MG)
	R900MGDao r900mgDao;
	@Autowired
	TemplateService templateService;

	@Override
	public int insertTabR900Vote(int fo100, int fo100Voted) {
		// TODO Auto-generated method stub
		return r900mgDao.insertTabR900Vote(fo100, fo100Voted);
	}

	@Override
	public int insertTabR900Bookmark(int fo100, int fo100Voted, String rv921, String rv922) {
		// TODO Auto-generated method stub
		return r900mgDao.insertTabR900Bookmark(fo100, fo100Voted, rv921, rv922);
	}

	@Override
	public int stornoTabR900Bookmark(int fo100, int fo100Bookmark) {
		// TODO Auto-generated method stub
		return r900mgDao.stornoTabR900Bookmark(fo100, fo100Bookmark);
	}

	@Override
	public String getAllHisotry(int fo100) {
		// TODO Auto-generated method stub
		return r900mgDao.getAllHisotry(fo100);
	}

	@Override
	public List<O180> findPeopleBookmarkMe(int fo100, int limit) {
		// TODO Auto-generated method stub
		List<O180> listO180s = new ArrayList<O180>();
		try{
			// load list M900
			O180Service o180Service = (O180Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O180);
			listO180s = o180Service.listOfTabO180C(fo100, 0, 1000, "");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return listO180s;
	}

	@Override
	public int insertTabR900Share(int fo100, int fo100s, int fo100f, String content) {
		// TODO Auto-generated method stub
		return r900mgDao.insertTabR900Share(fo100, fo100s, fo100f, content);
	}

	@Override
	public List<O180> findPeopleBookmarkMeNew(int fo100) {
		// TODO Auto-generated method stub
		List<O180> list = findPeopleBookmarkMe(fo100, 7);
		// empty list new
		TemplateService templateMGService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		R900MG r900mg = templateMGService.findDocumentById(fo100, fo100, R900MG.class);
		if(r900mg != null && r900mg.getListR920NEWmgs() != null){
			r900mg.getListR920NEWmgs().clear();
			templateMGService.saveDocument(fo100, r900mg, QbMongoCollectionsName.R900);
		}
		// find new bookmark
		/*
		 * TemplateService templateMGService = (TemplateService)
		 * ApplicationHelper
		 * .findBean(ApplicationConstant.SERVICE_NAME_TEMPLATE); R900MG r900mg =
		 * templateMGService .findDocumentById(fo100, R900MG.class); if (r900mg
		 * != null && r900mg.getListR920NEWmgs() != null) { for(M900MG
		 * m900mg:listM900mgs){ for(R920NEWMG r920newmg:
		 * r900mg.getListR920NEWmgs()) { if(m900mg.getId() ==
		 * r920newmg.getFo100()) m900mg.setMn911(1);//bookmark moi } } }
		 */
		return list;
	}

	@Override
	public List<M900MG> loadBookmark(int fo100, int limit) {
		// TODO Auto-generated method stub
		List<M900MG> listM900mgs = new ArrayList<M900MG>();
		try{
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			R900MG r900mg = templateMGService.findDocumentById(fo100, fo100, R900MG.class);
			if (r900mg != null) {
				// load list M900
				if(r900mg.getListR920mgs() != null)
				for (R920MG r920mg : r900mg.getListR920mgs()) {
					M900MG m900mg = templateMGService.findDocumentById(fo100, r920mg.getFo100(), M900MG.class);
					m900mg.setMv913(r920mg.getRl946String());//luu tam vao mv913
					m900mg.setMv916(r920mg.getRv922());//luu tam ghi chu vao mv916
					listM900mgs.add(m900mg);
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return listM900mgs;
	}

}
