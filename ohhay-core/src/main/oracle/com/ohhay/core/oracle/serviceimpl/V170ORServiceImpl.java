package com.ohhay.core.oracle.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.oracle.V170OR;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.oracle.dao.V170ORDao;
import com.ohhay.core.oracle.service.V170ORService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author TuNt
 * create date Mar 16, 2017
 * ohhay-core
 */
@Service(value = SpringBeanNames.SERVICE_NAME_V170OR)
@Scope("prototype")
public class V170ORServiceImpl implements V170ORService{

	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_V170OR)
	private V170ORDao v170orDao;

	@Override
	public String checkedTabV170(int fp100, int fo100, int fo100d, String login) {
		// TODO Auto-generated method stub
		return v170orDao.checkedTabV170(fp100, fo100, fo100d, login);
	}

	@Override
	public int usedItTabV170O(int fv170, String vv172, int vn175, int fo100u, String pvLogin) {
		// TODO Auto-generated method stub
		return v170orDao.usedItTabV170O(fv170, vv172, vn175, fo100u, pvLogin);
	}

	@Override
	public List<V170OR> listOfTabV170(int fo100, int fp100, int fo100d, int offset, int limit,String pvLogin) {
		// TODO Auto-generated method stub
		List<V170OR> list = v170orDao.listOfTabV170(fo100, fp100, fo100d, offset,  limit, pvLogin);
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		for(V170OR v170or: list){
			M900MG m900mg = templateService.findDocumentById(v170or.getFo100d(), v170or.getFo100d(), M900MG.class);
			if(m900mg != null)
				v170or.setUrlAvarta(m900mg.getUrlAvarta());
		}
		return list;
	}
	
}
