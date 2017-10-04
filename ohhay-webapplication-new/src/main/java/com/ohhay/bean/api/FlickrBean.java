package com.ohhay.bean.api;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuthService;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author ThienND
 * created Dec 28, 2015
 */
@Controller
@Scope("prototype")
public class FlickrBean {
	/**
	 * login Flickr
	 * @return
	 */
	@RequestMapping(value = "/flickrBean.loginFlickr",method = RequestMethod.POST)
	public @ResponseBody JacksonResponse loginFlickr(HttpServletRequest httpRequest){
		JacksonResponse jsonResponse = new JacksonResponse();
        OAuthService flickrService = this.getFlickrService(httpRequest);
        // Obtain the Request Token
        Token requestToken = flickrService.getRequestToken();
        System.out.println(requestToken);
        String authorizationUrl = flickrService.getAuthorizationUrl(requestToken);
        ApplicationHelper.setSession(ApplicationConstant.FLICKR_REQUEST_TOKEN, requestToken);
        String result = authorizationUrl + "&perms=read";
		jsonResponse.setResult(result);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
	
	@RequestMapping(value = "/flickrBean.serviceAuthorized",method = RequestMethod.GET)
	public String serviceAuthorized(HttpServletRequest httpRequest,
			@RequestParam(required=true, value="oauth_token") String oauth_token,
			@RequestParam(required=true, value="oauth_verifier") String oauth_verifier){
		Verifier verifier = new Verifier(oauth_verifier);
		Token requestToken = (Token)ApplicationHelper.getSession(ApplicationConstant.FLICKR_REQUEST_TOKEN);
		OAuthService flickrService = this.getFlickrService(httpRequest);
		Token accessToken = flickrService.getAccessToken(requestToken, verifier);
		ApplicationHelper.setSession(ApplicationConstant.FLICKR_ACCESS_TOKEN, accessToken);
		System.out.println(accessToken.getToken());
		return OhhayPagesName.EVO_PAGE_SOCIAL_MESSAGE;
	}
	
	@RequestMapping(value = "/flickrBean.getUserFlickr",method = RequestMethod.POST)
	public @ResponseBody JacksonResponse getUserFlickr(HttpServletRequest httpRequest){
		JacksonResponse jsonResponse = new JacksonResponse();
		OAuthService flickrService = this.getFlickrService(httpRequest);
		OAuthRequest request = this.buildRequest(flickrService,"flickr.test.login",null,false);
		if (request != null){
			Response response = request.send();
			try {
				JSONObject jsonObject = new JSONObject(response.getBody());
				if (jsonObject.get("stat").equals("ok")) {
					String userID = jsonObject.getJSONObject("user").getString("id");
					ApplicationHelper.setSession(ApplicationConstant.FLICKR_USER_ID, userID);
					System.out.println(userID);
					jsonResponse.setResult(response.getBody());
					jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				} else {
					jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/flickrBean.callAPI",method = RequestMethod.POST)
	public @ResponseBody JacksonResponse callAPI(
			HttpServletRequest httpRequest,
			@RequestParam(required=false, value="paramArray") String paramArray,
			@RequestParam(required=true, value="method") String method,
			@RequestParam(required=true, value="withUser") Boolean withUser){
		System.out.println(method);
		System.out.println(paramArray);
		System.out.println(withUser);
		JacksonResponse jsonResponse = new JacksonResponse();
		OAuthService flickrService = this.getFlickrService(httpRequest);
		JSONObject jsonObject = null;
		if (paramArray != null){
			try {
				jsonObject = new JSONObject(paramArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		OAuthRequest request = this.buildRequest(flickrService,method, jsonObject, withUser);
		if (request != null) {
			Response response = request.send();
			jsonResponse.setResult(response.getBody());
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/flickrBean.checkUserLogin",method = RequestMethod.POST)
	public @ResponseBody JacksonResponse checkUserLogin(){
		JacksonResponse jsonResponse = new JacksonResponse();
		Token accessToken = (Token)ApplicationHelper.getSession(ApplicationConstant.FLICKR_ACCESS_TOKEN);
		String userID = (String) ApplicationHelper.getSession(ApplicationConstant.FLICKR_USER_ID);
		if(accessToken != null && userID != null){
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/flickrBean.logoutFlickr",method = RequestMethod.POST)
	public @ResponseBody JacksonResponse logoutFlickr(HttpServletRequest httpRequest){
		JacksonResponse jsonResponse = new JacksonResponse();
		ApplicationHelper.removeSession(ApplicationConstant.FLICKR_REQUEST_TOKEN);
		ApplicationHelper.removeSession(ApplicationConstant.FLICKR_ACCESS_TOKEN);
		ApplicationHelper.removeSession(ApplicationConstant.FLICKR_USER_ID);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
	
	private OAuthRequest buildRequest(OAuthService flickrService,String method,JSONObject jsonObject,Boolean withUser){
		Token accessToken = (Token)ApplicationHelper.getSession(ApplicationConstant.FLICKR_ACCESS_TOKEN);
		if (accessToken != null){
			OAuthRequest request = new OAuthRequest(Verb.GET, ApplicationConstant.FLICKR_RESOURCE_URL, flickrService);
			if (withUser) {
				String userID = (String) ApplicationHelper.getSession(ApplicationConstant.FLICKR_USER_ID);
				request.addQuerystringParameter("user_id", userID);
			}
			request.addQuerystringParameter("method", method);
			request.addQuerystringParameter("nojsoncallback", "1");
			request.addQuerystringParameter("format", "json");
			if (jsonObject != null) {
				Iterator<?> keys = jsonObject.keys();
				while (keys.hasNext()) {
					try {
						String key = (String) keys.next();
						String value = jsonObject.getString(key);
						request.addQuerystringParameter(key, value);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			flickrService.signRequest(accessToken, request);
			return request;
		}
		return null;
	}
	
	private OAuthService getFlickrService(HttpServletRequest httpRequest){
		String baseUrl = String.format("%s://%s:%d",httpRequest.getScheme(),  httpRequest.getServerName(), httpRequest.getServerPort());
        baseUrl += httpRequest.getServerName().equals("localhost") ? "/ohhay-webapplication-new" : "";
		return new ServiceBuilder().provider(FlickrApi.class).apiKey(ApplicationConstant.FLICKR_APP_KEY).apiSecret(ApplicationConstant.FLICKR_APP_SECRET).callback(baseUrl+"/flickrBean.serviceAuthorized").
                build();
	}
}
