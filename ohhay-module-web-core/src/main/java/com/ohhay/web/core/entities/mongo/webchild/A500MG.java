package com.ohhay.web.core.entities.mongo.webchild;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebChildBase;
/*
 * colection C900 trong mongoDb dc chia thanh C400-C920-C900
 */

@Document(collection = QbMongoCollectionsName.A500)
public class A500MG extends OhhayWebChildBase implements Serializable{
	@Transient
	private int roleAdmin;//check role admin on template
	@Transient
	private int editTemplate;//check edit template :  who create to be edit
	public A500MG() {
		// TODO Auto-generated constructor stub
	}
	public String getScreenShot(){
		return TemplateRule.TEMPLATE_ROOT_PATH_A900+"c"+id+"/screenshot";
	}
	public String getScreenShotMobile(){
		return TemplateRule.TEMPLATE_ROOT_PATH_A900+"c"+id+"/screenshotMobile";
	}
	@Override
	public String getFriendlyUrl() {
		// TODO Auto-generated method stub
		if(getFriendlyUrlKey() != null)
			return ApplicationConstant.CONTEXT_PATH + "c"+id+"/"+getFriendlyUrlKey();
		return null;
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getRoleAdmin() {
		return roleAdmin;
	}
	public void setRoleAdmin(int roleAdmin) {
		this.roleAdmin = roleAdmin;
	}
	public int getEditTemplate() {
		return editTemplate;
	}
	public void setEditTemplate(int editTemplate) {
		this.editTemplate = editTemplate;
	}
	
}
