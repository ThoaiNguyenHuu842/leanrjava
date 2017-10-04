package com.ohhay.piepme.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.dao.V300ORDao;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.dao.F150PMGDao;
import com.ohhay.piepme.mongo.entities.interaction.F150PMG;
import com.ohhay.piepme.mongo.nestedentities.V300PMG;
import com.ohhay.piepme.mongo.service.F150PMGService;
import com.ohhay.piepme.mongo.service.P300VPMGService;
import com.ohhay.piepme.mongo.service.T100PMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt
 * create date Oct 28, 2016
 * ohhay-module-piepme
 */
@Service(value= SpringBeanNames.SERVICE_NAME_F150P)
public class F150PMGServiceImpl implements F150PMGService {

	@Autowired
	private F150PMGDao f150pmgDao;
	
	@Override
	public List<F150PMG> listOfTabF150(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return f150pmgDao.listOfTabF150(fo100, offset, limit);
	}

	@Override
	public List<F150PMG> listOfTabF150T(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return f150pmgDao.listOfTabF150T(fo100, offset, limit);
	}

	@Override
	public int insertTabF150(final int fo100, final int fo100t, int fb300, String pieperType, final String uuid) {
		/*
		 * 1) add to COM if fo100t is a business account
		 */
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100t, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100t));
		if(n100pmg != null && n100pmg.getK100Con() != null){
			try {
				Thread thread = new Thread(){
				    public void run(){
				    	T100PMGService t100pmgService = (T100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T100P);
				    	t100pmgService.registerLoyaltyCustomer(fo100, fo100t);
				    	/*
						 * 03/07/2017
						 * 2) get voucher
						 */
				    	if(uuid != null && uuid.trim().length() > 0){
					    	String uuidGen = AESUtils.encrypt(ApplicationHelper.preProcessUIID(uuid).substring(0,16)); 
							V300ORDao v300orDao = (V300ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V300OR);
							int fv300 = v300orDao.checkedTabV300(fo100t, V300PMG.VV308_DOJ, ApplicationConstant.PVLOGIN_ANONYMOUS);
							if(fv300 > 0){
								P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
								p300Service.pushVoucher(fo100, fv300, uuidGen, ApplicationConstant.PVLOGIN_ANONYMOUS);
							}
				    	}
				    }
				};
				thread.start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		/*
		 * 2) insert following
		 */
		return f150pmgDao.insertTabF150(fo100, fo100t,fb300,pieperType);
	}

	@Override
	public String checkFollowStatus(int fo100, int fo100t) {
		// TODO Auto-generated method stub
		return f150pmgDao.checkFollowStatus(fo100, fo100t);
	}

	@Override
	public int storNoTabF150(int fo100, int fo100t) {
		//khong cho unfollow piepme admin
		if(fo100t != ApplicationConstant.FO100_SUPER_ADMIN_PIEPME){
			/*
			 * 2) remove from COM
			 */
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			T100PMG t100pmg = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.FO100E, fo100t));
			int result;
			if(t100pmg != null)
				templateService.removeDocumentById(fo100, t100pmg.getId(), T100PMG.class);
			result = f150pmgDao.storNoTabF150(fo100, fo100t);
			return result;
		}
		return 0;
	}

	@Override
	public int insertTabF150Admin(int fo100) {
		// TODO Auto-generated method stub
		int fo100t = ApplicationConstant.FO100_SUPER_ADMIN_PIEPME;
		/*
		 * 1) add to COM if fo100t is a business account
		 */
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100t, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100t));
		if(n100pmg != null && n100pmg.getK100Con() != null){
			T100PMGService t100pmgService = (T100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T100P);
			t100pmgService.registerLoyaltyCustomer(fo100, fo100t);
		}
		/*
		 * 2) insert following
		 */
		return f150pmgDao.insertTabF150(fo100, fo100t, 0, "");
	}

}
