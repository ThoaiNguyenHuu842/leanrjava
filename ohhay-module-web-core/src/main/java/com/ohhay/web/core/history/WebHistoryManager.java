package com.ohhay.web.core.history;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.SessionConstant;
import com.ohhay.web.core.entities.mongo.webbase.G920MG;
import com.ohhay.web.core.mongo.service.C900MGService;
import com.ohhay.web.core.utils.WebTemplateRule;

/**
 * @author ThoaiNH
 * date create: 02/12/2014
 * manager history edit web
 */
public class WebHistoryManager {
	private static Logger log = Logger.getLogger(WebHistoryManager.class);

	public static void test() {
		Map<String, WebHistoryStack> mapWebHistory = (Map<String, WebHistoryStack>) ApplicationHelper
				.getSession(SessionConstant.WEB_EDIT_HISTORY);
		if (mapWebHistory == null) {
			log.info("---web history is null");
		}
		else {
			for (Map.Entry<String, WebHistoryStack> entry : mapWebHistory
					.entrySet()) {
				log.info("---web history key:" + entry.getKey());
				WebHistoryStack historyStack = entry.getValue();
				for (WebEditedInfo webEditedInfo : historyStack
						.getStackUndo()) {
					log.info("-----------web history edit info:"
							+ webEditedInfo);
				}
			}
		}
	}
	/*
	 * get total redo step
	 */
	public static int getTotalRedoStep(String key, int fo100) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, WebHistoryStack> mapWebHistory = (Map<String, WebHistoryStack>) ApplicationHelper
					.getSession(SessionConstant.WEB_EDIT_HISTORY);
			if (mapWebHistory != null && key != null) {
				WebHistoryStack webHistoryStack = mapWebHistory.get(key);
				if (webHistoryStack != null
						&& webHistoryStack.getStackRedo() != null)
					return webHistoryStack.getStackRedo().size();
			}
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	/*
	 * get total undo step
	 */
	public static int getTotalUndoStep(String key, int fo100) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, WebHistoryStack> mapWebHistory = (Map<String, WebHistoryStack>) ApplicationHelper
					.getSession(SessionConstant.WEB_EDIT_HISTORY);
			if (mapWebHistory != null && key != null) {
				WebHistoryStack webHistoryStack = mapWebHistory.get(key);
				if (webHistoryStack != null
						&& webHistoryStack.getStackUndo() != null)
					return webHistoryStack.getStackUndo().size();
			}
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	public static int revert(WebEditedInfo webEditedInfo, int fo100){
		C900MGService c900mgService = (C900MGService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_C900MG);
		//fo100 user to know insert db center or user db
		int fo100i = (webEditedInfo.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE || webEditedInfo.getExtend() == OhhayWebType.WEBTYPE_OHHAY_TEMPLATE_CHILD)?0:fo100;
		switch (webEditedInfo.getType()) {
		//c100 storage type
		case 1:
			return c900mgService
					.updateEleme100(String
							.valueOf(webEditedInfo.getWebId()), webEditedInfo
							.getLanguageCode(), webEditedInfo
							.getIndexProperty(), QbMongoFiledsName.CV112, webEditedInfo
							.getValue().toString(), WebTemplateRule
							.getWebLanguageClassFromExtend(webEditedInfo
									.getExtend()), fo100, fo100i);
	    //color type
		case 2:
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			return templateService.updateOneField(fo100, WebTemplateRule.getWebClassFromExtend(webEditedInfo
					.getExtend()), webEditedInfo.getWebId() ,QbMongoFiledsName.CV807, webEditedInfo
					.getValue(), null);
	    //gmap 
		case 3:
			G920MG g920mg = (G920MG) webEditedInfo.getValue();
			c900mgService.updatePart(webEditedInfo.getWebId(), webEditedInfo.getIndexProperty(), "GMAP.GN923",g920mg.getGn923(), WebTemplateRule.getWebClassFromExtend(webEditedInfo.getExtend()),fo100, fo100i);
			c900mgService.updatePart(webEditedInfo.getWebId(), webEditedInfo.getIndexProperty(), "GMAP.GN924",g920mg.getGn924(), WebTemplateRule.getWebClassFromExtend(webEditedInfo.getExtend()),fo100, fo100i);
			c900mgService.updatePart(webEditedInfo.getWebId(), webEditedInfo.getIndexProperty(), "GMAP.GV922",g920mg.getGv922(), WebTemplateRule.getWebClassFromExtend(webEditedInfo.getExtend()),fo100, fo100i);
			c900mgService.updatePart(webEditedInfo.getWebId(), webEditedInfo.getIndexProperty(), "GMAP.GV921",g920mg.getGv921(), WebTemplateRule.getWebClassFromExtend(webEditedInfo.getExtend()),fo100, fo100i);
			return 1;
		//position
		case 4:
			return c900mgService
					.updateEleme100(String
							.valueOf(webEditedInfo.getWebId()), webEditedInfo
							.getLanguageCode(), webEditedInfo
							.getIndexProperty(), QbMongoFiledsName.CV115, webEditedInfo
							.getValue().toString(), WebTemplateRule
							.getWebLanguageClassFromExtend(webEditedInfo
									.getExtend()), fo100, fo100i);
		//background repeat
		case 5:
			TemplateService templateService2 = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			return templateService2.updateOneField(fo100, WebTemplateRule.getWebClassFromExtend(webEditedInfo
					.getExtend()), webEditedInfo.getWebId() ,QbMongoFiledsName.CV809, webEditedInfo
					.getValue(), null);
		default:
			break;
		}
		return 0;
	}
	/*
	 * redo current web (key is md5(extend+wenid+languageCode))
	 */
	public static int redo(String key, int fo100) {
		try {
			
			@SuppressWarnings("unchecked")
			Map<String, WebHistoryStack> mapWebHistory = (Map<String, WebHistoryStack>) ApplicationHelper
					.getSession(SessionConstant.WEB_EDIT_HISTORY);
			if (mapWebHistory != null && key != null) {
				WebHistoryStack webHistoryStack = mapWebHistory.get(key);
				if (webHistoryStack != null) {
					WebEditedInfo webEditedInfo = webHistoryStack
							.getStackRedo().pop();
					//push to undo stack
					webHistoryStack.getStackUndo().push(webEditedInfo);
					log.info("--pop history:" + webEditedInfo);
					if (webEditedInfo != null) {
						return revert(webEditedInfo, fo100);
					}
				}
			}
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	/*
	 * undo current web (key is md5(extend+wenid+languageCode))
	 */
	public static int undo(String key, int fo100) {
		try {
			
			@SuppressWarnings("unchecked")
			Map<String, WebHistoryStack> mapWebHistory = (Map<String, WebHistoryStack>) ApplicationHelper
					.getSession(SessionConstant.WEB_EDIT_HISTORY);
			if (mapWebHistory != null && key != null) {
				WebHistoryStack webHistoryStack = mapWebHistory.get(key);
				if (webHistoryStack != null) {
					WebEditedInfo webEditedInfo = webHistoryStack
							.getStackUndo().pop();
					//push to redo stack
					webHistoryStack.getStackRedo().push(webHistoryStack.getNewestInfo());
					log.info("--pop history:" + webEditedInfo);
					if (webEditedInfo != null) {
						return revert(webEditedInfo, fo100);
					}
				}
			}
			return 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	/**
	 * @param info: info before edit
	 * @param webNewestInfo: info after edit
	 */
	public static void addNewItem(WebEditedInfo info, WebEditedInfo webNewestInfo) {
		@SuppressWarnings("unchecked")
		Map<String, WebHistoryStack> mapWebHistory = (Map<String, WebHistoryStack>) ApplicationHelper
				.getSession(SessionConstant.WEB_EDIT_HISTORY);
		if (mapWebHistory == null)
			mapWebHistory = new HashMap<String, WebHistoryStack>();
		/*
		 * 1) find history of current web edit
		 */
		String crKey = ApplicationHelper.convertToMD5(info.getExtend()
				+ info.getWebId() + info.getLanguageCode());
		WebHistoryStack webHistory = mapWebHistory.get(crKey);
		if (webHistory != null) {
			// push new history item to web history
			webHistory.pushToUnDoStack(info);
			webHistory.setNewestInfo(webNewestInfo);
		}
		else {
			// create web history and push new history item to web history
			WebHistoryStack webHistoryStack = new WebHistoryStack();
			webHistoryStack.setKey(crKey);
			webHistoryStack.pushToUnDoStack(info);
			webHistoryStack.setNewestInfo(webNewestInfo);
			mapWebHistory.put(crKey, webHistoryStack);
		}
		/*
		 * 2) save to session
		 */
		ApplicationHelper.setSession(SessionConstant.WEB_EDIT_HISTORY, mapWebHistory);

	}
}
