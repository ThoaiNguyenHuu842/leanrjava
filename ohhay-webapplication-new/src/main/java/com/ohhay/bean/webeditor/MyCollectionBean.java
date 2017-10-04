package com.ohhay.bean.webeditor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.P950MG;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.ImageAlbumService;
import com.ohhay.core.mongo.service.P950MGService;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.JacksonResponse;

/**
 * @author ThoaiNH create Oct 28, 2015 controller for collection data src
 */
@Controller
@Scope("prototype")
public class MyCollectionBean {
	private static Logger log = Logger.getLogger(WebBuilderBean.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_IMAGE_ALBUM)
	ImageAlbumService imageAlbumService;
	@Autowired
	TemplateService templateService;
	@Autowired
	P950MGService p950mgService;

	/**
	 * upload base64 image to aws
	 * 
	 * @param imgBase64
	 * @param webId: khi trial thi dung istest lam webid, fo100
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/myCollectionBean.upLoad", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse upLoadImageAlbum(@ModelAttribute("imgBase64") String imgBase64, @ModelAttribute("webId") int webId, @ModelAttribute("src") String src, @ModelAttribute("ext") String ext, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 == null) {
			try {
				String imgContent = imgBase64.split("\\,")[1];
				/*
				 * upload trial
				 */
				byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
				EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils("AS", webId);
				String fileName = ApplicationHelper.generateFileName();
				if (ext != null && ext.length() > 0)
					fileName += "." + ext;
				String url = awsFileUtils.uploadObjectMutilPartImgAlbum(webId, fileName, btDataFil);
				P950MG p950mg = new P950MG();
				p950mg.setImgUrl(url);
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				jsonResponse.setResult(p950mg);
			} catch (Exception ex) {
				jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			}
		} else {
			log.info("--ext:" + ext);
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jsonResponse.setResult(imageAlbumService.upload(q100.getPo100(), q100.getM900mg().getRegion(), webId, imgBase64, src, ext));
		}
		return jsonResponse;

	}

	/**
	 * ANPH: getBase64FromUrl for CORS
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myCollectionBean.getBase64FromUrl", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse getBase64FromUrl(@ModelAttribute("imgUrl") String imageUrl) {
		JacksonResponse jsonResponse = new JacksonResponse();
		String base64Image = null;
		String[] imgPart = imageUrl.split("\\.");
		String imgType = imgPart[imgPart.length - 1];
		try {
			URL url = new URL(imageUrl);
			BufferedImage bufferedImage = ImageIO.read(url);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, imgType, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			base64Image = Base64.encode(imageInByte);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		List<String> respone = new ArrayList<>();
		respone.add("data:image/" + imgType + ";base64," + base64Image);
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		jsonResponse.setResult(respone);
		return jsonResponse;

	}

	/**
	 * load image uploaded by user
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myCollectionBean.load", method = RequestMethod.GET)
	public @ResponseBody JacksonResponse load(@ModelAttribute("src") String src, @ModelAttribute("offset") int offset, @ModelAttribute("limit") int limit) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 == null) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(null);
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			List<P950MG> listP950;
//			if (!"ALL".equals(src))
//				listP950 = templateService.findDocuments(q100.getPo100(), P950MG.class, new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()), new QbCriteria(QbMongoFiledsName.WEBID, 0), new QbCriteria(QbMongoFiledsName.SRC, src));
//			else
//				listP950 = templateService.findDocuments(q100.getPo100(), P950MG.class, new QbCriteria(QbMongoFiledsName.FO100, q100.getPo100()), new QbCriteria(QbMongoFiledsName.WEBID, 0));
			listP950 = p950mgService.listOfTabP950(q100.getPo100(), src, offset, limit);
			log.info(listP950);
			jsonResponse.setResult(listP950);
		}
		return jsonResponse;

	}

	/**
	 * remove image
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myCollectionBean.remove", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse remove(@ModelAttribute("pp950") int pp950, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if (q100 == null) {
			jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
			jsonResponse.setResult(null);
		} else {
			jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
			jsonResponse.setResult(imageAlbumService.remove(pp950, q100.getPo100(), q100.getM900mg().getAwsBucket()));
		}
		return jsonResponse;

	}
	/**
	 * 15/03/2016 upload by nodeJS
	 * @param imgUrl
	 * @param thumbUrl
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/myCollectionBean.uploadToMyCollection", method = RequestMethod.POST)
	public @ResponseBody JacksonResponse uploadToMyCollection(@ModelAttribute("imgUrl") String imgUrl, @ModelAttribute("thumbUrl") String thumbUrl, @ModelAttribute("src") String src, BindingResult result) {
		JacksonResponse jsonResponse = new JacksonResponse();
		Q100 q100 = AuthenticationHelper.getUserLogin();
		try {
			if(q100 != null){
				P950MG p950mg = new P950MG();
				SequenceService sequenceService = (SequenceService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SEQUENCE);
				long p950NewId;
				p950NewId = sequenceService.getNextSequenceId(q100.getPo100(), QbMongoCollectionsName.P950);
				p950mg.setFo100(q100.getPo100());
				p950mg.setId(p950NewId);
				p950mg.setPv951(imgUrl);
				p950mg.setPv952(thumbUrl);
				p950mg.setSrc(src);
				templateService.saveDocument(q100.getPo100() , p950mg, QbMongoCollectionsName.P950);
				jsonResponse.setResult(p950mg);
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				return jsonResponse;
			}
			else {
				P950MG p950mg = new P950MG();
				p950mg.setPv951(imgUrl);
				p950mg.setPv952(thumbUrl);
				jsonResponse.setResult(p950mg);
				jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
				return jsonResponse;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonResponse.setStatus(JacksonResponse.AJAX_SUCCESS);
		jsonResponse.setStatus(JacksonResponse.AJAX_ERROR);
		return jsonResponse;

	}
}