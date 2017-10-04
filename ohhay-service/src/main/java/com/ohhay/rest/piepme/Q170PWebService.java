package com.ohhay.rest.piepme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.service.Q170PMGService;

/**
 * mobile.bonevo.net/service/q170PWebService/.. quan ly phan quyen
 * @author ThoaiNH 
 * create Aug 30, 2017
 */
@Path("q170PWebService")
public class Q170PWebService {
	private static Logger log = Logger.getLogger(N100PWebService.class);

	/**
	 * DN set quyen cho user
	 * @param FO100 int FO100 COM OWNER
	 * @param FO100U int FO100 user
	 * @param FQ400S JSONArray
	 * @return
	 */
	@POST
	@Path("createQ170")
	@Produces("application/json")
	public String createQ170(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			int fo100u = jsonObject.getInt("FO100U");
			JSONArray fq400s = jsonObject.getJSONArray("FQ400S");
			List<Integer> listFQ400 = new ArrayList<Integer>();
			for (int i = 0; i < fq400s.length(); i++)
				listFQ400.add(fq400s.getInt(i));
			Q170PMGService q170pmgService = (Q170PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q170P);
			return QbRestUtils.convertToJsonStringForFunc(q170pmgService.createQ170(fo100, fo100u, listFQ400));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * DN xoa quyen user
	 * @param FQ400 int 
	 * @param FO100 int FO100 COM OWNER
	 * @param FO100U int FO100 user
	 * @param PVLOGIN String
	 * @return
	 */
	@POST
	@Path("stornoTabQ170")
	@Produces("application/json")
	public String stornoTabQ170(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			int fo100u = jsonObject.getInt("FO100U");
			int fq400 = jsonObject.getInt("FQ400");
			String pvLogin = jsonObject.getString("PVLOGIN");
			Q170PMGService q170pmgService = (Q170PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q170P);
			return QbRestUtils.convertToJsonStringForFunc(q170pmgService.stornoTabQ170(fq400, fo100u, fo100, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * danh sach user da duoc phan quyen
	 * @param FO100 int FO100 ADMIN
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("listOfTabQ170Users")
	@Produces("application/json")
	public String listOfTabQ170Users(@QueryParam("FO100") int fo100, @QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			Q170PMGService q170pmgService = (Q170PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q170P);
			return QbRestUtils.convertToJsonStringForProc(q170pmgService.listOfTabQ170Users(fo100, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * quyen sach quyen cua user
	 * @param FO100 int FO100 COM OWNER
	 * @param FO100U int FO100 user
	 * @param PVLOGIN int
	 * @return
	 */
	@GET
	@Path("listOfTabQ170")
	@Produces("application/json")
	public String listOfTabQ170(@QueryParam("FO100") int fo100, @QueryParam("FO100U") int fo100u, @QueryParam("PVLOGIN") String pvLogin) {
		try {
			Q170PMGService q170pmgService = (Q170PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q170P);
			return QbRestUtils.convertToJsonStringForProc(q170pmgService.listOfTabQ170(fo100u, fo100, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * DN set quyen cho user
	 * @param FO100 int FO100 COM OWNER
	 * @param DECENTRALS JSONArray object decentral ex: [{FO100U:1248, FQ400s:[1,2,3]}]
	 * @return
	 */
	@POST
	@Path("createQ170Multi")
	@Produces("application/json")
	public String createQ170Multi(String postString) {
		try {
			log.info("---postString:" + postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = jsonObject.getInt("FO100");
			Map<Integer, List<Integer>> mapDecentral = new HashMap<Integer, List<Integer>>();
			JSONArray decentralArray = jsonObject.getJSONArray("DECENTRALS");
			for(int i = 0; i < decentralArray.length(); i++){
				JSONObject decentral = decentralArray.getJSONObject(i);
				int fo100u = decentral.getInt("FO100U");
				JSONArray fq400s =  decentral.getJSONArray("FQ400S");
				List<Integer> listFQ400 = new ArrayList<Integer>();
				for (int j = 0; j < fq400s.length(); j++)
					listFQ400.add(fq400s.getInt(j));
				mapDecentral.put(fo100u, listFQ400);
			}
			Q170PMGService q170pmgService = (Q170PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_Q170P);
			return QbRestUtils.convertToJsonStringForFunc(q170pmgService.createQ170Multi(fo100, mapDecentral));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
