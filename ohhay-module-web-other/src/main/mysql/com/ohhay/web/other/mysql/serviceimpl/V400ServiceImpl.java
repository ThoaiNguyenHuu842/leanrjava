package com.ohhay.web.other.mysql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.web.other.mysql.dao.V400Dao;
import com.ohhay.web.other.mysql.service.V400Service;
@Service(value = SpringBeanNames.SERVICE_NAME_V400)
@Scope("prototype")
public class V400ServiceImpl implements V400Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V400)
	private V400Dao v400Dao;

	@Override
	public int insertTabV400(int pnPV400, String pvWV401, String ov1pvWV402, String pvWV403, String pvWV404, String pvWV405, int pnFO100, int pnFC800, int pnFD000 , int pnFH020, String pvLogin) {
		// TODO Auto-generated method stub
		return v400Dao.insertTabV400(pnPV400, pvWV401, ov1pvWV402, pvWV403, pvWV404, pvWV405, pnFO100, pnFC800, pnFD000, pnFH020, pvLogin);
	}
	
}
