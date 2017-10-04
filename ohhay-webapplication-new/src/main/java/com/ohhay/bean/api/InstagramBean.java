package com.ohhay.bean.api;

import java.util.Iterator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuth20ServiceImpl;
import com.github.scribejava.core.oauth.OAuthService;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
@Controller
@Scope("prototype")
public class InstagramBean {
	/**
	 * login Instagram
	 * @return
	 */
	@RequestMapping(value = "/instagramBean.loginInstagram", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse loginInstagram(HttpServletRequest httpRequest){
		JacksonResponse jsonResponse = new JacksonResponse();
		OAuth20ServiceImpl service = this.getInstagramService(httpRequest);
		String authorizationUrl = service.getAuthorizationUrl(null);
		jsonResponse.setResult(authorizationUrl);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
	
	@RequestMapping(value = "/instagramBean.serviceAuthorized",method = RequestMethod.GET)
	public String serviceAuthorized(HttpServletRequest httpRequest,
			@RequestParam(required=true, value="code") String code){
		Verifier verifier = new Verifier(code);
		OAuth20ServiceImpl service = this.getInstagramService(httpRequest);
		Token accessToken = service.getAccessToken(null, verifier);
		ApplicationHelper.setSession(ApplicationConstant.INSTAGRAM_ACCESS_TOKEN, accessToken);
		return OhhayPagesName.EVO_PAGE_SOCIAL_MESSAGE;
	}
	
	@RequestMapping(value = "/instagramBean.getUserInstagram", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse getUserInstagram(HttpServletRequest httpRequest){
		JacksonResponse jsonResponse = new JacksonResponse();
		OAuth20ServiceImpl instaService = this.getInstagramService(httpRequest);
		OAuthRequest request = this.buildRequest(instaService, "users/self/", null);
		if(request != null){
			Response response = request.send();
			try {
				JSONObject jsonObject = new JSONObject(response.getBody());
				if (response.getCode() == 200) {
					String userID = jsonObject.getJSONObject("data").getString("id");
					ApplicationHelper.setSession(ApplicationConstant.INSTAGRAM_USER_ID, userID);
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
	
	@RequestMapping(value = "/instagramBean.checkUserLogin",method = RequestMethod.POST)
	public @ResponseBody JacksonResponse checkUserLogin(){
		JacksonResponse jsonResponse = new JacksonResponse();
		Token accessToken = (Token)ApplicationHelper.getSession(ApplicationConstant.INSTAGRAM_ACCESS_TOKEN);
		String userID = (String) ApplicationHelper.getSession(ApplicationConstant.INSTAGRAM_USER_ID);
		if(accessToken != null && userID != null){
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/instagramBean.logoutInstagram",method = RequestMethod.POST)
	public @ResponseBody JacksonResponse logoutInstagram(){
		JacksonResponse jsonResponse = new JacksonResponse();
		ApplicationHelper.removeSession(ApplicationConstant.INSTAGRAM_ACCESS_TOKEN);
		ApplicationHelper.removeSession(ApplicationConstant.INSTAGRAM_USER_ID);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
	
	@RequestMapping(value = "/instagramBean.callAPI",method = RequestMethod.POST)
	public @ResponseBody JacksonResponse callAPI(
			HttpServletRequest httpRequest,
			@RequestParam(required=false, value="paramArray") String paramArray,
			@RequestParam(required=true, value="endpoint") String endpoint){
		JacksonResponse jsonResponse = new JacksonResponse();
		OAuth20ServiceImpl instaService = this.getInstagramService(httpRequest);
		JSONObject jsonObject = null;
		if (paramArray != null){
			try {
				jsonObject = new JSONObject(paramArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		OAuthRequest request = this.buildRequest(instaService,endpoint, jsonObject);
		if (request != null) {
			Response response = request.send();
			jsonResponse.setResult(response.getBody());
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		}
		return jsonResponse;
	}
	
	private OAuthRequest buildRequest(OAuth20ServiceImpl instaService, String endpoint,JSONObject jsonObject){
		Token accessToken = (Token)ApplicationHelper.getSession(ApplicationConstant.INSTAGRAM_ACCESS_TOKEN);
		if(accessToken != null){
			String url = ApplicationConstant.INSTAGRAM_RESOURCE_URL + endpoint;
			OAuthRequest request = new OAuthRequest(Verb.GET, url, instaService);
			String message = endpoint;
			message += "|access_token="+accessToken.getToken();
			if (jsonObject != null) {
				Iterator<?> keys = jsonObject.keys();
				while (keys.hasNext()) {
					try {
						String key = (String) keys.next();
						String value = jsonObject.getString(key);
						message += "|"+key+"="+value;
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			String signature = this.generateSignature(message);
//			System.out.println("url = "+url);
//			System.out.println("accessToken = "+accessToken.getToken());
//			System.out.println("sig = "+signature);
			request.addQuerystringParameter("sig", signature);
			instaService.signRequest(accessToken, request);
			return request;
		}
		return null;
	}
	
	private OAuth20ServiceImpl getInstagramService(HttpServletRequest httpRequest){
		String baseUrl = String.format("%s://%s:%d",httpRequest.getScheme(),  httpRequest.getServerName(), httpRequest.getServerPort());
        baseUrl += httpRequest.getServerName().equals("localhost") ? "/ohhay-webapplication-new" : "";
		return (OAuth20ServiceImpl) new ServiceBuilder().provider(InstagramApi20.class).apiKey(ApplicationConstant.INSTAGRAM_APP_KEY).apiSecret(ApplicationConstant.INSTAGRAM_APP_SECRET).callback(baseUrl+"/instagramBean.serviceAuthorized").grantType(OAuthConstants.AUTHORIZATION_CODE).build();
	}
	
	private String generateSignature(String message){
		try {
		     Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		     SecretKeySpec secret_key = new SecretKeySpec(ApplicationConstant.INSTAGRAM_APP_SECRET.getBytes(), "HmacSHA256");
		     sha256_HMAC.init(secret_key);
		     String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
		     return hash;
		    }
		    catch (Exception e){
		     System.out.println("Error");
		    }
		return null;
	}
}
