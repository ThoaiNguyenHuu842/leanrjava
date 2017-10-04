package com.ohhay.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.bean.web.requestmapping.WebBaseRequestMapping;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.OhhayViewer;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.R950MGService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.mysql.service.R900Service;

/**
 * @author ThoaiNH
 * create Jun 5, 2016
 */
@Controller
@Scope("prototype")
public class WebTrackingBean extends WebBaseRequestMapping{
	private static Logger log = Logger.getLogger(WebTrackingBean.class);
	@Autowired
	private R950MGService r950mgService;
	/**
	 * @param fo100AES
	 * @param objectName
	 * @param webId
	 * @param extend
	 * @param languageCode
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/webTrackingBean.tracking", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse trackByObject(@ModelAttribute("fo100") String fo100AES,
													   @ModelAttribute("objectName") String objectName, 
													   @ModelAttribute("webId") int webId,
													   @ModelAttribute("languageCode") String languageCode, 
													   HttpServletRequest request, 
													   BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		} else {
			int fo100 = Integer.parseInt(AESUtils.decrypt(fo100AES));
			int kq = 0;
			int fo100t = 0;
			String ipAddress = ApplicationHelper.getIpAddress(request);
			Q100 q100 = AuthenticationHelper.getUserLogin();
			if (q100 != null)
				fo100t = q100.getPo100();
			R900Service r900Service = (R900Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900);
			String webType = QbMongoCollectionsName.E400;
			OhhayViewer ohhayViewer = getOhhayViewer(request);
			String requestUrl = ApplicationHelper.getDomainFromRequest(request);
			log.info("---insertTabR900V1:" + fo100 + "," + ipAddress + "," + objectName + "," + languageCode + ","
										   + webType + "," + ohhayViewer.getDevice() + "," + ohhayViewer.getBrowser() + ","
										   + ohhayViewer.getOs() + "," + webId + "," + fo100t + "," + ApplicationConstant.PVLOGIN_ANONYMOUS);
			r900Service.insertTabR900V1(fo100, ipAddress, objectName,
										languageCode, webType, ohhayViewer.getDevice(),
										ohhayViewer.getBrowser(), ohhayViewer.getOs(), webId, fo100t,
										ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("---insertTabR900a: " + fo100 + " , " + ipAddress + " , " + request.getSession().getId() + " ,"
											+ objectName + "," + webId + " , " + languageCode + " ,"
											+ ApplicationConstant.DIGISTORE_IPN_PASSPHRASE + "," + ohhayViewer.getBrowser() + ","
											+ ohhayViewer.getDevice() + "," + ohhayViewer.getOs() + ","
											+ ApplicationConstant.PVLOGIN_ANONYMOUS);
			kq = r900Service.insertTabR900a(fo100, ipAddress, request.getSession().getId(), objectName, webId,
											languageCode, ApplicationConstant.DIGISTORE_IPN_PASSPHRASE, ohhayViewer.getBrowser(),
											ohhayViewer.getDevice(), ohhayViewer.getOs(), ApplicationConstant.PVLOGIN_ANONYMOUS);
			r950mgService.insertTabR950(fo100, fo100t, webId, ipAddress, request.getSession().getId(),
										languageCode, ohhayViewer.getCountryCode(), ohhayViewer.getCountryName(),
										ohhayViewer.getDevice(), ohhayViewer.getBrowser(), objectName, ohhayViewer.getOs(), ohhayViewer.getRegionCode(),requestUrl);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jsonResponse.setResult(kq);
		}
		return jsonResponse;
	}

	/**
	 * @param fo100AES
	 * @param objectName
	 * @param webId
	 * @param extend
	 * @param languageCode
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/webTrackingBean.trackingViewer", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse trackByViewer(@ModelAttribute("fo100") String fo100AES,
													   @ModelAttribute("webId") int webId, 
													   @ModelAttribute("languageCode") String languageCode,
			HttpServletRequest request, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		} else {
			OhhayViewer ohhayViewer = getOhhayViewer(request);
			R900Service r900Service = (R900Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_R900);
			int fo100 = (fo100AES.equals("0") ? 0 : Integer.parseInt(AESUtils.decrypt(fo100AES)));
			int kq = 0;
			String ipAddress = ApplicationHelper.getIpAddress(request);
			String webType = QbMongoCollectionsName.E400;
			int fo100t = 0;
			Q100 q100 = AuthenticationHelper.getUserLogin();
			if (q100 != null)
				fo100t = q100.getPo100();
			String requestUrl = ApplicationHelper.getDomainFromRequest(request);
			log.info("---insertTabR900V1:"+fo100+","+ipAddress+","+null+","+languageCode+","+webType+","+ohhayViewer.getDevice()+","+
										   ohhayViewer.getBrowser()+","+ohhayViewer.getOs()+","+webId+","+fo100t+","+
										   ApplicationConstant.PVLOGIN_ANONYMOUS);
			r900Service.insertTabR900V1(fo100, ipAddress, null, languageCode, webType, ohhayViewer.getDevice(),
										ohhayViewer.getBrowser(), ohhayViewer.getOs(), webId, fo100t,
									    ApplicationConstant.PVLOGIN_ANONYMOUS);
			// tracking new r900a
			log.info("---insertTabR900a:"+fo100+","+ipAddress+","+request.getSession().getId()+","+null+","+webId+","+languageCode+","+
										 ApplicationConstant.DIGISTORE_IPN_PASSPHRASE+","+ohhayViewer.getBrowser()+","+ohhayViewer.getDevice()+","+
										 ohhayViewer.getOs()+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			kq = r900Service.insertTabR900a(fo100, ipAddress, request.getSession().getId(), null, webId, languageCode,
											ApplicationConstant.DIGISTORE_IPN_PASSPHRASE, ohhayViewer.getBrowser(), ohhayViewer.getDevice(),
											ohhayViewer.getOs(), ApplicationConstant.PVLOGIN_ANONYMOUS);
			r950mgService.insertTabR950(fo100, fo100t, webId, ipAddress, request.getSession().getId(),
									   languageCode, ohhayViewer.getCountryCode(), ohhayViewer.getCountryName(),
									   ohhayViewer.getDevice(), ohhayViewer.getBrowser(), null, ohhayViewer.getOs(), ohhayViewer.getRegionCode(),requestUrl);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jsonResponse.setResult(kq);
		}
		return jsonResponse;
	}
}
