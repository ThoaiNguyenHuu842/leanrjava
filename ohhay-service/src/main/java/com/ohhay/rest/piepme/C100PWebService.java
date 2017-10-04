package com.ohhay.rest.piepme;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.constant.PiepmeActivity;
import com.ohhay.piepme.mongo.dao.AdminPMGDao;
import com.ohhay.piepme.mongo.entities.interaction.C100PMG;
import com.ohhay.piepme.mongo.service.C100PMGService;
import com.ohhay.piepme.mongo.service.N100PMGService;

/**
 * mobile.bonevo.net/service/c100PWebService/
 * friend management function: add friend, list friend, secure contact...
 * @author ThoaiNH
 * create Jun 3, 2016
 */
@Path("c100PWebService")
public class C100PWebService {
	/**
	 * gui yeu cau ket ban
	 * @param FO100 int 
	 * @param FO100F int
	 * @return
	 */
	@POST
	@Path("sendRequest")
	@Produces("application/json")
	public String sendRequest(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100f = Integer.parseInt(jsonObject.get("FO100F").toString());
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			int rs = c100pService.sendRequest(fo100, fo100f);
			if(rs > 0){
				N100PMGService n100pmgService = (N100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100P);
				//13/07/2017 update oracle
				n100pmgService.upgradeTabV220(fo100, PiepmeActivity.ADD_CONTACT, ApplicationConstant.PVLOGIN_ANONYMOUS);
			}
			return QbRestUtils.convertToJsonStringForFunc(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * xac nhan ket ban
	 * update 12/04/2017: neu confirm du 3 friend thi tu dong dua 3 friend do vao security contact (neu chua set)
	 * @param FO100F int
	 * @param FC100	int
	 * @return
	 */
	@POST
	@Path("confirmRequest")
	@Produces("application/json")
	public String confirmRequest(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100f = Integer.parseInt(jsonObject.get("FO100F").toString());
			int fc100 = Integer.parseInt(jsonObject.get("FC100").toString());
			AdminPMGDao adminDao = (AdminPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_ADMINP);
			long crTime = adminDao.getCurrentTime();
			
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForFunc(crTime, c100pService.confirmRequest(fo100f, fc100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * huy ket ban
	 * @param FO100F int
	 * @param FC100	int
	 * @return
	 */
	@POST
	@Path("cancelRequest")
	@Produces("application/json")
	public String cancelRequest(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100f = Integer.parseInt(jsonObject.get("FO100F").toString());
			int fc100 = Integer.parseInt(jsonObject.get("FC100").toString());
			AdminPMGDao adminDao = (AdminPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_ADMINP);
			long crTime = adminDao.getCurrentTime();
			
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForFunc(crTime, c100pService.cancelRequest(fo100f, fc100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * kiem tra trang thai ket ban
	 * @param FO100A int
	 * @param FO100B int
	 * @return 0 - are not friends / not request,1 - pnFO100A has sent a request to pnFO100B , 2 - pnFO100B has sent a request to pnFO100A, 2 - are friends
	 */
	@GET
	@Path("checkFriendStatus")
	@Produces("application/json")
	public String checkFriendStatus(@QueryParam("FO100A") int fo100A,
			@QueryParam("FO100B") int fo100B) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.checkFriendStatus(fo100A, fo100B));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get all friend of pnPO100
	 * update 04/04/2017: sort by nickname
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabC100Friend")
	@Produces("application/json")
	public String listOfTabC100Friend(@QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.listOfTabC100Friend(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get all user send friend request to pnPO100
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabC100Request")
	@Produces("application/json")
	public String listOfTabC100Request(@QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.listOfTabC100Request(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get all user that pnPN100 sent request
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabC100SentRequest")
	@Produces("application/json")
	public String listOfTabC100SentRequest(@QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.listOfTabC100SentRequest(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * set a nickname for a friend
	 * @param FO100 int
	 * @param FO100F int
	 * @param NICKNAME String
	 * @return
	 */
	@POST
	@Path("updateTabC100Nick")
	@Produces("application/json")
	public String updateTabC100Nick(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100F = Integer.parseInt(jsonObject.get("FO100F").toString());
			String nickName = jsonObject.getString("NICKNAME");
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForFunc(c100pService.updateTabC100Nick(fo100, fo100F, nickName));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * list recent activities
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabC100RecentAc")
	@Produces("application/json")
	public String listOfTabC100RecentAc(@QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.listOfTabC100RecentAc(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * set 3 contacts to secure contacts
	 * @param FO100 int
	 * @param FC100S JSONArray
	 * @return
	 */
	@POST
	@Path("setSecureContacts")
	@Produces("application/json")
	public String setSecureContacts(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			JSONArray fc100s = jsonObject.getJSONArray("FC100S");
			List<Double> list = new ArrayList<Double>();
			for (int i = 0; i < fc100s.length(); i++) {
				list.add(Double.parseDouble(fc100s.getString(i)));
			}
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForFunc(c100pService.updateTabSecureContact(fo100, list));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get 3 secure contact of pnPO100
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("listOfTabC100Secure")
	@Produces("application/json")
	public String listOfTabC100Secure(@QueryParam("FO100") int fo100) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.listOfTabC100Secure(fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get all friend of pnPO100 exclude secure contacts
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabC100FriendExcludeSecure")
	@Produces("application/json")
	public String listOfTabC100FriendExcludeSecure(@QueryParam("FO100") int fo100,
			@QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.listOfTabC100FriendExcludeSecure(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * nhap ma nguoi gioi thieu
	 * @param FO100 int user login
	 * @param CODE String
	 * @return -1 CODE is invalid, -2: user has inserted before, > 0: success (is FO100 of referer), 0: error
	 */
	@POST
	@Path("insertReferrerCode")
	@Produces("application/json")
	public String insertReferrerCode(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String code = jsonObject.getString("CODE");
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForFunc(c100pService.insertReferrerCode(fo100, code));
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
	@Path("listOfTabFO100Friend")
	@Produces("application/json")
	public String listOfTabFO100Friend(@QueryParam("FO100") int fo100) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			List<C100PMG> listC100 = c100pService.listOfTabC100Friend(fo100, 0, Integer.MAX_VALUE);
			List<Integer> listFo100 = new ArrayList<Integer>();
			for(C100PMG c100pmg: listC100)
				listFo100.add(c100pmg.getFo100f());
			return QbRestUtils.convertToJsonStringForProc(listFo100);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * get friends to store in app
	 * @param FO100 int
	 * @param FO100F int
	 * @param LONG_TIME long
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabC100FriendHis")
	@Produces("application/json")
	public String listOfTabC100FriendHis(@QueryParam("FO100") int fo100, @QueryParam("FO100F") int fo100f, @QueryParam("LONG_TIME") long longTime,
										 @QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			AdminPMGDao adminDao = (AdminPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_ADMINP);
			long crTime = adminDao.getCurrentTime();
			return QbRestUtils.convertToJsonStringForProc(crTime, c100pService.listOfTabC100FriendHis(fo100, fo100f, longTime, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param LONGTIME long
	 * @return
	 */
	@GET
	@Path("listOfTabC100FriendDel")
	@Produces("application/json")
	public String listOfTabC100FriendDel(@QueryParam("FO100") int fo100,@QueryParam("LONGTIME") String longtime) {
		try {
			AdminPMGDao adminDao = (AdminPMGDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_ADMINP);
			long crTime = adminDao.getCurrentTime();
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			List<Integer> listFo100 = c100pService.listOfTabC100FriendDel(fo100, Long.parseLong(longtime));
			return QbRestUtils.convertToJsonStringForProc(crTime, listFo100);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * get total secure contact of pnPO100
	 * @param FO100 int
	 * @return
	 */
	@GET
	@Path("getTotalC100Secure")
	@Produces("application/json")
	public String getTotalC100Secure(@QueryParam("FO100") int fo100) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForFunc(c100pService.listOfTabC100Secure(fo100).size());
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param FT100 int
	 * @param FT110 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabC100FriendEdu")
	@Produces("application/json")
	public String listOfTabC100FriendEdu(@QueryParam("FO100") int fo100, @QueryParam("FT100") int ft100, @QueryParam("FT110") int ft110,
										 @QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.listOfTabC100FriendEdu(fo100, ft100, ft110, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabC100FriendAdm")
	@Produces("application/json")
	public String listOfTabC100FriendAdm(@QueryParam("FO100") int fo100, @QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			C100PMGService c100pService = (C100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C100P);
			return QbRestUtils.convertToJsonStringForProc(c100pService.listOfTabC100FriendAdm(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
