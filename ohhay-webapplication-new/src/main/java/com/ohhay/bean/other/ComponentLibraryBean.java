package com.ohhay.bean.other;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.criteria.AdmininstrationComponent;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.JacksonResponse;
import com.ohhay.web.lego.entities.mongo.web.E900MG;

/**
 * @author ThoaiNH create Mar 28, 2017
 */
@Controller
@Scope("prototype")
public class ComponentLibraryBean implements Serializable {
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;
	Logger log = Logger.getLogger(ComponentLibraryBean.class);

	@RequestMapping(value = "/componentLibraryBean.listConponentsLibrary", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse listConponentsLibrary(@ModelAttribute("type") String type,
			@ModelAttribute("ev903") String ev903) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		// Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		log.info(type + " : " + ev903);
		List<E900MG> e900mgs = null;
		// if (q100 != null) {
		if (type.equalsIgnoreCase("normal-iframe"))
			e900mgs = templateService.findDocumentsOr(1200, E900MG.class, "LIB_SORT", Direction.DESC,
					new QbCriteria("EV903", "LIB"), new QbCriteria("TYPE", "normal-iframe"),
					new QbCriteria("TYPE", "normal_youtube_playlist"));
		else
			e900mgs = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E900MG.class, "LIB_SORT",
					Direction.DESC, 0, 1000, new QbCriteria("TYPE", type), new QbCriteria("EV903", ev903));

		log.info("Data : " + e900mgs);
		jsonResponse.setResult(e900mgs);
		return jsonResponse;
	}

	@RequestMapping(value = "/componentLibraryBean.swapComponent", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse swapPositionComponent(
			@RequestBody List<AdmininstrationComponent> admininstrationComponent) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		try {
			log.info(admininstrationComponent);
			for (AdmininstrationComponent item : admininstrationComponent) {
				templateService.updateOneField(ApplicationConstant.FO100_SUPER_ADMIN, E900MG.class, item.getId(),
						"LIB_SORT", item.getLibSort(), null);
			}
			jsonResponse.setResult(1);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			jsonResponse.setResult(0);
		}
		return jsonResponse;
	}

	/**
	 * @param pe900
	 * @return
	 */
	@RequestMapping(value = "/componentLibraryBean.loadComponentData", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse loadComponentData(@ModelAttribute("pe900") int pe900) {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		jsonResponse.setResult(
				templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, pe900, E900MG.class));
		return jsonResponse;
	}

	/**
	 * save new components to library
	 */
	@RequestMapping(value = "/componentLibraryBean.addComponentToLib", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse addComponentToLib(@ModelAttribute("id") int id,
			@ModelAttribute("ev901") String ev901, @ModelAttribute("ev902") String ev902) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			try {
				E900MG e900ORmg = templateService.findDocumentById(q100.getPo100(), (int) id, E900MG.class);
				E900MG e900mg = new E900MG(e900ORmg);
				long newCompId = sequenceService.getNextSequenceId(q100.getPo100(), QbMongoCollectionsName.E900);
				e900mg.setId(newCompId);
				e900mg.setFo100A(q100.getPo100());
				e900mg.setFo100(q100.getPo100());
				e900mg.setEv901(ev901);
				e900mg.setEv902(ev902);
				e900mg.setEv903("LIB");
				e900mg.setLibSort((int) newCompId);
				e900mg.setEl949(new Date());
				e900mg.setWebId(0);
				e900mg.setLibSort(id);
				int rs = templateService.saveDocument(q100.getPo100(), e900mg, QbMongoCollectionsName.E900);
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				jsonResponse.setResult(rs);
				return jsonResponse;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}

	/**
	 * remove components from library
	 */
	@RequestMapping(value = "/componentLibraryBean.removeComponentFromLib", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse removeComponentFromLib(@ModelAttribute("id") int id) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
		if (q100 != null && "ADMIN".equals(q100.getM900mg().getMv922())) {
			int rs = templateService.removeDocumentById(q100.getPo100(), id, E900MG.class);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jsonResponse.setResult(rs);
			return jsonResponse;
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;
	}
}
