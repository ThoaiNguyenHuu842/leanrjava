package com.ohhay.bean.other;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.OhhayViewer;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.entities.mongo.web.E950MG;
import com.ohhay.web.lego.service.WebLegoService;

/**
 * @author ThienND
 * created Jan 30, 2016
 * create e card
 */
@Controller
@Scope("prototype")
public class CreateCardBean {
	private static Logger log = Logger.getLogger(CreateCardBean.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	@Autowired
	private TemplateService templateService;
	/**
	 * @return
	 */
	@RequestMapping(value={"chucmungnammoi"}, method=RequestMethod.GET)
	public String createCard(Model model){
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			model.addAttribute("userLogin", q100);
			return OhhayPagesName.EVO_PAGE_CREATE_CARD;
		}
		return ApplicationHelper.getRedirectString("/chucxuan-evo");
	}
	/**
	 * @param request
	 * @param model
	 * @param pathOne
	 * @param editMode
	 * @return
	 */
	@RequestMapping(value = {"chucxuan-{pathOne:.*}"}, method = RequestMethod.GET)
	public String mapOnePathRequestChucXuan(HttpServletRequest request,ModelMap model,@PathVariable(value = "pathOne") String pathOne) {
		try {
			E400MG e400mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class, new QbCriteria(QbMongoFiledsName.EV405, pathOne));
			if(e400mg != null){
				Q100 q100 = AuthenticationHelper.getUserLogin();
				model.addAttribute("pe400", e400mg.getId());
				OhhayViewer ohhayViewer = ApplicationHelper.getInfoFromRequest(request);
				E950MG e950mg = templateService.findDocument(e400mg.getFo100(), E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
				model.addAttribute("e950mg", e950mg);
				model.addAttribute("e400mg", e400mg);
				model.addAttribute("userLogin", q100);
				model.addAttribute("fo100Encrypted", AESUtils.encrypt(String.valueOf(e400mg.getFo100())));
				model.addAttribute("device", ohhayViewer.getDevice());
				return OhhayPagesName.EVO_PAGE_WEB_CARD;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return OhhayPagesName.OHHAY_PAGE_404;
	}

	/**
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value = "/createCardBean.create", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse create(@ModelAttribute("templateId") int templateId, @ModelAttribute("cardContent") String cardContent, @ModelAttribute("imgBase64") String imgBase64
			, @ModelAttribute("imgBase64MB") String imgBase64MB) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			jsonResponse.setResult(webLegoService.createEVOCard(q100.getPo100(), templateId, cardContent, imgBase64, q100.getM900mg().getNv100Decrypt(), q100.getM900mg().getRegion()));
		}
		else
			jsonResponse.setResult(-1);
		return jsonResponse;
	}
}
