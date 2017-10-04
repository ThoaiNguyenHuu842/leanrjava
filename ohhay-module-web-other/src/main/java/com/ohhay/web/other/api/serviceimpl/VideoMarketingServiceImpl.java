package com.ohhay.web.other.api.serviceimpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M940MG;
import com.ohhay.core.entities.mongo.videomarketing.V910MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.WebLandingService;
import com.ohhay.web.core.entities.mongo.web.L400MG;
import com.ohhay.web.other.api.service.VideoMarketingService;
import com.ohhay.web.other.mongo.service.V910MGService;
/**
 * @author ThoaiNH
 *
 */
@Service(value = SpringBeanNames.SERVICE_NAME_VIDEO_MARKETING)
@Scope("prototype")
public class VideoMarketingServiceImpl implements VideoMarketingService {
	private static Logger log = Logger.getLogger(VideoMarketingServiceImpl.class);
	@Autowired
	TemplateService templateService;
	@Autowired
	M900MGService m900mgService;
	@Autowired
	V910MGService v910mgService;
	@Override
	public int changeTemplate(int fo100, int fl400, int fv910, int fc800, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1) create new template
			 */
			WebLandingService webLandingService = (WebLandingService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_WEBLANDING);
			int fl400New = webLandingService
					.createLandingPage(fo100, pvLogin, fc800); 
			if (fl400New > 0) {
				/*
				 * 2) remove old video page
				 */
				templateService.removeDocuments(fo100,L400MG.class, new QbCriteria(QbMongoFiledsName.CV805,ApplicationConstant.OHHAY_VIDEOMARKETING_CV805), 
															  new QbCriteria(QbMongoFiledsName.FO100, fo100),
															  new QbCriteria(QbMongoFiledsName.ID,fl400));
				/*
				 * 3) set new webid v910mg
				 */
				return templateService.updateOneField(fo100,V910MG.class, fv910, QbMongoFiledsName.WEBID, fl400New, "VL948");
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public int createWebVideoMarketing(int fo100, int fl800,  String menuName,String menuIcon,  String pvLogin) {
		// TODO Auto-generated method stub
		int webId = 0;
		int kq = 0;
		// create new web video
		WebLandingService webLandingService = (WebLandingService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_WEBLANDING);
		webId = webLandingService.createLandingPage(fo100, pvLogin, fl800);
		if (webId > 0) {
			SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			try {
				long newId = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.V910);
				V910MG v910mg = new V910MG(newId, fo100, webId, menuName,menuIcon,new ArrayList<M940MG>());
				kq = templateService.saveDocument(fo100,v910mg, QbMongoCollectionsName.V910);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kq;
	}

	@Override
	public int updateVideoNew(int fo100, int pv910,M940MG m940mg) {
		// TODO Auto-generated method stub
		try {
			int kq = 0;
			V910MG v910mg = templateService
					.findDocument(fo100, V910MG.class, new QbCriteria(QbMongoFiledsName.ID, pv910), new QbCriteria(QbMongoFiledsName.FO100,fo100));
			if (v910mg != null) {
				/*
				 * 1.1) when insert new
				 */
				if (m940mg.getId() == 0) {
					int newIndexId = v910mg.getVn914();
					int newID = v910mg.getVn912();
					m940mg.setMn944(newIndexId);
					m940mg.setId(newID);
					v910mg.getListM940mgs().add(m940mg);
					//update field v910
					v910mg.setVn912(newID+1);
					v910mg.setVn913(v910mg.getVn913()+1);
					v910mg.setVn914(newIndexId+1);
				}
				/*
				 * 1.2) when update
				 */
				else {
					log.info("update video:" + m940mg.getId());
					for (M940MG m940mg2 : v910mg.getListM940mgs()) {
						if (m940mg.getId() == m940mg2.getId()) {
							m940mg2.setMv941(m940mg.getMv941());
							m940mg2.setMv942(m940mg.getMv942());
							m940mg2.setMv943(m940mg.getMv943());
							m940mg2.setMn945(m940mg.getMn945());
							m940mg2.setMv946(m940mg.getMv946());
							m940mg2.setMv947(m940mg.getMv947());
							m940mg2.setMn948(m940mg.getMn948());
							break;
						}
					}
				}
				kq = templateService.saveDocument(fo100, v910mg, QbMongoCollectionsName.V910);
			}
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteVideoNew(int fo100, int pv910, int videoId) {
		// TODO Auto-generated method stub
		return v910mgService.deleteVideo(fo100, pv910, videoId);
	}

	@Override
	public int changeIndexNew(int fo100, int pv910, int videoId1, int index1, int videoId2, int index2) {
		// TODO Auto-generated method stub
		try {
			v910mgService.changeM940Index(fo100,pv910,videoId1, index2);
			v910mgService.changeM940Index(fo100,pv910,videoId2, index1);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
