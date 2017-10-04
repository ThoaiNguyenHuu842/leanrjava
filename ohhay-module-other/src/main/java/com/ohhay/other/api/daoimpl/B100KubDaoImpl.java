package com.ohhay.other.api.daoimpl;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.wsclient.QbWsClientParam;
import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;
import com.ohhay.other.api.dao.B100KubDao;
import com.ohhay.other.api.dao.K100OrelDao;
@Repository(value = SpringBeanNames.REPOSITORY_NAME_B100KUB)
@Scope("prototype")
public class B100KubDaoImpl extends QbWsClientSupport implements B100KubDao{

	@Override
	public int insertTabB100(int pnFK100, int pnPB100, String pvBV101, String pvBV102, String pvBV103, String pvBV104, String pvBV105, String pvBV106, int pnFB050, String pvLOGIN) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("pnFK100", pnFK100));
			listClientParams.add(new QbWsClientParam("pnPB100", pnPB100));
			listClientParams.add(new QbWsClientParam("pvBV101", pvBV101));
			listClientParams.add(new QbWsClientParam("pvBV102", pvBV102));
			listClientParams.add(new QbWsClientParam("pvBV103", pvBV103));
			listClientParams.add(new QbWsClientParam("pvBV104", pvBV104));
			listClientParams.add(new QbWsClientParam("pvBV105", pvBV105));
			listClientParams.add(new QbWsClientParam("pvBV106", pvBV106));
			listClientParams.add(new QbWsClientParam("pnFB050", pnFB050));
			listClientParams.add(new QbWsClientParam("pvLOGIN", pvLOGIN));
			callPostJson(WsClinentName.KUB_BKUB_INSERTABB100, listClientParams);
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
