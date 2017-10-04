package com.ohhay.other.mongo.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.DomainCriteria;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.entities.mongo.domain.topic.U800MG;
import com.ohhay.other.entities.mongo.domain.topic.U810MG;
import com.ohhay.other.mongo.dao.U800MGDao;
import com.ohhay.other.mongo.service.U800MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_U800MG)
@Scope("prototype")
public class U800MGServiceImpl implements U800MGService {
	private static Logger log = Logger.getLogger(U800MGServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_U800MG)
	U800MGDao dao;
	@Autowired
	TemplateService templateService;
	@Override
	public int insertTabU800(int fo100, int u810Pos, String uv811, String uv812, int un813) {
		// TODO Auto-generated method stub
		return dao.insertTabU800(fo100, u810Pos, uv811, uv812, un813);
	}
	@Override
	public U810MG checkTabDomain(String uv811) {
		// TODO Auto-generated method stub
		return dao.checkTabDomain(uv811);
	}
	@Override
	public int changeDomainOhhayFunction(int domainIndex, int mn814, int fo100) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			U800MG u800mg = templateService.findDocumentById(0, fo100, U800MG.class);
//			for (int i = 0; i < u800mg.getListU810mgs().size(); i++) {
//				// save by index
//				if (i == domainIndex) {
//					U810MG u810mg = u800mg.getListU810mgs().get(i);
//					u810mg.setUn814(mn814);
//					break;
//				}
//			}
			return templateService.saveDocument(ApplicationConstant.FO100_SUPER_ADMIN, u800mg, QbMongoCollectionsName.U800);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int saveDomain(String ov101, DomainCriteria domainCiteria, int fo100, AuthenticationRightInfo authenticationRightInfo, boolean appCall) {/*
		// TODO Auto-generated method stub
		try {
			int role = 0;
			if(appCall == true)
				role = ApplicationConstant.RE_VAILD_RIGHT;
			else
				role = checkRightAddDomain(authenticationRightInfo);
			if(role == ApplicationConstant.RE_VAILD_RIGHT){
				String wv812 = null;
				switch (domainCiteria.getType()) {
					case 1: 
						wv812 = ov101;
						break;
					case 2: 
						// domain poin to other web type
					default:
						break;
				}
				StringBuilder keywordBuilder = new StringBuilder();
				if (!domainCiteria.getDomainName().contains("http://"))
					keywordBuilder.append("http://");
				if (domainCiteria.getDomainName().charAt(domainCiteria
						.getDomainName().length() - 1) != '/')
					keywordBuilder.append(domainCiteria.getDomainName() + "/");
				else
					keywordBuilder.append(domainCiteria.getDomainName());
				U800MGService u800mgService = (U800MGService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_U800MG);
				U810MG u810mg = u800mgService.checkTabDomain(keywordBuilder.toString());
				if(u810mg == null){
					int kq = u800mgService.insertTabU800(ApplicationConstant.FO100_SUPER_ADMIN, domainCiteria.getPos(), keywordBuilder.toString(), wv812, domainCiteria.getType());
					return kq;
				}
				else
					return -1;
			}
			return role;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	*/
		return 1;
		}

	@Override
	public int deleteDomain(int domainIndex, int fo100) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			U800MG u800mg = templateService.findDocumentById(0, fo100, U800MG.class);
//			u800mg.getListU810mgs().remove(domainIndex);
			int kq = templateService.saveDocument(ApplicationConstant.FO100_SUPER_ADMIN, u800mg, QbMongoCollectionsName.U800);
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public String checkRightAddDomain(AuthenticationRightInfo authenticationRightInfo) {
		// TODO Auto-generated method stub
		log.info(authenticationRightInfo);
		if(authenticationRightInfo.isDOM_RED_DES() || authenticationRightInfo.isDOM_RED_PRO() || authenticationRightInfo.isDOM_RED_EXP() || authenticationRightInfo.isDOM_RED_OPT())
			return ApplicationConstant.RE_VAILD_RIGHT;
		return MVCResourceBundleUtil.getResourceBundle("upgrade.message");
	}	
	

}
