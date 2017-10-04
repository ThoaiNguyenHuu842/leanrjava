package com.ohhay.piepme.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.B200CPMGDao;
import com.ohhay.piepme.mongo.entities.reportpieper.B200CPMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author TuNt
 * create date Jun 7, 2017
 * ohhay-module-piepme
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_B200CP)
@Scope("prototype")
public class B200CPMGDaoImpl extends QbMongoDaoSupport implements B200CPMGDao {

	/* (non-Javadoc)
	 * @see com.ohhay.piepme.mongo.dao.B200CPMGDao#getListB200C(int, int, int)
	 */
	@Override
	public List<B200CPMG> getListB200C(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<B200CPMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.B200P_LISTOFTABB200C);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<B200CPMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					B200CPMG b200cpmg = new B200CPMG();
					b200cpmg.setId(jsonObject.getInt(QbMongoFiledsName.ID));
					b200cpmg.setFo100(fo100);
					b200cpmg.setFo100P(jsonObject.getInt(QbMongoFiledsName.FO100P));
					N100SummaryInfo n100sum = new N100SummaryInfo();
					n100sum.setFo100(jsonObject.getInt(QbMongoFiledsName.FO100P));
					n100sum.setNv106(jsonObject.getString(QbMongoFiledsName.NV106));
					n100sum.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					b200cpmg.setN100SummaryInfo(n100sum);
					listResult.add(b200cpmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

}
