package com.ohhay.core.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.P950MG;
import com.ohhay.core.mongo.dao.P950MGDao;
import com.ohhay.core.mongo.service.P950MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author TuNt
 * create date Jul 6, 2016
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_P950MG)
@Scope("prototype")
public class P950MGServiceImpl implements P950MGService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_P950MG)
	P950MGDao p950mgDao;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	TemplateService templateService;

	@Override
	public List<P950MG> listOfTabP950(int fo100, String src, int offset, int limit) {
		// TODO Auto-generated method stub
		return p950mgDao.listOfTabP950(fo100, src, offset, limit);
	}

	@Override
	public long getStorageUse(int fo100) {
		long result = 0;
		List<P950MG> listP950 = templateService.findDocuments(fo100, P950MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		for (P950MG p950mg : listP950) {
			result += ApplicationHelper.getFileSize(p950mg.getPv951());
		}
		return result;
	}
}
