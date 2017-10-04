package com.ohhay.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

/**
 * @author ThoaiNH
 * create 03/11/2014
 * youtube util
 */
public class YouTubeProccessUtil {

	/*
	 * get youtube video id from iframe url
	 */
	public static String getYouTubeVideoID(String videoURL) {
		String s = "";
		try {
			int pos = videoURL.indexOf("youtube.com/embed/") + 18;
			s = videoURL.substring(pos, pos + 11);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}

	/*
	 * add param auto play to iframe youtube at start time
	 */
	public static String addAutoPlay(String srcUrl,String start) {
		try {
			if (srcUrl.indexOf("?") > 0)
			{
				if(srcUrl.indexOf("youtube") > 0)
					return srcUrl += "&autoplay=1&loop=1&enablejsapi=1&start="+start;
				else
					return srcUrl += "&autoplay=1";
			}
			else
			{
				if(srcUrl.indexOf("youtube") > 0)
					return srcUrl += "?autoplay=1&loop=1&enablejsapi=1&start="+start;
				else
					return srcUrl += "?autoplay=1";
			}
				
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/*
	 * validate embed code
	 */
	public static boolean validateEmbedCode(String embedUrl) {
		String s = "";
		try {
			Document document = Jsoup.parse(embedUrl);
			s = document.body().select("iframe").attr("src");
			if(s!= null && s.indexOf("www.youtube.com/embed") > 0)
				return true;
			else if(s!=null && s.indexOf("webinar") > 0)
				return true;			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	/*
	 * get url src of iframe
	 */
	public static String getSrcOfIframe(String embedUrl) {
		String s = "";
		try {
			Document document = Jsoup.parse(embedUrl);
			s = document.body().select("iframe").attr("src");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
	
	/*
	 * get youtube video id from iframe url
	 */
	public static String getVimeoVideoID(String videoURL) {
		String s = "";
		try {
			int pos = videoURL.indexOf("vimeo.com/video/") + 16;
			int len = videoURL.length();
			s = videoURL.substring(pos, len);
			int pos2 = s.indexOf("?");
			s = s.substring(0, pos2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
	
	public static String getThumbnailVimeo(String id) throws ParserConfigurationException, SAXException{
		try {
			String url = "http://vimeo.com/api/v2/video/"+id.trim()+".xml";
			InputStream is = new URL(url).openStream();

		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    org.w3c.dom.Document doc = dBuilder.parse(is);
		    doc.getDocumentElement().normalize();
		    
		    String val = doc.getElementsByTagName("thumbnail_large").item(0).getTextContent();
		    
			return val;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
