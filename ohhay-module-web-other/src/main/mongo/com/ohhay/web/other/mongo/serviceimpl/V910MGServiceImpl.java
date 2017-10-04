package com.ohhay.web.other.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.videomarketing.V910MG;
import com.ohhay.web.other.mongo.dao.V910MGDao;
import com.ohhay.web.other.mongo.service.V910MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_V910MG)
@Scope("prototype")
public class V910MGServiceImpl implements V910MGService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V910MG)
	V910MGDao v910mgDao;

	@Override
	public int changeM940Index(int fo100, int pv910, int videoId, int newIndex) {
		// TODO Auto-generated method stub
		return v910mgDao.changeM940Index(fo100, pv910, videoId, newIndex);
	}

	@Override
	public int deleteVideo(int fo100, int pv910, int videoId) {
		// TODO Auto-generated method stub
		return v910mgDao.deleteVideo(fo100, pv910, videoId);
	}

	@Override
	public List<V910MG> loadListV910(int fo100) {
		// TODO Auto-generated method stub
		return v910mgDao.loadListV910(fo100);
	}
	

}
