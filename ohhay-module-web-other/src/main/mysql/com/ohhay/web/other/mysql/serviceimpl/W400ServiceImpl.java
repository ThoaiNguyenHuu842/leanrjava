package com.ohhay.web.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.other.mysql.dao.W400Dao;
import com.ohhay.web.other.mysql.service.W400Service;

@Service(value = SpringBeanNames.SERVICE_NAME_W400)
@Scope("prototype")
public class W400ServiceImpl implements W400Service {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_W400)
	private W400Dao w400Dao;

	@Override
	public int inserttabw400(int pnPW400, String pvWV401, String ov1pvWV402, String pvWV403, String fpvWV404k100, String pvWV404, int pnFO100, int pnFC800, int pnFH020, String pvLogin) {
		// TODO Auto-generated method stub
		return w400Dao
				.inserttabw400(pnPW400, pvWV401, ov1pvWV402, pvWV403, fpvWV404k100, pvWV404, pnFO100, pnFC800, pnFH020, pvLogin);
	}
}
