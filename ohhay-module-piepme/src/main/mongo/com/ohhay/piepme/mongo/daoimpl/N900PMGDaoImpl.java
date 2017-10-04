package com.ohhay.piepme.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.N900PMGDao;

/**
 * @author TuNt
 * create date Jun 28, 2017
 * ohhay-module-piepme
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_N900P)
@Scope("prototype")
public class N900PMGDaoImpl extends QbMongoDaoSupport implements N900PMGDao{

	/* (non-Javadoc)
	 * @see com.ohhay.piepme.mongo.dao.N900PMGDao#updateNotification(int, int, int, int)
	 */
	@Override
	public int updateNotification(int fo100, int fo100f, String objectName, int status) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.N900_UPDATETABN900);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(objectName);
			setParameterNumber(status);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

}
