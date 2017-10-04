package com.ohhay.piepme.mongo.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.piepme.mongo.dao.N900PMGDao;
import com.ohhay.piepme.mongo.entities.notification.N900PMG;
import com.ohhay.piepme.mongo.service.N900PMGService;

/**
 * @author ThoaiNH
 * create Jun 20, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_N900P)
@Scope("prototype")
public class N900PMGServiceImpl implements N900PMGService{
	@Autowired
	private TemplateService templateService;
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N900P)
	private N900PMGDao n900pmgDao;

	@Override
	public int updateNotification(int fo100, String key, int status) {
		// TODO Auto-generated method stub
		if(status == 0 || status == 1){
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N900PMG n900pmg = templateService.findDocumentById(fo100, fo100, N900PMG.class);
			if(n900pmg == null)
			{
				n900pmg = new N900PMG();
				Map<String, Integer> mapNotification = new HashMap<String, Integer>();
				n900pmg.setId(fo100);
				n900pmg.setMapNotification(mapNotification);
			}
			else {
				//k can update db
				if(n900pmg.getMapNotification().get(key.trim()) != null)
				{
					if(n900pmg.getMapNotification().get(key.trim()) == status)
						return 1;
				}
			}
			n900pmg.getMapNotification().put(key.trim(), status);
			return templateService.saveDocument(fo100, n900pmg, QbMongoCollectionsName.N900);
		}
		return 0;
	}

	@Override
	public int updateNotificationMulti(int fo100, Map<String, Integer> objects) {
		// TODO Auto-generated method stub
		for (Map.Entry<String, Integer> entry : objects.entrySet())
		   updateNotification(fo100, entry.getKey(), entry.getValue());
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.ohhay.piepme.mongo.service.N900PMGService#updateNotificationV2(int, int, java.lang.String, int)
	 */
	@Override
	public int updateNotificationV2(int fo100, int fo100f, String objectName, int status) {
		// TODO Auto-generated method stub
		return n900pmgDao.updateNotification(fo100, fo100f, objectName, status);
	}
	
}
