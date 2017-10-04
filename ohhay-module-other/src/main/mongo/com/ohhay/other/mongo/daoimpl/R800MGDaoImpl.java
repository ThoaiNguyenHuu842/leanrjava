package com.ohhay.other.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.R800MG;
import com.ohhay.other.mongo.dao.R800MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_R800MG)
@Scope("prototype")
public class R800MGDaoImpl extends QbMongoDaoSupport implements R800MGDao {

	private static Logger log = Logger.getLogger(R800MGDaoImpl.class);

	@Override
	public int insertTabR800(int fo100, int fm150, int fo100r, String rv801) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R800_INSERTTABR800);
			setParameterNumber(fo100);
			setParameterNumber(fm150);
			setParameterNumber(fo100r);
			setParameterString(rv801);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_TOPIC,fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("deprecation")
	public List<R800MG> getListOfTabR800(int fo100, int offset, int limit) {
		try {
			setFunctionName(QbMongoFunctionNames.R800_LISTOFTABR800);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_TOPIC, fo100).toString();
			log.info("kkkkkqqqqqqqqqqq:" + kq);
			if (kq != null) {
				List<R800MG> r800 = new ArrayList<R800MG>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					R800MG r = new R800MG();
					r.setId(Integer.parseInt(obj.getString("_id")));
					r.setFm150(Integer.parseInt(obj.getString("FM150")));
					r.setFo100(Integer.parseInt(obj.getString("FO100")));
					r.setFo100r(Integer.parseInt(obj.getString("FO100R")));
					r.setRv801(obj.getString("RV801"));
					String s = obj.getString("RD808");
					JSONObject date = new JSONObject(s);
					r.setRd808(date.getString("$date"));
					r.setRd808string(obj.getString("RD808_STRING"));
					r.setHv101(obj.getString("HV101"));
					r.setHoten(obj.getString("HOTEN"));
					r.setPhone(obj.getString("PHONE"));
					r.setAvatar(obj.getString("AVARTA"));
					r.setHv101share(obj.getString("HV101SHARE"));
					//r.setMv151(obj.getString("MV151"));
					//r.setHv101r(obj.getString("HV101R"));
					r800.add(r);
				}
				log.info("asas");
				return r800;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
