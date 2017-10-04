package com.ohhay.core.mongo.daoimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.ohhay.core.entities.mongo.other.P500MG;
import com.ohhay.core.mongo.dao.P500MGDao;

/**
 * @author TuNt create date Jul 13, 2016 ohhay-core
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_P500MG)
@Scope("prototype")
public class P500MGDaoImpl extends QbMongoDaoSupport implements P500MGDao {

	@Override
	public List<P500MG> listOfTabP500(int offset, int limit) {
		try {
			List<P500MG> listP500 = new ArrayList<P500MG>();
			setFunctionName(QbMongoFunctionNames.P500_LISTOFTABP500);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String result = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString();
			JSONArray jsonArray = new JSONArray(result);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				P500MG p500mg = new P500MG();
				p500mg.setPv501(jsonObject.getString("PV501"));
				p500mg.setPv502(jsonObject.getString("PV502"));
				if (offset == 0 && i == 0)
					p500mg.setRowss(jsonObject.getInt("ROWSS"));
				listP500.add(p500mg);
			}
			return listP500;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
