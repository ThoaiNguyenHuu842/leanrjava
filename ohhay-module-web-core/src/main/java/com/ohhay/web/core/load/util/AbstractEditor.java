package com.ohhay.web.core.load.util;

import java.util.Date;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.P900MG;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.C110MG;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebLanguageBase;
import com.ohhay.web.core.history.WebEditedInfo;
import com.ohhay.web.core.history.WebHistoryManager;
import com.ohhay.web.core.mongo.service.C900MGService;
import com.ohhay.web.core.utils.GalleryItemFieldCriteria;
import com.ohhay.web.core.utils.WebTemplateRule;

/**
 * @author ThoaiNH
 * create 25/11/2014
 * base web editor
 */
public abstract class AbstractEditor {
	@Autowired
	private TemplateService templateService;
	private static Logger log = Logger.getLogger(AbstractEditor.class);
	/*
	 * store old picture to delete
	 */
	protected int insertP900MG(String pv901) {
		Q100 q100 = (Q100)AuthenticationHelper.getUserLogin();
		P900MG p900mg = new P900MG();
		p900mg.setFo100(q100.getPo100());
		p900mg.setPv901(pv901);
		p900mg.setPd902(new Date());
		return templateService.saveDocument(q100.getPo100(), p900mg, QbMongoCollectionsName.P900);
	}
	/*
	 * save all C900
	 */
	protected boolean updateC900MG(C900MG c900mg) {
		log.info(" language code c900mg:" + c900mg.getLanguageCode());
		return updateC900MG(c900mg,c900mg.getExtend());
	}

	/*
	 * save all gallery item
	 */
	protected boolean updateC900MG(GalleryItemFieldCriteria galleryItemField) {
		log.info(" language code galleryItemField:"
				+ galleryItemField.getLanguageCode());
		return updateC900MG(galleryItemField,galleryItemField.getExtend());
	}

	/*
	 * save edit element to cv906
	 */
	@SuppressWarnings("restriction")
	protected void saveElementGalleryItemField(C110MG c110mg, GalleryItemFieldCriteria galleryItemField) {
		try {
			Document document = Jsoup.parse(c110mg.getCv112());
			Elements elementsEditAble = document.body().select("["+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT + "~=]");
			System.out.println(" saveElementGalleryItemFieldText");
			for (Element element : elementsEditAble) {
				String editItem = element.attr(TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT_ITEM);
				// find postion
				if (editItem.equals(galleryItemField.getEditItem())) {
					String type = element
							.attr(TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT);
					log.info(" type:" + type);
					/*
					 * text field
					 */
					if (type == null || type.length() == 0) {
						element.empty();
						element.append(galleryItemField.getContent());
					}
					/*
					 * href
					 */
					else if (type.equals("href")) {
						// link text
						if (galleryItemField.getGroupType() == GalleryItemFieldCriteria.GROUP_TYPE_LINK_TEXT) {
							String strings[] = (galleryItemField.getContent()+" ").split("##..");// pattern merge text and link - update 25/09/2015 + " " fix bug not enter link
							if (strings.length == 2) {
								String title = strings[0];
								String link = strings[1];
								// save href
								element.attr("href", link.trim());
								// save title
								if (element.select("tag").size() > 0) {
									Element elementInner = element
											.select("tag").get(0);
									elementInner.empty();
									elementInner.append(title);
								}
							}
						}
						// link image
						else {
							// save href
							String link = galleryItemField.getContent();
							element.attr("href", link);
							// save img
							if (galleryItemField.getImgBase64() != null
									&& galleryItemField.getImgBase64().length() > 0) {
								// upload to server
								String imgContent = galleryItemField
										.getImgBase64().split("\\,")[1];
								byte[] btDataFile = new sun.misc.BASE64Decoder()
										.decodeBuffer(imgContent);
								AWSFileUtils awsFileUtils = new AWSFileUtils();
								String fileName = ApplicationHelper.generateFileName(c110mg.getCv111());
								if(galleryItemField.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE)
								{
									awsFileUtils.uploadObjectMutilPartA900(galleryItemField.getWebid(), fileName, btDataFile);
									fileName =  TemplateRule.OHHAY_TEMPLATE_LINK+ fileName;
								}
								else
								{
									awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
									fileName =  TemplateRule.OHHAY_SERVER_LINK+ fileName;
								}
								System.out.println("- file name:" + fileName);
								if (element.select("img").size() > 0) {
									Element elementInner = element
											.select("img").get(0);
									String oldImg = elementInner.attr("src");
									elementInner.attr("src", fileName);
									//delete old file
									//awsFileUtils.deleteObject(ApplicationHelper.getAWSKeyFromBaseLink(oldImg));
									insertP900MG(ApplicationHelper.getAWSKeyFromBaseLink(oldImg));
								}
							}
						}
					}
					/*
					 * image
					 */
					else if (type.equals("src")) {
						// save img
						if (galleryItemField.getImgBase64() != null
								&& galleryItemField.getImgBase64().length() > 0) {
							// upload to server
							String imgContent = galleryItemField.getImgBase64()
									.split("\\,")[1];
							byte[] btDataFile = new sun.misc.BASE64Decoder()
									.decodeBuffer(imgContent);
							AWSFileUtils awsFileUtils = new AWSFileUtils();
							String fileName = ApplicationHelper.generateFileName(c110mg.getCv111());
							if(galleryItemField.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE)
							{
								awsFileUtils.uploadObjectMutilPartA900(galleryItemField.getWebid(), fileName, btDataFile);
								fileName =  TemplateRule.OHHAY_TEMPLATE_LINK+ fileName;
							}
							else
							{
								awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
								fileName =  TemplateRule.OHHAY_SERVER_LINK+ fileName;
							}
							awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
							String oldImg = element.attr("src");
							element.attr("src",fileName);
							//delete old file
							//awsFileUtils.deleteObject(ApplicationHelper.getAWSKeyFromBaseLink(oldImg));
							insertP900MG(ApplicationHelper.getAWSKeyFromBaseLink(oldImg));
						}
						//save image link
						log.info("---attach cv117:" + galleryItemField.getCv117());
						if(galleryItemField.getCv117() != null )
							element.attr(TemplateRule.OHHAY_IMGS_LINK, galleryItemField.getCv117());
					}
					/*
					 * percent type in template
					 */
					else if (type.equals("percent")) {
						element.attr("percent", galleryItemField.getContent());
					}
					break;
				}
			}
			log.info("Abstract webeditor: " + document.body().html());
			c110mg.setCv112(document.body().html());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/*
	 * gallery item type
	 */
	private <T> boolean updateC900MG(GalleryItemFieldCriteria galleryItemField, int extend) {
		C900MGService c900mgService = (C900MGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_C900MG);
		try {
			int webId = 0;
			int indexProperty = -1;
			Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
			int fo100 = 0;
			if (q100 != null)
				fo100 = q100.getPo100();
			log.info(" pc900:" + galleryItemField.getPc900());
			String[] ids = galleryItemField.getPc900().split("#");
			webId = Integer.parseInt(ids[0]);
			indexProperty = Integer.parseInt(ids[3]);
			/*
			 * 0) get fo100 user to know insert db center or user db
			 */
			int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100;
			/*
			 * 1)find real c900 in db (c900mg khong chua cv906 nen phai tim lai)
			 */
			TemplateService updateOneFieldService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			C110MG c110mg = null;
			OhhayWebLanguageBase languageBase = (OhhayWebLanguageBase) updateOneFieldService.findDocument(fo100i, WebTemplateRule.getWebLanguageClassFromExtend(extend), 
																										  new QbCriteria(QbMongoFiledsName.LANGUAGEID, 
																									      ApplicationHelper.convertToMD5(webId+ galleryItemField.getLanguageCode())));
			c110mg = languageBase.getLiC110mgs().get(indexProperty);
			String oldCv112 = c110mg.getCv112();
			/*
			 * 2) change c900Real.cv906
			 */
			saveElementGalleryItemField(c110mg, galleryItemField);
			if (indexProperty >= 0 && galleryItemField.getLanguageCode() != null) {
				if (c900mgService.updateEleme100(String.valueOf(webId), galleryItemField.getLanguageCode(), indexProperty, QbMongoFiledsName.CV112, c110mg.getCv112(), WebTemplateRule.getWebLanguageClassFromExtend(extend), fo100, fo100i) == 1)
				{
					log.info("---tracking object name:"+galleryItemField.getTrackingObjectName());
					log.info("--status:"+galleryItemField.getTrackingStatus());
					/*
					 * save tracking object name
					 */
					if(galleryItemField.getTrackingObjectName() != null && galleryItemField.getTrackingObjectName().trim().length() > 0)
					{
						if(!galleryItemField.getTrackingObjectName().equals(c110mg.getCv113()))
							c900mgService.updateEleme100(String.valueOf(webId), galleryItemField.getLanguageCode(), indexProperty, QbMongoFiledsName.CV113, galleryItemField.getTrackingObjectName(), WebTemplateRule.getWebLanguageClassFromExtend(extend), fo100, fo100i);
						if(galleryItemField.getTrackingStatus() != c110mg.getCn114())
							c900mgService.updateEleme100(String.valueOf(webId), galleryItemField.getLanguageCode(), indexProperty, QbMongoFiledsName.CN114, galleryItemField.getTrackingStatus(), WebTemplateRule.getWebLanguageClassFromExtend(extend), fo100,fo100i);
					}
					/*
					 * save to history
					 */
					WebEditedInfo oldEditedInfo = new WebEditedInfo(1,oldCv112,webId, extend, indexProperty, galleryItemField.getLanguageCode());
					WebEditedInfo newEditedInfo = new WebEditedInfo(1,c110mg.getCv112(),webId, extend, indexProperty, galleryItemField.getLanguageCode());
					WebHistoryManager.addNewItem(oldEditedInfo,newEditedInfo);
					WebHistoryManager.test();
					return true;
				}

			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/*
	 * normal type
	 */
	private <T> boolean updateC900MG(C900MG c900mg, int extend) {
		C900MGService c900mgService = (C900MGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_C900MG);
		try {
			int webId = 0;
			int indexPart = 0;
			int indexEleme = 0;
			int indexProperty = -1;
			Q100 q100 = (Q100) AuthenticationHelper.getUserLogin();
			int fo100 = 0;
			if (q100 != null)
				fo100 = q100.getPo100();
			/*
			 * get fo100 user to know insert db center or user db
			 */
			int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100;
			/*
			 * normal element
			 */
			log.info(" pc900:" + c900mg.getPc900());
			log.info(" update cv904 new:" + c900mg.getCv904());
			log.info(" update cv905 new:" + c900mg.getCv905());
			log.info(" update cv906 new:" + c900mg.getCv906());
			// luu tam super id vao pc900
			String[] ids = c900mg.getPc900().split("#");
			webId = Integer.parseInt(ids[0]);
			indexPart = Integer.parseInt(ids[1]);
			indexEleme = Integer.parseInt(ids[2]);
			indexProperty = Integer.parseInt(ids[3]);
			/*
			 * get old content save to history
			 */
			TemplateService updateOneFieldService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			C110MG c110mg = null;
			OhhayWebLanguageBase languageBase = (OhhayWebLanguageBase) updateOneFieldService
												.findDocument(fo100i, WebTemplateRule.getWebLanguageClassFromExtend(extend), new QbCriteria(
												QbMongoFiledsName.LANGUAGEID, ApplicationHelper.convertToMD5(webId + c900mg.getLanguageCode())));
			c110mg = languageBase.getLiC110mgs().get(indexProperty);
			String oldCv112 = c110mg.getCv112();
			if (indexProperty >= 0 && c900mg.getLanguageCode() != null) {
				if (c900mgService
						.updateEleme100(String.valueOf(webId), c900mg
								.getLanguageCode(), indexProperty, QbMongoFiledsName.CV112, c900mg
								.getCv905(), WebTemplateRule.getWebLanguageClassFromExtend(extend), fo100, fo100i) == 1
						&& c900mgService
								.updateEleme(webId, indexPart, indexEleme, QbMongoFiledsName.CV904, c900mg
										.getCv904(), WebTemplateRule.getWebClassFromExtend(extend), fo100,fo100i) == 1);
				{
					log.info("---tracking object name:"+c900mg.getTrackingObjectName());
					log.info("--status:"+c900mg.getTrackingStatus());
					/*
					 * save tracking object name
					 */
					if(c900mg.getTrackingObjectName() != null && c900mg.getTrackingObjectName().trim().length() > 0)
					{
						if(!c900mg.getTrackingObjectName().equals(c110mg.getCv113()))
							c900mgService.updateEleme100(String.valueOf(webId), c900mg.getLanguageCode(), indexProperty, QbMongoFiledsName.CV113, 
														c900mg.getTrackingObjectName(), WebTemplateRule.getWebLanguageClassFromExtend(extend), fo100, fo100i);
						if(c900mg.getTrackingStatus() != c110mg.getCn114())
							c900mgService.updateEleme100(String.valueOf(webId), c900mg.getLanguageCode(), indexProperty, QbMongoFiledsName.CN114,
														c900mg.getTrackingStatus(), WebTemplateRule.getWebLanguageClassFromExtend(extend), fo100, fo100i);
					}
					/*
					 * save to history
					 */
					WebEditedInfo oldEditedInfo = new WebEditedInfo(1,oldCv112,webId, extend, indexProperty, c900mg.getLanguageCode());
					WebEditedInfo newEditedInfo = new WebEditedInfo(1,c900mg.getCv905(),webId, extend, indexProperty, c900mg.getLanguageCode());
					WebHistoryManager.addNewItem(oldEditedInfo, newEditedInfo);
					WebHistoryManager.test();
					return true;
				}
			}
			else {
				if (c900mgService.updateEleme(webId, indexPart, indexEleme, QbMongoFiledsName.CV905, c900mg.getCv905(),
											  WebTemplateRule.getWebClassFromExtend(extend),fo100, fo100i) == 1
						&& c900mgService.updateEleme(webId, indexPart, indexEleme, QbMongoFiledsName.CV904, c900mg.getCv904(),
											  WebTemplateRule.getWebClassFromExtend(extend),fo100, fo100i) == 1)
				{
					return true;
				}
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 * on Edit Web Success update web short is new to reload web data
	 * @param mClass
	 * @param webId
	 * @param languageCode: null if update all language
	 * @return
	 */
	protected <T> int onEditWebSuccess(Class<T> mClass, int webId, String languageCode, int fo100) {
		if(languageCode == null)
			return templateService.updateOneFieldMultil(fo100, WebTemplateRule.getWebShortClassFromCollectionClass(mClass),QbMongoFiledsName.IS_NEW, 1, null, new QbCriteria(QbMongoFiledsName.WEBID, webId));
		else
			return templateService.updateOneField(fo100, WebTemplateRule.getWebShortClassFromCollectionClass(mClass),QbMongoFiledsName.IS_NEW, 1, null, new QbCriteria(QbMongoFiledsName.LANGUAGE_CODE, languageCode),new QbCriteria(QbMongoFiledsName.WEBID, webId));
	}
}
