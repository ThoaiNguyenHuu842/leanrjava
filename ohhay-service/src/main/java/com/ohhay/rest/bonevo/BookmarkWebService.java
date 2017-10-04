package com.ohhay.rest.bonevo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.O180;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.R900MGService;
import com.ohhay.core.mysql.service.O180Service;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 *
 */
@Path("bookmarkWebService")
public class BookmarkWebService {
	private static Logger log = Logger.getLogger(BookmarkWebService.class);

	/*
	 * bookmark
	 */
	@POST
	@Path("bookmark")
	@Produces("application/json")
	public String bookmark(@Context HttpServletRequest request, String postString) {
		try {
			R900MGService r900mgService = (R900MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R900MG);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			String qv101 = jsonObject.get("qv101").toString();
			int fo100b = Integer.parseInt(jsonObject.get("fo100b").toString());
			String rv921 = jsonObject.get("rv921").toString();
			String rv922 = jsonObject.get("rv922").toString();
			log.info("---insertTabR900Bookmark:" + fo100 + "," + fo100b + ","
					+ rv921 + "," + rv922);
			int kq = r900mgService.insertTabR900Bookmark(fo100, fo100b, rv921, rv922);
			if (kq > 0) {
				// sync to mysql
				O180Service o180Service = (O180Service) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_O180);
				o180Service
						.insertTabO180(0, null, null, null, fo100b, fo100, qv101);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * delete bookmark
	 */
	@POST
	@Path("delete")
	@Produces("application/json")
	public String delete(@Context HttpServletRequest request, String postString) {
		try {
			R900MGService r900mgService = (R900MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R900MG);
			JSONObject jsonObject = new JSONObject(postString);
			int fo100 = Integer.parseInt(jsonObject.get("fo100").toString());
			int fo100b = Integer.parseInt(jsonObject.get("fo100b").toString());
			String qv101 = jsonObject.get("qv101").toString();
			log.info("---stornoTabR900Bookmark:" + fo100 + "," + fo100b);
			int kq = r900mgService.stornoTabR900Bookmark(fo100, fo100b);
			if (kq > 0) {
				// sync to mysql
				O180Service o180Service = (O180Service) ApplicationHelper
						.findBean(SpringBeanNames.SERVICE_NAME_O180);
				o180Service.stornotabo180(fo100, fo100b, qv101);
			}
			return QbRestUtils.convertToJsonStringForFunc(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * load Bookmark
	 */
	@GET
	@Path("loadBookmark")
	@Produces("application/json")
	public String loadBookmark(@QueryParam("fo100") int fo100, @QueryParam("limit") int limit, @QueryParam("offset") int offset) {
		try {
			R900MGService r900mgService = (R900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900MG);
			List<M900MG> listM900mgs = r900mgService.loadBookmark(fo100, 10000);
			return QbRestUtils.convertToJsonStringForProc(listM900mgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}

	/*
	 * load People Bookmark Me
	 */
	@GET
	@Path("loadPeopleBookmarkMe")
	@Produces("application/json")
	public String loadPeopleBookmarkMe(@QueryParam("fo100") int fo100, @QueryParam("limit") int limit, @QueryParam("offset") int offset) {
		try {
			R900MGService r900mgService = (R900MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R900MG);
			List<O180> list = r900mgService.findPeopleBookmarkMe(fo100, limit);
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
