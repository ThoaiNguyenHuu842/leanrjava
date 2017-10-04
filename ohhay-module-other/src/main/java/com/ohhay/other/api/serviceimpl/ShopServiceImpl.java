package com.ohhay.other.api.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.OhhayDefaultData;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.shop.J910MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.api.service.K100OrelService;
import com.ohhay.other.api.service.ShopService;

/**
 * @author ThoaiNH
 *
 */
@Service(value = SpringBeanNames.SERVICE_NAME_SHOP_SERVICE)
@Scope("prototype")
public class ShopServiceImpl implements ShopService {
	private static Logger log = Logger.getLogger(ShopServiceImpl.class);
	@Autowired
	TemplateService templateService;
	@Override
	public int createWebShop(Q100 q100, String menuName, String menuIcon) {
		// TODO Auto-generated method stub
		if (q100.getM900mg().getMv901() == null
				|| q100.getM900mg().getMv901().trim().length() == 0
				|| q100.getM900mg().getMv902() == null
				|| q100.getM900mg().getMv902().trim().length() == 0
				|| q100.getM900mg().getMv903() == null
				|| q100.getM900mg().getMv903().trim().length() == 0)
			return -2;
		else {
			/*
			 * 2) insert k100 orel
			 */
			K100OrelService k100OrelService = (K100OrelService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_K100OREL);
			log.info("---insertTabK100Orel:" + 0 + ","
					+ q100.getM900mg().getMv901Decrypt() + ","
					+ q100.getM900mg().getMv902Decrypt() + ","
					+ q100.getM900mg().getMv903Decrypt() + "," + 0 + ","
					+ q100.getPo100() + "," + q100.getOv101() + ","
					+ q100.getQv101());
			String fk100andfn100 = k100OrelService.insertTabK100Orel(0, q100.getM900mg().getMv901Decrypt(), q100
							.getM900mg().getMv902Decrypt(), q100.getM900mg()
							.getMv903Decrypt(), 0, q100.getPo100(), q100
							.getOv101(), q100.getQv101());
			/*
			 * 3) create J910MG
			 */
			if (fk100andfn100 != null && fk100andfn100.length() > 0)
			{
				
				J910MG j910mg = new J910MG();
				j910mg.setJv911(OhhayDefaultData.DEFAULT_SHOP_TITLE);
				j910mg.setJv915(OhhayDefaultData.DEFAULT_SHOP_ICON);
				j910mg.setId(q100.getPo100());
//				System.out.println("-----CUOI----"+fk100andfn100);
//				System.out.println(ApplicationHelper.getIdOfSuperId(fk100andfn100, 0)+"-----FK100--------FN100---"+ApplicationHelper.getIdOfSuperId(fk100andfn100, 1));
				j910mg.setFk100(Integer.valueOf(ApplicationHelper.getIdOfSuperId(fk100andfn100, 0)));
				j910mg.setFn100(Integer.valueOf(ApplicationHelper.getIdOfSuperId(fk100andfn100, 1)));
				templateService.saveDocument(q100.getPo100(), j910mg, QbMongoCollectionsName.J910);
				return 1;
			}
			else
				return -1;
		}
	}

}
