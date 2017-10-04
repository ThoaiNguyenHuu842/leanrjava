package com.ohhay.web.other.api.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.wsclient.QbWsClientParam;
import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;
import com.ohhay.web.core.entities.B050Wbn;
import com.ohhay.web.other.api.dao.WebinarisDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_WEBINARIS)
@Scope("prototype")
public class WebinarisDaoImpl extends QbWsClientSupport implements WebinarisDao {
	@Override
	public List<B050Wbn> getListB050(String key) {
		// TODO Auto-generated method stub
		List<B050Wbn> list = new ArrayList<B050Wbn>();
		try {
			JSONObject jsonObject = callGet(WsClinentName.WEBINAR_REST+"?key="
					+ key);
			// get element
			JSONArray jsonArray = jsonObject.getJSONArray("info");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				B050Wbn b050 = new B050Wbn();
				b050.setBv051(jsonObject2.getString("BV051"));
				b050.setBv052(jsonObject2.getString("BV052"));
				b050.setUrl(jsonObject2.getString("url"));
				list.add(b050);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int confirmKey(String key) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = callGet(WsClinentName.WEBINAR_CHECKKEY+"?key="
					+ key);
			int pk100 = 0;
			if (jsonObject.getString("PK100") != null)
				pk100 = Integer.parseInt(jsonObject.getString("PK100"));
			if (pk100 > 0)
				return 1;
			else
				return 0;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertTabW400(int fw400, String webniarKey) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("FW400", fw400));
			listClientParams.add(new QbWsClientParam("WV402", webniarKey));
			JSONObject jsonObject = callPost(WsClinentName.WEBINAR_STDW_TOOLS_INSERTTABW400, listClientParams);
			int result = 0;
			if (jsonObject.getString("result") != null)
				result = Integer.parseInt(jsonObject.getString("result"));
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int stornoTabW400(int fw400) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("FW400", fw400));
			JSONObject jsonObject = callPost(WsClinentName.WEBINAR_STDW_TOOLS_STONORTABW400, listClientParams);
			int result = 0;
			if (jsonObject.getString("result") != null)
				result = Integer.parseInt(jsonObject.getString("result"));
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
