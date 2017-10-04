package com.ohhay.web.core.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;

/*
 * colection T900 trong mongoDb dc chia thanh T400-C920-C900
 * not use cloneable interface
 */
@Document(collection = QbMongoCollectionsName.T900)
public class T400MG extends OhhayWebBase implements Serializable{
	public T400MG() {
		// TODO Auto-generated constructor stub
	}
	public T400MG(C400MG c400mg) {
		this.listC920mg = c400mg.getListC920mg();
		this.listC500mg = c400mg.getListC500mg();
		this.hv101 = c400mg.getHv101();
		this.fo100 = c400mg.getFo100();
		this.cv802 = c400mg.getCv802();
		this.fc800 = c400mg.getFc800();
		this.cv807 = c400mg.getCv807();
		this.cv808 = c400mg.getCv808();
		this.cv809 = c400mg.getCv809();
		this.fa900 = c400mg.getFa900();
		try{
			this.n950mg = new N950MG(c400mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	public T400MG(A400MG a400mg) {
		this.listC920mg = a400mg.getListC920mg();
		this.listC500mg = a400mg.getListC500mg();
		this.hv101 = a400mg.getHv101();
		this.fo100 = a400mg.getFo100();
		this.cv802 = a400mg.getCv802();
		this.fc800 = a400mg.getFc800();
		this.cv807 = a400mg.getCv807();
		this.cv808 = a400mg.getCv808();
		this.cv809 = a400mg.getCv809();
		try{
			this.n950mg = new N950MG(a400mg.getN950mg());
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
		return m900mg.getUrlOhhay()+"/draft";
	}
}
