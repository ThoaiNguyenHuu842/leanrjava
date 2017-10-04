package com.ohhay.piepme.mongo.serviceimpl;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100Piepme;
import com.ohhay.core.entities.mongo.other.GeoDataPointMG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.oracle.N000OR;
import com.ohhay.core.mongo.dao.M900MGDao;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.dao.Q100Dao;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.oracle.dao.V220ORDao;
import com.ohhay.core.oracle.service.N000ORService;
import com.ohhay.core.oracle.service.N100ORService;
import com.ohhay.core.utils.AESUtilsPiepme;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.other.mysql.service.O050Service;
import com.ohhay.other.mysql.service.O100Service;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.dao.N100PMGDao;
import com.ohhay.piepme.mongo.entities.other.P150PMG;
import com.ohhay.piepme.mongo.entities.other.S250PMG;
import com.ohhay.piepme.mongo.entities.pieper.N100CAFMG;
import com.ohhay.piepme.mongo.entities.pieper.N100Status2;
import com.ohhay.piepme.mongo.entities.pieper.N100Status3;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300CPMG;
import com.ohhay.piepme.mongo.entities.pieper.P300PPMG;
import com.ohhay.piepme.mongo.nestedentities.A100PMG;
import com.ohhay.piepme.mongo.nestedentities.DeviceToken;
import com.ohhay.piepme.mongo.nestedentities.K100DetailPMG;
import com.ohhay.piepme.mongo.nestedentities.K100PMG;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.service.P300MPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

@Service(value = SpringBeanNames.SERVICE_NAME_N100P)
@Scope("prototype")
public class N100PMGServiceImpl implements N100PMGService {
	private static Logger log = Logger.getLogger(N100PMGServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M900MG)
	private M900MGDao m900mgDao;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	private TemplateService templateMGService;
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N100P)
	private N100PMGDao n100pmgDao;
	private VelocityEngine vec = (VelocityEngine) ApplicationHelper.findBean("velocityEngine");
	@Autowired
	private O050Service o050Service;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private AdminPMGDao adminPMGDao;
	@Override
	public N100PMG register(String name, String uuid) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100PMG = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.MV101, name));
		if (n100PMG == null) {
			n100PMG = new N100PMG();
			try {
				n100PMG.setId((int) sequenceService.getNextSequenceIdPiepMe(ApplicationConstant.FO100_SUPER_ADMIN,
						QbMongoCollectionsName.N100));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			n100PMG.setNv101(name);
			n100PMG.setNv102(uuid);
			templateService.saveDocument(ApplicationConstant.FO100_SUPER_ADMIN, n100PMG, QbMongoCollectionsName.N100);
			return n100PMG;
		}
		return null;
	}

	@Override
	public N100PMG login(int fo100, String uuid) {
		// TODO Auto-generated method stub
		Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		Q100Piepme q100 = q100Service.qhayCheckTabQ100Piepme(uuid, fo100);
		if (q100 != null) {
			N100PMG n100pmg = templateService.findDocument(q100.getPo100(), N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()));
			templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
			M900MG m900mg = templateService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
			m900mg.setUrlAvarta(m900mg.getUrlAvarta());
			n100pmg.setM900mg(m900mg);
			try {
				n100pmg.setQ100(q100);
				if (n100pmg.getA100() != null && n100pmg.getA100().getAd110() != null) {
					String dateString = DateHelper.formatDateShort(n100pmg.getA100().getAd110());
					n100pmg.getA100().setAd110Str(dateString);
				}
				log.info("---n100pmg:" + n100pmg);
				/*
				 * get system info
				 */
				try {
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					S250PMG s250pmg = templateService.findDocumentById(775, 1, S250PMG.class);
					n100pmg.setS250pmg(s250pmg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			log.info("---n100pmg:" + n100pmg);
			return n100pmg;
		}
		return null;
	}

	@Override
	public String sendMailConfirmNewAccount(String mail) {
		// TODO Auto-generated method stub
		/*
		 * 1) prepare data
		 */
		VelocityContext context = new VelocityContext();
		Template t = null;
		String mailContent1 = null;
		String emailSubject = null;
		/*
		 * 2) create itans list
		 */
		t = vec.getTemplate("piepme_email_confirm_otp_template_en.vm", "UTF-8");
		mailContent1 = ". Respect !";
		emailSubject = "PiepMe - Your OTP code";
		StringWriter writer = new StringWriter();
		/*
		 * 3) merge with content
		 */
		String code = o050Service.ohayInsertTabO050(mail, "84", "phongvt");
		context.put("mail", mail);
		context.put("mailLinkConfirm", code);
		context.put("mailContent1", mailContent1);
		t.merge(context, writer);
		M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
		int kq = m350Service.sendMailTabM350Confirm(ApplicationConstant.FO100_SUPER_ADMIN, mail, null,
				ApplicationHelper.decodeTopicString(emailSubject),
				ApplicationHelper.decodeTopicString(writer.toString()), mail);
		if (kq > 0)
			return code;
		return null;
	}

	@Override
	public String sendMailConfirmPiepmeAccount(M900MG m900mg, String mail, String qv101, int fo100) {
		// TODO Auto-generated method stub
		/*
		 * 1) prepare data
		 */
		VelocityContext context = new VelocityContext();
		Template t = null;
		String mailContent1 = null;
		String emailSubject = null;
		/*
		 * 2) create itans list
		 */
		t = vec.getTemplate("piepme_email_confirm_otp_template_en.vm", "UTF-8");
		mailContent1 = ". Respect !";
		emailSubject = "PiepMe - Your OTP code";
		StringWriter writer = new StringWriter();
		/*
		 * 2) merge with content
		 */
		context.put("mail", mail);
		String code = o050Service.ohayInsertTabO050(mail, "84", "phongvt");
		context.put("mailLinkConfirm", code);
		context.put("mailContent1", mailContent1);
		t.merge(context, writer);
		M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
		int kq = m350Service.sendMailTabM350Confirm(fo100, mail, null,
				ApplicationHelper.decodeTopicString(emailSubject),
				ApplicationHelper.decodeTopicString(writer.toString()), qv101);
		if (kq > 0)
			return code;
		return null;
	}

	@Override
	public List<N100PMG> listOfTabN100(int fo100, String nickName, String digits) {
		// TODO Auto-generated method stub
		return n100pmgDao.listOfTabN100V2(fo100, ApplicationHelper.getStemString(nickName), digits);
	}

	@Override
	public int updateNV106(int fo100, String nv106) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		int kq = 0;
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		if (n100pmg != null && nv106.trim().length() > 0) {
			/*
			 * verify nv106
			 */
			N100PMG n100pmgTemp = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.NV101, nv106 + n100pmg.getNv107()));
			if (n100pmgTemp == null || n100pmgTemp.getFo100() == fo100) {
				n100pmg.setNv106(nv106);
				n100pmg.setNv101(nv106 + n100pmg.getNv107());
				n100pmg.setNv101Stem(ApplicationHelper.getStemString(n100pmg.getNv101()));
				n100pmg.setNl166(new Date(adminPMGDao.getCurrentTime()));
				kq = templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
				/*
				 * update db center
				 */
				try {
					templateService.setAccessDBcentPiepme(true);
					templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return kq;
	}

	@Override
	public int updateNV107(int fo100, String nv107) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		int kq = 0;
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		if (n100pmg != null && nv107.trim().length() > 0) {
			/*
			 * verify nv107
			 */
			N100PMG n100pmgTemp = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.NV101, n100pmg.getNv106() + nv107));
			if (n100pmgTemp == null || n100pmgTemp.getFo100() == fo100) {
				n100pmg.setNv107(nv107);
				n100pmg.setNv101(n100pmg.getNv106() + nv107);
				n100pmg.setNv101Stem(ApplicationHelper.getStemString(n100pmg.getNv101()));
				n100pmg.setNl166(new Date(adminPMGDao.getCurrentTime()));
				kq = templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
				/*
				 * update db center
				 */
				try {
					templateService.setAccessDBcentPiepme(true);
					templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return kq;
	}

	@Override
	public int updateNV108(int fo100, String nv108) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		int kq = 0;
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		if (n100pmg != null && nv108.trim().length() > 0) {
			n100pmg.setNv108(nv108);
			n100pmg.setNl166(new Date(adminPMGDao.getCurrentTime()));
			log.info(n100pmg.toString());
			kq = templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
			/*
			 * update db center
			 */
			try {
				templateService.setAccessDBcentPiepme(true);
				templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return kq;
	}

	@Override
	public List<DeviceToken> listOfTabN100Token(String fo100s) {
		// TODO Auto-generated method stub
		return n100pmgDao.listOfTabN100Token(fo100s);
	}

	@Override
	public N100PMG getN100ByNV101(String nickName, String securityNumber) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
				new QbCriteria("NV101_STEM", ApplicationHelper.getStemString(nickName + securityNumber)));
		if (n100pmg != null) {
			templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
			M900MG m900mg = templateService.findDocumentById(n100pmg.getFo100(), n100pmg.getFo100(), M900MG.class);
			if (m900mg != null) {
				m900mg.setUrlAvarta(m900mg.getUrlAvarta());
				n100pmg.setM900mg(m900mg);
				return n100pmg;
			}
		}
		return null;
	}

	@Override
	public int updateUUID(String uuid , String nv002, int fo100, int fp150) {
		// user has confirm otp before
		String uuidGen = ApplicationHelper.preProcessUIID(uuid);
		templateMGService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		P150PMG p150pmg = templateMGService.findDocumentById(fo100, fp150, P150PMG.class);
		if (p150pmg != null && p150pmg.getPd157() != null && p150pmg.getPd158() == null) {
			p150pmg.setPd158(new Date(adminPMGDao.getCurrentTime()));
			templateMGService.saveDocument(fo100, p150pmg, QbMongoCollectionsName.P150);
			O100Service o100Servcie = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
			String piepmeKeyLogin = AESUtilsPiepme.createPiepmeKey(uuidGen, fo100);
			String piepmeKey = piepmeKeyLogin.substring(0, (int) piepmeKeyLogin.length() * 2 / 3);
			/*
			 * insert N000 oracle
			 */
			N000ORService n000orService = (N000ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N000OR);
			String uuidEncrypted = AESUtils.encrypt(uuidGen.substring(0, 16));
			n000orService.insertTabN000Pie(uuidEncrypted, nv002, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
			/*
			 * update mysql
			 */
			return o100Servcie.updateTabO100Key(piepmeKeyLogin, piepmeKey, fo100,
					ApplicationConstant.PVLOGIN_ANONYMOUS);
		}
		return 0;
	}

	@Override
	public List<N100PMG> listOfTabN100Sug(String search, int offset, int limit) {
		// TODO Auto-generated method stub
		return n100pmgDao.listOfTabN100Sug(search, offset, limit);
	}

	@Override
	public N100PMG loginV2(String nickName, String digits, String uuid) {
		// TODO Auto-generated method stub
		String uuidGen = ApplicationHelper.preProcessUIID(uuid);
		
		Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.NV101, nickName.trim() + digits.trim()));
		if (n100pmg != null) {
			Q100Piepme q100 = q100Service.qhayCheckTabQ100Piepme(uuidGen, n100pmg.getFo100());
			if (q100 != null) {
				templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				M900MG m900mg = templateService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
				m900mg.setUrlAvarta(m900mg.getUrlAvarta());
				n100pmg.setM900mg(m900mg);
				n100pmg.setQ100(q100);
				if (n100pmg.getA100() != null && n100pmg.getA100().getAd110() != null) {
					String dateString = DateHelper.formatDateShort(n100pmg.getA100().getAd110());
					n100pmg.getA100().setAd110Str(dateString);
				}
				log.info("---n100pmg:" + n100pmg);
				/*
				 * get system info
				 */
				try {
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					S250PMG s250pmg = templateService.findDocumentById(775, 1, S250PMG.class);
					n100pmg.setS250pmg(s250pmg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return n100pmg;

			}
		}
		return null;
	}

	@Override
	public N100PMG getN100ByUUID(String uuid) {
		// TODO Auto-generated method stub
		String uuidGen = ApplicationHelper.preProcessUIID(uuid);
		
		N000ORService n000orService = (N000ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N000OR);
		List<N000OR> list = n000orService.listOfTabN000PieCA(AESUtils.encrypt(uuidGen.substring(0, 16)),ApplicationConstant.PVLOGIN_ANONYMOUS);
		if (list != null && list.size() > 0) {
			int fo100 = list.get(0).getFo100();
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null && n100pmg.getK100Con() == null) {
				templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
				if (m900mg != null) {
					m900mg.setUrlAvarta(m900mg.getUrlAvarta());
					n100pmg.setM900mg(m900mg);
					return n100pmg;
				}
			}
		}
		return null;
	}

	@Override
	public String randomSecurityNumber(String nickName, int count) {
		// TODO Auto-generated method stub
		if (count == 999)
			return "NaN";
		String securityNumber = String.valueOf((int) (Math.random() * 999));
		if (securityNumber.length() == 1)
			securityNumber = "00" + securityNumber;
		else if (securityNumber.length() == 2)
			securityNumber = "0" + securityNumber;
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.NV101, nickName + securityNumber));
		if (n100pmg == null)
			return securityNumber;
		else
			return randomSecurityNumber(nickName, ++count);
	}

	@Override
	public int updateK100(int fo100, String kv101, String kv102, String kv103, String kv106, String kv107,
			List<String> licenseImgs, String affPiepmeID) {
		// TODO Auto-generated method stub
		templateMGService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateMGService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
		K100PMG k100pmg;
		if (n100pmg != null) {
			if (n100pmg.getK100() == null) {
				k100pmg = new K100PMG(0, kv101, kv102, kv103, K100PMG.KV105_NEW, kv106, kv107, new Date(adminPMGDao.getCurrentTime()),
						new Date(adminPMGDao.getCurrentTime()), null, licenseImgs, affPiepmeID,ApplicationHelper.getStemString(kv101));
				n100pmg.setK100(k100pmg);
			} else {
				k100pmg = n100pmg.getK100();
				k100pmg.setKv101(kv101);
				k100pmg.setKv101Stem(ApplicationHelper.getStemString(kv101));
				k100pmg.setKv102(kv102);
				k100pmg.setKv103(kv103);
				k100pmg.setKv105(K100PMG.KV105_NEW);
				k100pmg.setKv106(kv106);
				k100pmg.setKv107(kv107);
				k100pmg.setKd109(null);
				k100pmg.setKv110(affPiepmeID);
				k100pmg.setLicenseImgs(licenseImgs);
				k100pmg.setKd106(new Date(adminPMGDao.getCurrentTime()));
			}
			return templateMGService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
		}
		return 0;
	}

	@Override
	public K100DetailPMG getBusinessInfo(int fo100, int fo100b) {
		// TODO Auto-generated method stub
		return n100pmgDao.getBusinessInfo(fo100, fo100b);
	}

	@Override
	public int rejectK100(int fo100, int fo100c,String kv112) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		if (n100pmg != null && n100pmg.getK100() != null) {
			n100pmg.getK100().setFo100c(fo100c);
			n100pmg.getK100().setKv105(K100PMG.KV105_REJECTED);
			n100pmg.getK100().setKv112(kv112);
			if (templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100) > 0) {
				/*
				 * N000ORService n000orService = (N000ORService)
				 * ApplicationHelper
				 * .findBean(SpringBeanNames.SERVICE_NAME_N000OR);
				 * log.info("---updateTabN000Pie:" + fo100 + "," +
				 * n100pmg.getNv106()); n000orService.updateTabN000Pie("C",
				 * fo100, n100pmg.getNv106());
				 */
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int updateA100(int fo100, String av101, String av102, String av103, String av104, String av107,
			String ad110String, String av111) {
		// TODO Auto-generated method stub
		templateMGService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateMGService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
		A100PMG a100pmg;
		if (n100pmg != null) {
			if (n100pmg.getA100() == null) {
				a100pmg = new A100PMG(0, av101, av102, av103, av104, A100PMG.AV105_NEW, av107, null, new Date(adminPMGDao.getCurrentTime()),
						new Date(adminPMGDao.getCurrentTime()), null, av111);
				a100pmg.setAd110(DateHelper.convertStringToDate(ad110String, "dd/MM/yyyy"));
				n100pmg.setA100(a100pmg);
				/*
				 * update only first register
				 */
				log.info("---updateTabN100IWA:" + fo100 + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
				n100orService.updateTabN100IWA(fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * cap nhat thong tin nhan vien o oracle
				 */
				try {
					N100ORService n100orService2 = (N100ORService) ApplicationHelper
							.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
					n100orService2.updateTabN100ADD(n100pmg.getA100().getAv101(), n100pmg.getA100().getAv102(), new java.sql.Date(n100pmg.getA100().getAd110().getTime()),
							n100pmg.getA100().getAv111(), n100pmg.getA100().getAv107(), null, null, fo100,
							ApplicationConstant.PVLOGIN_ANONYMOUS);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} else {
				/*a100pmg = n100pmg.getA100();
				a100pmg.setAv101(av101);
				a100pmg.setAv102(av102);
				a100pmg.setAv102(av102);
				a100pmg.setAv104(av104);
				a100pmg.setAv105(A100PMG.AV105_NEW);
				a100pmg.setAv107(av107);
				a100pmg.setAv111(av111);
				a100pmg.setAd109(null);
				a100pmg.setAd110(DateHelper.convertStringToDate(ad110String, "dd/MM/yyyy"));*/
				return -1;
			}
			return templateMGService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
		}
		return 0;
	}

	@Override
	public int confirmA100(int fo100, int fo100c) {
		// TODO Auto-generated method stub
		try {
			N100ORService n100orService = (N100ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);

			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			N100PMG n100pmgAdmin = templateService.findDocument(fo100c, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100c));
			n100pmg.getA100().setAv105(A100PMG.AV105_CONFIRMED);
			n100pmg.getA100().setAd109(new Date(adminPMGDao.getCurrentTime()));
			n100pmg.getA100().setFo100c(fo100c);
			n100pmg.setA100Con(n100pmg.getA100());// update to confirmed data

			templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
			log.info("---updateTabN100AFF:" + fo100 + "," + n100pmgAdmin.getNv101());
			n100orService.updateTabN100AFF(fo100, n100pmgAdmin.getNv106());
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int rejectA100(int fo100, int fo100c) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		if (n100pmg != null && n100pmg.getA100() != null) {
			n100pmg.getA100().setFo100c(fo100c);
			n100pmg.getA100().setAv105(A100PMG.AV105_REJECTED);
			if (templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100) > 0)
				return 1;
		}
		return 0;
	}

	@Override
	public int updateLocation(final int fo100, double latitude, double longitude, String addressFullName) {
		// TODO Auto-generated method stub
		final TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		final GeoDataPointMG location = new GeoDataPointMG(longitude, latitude, addressFullName);
		n100pmg.setLocation(location);
		int kq = templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
		if (kq > 0) {
			try {
				Thread thread = new Thread() {
					public void run() {
						QbCriteria qbCriteria = new QbCriteria("FT300",4);
						qbCriteria.setConditionField(QbCriteria.TYPE_NE);
						// update location for all business pieper exclude ft300 = 4 (BDS)
						templateService.updateOneFieldMultil(fo100, P300BPMG.class, QbMongoFiledsName.LOC, location,
								null, new QbCriteria(QbMongoFiledsName.FO100, fo100), qbCriteria);
					}
				};
				thread.start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return kq;
	}

	@Override
	public int updateLocationWhenOnline(int fo100, double latitude, double longitude, String addressFullName) {
		// TODO Auto-generated method stub
		final TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		final GeoDataPointMG location = new GeoDataPointMG(longitude, latitude, addressFullName);
		n100pmg.setLocationR(location);
		int kq = templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
		return kq;
	}

	@Override
	public N100Status2 listOfTabN100Loc(int fo100, double latitude, double longitude, int radius) {
		// TODO Auto-generated method stub
		return n100pmgDao.listOfTabN100Loc(fo100, latitude, longitude, radius);
	}

	@Override
	public N100Status2 listOfTabN100LocR(int fo100, int radius) {
		// TODO Auto-generated method stub
		return n100pmgDao.listOfTabN100LocR(fo100, radius);
	}

	@Override
	public int updateTabN100REG(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		N100ORService n100orService = (N100ORService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
		int rs = n100orService.updateTabN100REG(fo100, pvLogin);
		try {
			P300MPMGService p300Service = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
			p300Service.messageByAdmin(fo100, "Chào bạn, \nPiepMe cảm ơn bạn đã đăng ký dịch vụ đăng tin bất động sản. \nĐể hoàn tất qui trình đăng ký, bạn vui lòng chuyển phí sử dụng (300K/30 ngày) vào tài khoản sau: \n- Số tài khoản: \n- Tên tài khoản: Cty CP Ong Chúa \n- Ngân hàng: EXIMBANK, Chi nhánh TPHCM \n- Nội dung nộp: PiepMe-Id của bạn \nChúng tôi sẽ mở dịch vụ đăng tin cho bạn sau khi phí sử dụng đã chuyển vào hoàn tất. \nTrân trọng \nPiepMe - Tin tự tìm bạn");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public N100Status3 listOfTabN100LocV2(int fo100, double latitude, double longitude, int radius) {
		// TODO Auto-generated method stub
		return n100pmgDao.listOfTabN100LocV2(fo100, latitude, longitude, radius);
	}

	@Override
	public N100Status3 listOfTabN100LocRV2(int fo100, int radius) {
		// TODO Auto-generated method stub
		return n100pmgDao.listOfTabN100LocRV2(fo100, radius);
	}

	@Override
	public N100PMG loginV3(String nickName, String digits, String uuid) {
		// TODO Auto-generated method stub
		Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.NV101, nickName + digits));
		if (n100pmg != null) {
			n100pmg.setK100Con(null);
			Q100Piepme q100 = q100Service.qhayCheckTabQ100PiepCa(uuid, n100pmg.getFo100());
		 	if (q100 != null) {
				templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				M900MG m900mg = templateService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
				m900mg.setUrlAvarta(m900mg.getUrlAvarta());
				n100pmg.setM900mg(m900mg);
				n100pmg.setQ100(q100);
				if (n100pmg.getA100() != null && n100pmg.getA100().getAd110() != null) {
					String dateString = DateHelper.formatDateShort(n100pmg.getA100().getAd110());
					n100pmg.getA100().setAd110Str(dateString);
				}
				log.info("---n100pmg:" + n100pmg);
				/*
				 * get system info
				 */
				try {
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					S250PMG s250pmg = templateService.findDocumentById(775, 1, S250PMG.class);
					n100pmg.setS250pmg(s250pmg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return n100pmg;

			}
		}
		return null;
	}

	@Override
	public N100PMG loginEnterprise(String nickName, String digits, String uuid) {
		// TODO Auto-generated method stub
		Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.NV101, nickName + digits));
		if (n100pmg != null && n100pmg.getK100Con() != null) {
			Q100Piepme q100 = q100Service.qhayCheckTabQ100PiepDn(uuid, n100pmg.getFo100());
			if (q100 != null) {
				templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				M900MG m900mg = templateService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
				m900mg.setUrlAvarta(m900mg.getUrlAvarta());
				n100pmg.setM900mg(m900mg);
				n100pmg.setQ100(q100);
				if (n100pmg.getA100() != null && n100pmg.getA100().getAd110() != null) {
					String dateString = DateHelper.formatDateShort(n100pmg.getA100().getAd110());
					n100pmg.getA100().setAd110Str(dateString);
				}
				log.info("---n100pmg:" + n100pmg);
				/*
				 * get system info
				 */
				try {
					templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
					S250PMG s250pmg = templateService.findDocumentById(775, 1, S250PMG.class);
					n100pmg.setS250pmg(s250pmg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return n100pmg;

			}
		}
		return null;
	}

	@Override
	public int removeAccount(int fo100Login, int fo100Remove) {
		// TODO Auto-generated method stub
		O100Service o100Service = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
		/*
		 * remove piepme data
		 */
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		templateService.removeDocuments(fo100Remove, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100Remove));
		templateService.removeDocuments(fo100Remove, P300BPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100Remove));
		templateService.removeDocuments(fo100Remove, P300PPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100Remove));
		templateService.removeDocuments(fo100Remove, P300CPMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100Remove));
		o100Service.removeAccount(fo100Remove);
		AdminPMGDao adminPMGDao = (AdminPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_ADMINP);
		adminPMGDao.removeDBUser(fo100Remove);
		return 1;
	}

	@Override
	public List<N100CAFMG> listOfTabN100OCaf(int fo100) {
		// TODO Auto-generated method stub
		return n100pmgDao.listOfTabN100OCaf(fo100);
	}

	@Override
	public int upgradeTabV220(int fo100, String pvActiv, String pvLogin) {
		// TODO Auto-generated method stub
		V220ORDao v220orDao = (V220ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V220OR);
		templateMGService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateMGService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		if(n100pmg != null && n100pmg.getK100Con() == null)
			return v220orDao.upgradeTabV220(fo100, pvActiv, pvLogin);
		return -1;
	}

	@Override
	public int updateK100V2(int fo100, String kv101, String kv102, String kv103, String kv106, String kv107, List<String> licenseImgs, String affPiepmeID, String uuid,  double latitude, double longitude, String addressFullName) {
		// TODO Auto-generated method stub
		templateMGService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100pmg = templateMGService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		K100PMG k100pmg;
		if (n100pmg != null) {
			/*
			 * tai khoan da duoc xac nhan khong duoc update, phai dung app SE
			 */
			if(n100pmg.getK100() != null && K100PMG.KV105_CONFIRMED.equals(n100pmg.getK100().getKv105()))
					return -2;
			if (n100pmg.getK100() == null) {
				k100pmg = new K100PMG(0, kv101, kv102, kv103, K100PMG.KV105_NEW, kv106, kv107, new Date(adminPMGDao.getCurrentTime()),
									  new Date(adminPMGDao.getCurrentTime()), null, licenseImgs, affPiepmeID,ApplicationHelper.getStemString(kv101));
				k100pmg.setKv113(uuid);
				n100pmg.setK100(k100pmg);
			} else {
				k100pmg = n100pmg.getK100();
				k100pmg.setKv101(kv101);
				k100pmg.setKv101Stem(ApplicationHelper.getStemString(kv101));
				k100pmg.setKv102(kv102);
				k100pmg.setKv103(kv103);
				k100pmg.setKv105(K100PMG.KV105_NEW);
				k100pmg.setKv106(kv106);
				k100pmg.setKv107(kv107);
				k100pmg.setKd109(null);
				k100pmg.setKv110(affPiepmeID);
				k100pmg.setKv113(uuid);
				k100pmg.setLicenseImgs(licenseImgs);
				k100pmg.setKd106(new Date(adminPMGDao.getCurrentTime()));
			}
			GeoDataPointMG loc = new GeoDataPointMG(106.679472, 10.779586, "134/1/19 Cách Mạng Tháng Tám, phường 10, Quận 3, Hồ Chí Minh");
			if(longitude > 0 && latitude > 0 && addressFullName != null && !addressFullName.isEmpty())
				 loc = new GeoDataPointMG(longitude, latitude, addressFullName);
			n100pmg.setLocationR(loc);
			return templateMGService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
		}
		return 0;
	}
	
	@Override
	public int confirmK100(int fo100, int fo100c) {
		// TODO Auto-generated method stub
		try {
			N000ORService n000orService = (N000ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N000OR);
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			/*
			 * 1) update mongo data
			 */
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			N100PMG n100pmgAdmin = templateService.findDocument(fo100c, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100c));
			n100pmg.getK100().setKv105(K100PMG.KV105_CONFIRMED);
			n100pmg.getK100().setKd109(new Date());
			n100pmg.getK100().setFo100c(fo100c);
			n100pmg.setK100Con(n100pmg.getK100());// update to confirmed data
			/*
			 * tao channel quan ly KHTT cho doanh nghiep
			 */
			T100PMG t100Bus = templateService.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100), new QbCriteria("TV104",T100PMG.TYPE_COM));
			if (t100Bus == null) {
				int newT100Id = (int) sequenceService.getNextSequenceIdPiepMe(fo100, QbMongoCollectionsName.T100);
				T100PMG t100mg = new T100PMG();
				t100mg.setId(newT100Id);
				t100mg.setFo100(fo100);
				t100mg.setTv101(T100PMG.TYPE_COM);
				t100mg.setTv102("");
				t100mg.setTv103(T100PMG.TYPE_COM);
				t100mg.setTv104(T100PMG.TYPE_COM);
				t100mg.setTl146(new Date());
				t100mg.setTl148(new Date());
				templateService.saveDocument(fo100, t100mg, QbMongoCollectionsName.T100);
			}
			try {
				/*
				 * 2) update ORACLE data
				 */
				templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
				log.info("---updateTabN000Pie:" + "D" + "," + fo100 + "," + n100pmg.getNv106());
				n000orService.updateTabN000Pie("D", fo100, n100pmgAdmin.getNv106());
				N100ORService n100orService2 = (N100ORService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
				n100orService2.updateTabN100ADD(null, null, null, n100pmg.getK100().getKv102(), n100pmg.getK100().getKv107(),
						n100pmg.getK100().getKv103(), null, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * 3) update MYSQL data
				 */
				Q100Dao q100Dao = (Q100Dao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q100);
				q100Dao.updateTabQ100Busi(fo100, n100pmgAdmin.getNv106());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double getDistanceFromEnterprise(int fo100, int fo100e) {
		// TODO Auto-generated method stub
		double rs = n100pmgDao.getDistanceFromEnterprise(fo100, fo100e);
		if(rs <= 5000)
			return 5;
		else if(rs <= 30000)
			return 30;
		else if(rs <= 100000)
			return 100;
		return 111;
	}
}
