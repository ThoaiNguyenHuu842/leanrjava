package com.ohhay.web.editor.tools;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.api.service.N950OrelService;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractEditor;
import com.ohhay.web.core.mongo.service.C900MGService;
import com.ohhay.web.core.utils.GalleryItemFieldCriteria;
import com.ohhay.web.core.utils.WebTemplateRule;

/**
 * @author ThoaiNH 
 * create 19/11/2014
 * web edit service
 * Chu y: superid khi edit se duoc luu tam vao pc900
 */
@Service
@Scope("prototype")
public class GalleryItemEditor extends AbstractEditor {
	private static Logger log = Logger.getLogger(GalleryItemEditor.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	private TemplateService templateService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_C900MG)
	private C900MGService c900mgService;
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_N950OREL)
	private N950OrelService n950OrelService;
	/**
	 * save gallery field
	 */
	public boolean saveGalleryItemField(GalleryItemFieldCriteria galleryItemField) {
		log.info("- extend:" + galleryItemField.getExtend());
		if(updateC900MG(galleryItemField))
		{
			String[] ids = galleryItemField.getPc900().split("#");
			int webId = Integer.parseInt(ids[0]);
			Q100 q100 = AuthenticationHelper.getUserLogin();
			onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(galleryItemField.getExtend()),webId, galleryItemField.getLanguageCode(), q100.getPo100());
			return true;
		}
		return false;
	}
	/**
	 * save image C900 image base 64
	 */
	public boolean saveC900MGImgBase64(C900MG c900mg) {
		try {
			log.info("--begin upload to aws");
			Q100 q100 = AuthenticationHelper.getUserLogin();
			String imgContent = c900mg.getImgBase64().split("\\,")[1];
			@SuppressWarnings("restriction")
			byte[] btDataFile = new sun.misc.BASE64Decoder()
					.decodeBuffer(imgContent);
			AWSFileUtils awsFileUtils = new AWSFileUtils();
			String fileName = ApplicationHelper.generateFileName(c900mg.getPc900());
			if(c900mg.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE)
			{
				awsFileUtils.uploadObjectMutilPartA900(c900mg.getWebId(), fileName, btDataFile);
				fileName =  TemplateRule.OHHAY_TEMPLATE_LINK+ fileName;
			}
			else
			{
				awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
				fileName =  TemplateRule.OHHAY_SERVER_LINK+ fileName;
			}
			int kq = awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
			if (kq > 0) {
				//delete old file
				//awsFileUtils.deleteObject(ApplicationHelper.getAWSKeyFromUrlImg(c900mg.getCv905()));
				insertP900MG(c900mg.getCv905());
				// save content
				c900mg.setCv905(fileName);
				log.info("--begin save to mongo");
				if(updateC900MG(c900mg))
				{
					String[] ids = c900mg.getPc900().split("#");
					int webId = Integer.parseInt(ids[0]);
					onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(c900mg.getExtend()),webId, c900mg.getLanguageCode(), q100.getPo100());
					return true;
				}
			}
			return false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * save gallery link image base 64
	 */
	@SuppressWarnings("restriction")
	public boolean saveC900GalleryLinkImage(C900MG c900mg) {
		if (c900mg.getImgBase64() != null && c900mg.getImgBase64().length() > 0) {
			byte[] btDataFile;
			try {
				String imgContent = c900mg.getImgBase64().split("\\,")[1];
				log.info(" imgContent:" + imgContent);
				btDataFile = new sun.misc.BASE64Decoder()
						.decodeBuffer(imgContent);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				String fileName = ApplicationHelper.generateFileName(c900mg.getPc900());
				if(c900mg.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE)
				{
					awsFileUtils.uploadObjectMutilPartA900(c900mg.getWebId(), fileName, btDataFile);
					fileName =  TemplateRule.OHHAY_TEMPLATE_LINK+ fileName;
				}
				else
				{
					awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
					fileName =  TemplateRule.OHHAY_SERVER_LINK+ fileName;
				}
				int kq = awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
				if (kq > 0) {
					//delete old file
					//awsFileUtils.deleteObject(ApplicationHelper.getAWSKeyFromUrlImg(c900mg.getCv905()));
					insertP900MG(c900mg.getCv905());
					c900mg.setCv905(fileName);					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if(updateC900MG(c900mg))
		{
			Q100 q100 = AuthenticationHelper.getUserLogin();
			String[] ids = c900mg.getPc900().split("#");
			int webId = Integer.parseInt(ids[0]);
			onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(c900mg.getExtend()),webId, c900mg.getLanguageCode(), q100.getPo100());
			return true;
		}
		return false;
	}
	
	/**
	 * create Oct 28, 2015
	 * save url for image
	 * @param c900mg
	 * @return
	 */
	public boolean saveUrlForImg(C900MG c900mg, int extend, int fo100) {
		int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100;
		String[] ids = c900mg.getPc900().split("#");
		int webId = Integer.parseInt(ids[0]);
		int indexProperty = Integer.parseInt(ids[3]);
		C900MGService c900mgService = (C900MGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_C900MG);
		if(c900mgService.updateEleme100(String.valueOf(webId), c900mg
				.getLanguageCode(), indexProperty,"CV117", c900mg
				.getCv117(), WebTemplateRule.getWebLanguageClassFromExtend(extend), fo100, fo100i) > 0)
			onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(c900mg.getExtend()),webId, c900mg.getLanguageCode(), fo100);
		return false;
	}
}
