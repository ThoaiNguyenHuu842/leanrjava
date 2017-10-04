package com.ohhay.piepme.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.G100PMGDao;
import com.ohhay.piepme.mongo.entities.interaction.C100PMG;
import com.ohhay.piepme.mongo.entities.interaction.G100PMG;
import com.ohhay.piepme.mongo.entities.pieper.G100Status;
import com.ohhay.piepme.mongo.entities.pieper.N100Status;
import com.ohhay.piepme.mongo.service.G100PMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

@Service(value = SpringBeanNames.SERVICE_NAME_G100P)
@Scope("prototype")
public class G100PMGServiceImpl implements G100PMGService{
	private TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private G100PMGDao g100pmgDao;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService  = templateService;
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
	}
	@Override
	public int createGroup(int fo100, String groupName, String groupIcon) {
		// TODO Auto-generated method stub
		try {
			int newId = (int)sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.G100);
			G100PMG g100pmg = new G100PMG();
			g100pmg.setId(newId);
			g100pmg.setFo100(fo100);
			List<Integer> list = new ArrayList<Integer>();
			list.add(fo100);
			g100pmg.setGv101(groupName);
			g100pmg.setGv102(groupIcon);
			g100pmg.setListFO100R(list);
			g100pmg.setGd166(new Date(adminPMGDao.getCurrentTime()));
			g100pmg.setGd168(new Date(adminPMGDao.getCurrentTime()));
			templateService.saveDocument(fo100, g100pmg, QbMongoCollectionsName.G100);
			return newId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int addFriendToGroup(int fo100, int fg100, String fo100String) {
		// TODO Auto-generated method stub
		return g100pmgDao.addFriendToGroup(fo100, fg100, fo100String);
	}
	@Override
	public int removeFriendFromGroup(int fo100, int fg100, int fo100r) {
		return g100pmgDao.removeFriendFromGroup(fo100, fg100, fo100r);
	}
	@Override
	public List<G100PMG> listOfTabG100(int fo100, int offset, int limit, String type) {
		// TODO Auto-generated method stub
		return g100pmgDao.listOfTabG100(fo100, offset, limit, type);
	}
	@Override
	public List<N100PMG> listOfTabG100Friends(int fo100, int pg100, int offset, int limit, String kv105) {
		// TODO Auto-generated method stub
		return g100pmgDao.listOfTabG100Friends(fo100, pg100, offset, limit, kv105);
	}
	@Override
	public List<C100PMG> listOfTabFriendsToAdd(int fo100, int pg100, int offset, int limit) {
		// TODO Auto-generated method stub
		return g100pmgDao.listOfTabFriendsToAdd(fo100, pg100, offset, limit);
	}
	@Override
	public List<N100PMG> listOfTabG100FriendsDis(int fo100, String fg100String, int offset, int limit, String kv105) {
		// TODO Auto-generated method stub
		return g100pmgDao.listOfTabG100FriendsDis(fo100, fg100String, offset, limit, kv105);
	}
	@Override
	public int removeFriendFromGroup(int fo100, int fg100, List<Integer> fo100Del) {
		// TODO Auto-generated method stub
		try {
			G100PMG g100pmg = templateService.findDocumentById(fo100, fg100, G100PMG.class);
			if(g100pmg != null && g100pmg.getListFO100R() != null){
				Iterator<Integer> iterator = g100pmg.getListFO100R().iterator();
				while(iterator.hasNext()){
					int fo100r = iterator.next();
					for(Integer fo100d: fo100Del){
						if(fo100r == fo100d)
						{
							iterator.remove();
							break;
						}
					}
				}
			}
			return templateService.saveDocument(fo100, g100pmg, QbMongoCollectionsName.G100);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public N100Status getFO100OfLoyaltyCustomer(int fo100) {
		// TODO Auto-generated method stub
		return g100pmgDao.getFO100OfLoyaltyCustomer(fo100);
	}
	@Override
	public G100Status listOfTabG100Ids(int fo100) {
		// TODO Auto-generated method stub
		return g100pmgDao.listOfTabG100Ids(fo100);
	}

}
