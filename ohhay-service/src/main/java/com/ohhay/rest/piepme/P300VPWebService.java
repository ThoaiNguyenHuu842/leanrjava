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

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.nestedentities.PieperImg;
import com.ohhay.piepme.mongo.service.P300VPMGService;

/**
 * tao voucher tu tuc
 * mobile.bonevo.net/service/p300VPWebService/
 * @author ThoaiNH
 * create May 6, 2017
 */
@Path("p300VPWebService")
public class P300VPWebService {
	private static Logger log = Logger.getLogger(P300VPWebService.class);
	/**
	 *  
	 * @param FO100 int
	 * @param PP300 int 
	 * @param PV301 String
	 * @param PN303 int 
	 * @param PV304 String
	 * @param PV304THUMB String
	 * @param PV305 String
	 * @param PN306 int
	 * @param PN309 float
	 * @param PV314 String
	 * @param OTAGS (optional) String
	 * @param PIEPER_IMGS (optional) JSON array i.g: [{"URL":"""DES":""RATIO:1.3}] 
	 * @param VD303 String dd-MM-yyyy ngay bat dau
	 * @param VD304 String dd-MM-yyyy ngay ket thuc
	 * @param VN309 int so luong (99 = nhieu lan)
	 * @param VV308 String 
	 * @return
	 */
	@POST
	@Path("createPieper")
	@Produces("application/json")
	public String createPieper(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			String pv301 = jsonObject.get("PV301").toString();
			int pn303 = Integer.parseInt(jsonObject.get("PN303").toString());
			int pn306 = Integer.parseInt(jsonObject.get("PN306").toString());
			String pv304 = jsonObject.get("PV304").toString();
			String pv304Thumb = jsonObject.get("PV304THUMB").toString();
			String pv305 = jsonObject.get("PV305").toString();
			String pv314 = jsonObject.get("PV314").toString();
			float pn309 = Float.parseFloat(jsonObject.getString("PN309"));
			String otags = null;
			if(jsonObject.has("OTAGS"))
				otags = jsonObject.get("OTAGS").toString();
			List<Integer> listFO100R = new ArrayList<Integer>();
			List<PieperImg> pieperImgs = new ArrayList<PieperImg>();
			if(jsonObject.has("PIEPER_IMGS")){
				JSONArray jsonArray = jsonObject.getJSONArray("PIEPER_IMGS");
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject img = jsonArray.getJSONObject(i);
					PieperImg pieperImg = new PieperImg(img.getString("URL"),img.getString("DES"),img.getDouble("RATIO"));
					pieperImgs.add(pieperImg);
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String vd303Str = jsonObject.getString("VD303");
			Date vd303 = sdf.parse(vd303Str);
			String vd304Str = jsonObject.getString("VD304");
			Date vd304 = sdf.parse(vd304Str);
			int vn309 = jsonObject.getInt("VN309");
			String vv308 = jsonObject.getString("VV308");
			P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
			return QbRestUtils.convertToJsonStringForFunc(p300Service.createPieper(fo100, pp300, pv301, pn303, pv304, pv304Thumb, pv305, pn306, pn309, pv314, otags, listFO100R, pieperImgs, vd303, vd304, vv308, vn309));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100
	 * @param SEARCH
	 * @param SORT
	 * @param OFFSET
	 * @param LIMIT
	 * @return
	 */
	@GET
	@Path("getListPieperOwner")
	@Produces("application/json")
	public String getListPieperOwner(@QueryParam("FO100") int fo100, @QueryParam("PVSEARCH") String pvSearch, @QueryParam("SORT") int sort,@QueryParam("OFFSET") int pnOffset,@QueryParam("LIMIT") int pnLimit){
		try {
			P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
			return QbRestUtils.convertToJsonStringForProc(
					p300Service.getListPieperOwner(fo100, AESUtils.decrypt(pvSearch), sort, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param 
	 * FO100 int user login
	 * FO100B int enterprise
	 * VOUCHER_CODE String
	 * PVLOGIN
	 * @return
	 */
	@POST
	@Path("usedItTabV330")
	@Produces("application/json")
	public String usedItTabV330(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int fo100b = Integer.parseInt(jsonObject.get("FO100B").toString());
			String voucherCode = jsonObject.get("VOUCHER_CODE").toString();
			String pvLogin = jsonObject.get("PVLOGIN").toString();
			P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
			return QbRestUtils.convertToJsonStringForFunc(p300Service.useVoucher(fo100, fo100b, voucherCode, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * 
	 * @param FO100, int
	 * @param PP300, int 
	 * @param PVLOGIN, String
	 * @return
	 */
	@POST
	@Path("storNoTabP300V")
	@Produces("application/json")
	public String storNoTabP300V(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			String pvLogin = jsonObject.get("PVLOGIN").toString(); 
			P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
			return QbRestUtils.convertToJsonStringForFunc(p300Service.storNoTabP300V(fo100, pp300, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PP300 int
	 * @return
	 */
	@GET
	@Path("getPieperDetail")
	@Produces("application/json")
	public String getPieperDetail(@QueryParam("FO100") int fo100, @QueryParam("PP300") int pp300){
		try {
			P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
			return QbRestUtils.convertToJsonStringForProc(p300Service.getPieperDetail(fo100, pp300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * FO100 int
	 * PP300 int
	 * VV307 String
	 * PVLOGIN String
	 * @return
	 */
	@POST
	@Path("deActTabV300O")
	@Produces("application/json")
	public String deActTabV300O(String postString) {
		try {
			log.info(postString);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("FO100").toString());
			int pp300 = Integer.parseInt(jsonObject.get("PP300").toString());
			String vv307 = jsonObject.get("VV307").toString(); 
			String pvLogin = jsonObject.get("PVLOGIN").toString(); 
			P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
			return QbRestUtils.convertToJsonStringForFunc(p300Service.deActTabV300O(fo100, pp300, vv307, pvLogin));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	/**
	 * @param FO100 int
	 * @param PV300 int (optional)
	 * @return
	 */
	@GET
	@Path("getTotalVoucher")
	@Produces("application/json")
	public String getTotalVoucher(@QueryParam("FO100") int fo100, @QueryParam("PV300") int pv300){
		try {
			P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
			return QbRestUtils.convertToJsonStringForFunc(p300Service.getTotalVoucher(fo100, pv300));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
	
	/**
	 * @param FO100 int
	 * @param PV300 int
	 * @param OFFSET String 
	 * @param LIMIT String
	 * @return
	 */
	@GET
	@Path("listAllVoucher")
	@Produces("application/json")
	public String listAllVoucher(@QueryParam("FO100") int fo100, @QueryParam("PV300") int pv300, @QueryParam("OFFSET") int pnOffset,@QueryParam("LIMIT") int pnLimit){
		try {
			P300VPMGService p300Service = (P300VPMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_P300VP);
			return QbRestUtils.convertToJsonStringForProc(
					p300Service.listAllVoucher(fo100, pv300, pnOffset, pnLimit));
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
