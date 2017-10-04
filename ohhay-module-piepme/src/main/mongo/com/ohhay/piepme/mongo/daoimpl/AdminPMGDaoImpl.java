package com.ohhay.piepme.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;

/**
 * @author ThoaiNH
 * create May 5, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_ADMINP)
@Scope("prototype")
public class AdminPMGDaoImpl extends QbMongoDaoSupport implements AdminPMGDao{

	@Override
	public int createDBUser(int fo100) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.ADMIN_TOOLS_CREATE_USER);
			setParameterNumber(fo100);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, ApplicationConstant.FO100_SUPER_ADMIN).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeDBUser(int fo100) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.ADMIN_TOOLS_REMOVE_USER);
			setParameterNumber(fo100);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, ApplicationConstant.FO100_SUPER_ADMIN).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long getCurrentTime() {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.ADMIN_TOOLS_UTIL_GETCURRENT_TIME);
			long result = (long) Long.parseLong(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, ApplicationConstant.FO100_SUPER_ADMIN).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

}
