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
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.dao.Q100Dao;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.F150PMGDao;
import com.ohhay.piepme.mongo.dao.T100PMGDao;
import com.ohhay.piepme.mongo.entities.interaction.G100PMG;
import com.ohhay.piepme.mongo.service.T100PMGService;
import com.ohhay.piepme.mongo.service.T110PMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiVt 
 * date 21/09/2016
 */
@Service(value = SpringBeanNames.SERVICE_NAME_T100P)
@Scope("prototype")
public class T100PMGServiceImpl implements T100PMGService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private T100PMGDao t100PmgDao;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private F150PMGDao f150pmgDao;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Autowired
	private T110PMGService t110pmgService;
	@Override
	public List<T100PMG> getListT100(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return t100PmgDao.getListT100(fo100, offset, limit);
	}

	@Override
	public List<T100PMG> getListT100Def(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return t100PmgDao.getListT100Def(fo100, offset, limit);
	}

	@Override
	public N100PMG checkPiepmeId(int fo100, int fo100F) {
		// TODO Auto-generated method stub
		Q100Dao q100Dao = (Q100Dao) ApplicationHelper
				.findBean(SpringBeanNames.REPOSITORY_NAME_Q100);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		/*
		 * 1) get FO100 from piepme id
		 */
		N100PMG n100pmgF = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100F));
		T100PMG t100Cus = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), 
																			 new QbCriteria("TV104", T100PMG.TYPE_EOM), 
																			 new QbCriteria("TV101", AESUtils.encrypt(String.valueOf(fo100F))));
		if (t100Cus == null && n100pmgF.getK100Con() != null)
			return n100pmgF;
		return null;
	}

	@Override
	public int registerLoyaltyCustomer(int fo100, int fo100F) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1) get FO100 from piepme id
			 */
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			N100PMG n100pmgF = checkPiepmeId(fo100, fo100F);
			if (n100pmg != null && n100pmgF != null) {
				templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				M900MG m900mg = templateService.findDocumentById(fo100F, fo100F, M900MG.class);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				/*
				 * 3) business account 1) khi user nhập piepep id của doanh
				 * nghiệp: - Add user này vào group COM của doanh nghiệp: >>
				 * G100 của group này có thêm field TYPE = "COM", mỗi doanh
				 * nghiệp chỉ có 1 group này - Channel để doanh nghiệp quản
				 * lý KHTT: >> T100 có TV104 = "COM", mỗi doanh nghiệp chỉ
				 * có 1 channel này >>> app xử lý khi doanh nghiệp vào
				 * channel này thì hiển thị button + (tạo pieper) - Tạo 1
				 * channel để khách hàng theo dõi doanh nghiệp: >> T100 có
				 * TV104 = "EOM" với otag là FO100 của doanh nghiệp
				 * 
				 * 2) doanh nghiệp tạo pieper gửi KHTT: >> app xử lý
				 * p300BWebService/createPieper truyền pn306 = 2
				 * 
				 * 3) doanh nghiệp xem pieper ở channel "COM" đã gửi khách
				 * hàng: >> app xử lý p300BWebService/getListPieper: FO100 =
				 * FO100 doanh nghiệp, FO100F = FO100 doanh nghiệp, SORT = 2
				 * 
				 * 4) user xem pieper đã được doanh nghiệp gửi ở channel
				 * "EOM", backend xử lý ở app k có thay đổi
				 */

				/*
				 * 3.1) add user vao group COM cua doanh nghiep
				 */
				G100PMG g100pmg = templateService.findDocument(fo100F, G100PMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100F), new QbCriteria(QbMongoFiledsName.TYPE, G100PMG.TYPE_COM));
				if (g100pmg == null) {
					int newId = (int) sequenceService.getNextSequenceIdPiepMe(fo100F, QbMongoCollectionsName.G100);
					g100pmg = new G100PMG();
					g100pmg.setId(newId);
					g100pmg.setFo100(fo100F);
					List<Integer> list = new ArrayList<Integer>();
					list.add(fo100);
					g100pmg.setGv101(G100PMG.TYPE_COM);
					g100pmg.setGv102(m900mg.getUrlAvarta());
					g100pmg.setType(G100PMG.TYPE_COM);
					// g100pmg.setGv102(groupIcon);
					g100pmg.setListFO100R(list);
					g100pmg.setGd166(new Date(adminPMGDao.getCurrentTime()));
					g100pmg.setGd168(new Date(adminPMGDao.getCurrentTime()));
				}
				else {
					boolean hasAddedFo100 = false;
					for (Integer fo100i : g100pmg.getListFO100R()) {
						if (fo100i == fo100) {
							hasAddedFo100 = true;
							break;
						}
					}
					if (!hasAddedFo100) {
						g100pmg.getListFO100R().add(fo100);
						g100pmg.setGd166(new Date(adminPMGDao.getCurrentTime()));
					}
				}
				templateService.saveDocument(fo100F, g100pmg, QbMongoCollectionsName.G100);
				/*
				 * 3.2) tao channel quan ly KHTT cho doanh nghiep
				 */
				T100PMG t100Bus = templateService.findDocument(fo100F, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100F), new QbCriteria("TV104",T100PMG.TYPE_COM));
				if (t100Bus == null) {
					int newT100Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T100);
					T100PMG t100mg = new T100PMG();
					t100mg.setId(newT100Id);
					t100mg.setFo100(fo100F);
					t100mg.setTv101(T100PMG.TYPE_COM);
					t100mg.setTv102("");
					t100mg.setTv103(T100PMG.TYPE_COM);
					t100mg.setTv104(T100PMG.TYPE_COM);
					t100mg.setTl146(new Date(adminPMGDao.getCurrentTime()));
					t100mg.setTl148(new Date(adminPMGDao.getCurrentTime()));
					templateService.saveDocument(fo100F, t100mg, QbMongoCollectionsName.T100);
				}
				/*
				 * 3.3) tao channel de khach hang theo doi doanh nghiep
				 */
				T100PMG t100Cus = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100), new QbCriteria("TV104",T100PMG.TYPE_EOM), new QbCriteria("TV101",AESUtils.encrypt(String.valueOf(fo100F))));
				if (t100Cus == null) {
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					int newT100Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T100);
					T100PMG t100mg = new T100PMG();
					t100mg.setId(newT100Id);
					t100mg.setFo100(fo100);
					t100mg.setTv101(AESUtils.encrypt(String.valueOf(fo100F)));
					t100mg.setTv102(m900mg.getUrlAvarta());
					t100mg.setTv103(n100pmgF.getK100Con().getKv101());
					t100mg.setTv104(T100PMG.TYPE_EOM);
					t100mg.setTl146(new Date(adminPMGDao.getCurrentTime()));
					t100mg.setTl148(new Date(adminPMGDao.getCurrentTime()));
					templateService.saveDocument(fo100, t100mg, QbMongoCollectionsName.T100);
					templateService.updateOneField(fo100, T100PMG.class, newT100Id, "FO100E", fo100F, null);
				}
				/*
				 * 3.4) tu dong dang ky vao COM1 gan nhat neu la EDU
				 */
				if(T100PMG.COM_TYPE_EDU.equals(t100Bus.getComType()) && n100pmg.getLocationR() != null){
					List<T110PMG> listT100Nearest = t110pmgService.getNearestCOM1(fo100, n100pmg.getLocationR().getCoordinates().get(1), 
																			      n100pmg.getLocationR().getCoordinates().get(0), t100Bus.getId());
					if(listT100Nearest.size() == 1 && T110PMG.TYPE_COM1.equals(listT100Nearest.get(0).getTv119()))
						t110pmgService.registerLoyaltyCustomerCOM1(fo100, listT100Nearest.get(0).getId());
				}
				return fo100F;

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int createT100(int fo100, String tv101, String tv102, String tv103) {
		// TODO Auto-generated method stub
		try {
			int newT100Id = (int) sequenceService
					.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T100);
			T100PMG t100mg = new T100PMG();
			t100mg.setId(newT100Id);
			t100mg.setFo100(fo100);
			t100mg.setTv101(tv101);
			t100mg.setTv102(tv102);
			t100mg.setTv103(tv103);
			t100mg.setTv105(AESUtils.decrypt(tv101));
			t100mg.setTv105Stem(ApplicationHelper.getStemOtag(AESUtils.decrypt(tv101)));
			t100mg.setTl146(new Date(adminPMGDao.getCurrentTime()));
			t100mg.setTl148(new Date(adminPMGDao.getCurrentTime()));
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			int kq = templateService.saveDocument(fo100, t100mg, QbMongoCollectionsName.T100);
			try {
				templateService.setAccessDBcentPiepme(true);
				templateService.saveDocument(fo100, t100mg, QbMongoCollectionsName.T100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return newT100Id; 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info(e);
		}
		return 0;
	}

	@Override
	public int updateCOMBanner(int fo100, String tv106) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T100PMG t100pmg = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria("TV104", T100PMG.TYPE_COM));
		if(t100pmg != null)
		{
			t100pmg.setTv106(tv106);
			return templateService.saveDocument(fo100, t100pmg, QbMongoCollectionsName.T100);
		}
		return 0;
	}

	@Override
	public int removeT100(int fo100, int pt100) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T100PMG t100pmg = templateService.findDocument(fo100, T100PMG.class, new QbCriteria( QbMongoFiledsName.ID, pt100), new QbCriteria(QbMongoFiledsName.FO100, fo100));
		if(t100pmg != null){
			//is EOM channel -> remove channel and unfollow 
			if(T100PMG.TYPE_EOM.equals(t100pmg.getTv104()))
			{
				//khong cho unfollow channel piepme
				if(t100pmg.getFo100e() != ApplicationConstant.FO100_SUPER_ADMIN_PIEPME)
					f150pmgDao.storNoTabF150(fo100, t100pmg.getFo100e());
			}
			//is not COM channel
			if(!T100PMG.TYPE_COM.equals(t100pmg.getTv104())){
				int kq = templateService.removeDocumentById(fo100, pt100, T100PMG.class);
				try {
					templateService.setAccessDBcentPiepme(true);
					templateService.removeDocumentById(fo100, pt100, T100PMG.class);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return kq;
			}
		}
		return -1;
	}
}
