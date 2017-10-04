package com.ohhay.rest.bonevo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.authentication.AuthenticationRightInfo;


/**
 * @author ThoaiNH
 * date create: 09/07/2015
 */
@Path("q170WebService")
public class Q170WebService {
	private static Logger log = Logger.getLogger(Q170WebService.class);
	/**
	 * @param fo100
	 * @return
	 */
	@GET
	@Path("listOfTabQ170")
	@Produces("application/json")
	public String ahayCombtabn750(@QueryParam("fo100") int fo100, @QueryParam("ov101") String ov101) {
		try {
			List<AuthenticationRightInfo> list = new ArrayList<AuthenticationRightInfo>();
			list.add(AuthenticationHelper.loadUserRights(fo100, ov101));
			return QbRestUtils.convertToJsonStringForProc(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}
	}
}
