package com.ohhay.core.entities.mongo.other;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.entities.Q100;

/**
 * @author ThoaiNH
 * create Oct 28, 2015
 * image album entity
 */
@Document(collection = QbMongoCollectionsName.P950)
public class P950MG implements Serializable{
	@Id
	private long id;
	@Field("FO100")
	private int fo100;
	@Field("WEBID")
	private int webId;
	@Field("PV951")
	private String pv951;// img url
	@Field("SRC")
	private String src;// background or image
	@Field("PV952")
	private String pv952;// thumb url
	@Transient
	private String imgUrl;//store image when trial
	@Transient
	private int rowss;
	
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public int getWebId() {
		return webId;
	}
	public void setWebId(int webId) {
		this.webId = webId;
	}
	public String getPv951() {
		return pv951;
	}
	public void setPv951(String pv951) {
		this.pv951 = pv951;
	}
	public String getRealSrc(){
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(pv952 != null && pv952.trim().length()>0)
			return pv951;//up hinh moi bang node js
		if(q100 != null){
			if(webId == 0)
				return q100.getM900mg().getImgLinkCloudFront() + pv951;
			else
				return q100.getM900mg().getImgLinkCloudFront() + webId + "/" + pv951;
		} else
			return imgUrl;
	}
	public String getThumb(){
		Q100 q100 = AuthenticationHelper.getUserLogin();
		if(pv952 != null && pv952.trim().length()>0)
			return pv952;//up hinh moi bang node js
		if(q100 != null){
			if(webId == 0)
				return q100.getM900mg().getImgLinkCloudFront() + pv951;
			else
				return q100.getM900mg().getImgLinkCloudFront() + webId + "/" + pv951;
		} else
			return imgUrl;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getPv952() {
		return pv952;
	}
	public void setPv952(String pv952) {
		this.pv952 = pv952;
	}
	@Override
	public String toString() {
		return "P950MG [id=" + id + ", fo100=" + fo100 + ", webId=" + webId
				+ ", pv951=" + pv951 + ", src=" + src + ", pv952=" + pv952
				+ ", imgUrl=" + imgUrl + "]";
	}
	
	
	
	
	
	
}
