package com.ohhay.rest.bonevo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.shop.J910MG;
import com.ohhay.core.entities.mongo.videomarketing.V910MG;
import com.ohhay.core.mongo.service.AdminMGService;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.SessionManager;
import com.ohhay.web.core.entities.mongo.web.L400MG;
import com.ohhay.web.core.entities.mongo.web.T400MG;

/**
 * @author ThoaiNH
 *
 */
@Path("q100WebService")
public class Q100WebService {
	private static Logger log = Logger.getLogger(Q100WebService.class);
	/**
	 * @param qv101
	 * @return
	 */
	@GET
	@Path("listOfTabQ100New")
	@Produces("application/json")
	public String listOfTabQ100New(@QueryParam("mv903") String mv903) {
		try {
			log.info(mv903);
			Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			Q100 q100 = q100Service.qbonCheckTabQ100(mv903, ApplicationHelper.convertToMD5("0903387368"), "be", mv903);
			if(q100 != null)
				q100.setQv102(null);
			return QbRestUtils.convertToJsonStringForProc(q100);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @return
	 */
	@GET
	@Path("qhayCheckTabQ100CrossDomain")
	@Produces("application/json")
	public String qhayCheckTabQ100CrossDomainGet() {
		try {
			for (Entry<String, Integer> entry : SessionManager.getMapAllUser().entrySet()) {
				log.info("--id:" + entry.getKey());
				log.info("--fo100:" + entry.getValue());
			}
			return QbRestUtils.convertToJsonStringForProc(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("qhayCheckTabLoginOrel")
	@Produces("application/json")
	public String qhayCheckTabLoginOrel(String postString) {
		try {
			int kq = 0;
			JSONObject jsonObject = new JSONObject(postString);
			String mv903 = jsonObject.get("mv903").toString();
			int fk100 = Integer.parseInt(jsonObject.get("fk100").toString());
			log.info("---mv903:" + mv903);
			log.info("---fk100:" + fk100);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocument(0, M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, AESUtils.encrypt(mv903)));
			if (m900mg != null) {
				J910MG j910mg = templateService.findDocumentById(m900mg.getId(), m900mg.getId(), J910MG.class);
				if (j910mg.getFk100() == fk100)
					kq = 1;
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("qhayCheckTabQ100CrossDomain")
	@Produces("application/json")
	public String qhayCheckTabQ100CrossDomain(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			log.info(jsonObject);
			String sessionId = jsonObject.get("sessionId").toString();
			log.info("--map user size:" + SessionManager.getMapAllUser().size());
			log.info("---sessionId:" + sessionId);
			int fo100 = 0;
			try {
				fo100 = SessionManager.getMapAllUser().get(sessionId);
			} catch (Exception ex) {
				ex.printStackTrace();
				fo100 = SessionManager.getMapAllUser().get(postString);
			}
			log.info("--fo100:" + fo100);
			M900MG m900mgNew = null;
			if (fo100 != 0) {
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				M900MG m900mg2 = templateService.findDocumentById(fo100, fo100,M900MG.class);
				J910MG j910mg = templateService.findDocumentById(fo100, fo100,J910MG.class);
				m900mgNew = new M900MG();
				m900mgNew.setId(m900mg2.getId());
				m900mgNew.setMv907(m900mg2.getMv907());
				m900mgNew.setMv903(m900mg2.getMv903Decrypt());
				if (j910mg != null && j910mg.getJn916() == 1) {
					m900mgNew.setMn909(j910mg.getFk100());
					return QbRestUtils.convertToJsonStringForProc(m900mgNew);
				}
			}
			return QbRestUtils.getErrorStatus();
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("qhayCheckTabQ100")
	@Produces("application/json")
	public String qhayCheckTabQ100(String postString) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			JSONObject jsonObject = new JSONObject(postString);
			String qv101 = jsonObject.get("username").toString();
			String qv102 = jsonObject.get("password").toString();
			Q100Service q100Service = (Q100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			log.info("---qhayChecktabq100:" + qv101 + ","
					+ ApplicationHelper.convertToMD5(qv102) + "," + "phongvt");
			List<Q100> listQ100s = new ArrayList<Q100>();
			Q100 q100 = q100Service.qhayCheckTabQ100(qv101,
					ApplicationHelper.convertToMD5(qv102), "phongvt");
			if (q100 != null) {
				/*
				 * 1) get M900 from mongo
				 */
				M900MG m900mg = templateMGService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
				if (m900mg != null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String stringDate = dateFormat.format(m900mg.getMd904());
					m900mg.setMd904String(stringDate);
					if (m900mg != null) {
						/*
						 * 2) load history of m900
						 */
						M900MGService m900mgService = (M900MGService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
						m900mgService.loadHistory(m900mg);
						/*
						 * 3) list video marketing
						 */
						TemplateService templateService = (TemplateService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						List<V910MG> listV910mgs = templateService
								.findDocuments(q100.getPo100(), V910MG.class,
										new QbCriteria(QbMongoFiledsName.FO100,
												m900mg.getId()));
						for (V910MG v910mg : listV910mgs) {
							TemplateService templateService2 = (TemplateService) ApplicationHelper
									.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
							L400MG l400mg = templateService2.findDocumentById(q100.getPo100(), 
									v910mg.getWebId(), L400MG.class);
							if (l400mg != null)
								v910mg.setVisible(l400mg.getVisible());
						}
						q100.setListV910mgs(listV910mgs);
						/*
						 * 4) load draft thumb
						 */
						T400MG t400mg = templateMGService.findDocument(q100.getPo100(), 
								T400MG.class,
								new QbCriteria(QbMongoFiledsName.FO100, q100
										.getPo100()));
						if (t400mg != null)
							q100.setDraftPageThumb(t400mg.getCv802Full());
						/*
						 * 5) shop item
						 */
						J910MG j910mg = templateMGService.findDocumentById(q100.getPo100(), 
								m900mg.getId(), J910MG.class);
						if (j910mg != null)
							q100.setJ910mg(j910mg);
						q100.setM900mg(m900mg);
					}
				}
				listQ100s.add(q100);
				System.out.println(QbRestUtils .convertToJsonStringForProc(listQ100s));
				log.info("---result:"
						+ QbRestUtils.convertToJsonStringForProc(listQ100s));
			}
			return QbRestUtils.convertToJsonStringForProc(listQ100s);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("qhayCheckTabQ100Evo")
	@Produces("application/json")
	public String qhayCheckTabQ100Evo(String postString) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			JSONObject jsonObject = new JSONObject(postString);
			String qv101 = AESUtils.decrypt(jsonObject.get("username").toString());
			String qv102 = AESUtils.decrypt(jsonObject.get("password").toString());
			String src = AESUtils.decrypt(jsonObject.get("src").toString());
			log.info(qv101+","+qv102+","+src);
			
			Q100Service q100Service = (Q100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			log.info("---qbonCheckTabQ100:" + qv101 + ","
					+ ApplicationHelper.convertToMD5(qv102) +  "," + src + "," + "phongvt");
			List<Q100> listQ100s = new ArrayList<Q100>();
			Q100 q100 = q100Service.qbonCheckTabQ100(qv101,
					ApplicationHelper.convertToMD5(qv102), src,  "phongvt");
			if (q100 != null) {
				/*
				 * 1) get M900 from mongo
				 */
				M900MG m900mg = templateMGService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
				if (m900mg != null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					try {
						String stringDate = dateFormat.format(m900mg.getMd904());
						m900mg.setMd904String(stringDate);
					} catch (NullPointerException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if (m900mg != null) {
						/*
						 * 2) load history of m900
						 */
						M900MGService m900mgService = (M900MGService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
						m900mgService.loadHistory(m900mg);
						/*
						 * 3) list video marketing
						 */
						TemplateService templateService = (TemplateService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						List<V910MG> listV910mgs = templateService
								.findDocuments(q100.getPo100(), V910MG.class,
										new QbCriteria(QbMongoFiledsName.FO100,
												m900mg.getId()));
						for (V910MG v910mg : listV910mgs) {
							TemplateService templateService2 = (TemplateService) ApplicationHelper
									.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
							L400MG l400mg = templateService2.findDocumentById(q100.getPo100(), 
									v910mg.getWebId(), L400MG.class);
							if (l400mg != null)
								v910mg.setVisible(l400mg.getVisible());
						}
						q100.setListV910mgs(listV910mgs);
						/*
						 * 4) load draft thumb
						 */
						T400MG t400mg = templateMGService.findDocument(q100.getPo100(), 
								T400MG.class,
								new QbCriteria(QbMongoFiledsName.FO100, q100
										.getPo100()));
						if (t400mg != null)
							q100.setDraftPageThumb(t400mg.getCv802Full());
						/*
						 * 5) shop item
						 */
						J910MG j910mg = templateMGService.findDocumentById(q100.getPo100(), 
								m900mg.getId(), J910MG.class);
						if (j910mg != null)
							q100.setJ910mg(j910mg);
						q100.setM900mg(m900mg);
					}
				}
				listQ100s.add(q100);
				System.out.println(QbRestUtils .convertToJsonStringForProc(listQ100s));
				log.info("---result:"
						+ QbRestUtils.convertToJsonStringForProc(listQ100s));
			}
			return QbRestUtils.convertToJsonStringForProc(listQ100s);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	

	/**
	 * @param a
	 * @return
	 */
	@GET
	@Path("qhayListTabQ100")
	@Produces("application/json")
	public String qhayListTabQ100(@QueryParam("a") int a) {
		try {
			Q100Service q100Service = (Q100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			List<Q100> list = new ArrayList<Q100>();
			list = q100Service.getListQ100(0, "0", "phongvt");
			log.info("---list size:" + list.size());
			return QbRestUtils.getErrorStatus();
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	@GET
	@Path("qhayListTabQ100Evo")
	@Produces("application/json")
	public String qhayListTabQ100Evo(@QueryParam("a") int a) {
		try {
			Q100Service q100Service = (Q100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			List<Q100> list = new ArrayList<Q100>();
			list = q100Service.getListQ100(0, "0", "phongvt");
			log.info("---list size:" + list.size());
			return QbRestUtils.getErrorStatus();
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("qhayToolsUpdateTabQ100pwd")
	@Produces("application/json")
	public String qhayToolsUpdateTabQ100pwd(String postString) {
		try {
			log.info("postString: " + postString);
			JSONObject jsonObject = new JSONObject(postString);
			String qv101 = jsonObject.get("qv101").toString();
			String qv102 = jsonObject.get("qv102").toString();
			Q100Service q100Service = (Q100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			int kq = q100Service.qhayToolsUpdateTabQ100pwd(qv101, qv102,
					ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("--qhayToolsUpdateTabQ100pwd:" + qv101 + "," + qv102 + ","
					+ ApplicationConstant.PVLOGIN_ANONYMOUS);
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * @param postString
	 * @return
	 */
	@POST
	@Path("qhayCheckTabQ100Re")
	@Produces("application/json")
	public String qhayCheckTabQ100Re(String postString) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			JSONObject jsonObject = new JSONObject(postString);
			String qv101 = jsonObject.get("username").toString();
			String qv102 = jsonObject.get("password").toString();
			Q100Service q100Service = (Q100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			log.info("---qhayChecktabq100:" + qv101 + ","
					+ ApplicationHelper.convertToMD5(qv102) + "," + "phongvt");
			List<Q100> listQ100s = new ArrayList<Q100>();
			Q100 q100 = q100Service.qhayCheckTabQ100(qv101, qv102, "phongvt");
			if (q100 != null) {
				/*
				 * 1) get M900 from mongo
				 */
				M900MG m900mg = templateMGService.findDocumentById(q100.getPo100(), 
						q100.getPo100(), M900MG.class);
				if (m900mg != null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String stringDate = dateFormat.format(m900mg.getMd904());
					m900mg.setMd904String(stringDate);
					if (m900mg != null) {
						/*
						 * 2) load history of m900
						 */
						M900MGService m900mgService = (M900MGService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
						m900mgService.loadHistory(m900mg);
						/*
						 * 3) list video marketing
						 */
						TemplateService templateService = (TemplateService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						List<V910MG> listV910mgs = templateService
								.findDocuments(q100.getPo100(), V910MG.class,
										new QbCriteria(QbMongoFiledsName.FO100,
												m900mg.getId()));
						for (V910MG v910mg : listV910mgs) {
							TemplateService templateService2 = (TemplateService) ApplicationHelper
									.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
							L400MG l400mg = templateService2.findDocumentById(q100.getPo100(), 
									v910mg.getWebId(), L400MG.class);
							if (l400mg != null)
								v910mg.setVisible(l400mg.getVisible());
						}
						q100.setListV910mgs(listV910mgs);
						/*
						 * 4) load draft thumb
						 */
						T400MG t400mg = templateMGService.findDocument(q100.getPo100(), 
								T400MG.class,
								new QbCriteria(QbMongoFiledsName.FO100, q100
										.getPo100()));
						if (t400mg != null)
							q100.setDraftPageThumb(t400mg.getCv802Full());
						/*
						 * 5) shop item
						 */
						J910MG j910mg = templateMGService.findDocumentById(q100.getPo100(), 
								m900mg.getId(), J910MG.class);
						if (j910mg != null)
							q100.setJ910mg(j910mg);
						q100.setM900mg(m900mg);
					}
				}
				listQ100s.add(q100);
				System.out.println(QbRestUtils
						.convertToJsonStringForProc(listQ100s));
				log.info("---result:"
						+ QbRestUtils.convertToJsonStringForProc(listQ100s));
			}
			return QbRestUtils.convertToJsonStringForProc(listQ100s);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
	/**
	 * check login moi nhat
	 * @param postString: password (ma hoa md5)
	 * @return
	 */
	@POST
	@Path("qhayCheckTabQ100Code")
	@Produces("application/json")
	public String qhayCheckTabQ100Code(String postString) {
		try {
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			JSONObject jsonObject = new JSONObject(postString);
			String qv101 = jsonObject.get("username").toString();
			String qv102 = jsonObject.get("password").toString();
			String qv110 = jsonObject.get("regionCode").toString();
			Q100Service q100Service = (Q100Service) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_Q100);
			log.info("---qhayChecktabq100code:" + qv101 + ","
					+ qv102 + "," + qv110 + ","
					+ "phongvt");
			List<Q100> listQ100s = new ArrayList<Q100>();
			Q100 q100 = q100Service.qhayCheckTabQ100Code(qv101, qv102 , qv110, "phongvt");
			if (q100 != null) {
				q100.setPacket(q100.getPacket());
				AdminMGService adminMGService = (AdminMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_ADMINMG); 
				q100.setListLanguages(adminMGService.getUserLanguage(q100.getPo100()));
				/*
				 * 1) get M900 from mongo
				 */
				M900MG m900mg = templateMGService.findDocumentById(q100.getPo100(), 
						q100.getPo100(), M900MG.class);
				if (m900mg != null) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					if(m900mg.getMd904() != null){
						try{
							String stringDate = dateFormat.format(m900mg.getMd904());
							m900mg.setMd904String(stringDate);
						}catch(Exception ex){
							log.info("---user has not set date");
						}
					}
					if (m900mg != null) {
						/*
						 * 2) load history of m900
						 */
						M900MGService m900mgService = (M900MGService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_M900MG);
						m900mgService.loadHistory(m900mg);
						/*
						 * 3) list video marketing
						 */
						TemplateService templateService = (TemplateService) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
						List<V910MG> listV910mgs = templateService
								.findDocuments(q100.getPo100(), V910MG.class,
										new QbCriteria(QbMongoFiledsName.FO100,
												m900mg.getId()));
						for (V910MG v910mg : listV910mgs) {
							TemplateService templateService2 = (TemplateService) ApplicationHelper
									.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
							L400MG l400mg = templateService2.findDocumentById(q100.getPo100(), 
									v910mg.getWebId(), L400MG.class);
							if (l400mg != null)
								v910mg.setVisible(l400mg.getVisible());
						}
						q100.setListV910mgs(listV910mgs);
						/*
						 * 4) load draft thumb
						 */
						T400MG t400mg = templateMGService.findDocument(q100.getPo100(), 
								T400MG.class,
								new QbCriteria(QbMongoFiledsName.FO100, q100
										.getPo100()));
						if (t400mg != null)
						{
							if(t400mg.getFa900() != 0)
								q100.setDraftPageThumb(t400mg.getScreenShot());
							else
								q100.setDraftPageThumb(t400mg.getCv802Full());
						}
						/*
						 * 5) shop item
						 */
						J910MG j910mg = templateMGService.findDocumentById(q100.getPo100(), 
								m900mg.getId(), J910MG.class);
						if (j910mg != null)
							q100.setJ910mg(j910mg);
						q100.setM900mg(m900mg);
					}
				}
				listQ100s.add(q100);
				System.out.println(QbRestUtils
						.convertToJsonStringForProc(listQ100s));
				log.info("---result:"
						+ QbRestUtils.convertToJsonStringForProc(listQ100s));
			}
			return QbRestUtils.convertToJsonStringForProc(listQ100s);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

}
