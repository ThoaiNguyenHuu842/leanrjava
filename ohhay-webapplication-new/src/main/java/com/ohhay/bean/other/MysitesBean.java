package com.ohhay.bean.other;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.bean.web.requestmapping.WebBaseRequestMapping;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RequestParamRule;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.other.entities.mongo.domain.U920MG;
import com.ohhay.other.mysql.service.O200Service;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.base.web.OhhayLegoWebBase;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.service.WebLegoService;
import com.oohhay.web.lego.utils.EvoWebType;

/**
 * @author ThoaiNH
 * create Nov 20, 2015
 * home page manage website
 */
@Controller
@Scope("prototype")
public class MysitesBean  extends WebBaseRequestMapping{
	private static Logger log = Logger.getLogger(MysitesBean.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
	private WebLegoService webLegoService;
	@Autowired
	private E400MGService e400mgService;
	/**
	 * map all one path url request
	 */
	@RequestMapping(value = { "mysites"}, method = RequestMethod.GET)
	public String mapOnePathRequest(Model model, @RequestParam(required = false, value = RequestParamRule.PARAM_LANGUAGE) String languageCode) {
		log.info("---current language:"+LocaleContextHolder.getLocale().getLanguage());
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			//redirect to evo-templates neu lan dau truy cap mysite va chua tao web lan nao
			if(q100.getM900mg().getM930mg().getNewbieFirstAccessMysite() == 0){
				E400MG e400mg = templateService.findDocument(q100.getPo100(), E400MG.class, new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()));
				if(e400mg == null)
					return ApplicationHelper.getRedirectString("/evo-templates");
			}
			log.info(q100.getM900mg().getM930mg());
			M900MG m900mg = templateService.findDocumentById(q100.getPo100(), q100.getPo100(), M900MG.class);
			if(languageCode == null || !languageCode.equals(m900mg.getLanguage()))
				return ApplicationHelper.getRedirectString("/mysites?language="+m900mg.getLanguage());
			model.addAttribute("userLogin", q100);
			return OhhayPagesName.EVO_PAGE_MYSITES;
		}
		return ApplicationHelper.getRedirectString("/login");
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/mysitesBean.load", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse load(@ModelAttribute("pvSearch") String pvSearch, @ModelAttribute("offset") int offset, @ModelAttribute("limit") int limit){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			List<E400MG> listE400s = e400mgService.getListMysite(q100.getPo100(),pvSearch,offset,limit);
			jsonResponse.setResult(listE400s);
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/mysitesBean.create", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse create() {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			String returnMess = webLegoService.checkRightCreateWeb(q100.getAuthenticationRightInfo(), q100.getPo100());
			if(returnMess.equals(ApplicationConstant.RE_VAILD_RIGHT)){
				jsonResponse.setResult(webLegoService.createWebEvo(q100.getPo100(), q100.getM900mg().getHv101(), false, 0));
			}
			else
			{
				jsonResponse.setResult(-2);
				jsonResponse.setMesg(returnMess);
			}
			
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/mysitesBean.remove", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse remove(@ModelAttribute("pe400") int pe400) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
			jsonResponse.setResult(webLegoService.remove(q100.getPo100(), pe400, EvoWebType.EVOTYPE_WEB));
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/mysitesBean.duplicate", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse duplicate(@ModelAttribute("pe400") int pe400) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			String returnMess = webLegoService.checkRightCreateWeb(q100.getAuthenticationRightInfo(), q100.getPo100());
			if(returnMess.equals(ApplicationConstant.RE_VAILD_RIGHT)){
				jsonResponse.setResult(webLegoService.duplicate(q100.getPo100(), pe400));
			}
			else
			{
				jsonResponse.setResult(-2);
				jsonResponse.setMesg(returnMess);
			}
			
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * date create: 23/02/2015
	 * check right max website allow creating
	 * @return
	 */
	@RequestMapping(value = "/mysitesBean.checkRightCreateWeb", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse checkRightCreateWeb() {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		String returnCode = ApplicationConstant.RE_MUST_UPGRADE;
		if (q100 != null) 
			 returnCode = webLegoService.checkRightCreateWeb(q100.getAuthenticationRightInfo(), q100.getPo100());
		jsonResponse.setResult(returnCode);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		return jsonResponse;
	}
	/**
	 * ThoaiNH 02/08/2016
	 * create responsive site
	 * @return
	 */
	@RequestMapping(value = "/mysitesBean.createResponsiveSite", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse createResponsiveSite() {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			String returnMess = webLegoService.checkRightCreateWeb(q100.getAuthenticationRightInfo(), q100.getPo100());
			if(returnMess.equals(ApplicationConstant.RE_VAILD_RIGHT)){
				OhhayLegoWebBase  ohhayLegoWebBase = webLegoService.createWebEvo(q100.getPo100(), q100.getM900mg().getHv101(), false, 0);
				templateService.updateOneField(q100.getPo100(), E400MG.class,(int)ohhayLegoWebBase.getId(), "SRC", "RE", null);
				jsonResponse.setResult(ohhayLegoWebBase);
			}
			else
			{
				jsonResponse.setResult(-2);
				jsonResponse.setMesg(returnMess);
			}
			
		}
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @author ThoaiNH
	 * create 05/12/2016 
	 * @return
	 */
	@RequestMapping(value = "/mysitesBean.loadGraph", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadGraph(@ModelAttribute("fo100") int fo100){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
			jsonResponse.setResult(e400mgService.listOfTabE400Graph(fo100));
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
}
