package com.ohhay.piepme.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.T120PMGDao;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Aug 3, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_T120P)
@Scope("prototype")
public class T120PMGDaoImpl extends QbMongoDaoSupport implements T120PMGDao{

	@Override
	public List<N100SummaryInfo> listOfTabT120Users(int pnFO100, int pnFT110, String pnTV129, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T110_LISTOFTABT120_USERS);
			setParameterNumber(pnFO100);
			setParameterNumber(pnFT110);
			setParameterString(pnTV129);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100).toString();
			if (kq != null) {
				List<N100SummaryInfo> listResult = new ArrayList<N100SummaryInfo>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					N100SummaryInfo n100SummaryInfo = new N100SummaryInfo(obj.getInt("_id"), obj.getInt("FO100"), obj.getString("AVARTA_URL"), obj.getString("NV106"));
					listResult.add(n100SummaryInfo);
				}
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
