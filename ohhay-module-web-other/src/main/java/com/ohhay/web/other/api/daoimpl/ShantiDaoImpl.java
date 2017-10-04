package com.ohhay.web.other.api.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.wsclient.QbWsClientParam;
import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;
import com.ohhay.web.core.entities.B050Shanti;
import com.ohhay.web.core.entities.C150Shanti;
import com.ohhay.web.other.api.dao.ShantiDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_SHANTIDAO)
@Scope("prototype")
public class ShantiDaoImpl extends QbWsClientSupport implements ShantiDao {

	@Override
	public List<B050Shanti> listOfTabB050(String key, String pvLogin) {
		// TODO Auto-generated method stub
		List<B050Shanti> list = new ArrayList<B050Shanti>();
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("KV113", key));
			listClientParams.add(new QbWsClientParam("LOGIN", pvLogin));
			JSONObject jsonObject = callPost(WsClinentName.SHANTI_LISTOFTABB050KEY, listClientParams);
			// get element
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			String baseLink = jsonObject.getString("BASELINK");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				B050Shanti b050 = new B050Shanti();
				b050.setBv051(jsonObject2.getString("BV051"));
				b050.setBv052(jsonObject2.getString("BV052"));
				b050.setSv203(baseLink+jsonObject2.getString("SV203"));
				b050.setPb050(Integer.parseInt(jsonObject2.getString("PB050")));
				list.add(b050);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<C150Shanti> listOfTabC150(String key, int fb050, String pvLogin) {
		// TODO Auto-generated method stub
		List<C150Shanti> list = new ArrayList<C150Shanti>();
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("KV113", key));
			listClientParams.add(new QbWsClientParam("LOGIN", pvLogin));
			listClientParams.add(new QbWsClientParam("FB050", fb050));
			JSONObject jsonObject = callPost(WsClinentName.SHANTI_LISTOFTABC150KEY, listClientParams);
			String baseLink = jsonObject.getString("BASELINK");
			// get element
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				C150Shanti c150 = new C150Shanti();
				c150.setPc150(jsonObject2.getString("PC150"));
				c150.setCv151(jsonObject2.getString("CV151"));
				c150.setCv158(baseLink+jsonObject2.getString("CV158"));
				c150.setCn167(Integer.parseInt(jsonObject2.getString("CN167")));
				c150.setCn168(Integer.parseInt(jsonObject2.getString("CN168")));
				c150.setCn169(Integer.parseInt(jsonObject2.getString("CN169")));
				c150.setCn170(Integer.parseInt(jsonObject2.getString("CN170")));
				c150.setCn171(Integer.parseInt(jsonObject2.getString("CN171")));
				c150.setCn172(Integer.parseInt(jsonObject2.getString("CN172")));
				c150.setCn174(Integer.parseInt(jsonObject2.getString("CN174")));
				c150.setCn175(Integer.parseInt(jsonObject2.getString("CN175")));
				c150.setCn176(Integer.parseInt(jsonObject2.getString("CN176")));
				list.add(c150);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}