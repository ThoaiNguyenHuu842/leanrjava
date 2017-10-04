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
import com.ohhay.piepme.mongo.channel.T110PMG;
import com.ohhay.piepme.mongo.service.T110PMGService;

/**
 * mobile.bonevo.net/service/t110PWebService
 * branches COM1, COM2 of COM 
 * @author ThoaiNH
 * create Jul 28, 2017
 */
@Path("t110PWebService")
public class T110PWebService {
	private Logger logger = Logger.getLogger(T110PWebService.class);
	/**
	 * admin COM tao COM1
	 * @param FO100 int
	 * @param PT110 int 
	 * @param TV111 String tieu de channel
	 * @param TV112 String hinh dai dien channel
	 * @param LAT double
	 * @param LONG double
	 * @param ADDRESSFULLNAME String
	 * @param FO100S JSONArray danh sach nguoi phu trach
	 * @return -1: user chua dang ky EDU, 0: error, > 0: success
	 */
	@POST
	@Path("createT110COM1")
	@Produces("application/json")
	public String createT110COM1(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt110 = Integer.parseInt(jsonObject.get("PT110").toString());
			String tv111 = jsonObject.get("TV111").toString();
			String tv112 = null;
			if(jsonObject.has("TV112"))
				tv112 = jsonObject.get("TV112").toString();
			double latitude = Double.parseDouble(jsonObject.getString("LAT"));
			double longitude = Double.parseDouble(jsonObject.getString("LONG"));
			String addressFullName = jsonObject.getString("ADDRESSFULLNAME");
			JSONArray fo100s = jsonObject.getJSONArray("FO100S");
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < fo100s.length(); i++)
				list.add(Integer.parseInt(fo100s.getString(i)));
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForFunc(t110pmgService.createT110COM1(fo100, pt110, tv111, tv112, latitude, longitude, addressFullName, list));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * nguoi phu trach COM1 tao COM2
	 * @param FO100 int
	 * @param PT110 int
	 * @param FT110 int id COM1
	 * @param TV111 String tieu de channel
	 * @param TV112 String hinh dai dien channel
	 * @param TV115 (optinal) String yeu cau phai xac nhan khi user tham gia kenh ("C" or null)
	 * @param FO100S  JSONArray danh sach nguoi phu trach
	 * @return -1: COM1 khong ton tai, -2: user khong co quyen tao COM2, 0: error, > 0: success
	 */
	@POST
	@Path("createT110COM2")
	@Produces("application/json")
	public String createT110COM2(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt110 = Integer.parseInt(jsonObject.get("PT110").toString());
			int ft110 = Integer.parseInt(jsonObject.get("FT110").toString());
			String tv111 = jsonObject.get("TV111").toString();
			String tv112 = null;
			if(jsonObject.has("TV112"))
				tv112 = jsonObject.get("TV112").toString();
			String tv115 = null;
			if(jsonObject.has("TV115"))
				tv115 = jsonObject.get("TV115").toString();
			JSONArray fo100s = jsonObject.getJSONArray("FO100S");
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < fo100s.length(); i++)
				list.add(Integer.parseInt(fo100s.getString(i)));
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForFunc(t110pmgService.createT110COM2(fo100, pt110, ft110, tv111, tv112, tv115, list));
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * danh sach COM1 truc thuoc COM
	 * @param FO100 int user login
	 * @param FT100 int id channel COM
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListT110COM1")
	@Produces("application/json")
	public String getListT110COM1(@QueryParam("FO100") int fo100, @QueryParam("FT100") int ft100, @QueryParam("FO100E") int fo100e, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForProc(t110pmgService.getListT110COM1(fo100, ft100, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * danh sach COM2 truc thuoc COM1
	 * @param FO100 int user login
	 * @param FT110 int id COM1
	 * @param OFFSET int
	 * @param LIMIT int
	 * @return
	 */
	@GET
	@Path("getListT110COM2")
	@Produces("application/json")
	public String getListT110COM2(@QueryParam("FO100") int fo100, @QueryParam("FT110") int ft110, @QueryParam("OFFSET") int pnOffset, @QueryParam("LIMIT") int pnLimit) {
		try {
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForProc(t110pmgService.getListT110COM2(fo100, ft110, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * user theo doi COM1
	 * @param FO100 int
	 * @param FT110	int
	 * @return 0: error, > 0: success
	 */
	@POST
	@Path("registerLoyaltyCustomerCOM1")
	@Produces("application/json")
	public String registerLoyaltyCustomerCOM1(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int ft110 = Integer.parseInt(jsonObject.get("FT110").toString());
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForFunc(t110pmgService.registerLoyaltyCustomerCOM1(fo100, ft110));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * user theo doi COM2
	 * @param FO100 int
	 * @param FT110	int
	 * @return -3: chua tham gia COM1, -2: da tham gia kenh truoc do, -1: sai ma so xac nhan, 0: error, > 0: success
	 */
	@POST
	@Path("registerLoyaltyCustomerCOM2")
	@Produces("application/json")
	public String registerLoyaltyCustomerCOM2(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int ft110 = Integer.parseInt(jsonObject.get("FT110").toString());
			String regisCode = jsonObject.get("REGIS_CODE").toString();
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForFunc(t110pmgService.registerLoyaltyCustomerCOM2(fo100, ft110, regisCode));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * nguoi phu trach xem thong tin COM2
	 * @param FO100 int user login
	 * @param FT110 int is COM2
	 * @return
	 */
	@GET
	@Path("getT110Info")
	@Produces("application/json")
	public String getT110Info(@QueryParam("FO100") int fo100, @QueryParam("PT110") int pt110) {
		try {
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForProc(t110pmgService.getT110Info(fo100, pt110));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * danh sach channel truc thuoc COM EDU user co quyen tuong tac
	 * @param FO100 int user login
	 * @param FT100 int id channel COM
	 * @return
	 */
	@GET
	@Path("getListT110")
	@Produces("application/json")
	public String getListT110(@QueryParam("FO100") int fo100, @QueryParam("FT100") int ft100) {
		try {
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForProc(t110pmgService.getListT110(fo100, ft100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * lay channel COM gan user nhat
	 * @param FO100 int user login
	 * @param LAT double
	 * @param LONG double
	 * @param FT100 int id channel COM
	 * @return
	 */
	@GET
	@Path("getNearestCOM1")
	@Produces("application/json")
	public String getNearestCOM1(@QueryParam("FO100") int fo100, @QueryParam("LATITUDE") double pnLa, @QueryParam("LONGITUDE") double pnLong, @QueryParam("FT100") int ft100) {
		try {
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForProc(t110pmgService.getNearestCOM1(fo100, pnLa, pnLong, ft100));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * danh sach user da tham gia COM2
	 * @param FO100 int
	 * @param FT110 int 
	 * @param TV119 String 
	 * @param OFFSET int 
	 * @param LIMIT int 
	 * @return
	 */
	@GET
	@Path("listOfTabT110Users")
	@Produces("application/json")
	public String listOfTabT110Users(@QueryParam("FO100") int fo100, @QueryParam("FT110") int ft110, @QueryParam("TV119") String tv119, @QueryParam("OFFSET") int offset, @QueryParam("LIMIT") int limit) {
		try {
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForProc(t110pmgService.listOfTabT110Users(fo100, ft110, tv119, offset, limit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param PT110	int
	 * @return  */
	@POST
	@Path("removeT110")
	@Produces("application/json")
	public String removeT110(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt110 = Integer.parseInt(jsonObject.get("PT110").toString());
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			return QbRestUtils.convertToJsonStringForFunc(templateService.removeRealDocument(fo100, T110PMG.class, new QbCriteria(QbMongoFiledsName.ID, pt110), new QbCriteria(QbMongoFiledsName.FO100, fo100)));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * owner or admin update avatar
	 * @param FO100 int
	 * @param PT110	int
	 * @param TV112	int
	 * @return  
	 * */
	@POST
	@Path("updateTv112")
	@Produces("application/json")
	public String updateTv112(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt110 = Integer.parseInt(jsonObject.get("PT110").toString());
			String tv112 = jsonObject.get("TV112").toString();
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForFunc(t110pmgService.updateTv112(fo100, pt110, tv112));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * owner or admin update cover image
	 * @param FO100 int
	 * @param PT110	int
	 * @param TV120	int
	 * @return  
	 * */
	@POST
	@Path("updateTv120")
	@Produces("application/json")
	public String updateTv120(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pt110 = Integer.parseInt(jsonObject.get("PT110").toString());
			String tv120 = jsonObject.get("TV120").toString();
			T110PMGService t110pmgService = (T110PMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_T110P);
			return QbRestUtils.convertToJsonStringForFunc(t110pmgService.updateTv120(fo100, pt110, tv120));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * user nhap ma dang ky hien thi COM2 truoc khi dang ky
	 * @param FO100 int
	 * @param FT110 int id COM1
	 * @param REGIS_CODE String 
	 * @return
	 */
	@GET
	@Path("getCOM2ByRegisCode")
	@Produces("application/json")
	public String getCOM2ByRegisCode(@QueryParam("FO100") int fo100, @QueryParam("FT110") int ft110, @QueryParam("REGIS_CODE") String regisCode) {
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			T110PMG t110pmg = templateService.findDocument(fo100, T110PMG.class, new QbCriteria("FT110", ft110), new QbCriteria("TV113", regisCode));
			if(t110pmg == null)
				t110pmg = templateService.findDocument(fo100, T110PMG.class, new QbCriteria("FT110", ft110), new QbCriteria("TV114", regisCode));
			return QbRestUtils.convertToJsonStringForProc(t110pmg);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
