package com.ohhay.core.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.N800MG;
import com.ohhay.core.mongo.dao.N800MGDao;
import com.ohhay.core.utils.DateHelper;

/**
 * @author TuNt create date Dec 9, 2016 ohhay-core
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_N800MG)
@Scope("prototype")
public class N800MGDaoImpl extends QbMongoDaoSupport implements N800MGDao {
	Logger log = Logger.getLogger(N800MGDaoImpl.class);

	@Override
	public int insertTabN800(int typeDb, int fo100, int fo100n, String nv801, int nn802, String nv803, String nv804) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.N800_INSERTABN800);
			setParameterNumber(fo100);
			setParameterNumber(fo100n);
			setParameterString(nv801);
			setParameterNumber(nn802);
			setParameterString(nv803);
			setParameterString(nv804);
			int result = (int) Float.parseFloat(executeFunction(typeDb, fo100).toString());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.toString());
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<N800MG> listOfTabN800(int typeDb, int fo100, int offset, int limit, String nv804) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.N800_LISTOFTABN800);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			setParameterString(nv804);
			String result = executeFunction(typeDb, fo100).toString();
			List<N800MG> listN800 = new ArrayList<N800MG>();
			JSONArray jsonArray = new JSONArray(result);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				N800MG n800mg = new N800MG();
				n800mg.setId(json.getInt("_id"));
				n800mg.setFo100(json.getInt("FO100"));
				n800mg.setFo100n(json.getInt("FO100N"));
				n800mg.setNv801(json.getString("NV801"));
				n800mg.setNn802(json.getInt("NN802"));
				n800mg.setNv803(json.getString("NV803"));
				n800mg.setHoTen(json.getString("HOTEN"));
				n800mg.setAvarta(json.getString("AVARTA"));
				n800mg.setNv804(json.getString("NV804"));
				String timeLine = DateHelper.convertDateToTimeLine(json.getLong("SECOND_LAST_ACTION"));
				timeLine = timeLine.replace("h", " hours ").replace("m", " min ").replace("d", " day ");
				n800mg.setSecondLastAction(timeLine);
				listN800.add(n800mg);
			}
			return listN800;
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.toString());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertTabN800PieperComment(int typeDb, int fo100, String pieperType, int piperId, String nv801, int nn802,
			String nv803, String nv804) {
		// TODO Auto-generated method stub
				try {
					setFunctionName(QbMongoFunctionNames.N800_INSERTABN800_PIEPER_COMMENT);
					setParameterNumber(fo100);
					setParameterString(pieperType);
					setParameterNumber(piperId);
					setParameterString(nv801);
					setParameterNumber(nn802);
					setParameterString(nv803);
					setParameterString(nv804);
					int result = (int) Float.parseFloat(executeFunction(typeDb, fo100).toString());
					return result;
				} catch (Exception e) {
					// TODO: handle exception
					log.info(e.toString());
					e.printStackTrace();
				}
				return 0;
	}

}
