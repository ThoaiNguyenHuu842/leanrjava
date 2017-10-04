package com.ohhay.web.mongo.core.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebShortBase;
import com.ohhay.web.core.load.util.WebOhhay;
import com.ohhay.web.core.mongo.service.C950MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_C950MG)
@Scope("prototype")
public class C950MGServiceImpl implements C950MGService {
	@Autowired
	private TemplateService templateService;

	@Override
	public int insertC950MG(WebOhhay webOhhay, N950MG n950mg, int fo100, String languageCode,int fc800, String qbMongoColectionName) {
		// TODO Auto-generated method stub
		try {
			OhhayWebShortBase ohhayWebShortBase = new OhhayWebShortBase();
			ohhayWebShortBase.setRefid(ApplicationHelper.convertToMD5(webOhhay.getWebId() + languageCode));
			ohhayWebShortBase.setWebId(webOhhay.getWebId());
			ohhayWebShortBase.setLanguageCode(languageCode);
			ohhayWebShortBase.setFo100(fo100);
			ohhayWebShortBase.setCv201(webOhhay.getHeader());
			ohhayWebShortBase.setCv202(webOhhay.getRealBody());
			ohhayWebShortBase.setCv203(webOhhay.getColor());
			ohhayWebShortBase.setSetOhhayLibraryJS(webOhhay.getSetOhhayLibraryJS());
			ohhayWebShortBase.setFc800(fc800);
			ohhayWebShortBase.setN950mg(n950mg);
			return templateService.saveDocument(fo100, ohhayWebShortBase, qbMongoColectionName);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
}
