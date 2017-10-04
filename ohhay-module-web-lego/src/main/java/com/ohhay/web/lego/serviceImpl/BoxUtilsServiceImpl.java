package com.ohhay.web.lego.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.web.lego.entities.mongo.base.box.Box;
import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.ohhay.web.lego.entities.mongo.web.E920MG;
import com.ohhay.web.lego.entities.mongo.web.E950MG;
import com.ohhay.web.lego.service.BoxUtilsService;
import com.oohhay.web.lego.utils.WebLegoUtils;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH
 * create Nov 6, 2015
 */
@Service(value = SpringBeanNames.SERVICE_NAME_BOXUTILS)
@Scope("prototype")
public class BoxUtilsServiceImpl implements BoxUtilsService{
	private static Logger log = Logger.getLogger(BoxUtilsServiceImpl.class);
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private TemplateService templateService;
	@Override
	public int updateBox(int fo100, long webId, JSONObject box, Map<String, Long> returnData, int extend) {
		// TODO Auto-generated method stub
		try {
			log.info("---box:" + box);
			/*
			 * get status
			 */
			String stt = WebRule.WEB_PRO_STT_NO_CHANGE;
			try {
				if (box.getString(WebRule.WEB_PRO_STT) != null)
					stt = box.getString(WebRule.WEB_PRO_STT);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			String rawBoxId = box.getString(WebRule.WEB_PRO_ID);
			/*
			 * add new box
			 */
			if (stt.equals(WebRule.WEB_PRO_STT_NEW)) {
				Box e920mg = new Box();
				long newBoxId = sequenceService.getNextSequenceId(fo100, WebRule.getBoxMongoColectionFromExtend(extend));
				e920mg.setId(newBoxId);
				e920mg.setBl948(new Date());
				setDataForBox(e920mg, box, fo100, webId);
				templateService.saveDocument(fo100, e920mg, WebRule.getBoxMongoColectionFromExtend(extend));
				returnData.put(rawBoxId, newBoxId);
				rawBoxId = String.valueOf(newBoxId);
			}
			/*
			 * remove box
			 */
			else if (stt.equals(WebRule.WEB_PRO_STT_REMOVED)) {
				templateService.removeDocumentById(fo100, Integer.parseInt(rawBoxId), WebRule.getBoxClassFromExtend(extend));
			}
			/*
			 * update box
			 */
			else if (stt.equals(WebRule.WEB_PRO_STT_UPDATE)) {
				Box e920mg = (Box) templateService.findDocumentById(fo100, Integer.parseInt(rawBoxId), WebRule.getBoxClassFromExtend(extend));
				setDataForBox(e920mg, box, fo100, webId);
				templateService.saveDocument(fo100, e920mg, WebRule.getBoxMongoColectionFromExtend(extend));
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	/**
	 * @param mBox
	 * @param box
	 * @param fo100
	 * @param webID
	 */
	private void setDataForBox(Box mBox, JSONObject box, int fo100, long webID){
		try {
			//box height
			int h = box.getInt(WebRule.WEB_PRO_H);
			//box index
			int index = box.getInt(WebRule.WEB_PRO_INDEX);
			//box css
			if(box.has(WebRule.WEB_PRO_CSS))
				mBox.setCss(box.getString(WebRule.WEB_PRO_CSS));
			//box background css
			if(box.has(WebRule.WEB_PRO_BGCSS))
				mBox.setBgCss(box.getString(WebRule.WEB_PRO_BGCSS));
			//box background video
			BgWebBase bgVid = WebLegoUtils.getBgOgObject(box, WebRule.WEB_PRO_BG_VID);
			if(bgVid != null)
				mBox.setBgVid(bgVid);
			//grid css
			if(box.has(WebRule.WEB_PRO_GRID_CSS))
				mBox.setGridCss(box.getString(WebRule.WEB_PRO_GRID_CSS));
			//grid background css
			if(box.has(WebRule.WEB_PRO_GRID_BGCSS))
				mBox.setGridBgCss(box.getString(WebRule.WEB_PRO_GRID_BGCSS));
			//grid background video
			BgWebBase gridBgVid = WebLegoUtils.getBgOgObject(box, WebRule.WEB_PRO_GRID_BG_VID);
			if(gridBgVid != null)
				mBox.setGridBgVid(gridBgVid);
			//box name
			if(box.has(WebRule.WEB_PRO_NAME))
			{
				String name = box.getString(WebRule.WEB_PRO_NAME);
				mBox.setName(name);
			}
			//box name
			if(box.has(WebRule.WEB_PRO_FE920))
			{
				long fe920 = Long.parseLong(box.getString(WebRule.WEB_PRO_FE920));
				mBox.setFe920(fe920);
			}
			if(box.has(WebRule.WEB_PRO_LIBTYPE))
			{
				int libType = Integer.parseInt(box.getString(WebRule.WEB_PRO_LIBTYPE));
				mBox.setLibType(libType);
			}
			if(box.has(WebRule.WEB_PRO_DATA))
				mBox.setData(box.getString(WebRule.WEB_PRO_DATA));
			if(box.has(WebRule.WEB_PRO_WIGETNAME))
				mBox.setWidgetName(box.getString(WebRule.WEB_PRO_WIGETNAME));
			if(box.has(WebRule.WEB_PRO_RE_TYPE))
			{
				int reType = Integer.parseInt(box.getString(WebRule.WEB_PRO_RE_TYPE));
				mBox.setReType(reType);
			}
			//set data
			mBox.setBl946(new Date());
			mBox.setH(h);
			mBox.setFo100(fo100);
			mBox.setWebId(webID);
			mBox.setIndex(index);
			mBox.setType(box.getString(WebRule.WEB_PRO_TYPE));
			//set status NEW for library follow only
			if((mBox.getLibType() == 1 || mBox.getLibType() == 2 ) && mBox.getEditAble() == 1)
				mBox.setLibStt("NEW");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	public int updateBoxUsingLib(E920MG e920mgLib) {
		// TODO Auto-generated method stub
		/*
		 * 0) find section using this lib
		 */
		List<E920MG> listE920mgs = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E920MG.class, new QbCriteria(QbMongoFiledsName.FE920, e920mgLib.getId()));
		try {
			/*
			 * 1) get section html
			 */
			E950MG e950lib = templateService.findDocument(e920mgLib.getFo100(), E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, e920mgLib.getWebId()));
			Document documentLib = Jsoup.parse(e950lib.getEv951());
			Element lib = documentLib.select(".content-box[qb-box-id="+e920mgLib.getId()+"]").get(0);
			/*
			 * 2) replace HTML of using library site
			 */
			for(E920MG e920mg: listE920mgs){
				E950MG e950mg = templateService.findDocument(e920mg.getFo100(), E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, e920mg.getWebId()));
				Document documentSection = Jsoup.parse(e950mg.getEv951());
				if(e950mg != null){
					Element section = documentSection.select(".content-box[qb-box-id="+e920mg.getId()+"]").get(0);
					section.html(lib.html());
					section.attr("qb-gird-h",lib.attr("qb-gird-h"));
					section.select(".layer-background.bigbox").attr("style",e920mg.getBgCss());
					templateService.updateOneField(e950mg.getFo100(), E950MG.class, (int)e950mg.getId(), QbMongoFiledsName.EV951, documentSection.html(), null);
				}
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int updateBoxUsingLibMo(E920MG e920mgLib) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1) get section html
			 */
			E950MG e950lib = templateService.findDocument(e920mgLib.getFo100(), E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, e920mgLib.getWebId()));
			Document documentLib = Jsoup.parse(e950lib.getEv953());
			Element lib = documentLib.select(".content-box[qb-box-id="+e920mgLib.getId()+"]").get(0);
			/*
			 * 2) replace HTML of using library site
			 */
			List<E920MG> listE920mgs = templateService.findDocuments(ApplicationConstant.FO100_SUPER_ADMIN, E920MG.class, new QbCriteria(QbMongoFiledsName.FE920, e920mgLib.getId()));
			for(E920MG e920mg: listE920mgs){
				E950MG e950mg = templateService.findDocument(e920mg.getFo100(), E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, e920mg.getWebId()));
				Document documentSection = Jsoup.parse(e950mg.getEv953());
				if(e950mg != null){
					Element section = documentSection.select(".content-box[qb-box-id="+e920mg.getId()+"]").get(0);
					section.html(lib.html());
					section.attr("qb-gird-h",lib.attr("qb-gird-h"));//cap nhat chieu cao
					templateService.updateOneField(e950mg.getFo100(), E950MG.class, (int)e950mg.getId(), QbMongoFiledsName.EV953, documentSection.html(), null);
				}
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
}
