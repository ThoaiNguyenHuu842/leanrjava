package com.ohhay.web.core.api.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.wsclient.QbWsClientParam;
import com.ohhay.base.wsclient.QbWsClientSupport;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.WsClinentName;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.web.core.api.dao.PaymentWallOrelDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_PAYMENTOREL)
@Scope("prototype")
public class PaymentWallOrelDaoImpl  extends QbWsClientSupport implements PaymentWallOrelDao {

	@Override
	public String getPayMentWidget(String pmwuser, String widgetType, String pmwProductName, String pmwPrice, String pmwNgoaite, String pmwRecurring, String packetName, String userAddress, Date userBirthDate, 
											  String userCity, String userCountry, String userZip, String userFisrtName,
											  String userLastName, String userState, String userSex, String userName, String userEmail) {
		// TODO Auto-generated method stub
		try {
			List<QbWsClientParam> listClientParams = new ArrayList<QbWsClientParam>();
			listClientParams.add(new QbWsClientParam("pmwuser", pmwuser));
			listClientParams.add(new QbWsClientParam("typewidget", widgetType));
			listClientParams.add(new QbWsClientParam("pmwproductname", pmwProductName));
			listClientParams.add(new QbWsClientParam("pmwprice", pmwPrice));
			listClientParams.add(new QbWsClientParam("pmwngoaite", pmwNgoaite));
			listClientParams.add(new QbWsClientParam("pmwpackage", packetName));
			listClientParams.add(new QbWsClientParam("pmwrecurring", pmwRecurring));
			listClientParams.add(new QbWsClientParam("cusaddress", userAddress));
			listClientParams.add(new QbWsClientParam("cusbirthday", DateHelper.formatDateShort(userBirthDate)));
			listClientParams.add(new QbWsClientParam("cuscity", userCity));
			listClientParams.add(new QbWsClientParam("cuscountry", userCountry));
			listClientParams.add(new QbWsClientParam("cus_zip", userZip));
			listClientParams.add(new QbWsClientParam("cusfirstname", userFisrtName));
			listClientParams.add(new QbWsClientParam("cuslastname", userLastName));
			listClientParams.add(new QbWsClientParam("cusstate", userState));
			listClientParams.add(new QbWsClientParam("cussex", userSex));
			listClientParams.add(new QbWsClientParam("cususername", userName));
			listClientParams.add(new QbWsClientParam("email", userEmail));
			JSONObject jsonObject = callPostJson(WsClinentName.OREL_PAYMENTWALL_GETWIDGET, listClientParams);
			if(jsonObject.getString("status").equals("success"))
			{
				JSONObject val = new JSONObject(jsonObject.getString("elements"));
				return val.getString("result");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
