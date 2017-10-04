package com.ohhay.bean.web.requestmapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.RequestParamRule;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.OhhayViewer;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.entities.mongo.other.M200MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.M150MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.other.entities.mongo.domain.topic.U800MG;
import com.ohhay.other.lucene.KeySearch;
import com.ohhay.other.lucene.M150Luncene;
import com.ohhay.other.lucene.OhhayScoreDoc;
import com.ohhay.other.lucene.QbLuceneBase;
import com.ohhay.other.lucene.QueryFactory;
import com.ohhay.other.mongo.service.T150MGService;
import com.ohhay.web.lego.entities.mongo.topic.ET400MG;
import com.ohhay.web.lego.entities.mongo.web.E950MG;
/**
 * @author ThoaiNH
 * create Jan 7, 2016
 * service for topic new
 */
/**
 * @author ThoaiNH
 * create Jun 27, 2016
 */
@Controller
@Scope("prototype")
public class TopicBean extends WebBaseRequestMapping {
	private static Logger log = Logger.getLogger(TopicBean.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_T150SMG)
	T150MGService t150mg;
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/etopic")
	public String mapEditTopic(@CookieValue(value = SessionConstant.USER_LOGIN, required = false) String userLoginCookieInfo, 
							HttpServletRequest request, ModelMap model, HttpServletResponse httpResponse, 
							@RequestParam(required = false, value = RequestParamRule.PARAM_WEB_LANGUAGE) String webLanguageCode) {
		/*
		 * 1) setup HttpServletResponse
		 */
		 setUpHttpRespone(httpResponse);
		/*
		 * 2) find web by phone number or search path data (email)
		 */
		String phoneNumber = null;
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null){
			phoneNumber = q100.getM900mg().getMv907();
			M900MG m900mg = loadM900(phoneNumber, userLoginCookieInfo);
			if(m900mg != null){
				prepareModel(model, m900mg, q100, webLanguageCode);
				return OhhayPagesName.EVO_PAGE_WEBTOPIC;
			}
			else
				return ApplicationHelper.getRedirectString("https://oohhay.com/login?re=https://topic.oohhay.com/");
			
		}else{
			return ApplicationHelper.getRedirectString("https://oohhay.com/login?re=https://topic.oohhay.com/");
		}	
	}
	/**
	 * @param request
	 * @param model
	 * @param pathOne
	 * @return
	 */
	@RequestMapping(value = {"et{pathOne:.*}/{pathTwo:.*}"}, method = RequestMethod.GET)
	public String mapWebTopicPublic(HttpServletRequest request,ModelMap model,@PathVariable(value = "pathOne") String pathOne) {
		try {
			int topicId = Integer.parseInt(pathOne);
			OhhayViewer ohhayViewer = ApplicationHelper.getInfoFromRequest(request);
			ET400MG e400mg = templateMGService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, ET400MG.class, new QbCriteria(QbMongoFiledsName.FM150, topicId));
			if(e400mg != null){
				templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
				M150MG m150mg = templateMGService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, topicId, M150MG.class);
				E950MG e950mg = new E950MG();
				e950mg.setEv951(ApplicationHelper.decodeTopicString(m150mg.getMv152()));
				e950mg.setEv953(ApplicationHelper.decodeTopicString(m150mg.getMv152()));
				model.addAttribute("e950mg", e950mg);
				model.addAttribute("e400mg", e400mg);
				model.addAttribute("device", ohhayViewer.getDevice());
				return OhhayPagesName.EVO_PAGE_WEB_PUBLISH;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return OhhayPagesName.OHHAY_PAGE_404;
	}
	
	/**
	 * @param request
	 * @param model
	 * @param fo100
	 * @return
	 */
	@RequestMapping(value = {"lastTopic"}, method = RequestMethod.GET)
	public String mapWebLastTopic(HttpServletRequest request,@RequestParam(required = false, value = "fo100") String fo100) {
		try {
			M150MGService m150mgService = (M150MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M150MG);
			int fo100Real = Integer.parseInt(AESUtils.decrypt(fo100));
			JSONArray array =  m150mgService.listOfTabM150(fo100Real, fo100Real, 0, 1, "vi");
			if(array.length() > 0){
				JSONObject jsonObject = array.getJSONObject(0);
				String url = ApplicationConstant.TOPIC_CONTEXT_PATH+"topic-"+jsonObject.getInt("_id")+"-bonevo/"+ApplicationHelper.removeAccent(ApplicationHelper.decodeTopicString(jsonObject.getString("MV151"))).toLowerCase().trim().replace(" ", "-").replace("%", "")+"?share=tp";
				return ApplicationHelper.getRedirectString(url);
			}
			return OhhayPagesName.OHHAY_PAGE_404;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return OhhayPagesName.OHHAY_PAGE_404;
	}
	/*
	 * service get info of request for topic new
	 * create 28/05/2016
	 */
	@RequestMapping(value = "/topic.getInfo", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getInfo(@ModelAttribute("pathOne") String pathOne, @RequestParam("fo100") String fo100String) {
		log.info("fo100String:"+fo100String);
		JacksonResponse jsonResponse = new JacksonResponse();
		M900MG m900Login = null;
		int fo100Login = Integer.parseInt(fo100String);
		m900Login = templateMGService.findDocumentById(fo100Login, fo100Login, M900MG.class);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		/*
		 * 2) find web by phone number or search path data (email)
		 */
		M900MG m900mg = templateMGService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, M900MG.class, new QbCriteria(QbMongoFiledsName.MV903, AESUtils.encrypt(pathOne)));
		if(m900mg == null){
			/*
			 * path one is topic diemtin
			 */
			templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			M200MG m200MG = templateMGService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, M200MG.class, new QbCriteria(QbMongoFiledsName.MV203, pathOne));
			if(m200MG != null){
				log.info("--m900Login:"+m900Login);
				jsonResponse.setResult2(m900Login);
				jsonResponse.setResult(m200MG);
				templateMGService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				jsonResponse.setResultString(OhhayPagesName.OHHAY_PAGE_DIEMTIN);
				return jsonResponse;
			}
			jsonResponse.setResultString(OhhayPagesName.OHHAY_PAGE_404);
			return jsonResponse;
		}
		else
		{
			jsonResponse.setResult(m900mg);
			jsonResponse.setResult2(m900Login);
			jsonResponse.setResultString(OhhayPagesName.OHHAY_PAGE_TOPIC);
		}
		return jsonResponse;
	}
	/*
	 * service get info of owner topic
	 * create 01/06/2016
	 */
	@RequestMapping(value = "/topic.getTopicInfo", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getTopicInfo(@RequestParam("fm150") String fm150String, @RequestParam("fo100") String fo100String) {
		JacksonResponse jsonResponse = new JacksonResponse();
		M900MG m900Login = null;
		try {
			int fm150 = Integer.parseInt(fm150String);
			int fm100Login = Integer.parseInt(fo100String);
			templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			M150MG m150mg = templateMGService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, fm150, M150MG.class);
			if(m150mg != null)
			{
				templateMGService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
				jsonResponse.setResult(templateMGService.findDocumentById(m150mg.getFo100(), m150mg.getFo100(), M900MG.class));
				jsonResponse.setResult2(templateMGService.findDocumentById(fm100Login, fm100Login, M900MG.class));
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				return jsonResponse;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonResponse;
	}
	/**
	 * search topic by otag
	 * @param textSearch
	 * @param fo100
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/searchBean.searchM150", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse searchM150(@ModelAttribute("textSearch") String textSearch,@ModelAttribute("fo100") int fo100, @ModelAttribute("fo100Login") int fo100Login, BindingResult result) {
		textSearch = AESUtils.decrypt(textSearch);
		log.info("---ation search m150 textSearch:" + textSearch);
		log.info("---fo100:"+fo100);
		log.info("sài gòn");
		JacksonResponse jsonResponse = new JacksonResponse();
		if (result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		}
		else {
			// set time search and number result
			long beginTime =  System.currentTimeMillis();
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			KeySearch keySearch = new KeySearch(textSearch, 1);
			M150Luncene searcher = new M150Luncene(ApplicationConstant.INDEXPATH_M150);
			List<OhhayScoreDoc> listScoreDocResult = null;
			if(fo100 == 1222)
				listScoreDocResult = searcher.search(QueryFactory.createM150SearchAGOD(keySearch), 10000, QbLuceneBase.MODE_SEARCH_WEB);
			else if(fo100 == 1154)
				listScoreDocResult = searcher.search(QueryFactory.createM150SearchTieuDiem(keySearch), 10000, QbLuceneBase.MODE_SEARCH_WEB);
			else 
				listScoreDocResult = searcher.search(QueryFactory.createM150SearchOtags(keySearch,fo100), 10000, QbLuceneBase.MODE_SEARCH_WEB);
			List<M150MG> list = searcher.getResult(0, 10000, listScoreDocResult);
			List<Integer> listResult = new ArrayList<Integer>();
			for(M150MG m150mg: list)
			{
				log.info("---id m150:"+m150mg.getId());
				listResult.add(m150mg.getId());
			}
			long endTime = System.currentTimeMillis();
			double time = (double) (endTime - beginTime) / 1000;
			log.info("--Found " + listScoreDocResult.size() + " results in "
					+ time + "s");
			jsonResponse.setResult(listResult);
			jsonResponse.setResult2(templateMGService.findDocumentById(fo100Login, fo100Login, M900MG.class));
			jsonResponse.setResultString(String.valueOf(time));
		}
		return jsonResponse;
	}
	
	/**
	 * @param textSearch
	 * @param fm200
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/searchBean.searchM150DiemTin", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse searchM150DiemTin(@ModelAttribute("textSearch") String textSearch,@ModelAttribute("fm200") int fm200, @ModelAttribute("fo100Login") int fo100Login, BindingResult result) {
		textSearch = AESUtils.decrypt(textSearch);
		log.info("---ation search m150 textSearch:" + textSearch);
		log.info("---fm200:"+fm200);
		JacksonResponse jsonResponse = new JacksonResponse();
		if (result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(result.getAllErrors());
		}
		else {
			// set time search and number result
			long beginTime =  System.currentTimeMillis();
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			KeySearch keySearch = new KeySearch(textSearch, 1);
			M150Luncene searcher = new M150Luncene(ApplicationConstant.INDEXPATH_M150);
			List<OhhayScoreDoc> listScoreDocResult = searcher.search(QueryFactory.createM150SearchDiemtin(keySearch,fm200), 10000, QbLuceneBase.MODE_SEARCH_WEB);
			List<M150MG> list = searcher.getResult(0, 10000, listScoreDocResult);
			List<Integer> listResult = new ArrayList<Integer>();
			for(M150MG m150mg: list)
			{
				log.info("---id m150:"+m150mg.getId());
				listResult.add(m150mg.getId());
			}
			long endTime = System.currentTimeMillis();
			double time = (double) (endTime - beginTime) / 1000;
			log.info("--Found " + listScoreDocResult.size() + " results in "
					+ time + "s");
			jsonResponse.setResult(listResult);
			jsonResponse.setResult2(templateMGService.findDocumentById(fo100Login, fo100Login, M900MG.class));
			jsonResponse.setResultString(String.valueOf(time));
		}
		return jsonResponse;
	}

	/**
	 * create27/06/2016 get M900 owner domain verified
	 * @param requestUrl
	 * @param fo100String
	 * @return
	 */
	@RequestMapping(value = "/topic.getInfoTopicDomain", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getInfoTopicDomain(@ModelAttribute("requestUrl") String requestUrl, @RequestParam("fo100") String fo100String) {
		log.info("fo100String:"+fo100String);
		JacksonResponse jsonResponse = new JacksonResponse();
		M900MG m900Login = null;
		int fo100Login = Integer.parseInt(fo100String);
		m900Login = templateMGService.findDocumentById(fo100Login, fo100Login, M900MG.class);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		/*
		 * 2) find web by phone number or search path data (email)
		 */
		templateMGService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
		U800MG u800mg = templateMGService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, U800MG.class, new QbCriteria("UV801", requestUrl), new QbCriteria("UN803", 1));
		if(u800mg != null)
		{
			templateMGService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
			jsonResponse.setResult(templateMGService.findDocumentById(u800mg.getFo100(), u800mg.getFo100(), M900MG.class));
			jsonResponse.setResult2(m900Login);
			jsonResponse.setResultString(OhhayPagesName.OHHAY_PAGE_TOPIC);
		}
		return jsonResponse;
	}
}
