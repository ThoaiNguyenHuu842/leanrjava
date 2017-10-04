package com.ohhay.web.core.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;

/*
 * colection C900 trong mongoDb dc chia thanh C400-C920-C900
 * not use cloneable interface
 */
@Document(collection = QbMongoCollectionsName.L900)
public class L400MG extends OhhayWebBase implements Serializable, Comparable<L400MG>{
	public L400MG(L400MG l400mg) {
		this.listC920mg = l400mg.getListC920mg();
		this.listC500mg = l400mg.getListC500mg();
		this.hv101 = l400mg.getHv101();
		this.fo100 = l400mg.getFo100();
		this.cv802 = l400mg.getCv802();
		this.fc800 = l400mg.getFc800();
		this.cv807 = l400mg.getCv807();
		this.cv808 = l400mg.getCv808();
		this.cv809 = l400mg.getCv809();
		this.fa900 = l400mg.getFa900();
		this.visible = l400mg.getVisible();
		this.cv805 = l400mg.getCv805();
		try{
			this.n950mg = new N950MG(l400mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	public L400MG(A400MG a400mg) {
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
	public L400MG() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getFriendlyUrl() {
		// TODO Auto-generated method stub
		if(getFriendlyUrlKey() != null)
			return ApplicationConstant.CONTEXT_PATH + "l"+id+"/"+getFriendlyUrlKey();
		return null;
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		if(getFriendlyUrlKey() != null)
			return "l"+id+"/"+getFriendlyUrlKey();
		else {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			M900MG m900mg = templateService.findDocumentById(fo100, fo100, M900MG.class);
			return m900mg.getUrlOhhay()+"/landing-"+id;
		}
	}
	@Override
	public int compareTo(L400MG arg0) {
		// TODO Auto-generated method stub
		if(this.id < arg0.getId())
			return 1;
		else if(this.id > arg0.getId())
			return -1;
		else
			return 0;
	}
}
