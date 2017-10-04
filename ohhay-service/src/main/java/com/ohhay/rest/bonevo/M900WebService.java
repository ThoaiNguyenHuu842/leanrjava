package com.ohhay.rest.bonevo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Update;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.UserProfile;
import com.ohhay.core.entities.mongo.other.T200MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M910MG;
import com.ohhay.core.entities.mongo.profile.M920MG;
import com.ohhay.core.entities.mongo.shop.J910MG;
import com.ohhay.core.entities.mongo.videomarketing.V910MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.web.L400MG;

/**
 * @author ThoaiNH
 *
 */
@Path("m900WebService")
public class M900WebService {
	private static Logger log = Logger.getLogger(M900WebService.class);

	/*
	 * update share and contact
	 * date update: 14/04/2015: fo100 for share info
	 */
	@POST
	@Path("updateContactAndShare")
	@Produces("application/json")
	public String updateContactAndShare(String postString) {
		try {
			JSONObject jsonRootObject = new JSONObject(postString);
			log.info(jsonRootObject.toString());
			org.json.JSONArray jsonArrayContact = jsonRootObject
					.getJSONArray("CONTACT");
			org.json.JSONArray jsonArrayShare = jsonRootObject
					.getJSONArray("SHARE");
			int fo100 = Integer.parseInt(jsonRootObject.get("_id").toString());
			List<M910MG> listContact = new ArrayList<M910MG>();
			List<M910MG> listShare = new ArrayList<M910MG>();
			Update update = new Update();
			// get list contact
			for (int i = 0; i < jsonArrayContact.length(); i++) {
				JSONObject object = jsonArrayContact.getJSONObject(i);
				M910MG m910mg = new M910MG();
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				M900MG m900mg = templateService.findDocument(fo100, M900MG.class, new QbCriteria(QbMongoFiledsName.MV907, object.get("MV907").toString().replace(" ", "")));
				if(m900mg != null)
				{
					m910mg.setFo100(m900mg.getId());
				}
				m910mg.setMv907(object.get("MV907").toString());
				m910mg.setMv911(object.get("MV911").toString());
				listContact.add(m910mg);

			}
			// get list share
			for (int i = 0; i < jsonArrayShare.length(); i++) {
				log.info("---add info to share");
				JSONObject object = jsonArrayShare.getJSONObject(i);
				M910MG m910mg = new M910MG();
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				M900MG m900mg = templateService.findDocument(fo100, M900MG.class, new QbCriteria(QbMongoFiledsName.MV907, object.get("MV907").toString().replace(" ", "")));
				if(m900mg != null)
				{
					m910mg.setFo100(m900mg.getId());
				}
				m910mg.setMv907(object.get("MV907").toString());
				m910mg.setMv911(object.get("MV911").toString());
				listShare.add(m910mg);

			}
			// update to mongo
			TemplateService service = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			int kq = 0;
			kq = service.updateOneField(fo100, M900MG.class, fo100, "SHARE", listShare
					.toArray(), "ML946");
			kq = service
					.updateOneField(fo100, M900MG.class, fo100, "CONTACT", listContact
							.toArray(), "ML946");

			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * update one field in M900
	 */
	@POST
	@Path("updateOneField")
	@Produces("application/json")
	public String updateOneField(String postString) {
		try {
			// get from json
			JSONObject jsonObject = new JSONObject(postString);
			int id = Integer.parseInt(jsonObject.get("fo100").toString());
			String updateField = jsonObject.get("updatefield").toString().toUpperCase();
			String value = jsonObject.get("value").toString();
			String type = jsonObject.get("type").toString();
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			int kq = 0;
			if (type.equals("string")) {
				// update to mongo
				kq = templateService.updateOneField(id, M900MG.class, id, updateField, value, "ML948");
				//update db center
				templateService.updateOneField(0, M900MG.class, id, updateField, value, "ML948");
			}
			else if (type.equals("integer")) {
				int intValue = Integer.parseInt(value);
				kq = templateService.updateOneField(id, M900MG.class, id, updateField, intValue, "ML948");
				//update db center
				templateService.updateOneField(0, M900MG.class, id, updateField, intValue, "ML948");
			}
			else if (type.equals("date")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date date = dateFormat.parse(value);
				kq = templateService.updateOneField(id, M900MG.class, id, updateField, date, "ML948");
				//update db center
				templateService.updateOneField(0, M900MG.class, id, updateField, date, "ML948");
			}
			// update hoten, giotinh to mysql
			if (updateField.equals("MV901") || updateField.equals("MV902")
					|| updateField.equals("MV905")) {
				String qv101 = jsonObject.get("qv101").toString();
				String mv901 = jsonObject.get("mv901").toString();
				String mv902 = jsonObject.get("mv902").toString();
				String mv905 = jsonObject.get("mv905").toString();
				// udate nv100:hoten
				kq = templateService.updateOneField(id, M900MG.class, id, "NV100", mv901+ " " + mv902, "ML948");
				//update db center
				templateService.updateOneField(0, M900MG.class, id, "NV100", mv901+ " " + mv902, "ML948");
			}
			// on flag to reindex
			templateService.updateOneField(id, M900MG.class, id, "MN909", 1, "ML948");
			// update db center
			templateService.updateOneField(0, M900MG.class, id, "MN909", 1, "ML948");
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/*
	 * update email
	 */
	@POST
	@Path("updateEmail")
	@Produces("application/json")
	public String updateEmail(String postString) {
		try {
			int kq= 0;
			// get from json
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			String qv101 = jsonObject.get("qv101").toString();
			String qv102 = jsonObject.get("qv102").toString();
			String passwordConfirm = jsonObject.get("passwordConfirm").toString();
			String newEmail = jsonObject.get("newEmail").toString();
			M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
			log.info("---changeEmail:"+fo100+","+qv101+","+qv102+","+passwordConfirm+","+newEmail);
			kq = m900mgService.changeEmail(fo100, qv101, qv102, passwordConfirm, newEmail);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/*
	 * update job
	 */
	@POST
	@Path("updateJob")
	@Produces("application/json")
	public String updateJob(String postString) {
		try {
			int kq= 0;
			// set from json
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			String jobLabel = jsonObject.get("jobLabel").toString();
			int jobValue = Integer.parseInt(jsonObject.get("jobValue").toString());
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
			if(m900mg != null){
				M920MG m920mg = new M920MG();
				// save job
				m920mg.setLabel(jobLabel);
				m920mg.setVal(jobValue);
				m900mg.setM920MG(m920mg);
				kq = templateService.saveDocument(fo100, m900mg, QbMongoCollectionsName.M900);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/*
	 * validate email
	 */
	/**
	 * @param postString: email encrypted by AES
	 * @return 1: email hop le (chua ton tai)
	 */
	@POST
	@Path("validateExistEmail")
	@Produces("application/json")
	public String validateExistEmail(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String mv903 = jsonObject.get("mv903").toString();
			log.info("---email:"+mv903);
			M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
			int kq = 0;
			if(m900mgService.emailIsExists(mv903) == false)
				kq = 1;
			log.info("---kq:"+kq);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/*
	 * validate phone
	 */
	@POST
	@Path("validateExistPhone")
	@Produces("application/json")
	public String validateExistPhone(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String phone = jsonObject.get("phone").toString();
			String regionCode = jsonObject.get("regionCode").toString();
			log.info("---phone:"+phone);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			if(templateService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV907, ApplicationHelper.processDummiesPhones(phone, regionCode))) == null)
				return QbRestUtils.convertToJsonStringForFunc(1);
			else
				return QbRestUtils.convertToJsonStringForFunc(0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * user profile
	 * @param hv101: = home is get web home info or string of array hv101 to list multi profile 
	 * @return
	 */
	@GET
	@Path("getUserProfile")
	@Produces("application/json")
	public String getUserProfile(@QueryParam("hv101") String hv101s) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MGService m900mgService = (M900MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
			/*
			 * 1) m900 info
			 */
			if(hv101s.trim().equals("home"))
				hv101s = ApplicationHelper.convertToMD5("09033873686");
			String arr[] = hv101s.split("-");
			List<UserProfile> listUserProfiles = new ArrayList<UserProfile>();
			for(String hv101: arr){
				M900MG m900mg = m900mgService.loadUserProfile(hv101);
				if(m900mg != null){
					m900mg.getUrlAvarta();
					m900mgService.loadHistory(m900mg);
					/*
					 * 2) list video marketing
					 */
					TemplateService templateService = (TemplateService) ApplicationHelper
							.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					List<V910MG> listV910mgs = templateService
							.findDocuments(m900mg.getId() , V910MG.class, new QbCriteria(
									QbMongoFiledsName.FO100, m900mg.getId()));
					for (V910MG v910mg : listV910mgs) {
						TemplateService templateService2 = (TemplateService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						L400MG l400mg = templateService2.findDocumentById(m900mg.getId(), v910mg
								.getWebId(), L400MG.class, QbMongoFiledsName.VISIBLE);
						if (l400mg != null)
							v910mg.setVisible(l400mg.getVisible());
					}
					/*
					 * 3) get web shop online
					 */
					J910MG j910mg = templateMGService.findDocumentById(m900mg.getId(), m900mg.getId(), J910MG.class);
					UserProfile userProfile = new  UserProfile();
					userProfile.setM900mg(m900mg);
					userProfile.setListV910mgs(listV910mgs);
					userProfile.setJ910mg(j910mg);
					/*
					 * 4) all language of user web home
					 */
					C400MG c400mg = templateMGService.findDocument(m900mg.getId(), C400MG.class, new QbCriteria(QbMongoFiledsName.FO100, m900mg.getId()));
					userProfile.setListC500mg(c400mg.getListC500mg());
					listUserProfiles.add(userProfile);
					/*
					 * 4) process topic friendly link
					 */
					if(m900mg.getM960mg() != null)
						m900mg.getM960mg().setMv961(m900mg.getTopicFriendLyUrl());
				}
			}
			return QbRestUtils.convertToJsonStringForProc(listUserProfiles);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * user profile
	 * @param mv903: email can lay, co the truyen nhieu email phan cach boi dau - de lay nhieu profile 
	 * @return
	 */
	@GET
	@Path("getUserProfileMerian") 
	@Produces("application/json")
	public String getUserProfileMerian(@QueryParam("fo100s") String fo100s) {
		try {
			log.info("---fo100s:"+fo100s);
			M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
			/*
			 * 1) m900 info
			 */
			String arr[] = fo100s.split("-");
			List<UserProfile> listUserProfiles = new ArrayList<UserProfile>();
			for(String fo100: arr){
				M900MG m900mg = m900mgService.loadUserProfileMerian(Integer.parseInt(fo100));
				if(m900mg != null){
					m900mg.getUrlAvarta();
					m900mgService.loadHistory(m900mg);
					UserProfile userProfile = new  UserProfile();
					userProfile.setM900mg(m900mg);
					listUserProfiles.add(userProfile);
				}
			}
			return QbRestUtils.convertToJsonStringForProc(listUserProfiles);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * @param FO100 int
	 * @return
	 */
	@POST
	@Path("getAccessToken")
	@Produces("application/json")
	public String getAccessToken(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
			T200MG t200mg = new T200MG();
			try {
				t200mg.setId((int)sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.T200));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t200mg.setFo100(fo100);
			t200mg.setTv201(UUID.randomUUID().toString());
			t200mg.setTd208(new Date());
			templateService.saveDocument(fo100, t200mg, QbMongoCollectionsName.T200);
			return QbRestUtils.convertToJsonStringForFunc(t200mg.getTv201());
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
