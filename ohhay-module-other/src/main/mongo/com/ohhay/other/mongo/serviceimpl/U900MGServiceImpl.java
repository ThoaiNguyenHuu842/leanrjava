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
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.other.entities.mongo.domain.U900MG;
import com.ohhay.other.entities.mongo.domain.U910MG;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.mongo.dao.U900MGDao;
import com.ohhay.other.mongo.service.U900MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_U900MG)
@Scope("prototype")
public class U900MGServiceImpl implements U900MGService {
	private static Logger log = Logger.getLogger(U900MGServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_U900MG)
	U900MGDao dao;
	@Autowired
	TemplateService templateService;
	@Override
	public int insertTabU900(int fo100, int u910Pos, String uv911, String uv912, int un913) {
		// TODO Auto-generated method stub
		return dao.insertTabU900(fo100, u910Pos, uv911, uv912, un913);
	}
	/**
	 * update 15/09/2014
	 * bo sung map domain for subdomain O!hay 
	 * (non-Javadoc)
	 * @see com.ohhay.other.mongo.service.U900MGService#checkTabDomain(java.lang.String)
	 */
	@Override
	public U910MG checkTabDomain(String uv911) {
		// TODO Auto-generated method stub
		U910MG u910mg =  dao.checkTabDomain("http://" + uv911 + "/");
		//find sub domain
		if(u910mg == null) {
			String token[] = uv911.split("\\.");
			if(uv911.indexOf("oohhay.com") > 0 && token.length == 3){
				M900MG m900mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, M900MG.class, QbMongoFiledsName.PART, new QbCriteria(QbMongoFiledsName.MV923, token[0]));
				if(m900mg != null)
				{
					u910mg = new U910MG();
					u910mg.setUn914(0);
					u910mg.setUv912(m900mg.getMv907());
				}
			}
		}
		return u910mg;
	}
	@Override
	public int changeDomainOhhayFunction(int domainIndex, int mn914, int fo100) {
		// TODO Auto-generated method stub
		try {
			U900MG u900mg = templateService.findDocumentById(0, fo100, U900MG.class);
			for (int i = 0; i < u900mg.getListU910mgs().size(); i++) {
				// save by index
				if (i == domainIndex) {
					U910MG u910mg = u900mg.getListU910mgs().get(i);
					u910mg.setUn914(mn914);
					break;
				}
			}
			return templateService.saveDocument(ApplicationConstant.FO100_SUPER_ADMIN, u900mg, QbMongoCollectionsName.U900);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int saveDomain(String ov101, DomainCriteria domainCiteria, int fo100, AuthenticationRightInfo authenticationRightInfo, boolean appCall) {
		return 0;
	}

	@Override
	public int deleteDomain(int domainIndex, int fo100) {
		// TODO Auto-generated method stub
		try {
			U900MG u900mg = templateService.findDocumentById(0, fo100, U900MG.class);
			u900mg.getListU910mgs().remove(domainIndex);
			int kq = templateService.saveDocument(ApplicationConstant.FO100_SUPER_ADMIN, u900mg, QbMongoCollectionsName.U900);
			return kq;

		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	@Override
	public String checkRightAddDomain(AuthenticationRightInfo authenticationRightInfo, int fo100) {
		// TODO Auto-generated method stub
		log.info(authenticationRightInfo);
 		if(authenticationRightInfo.isDOM_RED_DES())
			return ApplicationConstant.RE_VAILD_RIGHT;
 		//packet 3 domain
 		if(authenticationRightInfo.isDOM_RED_PRO())
 		{
 			List<U920MG> listU920 = templateService.findDocuments(fo100, U920MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
 			if(listU920.size() < 3)
 				return ApplicationConstant.RE_VAILD_RIGHT;
 			else
 				return MVCResourceBundleUtil.getResourceBundle("setting.ohaydomain.error3").replace("$domainNumber", String.valueOf(listU920.size()));
 		}
 		//packet 2 domain
 		else if(authenticationRightInfo.isDOM_RED_EXP())
 		{
 			List<U920MG> listU920 = templateService.findDocuments(fo100, U920MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
 			if(listU920.size() < 2)
 				return ApplicationConstant.RE_VAILD_RIGHT;
 			else
 				return MVCResourceBundleUtil.getResourceBundle("setting.ohaydomain.error3").replace("$domainNumber", String.valueOf(listU920.size()));
 		}
 		//packet 1 domain
 		else if(authenticationRightInfo.isDOM_RED_OPT())
 		{
 			List<U920MG> listU920 = templateService.findDocuments(fo100, U920MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
 			if(listU920.size() == 0)
 				return ApplicationConstant.RE_VAILD_RIGHT;
 			else
 				return MVCResourceBundleUtil.getResourceBundle("setting.ohaydomain.error3").replace("$domainNumber", String.valueOf(listU920.size()));
 		}
		return MVCResourceBundleUtil.getResourceBundle("updrade.message");
	}	
	

}
