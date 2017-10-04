package com.oohhay.jersey;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.entities.other.R000PMG;
import com.sun.jersey.api.container.ContainerException;
import com.sun.jersey.core.util.ReaderWriter;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 * Jersey HTTP Basic Auth filter
 * @author ThoaiNH, TuNT 
 * create May 14, 2017
 */
public class AuthFilter implements ContainerRequestFilter {
	private static Logger log = Logger.getLogger(AuthFilter.class);
	@Context
	private HttpServletRequest sr;
	@Context
	private UriInfo info;

	/**
	 * Apply the filter : check input request, validate or not with user auth
	 * @param containerReques The request from Tomcat server
	 */
	@Override
	public ContainerRequest filter(ContainerRequest containerRequest)
			throws WebApplicationException {
		log.info("--fillter web service");
		/*
		 * 1) get info
		 */
		final String method = containerRequest.getMethod();
		final R000PMG r000pmg = new R000PMG();
		r000pmg.setMethod(method);
		final String ip = sr.getRemoteAddr();
		r000pmg.setIp(ip);
		final URI url = containerRequest.getAbsolutePath();
		r000pmg.setUrl(url.toString());
		int apiVersion = 0;
		if (method.equals("POST")) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = containerRequest.getEntityInputStream();
			final StringBuilder b = new StringBuilder();
			try {
				ReaderWriter.writeTo(in, out);
				byte[] requestEntity = out.toByteArray();
				if (requestEntity.length > 0)
					b.append(new String(requestEntity));
				JSONObject jsonP = new JSONObject(b.toString());
				r000pmg.setPrams(jsonP.toString());
				if (jsonP.has("FO100"))
					r000pmg.setFo100(jsonP.getInt("FO100"));
				else
					r000pmg.setFo100(0);
				if (jsonP.has("SRC"))
					r000pmg.setSrc(jsonP.getString("SRC"));
				if (jsonP.has("MODEL"))
					r000pmg.setModel(jsonP.getString("MODEL"));
				if (jsonP.has("OS"))
					r000pmg.setOs(jsonP.getString("OS"));
				if (jsonP.has("VERSION"))
				{
					try {
						apiVersion = Integer.parseInt(jsonP.getString("VERSION"));
					} catch (NumberFormatException e) {
						// TODO: handle exception
					}
				}
				containerRequest.setEntityInputStream(new ByteArrayInputStream(
						requestEntity));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		else {
			final URI urlFull = containerRequest.getRequestUri();
			String[] arr = urlFull.toString().split("[?]");
			if (arr.length > 1) {
				String a = arr[1];
				String[] array = a.split("[&]");
				for (int i = 0; i < array.length; i++) {
					String[] aa = array[i].split("[=]");
					if ("FO100".equals(aa[0])) {
						try {
							r000pmg.setFo100(Integer.parseInt(aa[1]));
						} catch (NumberFormatException e) {
							// TODO: handle exception
						}
					}
					if ("SRC".equals(aa[0])) {
						r000pmg.setSrc(aa[1]);
					}
					if ("MODEL".equals(aa[0])) {
						r000pmg.setModel(aa[1]);
					}
					if ("OS".equals(aa[0])) {
						r000pmg.setOs(aa[1]);
					}
					if ("VERSION".equals(aa[0])) {
						try {
							apiVersion = Integer.parseInt(aa[1]);
						} catch (NumberFormatException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
				r000pmg.setPrams(a);
			}
		}
		

		// GET, POST, PUT, DELETE, ... String method =
		String path = containerRequest.getPath(true);

		// We do allow wadl to be retrieve
		if (method.equals("GET") && (path.equals("application.wadl")
				|| path.equals("application.wadl/xsd0.xsd"))) {
			return containerRequest;
		}
		
		/*
		 * 2) verify authentication 
		 * unauthenticated for first version
		 */
		if(apiVersion > 0){
			// Get the authentification passed in HTTP headers parameters
			String auth = containerRequest.getHeaderValue("authorization");
	
			// If the user does not have the right (does not provide any HTTP Basic Auth)
			if (auth == null)
				throw new WebApplicationException(Status.UNAUTHORIZED);
			String[] lap = BasicAuth.decode(auth);
	
			// lap : loginAndPassword String[] lap = BasicAuth.decode(auth);
			log.info("webserice username + pass:" + lap[0] + "," + lap[1]); // If
			
			// login or password fail
			if (lap == null || lap.length != 2)
				throw new WebApplicationException(Status.UNAUTHORIZED);
			if (!lap[0].equals("piepmeQueenbJSC") || !lap[1].equals("dA9mkdLYvgPQ3Y7hftXoFGqkAglDcZB1jdiJ22"))
				throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		/*
		 * 3) save log
		 */
		r000pmg.setTs(new Date());
		r000pmg.setVersion(apiVersion);
		Thread thread = new Thread() {
			public void run() {
				// insert tracking
				TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
				templateService.saveDocument(ApplicationConstant.FO100_SUPER_ADMIN, r000pmg, QbMongoCollectionsName.R000);

			}
		};
		thread.start();
		return containerRequest;
	}
}
