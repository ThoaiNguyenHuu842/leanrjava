package com.ohhay.web.core.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.core.mongo.dao.WebToolDao;
import com.ohhay.web.core.mongo.service.WebToolService;
import com.ohhay.web.core.utils.WebTemplateRule;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_WEBTOOLDAO)
@Scope("prototype")
public class WebToolDaoImpl extends QbMongoDaoSupport implements WebToolDao{

	@Override
	public int getTotalWebOfUser(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_TOOLS_GETTOTALWEBOFUSER);
			setParameterNumber(fo100);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getTotalWebDraftOfUser(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.WEB_TOOLS_GETTOTALWEBOFUSER_DRAFT);
			setParameterNumber(fo100);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
