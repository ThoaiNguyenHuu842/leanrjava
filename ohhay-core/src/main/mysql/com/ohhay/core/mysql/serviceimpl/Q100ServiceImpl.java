package com.ohhay.core.mysql.serviceimpl;

import java.io.StringWriter;
import java.sql.Date;
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
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.Q100Piepme;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.dao.Q100Dao;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.AESUtilsPiepme;
import com.ohhay.core.utils.ApplicationHelper;

@Service(value = SpringBeanNames.SERVICE_NAME_Q100)
@Scope("prototype")
public class Q100ServiceImpl implements Q100Service {
	private static Logger log = Logger.getLogger(Q100ServiceImpl.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_Q100)
	private Q100Dao q100Dao;
	@Autowired
	TemplateService templateService;
	@Autowired
	VelocityEngine ve;
	@Override
	public List<Q100> getListQ100(int fq100, String qv101, String pvLogin) {
		// TODO Auto-generated method stub
		return q100Dao.getListQ100(fq100, qv101, pvLogin);
	}

	@Override
	public int qhayInsertTabQ100(int pnPQ100, String pvQV101, String pvQV102,
			Date pdQD103, int pnFO100, String pvLogin) {
		// TODO Auto-generated method stub
		return q100Dao.qhayInsertTabQ100(pnPQ100, pvQV101, pvQV102, pdQD103,
				pnFO100, pvLogin);
	}

	@Override
	public Q100 qhayCheckTabQ100(String pvQv101, String pvQv102, String pvLogin) {
		// TODO Auto-generated method stub
		Q100 q100 = q100Dao.qhayCheckTabQ100(pvQv101, pvQv102, pvLogin);
		if (q100 != null) {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(0, q100.getPo100(), M900MG.class);
			if (m900mg != null) {
				/*
				 * 1) load history of m900
				 */
				M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
				try {
					m900mgService.loadHistory(m900mg);
					/*
					 * 2) save to map user login
					 */
					// SessionManager.mapAllUser.put(ApplicationHelper.getHttpSession().getId(),
					// q100.getPo100());
					// log.info("--map size:"+Q100WebService.mapAllUser.size());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				q100.setM900mg(m900mg);
			}
		}
		return q100;
	}

	@Override
	public int qhayToolsUpdateTabQ100pwd(String pvQv101, String pvQv102,
			String pvLogin) {
		// TODO Auto-generated method stub
		return q100Dao.qhayToolsUpdateTabQ100pwd(pvQv101, pvQv102, pvLogin);
	}

	@Override
	public int qhayToolsUpdateTabQ100pkg(int pnFO100, Date pdQD104,
			String pvVV503, String pvLogin) {
		// TODO Auto-generated method stub
		return q100Dao.qhayToolsUpdateTabQ100pkg(pnFO100, pdQD104, pvVV503,
				pvLogin);
	}

	@Override
	public String qhayToolsUpdatetabq100reset(String pvOV102, String pvLOGIN) {
		Logger log = Logger.getLogger(Q100ServiceImpl.class);
		try {
			M900MG m900mg = templateService.findDocument(0, M900MG.class,new QbCriteria(QbMongoFiledsName.MV903, AESUtils.encrypt(pvOV102)));
			if (m900mg == null)
				return "emailfalse";
			int fo100 = m900mg.getId();
			String mv907 = m900mg.getMv907();
			String kq = q100Dao.qhayToolsUpdatetabq100reset(pvOV102, pvLOGIN);
			log.info("code: " + kq);
			VelocityContext context = new VelocityContext();
			String link = "https://bonevo.net/forgotpass?email=" + pvOV102
					+ "&eid=" + kq;
			Template t = ve.getTemplate("email_reset_password.vm", "UTF-8");
			StringWriter writer = new StringWriter();
			String emailSubject = "BONEVO - reset password";
			switch (m900mg.getLanguage()) {
			case "de": {
					context.put("mailTitle", "Neues Kennwort anfordern!");
					context.put("mailContent1",
							"Vielen Dank für Dein Interesse an BONEVO");
					context.put("mailContent2",
							"Um ein neues Kennwort anzufordern klicke bitte den folgenden Link an:");
					context.put(
							"mailLinkConfirm",
							"<a href='"
									+ link
									+ "' style='color: #0099ff;font-family: Roboto, sans-serif;font-size: 12px;line-height: 22px;font-weight: normal;margin: 14px 0px 16px 0px;cursor: pointer;text-decoration:none;display: block;'>Hier bestÃ¤tigen </a>");
					context.put("mailContent3", "Mit freundlichen Grüßen !");
					context.put("mailContent4", "Das BONEVO Team.");
					context.put("mailContent5",
							"Warum Du diese E-Mail erhältst?");
					context.put(
							"mailContent6",
							"BONEVO bittet immer dann um eine BestÃ¤tigung, wenn eine E-Mail-Adresse zu einem BONEVO-Konto verwendet wird. Ohne diese Bestätigung kann Ihre E-Mail-Adresse nicht verwendet werden.");
				}
				break;
			case "vi": {
					context.put("mailTitle", "Lấy lại mật khẩu!");
					context.put("mailContent1",
							"Cảm ơn bạn đã truy cập vào trang BONEVO");
					context.put("mailContent2",
							"Để khôi phục mật khẩu , bạn vui lòng bấm vào liên kết bên dưới :");
					context.put(
							"mailLinkConfirm",
							"<a href='"
									+ link
									+ "' style='color: #0099ff;font-family: Roboto, sans-serif;font-size: 12px;line-height: 22px;font-weight: normal;margin: 14px 0px 16px 0px;cursor: pointer;text-decoration:none;display: block;'>Click vào đây để xác nhận</a>");
					context.put("mailContent3", "Trân trọng !");
					context.put("mailContent4", "BONEVO Team.");
					context.put("mailContent5",
							"Tại sao bạn cần thực hiện thao tác này khi sử dụng BONEVO?");
					context.put(
							"mailContent6",
							"Nhầm đảm bảo Email này gửi đến đúng người và các thông tin tài khoản của bạn sẽ được bảo mật . Nếu bạn không xác nhận ,email này sẽ không thể sử dụng trên BONEVO.");
				}
				break;
			default:{
					context.put("mailTitle", "Receive your password!");
					context.put("mailContent1",
							"Thank you for visit BONEVO");
					context.put("mailContent2",
							"To recover your password, you please click on the link below:");
					context.put(
							"mailLinkConfirm",
							"<a href='"
									+ link
									+ "' style='color: #0099ff;font-family: Roboto, sans-serif;font-size: 12px;line-height: 22px;font-weight: normal;margin: 14px 0px 16px 0px;cursor: pointer;text-decoration:none;display: block;'>Click here to confirm</a>");
					context.put("mailContent3", "Respect !");
					context.put("mailContent4", "BONEVO Team.");
					context.put("mailContent5",
							"Why do you need to perform this action when using BONEVO?");
					context.put(
							"mailContent6",
							"To ensure this email sent to the right people and all the information about your account is confidential. If you do not confirm, this email address will not be used on BONEVO.");
			}
				break;
			}
			t.merge(context, writer);
			log.info("send mail: (" + fo100 + "," + pvOV102 + "," + null + ","
					+ ApplicationHelper.decodeTopicString(emailSubject) + ","
					+ ApplicationConstant.PVLOGIN_ANONYMOUS);
			M350Service m350Service = (M350Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_M350);
			int mail = m350Service.sendMailTabM350Confirm(fo100, pvOV102, null,
					ApplicationHelper.decodeTopicString(emailSubject),
					ApplicationHelper.decodeTopicString(writer.toString()),
					ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("ket qua send mail: " + mail);
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public int qhayToolsUpdatetabq100repwd(String pvOV102, String pvQV102,
			String pvOV061, String pvCHKEY, String pvLOGIN) {
		return q100Dao.qhayToolsUpdatetabq100repwd(pvOV102, pvQV102, pvOV061,
				pvCHKEY, pvLOGIN);
	}

	@Override
	public Q100 qhayCheckTabQ100Code(String pvQv101, String pvQv102,
			String pvQv110, String pvLogin) {
		Q100 q100 = q100Dao.qhayCheckTabQ100Code(pvQv101, pvQv102, pvQv110,
				pvLogin);
		if (q100 != null) {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(q100.getPo100(), q100.getPo100(),M900MG.class);
			if (m900mg != null) {
				/*
				 * 1) load history of m900
				 */
				M900MGService m900mgService = (M900MGService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
				try {
					m900mgService.loadHistory(m900mg);
					/*
					 * 2) save to map user login
					 */
					// SessionManager.mapAllUser.put(ApplicationHelper.getHttpSession().getId(),
					// q100.getPo100());
					// log.info("--map size:"+Q100WebService.mapAllUser.size());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				q100.setM900mg(m900mg);
			}
		}
		return q100;
	}

	@Override
	public Q100 qbonCheckTabQ100(String pvQv101, String pvQv102, String src, String pvLogin) {
		// TODO Auto-generated method stub
		Q100 q100 = q100Dao.qbonCheckTabQ100(pvQv101, pvQv102, src, pvLogin);
		if (q100 != null) {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(q100.getPo100(), q100.getPo100(),M900MG.class);
			/*
			 * da co tai khoan O!hay truoc do, hoac dk theo quy trinh moi
			 */
			if (m900mg != null) {
				/*
				 * 1) load history of m900
				 */
				M900MGService m900mgService = (M900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
				try {
					m900mgService.loadHistory(m900mg);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				q100.setM900mg(m900mg);
			}
			else
				return null;
		}
		return q100;
	}

	@Override
	public Q100Piepme qhayCheckTabQ100Piepme(String uiid, int fo100) {
		// TODO Auto-generated method stub
		String piepmeKey = AESUtilsPiepme.createPiepmeKey(uiid, fo100);
		log.info("---qhayCheckTabQ100Piepme:"+piepmeKey+","+fo100+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
		Q100Piepme q100 = q100Dao.qhayCheckTabQ100Piepme(piepmeKey, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
		return q100;
	}

	@Override
	public int getValTabQ100Piepme(String qv110) {
		// TODO Auto-generated method stub
		return q100Dao.getValTabQ100Piepme(qv110);
	}

	@Override
	public int udpateTabQ100Piep(int pnFO100, String pvNV107, String pvNV112, Date pdND117, String pvNV118,String pvNV119, String pvNV126, String pvNV128, Date pdND129, String pvVV503, String pvLOGIN) {
		// TODO Auto-generated method stub
		return q100Dao.udpateTabQ100Piep(pnFO100, pvNV107, pvNV112, pdND117, pvNV118, pvNV119, pvNV126, pvNV128, pdND129, pvVV503, pvLOGIN);
	}

	@Override
	public Q100Piepme qhayCheckTabQ100PiepCa(String uiid, int fo100) {
		// TODO Auto-generated method stub
		String piepmeKey = AESUtilsPiepme.createPiepmeKey(uiid, fo100);
		log.info("---qhayCheckTabQ100Piepme:"+piepmeKey+","+fo100+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
		Q100Piepme q100 = q100Dao.qhayCheckTabQ100PiepCa(piepmeKey, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
		return q100;
	}

	@Override
	public Q100Piepme qhayCheckTabQ100PiepDn(String uiid, int fo100) {
		// TODO Auto-generated method stub
		String piepmeKey = AESUtilsPiepme.createPiepmeKey(uiid, fo100);
		log.info("---qhayCheckTabQ100Piepme:"+piepmeKey+","+fo100+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
		Q100Piepme q100 = q100Dao.qhayCheckTabQ100PiepDn(piepmeKey, fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
		return q100;
	}

}
