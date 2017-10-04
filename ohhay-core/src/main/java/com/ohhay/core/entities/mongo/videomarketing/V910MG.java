package com.ohhay.core.entities.mongo.videomarketing;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.mongo.profile.M940MG;
import com.ohhay.core.mongo.service.AdminMGService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * date create 10:40 am 06/04/2015
 *  * web video info:
 * - menu title
 * - menu icon
 * - list video
 */
@Document(collection = QbMongoCollectionsName.V910)
public class V910MG implements Serializable{
	@Id
	private long id;
	@Field("FO100")
	private int fo100;
	@Field("WEBID")
	private int webId;
	@Field("VV911")
	private String vv911; //video title
	@Field("VN912")
	private int vn912; //video new id
	@Field("VN913")
	private int vn913; //total video
	@Field("VN914")
	private int vn914; //max index
	@Field("VV915")
	private String vv915; //icon font awesome
	@Field("VL948")
	private java.util.Date vl948;//date update
	@Field("VIDEOS")
	private List<M940MG> listM940mgs;	
	@Transient
	private int visible;//0: public; -1: private
	public V910MG(long id, int fo100, int webId, String vv911, String vv915,
			List<M940MG> listM940mgs) {
		super();
		this.id = id;
		this.fo100 = fo100;
		this.webId = webId;
		this.vv911 = vv911;
		this.listM940mgs = listM940mgs;
		vn912 = 1;
		vn913 = 0;
		vn914 = 1;
		this.vv915 = vv915;
		vl948 = new java.util.Date();
	}
	public String getVv911() {
		return vv911;
	}
	public void setVv911(String vv911) {
		this.vv911 = vv911;
	}
	public List<M940MG> getListM940mgs() {
		return listM940mgs;
	}
	public void setListM940mgs(List<M940MG> listM940mgs) {
		this.listM940mgs = listM940mgs;
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
	
	public int getVn912() {
		return vn912;
	}
	public void setVn912(int vn912) {
		this.vn912 = vn912;
	}
	public int getVn913() {
		return vn913;
	}
	public void setVn913(int vn913) {
		this.vn913 = vn913;
	}
	public int getVn914() {
		return vn914;
	}
	public void setVn914(int vn914) {
		this.vn914 = vn914;
	}
	/*
	 * get font of icon menu
	 */
	public String getVv915() {
		String []ss = vv915.split(" ");
		if(ss.length > 1)
			return ss[0];
		return vv915;
	}
	public void setVv915(String vv915) {
		this.vv915 = vv915;
	}
	
	public java.util.Date getVl948() {
		return vl948;
	}
	public void setVl948(java.util.Date vl948) {
		this.vl948 = vl948;
	}	
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getThumbnail() {
		AdminMGService adminMGService = (AdminMGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_ADMINMG);
		return TemplateRule.TEMPLATE_ROOT_PATH+adminMGService.getThumbnailOfWeb(fo100, webId, QbMongoCollectionsName.L900)+TemplateRule.TEMPLATE_TEMPLATE_SCREENSHOT;
	}
}
