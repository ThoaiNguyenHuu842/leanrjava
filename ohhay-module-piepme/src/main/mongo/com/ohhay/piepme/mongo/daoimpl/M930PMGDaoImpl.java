package com.ohhay.piepme.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.M930PMGDao;

/**
 * @author ThoaiNH
 * create Jan 16, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_M930P)
@Scope("prototype")
public class M930PMGDaoImpl extends QbMongoDaoSupport implements M930PMGDao{

	@Override
	public int insertTabM930(int fo100, String key) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.M930_INSERTTABM930);
			setParameterNumber(fo100);
			setParameterString(key);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

}
