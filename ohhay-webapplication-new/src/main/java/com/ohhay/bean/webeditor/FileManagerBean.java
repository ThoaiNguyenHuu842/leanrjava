package com.ohhay.bean.webeditor;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.D100MG;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author TuNt create date Aug 18, 2016 ohhay-webapplication-new
 */
@Controller
@Scope("prototype")
public class FileManagerBean {
	private Logger log = Logger.getLogger(FileManagerBean.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SequenceService sequenceService;

	/**
	 * @return
	 */
	@RequestMapping(value = "/fileManagerBean.getListFile", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse getListFile() {
		JacksonResponse jsonResponse = new JacksonResponse();
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();

		if (q100 != null) {
			List<D100MG> listD100mg = templateService.findDocuments(q100.getPo100(), D100MG.class,
					new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()));
			jsonResponse.setResult(listD100mg);
		} else
			jsonResponse.setResult(null);
		return jsonResponse;
	}

	/**
	 * @param dv101
	 * @param dv102
	 * @return
	 */
	@RequestMapping(value = "/fileManagerBean.uploadFile", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse uploadFile(@ModelAttribute("dv101") String dv101,
			@ModelAttribute("dv102") String dv102) {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		int id, result = 0;
		boolean check = false;
		if (q100 != null) {
			List<String> fileLimit = new ArrayList<String>();

			fileLimit.add(".pdf");
			fileLimit.add(".doc");
			fileLimit.add(".docx");
			fileLimit.add(".ppt");
			fileLimit.add(".pptx");
			fileLimit.add(".xls");
			fileLimit.add(".odp");
			fileLimit.add(".odt");
			fileLimit.add(".xlsx");

			for (String ex : fileLimit) {
				if (dv101.toLowerCase().endsWith(ex))
					check = true;
			}
			if (check) {
				try {
					byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(dv102);
					EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils("AS", q100.getPo100());
					String rootPath = "docs/";
					String fileName = dv101;
					String url = awsFileUtils.uploadObjectMutilPart(rootPath + fileName, btDataFil);
					log.info("path file new update ---- "+url);
					D100MG d100mg = new D100MG();
					id = (int) sequenceService.getNextSequenceId(q100.getPo100(), QbMongoCollectionsName.D100);
					d100mg.setId(id);
					d100mg.setFo100(q100.getPo100());
					d100mg.setDv101(dv101);
					d100mg.setDv103(url);
					d100mg.setDn102(ApplicationHelper.getFileSize(url));
					d100mg.setDd108(new Date());
					d100mg.setDd106(new Date());
					result = templateService.saveDocument(q100.getPo100(), d100mg, QbMongoCollectionsName.D100);
					jsr.setResult(result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					jsr.setResult(result);
				}
			} else
				jsr.setResult(-2);
		}
		return jsr;
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/fileManagerBean.removeFile", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse removeFile(@ModelAttribute("id") int id) {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			jsr.setResult(templateService.removeDocumentById(q100.getPo100(), id, D100MG.class));
		}
		return jsr;
	}
	
	@RequestMapping(value = "/fileManagerBean.checkRightUpDoc", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse checkRightUpDoc() {
		JacksonResponse jsr = new JacksonResponse();
		jsr.setStatus(JacksonResponse.AJAX_SUCCESS);
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 != null) {
			jsr.setResult(q100.getAuthenticationRightInfo().isADD_DOC_ALL());
		}
		return jsr;
	}

}
