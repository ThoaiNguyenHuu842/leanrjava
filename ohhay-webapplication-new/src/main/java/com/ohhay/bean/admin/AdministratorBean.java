package com.ohhay.bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.MongoDBManager;
import com.ohhay.base.mysql.MySQLManager;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author ThoaiVt 19/02/2016
 */
@Controller
public class AdministratorBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(AdministratorBean.class);

	/**
	 * @return
	 */
	@RequestMapping(value = {"/administrator","/administrator/p{id}"}, method = RequestMethod.GET)
	public ModelAndView administratorHome(ModelMap model) {
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null && (("ADMIN").equalsIgnoreCase(q100.getM900mg().getMv922()) || ("TESTER").equalsIgnoreCase(q100.getM900mg().getMv922()))) {
			log.info("Info account : " + q100.getPo100());
			model.addAttribute("mapMongoInstance", MongoDBManager.mapMongoInstance);
			model.addAttribute("mapFO100TopicDB", MongoDBManager.mapFO100TopicDB);
			model.addAttribute("mapFO100OhhayDB", MongoDBManager.mapFO100OhhayDB);
			model.addAttribute("mapMySQLInstance", MySQLManager.mapMySQLInstance);
			model.addAttribute("mapFO100DB", MySQLManager.mapFO100DB);
			model.addAttribute("mapMODB", MySQLManager.mapMODB);
			return new ModelAndView(OhhayPagesName.EVO_PAGE_ADMINISTRATOR, "userLogin", q100);
		}
		return new ModelAndView(OhhayPagesName.OHHAY_PAGE_NOT_FOUND);
	}

	/**
	 * @param request
	 * @param model
	 * @param httpResponse
	 * @return
	 */
	@RequestMapping("/conection-manager")
	public String conectionManager(HttpServletRequest request, ModelMap model, HttpServletResponse httpResponse) {
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null && q100.getM900mg().getMv922().equals(ApplicationConstant.SYSTEM_ROLE_ADMIN)) {
			model.addAttribute("mapMongoInstance", MongoDBManager.mapMongoInstance);
			model.addAttribute("mapFO100TopicDB", MongoDBManager.mapFO100TopicDB);
			model.addAttribute("mapFO100OhhayDB", MongoDBManager.mapFO100OhhayDB);
			model.addAttribute("mapMySQLInstance", MySQLManager.mapMySQLInstance);
			model.addAttribute("mapFO100DB", MySQLManager.mapFO100DB);
			model.addAttribute("mapMODB", MySQLManager.mapMODB);
			return OhhayPagesName.OHHAY_WEB_ADMIN;
		} else
			return OhhayPagesName.OHHAY_PAGE_404;
	}

	/**
	 * @param type
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/admin.resetConnection", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse resetConnection(@ModelAttribute("type") int type, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		} else {
			Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
			if (q100 != null && q100.getM900mg().getMv922().equals(ApplicationConstant.SYSTEM_ROLE_ADMIN)) {
				log.info("--clear map type:" + type);
				switch (type) {
				case 1:
					MongoDBManager.mapFO100OhhayDB.clear();
					break;
				case 2:
					MongoDBManager.mapFO100TopicDB.clear();
					break;
				case 3:
					MongoDBManager.mapMongoInstance.clear();
					break;
				case 4:
					MySQLManager.mapFO100DB.clear();
					break;
				case 5:
					MySQLManager.mapMODB.clear();
					break;
				case 6:
					MySQLManager.mapMySQLInstance.clear();
					break;
				default:
					break;
				}
			}
			jsonResponse.setResult(1);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		}
		return jsonResponse;
	}
}
