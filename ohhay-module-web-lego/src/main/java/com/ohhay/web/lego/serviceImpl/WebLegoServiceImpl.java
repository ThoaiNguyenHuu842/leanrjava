package com.ohhay.web.lego.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.entities.N100CentPMG;
import com.ohhay.core.authentication.AuthenticationRightInfo;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.filesutil.EvoAWSFileUtils;
import com.ohhay.core.mongo.service.SequenceService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.other.api.dao.B100KubDao;
import com.ohhay.web.core.mongo.service.E400MGService;
import com.ohhay.web.lego.entities.mongo.base.box.BMobileData;
import com.ohhay.web.lego.entities.mongo.base.box.Box;
import com.ohhay.web.lego.entities.mongo.base.box.BoxTrial;
import com.ohhay.web.lego.entities.mongo.base.component.CMobileData;
import com.ohhay.web.lego.entities.mongo.base.component.Component;
import com.ohhay.web.lego.entities.mongo.base.component.ComponentTrial;
import com.ohhay.web.lego.entities.mongo.base.web.BgWebBase;
import com.ohhay.web.lego.entities.mongo.base.web.ConfigWebBase;
import com.ohhay.web.lego.entities.mongo.base.web.ElementCrId;
import com.ohhay.web.lego.entities.mongo.base.web.N950MG;
import com.ohhay.web.lego.entities.mongo.base.web.OhhayLegoWebBase;
import com.ohhay.web.lego.entities.mongo.topic.ET400MG;
import com.ohhay.web.lego.entities.mongo.topic.ET900MG;
import com.ohhay.web.lego.entities.mongo.topic.ET920MG;
import com.ohhay.web.lego.entities.mongo.web.E400MG;
import com.ohhay.web.lego.entities.mongo.web.E900MG;
import com.ohhay.web.lego.entities.mongo.web.E920MG;
import com.ohhay.web.lego.entities.mongo.web.E950MG;
import com.ohhay.web.lego.service.BoxUtilsService;
import com.ohhay.web.lego.service.ComponentUtilsService;
import com.ohhay.web.lego.service.WebLegoService;
import com.ohhay.web.lego.service.WebUtilsService;
import com.oohhay.web.lego.utils.WebRule;

/**
 * @author ThoaiNH 
 * create Oct 13, 2015
 * 
 */
@Service(value = SpringBeanNames.SERVICE_NAME_WEBLEGO)
@Scope("prototype")
public class WebLegoServiceImpl implements WebLegoService {
	private static Logger log = Logger.getLogger(WebLegoServiceImpl.class);
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private WebUtilsService webUtilsService;
	@Autowired
	private BoxUtilsService boxUtilsService;
	@Autowired
	private ComponentUtilsService componentUtilsService;
	@Autowired
	private E400MGService e400mgService;
	@Override
	public <T> OhhayLegoWebBase createWebEvo(int fo100, String hv101, boolean isTopicContent, long templateID) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1) create E400
			 */
			OhhayLegoWebBase e400mg = new OhhayLegoWebBase();
			e400mg.setEl446(new Date());
			e400mg.setEl448(new Date());
			e400mg.setFo100(fo100);
			e400mg.setHv101(hv101);
			e400mg.setFe400(templateID);
			// background
			BgWebBase bgWebBase = new BgWebBase();
			bgWebBase.setType("color");
			bgWebBase.setData("#ffffff");
			e400mg.setBg(bgWebBase);
			// config
			ConfigWebBase configWebBase = new ConfigWebBase();
			configWebBase.setMgB(0);
			configWebBase.setMgL(-1);// center
			configWebBase.setMgT(0);
			configWebBase.setW(1170);
			e400mg.setConfig(configWebBase);
			// element id
			ElementCrId elementCrId = new ElementCrId();
			elementCrId.setcFormId(1);
			elementCrId.setrFormId(1);
			e400mg.setElementCrId(elementCrId);
			/*
			 * 2) save to DB when create web EVO
			 */
			if(!isTopicContent)
			{
				long webId = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E400);
				e400mg.setId(webId);
				templateService.saveDocument(fo100, e400mg, QbMongoCollectionsName.E400);
				//create E950
				long e950Id = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E950);
				E950MG e950mg = new E950MG();
				e950mg.setFo100(fo100);
				e950mg.setWebId(webId);
				e950mg.setId(e950Id);
				templateService.saveDocument(fo100, e950mg, QbMongoCollectionsName.E950);			
			}
			return e400mg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OhhayLegoWebBase getWebEvoData(int fo100, int pe400) {
		// TODO Auto-generated method stub
		E400MG e400mg = templateService.findDocument(fo100, E400MG.class, new QbCriteria(QbMongoFiledsName.ID, pe400));
		if(e400mg != null){
			//load boxs and components
			List<E920MG> listE920 = templateService.findDocuments(ApplicationConstant.FO100_ADMIN_TEMPLATE, E920MG.class, 
					new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
			//Collections.sort(listE920);
			List<E900MG> listE900 = templateService.findDocuments(ApplicationConstant.FO100_ADMIN_TEMPLATE, E900MG.class, 
					new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
			//create map data
			Map<Object, Object> mapBox = new LinkedHashMap<Object, Object>();
			Map<Object, Object> mapComponent = new LinkedHashMap<Object, Object>();
			for(E900MG e900mg: listE900)
				mapComponent.put(new Long(e900mg.getId()), e900mg);
			for(E920MG e920mg: listE920)
			{
				//add components of lib box
				if(e920mg.getFe920() != 0 && e920mg.getLibType() == 3){
					E920MG e920mgOrg = templateService.findDocument(fo100, E920MG.class, new QbCriteria(QbMongoFiledsName.ID, e920mg.getFe920()));
					if(e920mgOrg != null){
						//get grid layout of original box
						e920mg.setGridBgCss(e920mgOrg.getGridBgCss());
						e920mg.setGridBgVid(e920mgOrg.getGridBgVid());
						e920mg.setGridCss(e920mgOrg.getGridCss());
						List<E900MG> listE900Lib = templateService.findDocuments(ApplicationConstant.FO100_ADMIN_TEMPLATE, E900MG.class, 
								new QbCriteria(QbMongoFiledsName.BOX_ID, e920mg.getFe920()));
						for(E900MG e900mg: listE900Lib)
						{
							e900mg.setBoxId(e920mg.getId());
							mapComponent.put(new Long(e900mg.getId()), e900mg);
						}
						//update height (will fix)
						e920mg.setH(e920mgOrg.getH());
						//ThoaiNH update: 08/07/2016 get mobile height of orginal section
						try {
							if(e920mgOrg.getmData() != null)
							{
								if(e920mg.getmData() != null)
									e920mg.getmData().setH(e920mgOrg.getmData().getH());
								else {
									e920mg.setmData(e920mgOrg.getmData());
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						mapBox.put(new Long(e920mg.getId()), e920mg);
					}
				}
				else
					mapBox.put(new Long(e920mg.getId()), e920mg);
			}
			/*
			 * Thong: load footer and header
			 */
			/*E920MG headerBox = templateService.findDocument(ApplicationConstant.FO100_ADMIN_TEMPLATE, E920MG.class, 
					new QbCriteria(QbMongoFiledsName.FO100, e400mg.getFo100()),
					new QbCriteria(QbMongoFiledsName.TYPE, "header"));
			List<E900MG> headerComponent = new ArrayList<E900MG>();
			if(headerBox != null)
				headerComponent = templateService.findDocuments(ApplicationConstant.FO100_ADMIN_TEMPLATE, E900MG.class,
					new QbCriteria(QbMongoFiledsName.FO100, e400mg.getFo100()),
					new QbCriteria(QbMongoFiledsName.BOX_ID, headerBox.getId()));
			E920MG footerBox = templateService.findDocument(ApplicationConstant.FO100_ADMIN_TEMPLATE, E920MG.class, 
					new QbCriteria(QbMongoFiledsName.FO100, e400mg.getFo100()),
					new QbCriteria(QbMongoFiledsName.TYPE, "footer"));
			List<E900MG> footerComponent = new ArrayList<E900MG>();
			if(footerBox != null)
				footerComponent = templateService.findDocuments(ApplicationConstant.FO100_ADMIN_TEMPLATE, E900MG.class,
					new QbCriteria(QbMongoFiledsName.FO100, e400mg.getFo100()),
					new QbCriteria(QbMongoFiledsName.BOX_ID, footerBox.getId()));
			for(E900MG comp: headerComponent)
				if(comp.getWebId()!=pe400)
					mapComponent.put(new Long(comp.getId()), comp);
			
			if(headerBox.getWebId()!=pe400)
					mapBox.put(new Long(headerBox.getId()), headerBox);
			
			for(E900MG comp: footerComponent)
				if(comp.getWebId()!=pe400)
					mapComponent.put(new Long(comp.getId()), comp);
			
			if(footerBox.getWebId()!=pe400)
					mapBox.put(new Long(footerBox.getId()), footerBox);*/
			e400mg.setListBox(mapBox);
			e400mg.setListComponent(mapComponent);
			return e400mg;
		}
		return null;
	}

	@Override
	public OhhayLegoWebBase getWebTopicData(int fo100, int fm150) {
		// TODO Auto-generated method stub
		ET400MG e400mg = templateService.findDocument(fo100, ET400MG.class,new QbCriteria(QbMongoFiledsName.FM150, fm150));
		if(e400mg != null){
			//load boxs and components
			List<ET920MG> listE920 = templateService.findDocuments(fo100, ET920MG.class, 
					new QbCriteria(QbMongoFiledsName.FO100, fo100), 
					new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
			//Collections.sort(listE920);
			List<ET900MG> listE900 = templateService.findDocuments(fo100, ET900MG.class, 
					new QbCriteria(QbMongoFiledsName.FO100, fo100), 
					new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
			//create map data
			Map<Object, Object> mapBox = new LinkedHashMap<Object, Object>();
			Map<Object, Object> mapComponent = new LinkedHashMap<Object, Object>();
			for(ET900MG e900mg: listE900)
				mapComponent.put(new Long(e900mg.getId()), e900mg);
			for(ET920MG e920mg: listE920)
				mapBox.put(new Long(e920mg.getId()), e920mg);
			e400mg.setListBox(mapBox);
			e400mg.setListComponent(mapComponent);
			return e400mg;
		}
		return null;
	}
	@Override
	public int remove(int fo100, int pe400, int extend) {
		// TODO Auto-generated method stub
		try {
			templateService.removeDocumentById(fo100, pe400, WebRule.getWebClassFromExtend(extend));
			templateService.removeDocuments(fo100, WebRule.getBoxClassFromExtend(extend), new QbCriteria(QbMongoFiledsName.WEBID, pe400));
			templateService.removeDocuments(fo100, WebRule.getComponentClassFromExtend(extend), new QbCriteria(QbMongoFiledsName.WEBID, pe400));
			templateService.removeDocuments(fo100, WebRule.getWebShortClassFromExtend(extend), new QbCriteria(QbMongoFiledsName.WEBID, pe400));
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int updateSiteName(int fo100, int webId, int extend, String siteName) {
		// TODO Auto-generated method stub
		try {
			if(siteName != null && !siteName.isEmpty()){
				templateService.updateOneField(fo100, WebRule.getWebShortClassFromExtend(extend), QbMongoFiledsName.EV952, siteName, null, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, webId));
				templateService.updateOneField(fo100, WebRule.getWebClassFromExtend(extend), QbMongoFiledsName.EV401, siteName, null, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.ID, webId));
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int saveMobileVersion(int fo100, long webId, String html, String jsonData, String apiCompSelector, String editToolSelector, int extend) {
		// TODO Auto-generated method stub
		try{
			JSONObject webData = new JSONObject(jsonData);
			/*
			 * 1) save box
			 */
			if(webData.has(WebRule.WEB_PRO_LISTBOX)){
				JSONObject mapBox = webData.getJSONObject(WebRule.WEB_PRO_LISTBOX);
				if(mapBox.names() != null && mapBox.names().length() > 0){
					for (int i = 0; i < mapBox.names().length(); i++) {
						JSONObject box = mapBox.getJSONObject(mapBox.names().getString(i));
						String rawBoxId = box.getString(WebRule.WEB_PRO_ID);
						Box e920mg = (Box) templateService.findDocumentById(fo100, Integer.parseInt(rawBoxId), WebRule.getBoxClassFromExtend(extend));
						//mobile data
						try {
							if(box.has(WebRule.WEB_PRO_MDATA)){
								JSONObject mData = box.getJSONObject(WebRule.WEB_PRO_MDATA);
								BMobileData bMobileData = new BMobileData();
								if(mData.has(WebRule.WEB_PRO_H) && !mData.getString(WebRule.WEB_PRO_H).equals("null") && mData.getString(WebRule.WEB_PRO_H).length() > 0)
								{
									try {
										bMobileData.setH(Integer.parseInt((mData.getString(WebRule.WEB_PRO_H))));
									} catch (Exception ex) {
										// TODO: handle exception
										ex.printStackTrace();
									}
								}
								if(mData.has(WebRule.WEB_PRO_HIDE) && !mData.getString(WebRule.WEB_PRO_HIDE).equals("null") && mData.getString(WebRule.WEB_PRO_HIDE).length() > 0)
								{
									try {
										bMobileData.setHide(Boolean.parseBoolean(mData.getString(WebRule.WEB_PRO_HIDE)));
									} catch (Exception ex) {
										// TODO: handle exception
										ex.printStackTrace();
									}
								}
								if(mData.has(WebRule.WEB_PRO_BGCSS) && !mData.getString(WebRule.WEB_PRO_BGCSS).equals("null") && mData.getString(WebRule.WEB_PRO_BGCSS).length() > 0)
								{
									try {
										bMobileData.setBgCss(mData.getString(WebRule.WEB_PRO_BGCSS));
									} catch (Exception ex) {
										// TODO: handle exception
										ex.printStackTrace();
									}
								}
								if(mData.has(WebRule.WEB_PRO_GRID_BGCSS) && !mData.getString(WebRule.WEB_PRO_GRID_BGCSS).equals("null") && mData.getString(WebRule.WEB_PRO_GRID_BGCSS).length() > 0)
								{
									try {
										bMobileData.setGridBgCss(mData.getString(WebRule.WEB_PRO_GRID_BGCSS));
									} catch (Exception ex) {
										// TODO: handle exception
										ex.printStackTrace();
									}
								}
								if(mData.has(WebRule.WEB_PRO_CSS) && !mData.getString(WebRule.WEB_PRO_CSS).equals("null") && mData.getString(WebRule.WEB_PRO_CSS).length() > 0)
								{
									try {
										bMobileData.setCss(mData.getString(WebRule.WEB_PRO_CSS));
									} catch (Exception ex) {
										// TODO: handle exception
										ex.printStackTrace();
									}
								}
								if(mData.has(WebRule.WEB_PRO_GRID_CSS) && !mData.getString(WebRule.WEB_PRO_GRID_CSS).equals("null") && mData.getString(WebRule.WEB_PRO_GRID_CSS).length() > 0)
								{
									try {
										bMobileData.setGridCss(mData.getString(WebRule.WEB_PRO_GRID_CSS));
									} catch (Exception ex) {
										// TODO: handle exception
										ex.printStackTrace();
									}
								}
								e920mg.setmData(bMobileData);
							}
						} catch (JSONException e) {
							// TODO: handle exception
							log.info("---mData in not json object");
						}
						//set data
						e920mg.setBl946(new Date());
						if((e920mg.getLibType() == 1 || e920mg.getLibType() == 2 ) && e920mg.getEditAble() == 1)
							e920mg.setLibSttMo("NEW");
						templateService.saveDocument(fo100, e920mg, WebRule.getBoxMongoColectionFromExtend(extend));
					}
				}
			}
			/*
			 * 2) save component
			 */
			if(webData.has(WebRule.WEB_PRO_LISTCOMP)){
				JSONObject mapComponent = webData.getJSONObject(WebRule.WEB_PRO_LISTCOMP);
				if(mapComponent.names() != null && mapComponent.names().length() > 0){
					for(int i=0; i < mapComponent.names().length(); i++){
						JSONObject component = mapComponent.getJSONObject(mapComponent.names().getString(i));
						String rawCompId = component.getString(WebRule.WEB_PRO_ID);
						Component e900mg = (Component) templateService.findDocumentById(fo100, Integer.parseInt(rawCompId), WebRule.getComponentClassFromExtend(extend));
						//mobile data
						try{
							if(component.has(WebRule.WEB_PRO_MDATA)){
								JSONObject mData = component.getJSONObject(WebRule.WEB_PRO_MDATA);
								CMobileData cMobileData = new CMobileData();
								if(mData.has(WebRule.WEB_PRO_W)){
									cMobileData.setH(mData.getInt(WebRule.WEB_PRO_H));
									cMobileData.setW(mData.getInt(WebRule.WEB_PRO_W));
									cMobileData.setX(mData.getInt(WebRule.WEB_PRO_X));
									cMobileData.setY(mData.getInt(WebRule.WEB_PRO_Y));
								}
								if(mData.has(WebRule.WEB_PRO_CSS) && !mData.getString(WebRule.WEB_PRO_CSS).equals("null") && mData.getString(WebRule.WEB_PRO_CSS).length() > 0)
									cMobileData.setCss(mData.getString(WebRule.WEB_PRO_CSS));
								if(mData.has(WebRule.WEB_PRO_HIDE) && !mData.getString(WebRule.WEB_PRO_HIDE).equals("null") && mData.getString(WebRule.WEB_PRO_HIDE).length() > 0)
								{
									try {
										cMobileData.setHide(Boolean.parseBoolean(mData.getString(WebRule.WEB_PRO_HIDE)));
									} catch (Exception ex) {
										// TODO: handle exception
										ex.printStackTrace();
									}
								}
								if(mData.has(WebRule.WEB_PRO_DATA) && !mData.getString(WebRule.WEB_PRO_DATA).equals("null") && mData.getString(WebRule.WEB_PRO_DATA).length() > 0)
								{
									try {
										cMobileData.setData(mData.getString(WebRule.WEB_PRO_DATA));
									} catch (Exception ex) {
										// TODO: handle exception
										ex.printStackTrace();
									}
								}
								e900mg.setmData(cMobileData);
								e900mg.setCl946(new Date());
								templateService.saveDocument(fo100, e900mg, WebRule.getComponentMongoColectionFromExtend(extend));
							}
						}
						catch(JSONException ex){
							log.error("---mData is not json object");
						}
					}
				}
			}
			/*
			 * 3) save to view version
			 */
			if(html != null){
				Document document = Jsoup.parse(html);
				//remove edit tools
				document.select(editToolSelector).remove();
				//change img dom data to make lazy load effect
				Elements imgElements = document.select(".comp-image img");
				for(Element img:imgElements)
				{
					img.attr("data-src",img.attr("src"));
					img.attr("src","");
				}
				//empty content of API component
				Elements apiComponents = document.select(apiCompSelector);
				for(Element element: apiComponents)
					element.empty();
				templateService.updateOneField(fo100, E950MG.class, QbMongoFiledsName.EV953, document.html(), null, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, webId));
			}
			templateService.updateOneField(fo100, E400MG.class, "EL446", new Date(), null, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.ID, webId));
			return 1;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}
	

	/**
	 * @param jsonObject
	 * @return
	 */

	@Override
	public Map<String, Long> save(int fo100, long webId, String html, String jsonData, String apiCompSelector, String editToolSelector, int extend){
		// TODO Auto-generated method stub
		Map<String, Long> returnId = new HashMap<String, Long>();
		try {
			/*
			 * 1) save web base info
			 */
			JSONObject webData = new JSONObject(jsonData);
			webUtilsService.saveBG(fo100, webId, webData, extend);
			webUtilsService.saveConfig(fo100, webId, webData, extend);
			webUtilsService.saveElemeCrId(fo100, webId, webData, extend);
			/*
			 * 3) save box
			 */
			if(webData.has(WebRule.WEB_PRO_LISTBOX)){
				JSONObject mapBox = webData.getJSONObject(WebRule.WEB_PRO_LISTBOX);
				if(mapBox.names() != null && mapBox.names().length() > 0){
					for (int i = 0; i < mapBox.names().length(); i++) {
						JSONObject box = mapBox.getJSONObject(mapBox.names().getString(i));
						boxUtilsService.updateBox(fo100, webId, box, returnId, extend);
					}
				}
			}
			/*
			 * 4) save component
			 */
			if(webData.has(WebRule.WEB_PRO_LISTCOMP)){
				JSONObject mapComponent = webData.getJSONObject(WebRule.WEB_PRO_LISTCOMP);
				if(mapComponent.names() != null && mapComponent.names().length() > 0){
					for(int i=0; i < mapComponent.names().length(); i++){
						JSONObject component = mapComponent.getJSONObject(mapComponent.names().getString(i));
						long boxId = 0;
						try{
							boxId = Long.parseLong(component.getString(WebRule.WEB_PRO_BOXID));
						}
						catch(NumberFormatException ex){
							log.info("---component of new box so must get from map box id data:"+component.getString(WebRule.WEB_PRO_BOXID));
							try {
								boxId = returnId.get(component.getString(WebRule.WEB_PRO_BOXID));
							} catch (NullPointerException e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
						componentUtilsService.updateComponent(fo100, webId, boxId, component, returnId, extend);
					}
				}
			}
			/*
			 * 5) save to view version
			 */
			if(html != null){
				Document document = Jsoup.parse(html);
				//remove edit tools
				document.select(editToolSelector).remove();
				//empty content of API component
				Elements apiComponents = document.select(apiCompSelector);
				for(Element element: apiComponents)
					element.empty();
				//change img dom data to make lazy load effect
				Elements imgElements = document.select(".comp-image img");
				for(Element img:imgElements)
				{
					img.attr("data-src",img.attr("src"));
					img.attr("src","");
				}
				//set id for new component, new box
				for (Map.Entry<String, Long> entry : returnId.entrySet()) {
					String rawId = entry.getKey();
					String realId = String.valueOf(entry.getValue());
					//new component
					if(rawId.indexOf("c") == 0)
					{
						Elements elements = document.select("[qb-component-id="+rawId+"]");
						if(elements != null && elements.size() > 0)
							elements.get(0).attr("qb-component-id", realId);
					}
					//new box
					else
					{
						Elements elements = document.select("[qb-box-id="+rawId+"]");
						if(elements != null && elements.size() > 0)
							elements.get(0).attr("qb-box-id", realId);
					}
				}
				templateService.updateOneField(fo100, E950MG.class, QbMongoFiledsName.EV951, minifyHtmlString(document.html()), null, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, webId));
			}
			templateService.updateOneField(fo100, E400MG.class, "EL446", new Date(), null, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.ID, webId));
			return returnId;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Long> saveTrial(int fo100, long tempateId, String hv101,  String html, String jsonData, String apiCompSelector, String editToolSelector, int extend) {
		// TODO Auto-generated method stub
		OhhayLegoWebBase ohhayLegoWebBase = createWebEvo(fo100, hv101, false, tempateId);
		if(ohhayLegoWebBase != null)
			return save(fo100, ohhayLegoWebBase.getId(), html, jsonData, apiCompSelector, editToolSelector, extend);
		return null;
	}

	@Override
	public int duplicate(int fo100, int webId) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1) load data
			 */
			E400MG e400mg = templateService.findDocument(fo100, E400MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.ID, webId));
			E950MG e950mg = templateService.findDocument(fo100, E950MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, webId));
			List<E920MG> listE920s = templateService.findDocuments(fo100, E920MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, webId));
			List<E900MG> listE900s = templateService.findDocuments(fo100, E900MG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, webId));
			/*
			 * 2) copy data
			 */
			//web data
			long webIdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E400);
			E400MG e400mgNew = new E400MG(e400mg);
			e400mgNew.setId(webIdNew);
			e400mgNew.setEn402(0);
			templateService.saveDocument(fo100, e400mgNew, QbMongoCollectionsName.E400);
			//web publish data
			long e950IdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E950);
			E950MG e950mgNew = new E950MG(e950mg);
			e950mgNew.setId(e950IdNew);
			e950mgNew.setWebId(webIdNew);
			templateService.saveDocument(fo100, e950mgNew, QbMongoCollectionsName.E950);
			//component and box
			for(E920MG e920mg: listE920s){
				//box
				E920MG e920mgNew = new E920MG(e920mg);
				long e920IdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E920);
				e920mgNew.setWebId(webIdNew);
				e920mgNew.setId(e920IdNew);
				templateService.saveDocument(fo100, e920mgNew, QbMongoCollectionsName.E920);
				//copy component
				for(E900MG e900mg: listE900s){
					if(e900mg.getBoxId() == e920mg.getId()){
						E900MG e900mgNew = new E900MG(e900mg);
						long e900IdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E900);
						e900mgNew.setBoxId(e920IdNew);
						e900mgNew.setWebId(webIdNew);
						e900mgNew.setId(e900IdNew);
						templateService.saveDocument(fo100, e900mgNew, QbMongoCollectionsName.E900);
					}
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
	public OhhayLegoWebBase getWebEvoDataTrial(int pe400) {
		// TODO Auto-generated method stub
		E400MG e400mg = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN, E400MG.class, new QbCriteria(QbMongoFiledsName.ID, pe400));
		if(e400mg != null){
			//load boxs and components
			List<E920MG> listE920 = templateService.findDocuments(ApplicationConstant.FO100_ADMIN_TEMPLATE, E920MG.class, 
					new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
			//Collections.sort(listE920);
			List<E900MG> listE900 = templateService.findDocuments(ApplicationConstant.FO100_ADMIN_TEMPLATE, E900MG.class, 
					new QbCriteria(QbMongoFiledsName.WEBID, e400mg.getId()));
			//create map data
			Map<Object, Object> mapBox = new LinkedHashMap<Object, Object>();
			Map<Object, Object> mapComponent = new LinkedHashMap<Object, Object>();
			for(E900MG e900mg: listE900)
			{
				ComponentTrial componentTrial = new ComponentTrial(e900mg);
				componentTrial.setId("c"+e900mg.getId());
				componentTrial.setBoxId("b"+e900mg.getBoxId());
				mapComponent.put(componentTrial.getId(), componentTrial);
			}
			for(E920MG e920mg: listE920)
			{
				BoxTrial boxTrial = new BoxTrial(e920mg);
				boxTrial.setId("b"+e920mg.getId());
				mapBox.put(boxTrial.getId(), boxTrial);
			}
			e400mg.setListBox(mapBox);
			e400mg.setListComponent(mapComponent);
			return e400mg;
		}
		return null;
	}

	@Override
	public int createBytemplate(int fo100, int templateId, boolean checkDB) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1) load data
			 */
			E400MG e400mg = templateService.findDocument(fo100, E400MG.class, new QbCriteria(QbMongoFiledsName.ID, templateId));
			boolean flag = true;
			if(checkDB == true)
			{
				if(e400mg.getEn402() == 1 || e400mg.getEn404() > 0)
					flag = true;
				else
					flag = false;
			}
			if(e400mg != null && flag){
				E950MG e950mg = templateService.findDocument(fo100, E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, templateId));
				List<E920MG> listE920s = templateService.findDocuments(fo100, E920MG.class, new QbCriteria(QbMongoFiledsName.WEBID, templateId));
				List<E900MG> listE900s = templateService.findDocuments(fo100, E900MG.class, new QbCriteria(QbMongoFiledsName.WEBID, templateId));
				/*
				 * 2) copy data
				 */
				//web data
				long webIdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E400);
				E400MG e400mgNew = new E400MG(e400mg);
				e400mgNew.setId(webIdNew);
				e400mg.setFe400(templateId);
				e400mgNew.setEn402(0);
				e400mgNew.setFo100(fo100);
				templateService.saveDocument(fo100, e400mgNew, QbMongoCollectionsName.E400);
				//web publish data
				long e950IdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E950);
				E950MG e950mgNew = new E950MG(e950mg);
				e950mgNew.setId(e950IdNew);
				e950mgNew.setFo100(fo100);
				e950mgNew.setWebId(webIdNew);
				templateService.saveDocument(fo100, e950mgNew, QbMongoCollectionsName.E950);
				//component and box
				for(E920MG e920mg: listE920s){
					//box
					E920MG e920mgNew = new E920MG(e920mg);
					long e920IdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E920);
					e920mgNew.setWebId(webIdNew);
					e920mgNew.setId(e920IdNew);
					e920mgNew.setFo100(fo100);
					templateService.saveDocument(fo100, e920mgNew, QbMongoCollectionsName.E920);
					//copy component
					for(E900MG e900mg: listE900s){
						if(e900mg.getBoxId() == e920mg.getId()){
							E900MG e900mgNew = new E900MG(e900mg);
							long e900IdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E900);
							e900mgNew.setBoxId(e920IdNew);
							e900mgNew.setWebId(webIdNew);
							e900mgNew.setId(e900IdNew);
							e900mgNew.setFo100(fo100);
							templateService.saveDocument(fo100, e900mgNew, QbMongoCollectionsName.E900);
						}
					}
				}
				return (int) webIdNew;	
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateSEOinfo(int fo100, int pe400, String title, String description, String imageBase64,
			 String imageBase64Fav, String region, String googleTrackingScript) {
		// TODO Auto-generated method stub
		try {
			log.info("--googleTrackingScript:"+googleTrackingScript);
			E400MG e400mg = templateService.findDocument(fo100, E400MG.class, 
													     new QbCriteria(QbMongoFiledsName.ID, pe400),
													     new QbCriteria(QbMongoFiledsName.FO100, fo100));
			if(e400mg != null){
				N950MG n950mg = e400mg.getN950mg();
				if(n950mg == null)
					n950mg = new N950MG();
				n950mg.setNv951(title);
				n950mg.setNv952(description);
				/*
				 * upload feature image
				 */
				if(imageBase64 != null && imageBase64.trim().length() > 0){
					String imgContent = imageBase64.split("\\,")[1];
					byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
					EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils(region, pe400);
					String fileName = ApplicationHelper.generateFileName();
					String url = awsFileUtils.uploadObjectMutilPartImgAlbum(pe400, fileName, btDataFil);
					n950mg.setNv953(url);
				}
				/*
				 * update fav icon
				 */
				if(imageBase64Fav != null && imageBase64Fav.trim().length() > 0){
					String imgContent = imageBase64Fav.split("\\,")[1];
					byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
					EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils(region, pe400);
					String fileName = ApplicationHelper.generateFileName();
					String url = awsFileUtils.uploadObjectMutilPartImgAlbum(pe400, fileName, btDataFil);
					n950mg.setNv954(url);
				}
				n950mg.setNv955(googleTrackingScript);
				return templateService.updateOneField(fo100, E400MG.class, pe400, "SEO", n950mg,"EL448");
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int createKubLandingPage(int fo100, int templateId, String kubvideo) {
		// TODO Auto-generated method stub
		log.info("--createKubLandingPage:"+fo100+","+templateId+","+kubvideo);
		int webid = createBytemplate(fo100, templateId, true);
		if(webid > 0 && kubvideo != null && kubvideo.trim().length() > 0){
			E900MG e900mg = templateService.findDocument(fo100, E900MG.class, new QbCriteria(QbMongoFiledsName.WEBID, webid), new QbCriteria(QbMongoFiledsName.TYPE, "normal-iframe"));
			if(e900mg != null){
				e900mg.setData("{\"iframeType\":\"normal_iframe_empty\",\"src\":\"//my.kubplayer.com/preview/view/"+kubvideo+"\"}");
				templateService.saveDocument(fo100, e900mg, QbMongoCollectionsName.E900);
				try {
					E400MG e400mg = templateService.findDocumentById(ApplicationConstant.FO100_SUPER_ADMIN, templateId, E400MG.class);
					String ss[] = kubvideo.split("_");
					int fb050 = Integer.parseInt(ss[ss.length-1]);
					String ss2[] = kubvideo.split("/");
					int fk100 = Integer.parseInt(ss2[0]);
					//call webservice from kub
					B100KubDao b100KubDao = (B100KubDao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_B100KUB);
					log.info("--KUB service insertTabB100:"+fk100+","+0+","+""+","+e400mg.getEv401()+","+""+","+e400mg.getEv403()+","+String.valueOf(webid)+","+String.valueOf(templateId)+","+fb050+","+ApplicationConstant.PVLOGIN_ANONYMOUS);
					b100KubDao.insertTabB100(fk100,0, "", e400mg.getEv401(), "", e400mg.getEv403(), String.valueOf(webid), String.valueOf(templateId),fb050, ApplicationConstant.PVLOGIN_ANONYMOUS);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return webid;
	}

	@Override
	public int createEVOCard(int fo100, int templateId, String cardContent, String imgBase64,String nv100, String region) {
		// TODO Auto-generated method stub
		log.info("--createEVOCread:"+fo100+","+templateId+","+cardContent);
		try {
			/*
			 * 1) create web EVO
			 */
			int webId = createBytemplate(fo100, templateId, true);
			/*
			 * 2) merge data and web publish html
			 */
			if(webId > 0){
				if(cardContent.trim().length()> 0){
					try {
						E400MG e400mg = templateService.findDocumentById(fo100, webId, E400MG.class);
						e400mg.setEn402(2);
						N950MG n950mg = e400mg.getN950mg();
						if(n950mg == null)
							n950mg = new N950MG();
						n950mg.setNv952("Bạn nhận được một thiệp chúc từ "+nv100);
						e400mg.setN950mg(n950mg);
						templateService.saveDocument(fo100, e400mg, QbMongoCollectionsName.E400);
						/*
						 * 2.1) merger PC version
						 */
						E950MG e950mg = templateService.findDocument(fo100, E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, templateId));
						Document document = Jsoup.parse(e950mg.getEv951());
						Elements elementContent = document.select(".qb-evo-card-title");
						if(elementContent.size() > 0){
							elementContent.get(0).html(cardContent);
						}
						String imgURL = null;
						if(imgBase64 != null && imgBase64.trim().length() > 0){
							String imgContent = imgBase64.split("\\,")[1];
							byte[] btDataFil = new sun.misc.BASE64Decoder().decodeBuffer(imgContent);
							EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils(region, webId);
							String fileName = ApplicationHelper.generateFileName();
							imgURL = awsFileUtils.uploadObjectMutilPartImgAlbum(webId, fileName, btDataFil);
							log.info("---card background:"+imgURL);
							Elements elementBG = document.select(".layer-background.girdbox");
							if(elementBG.size() > 0){
								elementBG.get(0).attr("style", "");
								elementBG.get(0).attr("cardimg", imgURL);
							}
						}
						templateService.updateOneField(fo100, E950MG.class, QbMongoFiledsName.EV951, document.html(), null, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, webId));
						/*
						 * 2.2) merger mobile version
						 */
						Document documentContentMB = Jsoup.parse(e950mg.getEv953());
						Elements elementMBs = documentContentMB.select(".qb-evo-card-title");
						if(elementMBs.size() > 0){
							elementMBs.get(0).html(cardContent);
						}
						if(imgURL != null && imgURL.trim().length() > 0){
							log.info("---card background mb:"+imgURL);
							Elements elementBG = documentContentMB.select(".layer-background.girdbox");
							if(elementBG.size() > 0){
								elementBG.get(0).attr("style", "");
								elementBG.get(0).attr("cardimg", imgURL);
							}
						}
						templateService.updateOneField(fo100, E950MG.class, QbMongoFiledsName.EV953, documentContentMB.html(), null, new QbCriteria(QbMongoFiledsName.FO100, fo100), new QbCriteria(QbMongoFiledsName.WEBID, webId));
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
			return webId;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String checkRightCreateWeb(AuthenticationRightInfo authenticationRightInfo, int fo100) {
		// TODO Auto-generated method stub
		if(authenticationRightInfo.isWEB_SIT_PRO() || authenticationRightInfo.isWEB_SIT_DES())
			return ApplicationConstant.RE_VAILD_RIGHT;
		else
		{
			//not show web card
			QbCriteria criteria = new QbCriteria(QbMongoFiledsName.EN402, 2);
			criteria.setType(QbCriteria.TYPE_NE);
			List<E400MG> listE400s = templateService.findDocuments(fo100, E400MG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100), criteria);
			if(listE400s != null && listE400s.size() > 0)
			{
				int allowWeb = 5;//free packet
				if(authenticationRightInfo.isWEB_SIT_EXP())
					allowWeb = 30;
				else if(authenticationRightInfo.isWEB_SIT_OPT())
					allowWeb = 10;
				if(listE400s.size() < allowWeb)
					return ApplicationConstant.RE_VAILD_RIGHT;
				else
					return MVCResourceBundleUtil.getResourceBundle("mys.error1").replace("$websiteNumber", String.valueOf(listE400s.size()));
			}
			else
				return ApplicationConstant.RE_VAILD_RIGHT;
		}
	}

	@Override
	public int replaceWebToWeb(int fo100, int fo100Temp, int currentWebId, int templateId) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1) load data
			 */
			E400MG e400mgTemplate = templateService.findDocument(fo100Temp, E400MG.class, new QbCriteria(QbMongoFiledsName.ID, templateId));
			E400MG e400mgOrgin = templateService.findDocument(fo100, E400MG.class, new QbCriteria(QbMongoFiledsName.ID, currentWebId));
			if(e400mgTemplate != null && e400mgOrgin != null){
				E950MG e950mgTemplate = templateService.findDocument(fo100Temp, E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, templateId));
				E950MG e950mgOrgin = templateService.findDocument(fo100, E950MG.class, new QbCriteria(QbMongoFiledsName.WEBID, currentWebId));
				List<E920MG> listE920s = templateService.findDocuments(fo100Temp, E920MG.class, new QbCriteria(QbMongoFiledsName.WEBID, templateId));
				List<E900MG> listE900s = templateService.findDocuments(fo100Temp, E900MG.class, new QbCriteria(QbMongoFiledsName.WEBID, templateId));
				/*
				 * 2) copy data
				 */
					/*
					 * 2.1) create web data, keep some old info
					 */
					E400MG e400mgNew = new E400MG(e400mgOrgin);
					e400mgTemplate.setConfig(e400mgTemplate.getConfig());
					e400mgTemplate.setBg(e400mgTemplate.getBg());
					e400mgTemplate.setFe400(e400mgOrgin.getFe400());
					/*
					 * 2.2) remove current web
					 */
					templateService.removeRealDocumentById(fo100, currentWebId, E400MG.class);
					templateService.removeDocuments(fo100, E920MG.class, new QbCriteria(QbMongoFiledsName.WEBID, currentWebId));
					templateService.removeDocuments(fo100, E900MG.class, new QbCriteria(QbMongoFiledsName.WEBID, currentWebId));
					/*
					 * 2.3) create new web, keep old id
					 */
					e400mgNew.setId(currentWebId);
					e400mgNew.setEv405(e400mgOrgin.getEv405());
					templateService.saveDocument(fo100, e400mgNew, QbMongoCollectionsName.E400);
					/*
					 * 2.4) web publish data
					 */
					E950MG e950mgNew = new E950MG(e950mgTemplate);
					e950mgNew.setId(e950mgOrgin.getId());
					e950mgNew.setFo100(fo100);
					e950mgNew.setWebId(currentWebId);
					/*
					 * 2.5) remove web publish data old
					 */
					templateService.removeRealDocumentById(fo100, (int)e950mgOrgin.getId(), E950MG.class);
					/*
					 * 2.6) save web publish data
					 */
					templateService.saveDocument(fo100, e950mgNew, QbMongoCollectionsName.E950);
					/*
					 * 2.7) component and box
					 */
					for(E920MG e920mg: listE920s){
						//box
						E920MG e920mgNew = new E920MG(e920mg);
						long e920IdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E920);
						e920mgNew.setWebId(currentWebId);
						e920mgNew.setId(e920IdNew);
						e920mgNew.setFo100(fo100);
						templateService.saveDocument(fo100, e920mgNew, QbMongoCollectionsName.E920);
						//copy component
						for(E900MG e900mg: listE900s){
							if(e900mg.getBoxId() == e920mg.getId()){
								E900MG e900mgNew = new E900MG(e900mg);
								long e900IdNew = sequenceService.getNextSequenceId(fo100, QbMongoCollectionsName.E900);
								e900mgNew.setBoxId(e920IdNew);
								e900mgNew.setWebId(currentWebId);
								e900mgNew.setId(e900IdNew);
								e900mgNew.setFo100(fo100);
								templateService.saveDocument(fo100, e900mgNew, QbMongoCollectionsName.E900);
							}
						}
					}
					return (int) currentWebId;	
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * @param htmlString
	 * @return
	 */
	private String minifyHtmlString(String htmlString){
		return htmlString;
	}

	@Override
	public int createPiepmeSite(int fo100, boolean isBusinessAccount) {
		// TODO Auto-generated method stub
		List<E400MG> listE400s = e400mgService.getListMysite(fo100, null, 0, 1);
		if (listE400s == null || listE400s.size() == 0) {
			List<E400MG> listPiepmeTemplate = null;
			if(isBusinessAccount)
				listPiepmeTemplate = templateService.findDocuments(fo100, E400MG.class, new QbCriteria("IS_PIEPME_TEMP", 2));
			else
				listPiepmeTemplate = templateService.findDocuments(fo100, E400MG.class, new QbCriteria("IS_PIEPME_TEMP", 1));
			int tempalteId = (int)listPiepmeTemplate.get(new Random().nextInt(listPiepmeTemplate.size())).getId();
			int webId = createBytemplate(fo100, tempalteId, false);
			return webId;
		}
		return 0;
	}
}
