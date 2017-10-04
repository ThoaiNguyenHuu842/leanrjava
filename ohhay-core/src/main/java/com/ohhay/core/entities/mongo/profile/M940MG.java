package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.utils.YouTubeProccessUtil;
/**
 * @author ThoaiNH
 * create 19/12/2014
 * item video
 */
public class M940MG implements Serializable, Comparable<M940MG>{
	@Id
	private int id;
	@NotBlank(message="{setting.videomarketing.error.videotitle}")
	@Field("MV941")
	private String mv941;//title
	@NotBlank(message="{setting.videomarketing.error.videocontent}")
	@Field("MV942")
	private String mv942;//content
	@Field("MV943")
	private String mv943;//url embed code
	@Field("MN944")
	private int mn944;//index sort
	@Field("MN945")
	private int mn945;//must register to see nor no (0: no need register,-1: must register)
	@Field("MV946")
	private String mv946;//video code orel
	@Field("MV947")
	private String mv947;//link dang ky xem video tu orel
	@Field("MN948")
	private int mn948;//phai xac nhan ma khi xem video, k can nhan ma khi xem video (-1: phai dk,0: k can dk)
	@Transient
	private int pv910;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 

	public String getMv941() {
		return mv941;
	}

	public void setMv941(String mv941) {
		this.mv941 = mv941;
	}

	public String getMv942() {
		return mv942;
	}

	public void setMv942(String mv942) {
		this.mv942 = mv942;
	}

	public String getMv943() {
		return mv943;
	}

	public void setMv943(String mv943) {
		this.mv943 = mv943;
	}

	public int getMn944() {
		return mn944;
	}

	public void setMn944(int mn944) {
		this.mn944 = mn944;
	}

	public int getMn945() {
		return mn945;
	}

	public void setMn945(int mn945) {
		this.mn945 = mn945;
	}

	public String getMv946() {
		return mv946;
	}

	public void setMv946(String mv946) {
		this.mv946 = mv946;
	}

	public String getMv947() {
		return mv947;
	}

	public void setMv947(String mv947) {
		this.mv947 = mv947;
	}
	
	public int getMn948() {
		return mn948;
	}

	public void setMn948(int mn948) {
		this.mn948 = mn948;
	}
	
	public int getPv910() {
		return pv910;
	}

	public void setPv910(int pv910) {
		this.pv910 = pv910;
	}
	public String getThumbnail(){
		String imgThumbnail = YouTubeProccessUtil.getYouTubeVideoID(mv943).replace(" ", "");
		if(imgThumbnail != null && imgThumbnail.length() == 11)
			return "http://img.youtube.com/vi/"+imgThumbnail+"/hqdefault.jpg";
		else
			return "http://oohhay.com/html/images/61.png";
	}
	@Override
	public int compareTo(M940MG o) {
		// TODO Auto-generated method stub
		if(this.mn944 > o.mn944)
			return 1;
		else if(this.mn944 < o.mn944)
			return -1;
		else
			return 0;
	}
}
