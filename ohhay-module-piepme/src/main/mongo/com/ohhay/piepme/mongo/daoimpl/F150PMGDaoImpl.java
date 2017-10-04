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
import com.ohhay.piepme.mongo.dao.F150PMGDao;
import com.ohhay.piepme.mongo.entities.interaction.F150PMG;

/**
 * @author TuNt create date Oct 28, 2016 ohhay-module-piepme
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_F150P)
@Scope("prototype")
public class F150PMGDaoImpl extends QbMongoDaoSupport implements F150PMGDao {

	@Override
	public List<F150PMG> listOfTabF150(int fo100, int offset, int limit) {
		List<F150PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.F150_LISTOFTABF150);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<F150PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					F150PMG f150 = new F150PMG();
					f150.setId(jsonObject.getInt("_id"));
					f150.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					f150.setNv101(jsonObject.getString("NV101"));
					f150.setNv102(jsonObject.getString("NV102"));
					listResult.add(f150);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<F150PMG> listOfTabF150T(int fo100, int offset, int limit) {
		List<F150PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.F150_LISTOFTABF150T);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME,
					fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<F150PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					F150PMG f150 = new F150PMG();
					f150.setId(jsonObject.getInt("_id"));
					f150.setFo100t(Integer.parseInt(jsonObject.getString("FO100T")));
					f150.setNv101(jsonObject.getString("NV101"));
					f150.setNv102(jsonObject.getString("NV102"));
					listResult.add(f150);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public int insertTabF150(int fo100, int fo100t, int fb300, String pieperType) {
		try {
			setFunctionName(QbMongoFunctionNames.F150_INSERTTABF150);
			setParameterNumber(fo100);
			setParameterNumber(fo100t);
			setParameterNumber(fb300);
			setParameterString(pieperType);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String checkFollowStatus(int fo100, int fo100t) {
		try {
			setFunctionName(QbMongoFunctionNames.F150_CHECKFOLLOWSTATUS);
			setParameterNumber(fo100);
			setParameterNumber(fo100t);
			String result = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
					.toString();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int storNoTabF150(int fo100, int fo100t) {
		try {
			setFunctionName(QbMongoFunctionNames.F150_STORNOTABF150);
			setParameterNumber(fo100);
			setParameterNumber(fo100t);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
