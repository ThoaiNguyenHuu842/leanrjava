package com.ohhay.other.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mongo.dao.T150MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_T150SMG)
@Scope("prototype")
public class T150MGDaoImpl extends QbMongoDaoSupport implements T150MGDao{
	
	@Override
	public int updateTabT150(int pnFO100, int pnFO100S, String pnTV151) {
		// TODO Auto-generated method stub
		try {
			setParameterNumber(pnFO100);
			setParameterNumber(pnFO100S);
			setParameterString(pnTV151);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_TOPIC,0).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
}
