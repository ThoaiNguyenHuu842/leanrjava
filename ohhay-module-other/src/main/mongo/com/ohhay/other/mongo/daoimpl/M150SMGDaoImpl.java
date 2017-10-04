package com.ohhay.other.mongo.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.mongo.dao.M150SMGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_M150SMG)
@Scope("prototype")
public class M150SMGDaoImpl extends QbMongoDaoSupport implements M150SMGDao{

	@Override
	public int updateTabMn211(int pnFO100, int pnFM150, int pnMN211) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M150S_UPDATETABMN211);
			setParameterNumber(pnFO100);
			setParameterNumber(pnFM150);
			setParameterNumber(pnMN211);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_TOPIC, pnFO100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	
	
}
