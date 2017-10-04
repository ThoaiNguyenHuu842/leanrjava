package com.ohhay.web.core.entities.mongo.webchild;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.web.C400MG;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebChildBase;
/*
 * webinaris website
 */
@Document(collection = QbMongoCollectionsName.T500)
public class T500MG extends OhhayWebChildBase implements Serializable{
	public T500MG() {
		// TODO Auto-generated constructor stub
	}
	public T500MG(A500MG a500mg) {
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
		this.fa500 = a500mg.getId();
		try{
			this.n950mg = new N950MG(a500mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public T500MG(T500MG t500mg) {
		this.listC920mg = t500mg.getListC920mg();
		this.listC500mg = t500mg.getListC500mg();
		this.hv101 = t500mg.getHv101();
		this.fo100 = t500mg.getFo100();
		this.cv802 = t500mg.getCv802();
		this.fc800 = t500mg.getFc800();
		this.cn806 = t500mg.getCn806();
		this.cv807 = t500mg.getCv807();
		this.cv808 = t500mg.getCv808();
		this.cv809 = t500mg.getCv809();
		this.fa500 = t500mg.getId();
		try{
			this.n950mg = new N950MG(t500mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	@Override
	public String getFriendlyUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
		return m900mg.getUrlOhhay()+"/draft/child-"+id;
	}
}
