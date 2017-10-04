package com.ohhay.web.load.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.OhhayWebType;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.MVCResourceBundleUtil;
import com.ohhay.web.core.entities.V250;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.entities.mongo.webbase.C920MG;
import com.ohhay.web.core.entities.mongo.webbase.G920MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayAllJS;
import com.ohhay.web.core.entities.mongo.webbase.OhhayLibraryJS;
import com.ohhay.web.core.load.util.AbstractElement;
import com.ohhay.web.core.load.util.AbstractSubContainer;
import com.ohhay.web.core.load.util.AbstractWebBase;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.other.api.service.BsellService;
import com.ohhay.web.other.subcontainer.ShantiGalleryItemC150SubContainer;
import com.ohhay.web.other.superelement.BsellV250Element;

/**
 * @author ThoaiNH 
 * create: 22/10/2014
 * load list c900 to c920
 */
public class BoxCreator extends AbstractWebBase {
	private static Logger log = Logger.getLogger(BoxCreator.class);
	private WebCreateParam webCreateParam;
	private C920MG c920mg;
	private int type;// footer:0,header:0, near footer:1,near header:2, other :3, near footer and header (when 3 box): 4
	private Set<OhhayLibraryJS> setOhhayLibraryJS = new java.util.HashSet<OhhayLibraryJS>();

	// box creator constructor for normal box
	public BoxCreator(WebCreateParam webCreateParam,
			Set<OhhayLibraryJS> setOhhayLibraryJS, C920MG c920mg, int type) {
		super();
		this.type = type;
		this.c920mg = c920mg;
		this.setOhhayLibraryJS = setOhhayLibraryJS;
		this.webCreateParam = webCreateParam;
		log.info("---BoxCreator:" + webCreateParam);
	}

	public BoxCreator() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * tim list c900 trong c400 theo fc920
	 */
	private Map<String, C900MG> convertListC900MGToMap(int webId, int indexBox, List<C900MG> list) {
		Map<String, C900MG> map = new HashMap<String, C900MG>();
		for (int i = 0; i < list.size(); i++) {
			C900MG c900 = list.get(i);
			if (c900 != null && c900.getCv901() != null) {
				// create super id
				c900.setIndexBox(indexBox);
				c900.setIndexEleme(i);
				c900.setWebId(webId);
				map.put(c900.getCv901(), c900);
			}
		}
		return map;
	}

	/*
	 * thay the noi dung element
	 */
	private void replaceElementContent(Element element, C900MG c900mg) {
		if (c900mg.getCv905() != null) {
			AbstractElement abstractElement = null;
			// data text type
			if (c900mg.getCv902()
					.equals(TemplateRule.OHHAY_DATA_QB_TYPE_TEXT)) {
				abstractElement = (AbstractElement) ApplicationHelper
						.findBean(SpringBeanNames.ELEMENT_NAME_TEXT);
			}
			else
			// data percent type
			if (c900mg.getCv902()
					.equals(TemplateRule.OHHAY_DATA_QB_TYPE_PERCENT)) {
				abstractElement = (AbstractElement) ApplicationHelper
						.findBean(SpringBeanNames.ELEMENT_NAME_PERCENT);
			}
			else
			// data image type
			if (c900mg.getCv902()
					.equals(TemplateRule.OHHAY_DATA_QB_TYPE_IMAGE)) {
				abstractElement = (AbstractElement) ApplicationHelper
						.findBean(SpringBeanNames.ELEMENT_NAME_IMAGE);
			}
			else
			// bg color
			if (c900mg.getCv902()
					.equals(TemplateRule.OHHAY_DATA_QB_TYPE_BG_COLOR)) {
				abstractElement = (AbstractElement) ApplicationHelper
						.findBean(SpringBeanNames.ELEMENT_NAME_BACKGROUND);
			}
			// group: is able change visibility
			if (c900mg.getCv902()
					.equals(TemplateRule.OHHAY_DATA_QB_TYPE_GROUP)) {
				abstractElement = (AbstractElement) ApplicationHelper
						.findBean(SpringBeanNames.ELEMENT_NAME_GROUP);
			}
			if (abstractElement != null) {
				abstractElement.mapElementToBox(element, c900mg, webCreateParam);
				// class and attr for edit
				addClassForElement(element, TemplateRule.OHHAY_EDIT_CLASS, webCreateParam.getRole());
				addIdToElement(element, c900mg.getSuperID(), webCreateParam.getRole());
				// remove qb-data-content if no webCreateParam.getRole() edit
				removeQbClass(element, webCreateParam.getRole());
			}
		}

	}

	public String loadC900ToC920() {
		String cv923 = c920mg.getCv923();
		List<C900MG> listC900s = new ArrayList<C900MG>();
		if(c920mg.getListC900mg() != null)
			listC900s = c920mg.getListC900mg();
		Map<String, C900MG> mapC900 = convertListC900MGToMap(webCreateParam
				.getOhhayWebBase().getId(), c920mg.getRealIndex(), listC900s);
		log.info("INDEX BOX------------------" + c920mg.getRealIndex());
		Document document = Jsoup.parse(cv923);
		/*
		 * 1) add id,attr to edit box (date update: 25/12/2014)
		 */
		Elements elementQbContentBoxs = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_STYLE + "~=]");
		if (elementQbContentBoxs.size() > 0) {
			Element box = elementQbContentBoxs.get(0);
			addIdToBox(box, String.valueOf(c920mg.getFc920()), webCreateParam.getRole());
			addClassForElement(box, TemplateRule.OHHAY_EDIT_BOX_CLASS, webCreateParam
					.getRole());
			addAttr(box, TemplateRule.OHHAY_EDIT_BOX_TYPE, String.valueOf(type), webCreateParam
					.getRole());
			addAttr(box, TemplateRule.OHHAY_EDIT_BOX_ORIGINAL, String.valueOf(c920mg
					.getOriginal()), webCreateParam.getRole());
			addAttr(box, TemplateRule.OHHAY_EDIT_BOX_VISIBLE, String.valueOf(c920mg
					.getVisible()), webCreateParam.getRole());
			addAttr(box, TemplateRule.OHHAY_EDIT_BOX_REAL_INDEX, String.valueOf(c920mg
					.getRealIndex()), webCreateParam.getRole());
		}
		/*
		 * 2) xu ly thay the attribute tu du lieu cua c900
		 */
		Elements elementQbContents = document.body().select("["
				+ TemplateRule.OHHAY_QB_DATA_CONTENT + "~=]");
		/*
		 * 2.0) normal element
		 */
		if (elementQbContents != null && elementQbContents.size() > 0) {
			for (int i = 0; i < elementQbContents.size(); i++)
				emptyElement(elementQbContents.get(i));
			// thay doi noi dung cua user
			for (Element element : elementQbContents) {
				// tim attribute data-qb-content de replace
				String attrValue = element
						.attr(TemplateRule.OHHAY_QB_DATA_CONTENT);
				C900MG c900mg = mapC900.get(attrValue);
				if (c900mg != null) {
					replaceElementContent(element, c900mg);
				}

			}
		}
		/*
		 * 2.1) element GalleryItemSubContainer
		 */
		Elements elementQbSubContainners = document
				.body()
				.select("[" + TemplateRule.OHHAY_DATA_QB_SUB_CONTAINER
						+ "]").empty();
		for(Element elementQbSubContainner: elementQbSubContainners) {
			/*
			 * 2.1.1) extend gallery item
			 */
			if (webCreateParam.getExtendPage() == OhhayWebType.WEBTYPE_OHHAY_WEBINAR) {
				/*
				 * 2.1.1.1) ThoaiNH: webinaris
				 */
				AbstractSubContainer abstractSubContainer = (AbstractSubContainer) ApplicationHelper
						.findBean(SpringBeanNames.WEBINAR_SUB_CONTAINER_GALLERY_ITEM);
				try {
					abstractSubContainer
							.mapElementToBoxExtend(elementQbSubContainners
									.get(0), listC900s, webCreateParam
									.getRole(), webCreateParam.getKey(), webCreateParam.getOhhayWebBase().getFo100());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (webCreateParam.getExtendPage() == OhhayWebType.WEBTYPE_OHHAY_BSELL) {
				/*
				 * 2.1.1.2) tungns: bsell
				 */
				AbstractSubContainer abstractSubContainer = (AbstractSubContainer) ApplicationHelper
						.findBean(SpringBeanNames.BSELL_SUB_CONTAINER_GALLERY_ITEM);
				try {
					abstractSubContainer
							.mapElementToBoxExtend(elementQbSubContainners
									.get(0), listC900s, webCreateParam
									.getRole(), webCreateParam.getKey(), webCreateParam.getOhhayWebBase().getFo100());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			else if (webCreateParam.getExtendPage() == OhhayWebType.WEBTYPE_OHHAY_VIDEOMARKETING && document.body().child(0).hasClass(TemplateRule.OHHAY_DATA_QB_VIDEO_SUBCONTAINER)) {
				/*
				 * 2.1.1.3) video marketing
				 */
					log.info("---Box creator: video marketing box api webCreateParam.getKey():"
							+ webCreateParam.getKey());
					AbstractSubContainer abstractSubContainer = (AbstractSubContainer) ApplicationHelper
							.findBean(SpringBeanNames.VIDEO_MARKETING_SUB_CONTAINER_GALLERY_ITEM);
					try {
						abstractSubContainer
								.mapElementToBoxExtend(elementQbSubContainners
										.get(0), listC900s, webCreateParam
										.getRole(), webCreateParam.getKey(), webCreateParam.getOhhayWebBase().getFo100());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}

			}
			/*
			 * 2.1.2) normal gallery item
			 */
			else {
				switch (c920mg.getFc820()) {
				/*
				 * 2.1.2.2) shanti box 1
				 */
				case 82: {
					log.info("---Box creator: shanti box b050");
					AbstractSubContainer abstractSubContainer = (AbstractSubContainer) ApplicationHelper
							.findBean(SpringBeanNames.SHANTI_SUB_CONTAINER_GALLERY_B050_ITEM);
					try {
						abstractSubContainer
								.mapElementToBoxExtend(elementQbSubContainners
										.get(0), listC900s, webCreateParam
										.getRole(), webCreateParam.getKey(), webCreateParam.getOhhayWebBase().getFo100());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					break;
				/*
				 * 2.1.2.3) shanti box 2
				 */
				case 86: {
					ShantiGalleryItemC150SubContainer c150SubContainer = (ShantiGalleryItemC150SubContainer) ApplicationHelper
							.findBean(SpringBeanNames.SHANTI_SUB_CONTAINER_GALLERY_C150_ITEM);
					log.info("---Box creator: shanti box c150");
					try {
						c150SubContainer.setFb050(Integer
								.parseInt(webCreateParam.getKey()));
						c150SubContainer
								.mapElementToBoxExtend(elementQbSubContainners
										.get(0), listC900s, webCreateParam
										.getRole(), webCreateParam.getKey(), webCreateParam.getOhhayWebBase().getFo100());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					break;
				/*
				 * 2.1.2.4) other box
				 */
				default: {
					AbstractSubContainer abstractSubContainer = (AbstractSubContainer) ApplicationHelper.findBean(SpringBeanNames.SUB_CONTAINER_GALLERY_ITEM);
					abstractSubContainer.mapElementToBox(elementQbSubContainner, listC900s, webCreateParam);
				}
					break;
				}
			}
		}
		/*
		 * tungns: element v250 detail
		 */
		if (c920mg.getCv921() != null
				&& c920mg.getCv921().equalsIgnoreCase("Box Success")
				&& webCreateParam.getExtendPage() == OhhayWebType.WEBTYPE_OHHAY_BSELL) {
			BsellV250Element bsellV250Element = (BsellV250Element) ApplicationHelper
					.findBean(SpringBeanNames.SUPER_ELEMENT_NAME_BSELL);
			BsellService bsellService = (BsellService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_BSELLSER);
			List<V250> lisV250s = bsellService.listOfTabV250KEY(webCreateParam
					.getKey());
			try {
				if (elementQbContents != null && elementQbContents.size() > 0) {
					for (int i = 0; i < elementQbContents.size(); i++) {
						emptyElement(elementQbContents.get(i));
					}
					for (Element element : elementQbContents) {
						// tim attribute data-qb-content de replace
						String attrValue = element
								.attr(TemplateRule.OHHAY_QB_DATA_CONTENT);
						C900MG c900mg = mapC900.get(attrValue);
						if (c900mg != null) {
							bsellV250Element
									.mapElementToBox(element, webCreateParam
											.getRole(), webCreateParam.getKey(), c900mg
											.getCv901().toString(), lisV250s
											.get(0));
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		/*
		 * 2.2) map address to google map
		 */
		Elements elementMaps = document.body().select(".qb-out-map-canvas");
		if (elementMaps.size() > 0 && c920mg.getG920mg() != null) {
			setOhhayLibraryJS.add(OhhayAllJS.gogleMap);
			G920MG n950mg = c920mg.getG920mg();
			log.info("--address:" + n950mg.getGv921());
			log.info("--marker img:" + n950mg.getGv922());
			log.info("--la:" + n950mg.getGn923());
			log.info("--log:" + n950mg.getGn924());
			String infoContent = n950mg.getGv925() != null? n950mg.getGv925() : n950mg.getGv921();
			Element map = elementMaps.get(0);
			map.attr(TemplateRule.OHHAY_RULE_GMAP_MARKER, n950mg
					.getGv922());
			map.attr(TemplateRule.OHHAY_RULE_GMAP_LA, String
					.valueOf(n950mg.getGn923()));
			map.attr(TemplateRule.OHHAY_RULE_GMAP_LOG, String
					.valueOf(n950mg.getGn924()));
			map.attr(TemplateRule.OHHAY_RULE_GMAP_INFOR, infoContent);
			map.attr(TemplateRule.OHHAY_RULE_GMAP_ADDRESS, n950mg.getGv921());
			map.attr(TemplateRule.OHHAY_BOX_ID, String.valueOf(c920mg.getFc920()));
		}
		// remove if webCreateParam.getRole() is viewer
		if (document.body().childNodeSize() > 0)
			removeQbClass(document.body().child(0), webCreateParam.getRole());
		return document.body().html();
	}

	public C920MG getC920mg() {
		return c920mg;
	}

	public void setC920mg(C920MG c920mg) {
		this.c920mg = c920mg;
	}

}
