package com.ohhay.web.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.other.mysql.dao.B400Dao;
import com.ohhay.web.other.mysql.service.B400Service;

@Service(value = SpringBeanNames.SERVICE_NAME_B400)
@Scope("prototype")
public class B400ServiceImpl implements B400Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_B400)
	private B400Dao b400Dao;
	@Override
	public int inserttabb400(int pnPB400, String pvBV401, String ov1pvBV402,
			String pvBV403, String fpvBV404k100, String pvBV404, int pnFO100,
			int pnFC800, int pnFH020, String pvLogin) {
		// TODO Auto-generated method stub
		return b400Dao.inserttabb400(pnPB400, pvBV401, ov1pvBV402, pvBV403, fpvBV404k100, pvBV404, pnFO100, pnFC800, pnFH020, pvLogin);
	}

}
