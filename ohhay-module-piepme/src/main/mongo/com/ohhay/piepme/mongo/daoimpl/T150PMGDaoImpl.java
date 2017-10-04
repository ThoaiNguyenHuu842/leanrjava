package com.ohhay.piepme.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.T150PMGDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_T150P)
@Scope("prototype")
public class T150PMGDaoImpl extends QbMongoDaoSupport implements T150PMGDao {

	@Override
	public int insertTabT150(int pnFO100, int pnTV151) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T150_INSERTTABT150);
			setParameterNumber(pnFO100);
			setParameterNumber(pnTV151);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
