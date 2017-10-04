package com.ohhay.web.other.subcontainer;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.RequestParamRule;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.web.core.entities.B050Shanti;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractSubContainer;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.core.utils.WebRequestParam;
import com.ohhay.web.other.api.service.ShantiService;

/**
 * @author ThoaiNH
 * con hard code nhieu
 *
 */
@Service
public class ShantiGalleryItemB050SubContainer extends AbstractSubContainer {
	private static Logger log = Logger.getLogger(ShantiGalleryItemB050SubContainer.class);
	@Autowired
	private ShantiService shantiService;

	

	@Override
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900s, int role, String shantikey, int fo100)
			throws Exception {

		// TODO Auto-generated method stub
		List<B050Shanti> listB050Shantis = shantiService
				.listOfTabB050("72f907e07031916380b6ab15e40f1f47", "giann@queenb.vn");
		/*
		 * 1) find orginal gallery item of webinaris
		 */
		C900MG c900mgClone = null;
		for (int i = 0; i < listC900s.size(); i++) {
			C900MG c900mg = listC900s.get(i);
			if (c900mg
					.getCv902()
					.equals(TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM)) {
				System.out
						.println("---ShantiGalleryItemSubContainer: found shanti element id:"
								+ c900mg.getPc900());
				c900mgClone = new C900MG(c900mg);
				break;
			}
		}
		StringBuilder builderDbSubContainer = new StringBuilder("");
		/*
		 * 2) create list C900 from list B050
		 */
		if (c900mgClone != null) {
			System.out
					.println("---ShantiGalleryItemSubContainer: list b050 size:"
							+ listB050Shantis.size());
			for (int i = 0; i < listB050Shantis.size(); i++) {
				C900MG c900mg2 = new C900MG(c900mgClone);
				c900mg2.setPc900(null);// khong cho edit
				builderDbSubContainer
						.append(processGalleryItem(c900mg2, role, listB050Shantis
								.get(i)));
			}
			System.out
					.println("---ShantiGalleryItemSubContainer: listc900 size:"
							+ listC900s.size());
		}
		else
			throw new Exception("Template do'nt have any shanti element");
		element.append(builderDbSubContainer.toString());
		/*
		 * 3) remove attr if viewer
		 */
		if (role == ApplicationConstant.ROLE_VIEWER) {
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_SUB_CONTAINER);
		}

	}

	private Document mapB050ToCv906(String cv906, B050Shanti b050) {
		Document document = Jsoup.parse(cv906);
		Elements ele1 = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_API_NAME
				+ "=name-register]");
		Elements ele2 = document.body()
				.select("[" + TemplateRule.OHHAY_DATA_QB_API_NAME
						+ "=img-register]");
		Elements ele3 = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_API_NAME
				+ "=link-register]");
		log.info("--ele1 size:" + ele1.size());
		// title
		if (ele1.size() > 0) {
			Element element = ele1.get(0);
			element.empty();
			element.append(b050.getBv051());
		}
		// img
		if (ele2.size() > 0) {
			Element element = ele2.get(0);
			element.attr("src",b050.getSv203());
		}
		// link-register
		if (ele3.size() > 0) {
			Element element = ele3.get(0);
			element.attr("href", "child-2?"+RequestParamRule.PARAM_SHANTICLASS+"="+b050.getPb050());
		}
		return document;
	}

	private String processGalleryItem(C900MG c900mg, int role, B050Shanti b050) {
		Document document = mapB050ToCv906(c900mg.getCv906(), b050);
		Elements elementsEditAble = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT + "~=]");
		if (elementsEditAble.size() > 0) {
			for (Element element : elementsEditAble) {
				if (element.hasClass(TemplateRule.OHHAY_NO_EDIT_CLASS)) {
					// truong hop nhung element nam trong elemnt a -> khong edit
					// nhung element nay
				}
				else {
					processElementGroup(element, c900mg.getPc900(), role);
				}
				afterProccessElementOfCV906(element, role);
			}
		}
		Elements elements = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_TYPE + "~=]");
		if (elements.size() > 0)
			afterProccessCV906(elements.get(0), role);
		return document.body().html();
	}

	@Override
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900mgs, int role, String key, WebRequestParam webRequestParam)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mapElementToBox(Element element, List<C900MG> listC900mgs, WebCreateParam webCreateParam) {
		// TODO Auto-generated method stub
		
	}
}
