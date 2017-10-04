package com.ohhay.web.core.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.web.core.mysql.dao.L400Dao;
import com.ohhay.web.core.mysql.service.L400Service;

@Service(value = SpringBeanNames.SERVICE_NAME_L400)
@Scope("prototype")
public class L400ServiceImpl implements L400Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_L400)
	private L400Dao l400Dao;

	@Override
	public int generateTabL400(int fo100, int fc800, String pvLogin) {
		// TODO Auto-generated method stub
		return l400Dao.generateTabL400(fo100, fc800, pvLogin);
	}

	@Override
	public List<ComtabItem> combTabL400(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return l400Dao.combTabL400(fo100, pvLogin);
	}

	@Override
	public int inserttabL400(int pnPL400, String pvLV401, String pvLV402, String pvLV403, 
			String pvLV404, String pvLV405, int pnFO100, int pnFC800, int pnFD000,  int fh020, String pvLogin) {
		// TODO Auto-generated method stub
		return l400Dao.inserttabL400(pnPL400, pvLV401, pvLV402, pvLV403, pvLV404, pvLV405, pnFO100, pnFC800,pnFD000, fh020, pvLogin);
	}
	
	
	

}
