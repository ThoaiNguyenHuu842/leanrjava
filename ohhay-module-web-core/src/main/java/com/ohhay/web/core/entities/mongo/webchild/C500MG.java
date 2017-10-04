package com.ohhay.web.core.entities.mongo.webchild;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebChildBase;
/*
 * colection C900 trong mongoDb dc chia thanh C400-C920-C900
 */
@Document(collection = QbMongoCollectionsName.C500)
public class C500MG extends OhhayWebChildBase implements Serializable{
	public C500MG() {
		// TODO Auto-generated constructor stub
	}
	public C500MG(T500MG t500mg) {
		super();
		this.id = t500mg.getId();
		this.listC920mg = t500mg.getListC920mg();
		this.listC500mg = t500mg.getListC500mg();
		this.hv101 = t500mg.getHv101();
		this.fo100 = t500mg.getFo100();
		this.cv802 = t500mg.getCv802();
		this.cn806 = t500mg.getCn806();
		this.cv807 = t500mg.getCv807();
		this.cv808 = t500mg.getCv808();
		this.cv809 = t500mg.getCv809();
		this.fc800 = t500mg.getFc800();
		this.fa900 = t500mg.getFa900();
		this.fa500 = t500mg.getFa500();
	}
	public C500MG(A500MG a500mg) {
		this.listC920mg = a500mg.getListC920mg();
		this.listC500mg = a500mg.getListC500mg();
		this.hv101 = a500mg.getHv101();
		this.fo100 = a500mg.getFo100();
		this.cv802 = a500mg.getCv802();
		this.fc800 = a500mg.getFc800();
		this.cn806 = a500mg.getCn806();
		this.cv807 = a500mg.getCv807();
		this.cv808 = a500mg.getCv808();
		this.cv809 = a500mg.getCv809();
		this.fa900 = a500mg.getWebParentId();
		this.fa500 = a500mg.getId();
		try{
			this.n950mg = new N950MG(a500mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public C500MG(C500MG c500mg) {
		super();
		this.id = c500mg.getId();
		this.listC920mg = c500mg.getListC920mg();
		this.listC500mg = c500mg.getListC500mg();
		this.hv101 = c500mg.getHv101();
		this.fo100 = c500mg.getFo100();
		this.cv802 = c500mg.getCv802();
		this.cn806 = c500mg.getCn806();
		this.cv807 = c500mg.getCv807();
		this.cv808 = c500mg.getCv808();
		this.cv809 = c500mg.getCv809();
		this.fc800 = c500mg.getFc800();
		this.fa500 = c500mg.getFa500();
		this.webParentId = c500mg.getWebParentId();
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
		if(getFriendlyUrlKey() != null)
			return "c"+id+"/"+getFriendlyUrlKey();
		else {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
			return m900mg.getUrlOhhay()+"/child-"+id;
		}
	}
}
