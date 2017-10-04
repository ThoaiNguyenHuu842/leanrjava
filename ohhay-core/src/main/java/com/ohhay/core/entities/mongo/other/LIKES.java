package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.OhhayDefaultData;

/**
 * @author ThongQB create 24/8/2015 tag cloud of topic
 */

@SuppressWarnings("serial")
public class LIKES implements Serializable {
	@Id
	private String id;
	@Field("FO100")
	private int fo100;
	@Field("MD188")
	private Date md188;
	@Transient
	private String urlAvarta;
	@Transient
	private String region;
	@Transient
	private String mv905;
	@Transient
	private String mv908;
	@Transient
	private String mv903;
	
	
	public String getMv903() {
		return mv903;
	}

	public void setMv903(String mv903) {
		this.mv903 = mv903;
	}

	public void setUrlAvarta(String urlAvarta) {
		this.urlAvarta = urlAvarta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getFo100() {
		return fo100;
	}

	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}

	public Date getMd188() {
		return md188;
	}

	public void setMd188(Date md188) {
		this.md188 = md188;
	}
	
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMv905() {
		return mv905;
	}

	public void setMv905(String mv905) {
		this.mv905 = mv905;
	}

	public String getMv908() {
		return mv908;
	}

	public void setMv908(String mv908) {
		this.mv908 = mv908;
	}

	public String getUrlAvarta() {
		String urlAvarta = null;
		if (mv908 != null && mv908.length() > 0)
			urlAvarta = getImgLink() + mv908;
		else {
			if (mv905.toLowerCase().equals("m"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_M;
			else if (mv905.toLowerCase().equals("f"))
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_F;
			else
				urlAvarta = OhhayDefaultData.DEFAULT_AVATAR_N;
		}
		return urlAvarta;
	}

	public String getImgLink() {
		if (region != null)
			return "https://" + region.toLowerCase()
					+ "-oohhay.s3.amazonaws.com/" + fo100 + "/";
		else
			return "https://oohhay.s3.amazonaws.com/" + fo100 + "/";
	}

	@Override
	public String toString() {
		return "LIKES [id=" + id + ", fo100=" + fo100 + ", md188=" + md188
				+ ", urlAvarta=" + urlAvarta + ", region=" + region + ", mv905="
				+ mv905 + ", mv908=" + mv908 + ", mv903=" + mv903 + "]";
	}
	
	
}
