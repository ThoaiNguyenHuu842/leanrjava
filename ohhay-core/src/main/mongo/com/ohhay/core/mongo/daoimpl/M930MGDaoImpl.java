package com.ohhay.core.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.dao.M930MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_M930MG)
@Scope("prototype")
public class M930MGDaoImpl extends QbMongoDaoSupport implements M930MGDao{

	@Override
	public int insertTabM930(int fo100, String pnKey) {
		try {
			setFunctionName(QbMongoFunctionNames.M930_INSERTTABM930);
			setParameterNumber(fo100);
			setParameterString(pnKey);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
}
