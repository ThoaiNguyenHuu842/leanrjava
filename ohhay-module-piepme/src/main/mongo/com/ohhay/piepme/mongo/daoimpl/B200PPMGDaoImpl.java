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
import com.ohhay.piepme.mongo.dao.B200PPMGDao;
import com.ohhay.piepme.mongo.entities.reportpieper.B200PPMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_B200PP)
@Scope("prototype")
public class B200PPMGDaoImpl extends QbMongoDaoSupport implements B200PPMGDao {

	/* (non-Javadoc)
	 * @see com.ohhay.piepme.mongo.dao.B200PPMGDao#getListB200P(int, int, int)
	 */
	@Override
	public List<B200PPMG> getListB200P(int fo200, int offset, int limit) {
		// TODO Auto-generated method stub
		List<B200PPMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.B200P_LISTOFTABB200P);
			setParameterNumber(fo200);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo200).toString();
			if (resultData != null) {
				listResult = new ArrayList<B200PPMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					B200PPMG b200ppmg = new B200PPMG();
					b200ppmg.setId(jsonObject.getInt(QbMongoFiledsName.ID));
					b200ppmg.setFo100(fo200);
					b200ppmg.setFo100P(jsonObject.getInt(QbMongoFiledsName.FO100P));
					N100SummaryInfo n100sum = new N100SummaryInfo();
					n100sum.setFo100(jsonObject.getInt(QbMongoFiledsName.FO100P));
					n100sum.setNv106(jsonObject.getString(QbMongoFiledsName.NV106));
					n100sum.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					b200ppmg.setN100SummaryInfo(n100sum);
					listResult.add(b200ppmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

}
