package com.ohhay.piepme.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.T300PMGDao;
import com.ohhay.piepme.mongo.entities.other.T300PMG;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.service.T300PMGService;

/**
 * @author ThoaiNH
 * create Apr 8, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_T300P)
@Scope("prototype")
public class T300PMGserviceImpl implements T300PMGService{
	@Autowired
	private TemplateService templateService;
	@Autowired
	private T300PMGDao t300PmgDao;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Override
	public int insertT300(int pt300, int fo100, String tv301, String tv302, String otags) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
		try {
			T300PMG t300pmg = new T300PMG();
			t300pmg.setId((int)sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T300));
			t300pmg.setFo100(fo100);
			t300pmg.setTv301(tv301);
			t300pmg.setTv302(tv302);
			t300pmg.setTl346(new Date(adminPMGDao.getCurrentTime()));
			t300pmg.setTl348(new Date(adminPMGDao.getCurrentTime()));
			try {
				String tags[] = AESUtils.decrypt(otags).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
				List<Otag> listOtag = new ArrayList<Otag>();
				for (int i = 0; i < tags.length; i++)
				{
					if(tags[i].trim().length() > 0){
						Otag otag = new Otag();
						otag.setKey(tags[i].trim());
						otag.setKeyStem(ApplicationHelper.getStemOtag(tags[i].toLowerCase()));
						listOtag.add(otag);
					}
				}
				t300pmg.setListOtags(listOtag);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return templateService.saveDocument(fo100, t300pmg, QbMongoCollectionsName.T300);
		}
		catch (Exception e) {
			// TODO: handle exception
			return 0;
		} 
	
	}
	@Override
	public List<T300PMG> listOfTabT300(int fo100) {
		// TODO Auto-generated method stub
		return t300PmgDao.listOfTabT300(fo100);
	}

}
