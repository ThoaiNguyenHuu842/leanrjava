package com.ohhay.core.entities.mongo.other;

import java.io.Console;
import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.core.utils.YouTubeProccessUtil;


/**
 * @author ThoaiNH
 * create 15/07/2015
 */
@SuppressWarnings("serial")
public class M180MG implements Serializable{
	@Field("URL")
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/*lấy chuoi trong src của iframe*/
	public String getUrlIframe() {
		try{
			String s = ApplicationHelper.decodeTopicString(url); 
			Document document = Jsoup.parse(s);
			return document.body().children().get(0).attr("src");
		}catch(Exception e){
			return "";
		}
	}
	
	public String getImgIframe(){
		try{
			if(getUrlIframe()!= ""){
				String youtube = "youtube.com";
				String vimeo = "vimeo.com";
				
				if(getUrlIframe().contains(youtube) == true){
					String key =  YouTubeProccessUtil.getYouTubeVideoID(getUrlIframe()).replace(" ", "");
					return "http://img.youtube.com/vi/"+key+"/hqdefault.jpg";
				}else if(getUrlIframe().contains(vimeo) == true){
					String idVimeo = YouTubeProccessUtil.getVimeoVideoID(getUrlIframe()).replace(" ", "");
					return YouTubeProccessUtil.getThumbnailVimeo(idVimeo);
				}else{
					return "";
				}
			}else{
				String s = ApplicationHelper.decodeTopicString(url);
				Document document = Jsoup.parse(s);
				return document.body().select("img").get(0).attr("src");
			}
		}catch(Exception e){
			return "";
		}
	}
	

	
}
