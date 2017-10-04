package com.ohhay.other.mongo.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.R000MG;
import com.ohhay.other.mongo.dao.R000MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_R000MG)
@Scope("prototype")
public class R000MGDaoImpl extends QbMongoDaoSupport implements R000MGDao {
	Logger log = Logger.getLogger(R000MGDaoImpl.class);

	@Override
	public List<String> listOfTabRv001() {
		List<String> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.R000_LISTOFTABRV001);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString();
			if (resultData != null) {
				log.info("result data " + resultData);
				listResult = new ArrayList<String>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					listResult.add(jsonObject.getString("_id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<String> listOfTabRv000(String rv001) {
		List<String> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.R000_LISTOFTABR000);
			setParameterString(rv001);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString();
			if (resultData != null) {
				log.info("result return of R00_LISTOFTABR000" + resultData);
				listResult = new ArrayList<String>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					listResult.add(jsonObject.getString("RV002"));
					listResult.add(jsonObject.getString("DATE_CREATE"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

}
