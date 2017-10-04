package com.ohhay.piepme.mongo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.dao.V300ORDao;
import com.ohhay.core.oracle.dao.V330ORDao;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.P300MPMGDao;
import com.ohhay.piepme.mongo.dao.P300VPMGDao;
import com.ohhay.piepme.mongo.dao.T100PMGDao;
import com.ohhay.piepme.mongo.entities.pieper.P300MMG;
import com.ohhay.piepme.mongo.entities.pieper.P300VMG;
import com.ohhay.piepme.mongo.entities.pieper.Pieper;
import com.ohhay.piepme.mongo.nestedentities.Otag;
import com.ohhay.piepme.mongo.nestedentities.P300VConInfo;
import com.ohhay.piepme.mongo.nestedentities.P300VDetailInfo;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;
import com.ohhay.piepme.mongo.nestedentities.V300PMG;
import com.ohhay.piepme.mongo.nestedentities.VoucherInfo;
import com.ohhay.piepme.mongo.service.P300MPMGService;
import com.ohhay.piepme.mongo.service.P300VPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

@Service(value = SpringBeanNames.SERVICE_NAME_P300VP)
@Scope("prototype")
public class P300VPMGServiceImpl implements P300VPMGService{
	private static Logger log = Logger.getLogger(P300VPMGServiceImpl.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	private P300VPMGDao p300vpmgDao;
	@Autowired
	private P300MPMGService p300mService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Autowired
	private P300MPMGDao p300mDao;
	@Override
	public int createPieper(int fo100, int pp300, String pv301, int pn303, String pv304, 
			 String pv304Thumb, String pv305, int pn306, float pn309, 
			 String pv314, String otags, List<Integer> listFO100R, List<PieperImg> listImgs,
			 Date vd303, Date vd304, String vv308, int vn309) {
		// TODO Auto-generated method stub
		try {
			Date timeCurrent = new Date(adminPMGDao.getCurrentTime());
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null && n100pmg.getK100Con() != null) {
				V300ORDao v300orDao = (V300ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V300OR);
				log.info("--insertTabV300:0, " + pv301 + "," + pv305 + "," + DateHelper.toSQLDate(vd303) + "," + 
							DateHelper.toSQLDate(vd304) + "," + pv304 + "," + AESUtils.decrypt(otags) + "," + vv308 + "," + vn309 + "," + fo100 + "," + n100pmg.getNv101());
				int fv300 = v300orDao.insertTabV300(0, pv301, pv305, DateHelper.toSQLDate(vd303), DateHelper.toSQLDate(vd304), pv304, AESUtils.decrypt(otags), vv308, vn309, fo100, n100pmg.getNv101());
				if( fv300 > 0){
					/*
					 * create P300
					 */
					int id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.P300V);
					P300VMG p300mg = new P300VMG();
					p300mg.setId(id);
					p300mg.setFo100(fo100);
					p300mg.setPd308(timeCurrent);
					p300mg.setPv301(pv301);
					p300mg.setPn303(pn303);
					p300mg.setPv304(pv304);
					p300mg.setPv304Thumb(pv304Thumb);
					p300mg.setPv305(pv305);
					p300mg.setPn306(pn306);
					p300mg.setPn309(pn309);
					p300mg.setPv314(pv314);
					p300mg.setListFO100R(listFO100R);
					p300mg.setDeliveryStt(P300MMG.DELIVERY_STT_SENT);
					// if(loc != null)
					// pieper.setLocation(loc);
					try {
						String tags[] = AESUtils.decrypt(otags).split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
						List<Otag> listOtag = new ArrayList<Otag>();
						for (int i = 0; i < tags.length; i++)
						{
							if(tags[i].trim().length() > 0){
								Otag otag = new Otag();
								otag.setKey(tags[i].trim());
								otag.setKeyStem(ApplicationHelper.getStemOtag(tags[i].toLowerCase()));
								listOtag.add(otag);
							}
						}
						p300mg.setListOtags(listOtag);
					} catch (Exception e) {
						// TODO: handle exception
					}
					/*
					 * create pv302
					 */
					p300mg.setPv302(Pieper.PV302_OFF);
					p300mg.setPieperImgs(listImgs);
					/*
					 * create V300
					 */
					V300PMG v300pmg = new V300PMG(vd303, vd304, vn309, vv308, fv300);
					p300mg.setV300(v300pmg);
					if (templateService.saveDocument(fo100, p300mg, QbMongoCollectionsName.P300V) > 0)
						return id;
				}
				else
					return fv300;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateVN304(int fo100, int pp300, int vn304) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		return templateService.updateOneField(fo100, P300VMG.class, pp300, "V300.VN304", vn304, "PD306");
	}

	@Override
	public List<P300VConInfo> getListPieperOwner(int fo100, String pvSearch, int sort, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return p300vpmgDao.getListPieperOwner(fo100, pvSearch, sort, pnOffset, pnLimit);
	}

	@Override
	public int pushVoucher(int fo100, int fv300OR, String uuid, String pvLogin) {
		// TODO Auto-generated method stub
		V330ORDao v330orDao = (V330ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V330OR);
		String voucherCode = null;
		if(uuid != null && uuid.length() >= 16)
			voucherCode = v330orDao.insertTabV330(0, AESUtils.encrypt(uuid.substring(0, 16)), fo100, fv300OR, pvLogin);
		else
			voucherCode = v330orDao.insertTabV330(0, uuid, fo100, fv300OR, pvLogin);
		log.info("---voucherCode:"+voucherCode);
		if(!"NE".equals(voucherCode)){
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			P300VMG p300vmg = templateService.findDocument(fo100, P300VMG.class, new QbCriteria("V300.FV300OR", fv300OR));
			if(p300vmg != null){
				List<Integer> listFO100R = new ArrayList<Integer>();
				listFO100R.add(fo100);
				int pp300 = p300mService.createPieperV2(p300vmg.getFo100(), p300vmg.getPv301(), Pieper.PV302_OFF, p300vmg.getPn303(), p300vmg.getPv304(), p300vmg.getPv304Thumb(), p300vmg.getPv305(),
										  p300vmg.getPn306(), p300vmg.getPn309(), p300vmg.getPv314(), null, listFO100R, p300vmg.getPieperImgs());
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				templateService.updateOneField(p300vmg.getFo100(), P300MMG.class, pp300, "VOUCHER_CODE", voucherCode, null);
				templateService.updateOneField(p300vmg.getFo100(), P300MMG.class, pp300, "FP300V", p300vmg.getId(), null);
				return pp300;
			}
		}
		return 0;
	}
	@Override
	public int storNoTabP300V(int fo100, int pp300, String pvLogin) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			P300VMG p300v = templateService.findDocumentById(fo100, pp300, P300VMG.class);
			if(p300v != null){
				V300ORDao v300orDao = (V300ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V300OR);
				int result = v300orDao.stornoTabV300O(p300v.getV300().getFv300OR(), pvLogin);
				if(result>0){
					templateService.removeDocumentById(fo100, pp300, P300VMG.class);
					return result;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int useVoucher(int fo100, int fo100b, String voucherCode,  String pvLogin) {
		// TODO Auto-generated method stub
		V330ORDao v330orDao = (V330ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V330OR);
		int rs = v330orDao.usedItTabV330(voucherCode, fo100b, pvLogin);
		if(rs > 0)
			//update mongo info
			p300mDao.updateTabPD310(fo100, fo100b, voucherCode);
		return rs;
	}

	@Override
	public P300VDetailInfo getPieperDetail(int fo100, int pp300) {
		// TODO Auto-generated method stub
		return p300vpmgDao.getPieperDetail(fo100, pp300);
	}

	@Override
	public int deActTabV300O(final int fo100, final int pp300, final String vv307, final String pvLogin) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		final P300VMG p300vmg = templateService.findDocumentById(fo100, pp300, P300VMG.class);
		/*
		 * voucher khac DOJ chi dc tao -> active 1 lan duy nhat
		 */
		//hoac la voucher DOJ hoac active voucher loai khac chưa gửi (VV310 != SENT)
		if(p300vmg!= null
			&& 
		   (
			   V300PMG.VV308_DOJ.equals(p300vmg.getV300().getVv308()) 
			   || 
			   (V300PMG.VV307_ACTIVE.equals(vv307) &&  !V300PMG.VV310_SENT.equals(p300vmg.getV300().getVv310()))
		    )
	     ){
			final int fv300 = p300vmg.getV300().getFv300OR();
			V300ORDao v300orDao = (V300ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V300OR);
			int rs = v300orDao.deActTabV300O(fv300, vv307, pvLogin);
			if(rs > 0){
				templateService.updateOneField(fo100, P300VMG.class, pp300, "V300.VV307", vv307, null);
				templateService.updateOneField(fo100, P300VMG.class, pp300, "V300.VV310",  V300PMG.VV310_SENT, null);
				//gui voucher cho khach hang
				if(!V300PMG.VV308_DOJ.equals(p300vmg.getV300().getVv308())){
					
					Thread thread = new Thread(){
					    public void run(){
					    	TemplateService templateService  = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					    	T100PMGDao t100pmgDao = (T100PMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_T100P);
					    	List<Integer> listFO100 = t100pmgDao.getListFO100EOM(fo100, p300vmg.getV300().getVv308());
					    	log.info("--listFO100 size:"+listFO100.size());
					    	for(int fo100: listFO100)
					    	{
					    		try {
					    			V330ORDao v330orDao = (V330ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V330OR);
						    		//uuid truyen null, find tu n000 o oracle
						    		String voucherCode = v330orDao.insertTabV330(0, null, fo100, p300vmg.getV300().getFv300OR(), ApplicationConstant.PVLOGIN_ANONYMOUS);
						    		log.info("---voucherCode:"+voucherCode);
						    		if(!"NE".equals(voucherCode)){
						    			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
						    			List<Integer> listFO100R = new ArrayList<Integer>();
					    				listFO100R.add(fo100);
					    				int pp300 = p300mService.createPieperV2(p300vmg.getFo100(), p300vmg.getPv301(), Pieper.PV302_OFF, p300vmg.getPn303(), p300vmg.getPv304(), p300vmg.getPv304Thumb(), p300vmg.getPv305(),
					    										  p300vmg.getPn306(), p300vmg.getPn309(), p300vmg.getPv314(), null, listFO100R, p300vmg.getPieperImgs());
					    				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					    				templateService.updateOneField(p300vmg.getFo100(), P300MMG.class, pp300, "VOUCHER_CODE", voucherCode, null);
					    				templateService.updateOneField(p300vmg.getFo100(), P300MMG.class, pp300, "FP300V", p300vmg.getId(), null);
						    		}
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
					    	}
					    	//tu dong off chuong trinh sau khi chay xong
					    	V300ORDao v300orDao = (V300ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V300OR);
					    	v300orDao.deActTabV300O(fv300, V300PMG.VV307_INACTIVE, pvLogin);
							TemplateService templateService2 = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
							templateService2.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
							templateService2.updateOneField(fo100, P300VMG.class, pp300, "V300.VV307",  V300PMG.VV307_INACTIVE, null);
							templateService2.updateOneField(fo100, P300VMG.class, pp300, "V300.VV310",  V300PMG.VV310_SENT, null);
					    }
					};
					thread.start();
					
				}
			}
			return rs;
		}
		return -1;
	}

	@Override
	public List<VoucherInfo> listAllVoucher(int fo100, int pv300, int offset, int limit) {
		// TODO Auto-generated method stub
		return p300vpmgDao.listAllVoucher(fo100, pv300, offset, limit);
	}

	@Override
	public int getTotalVoucher(int fo100, int pv300) {
		// TODO Auto-generated method stub
		return p300vpmgDao.getTotalVoucher(fo100, pv300);
	}

}
