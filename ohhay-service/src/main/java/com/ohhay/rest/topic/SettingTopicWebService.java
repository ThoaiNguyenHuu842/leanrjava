package com.ohhay.rest.topic;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.M200MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M950MG;
import com.ohhay.core.entities.mongo.profile.M960MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.mongo.service.M960MGService;
import com.softlayer.api.service.scale.group.Log;


/**
 * @author TuNt
 * create date Jun 20, 2016
 * ohhay-service
 */
@Path("settingTopic")
public class SettingTopicWebService {
	Logger log = Logger.getLogger(SettingTopicWebService.class);

	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("/saveTopicInfo")
	@Produces("application/json")
	public String saveTopicInfo(String postString) {
		int kq = 0;
		TemplateService templateMGService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		try {
			log.info("Postring : "+postString);
			JSONObject json = new JSONObject(postString);
			int fo100 = Integer.parseInt(json.getString("fo100"));
			String mv961 = json.getString("mv961");
			String mv962 = json.getString("mv962");
			String mv963 = json.getString("mv963");
			String mv967 = json.getString("mv967");
			String imgBase64 = json.getString("imgBase64");
			String region = json.getString("region");

			M960MG m960mg = new M960MG();
			m960mg.setMv961(mv961);
			m960mg.setMv962(mv962);
			m960mg.setMv963(mv963);
			m960mg.setMv967(mv967);
			
			m960mg.setImgBase64(imgBase64);

			M900MG m900mg = templateMGService.findDocumentById(fo100, fo100, M900MG.class);
			if (m900mg.getM960mg() != null) {
				m900mg.getM960mg().setMv961(m960mg.getMv961());
				m900mg.getM960mg().setMv962(m960mg.getMv962());
				m900mg.getM960mg().setMv963(m960mg.getMv963());
				m900mg.getM960mg().setMv967(m960mg.getMv967());
				if(!imgBase64.equals(""))
					m900mg.getM960mg().setImgBase64(m960mg.getImgBase64());
			} else
				m900mg.setM960mg(m960mg);
			M960MGService m960MGService = (M960MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_M960MG);
			log.info("MV967 : "+m900mg.getM960mg().getMv967());
			kq = m960MGService.saveInfoTopicWithoutSession(m900mg, fo100, region);
			return QbRestUtils.convertToJsonStringForFunc(kq);

		} catch (JSONException e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param fo100(find
	 *            by fo100)
	 * @param pathOne(find
	 *            by email or MV203 in Topic) 
	 *            find one of two param
	 * @return
	 */
	@GET
	@Path("/getSettingTopic")
	@Produces("application/json")
	public String getSettingTopic(@QueryParam("FO100") int fo100, @QueryParam("PATHONE") String pathOne) {
		try {
			List<Object> result = new ArrayList<Object>();
			List<M950MG> listM950 = new ArrayList<M950MG>();
			List<M960MG> listM960 = new ArrayList<M960MG>();

			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900Login = templateService.findDocumentById(fo100, fo100, M900MG.class);
			if (m900Login != null && fo100 != 0) {
				listM960.add(m900Login.getM960mg());

				listM950.add(m900Login.getM950mg());

				result.add(listM950);
				result.add(listM960);
			} else {
				/*
				 * this is find web by phone number or search path data (email)
				 */
				M900MG m900mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, M900MG.class,
						new QbCriteria(QbMongoFiledsName.MV903, AESUtils.encrypt(pathOne)));
				if (m900mg == null) {
					templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
					M200MG m200MG = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, M200MG.class,
							new QbCriteria(QbMongoFiledsName.MV203, pathOne));
					if (m200MG != null) {
						templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
						m900mg = templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN,
								m200MG.getFo100(), M900MG.class);
					}
				}
				log.info("fo100 find by m203" + m900mg.getId());
				listM950.add(m900mg.getM950mg());
				listM960.add(m900mg.getM960mg());
				result.add(listM950);
				result.add(listM960);
			}
			return QbRestUtils.convertToJsonStringForProc(result);
		} catch (Exception e) {
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
