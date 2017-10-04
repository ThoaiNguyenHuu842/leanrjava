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
import com.ohhay.piepme.mongo.dao.B100PPMGDao;
import com.ohhay.piepme.mongo.entities.blockaccount.B100PPMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_B100PP)
@Scope("prototype")
public class B100PPMGDaoImpl extends QbMongoDaoSupport implements B100PPMGDao {

	/* (non-Javadoc)
	 * @see com.ohhay.piepme.mongo.dao.B100PPMGDao#getListB100P(int, int, int)
	 */
	@Override
	public List<B100PPMG> getListB100P(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<B100PPMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.B100P_LISTOFTABB100P);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<B100PPMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					B100PPMG b100ppmg = new B100PPMG();
					b100ppmg.setId(jsonObject.getInt(QbMongoFiledsName.ID));
					b100ppmg.setFo100(fo100);
					b100ppmg.setFo100P(jsonObject.getInt(QbMongoFiledsName.FO100P));
					N100SummaryInfo n100sum = new N100SummaryInfo();
					n100sum.setFo100(jsonObject.getInt(QbMongoFiledsName.FO100P));
					n100sum.setNv106(jsonObject.getString(QbMongoFiledsName.NV106));
					n100sum.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					b100ppmg.setN100SummaryInfo(n100sum);
					listResult.add(b100ppmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

}
