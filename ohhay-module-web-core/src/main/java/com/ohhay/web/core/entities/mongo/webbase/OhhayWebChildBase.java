package com.ohhay.web.core.entities.mongo.webbase;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.TemplateRule;

/**
 * @author ThoaiNH
 * create
 */
public abstract class OhhayWebChildBase extends OhhayWebBase implements Serializable{
	@Field("FA500")
	protected int fa500;//orginal template
	@Field("CN806")
	protected int cn806;//template pos: use for url .../child-n
	@Field("PARID")
	protected int webParentId;// id wed parent
	@Field("ORGINAL")
	protected int orginal;//no-orginal: -1, orginal: 0
	public int getCn806() {
		return cn806;
	}
	public void setCn806(int cn806) {
		this.cn806 = cn806;
	}
	public int getWebParentId() {
		return webParentId;
	}
	public void setWebParentId(int webParentId) {
		this.webParentId = webParentId;
	}
	public int getOrginal() {
		return orginal;
	}
	public void setOrginal(int orginal) {
		this.orginal = orginal;
	}
	public int getFa500() {
		return fa500;
	}
	public void setFa500(int fa500) {
		this.fa500 = fa500;
	}
	@Override
	public String getScreenShot(){
		return TemplateRule.TEMPLATE_ROOT_PATH_A900+"c"+fa500+"/screenshot";
	}
}
