package com.ohhay.bean.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.util.JSON;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.AccountEVORegisCriteria;
import com.ohhay.core.criteria.AccountRegisCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.oracle.dao.V500ORDao;
import com.ohhay.core.oracle.service.N100ORService;
import com.ohhay.core.oracle.service.V500ORService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.DateHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.service.CreateAccountService;

/**
 * @author ThoaiNH
 * create Mar 24, 2016
 */
@Controller
@Scope("prototype")
public class DigistoreBean {
	private static Logger log = Logger.getLogger(DigistoreBean.class);
	/**
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "/digistoreBean.receiveIPN", method = RequestMethod.POST)
	public @ResponseBody String receiveIPN(@RequestParam Map<String, String> parameters, HttpServletRequest httpServletRequest) {
		N100ORService n100orService = (N100ORService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100OR);
		String requestUrl = ApplicationHelper.getDomainFromRequest(httpServletRequest);
		log.info("---requestUrl:"+requestUrl);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dateFormatShort = new SimpleDateFormat("yyyy-MM-dd");
		log.info("-- receiveIPN --");
		log.info(JSON.serialize(parameters));
		//String signature = this.digistoreSignature(parameters);
		//log.info("signature1 = " + this.getPostedValue(parameters,"sha_sign"));
		//log.info("signature  = " + signature);
		String event = this.getPostedValue(parameters,"event");
		log.info("--- " + event + " ---");
		String api_mode = this.getPostedValue(parameters,"api_mode");
		Boolean is_test_mode = api_mode.equals("live");
		switch (event) {
		case "connection_test":
			return "OK";
		case "on_payment":
			try {
				/*
				 * 1) get info 
				 */
				String billing_type = getPostedValue(parameters,"billing_type");
				String email = getPostedValue(parameters,"email");
				String firstName = getPostedValue(parameters,"buyer_first_name");
				String lastName = getPostedValue(parameters,"buyer_last_name");
				java.util.Date orderDateTime = dateFormat.parse(getPostedValue(parameters,"order_date_time"));
				double transactionAmount = Double.parseDouble(getPostedValue(parameters,"transaction_amount"));
				//String productName = getPostedValue(parameters,"product_name");
				java.util.Date transactionDate = dateFormatShort.parse(getPostedValue(parameters,"transaction_date"));
				String transactionId = getPostedValue(parameters,"transaction_id");
				String productId = getPostedValue(parameters,"product_id");
				//String addressPhoneNo = getPostedValue(parameters,"address_phone_no");
				//String productLanguage = getPostedValue(parameters,"product_language");
				String addressZipcode = getPostedValue(parameters,"address_zipcode");
				String addressCountry = getPostedValue(parameters,"address_country");
				java.util.Date nextPaymentAt = dateFormatShort.parse(getPostedValue(parameters,"next_payment_at"));
				String invoiceUrl = getPostedValue(parameters,"invoice_url");
				String rebillingStopUrl = getPostedValue(parameters,"rebilling_stop_url");
				String receiptUrl = getPostedValue(parameters,"receipt_url");
				String renewUrl = getPostedValue(parameters,"renew_url");
				String requestRefundUrl = getPostedValue(parameters,"request_refund_url");
				String supportUrl = getPostedValue(parameters,"support_url");
				String becomeAffiliateUrl = getPostedValue(parameters,"become_affiliate_url");
				String paymentPlan = getPostedValue(parameters,"other_billing_intervals");//da thay doi
				String productLanguage  =  getPostedValue(parameters,"product_language");
				switch (billing_type) {
				case "single_payment":
					break;

				case "installment":
					break;

				case "subscription":
					break;
				}
				/*
				 * 2) check email
				 */
				String encryptedEmail = AESUtils.encrypt(email.trim());
				TemplateService templateMGService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
				M900MG m900mg = templateMGService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN,M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, encryptedEmail));
				if (m900mg != null) {
					/*
					 * 2.1) update if exists email
					 */
					log.info("---insertTabN100EVO:"+m900mg.getMv901()+","+
							m900mg.getMv902()+","+
							ApplicationHelper.removeAccent(m900mg.getMv901())+","+  
							ApplicationHelper.removeAccent(m900mg.getMv902())+","+
							null+","+
							m900mg.getMv903Decrypt()+","+ 
							m900mg.getMv907()+","+
							null+","+
							addressZipcode+","+ 
							addressCountry+","+
							DateHelper.toSQLDate(orderDateTime)+","+ 
							transactionAmount+","+
							addressCountry+","+ 
							null+","+
							productId+","+ 
							event+","+
							DateHelper.toSQLDate(transactionDate)+","+ 
							transactionId+","+
							paymentPlan+","+
							DateHelper.toSQLDate(nextPaymentAt)+","+ 
							invoiceUrl+","+
							rebillingStopUrl+","+
							receiptUrl+","+
							renewUrl+","+
							requestRefundUrl+","+
							supportUrl+","+
							becomeAffiliateUrl+","+
							m900mg.getId()+","+
							1+","+
							m900mg.getMv903Decrypt());
					n100orService.insertTabN100EVO(m900mg.getMv901(), 
							m900mg.getMv902(),
							ApplicationHelper.removeAccent(m900mg.getMv901()),  
							ApplicationHelper.removeAccent(m900mg.getMv902()),
							null,  
							m900mg.getMv903Decrypt(), 
							m900mg.getMv907(), 
							null, 
							addressZipcode, 
							addressCountry, 
							DateHelper.toSQLDate(orderDateTime), 
							transactionAmount, 
							addressCountry, 
							null,
							productId, 
							event, 
							DateHelper.toSQLDate(transactionDate), 
							transactionId, 
							paymentPlan,
							DateHelper.toSQLDate(nextPaymentAt),
							invoiceUrl,
							rebillingStopUrl,
							receiptUrl,
							renewUrl,
							requestRefundUrl,
							supportUrl,
							becomeAffiliateUrl,
							m900mg.getId(), 
							1, 
							m900mg.getMv903Decrypt());
				} else {
					/*
					 * 2.2) create new account for new email
					 */
					String password = ApplicationHelper.fastPasswordRandomizer(9);
					CreateAccountService createAccountService = (CreateAccountService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_CREATE_ACCOUNT);
					AccountEVORegisCriteria accountRegisCriteria = new AccountEVORegisCriteria();
					accountRegisCriteria.setRegisTypel(AccountEVORegisCriteria.REGISTYPE_EVO);
					accountRegisCriteria.setCountryCode("");
					accountRegisCriteria.setEmail(AESUtils.encrypt(email));
					accountRegisCriteria.setFd000(0);
					accountRegisCriteria.setfName(firstName);
					accountRegisCriteria.setlName(lastName);
					accountRegisCriteria.setGender(null);
					accountRegisCriteria.setJobName(null);
					accountRegisCriteria.setCountryCode(productLanguage);
					accountRegisCriteria.setLanguageCode(productLanguage);
					if(productLanguage.equals("de"))
						accountRegisCriteria.setLanguageName("Deutsch");
					else
						accountRegisCriteria.setLanguageName("EngLish");
					accountRegisCriteria.setPassWord(ApplicationHelper.convertToMD5(password));
					accountRegisCriteria.setRePassWord(ApplicationHelper.convertToMD5(password));
					accountRegisCriteria.setRegion("AS");
					accountRegisCriteria.setBuyerAddressCountry(addressCountry);
					accountRegisCriteria.setOrderDaytime(orderDateTime);
					accountRegisCriteria.setTransactionAmount(transactionAmount);
					accountRegisCriteria.setProductId(productId);
					accountRegisCriteria.setEven(event);
					accountRegisCriteria.setTransactionDate(transactionDate);
					accountRegisCriteria.setTransactionId(transactionId);
					accountRegisCriteria.setAddressZipcode(addressZipcode);
					accountRegisCriteria.setInvoiceUrl(invoiceUrl);
					accountRegisCriteria.setRebillingStopUrl(rebillingStopUrl);
					accountRegisCriteria.setReceiptUrl(receiptUrl);
					accountRegisCriteria.setRenewUrl(renewUrl);
					accountRegisCriteria.setRequestRefundUrl(requestRefundUrl);
					accountRegisCriteria.setSupportUrl(supportUrl);
					accountRegisCriteria.setBecomeAffiliateUrl(becomeAffiliateUrl);
					accountRegisCriteria.setPaymentPlan(paymentPlan);
					accountRegisCriteria.setPasswordForDigi(password);
					accountRegisCriteria.setNextPaymentDay(nextPaymentAt);
					createAccountService.createEVOAccount(accountRegisCriteria, null, LocaleContextHolder.getLocale().getLanguage());
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "OK";
		case "on_payment_missed":
			return "OK";
		case "on_refund":
		case "on_chargeback":
			return "OK";
		case "on_rebill_resumed":
			return "OK";
		case "on_rebill_cancelled":
			return "OK";
		case "on_affiliation":
			return "OK";
		default:
			// Unknown event
			return "OK";
		}
	}

	/**
	 * @param array
	 * @param key
	 * @return
	 */
	private String getPostedValue(Map<String,String>array,String key) {
		return array.get(key) != null ? array.get(key) : "";
	}
	
	private Object getPostedValue2(Map<String,String>array,String key) {
		return array.get(key);
	}

	/**
	 * @param array
	 * @return
	 */
	private String digistoreSignature(Map<String,String> array) {
		String signature = "";
		List<String> keys = new ArrayList<String>(array.keySet());
		keys.remove("sha_sign");
		Collections.sort(keys);
		for (String key : keys) {
			String value = StringEscapeUtils.unescapeHtml(array.get(key));
			if (value.length() == 0 || value.equals("false")) {
				continue;
			}
			signature += key + "=" + value + ApplicationConstant.DIGISTORE_IPN_PASSPHRASE;
		}
		signature = ApplicationHelper.convertToSHA512(signature).toUpperCase();
		return signature;
	}
	
	@RequestMapping(value = "/digistoreBean.callAPI", method = RequestMethod.POST)
	public @ResponseBody String callAPI(@RequestParam(required=true, value="method")String method, @RequestParam String params,HttpServletRequest httpServletRequest) {
		String apiKey = "";
		
		String apiURL = "https://www.digistore24.com/api/call/" + apiKey + "/json/" + method + "/?";
		log.info(apiURL);
		return "";
	}
	
	@RequestMapping(value = "/digistoreBean.createBuyUrl", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse createBuyUrl(@RequestParam String productId) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			// API KEY from Digistore24, must be Full Access
			
			// create digistore api url
			String apiURL = "https://www.digistore24.com/api/call/" + ApplicationConstant.DIGISTORE_API_KEY + "/json/createBuyUrl/?"+
					// digistore product id
					"product_id="+productId
					// user info
					+ "&buyer[first_name]="+q100.getM900mg().getMv901Decrypt()
					+ "&buyer[last_name]="+q100.getM900mg().getMv902Decrypt()
					+ "&buyer[email]="+q100.getM900mg().getMv903Decrypt()
					// cannot edit name and email
					+ "&buyer[readonly_keys]=email_and_name"
					// time until expire: 1 hour
					+ "&valid_until=1h";
			try {
				// read json response from api url
				URL url = new URL(apiURL);
				BufferedReader br;
				try {
					br = new BufferedReader(new InputStreamReader(url.openStream()));
					String strTemp = "";
					while (null != (strTemp = br.readLine())) {
						System.out.println(strTemp);
						try {
							// create json object from json response
							JSONObject json = new JSONObject(strTemp);
							String result = json.getString("result");
							if (result.equals("success")){
								JSONObject data = json.getJSONObject("data");
								// get buy url
								String buyURL = data.getString("url");
								System.out.println("buyURL: "+buyURL);
								jsonResponse.setResult(buyURL);
							}
						} catch (org.json.JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
}
