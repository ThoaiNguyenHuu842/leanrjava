package com.ohhay.core.authentication;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.Q170;
import com.ohhay.core.entities.User;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.service.Q100Service;
import com.ohhay.core.mysql.service.Q170Service;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.CookieConstant;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.core.utils.SessionManager;

/**
 * @author ThoaiNH
 * create 25/04/2015
 * AUTHEN RIGHT PROCESS IN APPLICATION
 * USE FOR ALL WEB APPLICATION OF O!HAY
 */
public class AuthenticationHelper{
	private static Logger log = Logger.getLogger(AuthenticationHelper.class);
	/**
	 * load list q170 to user right object
	 * @param fo100
	 * @param ov101
	 * @return
	 */
	public static AuthenticationRightInfo loadUserRights(int fo100, String ov101) {
		// TODO Auto-generated method stub
		log.info("---load user right of fo100:"+fo100);
		AuthenticationRightInfo authenticationRightInfo = null;
		try{
			Q170Service q170Service = (Q170Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q170);
			log.info("---listOfTabQ170:"+fo100+","+0+","+0+","+ov101);
			List<Q170> listQ170s = q170Service.listOfTabQ170(fo100, 0, 0, ov101);
			authenticationRightInfo = new AuthenticationRightInfo();
			for(Q170 q170: listQ170s){
				log.info("--RIGHT:"+q170.getQv173());
				if(q170.getQv173().equals(AuthenticationRightKeys.DOM_RED_OPT))
					authenticationRightInfo.setDOM_RED_OPT(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.DOM_RED_EXP))
					authenticationRightInfo.setDOM_RED_EXP(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.DOM_RED_PRO))
					authenticationRightInfo.setDOM_RED_PRO(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.DOM_RED_DES))
					authenticationRightInfo.setDOM_RED_DES(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.NEW_LET_FRE))
					authenticationRightInfo.setNEW_LET_FRE(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.NEW_LET_OPT))
					authenticationRightInfo.setNEW_LET_OPT(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.NEW_LET_EXP))
					authenticationRightInfo.setNEW_LET_EXP(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.NEW_LET_PRO))
					authenticationRightInfo.setNEW_LET_PRO(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.NEW_LET_DES))
					authenticationRightInfo.setNEW_LET_DES(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.WEB_SIT_FRE))
					authenticationRightInfo.setWEB_SIT_FRE(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.WEB_SIT_OPT))
					authenticationRightInfo.setWEB_SIT_OPT(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.WEB_SIT_EXP))
					authenticationRightInfo.setWEB_SIT_EXP(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.WEB_SIT_DES))
					authenticationRightInfo.setWEB_SIT_DES(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.WEB_SIT_PRO))
					authenticationRightInfo.setWEB_SIT_PRO(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.MER_TAS_FRE))
					authenticationRightInfo.setMER_TAS_FRE(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.MER_TAS_OPT))
					authenticationRightInfo.setMER_TAS_OPT(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.MER_TAS_EXP))
					authenticationRightInfo.setMER_TAS_EXP(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.MER_TAS_PRO))
					authenticationRightInfo.setMER_TAS_PRO(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.MER_TAS_DES))
					authenticationRightInfo.setMER_TAS_DES(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.CUS_MAG_DES))
					authenticationRightInfo.setCUS_MAG_DES(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.SHO_ONL_FRE))
					authenticationRightInfo.setSHO_ONL_FRE(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.SHO_ONL_OPT))
					authenticationRightInfo.setSHO_ONL_OPT(true);
				else if(q170.getQv173().equals(AuthenticationRightKeys.SHO_ONL_PRO))
					authenticationRightInfo.setSHO_ONL_PRO(true);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return authenticationRightInfo;
	}
	
	/** get user login
	 * @return
	 */
	public static Q100 getUserLogin() {
		try {
			log.info("Get session version 5***");
			Q100 q100 = (Q100) ApplicationHelper.getHttpSession().getAttribute(SessionConstant.USER_LOGIN);
			/**
			 * 0) for test
			 */
			if (q100 != null) {
				String serverName = ApplicationHelper.getHttpServletRequest().getServerName();
				log.info("---server name:" + serverName);
				log.info("---ov101:" + q100.getOv101());
				if (serverName.indexOf("localhost") >= 0 || serverName.indexOf("192.168") >= 0 || serverName.indexOf("dev.") >= 0) {
					log.info("---return here");
					return q100;
				}
			}
			/**
			 * end for test
			 */
			/**
			 * 1) get cookie info
			 */
			String userInfoPattern = null;
			String cookieInforLo = null;
			String cookieInforRe = null;
			Cookie[] cookies = ApplicationHelper.getHttpServletRequest().getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					log.info("cookie domain:" + cookie.getDomain());
					log.info("cookie name:" + cookie.getName());
					log.info("cookie info:" + cookie.getValue());
					if (cookie.getName().equals(CookieConstant.USER_LOGIN_LO)) {
						cookieInforLo = AESUtils.decrypt(cookie.getValue());
						if (q100 != null) {
							// if current cookie and session is the same user
							log.info("---cookieInforLo:" + cookieInforLo);
							String[] infors = cookieInforLo.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
							if (infors.length >= 2) {
								if (infors[0].trim().equals(q100.getQv101())) {
									log.info("---return here");
									return q100;
								}
							}
							// else remove current user login map
							//SessionManager.mapAllUser.remove(ApplicationHelper.getHttpSession().getId());
							ApplicationHelper.removeAllSession();
							q100 = null;
						}
					}
					if (cookie.getName().equals(CookieConstant.USER_LOGIN_RE))
						cookieInforRe = AESUtils.decrypt(cookie.getValue());
				}
			}
			else {
				log.info("Cookies null");
			}
			log.info("cookieInforLo:" + cookieInforLo);
			log.info("cookieInforRe:" + cookieInforRe);
			/**
			 * 3) check cookie lo
			 */
			if (cookieInforLo == null) {
				if (cookieInforRe != null)
					userInfoPattern = cookieInforRe;
				else {
					// remove current user login map
					SessionManager.remove(ApplicationHelper.getHttpSession().getId());
					ApplicationHelper.removeAllSession();
					log.info("---return here");
					return null;
				}
			}
			else
				userInfoPattern = cookieInforLo;
			/**
			 * 3) relogin
			 */
			log.info("---userInfoPattern:" + userInfoPattern);
			String[] infors = userInfoPattern.split(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN);
			if (infors.length >= 2) {
				Q100Service q100Service = (Q100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q100);
				log.info("---login with cookies qbonCheckTabQ100:"+infors[0]+","+infors[1]+","+infors[2]+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
				q100 = q100Service.qbonCheckTabQ100(infors[0], infors[1], infors[2], ApplicationConstant.PVLOGIN_ANONYMOUS);
				//set cookies to increase live time if user check remember me
				if (q100 != null) {
					onLoginSuccess(q100, null, null, false);
					log.info("---return here");
					return q100;
				}
			}
			log.info("---return here");
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * save to cookie lo when login success
	 * @param q100
	 * @param user
	 * @param response
	 */
	private static void saveCookieLO(Q100 q100, User user, HttpServletResponse response) {
		// TODO Auto-generated method stub
		log.info("--set cookie to use cross domain");
		Cookie cookieLo = new Cookie(CookieConstant.USER_LOGIN_LO, createCookieData(q100, user));
		cookieLo.setMaxAge(60*60);
		cookieLo.setDomain(".bonevo.net");
		
		Cookie cookieLo2 = new Cookie(CookieConstant.USER_LOGIN_LO, createCookieData(q100, user));
		cookieLo2.setMaxAge(60*60);
		cookieLo2.setDomain(".bonevo.net");
		response.addCookie(cookieLo);
		response.addCookie(cookieLo2);
	}

	/**
	 * save to cookie re when check remember me
	 * @param q100
	 * @param user
	 * @param response
	 */
	public static void saveCookieRO(Q100 q100, User user, HttpServletResponse response) {
		// TODO Auto-generated method stub
		if(user.getRemember() == 1){
			log.info("--set cookie to remember");
			Cookie cookieRe = new Cookie(CookieConstant.USER_LOGIN_RE,createCookieData(q100, user));
			cookieRe.setMaxAge(60 * 60 * 72);// 72h
			cookieRe.setDomain(".bonevo.net");
			
			Cookie cookieRe2 = new Cookie(CookieConstant.USER_LOGIN_RE,createCookieData(q100, user));
			cookieRe2.setMaxAge(60 * 60 * 72);// 72h
			cookieRe2.setDomain(".bonevo.net");
			response.addCookie(cookieRe);
			response.addCookie(cookieRe2);
		}
	}
	/**phone##qb##
	 * pass##qb##
	 * src(fb,goo or bevo)##qb##
	 * fo100(AES encrypted)##qb##
	 * regionAWSCode(ma chau luc)##qb##
	 * email(if confirmed)##qb##
	 * meriankey(if use merian)
	 * @param q100
	 * @param user
	 * @return
	 */
	public static String createCookieData(Q100 q100, User user) {
		// TODO Auto-generated method stub
		log.info("---user:"+user);
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(
				  q100.getQv101()
				+ ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN
				+ q100.getQv102()
				+ ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN 
				+ "be" //update 19/04/2016: luon set la 'be' de khi relogin thi login theo qv101,qv102. khong relogin theo fo100,g100
				+ ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN 
				+ AESUtils.encrypt(String.valueOf(q100.getPo100()))
				+ ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN 
				+ AESUtils.encrypt(q100.getM900mg().getRegion())
				);
		try{
			//email
			if(q100.getM900mg() != null && q100.getM900mg().getMv903()!= null)
				stringBuilder.append(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN+q100.getM900mg().getMv903());
			//merian key
			if(q100.getM900mg() != null && q100.getM900mg().getMv913() != null)
				stringBuilder.append(ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN+q100.getM900mg().getMv913());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		log.info("---cookie value:"+stringBuilder.toString());
		return AESUtils.encrypt(stringBuilder.toString());
	}

	/**
	 * on author success
	 * @param q100
	 * @param user
	 * @param response
	 */
	public static void onLoginSuccess(Q100 q100, User user, HttpServletResponse response, boolean saveToCookie) {
		// TODO Auto-generated method stub
		log.info("---user:"+user);
		/**
		 * 1) load user rights, add to session current online
		 */
		try{
			AuthenticationRightInfo authenticationRightInfo = AuthenticationHelper.loadUserRights(q100.getPo100(), q100.getQv101());
			if(q100.getPacket() == 3 || q100.getPacket() == 4 || q100.getPacket() == 5)
				authenticationRightInfo.setADD_DOC_ALL(true);
			log.info(authenticationRightInfo);
			q100.setAuthenticationRightInfo(authenticationRightInfo);
			SessionManager.put(ApplicationHelper.getHttpSession().getId(), q100.getPo100());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		ApplicationHelper.setSession(SessionConstant.USER_LOGIN, q100);
		/**
		 * update 12/09/2014
		 * bo sung nap lai cookie khi relogin cho getUSerLogin nen phai edit lai o day
		 * 2) save info to cookie
		 */
		if(saveToCookie == true){
			try{
				if(response == null)
					response = ApplicationHelper.getHttpServletResponse();
				if(user == null)
				{
					user = new User();
					user.setPhone(q100.getOv101());
					user.setPassword(q100.getQv102());
					log.info("--q100.getOv108:"+q100.getOv108());
					user.setSrc(q100.getOv108());
				}
				log.info("--user info set to cookie:" + user);
				saveCookieLO(q100, user, response);
				saveCookieRO(q100, user, response);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	/**
	 * authen on app request web view
	 * @param phone
	 * @param po100
	 * @param httpServletResponse
	 * @return
	 */
	public static Q100 authenOnAppEdit(String phone, String po100, HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			log.info("---po100:"+po100);
			log.info("---phone:"+phone);
			M900MG m900mg = templateService.findDocument(0, M900MG.class,new QbCriteria(QbMongoFiledsName.HV101, phone));
			if (ApplicationHelper.convertToMD5(String.valueOf(m900mg.getId())).equals(po100)) {
				Q100 q100 = new Q100();
				q100.setOv101(m900mg.getMv907());
				q100.setQv101(m900mg.getMv907());
				q100.setPo100(m900mg.getId());
				q100.setUseApp(true);
				q100.setM900mg(m900mg);
				log.info("login app set new session");
				User user = new User();
				user.setPhone(q100.getQv101());
				user.setPassword(q100.getQv102());
				onLoginSuccess(q100, user, httpServletResponse,true);
				return q100;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
