package com.ohhay.piepme.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.Q170OR;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.dao.Q170ORDao;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.dao.T100PMGDao;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;
import com.ohhay.piepme.mongo.service.C100PMGService;
import com.ohhay.piepme.mongo.service.F150PMGService;
import com.ohhay.piepme.mongo.service.Q170PMGService;

/**
 * @author ThoaiNH
 * create Aug 29, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_Q170P)
@Scope("prototype")
public class Q170PMGServiceImpl implements Q170PMGService{
	private static Logger log = Logger.getLogger(P300VPMGServiceImpl.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	private F150PMGService f150pmgService;
	@Autowired
	private C100PMGService c100pmgService;
	@Autowired
	private T100PMGDao t100pmgDao;
	@Override
	public int createQ170(int fo100, int fo100u, List<Integer> listFQ400) {
		// TODO Auto-generated method stub
		if(c100pmgService.checkFriendStatus(fo100, fo100u) == 3){
			Q170ORDao q170orDao = (Q170ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q170OR);
			q170orDao.stornoTabQ170(0, fo100u, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
			for(int fq400: listFQ400){
				Q170ORDao q170orDao2 = (Q170ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q170OR);
				log.info("---insertTabQ170:" + fq400 +","+ fo100u +","+ fo100 +","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				q170orDao2.insertTabQ170(fq400, fo100u, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
			}
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			T100PMG t100pmg = templateService.findDocument(fo100u, T100PMG.class, new QbCriteria("FO100", fo100u), new QbCriteria("FO100E", fo100));
			if(t100pmg == null)
			{
				f150pmgService.insertTabF150(fo100u, fo100, 0, null, null);
				t100pmg = templateService.findDocument(fo100u, T100PMG.class, new QbCriteria("FO100", fo100u), new QbCriteria("FO100E", fo100));
			}
			t100pmg.setComType(T100PMG.COM_TYPE_ADM);
			return templateService.saveDocument(fo100u, t100pmg, QbMongoCollectionsName.T100);
		}
		return -1;
	}
	@Override
	public int stornoTabQ170(int fq400, int fo100u, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		Q170ORDao q170orDao = (Q170ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q170OR);
		if(fq400 == 0){
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			T100PMG t100pmg = templateService.findDocument(fo100u, T100PMG.class, new QbCriteria("FO100", fo100u), new QbCriteria("FO100E", fo100));
			t100pmg.setComType(null);
			return templateService.saveDocument(fo100u, t100pmg, QbMongoCollectionsName.T100);
		}
		return q170orDao.stornoTabQ170(fq400, fo100u, fo100, pvLogin);
	}
	@Override
	public List<N100SummaryInfo> listOfTabQ170Users(int pnFO100, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		List<N100SummaryInfo> list = t100pmgDao.listOfTabUserADM(pnFO100, pnOffset, pnLimit);
		for(N100SummaryInfo n100SummaryInfo: list){
			List<Integer> fq400s = new ArrayList<Integer>();
			List<Q170OR> q170ors = listOfTabQ170(n100SummaryInfo.getFo100(), pnFO100, n100SummaryInfo.getNv106());
			for(Q170OR q170or: q170ors)
				fq400s.add(q170or.getFq400());
			n100SummaryInfo.setFq400s(fq400s);
		}
		return list;
	}
	@Override
	public List<Q170OR> listOfTabQ170(int fo100u, int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		Q170ORDao q170orDao = (Q170ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q170OR);
		return q170orDao.listOfTabQ170(fo100u, fo100, pvLogin);
	}
	@Override
	public int createQ170Multi(int fo100, Map<Integer, List<Integer>> mapDecentral) {
		// TODO Auto-generated method stub
		try {
			for (Entry<Integer, List<Integer>> entry : mapDecentral.entrySet()) {
			    int fo100u = entry.getKey();
			    List<Integer> listFQ400s = entry.getValue();
			    createQ170(fo100, fo100u, listFQ400s);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
