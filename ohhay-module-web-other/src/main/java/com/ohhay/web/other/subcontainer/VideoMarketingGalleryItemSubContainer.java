package com.ohhay.web.other.subcontainer;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M940MG;
import com.ohhay.core.entities.mongo.videomarketing.V910MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.YouTubeProccessUtil;
import com.ohhay.web.core.entities.mongo.webbase.C900MG;
import com.ohhay.web.core.load.util.AbstractSubContainer;
import com.ohhay.web.core.utils.WebCreateParam;
import com.ohhay.web.core.utils.WebRequestParam;

/**
 * @author ThoaiNH
 *
 */
@Service
public class VideoMarketingGalleryItemSubContainer extends AbstractSubContainer {
	private static Logger log = Logger.getLogger(VideoMarketingGalleryItemSubContainer.class);
	private int fl400;
	private M900MG m900mg;//fo100 of owner website
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ohhay.webcreate.util.ISubContainer#mapElementToBoxExtend(org.jsoup
	 * .nodes.Element, java.util.List, int, java.lang.String) dung tam key cho
	 * fo100
	 */
	@Override
	public void mapElementToBoxExtend(Element element, List<C900MG> listC900mgs, int role, String key, int fo100)
			throws Exception {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		log.info("---pv910 video:"+key);
		/*
		 * 1) find orginal gallery item of webinaris 
		 */
		C900MG c900mgClone = null;
		for (int i = 0; i < listC900mgs.size(); i++) {
			C900MG c900mg = listC900mgs.get(i);
			if (c900mg.getCv902().equals(TemplateRule.OHHAY_DATA_QB_TYPE_GALLERY_ITEM)) {
				log.info("---found video element id:"+ c900mg.getPc900());
				c900mgClone = new C900MG(c900mg);
				break;
			}
		}
		StringBuilder builderDbSubContainer = new StringBuilder("");
		/*
		 * 2) create list C900 from list B050
		 */
		fl400 = Integer.parseInt(key);
		V910MG v910mg = templateService.findDocument(fo100, V910MG.class, new QbCriteria(QbMongoFiledsName.WEBID, fl400));
		m900mg = templateService.findDocumentById(fo100, v910mg.getFo100(), M900MG.class);
		Collections.sort(v910mg.getListM940mgs());
		for (M940MG m940mg : v910mg.getListM940mgs()) {
			builderDbSubContainer.append(processGalleryItem(c900mgClone, role, m940mg, this.m900mg));
			/*builderDbSubContainer
					.append(" <li videoId=\""+m940mg.getId()+"\" class=\"qb-video-item\" url=\"<iframe id='youtube-video' enablejsapi='1' width='97%' height='448' frameborder='0' allowfullscreen='' src='"
							+YouTubeProccessUtil.addAutoPlay(YouTubeProccessUtil.getSrcOfIframe(m940mg.getMv943()),"0")+"'></iframe>\" content=\""
							+ m940mg.getMv942()
							+ "\" title=\""
							+ m940mg.getMv941()
							+ "\"><a class='"+ApplicationConstant.OHHAY_EDIT_CLASS_NOT_PREVENT_A_CLICK+"' href='#' title=''><img src='http://img.youtube.com/vi/"
							+ YouTubeProccessUtil.getYouTubeVideoID(m940mg.getMv943())
							+ "/hqdefault.jpg' alt='' /></a></li>");*/
		}
		element.append(builderDbSubContainer.toString());
		/*
		 * 3) remove attr if viewer
		 */
		if (role == ApplicationConstant.ROLE_VIEWER) {
			element.removeAttr(TemplateRule.OHHAY_DATA_QB_SUB_CONTAINER);
		}
	}
	/*
	 * process html for gallery item video
	 */
	public String processGalleryItem(C900MG c900mg, int role, M940MG m940mg, M900MG m900mg) {
		Document document = Jsoup.parse(c900mg.getCv906());
		Elements ele1 = document.body().select("li");
		Elements ele2 = document.body().select("img");
		Elements ele3 = document.body().select("a");
		Elements ele5 = document.body().select(".qb-iframe-content");
		Elements ele4 = document.body().select(".qb-iframe-title");
		ele1.attr("videoId", String.valueOf(m940mg.getId()));
		/*
		 * 1) url to play video
		 */
		ele1.attr("url","<iframe id='youtube-video' enablejsapi='1' width='97%' height='448' frameborder='0' allowfullscreen='' src='"
							+YouTubeProccessUtil.addAutoPlay(YouTubeProccessUtil.getSrcOfIframe(m940mg.getMv943()),"0")+"'></iframe>");
		ele1.attr("content", m940mg.getMv942());
		ele1.attr("title", m940mg.getMv941());
		if(m940mg.getMv946() != null)
			ele1.attr("videokey", m940mg.getMv946());
		ele1.addClass("qb-video-item");
		/*
		 * 2) link iframe register
		 */
		Q100 q100 = AuthenticationHelper.getUserLogin();
		//not register if is owner
		if(q100 != null && q100.getPo100()== fl400)
			m940mg.setMn945(0);
		if(m940mg.getMn945() == -1)
		{
			ele1.attr("must-register","1");
			if(m940mg.getMv947()!=null)
			{
				String cfi = "N";
				if(m940mg.getMn948() == -1)
					cfi = "Y";
				if(q100 != null)
				{
					String fName = q100.getM900mg().getMv901Decrypt() != null?q100.getM900mg().getMv901Decrypt().trim().replace(" ", "+"):"";
					String lName = q100.getM900mg().getMv902Decrypt() != null?q100.getM900mg().getMv902Decrypt().trim().replace(" ", "+"):"";
					String sex   = q100.getM900mg().getMv905() != null?q100.getM900mg().getMv905():"M";
					ele1.attr("urlResigter", m940mg.getMv947()+"&fname="+fName
							+"&lname="+lName
							+"&sex="+sex
							+"&email="+q100.getM900mg().getMv903Decrypt()
							+"&phone="+q100.getM900mg().getMv907()+"&cfi="+cfi+"&cde="+m940mg.getMv946()+"&fo100="+q100.getPo100()+"&sdt="+m900mg.getHv101());
				}
				else
					ele1.attr("urlResigter", m940mg.getMv947()+"&cfi="+cfi+"&cde="+m940mg.getMv946()+"&fo100=0"+"&sdt="+m900mg.getHv101());
					
			}
		}
		else
			ele1.attr("must-register","0");
		ele3.addClass(TemplateRule.OHHAY_EDIT_CLASS_NOT_PREVENT_A_CLICK);
		/*
		 * 3) thumbnail image
		 */
		String imgThumbnail = YouTubeProccessUtil.getYouTubeVideoID(m940mg.getMv943()).replace(" ", "");
		if(imgThumbnail != null && imgThumbnail.length() == 11)
		{
			ele3.append("<i class='fa fa-play-circle-o qb-play-btn'></i>");
			ele2.attr("src","http://img.youtube.com/vi/"+imgThumbnail+"/hqdefault.jpg");
		}
		else
			ele3.html("<div class=\"qb-out-default-video-thumbnail\"><label title=\""+m940mg.getMv941()+"\">Webinar "+m940mg.getId()+"</label></div>");
		/*
		 * 4) load content and title
		 */
		if(ele4 != null && ele4.size() > 0)
			ele4.get(0).html(m940mg.getMv941());
		if(ele5 != null && ele5.size() > 0)
			ele5.get(0).html(m940mg.getMv942());
		log.info("--img thumb:"+imgThumbnail);
		log.info("--video item:"+document.body().html());
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
