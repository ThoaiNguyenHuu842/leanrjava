package com.ohhay.web.editor.tools;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.history.WebEditedInfo;
import com.ohhay.web.core.history.WebHistoryManager;
import com.ohhay.web.core.load.util.AbstractEditor;
import com.ohhay.web.core.mongo.service.C900MGService;
import com.ohhay.web.core.utils.WebTemplateRule;

/**
 * @author ThoaiNH
 * create 05/10/2015
 * save resizeable, draggable, css of element, form send mail...
 */
@Service
@Scope("prototype")
public class InfoExtraEditor extends AbstractEditor{
	private static Logger log = Logger.getLogger(InfoExtraEditor.class);
	/**
	 * luu cac thong tin dinh kem cua 1 element
	 * nhu stype css, email cua form gui mail...
	 * chua lam su kien undo, redo cho luu form gui mail (CV116)
	 * @param extend
	 * @param pc900
	 * @param currentLanguage
	 * @param info
	 * @param fo100
	 * @return
	 */
	public boolean saveElementInfo(int extend,String pc900, String currentLanguage, String info, int fo100, String fieldName, String oldInfo){
		try{
			C900MGService c900mgService = (C900MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C900MG);
			int webId = 0;
			int indexProperty = -1;
			String[] ids = pc900.split("#");
			webId = Integer.parseInt(ids[0]);
			indexProperty = Integer.parseInt(ids[3]);
			//fo100 user to know insert db center or user db
			int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100;
			if(c900mgService.updateEleme100(String.valueOf(webId),currentLanguage, indexProperty,fieldName,info, WebTemplateRule.getWebLanguageClassFromExtend(extend),fo100, fo100i) > 0 )
			{
				log.info("---onEditWebSuccess:"+WebTemplateRule.getWebClassFromExtend(extend)+","+webId+","+currentLanguage);
				onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(extend),webId,currentLanguage, fo100i);
				/*
				 * save to history
				 */
				if(fieldName.equals(QbMongoFiledsName.CV115)){
					WebEditedInfo oldEditedInfo = new WebEditedInfo(4,oldInfo,webId, extend, indexProperty, currentLanguage);
					WebEditedInfo newEditedInfo = new WebEditedInfo(4,info,webId, extend, indexProperty, currentLanguage);
					WebHistoryManager.addNewItem(oldEditedInfo, newEditedInfo);
					WebHistoryManager.test();
				}
			}
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
