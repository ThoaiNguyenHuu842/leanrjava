package com.ohhay.core.mongo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.R950MG;
import com.ohhay.core.mongo.dao.R950MGDao;
import com.ohhay.core.mongo.service.R950MGService;

@Service(value = SpringBeanNames.SERVICE_NAME_R950MG)
@Scope("prototype")
public class R950MGServiceImpl implements R950MGService {

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R950MG)
	R950MGDao r950mgDao;

	@Override
	public int insertTabR950(int fo100, int fo100t, int webId, String rv951, String rv952, String rv953, String rv954, String rv955, String rv957, String rv958, String rv959, String rv961, String rv962, String rv963) {
		// TODO Auto-generated method stub
		return r950mgDao.insertTabR950(fo100, fo100t, webId, rv951, rv952, rv953, rv954, rv955, rv957, rv958, rv959, rv961, rv962, rv963);
	}

	@Override
	public List<R950MG> reportTab001(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		return r950mgDao
				.reportTab001(fo100, webId, dateCod, dateFromString, dateToString);
	}

	@Override
	public List<R950MG> reportTab002(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		return r950mgDao
				.reportTab002(fo100, webId, dateCod, dateFromString, dateToString);
	}

	@Override
	public List<R950MG> reportTab003(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		return r950mgDao
				.reportTab003(fo100, webId, dateCod, dateFromString, dateToString);
	}

	@Override
	public List<R950MG> reportTab004(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		return r950mgDao
				.reportTab004(fo100, webId, dateCod, dateFromString, dateToString);
	}

	@Override
	public List<R950MG> reportTab005(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		return r950mgDao
				.reportTab005(fo100, webId, dateCod, dateFromString, dateToString);
	}

	@Override
	public List<R950MG> reportTab006(int fo100, int webId, int dateCod, String dateFromString, String dateToString) {
		// TODO Auto-generated method stub
		return r950mgDao
				.reportTab006(fo100, webId, dateCod, dateFromString, dateToString);
	}

}
