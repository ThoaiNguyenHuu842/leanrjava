package com.ohhay.web.mongo.core.serviceimpl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RequestParamRule;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.GMapCriteria;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.L400MG;
import com.ohhay.web.core.entities.mongo.web.T400MG;
import com.ohhay.web.core.entities.mongo.web.V400MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.webchild.T500MG;
import com.ohhay.web.core.load.util.AbstractEditor;
import com.ohhay.web.core.mongo.dao.C900MGDao;
import com.ohhay.web.core.mongo.service.C900MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_C900MG)
@Scope("prototype")
public class C900MGServiceImpl extends AbstractEditor implements C900MGService {
	private static Logger log = Logger.getLogger(C900MGServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_C900MG)
	C900MGDao c900mgDao;
	@Autowired
	TemplateService templateService;
	@Override
	public int deleteC400(int fo100) {
		// TODO Auto-generated method stub
		return c900mgDao.deleteC400(fo100);
	}

	@Override
	public <T> int updateEleme(int id, int indexPart, int indexEleme, String fieldUpdate, Object value, Class<T> mClass, int fo100, int fo100i) {
		// TODO Auto-generated method stub
		log.info("---updateEleme:" + id + "," + indexPart + ","
				+ indexEleme + "," + fieldUpdate + "," + value + "," + mClass
				+ "," + fo100 + "," + fo100i);
		return c900mgDao.updateEleme(id, indexPart, indexEleme, fieldUpdate, value, mClass, fo100, fo100i);
	}

	@Override
	public <T> int updateEleme100(String webId, String languageCode, int indexProperties, String fieldName, Object value, Class<T> mClass, int fo100, int fo100i) {
		// TODO Auto-generated method stub
		log.info("--updateEleme100:" + webId + "," + languageCode
				+ "," + indexProperties + "," + fieldName + "," + value + "," + mClass + ","
				+ fo100 + "," + fo100i);
		return c900mgDao.updateEleme100(webId, languageCode, indexProperties,fieldName, value, mClass, fo100, fo100i);
	}

	@Override
	public <T> int updatePart(int id, int indexPart, String fieldUpdate, Object value, Class<T> mClass, int fo100, int fo100i) {
		// TODO Auto-generated method stub
		return c900mgDao.updatePart(id, indexPart, fieldUpdate, value, mClass, fo100, fo100i);
	}

	@Override
	public int updateWebColorByUrl(String url, int fo100, String newColor) {
		// TODO Auto-generated method stub
		/*
		 * 1) get all prameters
		 */
		try {
			int kq  = 0;
			String baseUrl = new String(url.substring(0,url.indexOf("?")));
			log.info("--url:"+url);
			log.info("--base url:"+baseUrl);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			Map<String, String> params = new HashMap<>();
			List<NameValuePair> result;
			result = URLEncodedUtils.parse(new URI(url), "utf8");
			for (NameValuePair nvp : result) {
				params.put(nvp.getName(), nvp.getValue());
			}
			if(params.size() > 0)
			{
				if(params.get(RequestParamRule.PARAM_EDIT_MODE)!= null && params.get(RequestParamRule.PARAM_EDIT_MODE).equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_ELEMENT) || 
						params.get(RequestParamRule.PARAM_EDIT_MODE).equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_BOX))
				{
					/*
					 * 2) web update case
					 */
					if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_VIDEOMARKETING) > 0)
					{
						log.info("---update video marketing");
						kq = templateService.updateOneField(fo100, V400MG.class, QbMongoFiledsName.FO100, fo100, QbMongoFiledsName.CV807, newColor, null);
					}
					else if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_CHILD) > 0)
					{
						 int webChildID = Integer.parseInt(baseUrl.substring(baseUrl.indexOf(RequestParamRule.WEB_REQUEST_PATH_CHILD+"-")+6, baseUrl.length()));
						 if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_DRAFT) > 0)
						 {
							 log.info("--web draft child id:"+webChildID);
							 kq = templateService.updateOneField(fo100, T500MG.class, webChildID, QbMongoFiledsName.CV807, newColor, null);
						 }
						 else
						 {
							 log.info("--web child id:"+webChildID);
							 kq = templateService.updateOneField(fo100, C500MG.class, webChildID, QbMongoFiledsName.CV807, newColor, null);
							 if(kq > 0)
								onEditWebSuccess(C500MG.class,webChildID,null,fo100);
						 }
					}
					else if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_DRAFT) > 0)
					{
						log.info("--update web draft");
						kq = templateService.updateOneField(fo100, T400MG.class, QbMongoFiledsName.FO100, fo100, QbMongoFiledsName.CV807, newColor, null);
					}
					else if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_LANDING) > 0)
					{
						int landingID = Integer.parseInt(baseUrl.substring(baseUrl.indexOf(RequestParamRule.WEB_REQUEST_PATH_LANDING+"-")+8, baseUrl.length()));
						log.info("--web landing id:"+landingID);
					    kq = templateService.updateOneField(fo100, L400MG.class, landingID, QbMongoFiledsName.CV807, newColor, null);
					}
					else
					{
						log.info("---udpate web home");
						kq = templateService.updateOneField(fo100, C400MG.class, QbMongoFiledsName.FO100, fo100, QbMongoFiledsName.CV807, newColor, null);
						C400MG c400mg = templateService.findDocument(fo100, C400MG.class,QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.FO100, fo100));
						if(kq > 0)
							onEditWebSuccess(C400MG.class,c400mg.getId(),null,fo100);
					}
				}
			}
			return kq;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public OhhayWebBase getWebByUrl(String url, int fo100) {
		// TODO Auto-generated method stub
		/*
		 * 1) get all prameters
		 */
		try {
			String color = null;
			String baseUrl = new String(url.substring(0,url.indexOf("?")));
			log.info("--url:"+url);
			log.info("--base url:"+baseUrl);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			Map<String, String> params = new HashMap<>();
			List<NameValuePair> result;
			result = URLEncodedUtils.parse(new URI(url), "utf8");
			for (NameValuePair nvp : result) {
				params.put(nvp.getName(), nvp.getValue());
			}
			if(params.size() > 0)
			{
				if(params.get(RequestParamRule.PARAM_EDIT_MODE)!= null && params.get(RequestParamRule.PARAM_EDIT_MODE).equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_ELEMENT) || 
						params.get(RequestParamRule.PARAM_EDIT_MODE).equals(RequestParamRule.WEB_PARAM_EDIT_VALUE_BOX))
				{
					/*
					 * 2) web update case
					 */
					if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_VIDEOMARKETING) > 0)
					{
						log.info("---get video marketing");
						V400MG v400mg = templateService.findDocument(fo100, V400MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
						if(v400mg != null)
							return v400mg;
					}
					else if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_CHILD) > 0)
					{
						 int webChildID = Integer.parseInt(baseUrl.substring(baseUrl.indexOf(RequestParamRule.WEB_REQUEST_PATH_CHILD+"-")+6, baseUrl.length()));
						 if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_DRAFT) > 0)
						 {
							 log.info("--web draft child id:"+webChildID);
							 T500MG t500mg = templateService.findDocumentById(fo100, webChildID, T500MG.class);
							 if(t500mg != null)
									return t500mg;
						 }
						 else
						 {
							 log.info("--web child id:"+webChildID);
							 C500MG c500mg = templateService.findDocumentById(fo100, webChildID, C500MG.class);
							 if(c500mg != null)
									return c500mg;
						 }
					}
					else if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_DRAFT) > 0)
					{
						log.info("--get web draft");
						T400MG t400mg = templateService.findDocument(fo100, T400MG.class,new QbCriteria(QbMongoFiledsName.FO100, fo100));
						 if(t400mg != null)
							 return t400mg;
					}
					else if(url.indexOf(RequestParamRule.WEB_REQUEST_PATH_LANDING) > 0)
					{
						int landingID = Integer.parseInt(baseUrl.substring(baseUrl.indexOf(RequestParamRule.WEB_REQUEST_PATH_LANDING+"-")+8, baseUrl.length()));
						log.info("--web landing id:"+landingID);
						 L400MG l500mg = templateService.findDocumentById(fo100, landingID, L400MG.class);
						 if(l500mg != null)
							 return l500mg;
					}
					else
					{
						log.info("---get web home");
						C400MG c400mg = templateService.findDocument(fo100, C400MG.class,new QbCriteria(QbMongoFiledsName.FO100, fo100));
						 if(c400mg != null)
							 return c400mg;
					}
				}
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param gMapCriteria
	 * @param mClass
	 * @param fo100 fo100 of user login
	 * @param fo100i fo100 set to know update db center or db user
	 * @return
	 */
	@Override
	public <T> OhhayWebBase saveGmap(GMapCriteria gMapCriteria, Class<T> mClass, int fo100, int fo100i) {
		// TODO Auto-generated method stub
		if(fo100 > 0){
			OhhayWebBase ohhayWebBase = (OhhayWebBase) templateService.findDocumentById(fo100, gMapCriteria.getWebId(), mClass);
			updatePart(gMapCriteria.getWebId(), gMapCriteria.getIndexPart(), "GMAP.GN923", gMapCriteria.getLa(), mClass,fo100, fo100i);
			updatePart(gMapCriteria.getWebId(), gMapCriteria.getIndexPart(), "GMAP.GN924", gMapCriteria.getLng(), mClass, fo100, fo100i);
			updatePart(gMapCriteria.getWebId(), gMapCriteria.getIndexPart(), "GMAP.GV925", gMapCriteria.getContentAddress(), mClass, fo100, fo100i);
			updatePart(gMapCriteria.getWebId(), gMapCriteria.getIndexPart(), "GMAP.GV921", gMapCriteria.getAddress(), mClass, fo100, fo100i);
			if(gMapCriteria.getMarkerImg() != null)
				updatePart(gMapCriteria.getWebId(), gMapCriteria.getIndexPart(), "GMAP.GV922", gMapCriteria.getMarkerImg(), mClass, fo100, fo100i);
			return ohhayWebBase;
		}
		return null;
	}

}
