package com.ohhay.serviceimpl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.entities.N100CentPMG;
import com.ohhay.base.mysql.service.C100CentService;
import com.ohhay.base.mysql.service.O100CentService;
import com.ohhay.base.mysql.service.R100CentService;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TopicRoleConstant;
import com.ohhay.core.criteria.AccountEVORegisCriteria;
import com.ohhay.core.criteria.AccountPiepmeRegisCriteria;
import com.ohhay.core.criteria.AccountRegisCriteria;
import com.ohhay.core.entities.ComtabItem;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M920MG;
import com.ohhay.core.entities.mongo.profile.M950MG;
import com.ohhay.core.entities.oracle.N000OR;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.dao.Q100Dao;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.oracle.dao.V300ORDao;
import com.ohhay.core.oracle.dao.V330ORDao;
import com.ohhay.core.oracle.service.N000ORService;
import com.ohhay.core.oracle.service.N100ORService;
import com.ohhay.core.utils.AESUtilsPiepme;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.other.mysql.service.N100Service;
import com.ohhay.other.mysql.service.O100Service;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.entities.notification.N900PMG;
import com.ohhay.piepme.mongo.entities.pieper.P300BPMG;
import com.ohhay.piepme.mongo.nestedentities.K100PMG;
import com.ohhay.piepme.mongo.nestedentities.V300PMG;
import com.ohhay.piepme.mongo.service.F150PMGService;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.service.P300MPMGService;
import com.ohhay.piepme.mongo.service.P300VPMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;
import com.ohhay.piepme.mysql.service.T000Servcie;
import com.ohhay.service.CreateAccountService;
import com.ohhay.web.core.api.service.LanguageService;
import com.ohhay.web.core.entities.mongo.web.A400MG;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webchild.A500MG;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.weblanguage.A100MG;
import com.ohhay.web.core.entities.mongo.weblanguage.C100MG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.A100CMG;
import com.ohhay.web.core.entities.mongo.weblanguagechild.C100CMG;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.core.mysql.service.C500Service;
import com.ohhay.web.core.mysql.service.C920Service;
import com.ohhay.web.lego.service.WebLegoService;
import com.oohhay.web.lego.utils.EvoWebType;
import com.softlayer.api.service.product.catalog.item.Price;
/**
 * @author ThoaiNH
 * - date create 12/02/2015 
 * - update - 24/04/2015 - them create account moi
 */
@Service(value = SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT)
@Scope("prototype")
public class CreateAccountServiceImpl implements CreateAccountService{
	@Autowired
	private TemplateService templateService;
	@Autowired
	private M900MGService m900mgService;
	private static Logger log = Logger.getLogger(CreateAccountServiceImpl.class);
	@Autowired
	private VelocityEngine ve;
	@Autowired
	private WebLegoService webLegoService;
	@Autowired
	private E400MGService e400mgService;
	@Autowired
	private N100PMGService n100pmgService;
	@Autowired
	private SequenceService sequenceService;
	@Override
	public M900MG createAccount(AccountRegisCriteria accountRegisCriteria) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {
			log.info("-----BAT DAU TAO------:"+accountRegisCriteria);
			String ov101 = accountRegisCriteria.getPhone();
			String ov102 = "0";
			String ov103 = accountRegisCriteria.getCountryCode();
			int fc500 = 0;
			int fk100 = 1;
			int fn750 = 1;
			int fc800 = accountRegisCriteria.getTemplateId();// template id
			int fd000 = accountRegisCriteria.getFd000();
			String languageCode =  accountRegisCriteria.getLanguageCode();
			String languageName = accountRegisCriteria.getLanguageName();
			String region = accountRegisCriteria.getRegion();
			LanguageService languageMGService = (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
			O100Service o100Servcie = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
			C920Service c920Service = (C920Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C920);
			log.info("---ohayInserttabO100:" + 0 + "," + ov101
					+ "," + ov102 + "," + ov103 + ","+ApplicationHelper.convertToMD5(accountRegisCriteria.getPassWord())+"," + fk100 + "," + fn750
					+ "," + fc500 + "," + fc800 + "," +fd000 + ",phongvt");
			int po100 = o100Servcie.ohayInserttabO100(0, ov101, ov102, ov103, ApplicationHelper.convertToMD5(accountRegisCriteria.getPassWord()), fk100, fn750, fc500, fc800, fd000, "phongvt", null);
			N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
			int fn100 = n100Service.getValTabN100(po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
			/*
			 * 1) insert to mongo
			 */
			log.info("---po100 created:"+po100);
			if (po100 > 0) {
				log.info("----kq:" + po100);
				log.info("--chayGetelemTabC920:" + ov101 + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
				String elemString = c920Service.chayGetelemTabC920(ov101, ApplicationConstant.PVLOGIN_ANONYMOUS);
				log.info("---elemString:" + elemString);
				//must add fo100
				templateService.insertWebStructure(0, "{" + elemString + "}", QbMongoCollectionsName.C900);
				/*
				 * 2) insert m900
				 */
				M900MG m900mg = new M900MG();
				m900mg.setId(po100);
				m900mg.setMl946(new Date());
				m900mg.setMl948(new Date());
				m900mg.setHv101(ApplicationHelper.convertToMD5(ov101));
				m900mg.setMv905(accountRegisCriteria.getGender());
				m900mg.setMv907(ov101);
				m900mg.setFn100O(fn100);
				m900mg.setMv903(AESUtils.encrypt(accountRegisCriteria.getEmail()));
				m900mg.setMv921(AESUtils.encrypt(accountRegisCriteria.getEmail()));
				m900mg.setMv901(accountRegisCriteria.getfName());
				m900mg.setMv902(accountRegisCriteria.getlName());
				m900mg.setNv100(accountRegisCriteria.getfName()+" "+accountRegisCriteria.getlName());
				m900mg.setMv920(accountRegisCriteria.getCountryCode());
				m900mg.setMn919(0);
				LanguageMG languageMG = new LanguageMG();
				languageMG.setCode(languageCode);
				languageMG.setText(languageName);
				m900mg.setrLanguageMG(languageMG);
				m900mg.setLanguage(languageCode);
				m900mg.setMv908("");
				//set up role topic
				M950MG m950mg = new M950MG();
				m950mg.setMn951(TopicRoleConstant.TOPIC_VIEWABLE_ROLE_1);
				m950mg.setMn952(TopicRoleConstant.TOPIC_ASHARE_ROLE_1);
				m950mg.setMn953(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m950mg.setMn954(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m900mg.setM950mg(m950mg);
				m900mg.setRegion(region);
				M920MG m920mg = new M920MG();
				m920mg.setVal(fd000);
				m920mg.setLabel(accountRegisCriteria.getJobName());
				m900mg.setM920MG(m920mg);
				templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
				/*
				 * 3) insert language
				 */
				C400MG c400mg = templateService.findDocument(po100, C400MG.class, new QbCriteria("FO100", po100));
				languageMGService.createLanguage(c400mg.getId(), c400mg.getFo100(), c400mg.getListC920mg(), languageCode, languageName, QbMongoCollectionsName.C900);
				/*
				 * 4) insert all child page
				 */
				C500Service c500Service = (C500Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C500);
				log.info("---listOfTabC500:" + po100 + "," + c400mg.getId()+ "," + ov101);
				List<ComtabItem> listComtabItems = c500Service.listOfTabC500(po100, c400mg.getId(), ov101);
				for (ComtabItem comtabItem : listComtabItems) {
					//declare service
					C920Service c920Service2 = (C920Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C920);
					LanguageService languageMGService2 = (LanguageService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_LANGUAGE);
					TemplateService templateMGService2 = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
					log.info("---chayGetelemTabC920Child:"+ comtabItem.getVal() + "," + ov101);
					String elemStringChild = c920Service2.chayGetelemTabC920Child(comtabItem.getVal(), ov101);
					log.info("---:" + elemStringChild);
					int kq2 = templateMGService2.insertWebStructure(po100, "{"+ elemStringChild + "}", QbMongoCollectionsName.C500);
					if (kq2 > 0) {
						/*
						 * ---- 2.6) insert language  C100CMG
						 */
						C500MG c500mg = templateMGService2.findDocumentById(po100, comtabItem.getVal(), C500MG.class);
						languageMGService2.createLanguage(c500mg.getId(), c500mg
														  .getFo100(), c500mg.getListC920mg(), languageCode,languageName, QbMongoCollectionsName.C500);
					}
				}
				/*
				 * 6) insert n100 oracle
				 */
				if(fn100 > 0){
					N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
					int kq = n100orService.insertTabN100(fn100, m900mg.getMv901(), m900mg.getMv902(),
														ApplicationHelper.removeAccent(m900mg.getMv901()), ApplicationHelper.removeAccent(m900mg.getMv902()), 
														null, m900mg.getMv903Decrypt(), 
														m900mg.getMv907(),null, "", accountRegisCriteria.getCountryCode(), po100, 1,ApplicationConstant.PVLOGIN_ANONYMOUS);
					log.info("--kq insert n100 oracle:"+kq);
				}
				/*
				 * 7) send mail success
				 */
				try{
					VelocityContext context = new VelocityContext();
					Template t = ve.getTemplate("email_register_success.vm", "UTF-8");
					StringWriter writer = new StringWriter();
					t.merge(context, writer);
					String emailSubject = "BON EVO - Register success";
					log.info("send mail: ("+po100+","+m900mg.getMv903Decrypt()+","+ null+","+ ApplicationHelper.decodeTopicString(emailSubject));
					M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
					int mail = m350Service.sendMailTabM350Confirm(po100,  m900mg.getMv903Decrypt(), null, ApplicationHelper.decodeTopicString(emailSubject), ApplicationHelper.decodeTopicString(writer.toString()), m900mg.getMv907());
					log.info("ket qua send mail: "+mail);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				log.info("-----XONG------");
				return m900mg;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	
	}
	@Override
	public int validateAccountWeb(AccountRegisCriteria accountRegisCriteria) {
		// TODO Auto-generated method stub
		accountRegisCriteria.setPhone(ApplicationHelper.processDummiesPhones(accountRegisCriteria.getPhone(), accountRegisCriteria.getCountryCode()));
		log.info(accountRegisCriteria);
		if(ApplicationHelper.validateRegex(accountRegisCriteria.getEmail(), RegexConstant.EMAIL_PATTERN) == false)
			return -1;
		else if(m900mgService.emailIsExists(accountRegisCriteria.getEmail()) == true)
			return -5;
		else if(accountRegisCriteria.getfName() == null || accountRegisCriteria.getfName().trim().length() == 0 || 
				accountRegisCriteria.getlName() == null || accountRegisCriteria.getlName().trim().length() == 0 )
			return -2;
		else if(accountRegisCriteria.getPassWord().equals(accountRegisCriteria.getRePassWord()) == false)
			return -3;
		else if(ApplicationHelper.validateRegex(accountRegisCriteria.getPassWord(), RegexConstant.PASSWORD_PATTERN) == false)
			return -4;
		else 
			return 1;
	}
	@Override
	public M900MG createAccountA900(AccountRegisCriteria accountRegisCriteria) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {
			log.info("-----BAT DAU TAO------:"+accountRegisCriteria);
			String ov101 = accountRegisCriteria.getPhone();
			String ov102 = "0";
			String ov103 = accountRegisCriteria.getCountryCode();
			int fc500 = 0;
			int fk100 = 1;
			int fn750 = 1;
			int fa900 = accountRegisCriteria.getTemplateId();// template id
			int fd000 = accountRegisCriteria.getFd000();
			String languageCode =  accountRegisCriteria.getLanguageCode();
			String languageName = accountRegisCriteria.getLanguageName();
			String region = accountRegisCriteria.getRegion();
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			O100Service o100Servcie = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
			O100CentService o100CentService = (O100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_O100CENT);
			C100CentService c100CentService = (C100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_C100CENT);
			/*
			 * 1) find template data
			 */
			A400MG a400mg = templateService.findDocumentById(0, fa900, A400MG.class);
			A100MG a100mg = templateService.findDocument(0, A100MG.class, new QbCriteria("WEBID", fa900));
			/*
			 * 2) insert o100
			 */
			log.info("---ohayInserttabO100:" + 0 + "," + ov101
					+ "," + ov102 + "," + ov103 + ","+accountRegisCriteria.getPassWord()+"," + fk100 + "," + fn750
					+ "," + fc500 + "," + a400mg.getFc800() + "," +fd000 + ",phongvt");
			int po100 = o100Servcie.ohayInserttabO100(0, ov101, ov102, ov103, accountRegisCriteria.getPassWord(), fk100, fn750, fc500, a400mg.getFc800(), fd000, "phongvt", 
														c100CentService.getValTabC100MySQL(ApplicationConstant.PVLOGIN_ANONYMOUS));
			N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
			/*
			 * 3) insert to mongo
			 */
			if (po100 > 0) {
				log.info("----kq:" + po100);
				/*
				 * 3.0)
				 */
				o100CentService.insertTabO100(po100, ov101, ov102, ov103, null, ApplicationConstant.PVLOGIN_ANONYMOUS);
				int fn100 = n100Service.getValTabN100(po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * 3.1) create web site data from template
				 */
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				int newFc400Id = (int) sequenceService.getNextSequenceId(po100, QbMongoCollectionsName.C900);
				/*
				 * -- 3.1.1) create web data
				 */
				C400MG c400mg = new C400MG(a400mg);
				c400mg.setFa900(a400mg.getId());
				c400mg.setId(newFc400Id);
				c400mg.setFo100(po100);
				c400mg.setCv808(a400mg.getAv403());
				List<LanguageMG> listLanguageMG = new ArrayList<LanguageMG>();
				LanguageMG languageMG = new LanguageMG();
				languageMG.setCode(languageCode);
				languageMG.setText(languageName);
				listLanguageMG.add(languageMG);
				c400mg.setListC500mg(listLanguageMG);
				C100MG c100mg = new C100MG(a100mg);
				/*
				 * -- 3.1.2) create language data
				 */
				c100mg.setFo100(po100);
				c100mg.setWebID(newFc400Id);
				c100mg.setLanguageID(ApplicationHelper.convertToMD5(newFc400Id+languageCode));
				c100mg.setCv101(languageCode);
				templateService.saveDocument(po100, c400mg, QbMongoCollectionsName.C900);
				templateService.saveDocument(po100, c100mg, QbMongoCollectionsName.C100);
				/*
				 * --- 3.1.3) insert child page
				 */
				List<A500MG> listA500MG = templateService.findDocuments(0, A500MG.class, new QbCriteria(QbMongoFiledsName.PARID, fa900));
				for(A500MG a500mg: listA500MG){
					//web data
					C500MG c500mg = new C500MG(a500mg);
					int c500IdNew = (int) sequenceService.getNextSequenceId(po100, QbMongoCollectionsName.C500);
					c500mg.setId(c500IdNew);
					c500mg.setFo100(po100);
					c500mg.setWebParentId(newFc400Id);
					c500mg.setHv101(ApplicationHelper.convertToMD5(ov101));
					c500mg.setListC500mg(listLanguageMG);
					//language data
					A100CMG a100cmg = templateService.findDocument(0, A100CMG.class, new QbCriteria(QbMongoFiledsName.WEBID, a500mg.getId()));
					C100CMG c100cmg = new C100CMG(a100cmg);
					c100cmg.setWebID(c500IdNew);
					c100cmg.setLanguageID(ApplicationHelper.convertToMD5(c500IdNew + languageCode));
					c100cmg.setFo100(po100);
					c100cmg.setCv101(languageCode);
					templateService.saveDocument(po100, c500mg, QbMongoCollectionsName.C500);
					templateService.saveDocument(po100, c100cmg, QbMongoCollectionsName.C100C);
				}
				/*
				 * 3.2) insert m900
				 */
				M900MG m900mg = new M900MG();
				m900mg.setId(po100);
				m900mg.setMl946(new Date());
				m900mg.setMl948(new Date());
				m900mg.setHv101(ApplicationHelper.convertToMD5(ov101));
				m900mg.setMv905(accountRegisCriteria.getGender());
				m900mg.setMv907(ov101);
				m900mg.setFn100O(fn100);
				m900mg.setMv921(accountRegisCriteria.getEmail());
				m900mg.setMv901(accountRegisCriteria.getfName());
				m900mg.setMv902(accountRegisCriteria.getlName());
				m900mg.setNv100(accountRegisCriteria.getfName()+" "+accountRegisCriteria.getlName());
				m900mg.setMv920(accountRegisCriteria.getCountryCode());
				m900mg.setMn919(0);
				m900mg.setrLanguageMG(languageMG);
				m900mg.setLanguage(languageCode);
				//set up role topic
				M950MG m950mg = new M950MG();
				m950mg.setMn951(TopicRoleConstant.TOPIC_VIEWABLE_ROLE_1);
				m950mg.setMn952(TopicRoleConstant.TOPIC_ASHARE_ROLE_1);
				m950mg.setMn953(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m950mg.setMn954(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m900mg.setM950mg(m950mg);
				m900mg.setRegion(region);
				M920MG m920mg = new M920MG();
				m920mg.setVal(fd000);
				m920mg.setLabel(accountRegisCriteria.getJobName());
				m900mg.setM920MG(m920mg);
				templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
				//insert to mongo center
				templateService.saveDocument(0, m900mg, QbMongoCollectionsName.M900);
				/*
				 * 3.3) create 3 video page
				 */
				/*VideoMarketingService videoMarktingService = (VideoMarketingService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_VIDEO_MARKETING);
				videoMarktingService.createWebVideoMarketing(po100, ApplicationConstant.OHHAY_TEMPLATE_VIDEO_MAKERTING_1, "Music playlist",OhhayDefaultData.DEFAULT_VIDEO_ICON, ov101);
				videoMarktingService.createWebVideoMarketing(po100, ApplicationConstant.OHHAY_TEMPLATE_VIDEO_MAKERTING_1, "Video playlist",OhhayDefaultData.DEFAULT_VIDEO_ICON, ov101);
				videoMarktingService.createWebVideoMarketing(po100, ApplicationConstant.OHHAY_TEMPLATE_VIDEO_MAKERTING_2, "Ohhay tutorial",OhhayDefaultData.DEFAULT_VIDEO_ICON, ov101);*/
				/*
				 * 3.4) insert n100 oracle
				 */
				if(fn100 > 0){
					N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
					log.info("----oracle insertTabN100:"+fn100+","+m900mg.getMv901()+","+m900mg.getMv902()+","+
														ApplicationHelper.removeAccent(m900mg.getMv901())+","+ApplicationHelper.removeAccent(m900mg.getMv902())+","+ 
														null+","+m900mg.getMv903Decrypt()+","+ 
														m900mg.getMv907()+","+null+","+""+","+accountRegisCriteria.getCountryCode()+","+po100+","+1+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
					int kq = n100orService.insertTabN100(fn100, m900mg.getMv901(), m900mg.getMv902(),
														ApplicationHelper.removeAccent(m900mg.getMv901()), ApplicationHelper.removeAccent(m900mg.getMv902()), 
														null, m900mg.getMv903Decrypt(), 
														m900mg.getMv907(),null, "", accountRegisCriteria.getCountryCode(), po100, 1,ApplicationConstant.PVLOGIN_ANONYMOUS);
					log.info("--kq insert n100 oracle:"+kq);
				}
				/*
				 * 4) send mail success
				 */
				try{
					VelocityContext context = new VelocityContext();
					Template t = ve.getTemplate("email_register_success.vm", "UTF-8");
					StringWriter writer = new StringWriter();
					t.merge(context, writer);
					
					String emailSubject = "BON EVO - Register success";
					log.info("send mail: ("+po100+","+m900mg.getMv921Decrypt()+","+ null+","+ ApplicationHelper.decodeTopicString(emailSubject));
					M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
					int mail = m350Service.sendMailTabM350Confirm(po100,  m900mg.getMv921Decrypt(), null, ApplicationHelper.decodeTopicString(emailSubject), ApplicationHelper.decodeTopicString(writer.toString()), m900mg.getMv907());
					log.info("ket qua send mail: "+mail);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				log.info("-----XONG------");
				return m900mg;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public int createAccountWebEVO(AccountEVORegisCriteria accountRegisCriteria, HttpServletResponse response, HttpServletRequest request, String currentLang) {
		// TODO Auto-generated method stub
		log.info(accountRegisCriteria);
		if(accountRegisCriteria.getfName() == null || accountRegisCriteria.getfName().trim().length() == 0 || 
				accountRegisCriteria.getlName() == null || accountRegisCriteria.getlName().trim().length() == 0 )
			return -2;
		else if(accountRegisCriteria.getPassWord().equals(accountRegisCriteria.getRePassWord()) == false)
			return -3;
		else {
			M900MG m900mg = createEVOAccount(accountRegisCriteria, request, currentLang);
			if(m900mg != null)
				return 1;
			return -6;
		}
	}
	@Override
	public M900MG createEVOAccount(AccountEVORegisCriteria accountRegisCriteria, HttpServletRequest request, String currentLanguage) {
		// TODO Auto-generated method stub
		try {
			log.info("-----BAT DAU TAO------:" + accountRegisCriteria);
			String ov101 = null;
			String ov102 = AESUtils.decrypt(accountRegisCriteria.getEmail());
			String ov103 = accountRegisCriteria.getCountryCode();
			int fk100 = 1;
			int fn750 = 1;
			int fd000 = accountRegisCriteria.getFd000();
			String languageCode =  accountRegisCriteria.getLanguageCode();
			String languageName = accountRegisCriteria.getLanguageName();
			String region = accountRegisCriteria.getRegion();
			if(accountRegisCriteria.getProductId() == null)
				accountRegisCriteria.setProductId("FREE");
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			O100Service o100Servcie = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
			O100CentService o100CentService = (O100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_O100CENT);
			C100CentService c100CentService = (C100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_C100CENT);
			/*
			 * 1) insert o100
			 */
			String uri = c100CentService.getValTabC100MySQL(ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("---ohayInsertTabO100Bon:"+0+","+ov101+","+ ov102+","+ ov103+","+ 
					 DateHelper.toSQLDate(accountRegisCriteria.getNextPaymentDay())+","+ accountRegisCriteria.getRegisTypel()+","+ 
					 accountRegisCriteria.getBuyerAddressCountry()+","+ accountRegisCriteria.getBuyerAddressCountry()+","+ 
					 DateHelper.toSQLDate(accountRegisCriteria.getOrderDaytime())+","+ accountRegisCriteria.getTransactionAmount()+","+ null+","+ accountRegisCriteria.getProductId()+","+
					 accountRegisCriteria.getEven()+","+ DateHelper.toSQLDate(accountRegisCriteria.getTransactionDate())+","+
					 accountRegisCriteria.getTransactionId()+","+ accountRegisCriteria.getInvoiceUrl()+","+
					 accountRegisCriteria.getRebillingStopUrl()+","+ accountRegisCriteria.getReceiptUrl()+","+ accountRegisCriteria.getRenewUrl()+","+ 
					 accountRegisCriteria.getRequestRefundUrl()+","+ accountRegisCriteria.getSupportUrl()+","+ accountRegisCriteria.getBecomeAffiliateUrl()+","+ 
					 accountRegisCriteria.getPassWord()+","+ fk100+","+ fn750+","+ 
					 fd000+","+ ApplicationConstant.PVLOGIN_ANONYMOUS+","+ uri);

			/*int po100 = o100Servcie.ohayInsertTabO100Evo(0, ov101, ov102, ov103, accountRegisCriteria.getRegisTypel(), accountRegisCriteria.getPassWord(),fk100, fn750, fd000, ApplicationConstant.PVLOGIN_ANONYMOUS, 
					c100CentService.getValTabC100MySQL(ApplicationConstant.PVLOGIN_ANONYMOUS));*/
			int po100 = o100Servcie.ohayInsertTabO100Bon(0, ov101, ov102, ov103, 
														 DateHelper.toSQLDate(accountRegisCriteria.getNextPaymentDay()), accountRegisCriteria.getRegisTypel(), 
														 accountRegisCriteria.getBuyerAddressCountry(), accountRegisCriteria.getBuyerAddressCountry(), 
														 DateHelper.toSQLDate(accountRegisCriteria.getOrderDaytime()), accountRegisCriteria.getTransactionAmount(), null, accountRegisCriteria.getProductId(),
														 accountRegisCriteria.getEven(), DateHelper.toSQLDate(accountRegisCriteria.getTransactionDate()),
														 accountRegisCriteria.getTransactionId(), accountRegisCriteria.getInvoiceUrl(),
														 accountRegisCriteria.getRebillingStopUrl(), accountRegisCriteria.getReceiptUrl(), accountRegisCriteria.getRenewUrl(), 
														 accountRegisCriteria.getRequestRefundUrl(), accountRegisCriteria.getSupportUrl(), accountRegisCriteria.getBecomeAffiliateUrl(), 
														 accountRegisCriteria.getPassWord(), fk100, fn750, 
														 fd000, ApplicationConstant.PVLOGIN_ANONYMOUS, uri);
			N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
			/*
			 * 2) insert to mongo
			 */
			if (po100 > 0) {
				log.info("----kq:" + po100);
				//o100CentService.insertTabO100EVO(po100, ov101, ov102, ov103, null, accountRegisCriteria.getRegisTypel(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				log.info("---o100CentService:ohayInsertTabO100Bon:"+po100+","+ ov101+","+ ov102+","+ accountRegisCriteria.getlName()+","+
					     accountRegisCriteria.getfName()+","+ accountRegisCriteria.getRegisTypel()+","+
					     DateHelper.toSQLDate(accountRegisCriteria.getOrderDaytime())+","+ accountRegisCriteria.getTransactionAmount()+","+
					     null+","+ accountRegisCriteria.getProductId()+","+ accountRegisCriteria.getEven()+","+ DateHelper.toSQLDate(accountRegisCriteria.getTransactionDate())+","+
					     accountRegisCriteria.getTransactionId()+","+ accountRegisCriteria.getPaymentPlan()+","+
					     DateHelper.toSQLDate(accountRegisCriteria.getNextPaymentDay())+","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				o100CentService.ohayInsertTabO100Bon(po100, ov101, ov102, accountRegisCriteria.getlName(),
												     accountRegisCriteria.getfName(), accountRegisCriteria.getRegisTypel(),
												     DateHelper.toSQLDate(accountRegisCriteria.getOrderDaytime()), accountRegisCriteria.getTransactionAmount(),
												     null, accountRegisCriteria.getProductId(), accountRegisCriteria.getEven(), DateHelper.toSQLDate(accountRegisCriteria.getTransactionDate()),
												     accountRegisCriteria.getTransactionId(), accountRegisCriteria.getPaymentPlan(),
												     DateHelper.toSQLDate(accountRegisCriteria.getNextPaymentDay()), ApplicationConstant.PVLOGIN_ANONYMOUS);
				int fn100 = n100Service.getValTabN100(po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * 2.1) insert m900
				 */
				M900MG m900mg = new M900MG();
				m900mg.setId(po100);
				m900mg.setMl946(new Date());
				m900mg.setMl948(new Date());
				m900mg.setMv905(accountRegisCriteria.getGender());
				m900mg.setFn100O(fn100);
				m900mg.setMv901(accountRegisCriteria.getfName());
				m900mg.setMv902(accountRegisCriteria.getlName());
				m900mg.setNv100(accountRegisCriteria.getfName()+" "+accountRegisCriteria.getlName());
				m900mg.setMv920(accountRegisCriteria.getCountryCode());
				if(accountRegisCriteria.getRegisTypel().equals(AccountEVORegisCriteria.REGISTYPE_EVO)){
					m900mg.setMv921(accountRegisCriteria.getEmail());
					m900mg.setMn919(0);
				}
				else {
					m900mg.setMv903(accountRegisCriteria.getEmail());
					m900mg.setMn919(1);
				}
				LanguageMG languageMG = new LanguageMG();
				languageMG.setCode(languageCode);
				languageMG.setText(languageName);
				m900mg.setrLanguageMG(languageMG);
				m900mg.setLanguage(currentLanguage.trim().toLowerCase());
				//set up role topic
				M950MG m950mg = new M950MG();
				m950mg.setMn951(TopicRoleConstant.TOPIC_VIEWABLE_ROLE_1);
				m950mg.setMn952(TopicRoleConstant.TOPIC_ASHARE_ROLE_1);
				m950mg.setMn953(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m950mg.setMn954(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m900mg.setM950mg(m950mg);
				m900mg.setRegion(region);
				M920MG m920mg = new M920MG();
				m920mg.setVal(fd000);
				m920mg.setLabel(accountRegisCriteria.getJobName());
				m900mg.setM920MG(m920mg);
				m900mg.setMv926(accountRegisCriteria.getRegisTypel());
				templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
				//insert to mongo center
				templateService.saveDocument(0, m900mg, QbMongoCollectionsName.M900);
				/*
				 * 2.2) insert n100 oracle 
				 */
				if(fn100 > 0){
					N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
					/*int kq = n100orService.insertTabN100(fn100, m900mg.getMv901(), m900mg.getMv902(),
					ApplicationHelper.removeAccent(m900mg.getMv901()), ApplicationHelper.removeAccent(m900mg.getMv902()), 
					null, m900mg.getMv903Decrypt(), 
					m900mg.getMv907(),null, "", accountRegisCriteria.getCountryCode(), po100, 1,ApplicationConstant.PVLOGIN_ANONYMOUS);*/
					log.info("----oracle insertTabN100EVO:"+m900mg.getMv901()+","+ 
												m900mg.getMv902()+","+
												ApplicationHelper.removeAccent(m900mg.getMv901())+","+  
												ApplicationHelper.removeAccent(m900mg.getMv902())+","+
												null+","+
												ov102+","+ 
												m900mg.getMv907()+","+
												null+","+
												accountRegisCriteria.getAddressZipcode()+","+
												accountRegisCriteria.getBuyerAddressCountry()+","+ 
												DateHelper.toSQLDate(accountRegisCriteria.getOrderDaytime())+","+ 
											 	accountRegisCriteria.getTransactionAmount()+","+
												accountRegisCriteria.getCountryCode()+","+
												accountRegisCriteria.getDigiStoreId()+","+
												accountRegisCriteria.getProductId()+","+
												accountRegisCriteria.getEven()+","+
												DateHelper.toSQLDate(accountRegisCriteria.getTransactionDate())+","+ 
												accountRegisCriteria.getTransactionId()+","+
												accountRegisCriteria.getPaymentPlan()+","+
												accountRegisCriteria.getNextPaymentDay()+","+
												accountRegisCriteria.getInvoiceUrl()+","+
												accountRegisCriteria.getRebillingStopUrl()+","+
												accountRegisCriteria.getReceiptUrl()+","+
												accountRegisCriteria.getRenewUrl()+","+
												accountRegisCriteria.getRequestRefundUrl()+","+
												accountRegisCriteria.getSupportUrl()+","+
												accountRegisCriteria.getBecomeAffiliateUrl()+","+
												po100+","+
												1+","+
												ov102);
					int kq = n100orService.insertTabN100EVO(m900mg.getMv901(), 
												m900mg.getMv902(),
												ApplicationHelper.removeAccent(m900mg.getMv901()),  
												ApplicationHelper.removeAccent(m900mg.getMv902()),
												null,  
												ov102, 
												m900mg.getMv907(), 
												null, 
												accountRegisCriteria.getAddressZipcode(), 
												accountRegisCriteria.getBuyerAddressCountry(), 
												DateHelper.toSQLDate(accountRegisCriteria.getOrderDaytime()), 
												accountRegisCriteria.getTransactionAmount(), 
												accountRegisCriteria.getCountryCode(), 
												accountRegisCriteria.getDigiStoreId(), 
												accountRegisCriteria.getProductId(), 
												accountRegisCriteria.getEven(), 
												DateHelper.toSQLDate(accountRegisCriteria.getTransactionDate()), 
												accountRegisCriteria.getTransactionId(), 
												accountRegisCriteria.getPaymentPlan(),
												DateHelper.toSQLDate(accountRegisCriteria.getNextPaymentDay()), 
												accountRegisCriteria.getInvoiceUrl(),
												accountRegisCriteria.getRebillingStopUrl(),
												accountRegisCriteria.getReceiptUrl(),
												accountRegisCriteria.getRenewUrl(),
												accountRegisCriteria.getRequestRefundUrl(),
												accountRegisCriteria.getSupportUrl(),
												accountRegisCriteria.getBecomeAffiliateUrl(),
												po100, 
												1, 
												ov102);
				}
				/*
				 * 3) create web when sign up with trial
				 */
				if(accountRegisCriteria.getHtml()!= null && accountRegisCriteria.getHtml().trim().length() > 0)
				{
					webLegoService.saveTrial(po100, accountRegisCriteria.getTemplateId(), null, accountRegisCriteria.getHtml(),
											accountRegisCriteria.getData(), accountRegisCriteria.getApiCompSelector(), 
											accountRegisCriteria.getEditToolSelector(), EvoWebType.EVOTYPE_WEB);
				}
				/*
				 * 4) send mail confirm
				 */
				try{
					if(accountRegisCriteria.getRegisTypel().equals(AccountEVORegisCriteria.REGISTYPE_EVO)){
						log.info("---sendMailConfirmEVOAccount");
						m900mgService.sendMailConfirmEVOAccount(m900mg, ov102, ov102, po100,accountRegisCriteria.getPasswordForDigi());
					}
					//auto confirm where register by FB,GOO
					else {
						O100Service o100Service = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
						log.info("---updateTabO100Confirm:"+ApplicationHelper.convertToMD5(ov102)+","+m900mg.getId()+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
						o100Service.updateTabO100Confirm(ApplicationHelper.convertToMD5(ov102), m900mg.getId(), ApplicationConstant.PVLOGIN_ANONYMOUS);
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				log.info("-----XONG------");
				return m900mg;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public int validateEVOAccount(AccountEVORegisCriteria accountRegisCriteria) {
		// TODO Auto-generated method stub
		log.info(accountRegisCriteria);
		if(ApplicationHelper.validateRegex(accountRegisCriteria.getEmail(), RegexConstant.EMAIL_PATTERN) == false)
			return -1;
		else if(m900mgService.emailIsExists(accountRegisCriteria.getEmail()) == true)
			return -5;
		else if(accountRegisCriteria.getfName() == null || accountRegisCriteria.getfName().trim().length() == 0 || 
				accountRegisCriteria.getlName() == null || accountRegisCriteria.getlName().trim().length() == 0 )
			return -2;
		else if(accountRegisCriteria.getPassWord().equals(accountRegisCriteria.getRePassWord()) == false)
			return -3;
		else if(ApplicationHelper.validateRegex(accountRegisCriteria.getPassWord(), RegexConstant.PASSWORD_PATTERN) == false)
			return -4;
		else 
			return 1;
	}
	@Override
	public int sendMailRegisSuccess(M900MG m900mg) {
		// TODO Auto-generated method stub
		try {
			VelocityContext context = new VelocityContext();
			Template t = null;
			String emailSubject = null;
			if(("vi").equals(m900mg.getLanguage())){
				t = ve.getTemplate("evo_email_register_success_vi.vm", "UTF-8");
				emailSubject = "BON EVO - Đăng ký thành công";
			}
			else if(("de").equals(m900mg.getLanguage())){
				t = ve.getTemplate("evo_email_register_success_de.vm", "UTF-8");
				emailSubject = "BONEVO - Erfolgreich angemeldet";
			}
			else {
				t = ve.getTemplate("evo_email_register_success_en.vm", "UTF-8");
				emailSubject = "BON EVO - Register success";
			}
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			log.info("send mail: ("+m900mg.getId()+","+m900mg.getMv903Decrypt()+","+ null+","+ ApplicationHelper.decodeTopicString(emailSubject));
			M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
			int mail = m350Service.sendMailTabM350Confirm(m900mg.getId(),  m900mg.getMv903Decrypt(), null, ApplicationHelper.decodeTopicString(emailSubject), ApplicationHelper.decodeTopicString(writer.toString()), m900mg.getMv907() != null?m900mg.getMv907():m900mg.getMv903Decrypt());
			log.info("ket qua send mail: "+mail);	
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int createAccountPiepmeWithExistsingBonevo(int po100, String mv926) {
		// TODO Auto-generated method stub
		try {
			log.info("-----BAT DAU TAO------");
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			O100Service o100Servcie = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
			M900MG m900mg = templateService.findDocumentById(po100, po100, M900MG.class);
			if(m900mg == null || m900mg.getMv924() != null)
				return -1;
			 AccountPiepmeRegisCriteria accountRegisCriteria = new AccountPiepmeRegisCriteria();
			 String info[] = mv926.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
			 accountRegisCriteria.setSecurityNumber(info[2]);
			 accountRegisCriteria.setNickName(info[1]);
			 accountRegisCriteria.setUuid(info[0]);
			/*
			 * 2) update piepme key
			 */
			String piepmeKeyLogin = AESUtilsPiepme.createPiepmeKey(accountRegisCriteria.getUuid(), po100);
			String piepmeKey = piepmeKeyLogin.substring(0, (int)piepmeKeyLogin.length()*2/3);
			o100Servcie.updateTabO100Key(piepmeKeyLogin, piepmeKey, po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
			/*
			 * 4) insert to mongo
			 */
			if (po100 > 0) {
				/*
				 * 4.1) update m900
				 */
				m900mg.setMv924(accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber());
				m900mg.setMv925(piepmeKey);
				templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
				//insert to mongo center
				templateService.saveDocument(0, m900mg, QbMongoCollectionsName.M900);
				/*
				 * 4.2) insert n100 piepme
				 */
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				N100PMG n100PMG = templateService.findDocument(po100, N100PMG.class, new QbCriteria("NV101", accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber()));
				System.out.println(n100PMG);
				if(n100PMG == null){
					System.out.println("--insert n100 piep");
					 n100PMG = new N100PMG();
					 try {
						n100PMG.setId((int)sequenceService.getNextSequenceIdPiepMe(po100, "N100"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 n100PMG.setNv101(accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber());
					 n100PMG.setNv101Stem(ApplicationHelper.getStemString(accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber()));
					 n100PMG.setNv102(piepmeKey);
					 n100PMG.setNv106(accountRegisCriteria.getNickName());
					 n100PMG.setNv107(accountRegisCriteria.getSecurityNumber());
					 n100PMG.setFo100(po100);
					 n100PMG.setNl166(new Date());
					 n100PMG.setNl168(new Date());
					 templateService.saveDocument(po100, n100PMG, "N100");
				}
				return po100;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	@Override
	public int createAccountPiepme(String email, String uuid, String nickName, String securityNumber) {
		// TODO Auto-generated method stub

		int po100 = 0;
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			/*
			 * 1) find account of this email
			 */
			M900MG m900mg = templateService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, AESUtils.encrypt(email)));
			/*
			 * 2) if account already exists but not registered PiepMe account
			 */
			if(m900mg != null && m900mg.getMv924() == null){
			    /*
				* 2.1) create piepme account
				*/
				String mv926 = uuid + ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN + nickName + ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN + securityNumber;
				/*
				 * store temp info using to confirm email
				 */
				po100 = createAccountPiepmeWithExistsingBonevo(m900mg.getId(), mv926);
			}
			else {
				/*
				 * 3) create new account
				 */
				AccountPiepmeRegisCriteria a = new AccountPiepmeRegisCriteria();
				a.setUuid(uuid);
				a.setEmail(email);;
				a.setNickName(nickName);
				a.setSecurityNumber(securityNumber);
				po100 = createAccountPiepmeNew(a);
			}
			/*
			 * 4) when create successfully
			 */
			if(po100 > 0){
				/*
				 * 4.1) auto confirm email
				 */
				log.info("---updateTabO100Confirm:"+ApplicationHelper.convertToMD5(email.trim())+","+po100+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
				O100Service o100Service = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
				String returnEmail = o100Service.updateTabO100Confirm(ApplicationHelper.convertToMD5(email.trim()), po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				log.info("---returnEmail:"+returnEmail);
				/*
				 * 4.1.1) save to mongo new email
				 */
				if(m900mg == null)
					m900mg = templateService.findDocumentById(po100, po100, M900MG.class);
				if(m900mg != null){
					/*
					 * v2) update to db user
					 */
					m900mg.setMn909(1);
					m900mg.setMn919(1);//on flag confirmed email
					m900mg.setMv903(AESUtils.encrypt(returnEmail));
					m900mg.setMv921("");
					templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
					/*
					 * 4.1.3) update piepme key
					 *  update ThoaiNH, 20/07/2016 
					 */
					if(m900mg.getMv925() != null)
					{
						String piepmeKey = m900mg.getMv925().substring(0, (int)m900mg.getMv925().length()*2/3);
						o100Service.updateTabO100Key(m900mg.getMv925(), piepmeKey, po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
					}
					/*
					 * 4.1.4) update center db
					 */
					M900MG m900mgCenter = templateService.findDocumentById(0, po100, M900MG.class);
					m900mgCenter.setMn909(1);
					m900mgCenter.setMn919(1);//on flag confirmed email
					m900mgCenter.setMv903(AESUtils.encrypt(returnEmail));
					m900mgCenter.setMv921("");
					templateService.saveDocument(0, m900mgCenter, QbMongoCollectionsName.M900);
				}
				/*
				 * 4.2) create ITANS file
				 */
				T000Servcie t000Servcie = (T000Servcie) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T000P);
				t000Servcie.insertTabT000(po100, email);
				String itansFile = t000Servcie.uploadTanListPDFToAWS(po100);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				templateService.updateOneField(po100, N100PMG.class, QbMongoFiledsName.FO100, po100, "NV103", itansFile, null);
			}
			return po100;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	
	}
	
	
	@Override
	public int createAccountPiepmeNew(AccountPiepmeRegisCriteria accountRegisCriteria) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {
			log.info("-----BAT DAU TAO------:" + accountRegisCriteria);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			O100Service o100Servcie = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
			O100CentService o100CentService = (O100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_O100CENT);
			C100CentService c100CentService = (C100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_C100CENT);
			M900MG m900mgF = templateService.findDocument(0, M900MG.class, new QbCriteria("MV924", accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber()));
			if(m900mgF != null)
				return -1;
			/*
			 * 1) insert o100
			 */
			String uri = c100CentService.getValTabC100MySQL(ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("---insertTabO100Piepme:"+0+","+accountRegisCriteria.getUuid()+","+accountRegisCriteria.getEmail()+","+null+","+ 
														accountRegisCriteria.getUuid()+","+ 
														accountRegisCriteria.getNickName()+ accountRegisCriteria.getSecurityNumber()+","+ 
														1+","+ 
														1+","+
														ApplicationConstant.PVLOGIN_ANONYMOUS+","+
														uri);
			int po100 = o100Servcie.insertTabO100Piepme(0, 
														accountRegisCriteria.getUuid(), 
														accountRegisCriteria.getEmail(), 
														null, 
														accountRegisCriteria.getUuid(), 
														accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber(), 
														1, 
														1, 
														ApplicationConstant.PVLOGIN_ANONYMOUS,
														uri);
			/*
			 * 2) update piepme key
			 */
			String piepmeKeyLogin = AESUtilsPiepme.createPiepmeKey(accountRegisCriteria.getUuid(), po100);
			String piepmeKey = piepmeKeyLogin.substring(0, (int)piepmeKeyLogin.length()*2/3);
			o100Servcie.updateTabO100Key(piepmeKeyLogin, piepmeKey, po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
			/*
			 * 3) insert o100 center
			 */
			log.info("---o100CentService.insertTabO100Piep:"+po100+","+piepmeKey+","+piepmeKey+","+null+","+piepmeKey+","+null+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			o100CentService.insertTabO100Piep(po100, piepmeKey, accountRegisCriteria.getEmail(), null, piepmeKey, accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber(), ApplicationConstant.PVLOGIN_ANONYMOUS);
			N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
			/*
			 * 4) insert to mongo
			 */
			if (po100 > 0) {
				log.info("----kq:" + po100);
				int fn100 = n100Service.getValTabN100(po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * 4.1) insert m900
				 */
				M900MG m900mg = new M900MG();
				m900mg.setId(po100);
				m900mg.setMl946(new Date());
				m900mg.setMl948(new Date());
				m900mg.setFn100O(fn100);
				m900mg.setMv901("");
				m900mg.setMv902("");
				m900mg.setNv100(accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber());
				m900mg.setMv921(AESUtils.encrypt(accountRegisCriteria.getEmail()));
				m900mg.setMn919(0);
				LanguageMG languageMG = new LanguageMG();
				languageMG.setCode("en");
				languageMG.setText("English");
				m900mg.setrLanguageMG(languageMG);
				m900mg.setLanguage("en");
				//set up role topic
				M950MG m950mg = new M950MG();
				m950mg.setMn951(TopicRoleConstant.TOPIC_VIEWABLE_ROLE_1);
				m950mg.setMn952(TopicRoleConstant.TOPIC_ASHARE_ROLE_1);
				m950mg.setMn953(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m950mg.setMn954(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m900mg.setM950mg(m950mg);
				m900mg.setRegion("AS");
				m900mg.setMv924(accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber());
				m900mg.setMv925(piepmeKeyLogin);
				templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
				//insert to mongo center
				templateService.saveDocument(0, m900mg, QbMongoCollectionsName.M900);
				/*
				 * 4.2) insert n100 piepme
				 */
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				N100PMG n100PMG = templateService.findDocument(po100, N100PMG.class, new QbCriteria(QbMongoFiledsName.NV101, accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber()));
				if(n100PMG == null){
					System.out.println("--insert n100 piep");
					 n100PMG = new N100PMG();
					 try {
						n100PMG.setId((int)sequenceService.getNextSequenceIdPiepMe(po100, QbMongoCollectionsName.N100));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 n100PMG.setNv101(accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber());
					 n100PMG.setNv101Stem(ApplicationHelper.getStemString(accountRegisCriteria.getNickName() + accountRegisCriteria.getSecurityNumber()));
					 n100PMG.setNv102(piepmeKey);
					 n100PMG.setNv106(accountRegisCriteria.getNickName());
					 n100PMG.setNv107(accountRegisCriteria.getSecurityNumber());
					 n100PMG.setFo100(po100);
					 n100PMG.setNl166(new Date());
					 n100PMG.setNl168(new Date());
					 templateService.saveDocument(po100, n100PMG, QbMongoCollectionsName.N100);
					 /*
					  * insert to db piepme center
					  */
					 try {
						 templateService.setAccessDBcentPiepme(true);
						 N100CentPMG n100CentPMG = new N100CentPMG();
						 n100CentPMG.setFo100(n100PMG.getFo100());
						 n100CentPMG.setId(n100PMG.getId());
						 n100CentPMG.setNl166(n100PMG.getNl166());
						 n100CentPMG.setNl168(n100PMG.getNl168());
						 n100CentPMG.setNv102(n100PMG.getNv102());
						 n100CentPMG.setNv106(n100PMG.getNv106());
						 n100CentPMG.setNv107(n100PMG.getNv107());
						 n100CentPMG.setNv121(AESUtils.encrypt("piepmedb01#mongo.oohhay.com;27017;piepme;Xmaj82oka28dm;piepme"));
						 templateService.saveDocument(po100, n100PMG, QbMongoCollectionsName.N100);
					 } catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					 }
				}
				/*
				 * 4.3) insert N100 oracle
				 */
				if(fn100 > 0){
					N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
					log.info("--insertTabN100PIE:"+null+","+null+","+accountRegisCriteria.getEmail()+","+po100+","+1 +","+accountRegisCriteria.getEmail());
					n100orService.insertTabN100PIE(null, null, accountRegisCriteria.getEmail(), po100, 1, accountRegisCriteria.getEmail());
				}
				return po100;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	
	}
	/**
	 * create unique String with 5 characters
	 * @return
	 */
	public static String genNV117()
    {
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
        String randomString = "";
        String lookup[] = null;
        int upperBound = 0;
        Random random = new Random();

        lookup = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        upperBound = 36;

        for (int i = 0; i < 5; i++)
        {
            randomString = randomString + lookup[random.nextInt(upperBound)];
        }
        if(templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class, new QbCriteria("NV117", randomString.toUpperCase())) == null)
        	return randomString.toUpperCase();
        else
        	return genNV117();
    }
	@Override
	public int createAccountPiepmeV2(String uuid, String nickName, String securityNumber, String affPiepmeId, String nv002) {
		final String uuidGen = ApplicationHelper.preProcessUIID(uuid);
		try {
			log.info("-----BAT DAU TAO------:" + uuidGen + "," + nickName +","+ securityNumber);
			final String nv101 = nickName.trim() + securityNumber.trim();
			String uuidEncrypt = AESUtils.encrypt(uuidGen);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			O100Service o100Servcie = (O100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O100);
			O100CentService o100CentService = (O100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_O100CENT);
			C100CentService c100CentService = (C100CentService) ApplicationHelper.findBean(com.ohhay.base.constant.SpringBeanNames.SERVICE_NAME_C100CENT);
			
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100PMG = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class, new QbCriteria(QbMongoFiledsName.NV101, nv101));
			if(n100PMG != null)
				return -1;
			String emailTemp = nv101 + "@piepme.com";
			/*
			 * 1) insert o100
			 */
			String uri = c100CentService.getValTabC100MySQL(ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("---insertTabO100Piepme:"+0+","+uuidEncrypt+","+emailTemp+","+null+","+ 
														uuidEncrypt+","+ 
														nv101+","+ 
														1+","+ 
														1+","+
														ApplicationConstant.PVLOGIN_ANONYMOUS+","+
														uri);
			final int po100 = o100Servcie.insertTabO100Piepme(0, 
														uuidEncrypt, 
														emailTemp, 
														null, 
														uuidEncrypt, 
														nv101, 
														1, 
														1, 
														ApplicationConstant.PVLOGIN_ANONYMOUS,
														uri);
			/*
			 * 2) update piepme key
			 */
			String piepmeKeyLogin = AESUtilsPiepme.createPiepmeKey(uuidGen, po100);
			String piepmeKey = piepmeKeyLogin.substring(0, (int)piepmeKeyLogin.length()*2/3);
			o100Servcie.updateTabO100Key(piepmeKeyLogin, piepmeKey, po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
			/*
			 * 3) insert o100 center
			 */
			log.info("---o100CentService.insertTabO100Piep:"+po100 +","+ piepmeKey +","+ emailTemp +","+ null +","+ piepmeKey +","+ nv101 +","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
			o100CentService.insertTabO100Piep(po100, piepmeKey, emailTemp, null, piepmeKey, nv101, ApplicationConstant.PVLOGIN_ANONYMOUS);
			N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
			/*
			 * 4) insert to mongo
			 */
			if (po100 > 0) {
				/*
				 * update 05/05/2017
				 * create piepme db user
				 * 4.0) 
				 */
				AdminPMGDao adminPMGDao = (AdminPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_ADMINP);
				adminPMGDao.createDBUser(po100);
				/*
				 * 4.1) insert m900
				 */
				log.info("----kq:" + po100);
				int fn100 = n100Service.getValTabN100(po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				M900MG m900mg = new M900MG();
				m900mg.setId(po100);
				m900mg.setMl946(new Date());
				m900mg.setMl948(new Date());
				m900mg.setFn100O(fn100);
				m900mg.setMv901("");
				m900mg.setMv902("");
				m900mg.setNv100(nv101);
				m900mg.setMv921(AESUtils.encrypt(emailTemp));
				m900mg.setMn919(0);
				LanguageMG languageMG = new LanguageMG();
				languageMG.setCode("en");
				languageMG.setText("English");
				m900mg.setrLanguageMG(languageMG);
				m900mg.setLanguage("en");
				//set up role topic
				M950MG m950mg = new M950MG();
				m950mg.setMn951(TopicRoleConstant.TOPIC_VIEWABLE_ROLE_1);
				m950mg.setMn952(TopicRoleConstant.TOPIC_ASHARE_ROLE_1);
				m950mg.setMn953(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m950mg.setMn954(TopicRoleConstant.TOPIC_ACOMMENT_ROLE_1);
				m900mg.setM950mg(m950mg);
				m900mg.setRegion("AS");
				m900mg.setMv924(nv101);
				m900mg.setMv925(piepmeKeyLogin);
				templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				templateService.saveDocument(po100, m900mg, QbMongoCollectionsName.M900);
				//insert to mongo center
				templateService.saveDocument(0, m900mg, QbMongoCollectionsName.M900);
				/*
				 * 4.2) insert n100 piepme
				 */
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				if(n100PMG == null){
					System.out.println("--insert n100 piep");
					 n100PMG = new N100PMG();
					 try {
						n100PMG.setId((int)sequenceService.getNextSequenceIdPiepMe(po100, QbMongoCollectionsName.N100));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 n100PMG.setNv101(nv101);
					 n100PMG.setNv101Stem(ApplicationHelper.getStemString(nv101));
					 n100PMG.setNv102(piepmeKey);
					 n100PMG.setNv106(nickName.trim());
					 n100PMG.setNv107(securityNumber.trim());
					 //n100PMG.setNv117(genNV117());
					 n100PMG.setFo100(po100);
					 n100PMG.setNl166(new Date());
					 n100PMG.setNl168(new Date());
					 templateService.saveDocument(po100, n100PMG, QbMongoCollectionsName.N100);
					 /*
					  * insert to db piepme center
					  */
					/* try {
						 templateService.setAccessDBcentPiepme(true);
						 N100CentPMG n100CentPMG = new N100CentPMG();
						 n100CentPMG.setFo100(n100PMG.getFo100());
						 n100CentPMG.setId(n100PMG.getId());
						 n100CentPMG.setNl166(n100PMG.getNl166());
						 n100CentPMG.setNl168(n100PMG.getNl168());
						 n100CentPMG.setNv102(n100PMG.getNv102());
						 n100CentPMG.setNv106(n100PMG.getNv106());
						 n100CentPMG.setNv107(n100PMG.getNv107());
						 n100CentPMG.setNv121(AESUtils.encrypt(uri));
						 templateService.saveDocument(po100, n100PMG, QbMongoCollectionsName.N100);
					 } catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					 }*/
				}
				/*
				 * 4.3) insert N100 oracle
				 */
				if(fn100 > 0){
					N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
					log.info("--insertTabN100PIE:"+null+","+null+","+emailTemp+","+po100+","+1 +","+emailTemp);
					String piepmeID = n100orService.insertTabN100PIE(null, null, emailTemp, po100, 1,emailTemp);
					/*
					 * 4.4) insert N000 oracle
					 */
					N000ORService n000orService = (N000ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N000OR);
					String uuidEncrypted = AESUtils.encrypt(uuidGen.substring(0, 16));
					n000orService.insertTabN000Pie(uuidEncrypted, nv002, po100, emailTemp);
					/*
					 * update 29/03/2017 - piemeid cua cong tac vien gioi thieu dang ky
					 * 4.5) update N100Aff
					 */
					try {
						if(affPiepmeId != null && !affPiepmeId.isEmpty()){
							N100ORService n100orService2 = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
							n100orService2.updateTabN100BUS(po100, affPiepmeId, emailTemp);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					/*
					 * 5) update piepme id to mysql
					 */
					Q100Dao q100Dao = (Q100Dao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q100);
					q100Dao.updateTabQ100Regi(po100, piepmeID, nv101);
					/*
					 * 6) create notification document
					 */
					N900PMG n900pmg = new N900PMG();
					Map<String, Integer> mapNotification = new HashMap<String, Integer>();
					n900pmg.setId(po100);
					n900pmg.setMapNotification(mapNotification);
					templateService.saveDocument(po100, n900pmg, QbMongoCollectionsName.N900);
					
					Thread thread = new Thread(){
					    public void run(){
					    	try {
					    		/*
								 * 7) auto create piepme site
								 */
								try {
									int webId = webLegoService.createPiepmeSite(po100, false);
									/*
									 * update nickname to sitename
									 */
									String siteName = ApplicationHelper.removeAccent(nv101).trim().replaceAll(" ", "-");
									e400mgService.updateSiteName(po100, webId, siteName);
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
					    		/*
								 * 8) auto follow piepme channel
								 */
								F150PMGService f150pmgService = (F150PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_F150P);
								f150pmgService.insertTabF150Admin(po100);
								/*
								 * 9) get voucher piepme
								 */
								V300ORDao v300orDao = (V300ORDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_V300OR);
								int fv300 = v300orDao.checkedTabV300(ApplicationConstant.FO100_SUPER_ADMIN_PIEPME, V300PMG.VV308_DOJ, ApplicationConstant.PVLOGIN_ANONYMOUS);
								if(fv300 > 0){
									P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
									p300Service.pushVoucher(po100, fv300, uuidGen, ApplicationConstant.PVLOGIN_ANONYMOUS);
								}
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
					    }
					};
					thread.start();
				}
				log.info("--TAO XONG TAI KHOAN " + nickName + ", PO100 = " + po100);
				return po100;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	@Override
	public int confirmEnterpriseAccount(int fo100User, int fo100c) {
		// TODO Auto-generated method stub
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			/*
			 * 1) find mongo data
			 */
			N100PMG n100pmg = templateService.findDocument(fo100User, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100User));
			if(n100pmg != null && n100pmg.getK100() != null){
				String uuid = n100pmg.getK100().getKv113();
				/*
				 * 2) cap nhat trang thai cua user
				 */
				n100pmg.getK100().setKv105(K100PMG.KV105_CONFIRMED);
				n100pmg.getK100().setKd109(new Date());
				n100pmg.getK100().setFo100c(fo100c);
				n100pmg.getK100().setKv113(null);
				templateService.saveDocument(fo100User, n100pmg, QbMongoCollectionsName.N100);
				/*
				 * 3) tao tk DN
				 */
				int po100 = createAccountPiepmeV2(uuid, n100pmg.getNv106() + "(DN)", n100pmg.getNv107(), n100pmg.getK100().getKv110(), N000OR.NV002_DN);
				N100PMG n100DN = templateService.findDocument(po100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, po100));
				/*
				 * 4) cap nhat thong tin DN
				 */
				n100pmgService.updateK100(po100, n100pmg.getK100().getKv101(), n100pmg.getK100().getKv102(), n100pmg.getK100().getKv103(),
										  n100pmg.getK100().getKv106(), n100pmg.getK100().getKv107(), n100pmg.getK100().getLicenseImgs(), n100pmg.getK100().getKv110());
				n100DN.setK100(n100pmg.getK100());
				/*
				 * 5) auto update location
				 */
				try {
					if(n100DN.getLocation() == null 
						&& n100pmg.getLocationR() != null 
						&& n100pmg.getLocationR().getCoordinates() != null 
						&& n100pmg.getLocationR().getCoordinates().size() == 2
						&& n100pmg.getLocationR().getCoordinates().get(0) != 1){
							n100DN.setLocation(n100pmg.getLocationR());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				/*
				 * 6) xac nhan
				 */
				n100DN.setK100Con(n100pmg.getK100());
				templateService.saveDocument(po100, n100DN, QbMongoCollectionsName.N100);
				/*
				 * 7) tao channel quan ly KHTT cho doanh nghiep
				 */
				T100PMG t100Bus = templateService.findDocument(po100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, po100), new QbCriteria("TV104", T100PMG.TYPE_COM));
				if (t100Bus == null) {
					int newT100Id = (int) sequenceService.getNextSequenceIdPiepMe(po100, QbMongoCollectionsName.T100);
					T100PMG t100mg = new T100PMG();
					t100mg.setId(newT100Id);
					t100mg.setFo100(po100);
					t100mg.setTv101(T100PMG.TYPE_COM);
					t100mg.setTv102("");
					t100mg.setTv103(T100PMG.TYPE_COM);
					t100mg.setTv104(T100PMG.TYPE_COM);
					t100mg.setTl146(new Date());
					t100mg.setTl148(new Date());
					templateService.saveDocument(po100, t100mg, QbMongoCollectionsName.T100);
				}
				/*
				 * 8) update ORACLE data
				 */
				N000ORService n000orService = (N000ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N000OR);
				N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
				N100ORService n100orService2 = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
				log.info("---updateTabN000Pie:" + "D" + "," + po100 + "," + n100DN.getNv106());
				n000orService.updateTabN000Pie("D", po100, n100DN.getNv106());
				
				log.info("---updateTabN100BUS:" + po100 + "," + n100DN.getK100().getKv110() + ","+ ApplicationConstant.PVLOGIN_ANONYMOUS);
				n100orService.updateTabN100BUS(po100, n100DN.getK100().getKv110(), ApplicationConstant.PVLOGIN_ANONYMOUS);
				
				n100orService2.updateTabN100ADD(null, null, null, n100DN.getK100().getKv102(), n100DN.getK100().getKv107(),
						n100DN.getK100().getKv103(), null, po100, ApplicationConstant.PVLOGIN_ANONYMOUS);
				/*
				 * 9) update MYSQL data
				 */
				Q100Dao q100Dao = (Q100Dao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q100);
				q100Dao.updateTabQ100Busi(po100, n100DN.getNv106());
				/*
				 * 10) gui thong bao tai app SE
				 */
				P300MPMGService p300mService = (P300MPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300MP);
				p300mService.messageByAdmin(fo100User, "Tài khoản doanh nghiệp của bạn đã được xác nhận, hãy tải ứng dụng Piepme SE cho doanh nghiệp để sử dụng ngay!");
				return po100;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
}
