package com.ohhay.web.core.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
/**
 * @author ThoaiNH
 * date create: 04/07/2015
 * store template ohay. all web when create will duplicate from A400
 */
@Document(collection = QbMongoCollectionsName.A900)
public class A400MG extends OhhayWebBase implements Serializable{
	@Transient
	private String nv100;
	@Transient
	private int childs;
	@Transient
	private boolean showfuc;
	@Transient
	private String dateCreate;
	@Transient
	private int roleAdmin;//check role admin on template
	@Transient
	private int editTemplate;//check edit template :  who create to be edit
	@Field("AN402")
	private int an402;//status: published or no (published = 1, no = 0)
	@Field("FA950")
	private int fa950;//category 
	@Field("AV403")
	private String av403;// template name
	@Field("ROWSS")
	private int rowss;
	public A400MG(L400MG l400mg) {
		this.listC920mg = l400mg.getListC920mg();
		this.listC500mg = l400mg.getListC500mg();
		this.hv101 = l400mg.getHv101();
		this.cv802 = l400mg.getCv802();
		this.fc800 = l400mg.getFc800();
		this.cv807 = l400mg.getCv807();
		this.cv808 = l400mg.getCv808();
		this.cv809 = l400mg.getCv809();
		try{
			this.n950mg = new N950MG(l400mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public A400MG(C400MG c400mg) {
		this.listC920mg = c400mg.getListC920mg();
		this.listC500mg = c400mg.getListC500mg();
		this.hv101 = c400mg.getHv101();
		this.cv802 = c400mg.getCv802();
		this.fc800 = c400mg.getFc800();
		this.cv807 = c400mg.getCv807();
		this.cv808 = c400mg.getCv808();
		this.cv809 = c400mg.getCv809();
		try{
			this.n950mg = new N950MG(c400mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public A400MG(A000MG a000MG) {
		this.listC920mg = a000MG.getListC920mg();
		this.listC500mg = a000MG.getListC500mg();
		this.hv101 = a000MG.getHv101();
		this.cv802 = a000MG.getCv802();
		this.fc800 = a000MG.getFc800();
		this.cv807 = a000MG.getCv807();
		this.cv808 = a000MG.getCv808();
		this.cv809 = a000MG.getCv809();
		try{
			this.n950mg = new N950MG(a000MG.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public A400MG() {
		// TODO Auto-generated constructor stub
	}
	public int getAn402() {
		return an402;
	}
	public void setAn402(int an402) {
		this.an402 = an402;
	}
	public int getFa950() {
		return fa950;
	}
	public void setFa950(int fa950) {
		this.fa950 = fa950;
	}
	public String getAv403() {
		return av403;
	}
	public void setAv403(String av403) {
		this.av403 = av403;
	}
	public int getChilds() {
		return childs;
	}
	public void setChilds(int childs) {
		this.childs = childs;
	}
	public String getNv100() {
		return nv100;
	}
	public void setNv100(String nv100) {
		this.nv100 = nv100;
	}
	
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getScreenShot(){
		return TemplateRule.TEMPLATE_ROOT_PATH_A900+id+"/screenshot";
	}
	public String getScreenShotMobile(){
		return TemplateRule.TEMPLATE_ROOT_PATH_A900+id+"/screenshotMobile";
	}
	@Override
	public String getFriendlyUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isShowfuc() {
		return showfuc;
	}
	public void setShowfuc(boolean showfuc) {
		this.showfuc = showfuc;
	}
	public int getRowss() {
		return rowss;
	}
	public void setRowss(int rowss) {
		this.rowss = rowss;
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
