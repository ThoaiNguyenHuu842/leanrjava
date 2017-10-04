package com.ohhay.piepme.mongo.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.channel.T120PMG;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.N100PMGDao;
import com.ohhay.piepme.mongo.dao.T110PMGDao;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;
import com.ohhay.piepme.mongo.service.T110PMGService;

/**
 * @author ThoaiNH
 * create Jul 28, 2017
 */
@Service(value = SpringBeanNames.SERVICE_NAME_T110P)
@Scope("prototype")
public class T110PMGServiceImpl implements T110PMGService{
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Autowired
	private T110PMGDao t110pmgDao;
	@Autowired
	private N100PMGDao n100pmgDao;
	@Override
	public int createT110COM1(int fo100, int pt110, String tv111, String tv112, double latitude, double longitude, String addressFullName, List<Integer> fo100s) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T100PMG t100pmg = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100),
																			 new QbCriteria(QbMongoFiledsName.TV104, T100PMG.TYPE_COM),
																			 new QbCriteria(QbMongoFiledsName.COM_TYPE, T100PMG.COM_TYPE_EDU));
		if(t100pmg != null){
			if(tv111 != null && !tv111.isEmpty() && latitude > 0 && longitude > 0 && !addressFullName.isEmpty() && !fo100s.isEmpty()){
				try {
					GeoDataPointMG location = new GeoDataPointMG(longitude, latitude, addressFullName);
					if(pt110 == 0)
					{
						pt110 = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T110);
						T110PMG t110pmg = new T110PMG(pt110, t100pmg.getId(), fo100, tv111, tv112, location, fo100s);
						if(templateService.saveDocument(fo100, t110pmg, QbMongoCollectionsName.T110) > 0)
							return pt110; 
					}
					else {
						T110PMG t110pmg = templateService.findDocument(fo100, T110PMG.class, new QbCriteria(QbMongoFiledsName.ID, pt110), new QbCriteria(QbMongoFiledsName.FO100, fo100));
						if(t110pmg != null){
							t110pmg = new T110PMG(pt110, t100pmg.getId(), fo100, tv111, tv112, location, fo100s);
							if(templateService.saveDocument(fo100, t110pmg, QbMongoCollectionsName.T110) > 0)
								return pt110; 
						}
					}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			return 0;
		}
		return -1;
	}

	@Override
	public int createT110COM2(int fo100, int pt110, int ft110, String tv111, String tv112, String tv115, List<Integer> fo100s) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T110PMG t110COM1 = templateService.findDocumentById(fo100, ft110, T110PMG.class);
		if(t110COM1 != null){
			if(t110COM1.getFo100s().contains(fo100)){
				if(tv111 != null && !tv111.isEmpty() && !fo100s.isEmpty()){
					try {
						if(pt110 == 0)
						{
							pt110 = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T110);
							T110PMG t110pmg = new T110PMG(pt110, t110COM1.getFt100(), t110COM1.getId(), fo100, tv111, tv112, tv115, fo100s);
							if(templateService.saveDocument(fo100, t110pmg, QbMongoCollectionsName.T110) > 0)
								return pt110; 
						}
						else {
							T110PMG t110pmg = templateService.findDocument(fo100, T110PMG.class, new QbCriteria(QbMongoFiledsName.ID, pt110), new QbCriteria(QbMongoFiledsName.FO100, fo100));
							if(t110pmg != null){
								t110pmg = new T110PMG(pt110, t110COM1.getFt100(), t110COM1.getId(), fo100, tv111, tv112, tv115, fo100s);
								if(templateService.saveDocument(fo100, t110pmg, QbMongoCollectionsName.T110) > 0)
									return pt110; 
							}
						}
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				return 0;
			}
			return -2;
		}
		return -1;
	}

	@Override
	public List<T110PMG> getListT110COM1(int fo100, int ft100, int offset, int limit) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		return templateService.findDocuments(fo100, T110PMG.class, QbMongoFiledsName.ID, Direction.DESC, offset, limit, new QbCriteria(QbMongoFiledsName.FT100, ft100),
																													    new QbCriteria("TV119", T110PMG.TYPE_COM1));
	}

	@Override
	public List<T110PMG> getListT110COM2(int fo100, int ft110, int offset, int limit) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		return templateService.findDocuments(fo100, T110PMG.class, QbMongoFiledsName.ID, Direction.DESC, offset, limit, new QbCriteria("FT110", ft110),
				   																									    new QbCriteria("TV119", T110PMG.TYPE_COM2));
	}

	@Override
	public int registerLoyaltyCustomerCOM1(int fo100, int ft110) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T110PMG t110COM1 = templateService.findDocumentById(fo100, ft110, T110PMG.class);
		//nguoi quan ly k dc join kenh do chinh minh tao
		if(t110COM1 != null && T110PMG.TYPE_COM1.equals(t110COM1.getTv119()) && fo100 != t110COM1.getFo100() && !t110COM1.getFo100s().contains(fo100)){
			try {
				/*
				 * 1) kiem tra user da join COM1 nao trong COM chua
				 */
				T110PMG t110EOMOld = templateService.findDocument(fo100, T110PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100),
																					 new QbCriteria("FT100", t110COM1.getFt100()),
																					 new QbCriteria("TV119", T110PMG.TYPE_EOM));
				/*
				 * 2) xoa channel EOM01 user da join truoc do
				 */
				if(t110EOMOld != null)
					templateService.removeDocumentById(fo100, t110EOMOld.getId(), T110PMG.class);
				/*
				 * 3) tao channel EOM01 moi
				 */
				int pt110 = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T110);
				T110PMG t110EOMNew = new T110PMG(pt110, t110COM1.getFt100(), t110COM1.getId(), fo100, T110PMG.TYPE_EOM);
				return templateService.saveDocument(fo100, t110EOMNew, QbMongoCollectionsName.T110);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int registerLoyaltyCustomerCOM2(int fo100, int ft110, String regisCode) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T110PMG t110COM2 = templateService.findDocumentById(fo100, ft110, T110PMG.class);
		//nguoi quan ly k dc join kenh do chinh minh tao
		if(t110COM2 != null && T110PMG.TYPE_COM2.equals(t110COM2.getTv119()) && fo100 != t110COM2.getFo100() && !t110COM2.getFo100s().contains(fo100)){
			try {
				/*
				 * 0) user phai theo doi COM1 truoc do
				 */
				T110PMG t110EOM1 = templateService.findDocument(fo100, T110PMG.class, new QbCriteria("FT110", t110COM2.getFt110()), new QbCriteria("TV119", "EOM"));
				if(t110EOM1 != null){
					/*
					 * 1) kiem tra user da join COM2 truoc do chua
					 */
					T110PMG t110EOMOld = templateService.findDocument(fo100, T110PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100),
							 										  					    new QbCriteria("FT110", t110COM2.getId()));
					if(t110EOMOld == null){
						//kiem tra ma xac nhan
						if(regisCode.trim().equals(t110COM2.getTv113())){
							/*
							 * 2) tao channel EOM cho phu huynh
							 */
							if("C".equals(t110COM2.getTv115()))
							{
								int pt120 = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T120);
								T120PMG t120pmg = new T120PMG(pt120, t110COM2.getFt100(), t110COM2.getId(), fo100, T110PMG.TYPE_EOM_PH);
								if(templateService.saveDocument(fo100, t120pmg, QbMongoCollectionsName.T120) > 0)
									return 1;//cho xac nhan
							}
							else
							{
								int pt110 = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T110);
								T110PMG t110EOMNew = new T110PMG(pt110, t110COM2.getFt100(), t110COM2.getId(), fo100, T110PMG.TYPE_EOM_PH);
								if(templateService.saveDocument(fo100, t110EOMNew, QbMongoCollectionsName.T110) > 0)
									return 2;//tham gia thanh cong
							}
						}
						else if(regisCode.trim().equals(t110COM2.getTv114())){
							/*
							 * 2) tao channel EOM cho phu huynh
							 */
							if("C".equals(t110COM2.getTv115()))
							{
								int pt120 = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T120);
								T120PMG t120pmg = new T120PMG(pt120, t110COM2.getFt100(), t110COM2.getId(), fo100, T110PMG.TYPE_EOM_HS);
								if(templateService.saveDocument(fo100, t120pmg, QbMongoCollectionsName.T120) > 0)
									return 1;//cho xac nhan
							}
							else
							{
								int pt110 = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T110);
								T110PMG t110EOMNew = new T110PMG(pt110, t110COM2.getFt100(), t110COM2.getId(), fo100, T110PMG.TYPE_EOM_HS);
								if(templateService.saveDocument(fo100, t110EOMNew, QbMongoCollectionsName.T110) > 0)
									return 2;//tham gia thanh cong
							}
						} 
						return -1; //xac nhan sai
					}
					else
						return -2;//da tham gia truoc do
				}
				else
					return -3;//chua tham gia COM1
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return 0;//error
	}
	@Override
	public T110PMG getT110Info(int fo100, int pt110) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T110PMG t110pmg = templateService.findDocumentById(fo100, pt110, T110PMG.class);
		if(t110pmg != null && (t110pmg.getFo100() == fo100 || t110pmg.getFo100s().contains(fo100)))
		{
			t110pmg.setListAdmin(n100pmgDao.listOfTabN100Summary(t110pmg.getFo100s()));
			return t110pmg;
		}
		return null;
	}

	@Override
	public List<T110PMG> getListT110(int fo100, int ft100) {
		// TODO Auto-generated method stub
		return t110pmgDao.getListT110EOM(fo100, ft100);
	}

	@Override
	public List<T110PMG> getNearestCOM1(int fo100, double latitude, double longitude, int ft100) {
		// TODO Auto-generated method stub
		return t110pmgDao.getNearestCOM1(fo100, latitude, longitude, ft100);
	}

	@Override
	public List<N100SummaryInfo> listOfTabT110Users(int pnFO100, int pnFT110, String pnTV119, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		return t110pmgDao.listOfTabT110Users(pnFO100, pnFT110, pnTV119, pnOffset, pnLimit);
	}

	@Override
	public int updateTv112(int fo100, int pt110, String tv112) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T110PMG t110pmg = templateService.findDocumentById(fo100, pt110, T110PMG.class);
		if(t110pmg != null && (t110pmg.getFo100() == fo100 || t110pmg.getFo100s().contains(fo100))){
			return templateService.updateOneField(fo100, T110PMG.class, pt110, "TV112", tv112, "TL118");
		}
		return -1;
	}

	@Override
	public int updateTv120(int fo100, int pt110, String tv120) {
		// TODO Auto-generated method stub
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		T110PMG t110pmg = templateService.findDocumentById(fo100, pt110, T110PMG.class);
		if(t110pmg != null && (t110pmg.getFo100() == fo100 || t110pmg.getFo100s().contains(fo100))){
			return templateService.updateOneField(fo100, T110PMG.class, pt110, "TV120", tv120, "TL118");
		}
		return -1;
	}

}
