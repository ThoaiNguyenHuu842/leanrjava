package com.ohhay.other.api.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.util.AESUtils;
import com.ohhay.base.wsclient.QbWsClientParam;
import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.api.dao.M350OrelDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_M350OREL)
@Scope("prototype")
public class M350OrelDaoImpl extends QbWsClientSupport implements M350OrelDao{

	public int sendMailTabM350Topic(int pnFO100, String ov102, String pvMV367, String pvMV375, String pvMESSA, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("pnFO100", pnFO100));
			listClientParams.add(new QbWsClientParam("pvOV102", ov102));
			listClientParams.add(new QbWsClientParam("pvMV367", AESUtils.encrypt(pvMV367)));
			listClientParams.add(new QbWsClientParam("pvMV375", AESUtils.encrypt(ApplicationHelper.processSubjectEmail(pvMV375))));
			listClientParams.add(new QbWsClientParam("pvMESSA", AESUtils.encrypt(pvMESSA)));
			listClientParams.add(new QbWsClientParam("pvLOGIN", pvLogin));
			JSONObject jsonObject = callPostJson(WsClinentName.OREL_MREL_INSERTABM350STOPIC, listClientParams);
			int result = 0;
			if (jsonObject.getJSONObject("elements").getString("result") != null)
				result = Integer.parseInt(jsonObject.getJSONObject("elements").getString("result"));
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int sendMailTabM350Shop(int pnFO100, String ov102, String pvMV367, String pvMV375, String pvMESSA, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("pnFO100", pnFO100));
			listClientParams.add(new QbWsClientParam("pvOV102", (ov102)));
			listClientParams.add(new QbWsClientParam("pvMV367", AESUtils.encrypt(pvMV367)));
			listClientParams.add(new QbWsClientParam("pvMV375", AESUtils.encrypt(ApplicationHelper.processSubjectEmail(pvMV375))));
			listClientParams.add(new QbWsClientParam("pvMESSA", AESUtils.encrypt(pvMESSA)));
			listClientParams.add(new QbWsClientParam("pvLOGIN", pvLogin));
			JSONObject jsonObject = callPostJson(WsClinentName.OREL_MREL_INSERTABM350SHOP, listClientParams);
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
	public int sendMailTabM350Topic(int pnFO100, String ov102, String pvMV366, String pvMV367, String pvMV368, String pvMV369, String pvMV370, String pvMV371, String pvMV375, String pvMESSA, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("pnFO100", pnFO100));
			listClientParams.add(new QbWsClientParam("pvOV102", ov102));
			listClientParams.add(new QbWsClientParam("pvMV366",pvMV366));
			listClientParams.add(new QbWsClientParam("pvMV367", AESUtils.encrypt(pvMV367)));
			listClientParams.add(new QbWsClientParam("pvMV368", pvMV368));
			listClientParams.add(new QbWsClientParam("pvMV369", pvMV369));
			listClientParams.add(new QbWsClientParam("pvMV370", pvMV370));
			listClientParams.add(new QbWsClientParam("pvMV371", pvMV371));
			listClientParams.add(new QbWsClientParam("pvMV375", AESUtils.encrypt(ApplicationHelper.processSubjectEmail(pvMV375))));
			listClientParams.add(new QbWsClientParam("pvMESSA", AESUtils.encrypt(pvMESSA)));
			listClientParams.add(new QbWsClientParam("pvLOGIN", pvLogin));
			JSONObject jsonObject = callPostJson(WsClinentName.OREL_MREL_INSERTABM350STOPIC, listClientParams);
			int result = 0;
			if (jsonObject.getJSONObject("elements").getString("result") != null)
				result = Integer.parseInt(jsonObject.getJSONObject("elements").getString("result"));
			return result;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int checkEmail(String email, String pass, String host,
			String style, String port) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("email", email));
			listClientParams.add(new QbWsClientParam("pass", pass));
			listClientParams.add(new QbWsClientParam("host", host));
			listClientParams.add(new QbWsClientParam("tyle", style));
			listClientParams.add(new QbWsClientParam("port", port));
			JSONObject jsonObject = callPostJson(WsClinentName.OREL_MREL_CHECKEMAILM350, listClientParams);
			int result = 0;
			if (jsonObject.getJSONObject("elements").getString("result") != null)
				result = Integer.parseInt(jsonObject.getJSONObject("elements").getString("result"));
			return result;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	
}
