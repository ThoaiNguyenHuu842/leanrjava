package com.ohhay.rest.piepme;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.entities.interaction.G100PMG;
import com.ohhay.piepme.mongo.entities.pieper.N100Status;
import com.ohhay.piepme.mongo.service.G100PMGService;

/**
 * mobile.bonevo.net/service/g100PWebService/
 * pieme group: create group, add friend to group,...
 * @author TuNt 
 * create date Aug 9, 2016 
 * ohhay-service
 */
@Path("g100PWebService")
public class G100PWebService {
	private static Logger log = Logger.getLogger(G100PWebService.class);

	/**
	 * create group
	 * @param FO100 int
	 * @param GROUPNAME String
	 * @param GROUPICON String
	 * @return
	 */
	@POST
	@Path("createGroup")
	@Produces("application/json")
	public String createGroup(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			String groupName = jsonObject.getString("GROUPNAME");
			String groupIcon = jsonObject.getString("GROUPICON");
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForFunc(g100pService.createGroup(fo100, groupName, groupIcon));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * add friend to group
	 * @param FO100 int
	 * @param FG100 int
	 * @param FO100STRING String
	 * @return
	 */
	@POST
	@Path("addFriendToGroup")
	@Produces("application/json")
	public String addFriendToGroup(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fg100 = Integer.parseInt(jsonObject.get("FG100").toString());
			String fo100String = jsonObject.getString("FO100STRING");
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForFunc(g100pService.addFriendToGroup(fo100, fg100, fo100String));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * remove friend from group
	 * @param FO100 int
	 * @param FG100 int
	 * @param FO100R int
	 * @return
	 */
	@POST
	@Path("removeFriendFromGroup")
	@Produces("application/json")
	public String removeFriendFromGroup(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fg100 = Integer.parseInt(jsonObject.get("FG100").toString());
			int fo100r = Integer.parseInt(jsonObject.get("FO100R").toString());
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForFunc(g100pService.removeFriendFromGroup(fo100, fg100, fo100r));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * list group user pnFO100 created
	 * @param FO100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @param TYPE String (optional): set TYPE = "PER" to exclude group type "COM"
	 * @return
	 */
	@GET
	@Path("listOfTabG100")
	@Produces("applicaton/json")
	public String listOfTabG100(@QueryParam("FO100") int fo100, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit, @QueryParam("TYPE") String type) {
		try {
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForProc(g100pService.listOfTabG100(fo100, offset, limit, type));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}

	/**
	 * distinct list friend in groups of user pnFO100
	 * @param FO100 int
	 * @param PG100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabG100Friends")
	@Produces("application/json")
	public String listOfTabG100Friends(@QueryParam("FO100") int fo100, @QueryParam("PG100") int pg100, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit, @QueryParam("KV105") String kv105) {
		try {
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForProc(g100pService.listOfTabG100Friends(fo100, pg100, offset, limit, kv105));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get friends for adding to group pnPG100 (expected friends who joined this group before)
	 * @param FO100 int
	 * @param PG100 int
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabFriendsToAdd")
	@Produces("application/json")
	public String listOfTabFriendsToAdd(@QueryParam("FO100") int fo100, @QueryParam("PG100") int pg100, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit) {
		try {
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForProc(g100pService.listOfTabFriendsToAdd(fo100, pg100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PG100 int
	 * @param FIELD String
	 * @param VALUE String
	 * @return
	 */
	@POST
	@Path("updateOneField")
	@Produces("application/json")
	public String updateOneField(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pg100 = Integer.parseInt(jsonObject.get("PG100").toString());
			String field = jsonObject.getString("FIELD");
			String value = jsonObject.getString("VALUE");
			log.info(fo100+","+pg100+","+field+","+value);
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForFunc(templateService.updateOneField(fo100, G100PMG.class, field,value, "GD166", 
																						 new QbCriteria(QbMongoFiledsName.FO100, fo100), 
																						 new QbCriteria(QbMongoFiledsName.ID, pg100)));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * distinct list friend in groups of user pnFO100
	 * EX: FO100=1285&PG100_STRING=19,20,21&OFFSET=0&LIMIT=20
	 * set friends in groups
	 * @param FO100 int
	 * @param PG100_STRING String
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabG100FriendsDis")
	@Produces("application/json")
	public String listOfTabG100FriendsDis(@QueryParam("FO100") int fo100, @QueryParam("PG100_STRING") String pg100String, @QueryParam("OFFSET") int offset,
			@QueryParam("LIMIT") int limit, @QueryParam("KV105") String kv105) {
		try {
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForProc(g100pService.listOfTabG100FriendsDis(fo100, pg100String.replace(",", ApplicationConstant.COOKIE_LOGIN_INFO_PATTERN), offset, limit, kv105));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PG100 int
	 * @return -1 cannot remove COM group, 0: error, 1: success
	 */
	@POST
	@Path("remove")
	@Produces("application/json")
	public String remove(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pg100 = Integer.parseInt(jsonObject.get("PG100").toString());
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			G100PMG g100pmg = templateService.findDocument(fo100, G100PMG.class,  new QbCriteria(QbMongoFiledsName.ID, pg100), new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if(!G100PMG.TYPE_COM.equals(g100pmg.getType()))
				return QbRestUtils.convertToJsonStringForFunc(templateService.removeDocuments(fo100, G100PMG.class, new QbCriteria(QbMongoFiledsName.ID, pg100), new QbCriteria(QbMongoFiledsName.FO100, fo100)));
			return QbRestUtils.convertToJsonStringForFunc(-1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get FO100 Of Loyalty Customer 
	 * @param FO100 int
	 */
	@GET
	@Path("getFO100OfLoyaltyCustomer")
	@Produces("application/json")
	public String getFO100OfLoyaltyCustomer(@QueryParam("FO100") int fo100) {
		try {
			G100PMGService g100service = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			N100Status n100OnlineStatus = g100service.getFO100OfLoyaltyCustomer(fo100);
			return QbRestUtils.convertToJsonStringForProc(n100OnlineStatus);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * get number of loyalty customer of enterprise
	 * @param fo100
	 * @return
	 */
	@GET
	@Path("getNumberOfLoyaltyCustomer")
	@Produces("application/json")
	public String getNumberOfLoyaltyCustomer(@QueryParam("FO100") int fo100) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			List<T100PMG> list = templateService.findDocuments(fo100, T100PMG.class, new QbCriteria("FO100E", fo100));
			return QbRestUtils.convertToJsonStringForFunc(list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @deprecated
	 * remove multiple friend from group
	 * @param FO100 int
	 * @param PG100 int
	 * @param FO100RS JSONArray
	 * @return
	 */
	@POST
	@Path("removeFriendsFromGroup")
	@Produces("application/json")
	public String removeFriendsFromGroup(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pg100 = Integer.parseInt(jsonObject.get("PG100").toString());
			JSONArray fo100s = jsonObject.getJSONArray("FO100RS");
			List<Integer> listFo100Del = new ArrayList<Integer>();
			for (int i = 0; i < fo100s.length(); i++) {
				listFo100Del.add(Integer.parseInt(fo100s.getString(i)));
			}
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForFunc(g100pService.removeFriendFromGroup(fo100,pg100,listFo100Del));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * update list friend in group
	 * @param FO100 int
	 * @param PG100 int
	 * @param FO100R JSONArray
	 * @return
	 */
	@POST
	@Path("updateFO100R")
	@Produces("application/json")
	public String updateFO100R(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pg100 = Integer.parseInt(jsonObject.get("PG100").toString());
			JSONArray fo100s = jsonObject.getJSONArray("FO100R");
			List<Integer> listFo100R = new ArrayList<Integer>();
			for (int i = 0; i < fo100s.length(); i++) {
				listFo100R.add(Integer.parseInt(fo100s.getString(i)));
			}
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForFunc(templateService.updateOneField(fo100, G100PMG.class, pg100, "FO100R", listFo100R, "GD166"));
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
	@Path("listOfTabG100Ids")
	@Produces("application/json")
	public String listOfTabG100Ids(@QueryParam("FO100") int fo100) {
		try {
			G100PMGService g100pService = (G100PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_G100P);
			return QbRestUtils.convertToJsonStringForProc(g100pService.listOfTabG100Ids(fo100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
