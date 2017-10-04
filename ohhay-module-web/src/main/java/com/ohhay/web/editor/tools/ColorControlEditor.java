package com.ohhay.web.editor.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.history.WebEditedInfo;
import com.ohhay.web.core.history.WebHistoryManager;
import com.ohhay.web.core.load.util.AbstractEditor;
import com.ohhay.web.core.utils.WebTemplateRule;

/**
 * @author ThoaiNH
 * create 05/10/2015
 */
@Service
@Scope("prototype")
public class ColorControlEditor extends AbstractEditor{
	@Autowired
	@Qualifier(value = SpringBeanNames.SERVICE_NAME_TEMPLATE)
	private TemplateService templateService;
	/**
	 * save all type color
	 */
	public boolean saveWebColor(int fo100, int webId, int extend, String color) throws Exception {
		//fo100 user to know insert db center or user db
		int fo100i = (extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || extend == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD) ? 0: fo100;
		OhhayWebBase ohhayWebBase = (OhhayWebBase) templateService.findAndModify(fo100i, WebTemplateRule.getWebClassFromExtend(extend),webId,QbMongoFiledsName.CV807, color, null);
		if(ohhayWebBase != null){
			/*
			 * save to history stack
			 * color is not store in language so must save to history for all language 
			 */
			for(LanguageMG languageMG: ohhayWebBase.getListC500mg()){
				WebEditedInfo webEditedInfo = new WebEditedInfo(2,ohhayWebBase.getCv807(),webId, extend,0,languageMG.getCode());
				WebHistoryManager.addNewItem(webEditedInfo, new WebEditedInfo(2,color,webId, extend,0,languageMG.getCode()));
			}
			WebHistoryManager.test();
			onEditWebSuccess(WebTemplateRule.getWebClassFromExtend(extend),webId, null, fo100i);
			return true;
		}
		return false;
	}
}
