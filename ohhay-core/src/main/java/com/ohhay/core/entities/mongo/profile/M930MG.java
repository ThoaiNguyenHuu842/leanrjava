package com.ohhay.core.entities.mongo.profile;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
/**
 * @author ThongQB
 * create: 15/08/2015
 * dialog
 */
@Document(collection = QbMongoCollectionsName.M930)
@SuppressWarnings("serial")
public class M930MG implements Serializable{
	@Id
	private int id;// po100
	@Field("NEWBIE_EMAIL_COMFIRM")
	private int newbieEmailConfig;
	@Field("NEWBIE_FIRST_ACCESS")
	private int newbieFirstAccess;
	@Field("NEWBIE_FIRST_ACCESS_MYSITE")
	private int newbieFirstAccessMysite;
	@Field("NEWBIE_FIRST_ACCESS_EVO_TEMPLATE")
	private int newbieFirstAccessEvoTemplate;
	@Field("NEWBIE_FIRST_ACCESS_MOBILE_EDITOR")
	private int newbieFirstAccessMobileEditor;
	@Field("NEWBIE_FIRST_ACCESS_PC_EDITOR")
	private int newbieFirstAccessPCEditor;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNewbieEmailConfig() {
		return newbieEmailConfig;
	}

	public void setNewbieEmailConfig(int newbieEmailConfig) {
		this.newbieEmailConfig = newbieEmailConfig;
	}

	public int getNewbieFirstAccess() {
		return newbieFirstAccess;
	}

	public void setNewbieFirstAccess(int newbieFirstAccess) {
		this.newbieFirstAccess = newbieFirstAccess;
	}

	public int getNewbieFirstAccessMysite() {
		return newbieFirstAccessMysite;
	}

	public void setNewbieFirstAccessMysite(int newbieFirstAccessMysite) {
		this.newbieFirstAccessMysite = newbieFirstAccessMysite;
	}

	public int getNewbieFirstAccessEvoTemplate() {
		return newbieFirstAccessEvoTemplate;
	}

	public void setNewbieFirstAccessEvoTemplate(int newbieFirstAccessEvoTemplate) {
		this.newbieFirstAccessEvoTemplate = newbieFirstAccessEvoTemplate;
	}

	public int getNewbieFirstAccessMobileEditor() {
		return newbieFirstAccessMobileEditor;
	}

	public void setNewbieFirstAccessMobileEditor(int newbieFirstAccessMobileEditor) {
		this.newbieFirstAccessMobileEditor = newbieFirstAccessMobileEditor;
	}

	public int getNewbieFirstAccessPCEditor() {
		return newbieFirstAccessPCEditor;
	}

	public void setNewbieFirstAccessPCEditor(int newbieFirstAccessPCEditor) {
		this.newbieFirstAccessPCEditor = newbieFirstAccessPCEditor;
	}

	@Override
	public String toString() {
		return "M930MG [id=" + id + ", newbieEmailConfig=" + newbieEmailConfig
				+ ", newbieFirstAccess=" + newbieFirstAccess
				+ ", newbieFirstAccessMysite=" + newbieFirstAccessMysite
				+ ", newbieFirstAccessEvoTemplate="
				+ newbieFirstAccessEvoTemplate
				+ ", newbieFirstAccessMobileEditor="
				+ newbieFirstAccessMobileEditor + ", newbieFirstAccessPCEditor="
				+ newbieFirstAccessPCEditor + "]";
	}
}
