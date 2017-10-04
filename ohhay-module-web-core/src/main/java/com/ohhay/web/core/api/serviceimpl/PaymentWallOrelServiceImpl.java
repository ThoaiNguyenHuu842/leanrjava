package com.ohhay.web.core.api.serviceimpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.V500OR;
import com.ohhay.core.oracle.service.V500ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.dao.PaymentWallOrelDao;
import com.ohhay.web.core.api.service.PaymentWallOrelService;

@Service(value = SpringBeanNames.SERVICE_NAME_PAYMENTOREL)
@Scope("prototype")
public class PaymentWallOrelServiceImpl implements PaymentWallOrelService {
	private static Logger log = Logger
			.getLogger(PaymentWallOrelServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_PAYMENTOREL)
	PaymentWallOrelDao paymentWallOrelDao;

	@Override
	public String getPayMentWidget(int fo100, String widgetType, String skuId, String userAddress, Date userBirthDate, String userCity, String userCountry, String userZip, String userFisrtName, String userLastName, String userState, String userSex, String userName, String userEmail) {
		String ss[] = skuId.split("-");
		// optimal
		String realSkuID = ApplicationConstant.PACKET_OPTIMAL;
		// expert - pro - designer
		int year = Integer.parseInt(ss[1]);
		if (ss[0].equals("1")) {
			realSkuID = ApplicationConstant.PACKET_EXPERT;
		}
		else if (ss[0].equals("2")) {
			realSkuID = ApplicationConstant.PACKET_PRO;
		}
		else if (ss[0].equals("3")) {
			realSkuID = ApplicationConstant.PACKET_DESIGNER;
		}
		V500ORService v500orService = (V500ORService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_V500OR);
		log.info("---listOfTabV500:" + realSkuID + "," + null + ","
				+ ApplicationConstant.PVLOGIN_ANONYMOUS);
		List<V500OR> list = v500orService
				.listOfTabV500(realSkuID, null, ApplicationConstant.PVLOGIN_ANONYMOUS);
		if (list != null && list.size() == 1) {
			V500OR v500or = list.get(0);
			String pmwProductName = realSkuID;
			String pmwPrice = String.valueOf(v500or.getVn505());
			String pmwNgoaite = v500or.getVv504();
			String pmwRecurring = String.valueOf(v500or.getVn510());
			String packetName = v500or.getVv501();
			return paymentWallOrelDao.getPayMentWidget(fo100
					+ ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN
					+ v500or.getPv500()
					+ ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN
					+ year, widgetType, pmwProductName, pmwPrice, pmwNgoaite, pmwRecurring, packetName, userAddress, userBirthDate, userCity, userCountry, userZip, userFisrtName, userLastName, userState, userSex, userName, userEmail);
		}
		return null;
	}

}
