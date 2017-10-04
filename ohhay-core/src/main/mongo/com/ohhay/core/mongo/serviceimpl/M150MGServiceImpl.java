package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.M150CMG;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.entities.mongo.other.UserOtag;
import com.ohhay.core.mongo.dao.M150MGDao;
import com.ohhay.core.mongo.service.M150MGService;
@Service(value = SpringBeanNames.SERVICE_NAME_M150MG)
@Scope("prototype")
public class M150MGServiceImpl implements M150MGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M150MG)
	M150MGDao dao;

	@Override
	public String getNewTopic(int fo100) {
		// TODO Auto-generated method stub
		return dao.getNewTopic(fo100);
	}

	@Override
	public List<M150MG> findM150Index(int limit) {
		// TODO Auto-generated method stub
		return dao.findM150Index(limit);
	}

	public List<UserOtag> getListUserOtags(int fo100){
		return dao.getListUserOtags(fo100);
	}

	@Override
	public JSONArray listOfTabM150(int fo100Login, int fo100View, int offset, int limit, String langCode) {
		// TODO Auto-generated method stub
		return dao.listOfTabM150(fo100Login, fo100View, offset, limit, langCode);
	}

	@Override
	public JSONArray listOfTabM150one(int pnFO100, int pnFM150){
		return dao.listOfTabM150one(pnFO100, pnFM150);
	}
	
	@Override
	public List<M150CMG> findM150C(int pnFM150){
		return dao.findM150C(pnFM150);
	}
}
