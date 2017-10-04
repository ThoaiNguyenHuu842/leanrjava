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
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.web.core.entities.C150Shanti;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractSubContainer;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.core.utils.WebRequestParam;
import com.ohhay.web.other.api.service.ShantiService;

/**
 * @author ThoaiNH
 * con hardcode nhieu
 *
 */
@Service
public class ShantiGalleryItemC150SubContainer extends AbstractSubContainer {
	private static Logger log = Logger.getLogger(ShantiGalleryItemC150SubContainer.class);
	@Autowired
	private ShantiService shantiService;
	private int fb050;
	
	@Override
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900s, int role, String shantikey, int fo100)
			throws Exception {

		// TODO Auto-generated method stub
		List<C150Shanti> listC150Shantis = shantiService
				.listOfTabC150("72f907e07031916380b6ab15e40f1f47", fb050, "giann@queenb.vn");
		/*
		 * 1) find orginal gallery item of webinaris
		 */
		C900MG c900mgClone = null;
		for (int i = 0; i < listC900s.size(); i++) {
			C900MG c900mg = listC900s.get(i);
			if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM)) {
				log.info("---ShantiGalleryItemSubContainer: found shanti element id:"+ c900mg.getPc900());
				c900mgClone = new C900MG(c900mg);
				break;
			}
		}
		StringBuilder builderDbSubContainer = new StringBuilder("");
		/*
		 * 2) create list C900 from list B050
		 */
		if (c900mgClone != null) {
			log.info("---ShantiGalleryItemSubContainer: list c150 size:"+ listC150Shantis.size());
			for (int i = 0; i < listC150Shantis.size(); i++) {
				C900MG c900mg2 = new C900MG(c900mgClone);
				c900mg2.setPc900(null);// khong cho edi
				builderDbSubContainer.append(processGalleryItem(c900mg2, role, listC150Shantis.get(i)));
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

	private Document mapC150ToCv906(String cv906, C150Shanti c150) {
		Document document = Jsoup.parse(cv906);
		Elements ele1 = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_API_NAME + "=name-course]");
		Elements ele2 = document.body()
				.select("[" + TemplateRule.OHHAY_DATA_QB_API_NAME
						+ "=image-course]");
		Elements ele3 = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_API_NAME + "=time-course");
		Elements ele4 = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_API_NAME
				+ "=description-course");
		Elements ele5 = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_API_NAME + "=price-course");
		Elements ele6 = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_API_NAME + "=link-course");
		log.info("--ele1 size:" + ele1.size());
		// name course
		if (ele1.size() > 0) {
			Element element = ele1.get(0);
			element.empty();
			element.append(c150.getCv151());
		}
		// img course
		if (ele2.size() > 0) {
			Element element = ele2.get(0);
			element.attr("src", c150.getCv158());
		}
		// time course
		if (ele3.size() > 0) {
			Element element = ele3.get(0);
			element.html("Ngày khai giảng " + String.valueOf(c150.getCn169())
					+ "/" + String.valueOf(c150.getCn168()) + "/"
					+ String.valueOf(c150.getCn167()));
		}
		// description course
		if (ele4.size() > 0) {
			Element element = ele4.get(0);
			//element.html("");
		}
		// price course
		if (ele5.size() > 0) {
			Element element = ele5.get(0);
			element.html(String.valueOf(c150.getCn174()) + " vnđ");
		}
		// link course
		if (ele6.size() > 0) {
			Element element = ele6.get(0);
			createLinkRegisterShanti(element, c150);
		}
		return document;
	}
	/*
	 * create link register shanti
	 */
	private void createLinkRegisterShanti(Element element, C150Shanti c150Shanti) {
		element.addClass(TemplateRule.OHHAY_QB_LINK_REGIST_CLASS);
		element.attr("target", "_blank");
		if (AuthenticationHelper.getUserLogin() != null) {
			Q100 q100 = AuthenticationHelper.getUserLogin();
			M900MG m900mg = q100.getM900mg();
			if(m900mg != null){
				//check profile name,email, phone not empty
				if(m900mg.getMv901() != null && m900mg.getMv901().length() >0 && 
				   m900mg.getMv902() != null && m900mg.getMv902().length() >0 &&
				   m900mg.getMv903() != null && m900mg.getMv903().length() >0 &&
				   m900mg.getMv907() != null && m900mg.getMv907().length() >0 ){
					StringBuilder stringBuilder = new StringBuilder("http://shanti.ghs.vn/lp/course/GHS-024828.html");
					stringBuilder.append("?fname="+m900mg.getMv901Decrypt());
					stringBuilder.append("&lname="+m900mg.getMv902Decrypt());
					stringBuilder.append("&email="+m900mg.getMv903Decrypt().trim());
					stringBuilder.append("&mobile="+m900mg.getMv907());
					stringBuilder.append("&source_id=ohay");
					element.attr("href", stringBuilder.toString());
					return;
				}
			}
		}
		element.attr("href", "http://shanti.ghs.vn/lp/course/GHS-024828.html?source_id=ohay");
		
	}
	/*
	 * proccess gallery item shanti
	 */
	private String processGalleryItem(C900MG c900mg, int role, C150Shanti c150) {
		Document document = mapC150ToCv906(c900mg.getCv906(), c150);
		Elements elementsEditAble = document.body().select("["
				+ TemplateRule.OHHAY_DATA_QB_ACCEPT_EDIT + "~=]");
		if (elementsEditAble.size() > 0) {
			for (Element element : elementsEditAble) {
				if (element.hasClass(TemplateRule.OHHAY_NO_EDIT_CLASS)) {
					// truong hop nhung element nam trong elemnt a -> khong edit nhung element nay
				}
				else {
					processElementGroup(element,c900mg.getPc900(),role);
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

	public int getFb050() {
		return fb050;
	}

	public void setFb050(int fb050) {
		this.fb050 = fb050;
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
