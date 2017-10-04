package com.ohhay.bean.designer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.criteria.CustomerConfirmE150Criteria;
import com.ohhay.core.entities.E150D;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.core.mongo.service.E150MGService;
import com.ohhay.web.lego.service.CustomerService;

/**
 * @author ThoaiNH create Feb 25, 2016
 */
@Controller
@Scope("prototype")
public class CustomerBean {
	@Autowired
	private TemplateService templateService;
	private static Logger log = Logger.getLogger(CustomerBean.class);
	@Autowired
	private M900MGService m900mgService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private E150MGService e150mgService;
	/**
	 * @param pvSearch
	 * @param offset
	 * @param limit
	 * @return Tunt comment controller loadListDes 17/11/2016
	 */
	// @RequestMapping(value = "/customerBean.loadListDes", method =
	// RequestMethod.GET)
	// public @ResponseBody JacksonResponse loadListDes(@ModelAttribute("webID")
	// int webID, @ModelAttribute("pvSearch") String pvSearch,
	// @ModelAttribute("offset") int offset, @ModelAttribute("limit") int
	// limit){
	// JacksonResponse jsonResponse = new JacksonResponse();
	// jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
	// Q100 q100 = AuthenticationHelper.getUserLogin();
	// if(q100 != null)
	// {
	// log.info("---ohayListTabO100D:"+q100.getPo100()+","+String.valueOf(webID)+","+pvSearch+","+offset+","+limit+","+q100.getQv101());
	// List<O100> listDes = customerService.ohayListTabO100D(q100.getPo100(),
	// String.valueOf(webID), pvSearch, offset, limit, q100.getQv101());
	// jsonResponse.setResult(listDes);
	// }
	// else
	// jsonResponse.setResult(null);
	// return jsonResponse;
	// }

	/**
	 * @param webID
	 * @param pvSearch
	 * @param offset
	 * @param limit
	 * @return Tunt create 17/11/2016
	 */
	@RequestMapping(value = "/customerBean.loadListDes", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListDes(@ModelAttribute("webID") int webID,
			@ModelAttribute("pvSearch") String pvSearch, @ModelAttribute("offset") int offset,
			@ModelAttribute("limit") int limit) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			List<M900MG> listDes = m900mgService.listOfTabDesigner(q100.getPo100(), webID, pvSearch, offset, limit);
			jsonResponse.setResult(listDes);
		} else
			jsonResponse.setResult(null);
		return jsonResponse;
	}

	/**
	 * @param fo100d
	 * @param webID
	 * @param boxIDPattern
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "/customerBean.addDes", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addDes(@ModelAttribute("fo100d") int fo100d,
			@ModelAttribute("webID") long webID, @ModelAttribute("boxIDPattern") String boxIDPattern,
			@ModelAttribute("note") String note) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			log.info("---shareForDes:" + q100.getPo100() + "," + fo100d + "," + webID + "," + boxIDPattern + "," + note
					+ "," + q100.getQv101());
			int kq = customerService.shareForDes(q100.getPo100(), fo100d, webID, boxIDPattern, note, q100.getQv101());
			jsonResponse.setResult(kq);
		} else
			jsonResponse.setResult(null);
		return jsonResponse;
	}

	/**
	 * @param fe400
	 * @return
	 */
	@RequestMapping(value = "/customerBean.loadListDesOfWeb", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadListDesOfWeb(@ModelAttribute("fe400") int fe400) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			log.info("---listOfTabE150C:" + q100.getPo100() + "," + String.valueOf(fe400) + "," + q100.getQv101());
			List<E150D> listDes = customerService.listOfTabE150C(q100.getPo100(), String.valueOf(fe400),
					q100.getQv101());
			jsonResponse.setResult(listDes);
		} else
			jsonResponse.setResult(null);
		return jsonResponse;
	}

	/**
	 * @param fe400
	 * @param fe150
	 * @return changed by tunt 22/11/2016
	 */
	@RequestMapping(value = "/customerBean.removeDesFromWeb", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse removeDesFromWeb(@ModelAttribute("fe150") int fe150) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			log.info("---stornoTabE150:" + fe150);
			// int kq = customerService.stornoTabE150(fe150, q100.getQv101());
			int kq = e150mgService.storNoTab(q100.getPo100(), fe150);
			jsonResponse.setResult(kq);
		} else
			jsonResponse.setResult(null);
		return jsonResponse;
	}

	/**
	 * @param designerFinishE150Criteria
	 * @return
	 */
	@RequestMapping(value = "/customerBean.confirmBackup", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse confirmBackup(
			@ModelAttribute("designerFinishE150Criteria") CustomerConfirmE150Criteria customerConfirmE150Criteria) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			int kq = customerService.confirmTabE150(q100.getPo100(), customerConfirmE150Criteria.getFo100Des(),
					customerConfirmE150Criteria.getPe150(), customerConfirmE150Criteria.getFe400C(), q100.getQv101());
			jsonResponse.setResult(kq);
		} else
			jsonResponse.setResult(null);
		return jsonResponse;
	}

	/**
	 * @param fo100Des
	 * @param fo100Cus
	 * @param fe400Des
	 * @param fe400Cus
	 * @param fe150
	 * @return
	 */
	@RequestMapping(value = "/customerBean.applySiteOfDes", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse applySiteOfDes(@ModelAttribute("fo100Des") int fo100Des,
			@ModelAttribute("fe400Des") int fe400Des,
			@ModelAttribute("fe400Cus") int fe400Cus, @ModelAttribute("fe150") int fe150) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			jsonResponse.setResult(customerService.applySiteOfDes(q100.getPo100(), fo100Des, fe400Cus, fe400Des, fe150));
		} else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	
	@RequestMapping(value = "/customerBean.rejectSiteOfDes", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse rejectSiteOfDes(@ModelAttribute("fe150") int fe150, @ModelAttribute("ev161") String ev161){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			jsonResponse.setResult(customerService.rejectSiteOfDes(q100.getPo100(), fe150, ev161));
		} else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
}
