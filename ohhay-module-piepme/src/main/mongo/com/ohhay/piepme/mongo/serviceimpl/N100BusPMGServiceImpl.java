package com.ohhay.piepme.mongo.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.oracle.N000OR;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.service.N000ORService;
import com.ohhay.core.oracle.service.N200ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.N100BusPMGDao;
import com.ohhay.piepme.mongo.nestedentities.K100PMG;
import com.ohhay.piepme.mongo.nestedentities.K100SummaryInfo;
import com.ohhay.piepme.mongo.service.N100BusPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author TuNt
 * create date Jun 15, 2017
 * ohhay-module-piepme
 */
@Service(value = SpringBeanNames.SERVICE_NAME_N100BUSP)
@Scope("prototype")
public class N100BusPMGServiceImpl implements N100BusPMGService{
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	private TemplateService templateMGService;
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N100BUSP)
	private N100BusPMGDao n100BusPmgDao;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Override
	public N100PMG getN100ByUUID(String uuid) {
		// TODO Auto-generated method stub
		N000ORService n000orService = (N000ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N000OR);
		List<N000OR> list = n000orService.listOfTabN000PieDN(AESUtils.encrypt(uuid.substring(0, 16)),
				ApplicationConstant.PVLOGIN_ANONYMOUS);
		if (list != null && list.size() > 0) {
			int fo100 = list.get(0).getFo100();
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null && n100pmg.getK100Con() != null) {
				templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
				if (m900mg != null) {
					m900mg.setUrlAvarta(m900mg.getUrlAvarta());
					n100pmg.setM900mg(m900mg);
					return n100pmg;
				}
			}else{
				return null;
			}
				
		}
		return null;
	}

	@Override
	public N100PMG getN100ByNV101(String nickName, String securityNumber) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.NV101, nickName + securityNumber));
		if (n100pmg != null && n100pmg.getK100Con() != null) {
			templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
			M900MG m900mg = templateService.findDocumentById(n100pmg.getFo100(), n100pmg.getFo100(), M900MG.class);
			if (m900mg != null) {
				m900mg.setUrlAvarta(m900mg.getUrlAvarta());
				n100pmg.setM900mg(m900mg);
				return n100pmg;
			}
		}
		return null;
	}

	@Override
	public List<N100PMG> listOfTabN100Sug(String search, int offset, int limit) {
		// TODO Auto-generated method stub
		return n100BusPmgDao.listOfTabN100Sug(search, offset, limit);
	}

	@Override
	public List<K100SummaryInfo> listSuggestedEnterprise(int fo100, String pvSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		return n100BusPmgDao.listSuggestedEnterprise(fo100, pvSearch, ApplicationHelper.createPVSearchStem(pvSearch), offset, limit);
	}

	@Override
	public int update(int fo100, String nv106, String nv107, String nv108, String kv101, String kv102, String kv107,String nv201, String nv204, String nv207, String nv208, String nv209, String nv212,
			String login) {
		// TODO Auto-generated method stub
		templateMGService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateMGService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		K100PMG k100pmg;
		if (n100pmg != null && n100pmg.getK100Con()!=null) {
			n100pmg.setNv106(nv106);
			n100pmg.setNv107(nv107);
			n100pmg.setNv108(nv108);
			k100pmg = n100pmg.getK100();
			k100pmg.setKv101(kv101);
			k100pmg.setKv101Stem(ApplicationHelper.getStemString(kv101));
			k100pmg.setKv102(kv102);
			k100pmg.setKv105(K100PMG.KV105_NEW);
			k100pmg.setKv106(kv107);
			k100pmg.setKd109(null);
			k100pmg.setKd106(new Date(adminPMGDao.getCurrentTime()));
			
			int resultSave =  templateMGService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
			if(resultSave > 0){
				N200ORService n200orService = (N200ORService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_N200OR);
				int result = n200orService.insertTabN200VND(nv201, nv204, nv207, nv208, nv209, nv212, fo100, login);
				return result;
			}else{
				return 0;
			}
		}
		return 0;
	}

}
