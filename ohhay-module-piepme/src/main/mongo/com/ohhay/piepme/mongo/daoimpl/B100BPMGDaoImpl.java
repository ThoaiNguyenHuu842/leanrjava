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
import com.ohhay.piepme.mongo.dao.B100BPMGDao;
import com.ohhay.piepme.mongo.entities.blockaccount.B100BPMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author TuNt
 * create date May 12, 2017
 * ohhay-module-piepme
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_B100BP)
@Scope("prototype")
public class B100BPMGDaoImpl extends QbMongoDaoSupport implements B100BPMGDao{

	/* (non-Javadoc)
	 * @see com.ohhay.piepme.mongo.dao.B100BPMGDao#getListB100B(int, int, int)
	 */
	@Override
	public List<B100BPMG> getListB100B(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<B100BPMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.B100B_LISTOFTABB100B);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<B100BPMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					B100BPMG b100bpmg = new B100BPMG();
					b100bpmg.setId(jsonObject.getInt(QbMongoFiledsName.ID));
					b100bpmg.setFo100(fo100);
					b100bpmg.setFo100P(jsonObject.getInt(QbMongoFiledsName.FO100P));
					N100SummaryInfo n100sum = new N100SummaryInfo();
					n100sum.setFo100(jsonObject.getInt(QbMongoFiledsName.FO100P));
					n100sum.setNv106(jsonObject.getString(QbMongoFiledsName.NV106));
					n100sum.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					b100bpmg.setN100SummaryInfo(n100sum);
					listResult.add(b100bpmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

}
