package com.ohhay.rest.piepme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort.Direction;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.oracle.N000OR;
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.service.N100ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.other.mysql.service.O050Service;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.entities.pieper.N100CAFMG;
import com.ohhay.piepme.mongo.entities.pieper.N100Status2;
import com.ohhay.piepme.mongo.entities.pieper.N100Status3;
import com.ohhay.piepme.mongo.service.N100PMGService;
import com.ohhay.piepme.mongo.userprofile.N100PMG;
import com.ohhay.piepme.mysql.dao.T000Dao;
import com.ohhay.service.CreateAccountService;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * mobile.bonevo.net/service/n100PWebService/ login, register and all user
 * profile function: update profile, confirm/reject business account...
 * @author ThoaiNH 
 * create Jun 3, 2016
 */
@Path("n100PWebService")
public class N100PWebService {
	private static Logger log = Logger.getLogger(N100PWebService.class);

	/**
	 * register piepme account
	 * @deprecated replaced by {@link #registerV2(String)}
	 * @param EMAIL String
	 * @param UUID String
	 * @param NICKNAME String
	 * @param SECURITYNUMBER String
	 * @return
	 */
	@POST
	@Path("register")
	@Produces("application/json")
	public String register(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String email = jsonObject.get("EMAIL").toString();
			String uuid = jsonObject.get("UUID").toString();
			String nickName = jsonObject.get("NICKNAME").toString();
			String securityNumber = jsonObject.get("SECURITYNUMBER").toString();
			CreateAccountService createAccountService = (CreateAccountService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
			return QbRestUtils.convertToJsonStringForFunc(
					createAccountService.createAccountPiepme(email, uuid, nickName, securityNumber));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param UUID String
	 * @param NICKNAME String
	 * @param AFF_PIEPME_ID String
	 * @param SECURITYNUMBER String
	 * @return
	 */
	@POST
	@Path("registerV2")
	@Produces("application/json")
	public String registerV2(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String uuid = jsonObject.get("UUID").toString();
			String nickName = jsonObject.get("NICKNAME").toString();
			String affPiepmeID = null;
			if (jsonObject.has("AFF_PIEPME_ID"))
				affPiepmeID = jsonObject.get("AFF_PIEPME_ID").toString();
			String securityNumber = jsonObject.get("SECURITYNUMBER").toString();
			CreateAccountService createAccountService = (CreateAccountService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
			return QbRestUtils.convertToJsonStringForFunc(
					createAccountService.createAccountPiepmeV2(uuid, nickName, securityNumber, affPiepmeID, N000OR.NV002_CN));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * login PIEPME account
	 * @deprecated replaced by {@link #loginV2(String)}
	 * @param FO100 int
	 * @param UUID String
	 * @return
	 */
	@POST
	@Path("login")
	@Produces("application/json")
	public String login(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String uuid = jsonObject.get("UUID").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			//return QbRestUtils.convertToJsonStringForProc(n100pService.login(fo100, uuid));
			return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @deprecated replaced by {@link #loginV3(String)}
	 * login PIEPME account
	 * @param NICKNAME String
	 * @param SECURITYNUMBER String
	 * @param UUID String
	 * @return
	 */
	@POST
	@Path("loginV2")
	@Produces("application/json")
	public String loginV2(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String nickName = jsonObject.get("NICKNAME").toString();
			String securityNumber = jsonObject.get("SECURITYNUMBER").toString();
			String uuid = jsonObject.get("UUID").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			//return QbRestUtils.convertToJsonStringForProc(n100pService.loginV2(nickName, securityNumber, uuid));
			return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("getAllUser")
	@Produces("application/json")
	public String getAllUser(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			if (fo100 == 0)
				return QbRestUtils.convertToJsonStringForProc(
						templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class));
			else
				return QbRestUtils
						.convertToJsonStringForProc(templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN,
								N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100)));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * check nickname and security number are exists
	 * @param NICKNAME String
	 * @param SECURITYNUMBER String
	 * @return 1: check success, 0: check fail
	 */
	@POST
	@Path("checkPiepmeAccount")
	@Produces("application/json")
	public String checkPiepmeAccount(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String nickName = jsonObject.get("NICKNAME").toString();
			String securityNumber = jsonObject.get("SECURITYNUMBER").toString();
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mgF = templateService.findDocument(0, M900MG.class,
					new QbCriteria("MV924", nickName + securityNumber));
			if (m900mgF != null)
				return QbRestUtils.convertToJsonStringForFunc(0);
			else
				return QbRestUtils.convertToJsonStringForFunc(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @deprecated
	 * @param MV903 String email encrypted by AES
	 * @return 1: email hop le (chua ton tai)
	 */
	@POST
	@Path("sendOTPToEmail")
	@Produces("application/json")
	public String sendOTPToEmail(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			String mv903 = jsonObject.get("MV903").toString();
			log.info("---email:" + mv903);
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocument(0, M900MG.class,
					new QbCriteria(QbMongoFiledsName.MV903, mv903));
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			int kq = 0;
			String otpCode = null;
			// email not resiger BONEVO
			if (m900mg == null) {
				otpCode = n100pService.sendMailConfirmNewAccount(AESUtils.decrypt(mv903));
				kq = 1;
			}
			// registered BONEVO but not registered PiepMe
			else if (m900mg.getMv924() == null) {
				otpCode = n100pService.sendMailConfirmPiepmeAccount(m900mg, AESUtils.decrypt(mv903),
						AESUtils.decrypt(mv903), m900mg.getId());
				kq = 2;
			} else
				kq = 0;
			log.info("---kq:" + kq);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @deprecated
	 * @param MV903 String email encrypted by AES
	 * @return 1: email hop le (chua ton tai)
	 */
	@POST
	@Path("validateExistEmail")
	@Produces("application/json")
	public String validateExistEmail(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			String mv903 = jsonObject.get("MV903").toString();
			log.info("---email:" + mv903);
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocument(0, M900MG.class,
					new QbCriteria(QbMongoFiledsName.MV903, mv903));
			int kq = 0;
			// email not resiger BONEVO
			if (m900mg == null)
				kq = 1;
			// registered BONEVO but not registered PiepMe
			else if (m900mg.getMv924() == null)
				kq = 2;
			else
				kq = 0;
			log.info("---kq:" + kq);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @deprecated check email in login process to get No. Itan
	 * @param MV903 String
	 * @return
	 */
	@POST
	@Path("checkEmailLogin")
	@Produces("application/json")
	public String checkEmailLogin(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String mv903 = jsonObject.get("MV903").toString();
			log.info("---email:" + mv903);
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocument(0, M900MG.class,
					new QbCriteria(QbMongoFiledsName.MV903, mv903));
			String kq = "";
			if (m900mg != null && m900mg.getMv924() != null) {
				T000Dao t000Dao = (T000Dao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_T000);
				kq += t000Dao.getValTabT000(m900mg.getId(), AESUtils.decrypt(m900mg.getMv903()));
				kq += (ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN + m900mg.getId());
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @deprecated
	 * @param FO100
	 * @param STT
	 * @param ITAN
	 * @return
	 */
	@POST
	@Path("checkItan")
	@Produces("application/json")
	public String checkItan(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int pnFO100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int stt = Integer.parseInt(jsonObject.get("STT").toString());
			String itan = jsonObject.get("ITAN").toString();
			T000Dao t000Dao = (T000Dao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_T000);
			int kq = t000Dao.checkTiTabT000(pnFO100, stt, itan, ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @deprecated confirm new email
	 * @param OTPCODE String
	 * @param EMAIL String
	 * @return
	 */
	@POST
	@Path("confirmNewEmail")
	@Produces("application/json")
	public String confirmNewEmail(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			String otpCode = jsonObject.get("OTPCODE").toString();
			String email = jsonObject.get("EMAIL").toString();
			O050Service o050Service = (O050Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_O050);
			log.info("---ohayConfirmTabO050:" + email + "," + otpCode + "," + "84" + "," + ","
					+ ApplicationConstant.PVLOGIN_ANONYMOUS);
			int kq = o050Service.ohayConfirmTabO050(email, otpCode, "84", ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * search user by nickname + digits
	 * @param FO100 int
	 * @param NICKNAME String
	 * @param DIGITS String
	 * @return list user and friend status (FC100 if exists) with pnFO100
	 */
	@GET
	@Path("findAccount")
	@Produces("application/json")
	public String findAccount(@QueryParam("FO100") int fo100, @QueryParam("NICKNAME") String nickName,
			@QueryParam("DIGITS") String digits) {
		try {
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForProc(n100pService.listOfTabN100(fo100, nickName, digits));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update nick name
	 * @param FO100 int
	 * @param NV106 String
	 * @return
	 */
	@POST
	@Path("updateNV106")
	@Produces("application/json")
	public String updateNV106(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String nv106 = jsonObject.get("NV106").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForFunc(n100pService.updateNV106(fo100, nv106.trim()));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update security digits
	 * @param FO100 int
	 * @param NV107 String
	 * @return
	 */
	@POST
	@Path("updateNV107")
	@Produces("application/json")
	public String updateNV107(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String nv107 = jsonObject.get("NV107").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForFunc(n100pService.updateNV107(fo100, nv107.trim()));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update UI color of user
	 * @param FO100 int
	 * @param NV108 String
	 * @return
	 */
	@POST
	@Path("updateNV108")
	@Produces("application/json")
	public String updateNV108(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String nv108 = jsonObject.get("NV108").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForFunc(n100pService.updateNV108(fo100, nv108));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update user location coordinate update Location
	 * @param FO100 int
	 * @param LAT double
	 * @param LONG double
	 * @param ADDRESSFULLNAME String
	 * @return
	 */
	@POST
	@Path("updateLocation")
	public String updateLocation(String postString) {
		try {
			JSONObject json = new JSONObject(postString);
			final int fo100 = Integer.parseInt(json.getString("FO100"));
			double latitude = Double.parseDouble(json.getString("LAT"));
			double longitude = Double.parseDouble(json.getString("LONG"));
			String addressFullName = json.getString("ADDRESSFULLNAME");
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			int kq = n100pService.updateLocation(fo100, latitude, longitude, addressFullName);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * update user profile
	 * @param FO100 int
	 * @param NV106 String nick name
	 * @param NV107 String security number
	 * @param NV108 String UI color code
	 * @param NN112 String year of birthday
	 * @return 1 succeed, 0 fail, -1 nickname + digits is being used by another
	 *         user
	 */
	@POST
	@Path("updateAll")
	public String updateAll(String postString) {
		try {
			log.info(postString);
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String nv106 = jsonObject.get("NV106").toString();
			String nv107 = jsonObject.get("NV107").toString();
			String nv108 = jsonObject.get("NV108").toString();
			int nn112 = Integer.parseInt(jsonObject.get("NN112").toString());
			N100PMG n100pmgTemp = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.NV101, nv106 + nv107));
			if (n100pmgTemp == null || n100pmgTemp.getFo100() == fo100) {
				N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
						new QbCriteria(QbMongoFiledsName.FO100, fo100));
				n100pmg.setNv101(nv106 + nv107);
				n100pmg.setNv101Stem(ApplicationHelper.getStemString(n100pmg.getNv101()));
				n100pmg.setNl166(new Date());
				n100pmg.setNv106(nv106);
				n100pmg.setNv107(nv107);
				n100pmg.setNv108(nv108);
				n100pmg.setNn112(nn112);
				int kq = templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
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
				return QbRestUtils.convertToJsonStringForFunc(kq);
			}
			return QbRestUtils.convertToJsonStringForFunc(-1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update access token
	 * @param FO100 int
	 * @param NV111 String
	 * @return
	 */
	@POST
	@Path("updateNV111")
	@Produces("application/json")
	public String updateNV111(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String nv111 = jsonObject.get("NV111").toString();
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			int kq = templateService.updateOneField(fo100, N100PMG.class, "NV111", nv111, "NL166",
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * update access token for business account
	 * @param FO100 int
	 * @param KV111 String
	 * @return
	 */
	@POST
	@Path("updateKV111")
	@Produces("application/json")
	public String updateKV111(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String kv111 = jsonObject.get("KV111").toString();
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			int kq = 0;
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if(n100pmg != null && n100pmg.getK100Con() != null)
			{
				//tach app nen update uuid vao nv111
				n100pmg.setNv111(kv111);
				n100pmg.getK100Con().setKv111(kv111);
				kq = templateService.saveDocument(fo100, n100pmg, QbMongoCollectionsName.N100);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * update user's year of birthday
	 * @param FO100 int
	 * @param NN112 int
	 * @return
	 */
	@POST
	@Path("updateNN112")
	@Produces("application/json")
	public String updateNN112(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int nn112 = Integer.parseInt(jsonObject.get("NN112").toString());
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			int kq = templateService.updateOneField(fo100, N100PMG.class, "NN112", nn112, "NL166",
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update user device name
	 * @param FO100 int
	 * @param NV114 String
	 * @return
	 */
	@POST
	@Path("updateNV114")
	@Produces("application/json")
	public String updateNV114(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String nv114 = jsonObject.get("NV114").toString();
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			int kq = templateService.updateOneField(fo100, N100PMG.class, "NV114", nv114, "NL166",
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * get device name and access token used in info center
	 * @param FO100S String
	 * @return
	 */
	@GET
	@Path("getListToken")
	@Produces("application/json")
	public String getListToken(@QueryParam("FO100S") String fo100S) {
		try {
			N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForProc(n100pmgService.listOfTabN100Token(fo100S));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * find user by wildcard user name
	 * @param PVSEARCH String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabN100Sug")
	@Produces("application/json")
	public String listOfTabN100Sug(@QueryParam("PVSEARCH") String pvSearch, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit) {
		try {
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForProc(n100pService.listOfTabN100Sug(pvSearch, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param UUID String
	 * @return
	 */
	@GET
	@Path("getN100ByUUID")
	@Produces("application/json")
	public String getN100ByUUID(@QueryParam("UUID") String uuid) {
		try {
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForProc(n100pService.getN100ByUUID(uuid));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param NICKNAME String
	 * @param SECURITYNUMBER String
	 * @return
	 */
	@GET
	@Path("getN100ByNV101")
	@Produces("application/json")
	public String getN100ByNV101(@QueryParam("NICKNAME") String nickName,
			@QueryParam("SECURITYNUMBER") String securityNum) {
		try {
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForProc(n100pService.getN100ByNV101(nickName, securityNum));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * random security number
	 * @param NICKNAME String
	 * @return
	 */
	@GET
	@Path("randomSecurityNumber")
	@Produces("application/json")
	public String randomSecurityNumber(@QueryParam("NICKNAME") String nickName) {
		try {
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForFunc(n100pService.randomSecurityNumber(nickName, 0));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update UUID after confirm OTP successfully
	 * @param FO100 int
	 * @param FP150 int
	 * @param UUID String
	 * @return
	 */
	@POST
	@Path("updateUUID")
	@Produces("application/json")
	public String updateUUID(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fp150 = (int) Double.parseDouble(jsonObject.get("FP150").toString());
			String uuid = jsonObject.get("UUID").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			int kq = n100pService.updateUUID(uuid, N000OR.NV002_CN, fo100, fp150);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * update business info
	 * @param FO100 int
	 * @param KV101 String company name
	 * @param KV102 String company address
	 * @param KV103 String tax number
	 * @param KV106 String introduction text
	 * @param KV107 String phone
	 * @param UUID String
	 * @param LICENSE_IMGS JSONArray String license images
	 * @param AFF_PIEPME_ID String affiliate piepme id
	 * @param LAT double
	 * @param LONG double
	 * @param ADDRESSFULLNAME String
	 * @return 1: success, 0: error
	 */
	@POST
	@Path("updateK100")
	@Produces("application/json")
	public String updateK100(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String kv101 = jsonObject.getString("KV101");
			String kv102 = jsonObject.getString("KV102");
			String kv103 = jsonObject.getString("KV103");
			String kv107 = jsonObject.getString("KV107");
			String kv106 = jsonObject.getString("KV106");
			
			JSONArray licenseArray = jsonObject.getJSONArray("LICENSE_IMGS");
			String affPiepmeID = null;
			if (jsonObject.has("AFF_PIEPME_ID"))
				affPiepmeID = jsonObject.get("AFF_PIEPME_ID").toString();
			List<String> listLicenseImgs = new ArrayList<String>();
			for (int i = 0; i < licenseArray.length(); i++)
				listLicenseImgs.add(licenseArray.getString(i));
			N100PMGService n100pService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			int rs = 0;
			if(jsonObject.has("UUID")){
				String uuid = jsonObject.getString("UUID");
				double latitude = Double.parseDouble(jsonObject.getString("LAT"));
				double longitude = Double.parseDouble(jsonObject.getString("LONG"));
				String addressFullName = jsonObject.getString("ADDRESSFULLNAME");
				rs = n100pService.updateK100V2(fo100, kv101, kv102, kv103, kv106, kv107, listLicenseImgs, affPiepmeID, uuid, latitude, longitude, addressFullName);
			}
			else
				rs = 0;//n100pService.updateK100(fo100, kv101, kv102, kv103, kv106, kv107, listLicenseImgs, affPiepmeID);
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * xac nhan tai khoan doanh nghiep
	 * @param FO100 int - doanh nghiep
	 * @param FO100C int - admin
	 * @return
	 */
	@POST
	@Path("confirmK100")
	@Produces("application/json")
	public String confirmK100(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100c = Integer.parseInt(jsonObject.get("FO100C").toString());
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if(n100pmg != null && n100pmg.getK100() != null)
			{
				CreateAccountService createAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
				N100PMGService n100pService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
				if(n100pmg.getK100().getKv113() != null)
					return QbRestUtils.convertToJsonStringForFunc(createAccountService.confirmEnterpriseAccount(fo100, fo100c));
				else
					return QbRestUtils.convertToJsonStringForFunc(n100pService.confirmK100(fo100, fo100c));
					
			}
			return QbRestUtils.convertToJsonStringForFunc(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * tu choi xac nhan tai khoan doanh nghiep
	 * @param FO100 int - doanh nghiep
	 * @param FO100C int - admin
	 * @param KV112 String ly do tu choi (optional)
	 * @return
	 */
	@POST
	@Path("rejectK100")
	@Produces("application/json")
	public String rejectK100(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100c = Integer.parseInt(jsonObject.get("FO100C").toString());
			String kv112 = null;
			if(jsonObject.has("KV112"))
				kv112 = jsonObject.getString("KV112");
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForFunc(n100pService.rejectK100(fo100, fo100c, kv112));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * get enterprise information to show to user
	 * @param FO100 int user login
	 * @param FO100B int enterprise
	 * @return
	 */
	@GET
	@Path("getBusinessInfo")
	@Produces("application/json")
	public String getBusinessInfo(@QueryParam("FO100") int fo100, @QueryParam("FO100B") int fo100b) {
		try {
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForProc(n100pService.getBusinessInfo(fo100, fo100b));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * get enterprise information to show to owner account
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("getK100Info")
	@Produces("application/json")
	public String getK100Info(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null)
				return QbRestUtils.convertToJsonStringForProc(n100pmg.getK100());
			else
				return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * danh sach doanh nghiep theo trang thai
	 * @param TYPE String status "CON","REJ" or "NEW"
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return danh sach account gui yeu cau xac nhan N100 co K100.KV105 = 'NEW'
	 *         da xac nhan N100 co K100.KV105 = 'CON' danh sach account bi tu
	 *         choi N100 co K100.KV105 = 'REJ'
	 */
	@GET
	@Path("listOfTabK100")
	@Produces("application/json")
	public String listOfTabK100(@QueryParam("TYPE") String type, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			List<N100PMG> listN100pmg = (List<N100PMG>) templateService.findDocuments(
					ApplicationConstant.FO100_SUPER_ADMIN, N100PMG.class, QbMongoFiledsName.ID, Direction.DESC, offset,
					limit, new QbCriteria("K100.KV105", type));
			if (listN100pmg != null) {
				for (N100PMG n100pmg : listN100pmg) {
					SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
					if (n100pmg.getNl168() != null) {
						String nl168Str = sf.format(n100pmg.getNl168());
						n100pmg.setNl168Str(nl168Str);
					}
					if (n100pmg.getNl166() != null) {
						String nl166Str = sf.format(n100pmg.getNl166());
						n100pmg.setNl168Str(nl166Str);
					}
				}
				return QbRestUtils.convertToJsonStringForProc(listN100pmg);
			} else
				return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * Danh sach CTV do QueenB quan ly
	 * @param NV126
	 * @param OFFSET
	 * @param LIMIT
	 * @param PVLOGIN
	 * @return
	 */
	@GET
	@Path("listOfTabN100AFF")
	@Produces("application/json")
	public String listOfTabN100AFF(@QueryParam("NV126") String nv126, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLOGIN) {
		try {
			N100ORService n100orService = (N100ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			List<N100OR> n100ors = n100orService.listOfTabN100AFF(nv126, offset, limit, pvLOGIN);
			return QbRestUtils.convertToJsonStringForProc(n100ors);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * Moi CTV co the len Web xem so luong KH ho da gioi thieu, tu quan KH cua
	 * minh..
	 * @param NV126 String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabN100CUS")
	@Produces("application/json")
	public String listOfTabN100CUS(@QueryParam("NV126") String nv126, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLOGIN) {
		try {
			N100ORService n100orService = (N100ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			List<N100OR> n100ors = n100orService.listOfTabN100AFF(nv126, offset, limit, pvLOGIN);
			return QbRestUtils.convertToJsonStringForProc(n100ors);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabN100OPIE")
	@Produces("application/json")
	public String listOfTabN100OPIE(@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit,
			@QueryParam("PVLOGIN") String pvLOGIN) {
		try {
			N100ORService n100orService = (N100ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			List<N100OR> n100ors = n100orService.listOfTabN100OPIE(offset, limit, pvLOGIN);
			return QbRestUtils.convertToJsonStringForProc(n100ors);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * danh sach muon tham gia lam cong tac vien
	 * @param NV126 String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabN100IWA")
	@Produces("application/json")
	public String listOfTabN100IWA(@QueryParam("NV126") String nv126, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit, @QueryParam("PVLOGIN") String pvLOGIN) {
		try {
			N100ORService n100orService = (N100ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			List<N100OR> n100ors = n100orService.listOfTabN100IWA(nv126, offset, limit, pvLOGIN);
			return QbRestUtils.convertToJsonStringForProc(n100ors);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * update affiliate info
	 * @param FO100 int
	 * @param AV101 String first name
	 * @param AV102 String last name
	 * @param AV103 String id card number
	 * @param AV104 String id card image link
	 * @param AV107 String phone number
	 * @param AV111 String address
	 * @param AD110 String birthday dd/MM/yyyy
	 * @return
	 */
	@POST
	@Path("updateA100")
	@Produces("application/json")
	public String updateA100(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String av101 = jsonObject.getString("AV101");
			String av102 = jsonObject.getString("AV102");
			String av103 = jsonObject.getString("AV103");
			String av104 = jsonObject.getString("AV104");
			String av107 = jsonObject.getString("AV107");
			String ad110 = jsonObject.getString("AD110");
			String av111 = jsonObject.getString("AV111");
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForFunc(
					n100pService.updateA100(fo100, av101, av102, av103, av104, av107, ad110, av111));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * get affiliate info
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("getA100Info")
	@Produces("application/json")
	public String getA100Info(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null) {
				if (n100pmg.getA100() != null && n100pmg.getA100().getAd110() != null)
					n100pmg.getA100().setAd110Str(DateHelper.formatDateShort(n100pmg.getA100().getAd110()));
				return QbRestUtils.convertToJsonStringForProc(n100pmg.getA100());
			} else
				return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * get affiliate information to show to user
	 * @param FO100 int user login
	 * @param FO100B int enterprise
	 * @return
	 */
	@GET
	@Path("getAffiliateInfo")
	@Produces("application/json")
	public String getAffiliateInfo(@QueryParam("FO100") int fo100, @QueryParam("FO100B") int fo100b) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null)
				return QbRestUtils.convertToJsonStringForProc(n100pmg.getA100Con());
			else
				return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * web profile - lay thong tin URL web cua user, neu user chua co se tao moi
	 * 1 trang web
	 * @param fo100
	 * @return
	 */
	@GET
	@Path("getBonevoSiteURL")
	@Produces("application/json")
	public String getBonevoSiteURL(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			E400MGService e400mgService = (E400MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_E400MG);
			WebLegoService webLegoService = (WebLegoService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_WEBLEGO);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			N100PMG n100pmg = templateService.findDocument(fo100, N100PMG.class,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if (n100pmg != null) {
				List<E400MG> list = e400mgService.getListMysite(fo100, "", 0, 1);
				if (list != null && list.size() > 0) {
					E400MG e400mg = list.get(0);
					return QbRestUtils.convertToJsonStringForFunc("https://bonevo.net/" + e400mg.getEv405());
				} else {
					boolean isBusinessAccount = n100pmg.getK100Con() != null ? true : false;
					int webID = webLegoService.createPiepmeSite(fo100, isBusinessAccount);
					return QbRestUtils.convertToJsonStringForFunc("https://bonevo.net/e" + webID + "/bonevo-site");
				}
			}
			return QbRestUtils.convertToJsonStringForFunc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * thong tin của CTV gioi thieu thanh cong
	 * @param FO100 int
	 * @param PVLOGIN String
	 * @return
	 */
	@GET
	@Path("listOfTabN100OADD")
	@Produces("application/json")
	public String listOfTabN100OADD(@QueryParam("FO100") int fo100, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			N100ORService n100orService = (N100ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			return QbRestUtils.convertToJsonStringForProc(n100orService.listOfTabN100OADD(fo100, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * queenb xac nhan CTV
	 * @param FO100 int - doanh nghiep
	 * @param FO100C int - admin
	 * @return
	 */
	@POST
	@Path("confirmA100")
	@Produces("application/json")
	public String confirmA100(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100c = Integer.parseInt(jsonObject.get("FO100C").toString());
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForFunc(n100pService.confirmA100(fo100, fo100c));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * queenb tu choi CTV
	 * @param FO100 int - doanh nghiep
	 * @param FO100C int - admin
	 * @return
	 */
	@POST
	@Path("rejectA100")
	@Produces("application/json")
	public String rejectA100(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100c = Integer.parseInt(jsonObject.get("FO100C").toString());
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForFunc(n100pService.rejectA100(fo100, fo100c));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * dang ky tai khoan Bat Dong San
	 * @param FO100 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("updateTabN100REG")
	@Produces("application/json")
	public String updateTabN100REG(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pvLogin = jsonObject.get("PVLOGIN").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			int rs = n100pService.updateTabN100REG(fo100, pvLogin);
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * admin xac nhan khoan Bat Dong San
	 * @param FO100 int
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("updateTabN100IMM")
	@Produces("application/json")
	public String updateTabN100IMM(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pvLogin = jsonObject.get("PVLOGIN").toString();
			N100ORService n100orService = (N100ORService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			int rs = n100orService.updateTabN100IMM(fo100, pvLogin);
			if(rs > 0){
				TemplateService templateService = (TemplateService) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				templateService.updateOneField(fo100, N100PMG.class, "NN120", 1, null, new QbCriteria(QbMongoFiledsName.FO100, fo100));
			}
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * danh sach dang ky bat dong san
	 * @param NV126 (pieper id) PVLOGIN
	 * @param OFFSET int 
	 * @param LIMIT int 
	 * @param PVLOGIN PVLOGIN
	 * @return
	 */
	@GET
	@Path("listOfTabN100RIM")
	@Produces("application/json")
	public String listOfTabN100RIM(@QueryParam("NV126") String nv126, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit,@QueryParam("PVLOGIN") String pvLogin){
		try {
			N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			List<N100OR> result = n100orService.listOfTabN100RIM(nv126, offset, limit, pvLogin);
			return QbRestUtils.convertToJsonStringForProc(result);
		}catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * Danh sach ca nhan xac nhan duoc dang tin bat dong san
	 * @param NV126 PVLOGIN
	 * @param OFFSET int 
	 * @param LIMIT int 
	 * @param PVLOGIN PVLOGIN
	 * @return
	 */
	@GET
	@Path("listOfTabN100IMM")
	@Produces("application/json")
	public String listOfTabN100IMM(@QueryParam("NV126") String nv126, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit,@QueryParam("PVLOGIN") String pvLogin){
		try {
			N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			List<N100OR> result = n100orService.listOfTabN100IMM(nv126, offset, limit, pvLogin);
			return QbRestUtils.convertToJsonStringForProc(result);
		}catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @deprecated
	 * admin xac nhan duoc quyen dang pieper bat dong san
	 * @param 
	 * @param FO100 int 
	 * @param PVLOGIN PVLOGIN
	 * @return
	 */
	@POST
	@Path("confirmTabN100IMM")
	@Produces("application/json")
	public String confirmTabN100IMM(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String pvLogin = jsonObject.getString("PVLOGIN");
			N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
			int rs = n100orService.confirmTabN100IMM(fo100, pvLogin);
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * update status user online/offline
	 * @param FO100 int
	 * @param LAT double
	 * @param LONG double
	 * @param NN119 int
	 * @return
	 */
	@POST
	@Path("updateNN119")
	@Produces("application/json")
	public String updateNN119(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int nn119 = Integer.parseInt(jsonObject.get("NN119").toString());
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			int rs = templateService.updateOneField(fo100, N100PMG.class, QbMongoFiledsName.NN119, nn119, null,
					new QbCriteria(QbMongoFiledsName.FO100, fo100));

			if (nn119 == 1)
			{
				double latitude = Double.parseDouble(jsonObject.getString("LAT"));
				double longitude = Double.parseDouble(jsonObject.getString("LONG"));
				n100pmgService.updateLocationWhenOnline(fo100, latitude, longitude, null);
			}
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @deprecated replaced by {@link #listOfTabN100LocV2(int, int)}
	 * get lat/long by location
	 * @param FO100 int
	 * @param LAT double
	 * @param LONG double
	 * @param RADIUS int
	 * @return
	 */
	@GET
	@Path("listOfTabN100Loc")
	@Produces("application/json")
	public String listOfTabN100Loc(@QueryParam("FO100") int fo100,@QueryParam("LAT") double latitude,@QueryParam("LONG") double longitude,@QueryParam("RADIUS") int radius) {
		N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_N100P);
		return QbRestUtils.convertToJsonStringForProc(n100pmgService.listOfTabN100Loc(fo100, latitude, longitude, radius).getFo100s());
	}
	
	/**
	 * get lat/long by location
	 * @param FO100 int
	 * @param LAT double
	 * @param LONG double
	 * @param RADIUS int
	 * @return
	 */
	@GET
	@Path("listOfTabN100LocV2")
	@Produces("application/json")
	public String listOfTabN100LocV2(@QueryParam("FO100") int fo100,@QueryParam("LAT") double latitude,@QueryParam("LONG") double longitude,@QueryParam("RADIUS") int radius) {
		N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_N100P);		
		N100Status3 n100Status3 = n100pmgService.listOfTabN100LocV2(fo100, latitude, longitude, radius);
		N100Status2 n100Status2 = new N100Status2();
		List<Integer> listFollows = new ArrayList<Integer>();
		if(n100Status3 != null && n100Status3.getFo100follows() != null)
		{
			listFollows.addAll(n100Status3.getFo100follows().getUsersOnl());	
			listFollows.addAll(n100Status3.getFo100follows().getUsersOff());
		}
		List<Integer> listFO100s = new ArrayList<Integer>();
		if(n100Status3 != null && n100Status3.getFo100s() != null)
		{
			listFO100s.addAll(n100Status3.getFo100s().getUsersOnl());
			listFO100s.addAll(n100Status3.getFo100s().getUsersOff());
		}
		n100Status2.setFo100follows(listFollows);
		n100Status2.setFo100s(listFO100s);
		return QbRestUtils.convertToJsonStringForProc(n100Status2);
	}
	
	/**
	 * get lat/long by location
	 * @param FO100 int
	 * @param LAT double
	 * @param LONG double
	 * @param RADIUS int
	 * @return
	 */
	@GET
	@Path("listOfTabN100LocV3")
	@Produces("application/json")
	public String listOfTabN100LocV3(@QueryParam("FO100") int fo100,@QueryParam("LAT") double latitude,@QueryParam("LONG") double longitude,@QueryParam("RADIUS") int radius) {
		N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_N100P);
		return QbRestUtils.convertToJsonStringForProc(n100pmgService.listOfTabN100LocV2(fo100, latitude, longitude, radius));
	}
	
	/**
	 * @deprecated replaced by {@link #listOfTabN100LocRV2(int, int)}
	 * get lat|long by locationR
	 * @param FO100 int
	 * @param RADIUS int
	 * @return
	 */
	@GET
	@Path("listOfTabN100LocR")
	@Produces("application/json")
	public String listOfTabN100LocR(@QueryParam("FO100") int fo100,@QueryParam("RADIUS") int radius) {
		N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_N100P);
		return QbRestUtils.convertToJsonStringForProc(n100pmgService.listOfTabN100LocR(fo100, radius).getFo100s());
	}
	
	/**
	 * get lat|long by locationR
	 * @param FO100 int
	 * @param RADIUS int
	 * @return
	 */
	@GET
	@Path("listOfTabN100LocRV2")
	@Produces("application/json")
	public String listOfTabN100LocRV2(@QueryParam("FO100") int fo100,@QueryParam("RADIUS") int radius) {
		N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_N100P);
		N100Status3 n100Status3 = n100pmgService.listOfTabN100LocRV2(fo100, radius);
		N100Status2 n100Status2 = new N100Status2();
		List<Integer> listFollows = new ArrayList<Integer>();
		if(n100Status3 != null && n100Status3.getFo100follows() != null)
		{
			listFollows.addAll(n100Status3.getFo100follows().getUsersOnl());	
			listFollows.addAll(n100Status3.getFo100follows().getUsersOff());
		}
		List<Integer> listFO100s = new ArrayList<Integer>();
		if(n100Status3 != null && n100Status3.getFo100s() != null)
		{
			listFO100s.addAll(n100Status3.getFo100s().getUsersOnl());
			listFO100s.addAll(n100Status3.getFo100s().getUsersOff());
		}
		n100Status2.setFo100follows(listFollows);
		n100Status2.setFo100s(listFO100s);
		return QbRestUtils.convertToJsonStringForProc(n100Status2);
	}
	
	/**
	 * get lat|long by locationR
	 * @param FO100 int
	 * @param RADIUS int
	 * @return
	 */
	@GET
	@Path("listOfTabN100LocRV3")
	@Produces("application/json")
	public String listOfTabN100LocRV3(@QueryParam("FO100") int fo100,@QueryParam("RADIUS") int radius) {
		N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_N100P);
		return QbRestUtils.convertToJsonStringForProc(n100pmgService.listOfTabN100LocRV2(fo100, radius));
	}
	
	/**
	 * login for personal account
	 * login PIEPME account
	 * @param NICKNAME String
	 * @param SECURITYNUMBER String
	 * @param UUID String
	 * @return
	 */
	@POST
	@Path("loginV3")
	@Produces("application/json")
	public String loginV3(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String nickName = jsonObject.get("NICKNAME").toString();
			String securityNumber = jsonObject.get("SECURITYNUMBER").toString();
			String uuid = jsonObject.get("UUID").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForProc(n100pService.loginV3(nickName, securityNumber, uuid));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * login for enterprise account
	 * login PIEPME account
	 * @param NICKNAME String
	 * @param SECURITYNUMBER String
	 * @param UUID String
	 * @return
	 */
	@POST
	@Path("loginEnterprise")
	@Produces("application/json")
	public String loginEnterprise(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String nickName = jsonObject.get("NICKNAME").toString();
			String securityNumber = jsonObject.get("SECURITYNUMBER").toString();
			String uuid = jsonObject.get("UUID").toString();
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			return QbRestUtils.convertToJsonStringForProc(n100pService.loginEnterprise(nickName, securityNumber, uuid));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * remove account
	 * @param FO100_AD int
	 * @param FO100 int
	 * @return
	 */
	@POST
	@Path("removeAccount")
	@Produces("application/json")
	public String removeAccount(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100Ad = Integer.parseInt(jsonObject.get("FO100_AD").toString());
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			N100PMGService n100pService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			int rs = n100pService.removeAccount(fo100Ad, fo100);
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * get enterprise account FO100 following
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("listOfTabN100COM")
	@Produces("application/json")
	public String listOfTabN100LocRV3(@QueryParam("FO100") int fo100) {
		List<Integer> listFo100 = new ArrayList<Integer>();
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		List<T100PMG> listT100s = templateService.findDocuments(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100));
		for(T100PMG t100pmg: listT100s)
		{
			if(t100pmg.getFo100e() > 0)
				listFo100.add(t100pmg.getFo100e());
		}
		return QbRestUtils.convertToJsonStringForProc(listFo100);
	}
	
	/**
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("listOfTabN100All")
	@Produces("application/json")
	public String listOfTabN100All(@QueryParam("FO100") int fo100) {
		List<Integer> listFo100 = new ArrayList<Integer>();
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		List<N100PMG> lisN100s = templateService.findDocuments(fo100, N100PMG.class);
		for(N100PMG n100pmg: lisN100s)
			listFo100.add(n100pmg.getFo100());
		return QbRestUtils.convertToJsonStringForProc(listFo100);
	}
	
	/**
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("listOfTabN100OCAF")
	@Produces("application/json")
	public String listOfTabN100OCAF(@QueryParam("FO100") int fo100) {
		try {
			N100PMGService n100pmgService = (N100PMGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_N100P);
			List<N100CAFMG> n100CafMg = n100pmgService.listOfTabN100OCaf(fo100);
			return QbRestUtils.convertToJsonStringForProc(n100CafMg);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
