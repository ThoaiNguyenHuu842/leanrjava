package com.ohhay.nexmo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.wsclient.QbWsClientParam;
import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;


@Repository(value = SpringBeanNames.REPOSITORY_NAME_SMS)
@Scope("prototype")
public class SmsDaoImpl extends QbWsClientSupport implements SmsDao {
//	{"message-count":"1","messages":[{"to":"84933508287","message-id":"06000000156036B7",
	//"status":"0","remaining-balance":"1.86200000","message-price":"0.02300000","network":"45201"}]}
	private static Logger log = Logger.getLogger(SmsDaoImpl.class);
	@Override
	public int sendMessageNexmo(String api_key, String api_secret, String from, String to, String text) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("api_key", api_key));
			listClientParams.add(new QbWsClientParam("api_secret", api_secret));
			//listClientParams.add(new QbWsClientParam("type", "unicode"));
			listClientParams.add(new QbWsClientParam("from", from));
			listClientParams.add(new QbWsClientParam("to", to));
			listClientParams.add(new QbWsClientParam("text", text));
			JSONObject jsonObject = callPost(WsClinentName.SENDMESSAGE_NEXMO, listClientParams);
			JSONArray jsonArray = jsonObject.getJSONArray("messages");
			JSONObject jsonObject2 = jsonArray.getJSONObject(0);
			log.info("----AAA-------"+jsonObject2.getString("status"));
			
			return Integer.parseInt(jsonObject2.getString("status"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int sendMessageNexmo2(String api_key, String api_secret, String from, String to, String o050Code) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("api_key", api_key));
			listClientParams.add(new QbWsClientParam("api_secret", api_secret));
			listClientParams.add(new QbWsClientParam("to", to));
			listClientParams.add(new QbWsClientParam("pin", o050Code));
			JSONObject jsonObject = callPost(WsClinentName.SENDMESSAGE_NEXMO, listClientParams);
			JSONArray jsonArray = jsonObject.getJSONArray("messages");
			JSONObject jsonObject2 = jsonArray.getJSONObject(0);
			log.info("----AAA-------"+jsonObject2.getString("status"));
			
			return Integer.parseInt(jsonObject2.getString("status"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

}
