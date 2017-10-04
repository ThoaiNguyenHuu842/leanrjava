package com.ohhay.core.mongo.serviceimpl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
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
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.criteria.ChangePassCriteria;
import com.ohhay.core.criteria.M900MGCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900DesMG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M920MG;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.core.mongo.dao.M900MGDao;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.ApplicationHelper;

@Service(value = SpringBeanNames.SERVICE_NAME_M900MG)
@Scope("prototype")
public class M900MGServiceImpl implements M900MGService {
	private static Logger log = Logger.getLogger(M900MGServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_M900MG)
	M900MGDao m900mgDao;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	TemplateService templateMGService;
	VelocityEngine vec = (VelocityEngine) ApplicationHelper.findBean("velocityEngine");

	@Override
	public List<M900MG> findM900All(int limit) {
		// TODO Auto-generated method stub
		return m900mgDao.findM900All(limit);
	}

	@Override
	public int getMaxId() {
		// TODO Auto-generated method stub
		return m900mgDao.getMaxId();

	}

	@Override
	public List<M900MG> findM900Index(int limit) {
		// TODO Auto-generated method stub
		return m900mgDao.findM900Index(limit);
	}

	@Override
	public int checkTabPrivacy(String mv907) {
		// TODO Auto-generated method stub
		return m900mgDao.checkTabPrivacy(mv907);
	}

	@Override
	public void loadHistory(M900MG m900mg) {
		// TODO Auto-generated method stub
		m900mgDao.loadHistory(m900mg);
	}
	@SuppressWarnings("restriction")
	@Override
	public int saveBackgroundTopic(String bgBase64, String defaultImg, M900MG m900mg) {
		// TODO Auto-generated method stub
		int kq = 0;
		try {
			if(defaultImg != null && defaultImg.trim().length() > 0){
				m900mg.setTopicBG(TemplateRule.OHHAY_BASE_LINK+defaultImg);
			}
			else
			{
				log.info("--upload topic img:"+bgBase64);
				// upload to aws
				String imgContent = bgBase64.split("\\,")[1];
				byte[] btDataFile;
				btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				String fileName = "topicBackground72";
				if (awsFileUtils.uploadObjectMutilPart(fileName, btDataFile) > 0)
					m900mg.setTopicBG(fileName);
			}
			kq = templateMGService.saveDocument(0, m900mg, QbMongoCollectionsName.M900);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kq;
	}
	@SuppressWarnings("restriction")
	@Override
	public int saveAvarta(String avartaBase64, M900MG m900mg) {
		// TODO Auto-generated method stub
		int kq = 0;
		try {
			// upload to aws
			String imgContent = avartaBase64.split("\\,")[1];
			byte[] btDataFile;
			btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
			AWSFileUtils awsFileUtils = new AWSFileUtils();
			String fileName = ApplicationConstant.OHHAY_AVARTA_CONSTANT;
			if (awsFileUtils.uploadObjectMutilPart(fileName, btDataFile) > 0) {

				m900mg.setMv908(fileName);
				// re-index this profile
				m900mg.setMn909(1);
				kq = templateMGService.saveDocument(m900mg.getId(), m900mg, QbMongoCollectionsName.M900);
				//update db center
				templateMGService.updateOneField(0, M900MG.class, m900mg.getId(), QbMongoFiledsName.MV908, fileName, QbMongoFiledsName.ML946);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return kq;
	}

	@Override
	public int changePassWord(ChangePassCriteria changePassCiteria, String qv102, String qv101) {
		// TODO Auto-generated method stub

		int kq = 0;
		// confirm old password
		if (!qv102.equals(ApplicationHelper.convertToMD5(changePassCiteria
				.getQv102Old()))) {
			kq = -2;
		}
		// confirm 2 new password
		else if (!changePassCiteria.getQv102New().trim()
				.equals(changePassCiteria.getQv102ReNew().trim()))
			kq = -1;
		else if(ApplicationHelper.validateRegex( changePassCiteria.getQv102New(),RegexConstant.PASSWORD_PATTERN) == false)
			kq = -3;
		else {
			Q100Service q100Service = (Q100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			kq = q100Service
					.qhayToolsUpdateTabQ100pwd(qv101,ApplicationHelper.convertToMD5(changePassCiteria.getQv102New()), qv101);
			// logout and go to home re-login
			if (kq > 0)
				ApplicationHelper.removeAllSession();
		}

		return kq;
	}
	@Override
	public boolean emailIsExists(String email) {
		String emailEncrypt = AESUtils.encrypt(email.trim());
		M900MG m900mg = templateMGService.findDocument(775,M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, emailEncrypt));
		if ( m900mg == null)
		{
			//check email registered before but not confirmed
			m900mg = templateMGService.findDocument(775,M900MG.class, new QbCriteria(QbMongoFiledsName.MV921, emailEncrypt));
			if(m900mg != null)
				return true;
			return false;
		}
		else
		{
			log.info("po100:"+m900mg.getId());
			return true;
		}
	}

	
	
	@Override
	public int changeEmail(int fo100, String qv101, String qv102, String passwordConfirm, String newEmail) {
		// TODO Auto-generated method stub
		try {
			int kq = 1;
			/*
			 * 1) check password confirm
			 */
			if (qv102.equals(ApplicationHelper.convertToMD5(passwordConfirm))) {
				M900MG m900mg = templateMGService.findDocumentById(fo100, fo100, M900MG.class);
				/*
				 * 2) check email input
				 */
				if (newEmail != null && newEmail.trim().length() > 5) {
					if (m900mg.getMv903Decrypt().equals(newEmail))
						kq = -1;// email not change
					else if (ApplicationHelper
							.validateRegex(newEmail, RegexConstant.EMAIL_PATTERN) == false)
						kq = -2;// email pattern error
					else if (emailIsExists(newEmail) == true)
						kq = -3; // email exist
					else {
						kq = sendMail(m900mg, newEmail, qv101, fo100);
						//set flag 'not confirm email' to m900mg
						if(kq > 0){
							templateMGService.updateOneField(fo100, M900MG.class, fo100, QbMongoFiledsName.MN919, 0, QbMongoFiledsName.ML948);
							templateMGService.updateOneField(fo100, M900MG.class, fo100, QbMongoFiledsName.MV921, AESUtils.encrypt(newEmail), QbMongoFiledsName.ML948);
							//udpate db center
							templateMGService.updateOneField(0, M900MG.class, fo100, QbMongoFiledsName.MN919, 0, QbMongoFiledsName.ML948);
							templateMGService.updateOneField(0, M900MG.class, fo100, QbMongoFiledsName.MV921, AESUtils.encrypt(newEmail), QbMongoFiledsName.ML948);
						}
					}
				}
				else
					kq = -2;// email pattern error
			}
			else
				kq = 0;// password confirm error
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int sendEmailAgain(int fo100, String qv101, String qv102, String passwordConfirm, String newEmail) {
		// TODO Auto-generated method stub
		try {
			int kq = 1;
			/*
			 * 1) check password confirm
			 */
			
			if (qv102.equals(passwordConfirm)) {
				M900MG m900mg = templateMGService.findDocumentById(fo100, fo100, M900MG.class);
				String mail = AESUtils.decrypt(newEmail);
				/*
				 * 2) check email input
				 */
				if (AESUtils.encrypt(mail) != null && AESUtils.encrypt(mail).trim().length() > 5) {
					if (ApplicationHelper
							.validateRegex(mail, RegexConstant.EMAIL_PATTERN) == false)
						kq = -2;// email pattern error
					else {
						kq = sendMail(m900mg, mail, qv101, fo100);
						if(kq > 0)
						{
							templateMGService.updateOneField(fo100, M900MG.class, fo100, QbMongoFiledsName.MN919, 0, QbMongoFiledsName.ML948);
							//udpate db center
							templateMGService.updateOneField(0, M900MG.class, fo100, QbMongoFiledsName.MN919, 0, QbMongoFiledsName.ML948);
						}
					}
				}
				else
					kq = -2;// email pattern error
			}
			else
				kq = 0;// password confirm error
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	/** send email confirm
	 * @deprecated
	 * @param m900mg
	 * @param mail
	 * @param qv101
	 * @param fo100
	 * @return
	 */
	private int sendMail(M900MG m900mg, String mail, String qv101, int fo100){
		/*
		 * 1) save to db email content by user language
		 */
		String link = "http://oohhay.com/publicservice/emailconfirm?"
				+ "fo100="
				+ m900mg.getId()
				+ "&"
				+ "email="
				+ ApplicationHelper.convertToMD5(mail);
		
		VelocityContext context = new VelocityContext();
		Template t = vec.getTemplate("email_confirm_template.vm", "UTF-8");
		StringWriter writer = new StringWriter();
		/*
		 * 2) merge with content
		 */
		String emailSubject = "O!hay - Confirm your email address";
		switch (m900mg.getLanguage()) {
		case "de": {
				context.put("mailLogo", "<img style='width: 100%;' src='https://oohhay.com/html/images/email/email-logo.png' />");
				context.put("mailTitle",  "Deine E-Mail-Adresse wurde erfolgreich geändert!");
				context.put("mailContent1",  "Vielen Dank für Dein Interesse an O!hay und Deine Eingabe Deiner E-Mail-Adresse <a style='color: #0099ff;text-decoration: none;' target='_blank' href='mailto:"+mail+"'>"+mail+"</a> zur Nutzung der folgenden Funktionen.");
				context.put("mailContent2",  "Du hast vor kurzem, vOV102, als Deine E-Mail-Adresse für Dein O!hay-Konto verwendet. Klicke bitte unten auf den Link, um zu bestätigen, dass diese E-Mail-Adresse Dir gehört:");
				context.put("mailLinkConfirm",  "<a href='"+link+"' style='color: #0099ff;font-family: Roboto, sans-serif;font-size: 12px;line-height: 22px;font-weight: normal;margin: 14px 0px 16px 0px;cursor: pointer;text-decoration: none;display: bolck;'>Hier bestätigen </a>");
				context.put("mailContent3",  "Mit freundlichen Grüßen!");
				context.put("mailContent4",  "Dein O!hay Team.");
				context.put("mailContent5",  "Warum Du diese E-Mail erhälst?");
				context.put("mailContent6",  "O!hay bittet immer dann um eine Bestätigung, wenn eine E-Mail-Adresse zu einem O!hay-Konto verwendet wird. Ohne diese Bestätigung kann Ihre E-Mail-Adresse nicht verwendet werden.");
				emailSubject = "O!hay - Bestätige bitte Deine E-Mail-Adresse";
			}
			break;
		case "vi": {
				context.put("mailTitle",  "Bạn đã đổi email thành công!");
				context.put("mailContent1",  "Cảm ơn Bạn đã truy cập vào trang O!hay và cập nhật địa chỉ Email <a style='color: #0099ff;text-decoration: none;' target='_blank' href='mailto:"+mail+"'>"+mail+"</a> cho các tác vụ tại đây.");
				context.put("mailContent2",  "Để chắc chắn rằng địa chỉ email trên là của Bạn và Bạn sẽ nhận được đầy đủ các thông tin, thông báo liên quan đến tài khoản của mình trên O!hay, Bạn vui lòng xác nhận giúp chúng tôi 1 lần nữa bằng cách nhấp vào đường link bên dưới:");
				context.put("mailLinkConfirm",  "<a href='"+link+"' style='color: #0099ff;font-family: Roboto, sans-serif;font-size: 12px;line-height: 22px;font-weight: normal;margin: 14px 0px 16px 0px;cursor: pointer;text-decoration: none;display: block;'>Click vào đây để xác nhận</a>");
				context.put("mailContent3",  "Trân trọng !");
				context.put("mailContent4",  "O!hay Team.");
				context.put("mailContent5",  "Tại sao Bạn cần thực hiện thao tác này khi sử dụng O!hay?");
				context.put("mailContent6",  "Nhằm đảm bảo Email này gửi đến đúng người có nhu cầu và mọi thông tin về tài khoản của Bạn được bảo mật. Nếu Bạn không xác nhận, địa chỉ email này sẽ không được đưa vào sử dụng trên O!hay.");
				emailSubject = "O!hay - Xác nhận địa chỉ E-Mail của Bạn";
			}
			break;
		default:{
				context.put("mailLogo", "<img style='width: 100%;' src='https://oohhay.com/html/images/email/email-logo.png' />");
				context.put("mailTitle",  "You have changed email successfully!");
				context.put("mailContent1",  "Thank you for visit O!hay website and updates email address <a style='color: #0099ff;text-decoration: none;' target='_blank' href='mailto:"+mail+"'>"+mail+"</a> for services here.");
				context.put("mailContent2",  "To sure that the email address on that of you and you will get complete information and notices related to your account on the O!hay, Would you please help us confirm one again by clicking the link below :");
				context.put("mailLinkConfirm",  "<a href='"+link+"' style='color: #0099ff;font-family: Roboto, sans-serif;font-size: 12px;line-height: 22px;font-weight: normal;margin: 14px 0px 16px 0px;cursor: pointer;text-decoration: none;display: block;'>Click here to confirm </a>");
				context.put("mailContent3",  "Respect !");
				context.put("mailContent4",  "O!hay Team.");
				context.put("mailContent5",  "Why do you need to perform this action when using O!hay?");
				context.put("mailContent6",  "To ensure this email sent to the right people and all the information about your account is confidential. If you do not confirm, this email address will not be used on O!hay	.");
				emailSubject = "O!hay - Confirm your email address";
		}
			break;
		}
		t.merge(context, writer);
		M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
		int kq = m350Service.sendMailTabM350Confirm(fo100, mail, null,  ApplicationHelper.decodeTopicString(emailSubject),  ApplicationHelper.decodeTopicString(writer.toString()), qv101);
		return kq;
	}
	@Override
	public int getMaxIndexM940(int fo100) {
		// TODO Auto-generated method stub
		return m900mgDao.getMaxIndexM940(fo100);
	}

	@Override
	public int getMaxIdM940(int fo100) {
		// TODO Auto-generated method stub
		return m900mgDao.getMaxIdM940(fo100);
	}

	@Override
	public int changeM940Index(int fo100, int id, int newIndex) {
		// TODO Auto-generated method stub
		return m900mgDao.changeM940Index(fo100, id, newIndex);
	}

	@Override
	public M900MG loadUserProfile(String hv101) {
		// TODO Auto-generated method stub
		return m900mgDao.loadUserProfile(hv101);
	}

	@Override
	public int saveAccount(M900MGCriteria m900mgCriteria, Q100 q100) {
		// TODO Auto-generated method stub
		// save to mongo
		M900MG m900mg = q100.getM900mg();
		M920MG m920mg = new M920MG();
		// save job
		m920mg.setLabel(m900mgCriteria.getJobLabel());
		m920mg.setVal(m900mgCriteria.getJobVal());
		m900mg.setM920MG(m920mg);
		// name
		m900mg.setMv901(AESUtils.encrypt(m900mgCriteria.getMv901()));
		m900mg.setMv902(AESUtils.encrypt(m900mgCriteria.getMv902()));
		m900mg.setNv100(AESUtils.encrypt(m900mgCriteria.getMv902() + " "
				+ m900mgCriteria.getMv901()));
		m900mg.setLanguage(m900mgCriteria.getLanguage());
		try {
			DateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
			Date date = dateFormat.parse(m900mgCriteria.getMd904String());
			m900mg.setMd904(date);
		} catch (Exception ex) {
			return -3;// birth-date format error
		}
		m900mg.setMv905(m900mgCriteria.getMv905());
		m900mg.setMn906(m900mgCriteria.getMn906());
		m900mg.setMn912(m900mgCriteria.getMn912());
		// re-index this profile
		m900mg.setMn909(1);
		int kq = templateMGService.saveDocument(m900mg.getId(), m900mg, QbMongoCollectionsName.M900);
		return kq;
	}

	@Override
	public int saveAccountOneField(String fieldName, Object value, Q100 q100) {
		// TODO Auto-generated method stub
		int kq = 0;
		switch (fieldName) {
		/*
		 * save birthdate
		 */
		case QbMongoFiledsName.MD904:
			try {
				DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
				Date date = dateFormat.parse(value.toString());
				kq = templateMGService.updateOneField(q100.getPo100(), M900MG.class, QbMongoFiledsName.ID, q100.getPo100(), fieldName, date, QbMongoFiledsName.ML946);
				//update center db
				templateMGService.updateOneField(0, M900MG.class, QbMongoFiledsName.ID, q100.getPo100(), fieldName, date, QbMongoFiledsName.ML946);
			} catch (Exception ex) {
				ex.printStackTrace();
				kq =  -3;// birth-date format error 
			}
		/*
		 * job
		 */
		case "JOB":
			String ss[] = value.toString().split("#");
			if(ss!= null && ss.length == 2)
			{
				M900MG m900mg = templateMGService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
				m900mg.getM920MG().setVal(Integer.parseInt(ss[0]));
				m900mg.getM920MG().setLabel(ss[1]);
				kq = templateMGService.saveDocument(q100.getPo100(), m900mg, QbMongoCollectionsName.M900);
				//update center db
				templateMGService.saveDocument(0, m900mg, QbMongoCollectionsName.M900);
			}
			break;
		default:
			kq = templateMGService.updateOneField(q100.getPo100(), M900MG.class, QbMongoFiledsName.ID, q100.getPo100(), fieldName, value, QbMongoFiledsName.ML946);
			//update center db
			templateMGService.updateOneField(0, M900MG.class, QbMongoFiledsName.ID, q100.getPo100(), fieldName, value, QbMongoFiledsName.ML946);
			break;
		}
		if (kq > 0) {
			M900MG m900mg = templateMGService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
			m900mg.setNv100(m900mg.getMv901() + " " + m900mg.getMv902());
			// re index
			templateMGService.updateOneField(q100.getPo100(), M900MG.class, QbMongoFiledsName.ID, q100.getPo100(), QbMongoFiledsName.MN909, 1, QbMongoFiledsName.ML946);
			if (fieldName.equals(QbMongoFiledsName.MV901) || fieldName.equals(QbMongoFiledsName.MV902)){
				// update first name last name
				templateMGService.updateOneField(q100.getPo100(), M900MG.class, QbMongoFiledsName.ID, q100
												.getPo100(), QbMongoFiledsName.NV100, m900mg
												.getNv100(), QbMongoFiledsName.ML946);
				//update center db
				templateMGService.updateOneField(0, M900MG.class, QbMongoFiledsName.ID, q100
												.getPo100(), QbMongoFiledsName.NV100, m900mg
												.getNv100(), QbMongoFiledsName.ML946);
			}
			// reset session user info
			loadHistory(m900mg);
			q100.setM900mg(m900mg);
		}
		return kq;
	}
	public int checkUserExist(String mv907){
		QbCriteria qbCriteria2 = new QbCriteria(QbMongoFiledsName.MD965, null);
		qbCriteria2.setType(QbCriteria.TYPE_NE);
		M900MG m900mg = templateMGService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV907, mv907),qbCriteria2);
		if(m900mg != null)
			return 1;
		else
			return 0;
	}

	@Override
	public int storNotTabM900(int po100) {
		// TODO Auto-generated method stub
		return m900mgDao.storNotTabM900(po100);
	}

	@Override
	public M900MG loadUserProfileMerian(int fo100) {
		// TODO Auto-generated method stub
		return m900mgDao.loadUserProfileMerian(fo100);
	}

	@Override
	public int saveMV923(int po100, String mv923) {
		// TODO Auto-generated method stub
		int kq = 0;
		if(!ApplicationHelper.validateRegex(mv923, RegexConstant.FRIENDLYKEY_PATTERN))
			kq = -1;
		else {
			QbCriteria criteria = new QbCriteria(QbMongoFiledsName.ID,po100);
			criteria.setType(QbCriteria.TYPE_NE);
			M900MG m900mg = templateMGService.findDocument(po100, M900MG.class, QbMongoFiledsName.PART,new QbCriteria(QbMongoFiledsName.MV923, mv923), criteria);
			if(m900mg != null)
				kq = -2;
			else 
			{
				kq = templateMGService.updateOneField(po100, M900MG.class, po100, QbMongoFiledsName.MV923,mv923,QbMongoFiledsName.ML948);
				//update db center
				templateMGService.updateOneField(0, M900MG.class, po100, QbMongoFiledsName.MV923,mv923,QbMongoFiledsName.ML948);
			}
		}
		return kq;
	}

	@Override
	public int storNotTabM900Center(int po100) {
		// TODO Auto-generated method stub
		return m900mgDao.storNotTabM900Center(po100);
	}

	@Override
	public int sendMailConfirmEVOAccount(M900MG m900mg, String mail, String qv101, int fo100, String passForDigi) {
		// TODO Auto-generated method stub
		/*
		 * 1) prepare data
		 */
		String link = ApplicationConstant.CONTEXT_PATH+"publicservice/emailconfirm?"
				+ "fo100="
				+ m900mg.getId()
				+ "&"
				+ "email="
				+ ApplicationHelper.convertToMD5(mail);
		
		VelocityContext context = new VelocityContext();
		Template t = null;
		String mailContent1 = null;
		String emailSubject = null;
		if(("vi").equals(m900mg.getLanguage()))
		{
			t = vec.getTemplate("evo_email_confirm_template_vi.vm", "UTF-8");
			if(passForDigi == null)
				mailContent1 = "Trân trọng !";
			else
				mailContent1 = "Tài khoản BONEVO của bạn là: <b>"+passForDigi+"</b>. <br> Trân trọng !";
			emailSubject = "BONEVO - Xác thực địa chỉ email";
		}
		else if(("de").equals(m900mg.getLanguage()))
		{
			t = vec.getTemplate("evo_email_confirm_template_de.vm", "UTF-8");
			if(passForDigi == null)
				mailContent1 = "Mit freundlichen Grüßen !";
			else
				mailContent1 = "Das Kennwort zum Einloggen bei BONEVO lautet: <b>"+passForDigi+"</b>. <br>Mit freundlichen Grüßen !";
			emailSubject = "BONEVO - Bestätige Deine E-Mail-Adresse";
		}
		else 
		{
			t = vec.getTemplate("evo_email_confirm_template_en.vm", "UTF-8");
			if(passForDigi == null)
				mailContent1 = "Respect !";
			else
				mailContent1 = "Your password to signin BONEVO is: <b>"+passForDigi+"</b>. <br>Respect !";
			emailSubject = "BONEVO - Confirm your email address";
		}
		StringWriter writer = new StringWriter();
		/*
		 * 2) merge with content
		 */
		context.put("mail", mail);
		context.put("mailLinkConfirm", link);
		context.put("mailContent1",  mailContent1);
		t.merge(context, writer);
		M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
		int kq = m350Service.sendMailTabM350Confirm(fo100, mail, null,  ApplicationHelper.decodeTopicString(emailSubject),  ApplicationHelper.decodeTopicString(writer.toString()), qv101);
		return kq;
	}

	@Override
	public List<M900MG> getListAccount(String content,int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		return m900mgDao.getListAccount(content,fo100, offset, limit);
	}

	@Override
	public List<M900MG> listOfTabDesigner(int fo100, int fe400, String pvSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		return m900mgDao.listOfTabDesigner(fo100, fe400, pvSearch, offset, limit);
	}

	@Override
	public M900DesMG listOfTabDesignerOne(int fo100) {
		// TODO Auto-generated method stub
		return m900mgDao.listOfTabDesignerOne(fo100);
	}
}
