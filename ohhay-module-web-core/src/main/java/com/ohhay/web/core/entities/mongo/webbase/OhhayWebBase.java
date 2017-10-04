package com.ohhay.web.core.entities.mongo.webbase;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.constant.TemplateRule;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.mongo.service.C800MGService;

/**
 * @author ThoaiNH
 * create: 19/08/2014
 * date update: 01/07/2015: them cv802full variable
 */
public abstract class OhhayWebBase {
	@Id
	protected int id;// fc400
	@Field("PART")
	protected List<C920MG> listC920mg;
	@Field("LANGUAGES")
	protected List<LanguageMG> listC500mg;
	@Field("HV101")
	protected String hv101;
	@Field("FO100")
	protected int fo100;
	@Field("CV802")
	protected String cv802;
	@Field("CV805")
	protected String cv805;
	@Field("CV807")
	protected String cv807;//web color
	@Field("CV808")
	protected String cv808;//template name
	@Field("CV809")
	protected String cv809;//web background repeat
	@Field("CL946")
	protected Date cl946;//create
	@Field("CL948")
	protected Date cl948;//update
	@Field("FC800")
	protected int fc800;//orginal template of a900
	@Field("FA900")
	protected int fa900;//root template of web (a900)
	@Field("VISIBLE")
	protected int visible;//an: -1, hien: 0
	@Field("INFO")
	protected N950MG n950mg;
	@Transient
	protected String cv802FullForApp;//use for post to app
	@Transient Object po100;//check session with person create teamplate
	public int getFc800() {
		return fc800;
	}
	public void setFc800(int fc800) {
		this.fc800 = fc800;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<C920MG> getListC920mg() {
		return listC920mg;
	}
	public void setListC920mg(List<C920MG> listC920mg) {
		this.listC920mg = listC920mg;
	}
	public List<LanguageMG> getListC500mg() {
		return listC500mg;
	}
	public void setListC500mg(List<LanguageMG> listC500mg) {
		this.listC500mg = listC500mg;
	}
	public String getHv101() {
		return hv101;
	}
	public void setHv101(String hv101) {
		this.hv101 = hv101;
	}
	public int getFo100() {
		return fo100;
	}
	public void setFo100(int fo100) {
		this.fo100 = fo100;
	}
	public String getCv802() {
		return cv802;
	}
	public void setCv802(String cv802) {
		this.cv802 = cv802;
	}
	public Date getCl946() {
		return cl946;
	}
	public void setCl946(Date cl946) {
		this.cl946 = cl946;
	}
	public Date getCl948() {
		return cl948;
	}
	public void setCl948(Date cl948) {
		this.cl948 = cl948;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getCv807() {
		return cv807;
	}
	public void setCv807(String cv807) {
		this.cv807 = cv807;
	}
	public N950MG getN950mg() {
		return n950mg;
	}
	public void setN950mg(N950MG n950mg) {
		this.n950mg = n950mg;
	}
	public String getCv805() {
		return cv805;
	}
	public void setCv805(String cv805) {
		this.cv805 = cv805;
	}
	public String getCv802FullForApp() {
		return cv802FullForApp;
	}
	public void setCv802FullForApp(String cv802FullForApp) {
		this.cv802FullForApp = cv802FullForApp;
	}
	public int getFa900() {
		return fa900;
	}
	public void setFa900(int fa900) {
		this.fa900 = fa900;
	}
	public String getCv808() {
		return cv808;
	}
	public void setCv808(String cv808) {
		this.cv808 = cv808;
	}
	public String getCv809() {
		return cv809;
	}
	public void setCv809(String cv809) {
		this.cv809 = cv809;
	}
	
	/**
	 * friendly url if user set
	 * @return
	 */
	public abstract String getFriendlyUrl();
	/**
	 * url to access web
	 * @return
	 */
	public abstract String getUrl();
	public String getFriendlyUrlKey(){
		if(n950mg != null && n950mg.getNv966() != null)
		{
			//String key = ApplicationHelper.proccessFriendlyKey(n950mg.getNv966());
			String key = n950mg.getNv966();
			return key;
		}
		else
			return null;
	}
	/**
	 * orginal screen shot
	 * @return
	 */
	public String getCv802Full() {
		C800MGService c800mgService = (C800MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C800MG);
		return TemplateRule.TEMPLATE_ROOT_PATH+c800mgService.getCv802Template(fc800)+TemplateRule.TEMPLATE_TEMPLATE_SCREENSHOT;
	}
	/**
	 * @return
	 */
	public String getCv802IndexUrl() {
		C800MGService c800mgService = (C800MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_C800MG);
		return TemplateRule.TEMPLATE_ROOT_PATH+c800mgService.getCv802Template(fc800);
	}
	/**
	 * a900 screen shot
	 * @return
	 */
	public String getScreenShot(){
		return TemplateRule.TEMPLATE_ROOT_PATH_A900+fa900+"/screenshot";
	}
}
