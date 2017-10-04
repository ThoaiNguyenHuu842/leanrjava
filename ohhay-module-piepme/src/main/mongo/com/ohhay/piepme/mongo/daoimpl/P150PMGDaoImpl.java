package com.ohhay.piepme.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.P150PMGDao;

/**
 * @author TuNt
 * create date Mar 6, 2017
 * ohhay-module-piepme
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_P150P)
@Scope("prototype")
public class P150PMGDaoImpl extends QbMongoDaoSupport implements P150PMGDao{

	@Override
	public String insertTabP150(int fo100, String pv152) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P150_INSTERTTABP150);
			setParameterNumber(fo100);
			setParameterString(pv152);
			String result = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String confirmTabP150(int fo100, String otpCode, String pv152) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.P150_CONFIRMTABP150_ONE);
			setParameterNumber(fo100);
			setParameterString(otpCode);
			setParameterString(pv152);
			String result = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
