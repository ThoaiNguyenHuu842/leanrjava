package com.ohhay.bean.other;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayPagesName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.EvoAddLibCriteria;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.core.mongo.dao.E920MGDao;
import com.ohhay.web.lego.entities.mongo.web.E900MG;
import com.ohhay.web.lego.entities.mongo.web.E920MG;
import com.ohhay.web.lego.entities.mongo.web.E950MG;
import com.ohhay.web.lego.service.EvoLibraryService;

/**
 * @author ThoaiNH
 * create Jan 12, 2016
 */
@Controller
@Scope("prototype")
public class LibraryBean {
	@Autowired
	private TemplateService templateService;
	@Autowired
	private EvoLibraryService evoLibraryService;
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_E920MG)
	E920MGDao e920mgDao;
	/**
	 * @param pe920
	 * @param libType
	 * @param otags
	 * @param title
	 * @param editAble
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.addToLib", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addToLib(@ModelAttribute("evoAddLibCriteria") @Valid EvoAddLibCriteria evoAddLibCriteria, BindingResult result)  {
		JacksonResponse jsonResponse = new JacksonResponse();
		if (!result.hasErrors()) {
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			Q100 q100 = AuthenticationHelper.getUserLogin();
			if(q100 != null)
				jsonResponse.setResult(evoLibraryService.addToLib(q100.getPo100(), evoAddLibCriteria, q100.getM900mg().getRegion()));
			else
				jsonResponse.setResult(null);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		jsonResponse.setResult(result.getAllErrors());
		return jsonResponse;
	}
	/**
	 * @param pe920
	 * @param libType
	 * @param otags
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.addLibToWeb", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addLibToWeb(@ModelAttribute("webId") int webId, @ModelAttribute("pe920") int pe920)  {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
			jsonResponse.setResult(evoLibraryService.addLibToWeb(q100.getPo100(), pe920, webId));
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param pe920
	 * @param itemtype: ref(from other lib) or my
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.removeFromLib", method = RequestMethod.POST) 
	public @ResponseBody JacksonResponse removeFromLib(@ModelAttribute("pe920") int pe920, @ModelAttribute("itemtype") String itemtype)  {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
			jsonResponse.setResult(evoLibraryService.remove(q100.getPo100(), pe920, itemtype));
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.load", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse load(@ModelAttribute("libType") int libType, @ModelAttribute("textSearch") String textSearch,
			@ModelAttribute("offset") int offset, @ModelAttribute("limit") int limit){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
			jsonResponse.setResult(evoLibraryService.load(q100.getPo100(), libType, textSearch, offset, limit));
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.loadSectionData", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadSectionData(@ModelAttribute("pe920") int pe920){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		E920MG e920mg = templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, pe920, E920MG.class);
		if(e920mg != null && q100 != null){
			e920mgDao.incTotalAdd(pe920, q100.getPo100());
			List<E900MG> listE900mgs = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E900MG.class, new QbCriteria(QbMongoFiledsName.BOX_ID, e920mg.getId()));
			jsonResponse.setResult2(listE900mgs);
		}
		jsonResponse.setResult(e920mg);
		return jsonResponse;
	}
	/**
	 * @param request
	 * @param model
	 * @param pathOne
	 * @param boxId
	 * @return
	 */
	@RequestMapping(value = {"web{pathOne:.*}/{pathTwo:.*}"}, method = RequestMethod.GET)
	public String mapBoxView(HttpServletRequest request,ModelMap model,@PathVariable(value = "pathOne") String pathOne, @PathVariable(value = "pathTwo") String boxId) {
		try {
			int pe400 = Integer.parseInt(pathOne);
			E950MG e950mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, pe400));
			if(e950mg != null){
				Document document = Jsoup.parse(e950mg.getEv951());
				Elements boxs = document.select(".content-box:not([qb-box-id="+boxId+"])");
				for(Element box: boxs){
					box.remove();
				}
				model.addAttribute("html", document.html());
				return OhhayPagesName.EVO_PAGE_BOX_REVIEW;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return OhhayPagesName.OHHAY_PAGE_404;
	}
	/**
	 * @param pathOne
	 * @param boxId
	 * @return
	 */
	@RequestMapping(value = {"getsection{pathOne:.*}/{pathTwo:.*}"}, method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getsection(@PathVariable(value = "pathOne") String pathOne, @PathVariable(value = "pathTwo") String boxId) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		try {
			int pe400 = Integer.parseInt(pathOne);
			E950MG e950mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, pe400));
			if(e950mg != null){
				Document document = Jsoup.parse(e950mg.getEv951());
				Elements boxs = document.select(".content-box[qb-box-id="+boxId+"]");
				if(boxs.size() > 0)
					jsonResponse.setResult(boxs.get(0).outerHtml());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonResponse;
	}
	/**
	 * create Jan 20, 2016
	 * @param pe920
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.getToMyLib", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse getToMyLib(@ModelAttribute("pe920") int pe920)  {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
			jsonResponse.setResult(evoLibraryService.getToMyLib(q100.getPo100(), pe920));
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param webId
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.loadLibOfWeb", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadLibOfWeb(@ModelAttribute("webId") int webId){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 != null)
		{
			List<E920MG> listE920s = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E920MG.class, new QbCriteria(QbMongoFiledsName.WEBID, webId),
																														new QbCriteria(QbMongoFiledsName.LIB_TYPE, 2));
			if(listE920s.size() > 0 && listE920s.get(0).getFo100() != q100.getPo100());
			{
				jsonResponse.setResult(listE920s);
				return jsonResponse;
			}
		}
		jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param pe920
	 * @param star
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.u920UpdateVote", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse u920UpdateVote(@ModelAttribute("pe920") int pe920, @ModelAttribute("star") int star){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 !=null)
			jsonResponse.setResult(e920mgDao.e920UpdateVote(pe920, q100.getPo100(), star));
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
	/**
	 * @param pe920
	 * @return
	 */
	@RequestMapping(value = "/libraryBean.listOfTabVote", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse e920ListOfTabVote(@ModelAttribute("pe920") int pe920){
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(q100 !=null)
			jsonResponse.setResult(e920mgDao.e920ListOfTabVote(pe920, q100.getPo100()));
		else
			jsonResponse.setResult(null);
		return jsonResponse;
	}
}
