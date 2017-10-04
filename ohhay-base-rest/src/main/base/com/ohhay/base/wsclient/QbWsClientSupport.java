package com.ohhay.base.wsclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author ThoaiNH
 * create 12/12/2014
 * modify webservice parameter
 */
public class QbWsClientSupport {
	private static Logger log = Logger.getLogger(QbWsClientSupport.class);

	protected JSONObject callGet(String url) {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			String output = response.getEntity(String.class);
			JSONObject jsonObject = new JSONObject(output);
			return jsonObject;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	protected JSONObject callPost(String url,
			List<QbWsClientParam> listClientParams) throws Exception {
		String charset = "UTF-8";
		// create paramerters
		StringBuilder stringBuilderQuery = new StringBuilder();
		for (QbWsClientParam clientParam : listClientParams) {
			stringBuilderQuery.append(clientParam.getKey()
					+ "="
					+ URLEncoder.encode(clientParam.getValue().toString(),
							charset) + "&");
		}
		String query = stringBuilderQuery.substring(0,
				stringBuilderQuery.length() - 1);
		log.info("---WS CLIENT PARAM:" + query);
		// call post
		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST.
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=" + charset);
		try (OutputStream output = connection.getOutputStream()) {
			output.write(query.getBytes(charset));
		}
		InputStream response = connection.getInputStream();
		String contentType = connection.getHeaderField("Content-Type");
		for (String param : contentType.replace(" ", "").split(";")) {
			if (param.startsWith("charset=")) {
				charset = param.split("=", 2)[1];
				break;
			}
		}
		String output = null;
		if (charset != null) {
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(response, charset))) {
				for (String line; (line = reader.readLine()) != null;) {
					output = line;
				}
			}
		} else {
			// It's likely binary content, use InputStream/OutputStream.
		}
		log.info("--OUTPUT:" + output);
		JSONObject jsonObject = new JSONObject(output);
		return jsonObject;
	}

	protected JSONObject callPostJson(String url,
			List<QbWsClientParam> listClientParams) throws Exception {
		String charset = "UTF-8";
		// create paramerters
		StringBuilder stringBuilderQuery = new StringBuilder("{");
		
		for (QbWsClientParam clientParam : listClientParams) {
			log.info("---data "+clientParam.getKey()+"="+clientParam.getValue());
			stringBuilderQuery.append("\""+clientParam.getKey()+"\":\""+clientParam.getValue().toString()+"\",");
		}
		
		String query = stringBuilderQuery.substring(0,stringBuilderQuery.length() - 1)+"}";
		log.info("---WS CLIENT PARAM:" + query);
		// call post
		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST.
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=" + charset);
		try (OutputStream output = connection.getOutputStream()) {
			output.write(query.getBytes(charset));
		}
		InputStream response = connection.getInputStream();
		String contentType = connection.getHeaderField("Content-Type");
		for (String param : contentType.replace(" ", "").split(";")) {
			if (param.startsWith("charset=")) {
				charset = param.split("=", 2)[1];
				break;
			}
		}
		String output = null;
		if (charset != null) {
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(response, charset))) {
				for (String line; (line = reader.readLine()) != null;) {
					output = line;
				}
			}
		} else {
			// It's likely binary content, use InputStream/OutputStream.
		}
		log.info("--OUTPUT:" + output);
		JSONObject jsonObject = new JSONObject(output);
		return jsonObject;
	}

}
