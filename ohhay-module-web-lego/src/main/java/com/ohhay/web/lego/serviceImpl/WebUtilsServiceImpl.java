package com.ohhay.web.lego.serviceImpl;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.ohhay.web.lego.entities.mongo.base.web.ConfigWebBase;
import com.ohhay.web.lego.entities.mongo.base.web.ElementCrId;
import com.ohhay.web.lego.service.WebUtilsService;
import com.oohhay.web.lego.utils.WebLegoUtils;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH
 * create Nov 4, 2015
 * util using in save web evo module
 */
@Service(value = SpringBeanNames.SERVICE_NAME_WEBUTILS)
@Scope("prototype")
public class WebUtilsServiceImpl implements WebUtilsService{
	private static Logger log = Logger.getLogger(WebUtilsServiceImpl.class);
	@Autowired
	private TemplateService templateService;
	/**
	 * @param fo100
	 * @param webId
	 * @param webData
	 * @return
	 */
	public int saveBG(int fo100, long webId, JSONObject webData, int extend){
		BgWebBase bgWebBase = WebLegoUtils.getBgOgObject(webData, WebRule.WEB_PRO_BG);
		if (bgWebBase != null)
			return templateService
					.updateOneField(fo100, WebRule.getWebClassFromExtend(extend), "BG", bgWebBase, null, new QbCriteria(
							QbMongoFiledsName.FO100, fo100), new QbCriteria(
									QbMongoFiledsName.ID, webId));
		return 0;
	}
	@Override
	public int saveConfig(int fo100, long webId, JSONObject webData, int extend) {
		// TODO Auto-generated method stub
		try {
			JSONObject config = webData.getJSONObject(WebRule.WEB_PRO_CONFIG);
			if (config != null) {
				int w = config.getInt(WebRule.WEB_PRO_W);
				int mgL = config.getInt("mgL");
				int mgT = config.getInt("mgT");
				int mgB = config.getInt("mgB");
				int imgLazy = config.getInt("imgLazy");
				ConfigWebBase configWebBase= new ConfigWebBase();
				configWebBase.setMgB(mgB);
				configWebBase.setMgL(mgL);
				configWebBase.setMgT(mgT);
				configWebBase.setW(w);
				configWebBase.setImgLazy(imgLazy);
				return templateService
						.updateOneField(fo100, WebRule.getWebClassFromExtend(extend), "CONFIG", configWebBase, null, new QbCriteria(
								QbMongoFiledsName.FO100, fo100), new QbCriteria(
										QbMongoFiledsName.ID, webId));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int saveElemeCrId(int fo100, long webId, JSONObject webData, int extend) {
		// TODO Auto-generated method stub
		try {
			JSONObject elemeCrIDJson = webData.getJSONObject(WebRule.WEB_PRO_ELEME_CR_ID);
			if (elemeCrIDJson != null) {
				ElementCrId elementCrId = new ElementCrId();
				long cFormId = elemeCrIDJson.getLong(WebRule.WEB_PRO_C_FORMID);
				long rFormId = elemeCrIDJson.getLong(WebRule.WEB_PRO_R_FORMID);
				elementCrId.setcFormId(cFormId);
				elementCrId.setrFormId(rFormId);
				return templateService
						.updateOneField(fo100, WebRule.getWebClassFromExtend(extend), "ELEME_CR_ID", elementCrId, null, new QbCriteria(
								QbMongoFiledsName.FO100, fo100), new QbCriteria(
										QbMongoFiledsName.ID, webId));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}}

