package com.ohhay.web.lego.serviceImpl;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.web.lego.entities.mongo.base.component.CMobileData;
import com.ohhay.web.lego.entities.mongo.base.component.Component;
import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.ohhay.web.lego.entities.mongo.web.E900MG;
import com.ohhay.web.lego.service.ComponentUtilsService;
import com.oohhay.web.lego.utils.WebLegoUtils;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH
 * create Nov 6, 2015
 */
@Service(value = SpringBeanNames.SERVICE_NAME_COMPONENTUTILS)
@Scope("prototype")
public class ComponentUtilsServiceImpl implements ComponentUtilsService{
	private static Logger log = Logger.getLogger(ComponentUtilsServiceImpl.class);
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private TemplateService templateService;
	@Override
	public int updateComponent(int fo100, long webId, long boxId, JSONObject component, Map<String, Long> returnData, int extend) {
		// TODO Auto-generated method stub
		try {
			log.info("---component:" + component);
			log.info("---update component for box id:"+ boxId);
			String stt = WebRule.WEB_PRO_STT_NO_CHANGE;
			try {
				if (component.getString(WebRule.WEB_PRO_STT) != null)
					stt = component.getString(WebRule.WEB_PRO_STT);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			String rawCompId = component.getString(WebRule.WEB_PRO_ID);
			/*
			 * add new component
			 */
			if (stt.equals(WebRule.WEB_PRO_STT_NEW)) {
				E900MG e900mg = new E900MG();
				long newCompId = sequenceService.getNextSequenceId(fo100, WebRule.getComponentMongoColectionFromExtend(extend));
				e900mg.setId(newCompId);
				e900mg.setCl948(new Date());
				setDataForComponent(e900mg, component, fo100, webId, boxId);
				templateService.saveDocument(fo100, e900mg, WebRule.getComponentMongoColectionFromExtend(extend));
				returnData.put(rawCompId, newCompId);
				rawCompId = String.valueOf(newCompId);
			}
			/*
			 * remove component
			 */
			else if (stt.equals(WebRule.WEB_PRO_STT_REMOVED)) {
				templateService.removeDocumentById(fo100, Integer.parseInt(rawCompId), WebRule.getComponentClassFromExtend(extend));
			}
			/*
			 * update component
			 */
			else if(stt.equals(WebRule.WEB_PRO_STT_UPDATE)){
				Component e900mg = (Component) templateService.findDocumentById(fo100, Integer.parseInt(rawCompId), WebRule.getComponentClassFromExtend(extend));
				setDataForComponent(e900mg, component, fo100, webId, boxId);
				templateService.saveDocument(fo100, e900mg, WebRule.getComponentMongoColectionFromExtend(extend));
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * @param comp
	 * @param component
	 * @param fo100
	 * @param webId
	 * @param boxId
	 */
	private void setDataForComponent(Component comp, JSONObject component, int fo100, long webId, long boxId){
		try{
			int h = component.getInt(WebRule.WEB_PRO_H);
			int w = component.getInt(WebRule.WEB_PRO_W);
			int x = component.getInt(WebRule.WEB_PRO_X);
			int y = component.getInt(WebRule.WEB_PRO_Y);
			String type = component.getString(WebRule.WEB_PRO_TYPE);
			//object style
			if(component.has(WebRule.WEB_PRO_CSS))
				comp.setCss(component.getString(WebRule.WEB_PRO_CSS));
			//background css
			if(component.has(WebRule.WEB_PRO_BGCSS))
				comp.setBgCss(component.getString(WebRule.WEB_PRO_BGCSS));
			//box background video
			BgWebBase bgVid = WebLegoUtils.getBgOgObject(component, WebRule.WEB_PRO_BG_VID);
			if(bgVid != null)
				comp.setBgVid(bgVid);
			//component name
			if(component.has(WebRule.WEB_PRO_NAME))
			{
				String name = component.getString(WebRule.WEB_PRO_NAME);
				comp.setName(name);
			}
			//onload effect
			if(component.has(WebRule.WEB_PRO_ONLOAD_EFFECT))
			{
				String effectName = component.getString(WebRule.WEB_PRO_ONLOAD_EFFECT);
				comp.setOnLoadEffect(effectName);
			}
			//widget eleent id
			if(component.has(WebRule.WEB_PRO_WIGET_ELEMENT_ID))
				comp.setWidgetEleId(component.getString(WebRule.WEB_PRO_WIGET_ELEMENT_ID));
			if(component.has(WebRule.WEB_PRO_GRID_INDEX))
			{
				int gridIndex = Integer.parseInt(component.getString(WebRule.WEB_PRO_GRID_INDEX));
				comp.setGridIndex(gridIndex);
			}
			comp.setCl946(new Date());
			comp.setH(h);
			comp.setW(w);
			comp.setX(x);
			comp.setY(y);
			comp.setFo100(fo100);
			comp.setWebId(webId);
			comp.setBoxId(boxId);
			comp.setType(type);
			comp.setData(component.getJSONObject(WebRule.WEB_PRO_DATA).toString());
			/*
			 * update 31/03/017
			 */
			if(component.has(WebRule.WEB_PRO_FE900))
				comp.setFe900(Integer.parseInt(component.getString(WebRule.WEB_PRO_FE900)));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
