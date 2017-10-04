package com.ohhay.web.core.load.util;

import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.RequestParamRule;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.OhhayLibraryJS;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebShortBase;
import com.ohhay.zuss.ZussUtils;

/**
 * @author ThoaiNH
 * crate 20/12/2014
 * object return after create O!hay web
 */
public class WebOhhay {
	private static Logger log = Logger.getLogger(WebOhhay.class);
	private int webId;
	private String header;
	private String body;
	private String color;
	private String backgroundImg;
	private Set<OhhayLibraryJS> setOhhayLibraryJS = new java.util.HashSet<OhhayLibraryJS>();
	private int role;//viewer or owner
	public WebOhhay(){}
	public WebOhhay(OhhayWebShortBase ohhayWebShort) {
		// TODO Auto-generated constructor stub
		this.webId = ohhayWebShort.getWebId();
		this.header = ohhayWebShort.getCv201();
		//using lazy load for home page
		if(ohhayWebShort.getFo100() == ApplicationConstant.FO100_SUPER_ADMIN)
			role = 1;
		setBody(ohhayWebShort.getCv202());
		this.color = ohhayWebShort.getCv203();
		this.backgroundImg = ohhayWebShort.getCv204();
		log.info("--cv204:"+this.backgroundImg);
		this.setOhhayLibraryJS = ohhayWebShort.getSetOhhayLibraryJS();
		/*
		 * load color to ZUSS
		 */
		if(ohhayWebShort.getCv203()!= null && ApplicationHelper.validateRegex(ohhayWebShort.getCv203(), RegexConstant.COLOR_PATTERN))
		{
			log.info("----set web color ZUSS:"+ohhayWebShort.getCv203());
			ZussUtils.M_COLOR = ohhayWebShort.getCv203();
		}
		else
			ZussUtils.M_COLOR = null;

	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	//body goc
	public String getRealBody() {
		return body;
	}
	//body xong khi da toi uu load resource
	public String getBody() {
		/*
		 * 1) toi uu code load cac img
		 */
		Document document = Jsoup.parse(body);
		Elements containers = document.body().select(".container-fluid");
		for(Element container: containers){
			log.info("---xoa bo bg image");
			container.attr("style",container.attr("style")+"background-image: none;");
		}
		/*
		 * 2) lazy load imgs: khong load o che do edit va nhung imgs co class ohhay-img-no-lazy
		 */
		Elements imgs = document.body().select("img");
		for(Element img: imgs){
			if(!img.hasClass(TemplateRule.OHHAY_IMGS_NO_LAZY)){
				img.attr("data-src",img.attr("src"));
				img.attr("src","");
			}
		}
		return document.body().html().replace("\"", "'");
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getWebId() {
		return webId;
	}
	public void setWebId(int webId) {
		this.webId = webId;
	}
	public Set<OhhayLibraryJS> getSetOhhayLibraryJS() {
		return setOhhayLibraryJS;
	}
	public void setSetOhhayLibraryJS(Set<OhhayLibraryJS> setOhhayLibraryJS) {
		this.setOhhayLibraryJS = setOhhayLibraryJS;
	}
	public String getBackgroundImg() {
		return backgroundImg;
	}
	public void setBackgroundImg(String backgroundImg) {
		this.backgroundImg = backgroundImg;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
}
