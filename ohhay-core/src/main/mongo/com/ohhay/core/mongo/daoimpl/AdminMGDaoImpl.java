package com.ohhay.core.mongo.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.mongo.other.R000MG;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.dao.AdminMGDao;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_ADMINMG)
@Scope("prototype")
public class AdminMGDaoImpl extends QbMongoDaoSupport implements AdminMGDao{
	private static Logger log = Logger.getLogger(AdminMGDaoImpl.class);
	@Override
	public int getNewWebChildCn806(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_GET_NEWCHILD_CN806);
			setParameterNumber(fo100);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public String getThumbnailOfWeb(int fo100, int webId, String type) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_GET_THUMBNAIL);
			setParameterNumber(webId);
			setParameterString(type);
			return executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getUserColor(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.ADMIN_TOOLS_GETUSERCOLOR);
			setParameterNumber(fo100);
			return executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int checkWebShortExists(int fo100, String webType, String refId) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.CHECK_SHORTWEB_EXISTS);
			setParameterString(webType);
			setParameterString(refId);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<LanguageMG> getUserLanguage(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.ADMIN_TOOLS_GETUSERLANGUAGES);
			setParameterNumber(fo100);
			JSONArray jsonArray = new JSONArray(executeFunction(ApplicationConstant.DB_TYPE_TOPIC,fo100).toString());
			List<LanguageMG> list = new ArrayList<LanguageMG>();
			for (int i = 0; i < jsonArray.length(); i++) {
				LanguageMG languageMG = new LanguageMG();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				languageMG.setCode(jsonObject.getString("CODE"));
				languageMG.setText(jsonObject.getString("TEXT"));
				list.add(languageMG);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ComtabItem> getAllCoponent(int fc800) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_TOOLS_GETALLCOMPONENT);
			setParameterNumber(fc800);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY,0).toString();
			log.info("---kq:"+kq);
			JSONArray jsonArray = new JSONArray(kq);
			List<ComtabItem> list = new ArrayList<ComtabItem>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				ComtabItem com = new ComtabItem();
				com.setVal(Integer.parseInt(jsonObject.getString("FC820")));
				com.setLabel(jsonObject.getString("_id"));
				list.add(com);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int getNewWebDraftChildCn806(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_TOOLS_GETNEWWEBDRAFTCHILD_CN806);
			setParameterNumber(fo100);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertLog(String type, String message) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		R000MG r000mg = new R000MG();
		r000mg.setRv001(type);
		r000mg.setRv002(message);
		r000mg.setRd008(new Date());
		templateService.saveDocument(0, r000mg, QbMongoCollectionsName.R000);
		return 1;
	}
	
}
