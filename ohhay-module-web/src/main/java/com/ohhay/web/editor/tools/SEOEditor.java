package com.ohhay.web.editor.tools;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.criteria.N950Criteria;
import com.ohhay.core.filesutil.AWSFileUtils;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.load.util.AbstractEditor;
import com.ohhay.web.core.utils.WebTemplateRule;

/**
 * @author ThoaiNH
 * create 06/10/2015
 */
@Service
@Scope("prototype")
public class SEOEditor extends AbstractEditor{
	private static Logger log = Logger.getLogger(SEOEditor.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	private TemplateService templateService;
	/*
	 * Copy by ThongQB
	 * Save image to amazone
	 * 
	 * */
	@SuppressWarnings("restriction")
	public String saveImageBase64(String imageContent, int extend, int webID, int fo100){
		
		if(imageContent!=null && imageContent.length()>0){
			//upload web icon
			String imgContent = imageContent.split("\\,")[1];
			byte[] btDataFile;
			try {
				btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
				AWSFileUtils awsFileUtils = new AWSFileUtils();
				String fileName = ApplicationHelper.generateFileName();
				
				if(extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE)
				{
					awsFileUtils.uploadObjectMutilPartA900(webID, fileName, btDataFile);
					/*fileName =  TemplateRule.OHHAY_TEMPLATE_LINK+ fileName;*/
				}
				else
				{
					awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
					/*fileName =  TemplateRule.OHHAY_SERVER_LINK+ fileName;*/
				}
				awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
				log.info("filename"+fileName);
				return fileName;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else{
			return null;
		}
	}
	public int saveWebInfo(N950Criteria n950Criteria, int fo100){
		try{
			//fo100 user to know insert db center or user db
			int fo100i = (n950Criteria.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || n950Criteria.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100;
			//validate friendly url key
			if(n950Criteria.getFriendlyUrl() != null && n950Criteria.getFriendlyUrl().trim().length() > 0){
				if(!ApplicationHelper.validateRegex(n950Criteria.getFriendlyUrl(), RegexConstant.FRIENDLYKEY_PATTERN))
					return -2;
			}
			OhhayWebBase ohhayWebBase = (OhhayWebBase) templateService.findDocumentById(fo100i, n950Criteria.getWebId(), WebTemplateRule.getWebClassFromExtend(n950Criteria.getExtend()));
			if(ohhayWebBase != null)
			{
				//create new info if null
				if(ohhayWebBase.getN950mg() == null)
				{
					ohhayWebBase.setN950mg(new N950MG());
				}
				if(n950Criteria.getImgBase64()!=null && n950Criteria.getImgBase64().length()>0){
					//upload web icon
					String imgContent = n950Criteria.getImgBase64().split("\\,")[1];
					byte[] btDataFile;
					try {
						btDataFile = new sun.misc.BASE64Decoder()
								.decodeBuffer(imgContent);
						AWSFileUtils awsFileUtils = new AWSFileUtils();
						String fileName = ApplicationHelper.generateFileName();
						if(n950Criteria.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE)
						{
							awsFileUtils.uploadObjectMutilPartA900(n950Criteria.getWebId(), fileName, btDataFile);
							fileName =  TemplateRule.OHHAY_TEMPLATE_LINK+ fileName;
						}
						else
						{
							awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
							fileName =  TemplateRule.OHHAY_SERVER_LINK+ fileName;
						}
						awsFileUtils.uploadObjectMutilPart(fileName, btDataFile);
						//set img web icon
						ohhayWebBase.getN950mg().setNv962(fileName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//duong va so nha
				ohhayWebBase.getN950mg().setNv951(n950Criteria.getStnumbers()+ " " +n950Criteria.getRoute());
				//phuong
				ohhayWebBase.getN950mg().setNv952(n950Criteria.getSublocality());
				//quan huyen
				ohhayWebBase.getN950mg().setNv953(n950Criteria.getLocality());
				//zip code
				ohhayWebBase.getN950mg().setNv954(n950Criteria.getPostalcode());
				//quoc gia
				ohhayWebBase.getN950mg().setNv955(n950Criteria.getCountry());
				//validate phone if user input phone
				if(n950Criteria.getPhone() != null && n950Criteria.getPhone().length() >0)
				{
					if(ApplicationHelper.validatePhoneNumber(n950Criteria.getPhone()) == true)
						ohhayWebBase.getN950mg().setNv956(n950Criteria.getPhone());
					else
						return -1;
				}
				//email
				ohhayWebBase.getN950mg().setNv957(n950Criteria.getEmail());
				//address
				ohhayWebBase.getN950mg().setNv958(n950Criteria.getAddress());
				//la
				ohhayWebBase.getN950mg().setNn960(n950Criteria.getLa());;
				//lng
				ohhayWebBase.getN950mg().setNn961(n950Criteria.getLng());
				//friendlyUrl
				ohhayWebBase.getN950mg().setNv966(n950Criteria.getFriendlyUrl());
				//webTitle
				ohhayWebBase.getN950mg().setNv963(n950Criteria.getWebTitle());
				//webDescript
				ohhayWebBase.getN950mg().setNv964(n950Criteria.getWebDescript());
				//webImage
				if(n950Criteria.getWebImage() != null && n950Criteria.getWebImage().isEmpty() == false && n950Criteria.getWebImage().indexOf(ohhayWebBase.getN950mg().getNv965().toString()) <= 0){
					try{
						ohhayWebBase.getN950mg().setNv965(saveImageBase64(n950Criteria.getWebImage(),n950Criteria.getExtend(),n950Criteria.getWebId(),fo100));
						log.info("+++++++++++++++++++++++++++++CHANGED IMAGE+++++++++++++++++++");
					}
					catch(Exception ex){
						
					}
				}
				
				int kq = 0;
				kq = templateService.saveDocument(fo100i, ohhayWebBase, WebTemplateRule.getWebMongoColectionFromExtend(n950Criteria.getExtend()));
				if(kq > 0)
					onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(n950Criteria.getExtend()), n950Criteria.getWebId(),null, fo100i);
				return kq;
			}
			return 0;
		}	
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return 0;
	}
}
