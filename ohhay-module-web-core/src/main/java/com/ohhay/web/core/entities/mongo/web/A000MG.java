package com.ohhay.web.core.entities.mongo.web;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.web.core.entities.mongo.webbase.N950MG;
import com.ohhay.web.core.entities.mongo.webbase.OhhayWebBase;
import com.ohhay.web.core.entities.mongo.webchild.C500MG;
import com.ohhay.web.core.entities.mongo.webchild.T500MG;
import com.ohhay.web.core.entities.mongo.webchild.W500MG;
/**
 * @author ThoaiNH
 * create 01/09/2015
 * temp web use to create web in template cms
 */
@Document(collection = QbMongoCollectionsName.A000)
public class A000MG extends OhhayWebBase implements Serializable{
	public A000MG(C500MG l400mg) {
		this.id = l400mg.getId();
		this.listC920mg = l400mg.getListC920mg();
		this.listC500mg = l400mg.getListC500mg();
		this.hv101 = l400mg.getHv101();
		this.fo100 = l400mg.getFo100();
		this.cv802 = l400mg.getCv802();
		this.fc800 = l400mg.getFc800();
		this.cv807 = l400mg.getCv807();
		this.cv808 = l400mg.getCv808();
		this.cv809 = l400mg.getCv809();
		this.n950mg = new N950MG(l400mg.getN950mg());
	}
	public A000MG(W400MG w400mg) {
		this.id = w400mg.getId();
		this.listC920mg = w400mg.getListC920mg();
		this.listC500mg = w400mg.getListC500mg();
		this.hv101 = w400mg.getHv101();
		this.fo100 = w400mg.getFo100();
		this.cv802 = w400mg.getCv802();
		this.fc800 = w400mg.getFc800();
		this.cv807 = w400mg.getCv807();
		this.cv808 = w400mg.getCv808();
		this.cv809 = w400mg.getCv809();
		this.n950mg = new N950MG(w400mg.getN950mg());
	}
	public A000MG(W500MG w500mg) {
		this.id = w500mg.getId();
		this.listC920mg = w500mg.getListC920mg();
		this.listC500mg = w500mg.getListC500mg();
		this.hv101 = w500mg.getHv101();
		this.fo100 = w500mg.getFo100();
		this.cv802 = w500mg.getCv802();
		this.fc800 = w500mg.getFc800();
		this.cv807 = w500mg.getCv807();
		this.cv808 = w500mg.getCv808();
		this.cv809 = w500mg.getCv809();
		this.n950mg = new N950MG(w500mg.getN950mg());

	}
	public A000MG(B400MG b400mg) {
		this.id = b400mg.getId();
		this.listC920mg = b400mg.getListC920mg();
		this.listC500mg = b400mg.getListC500mg();
		this.hv101 = b400mg.getHv101();
		this.fo100 = b400mg.getFo100();
		this.cv802 = b400mg.getCv802();
		this.fc800 = b400mg.getFc800();
		this.cv807 = b400mg.getCv807();
		this.cv808 = b400mg.getCv808();
		this.cv809 = b400mg.getCv809();
		this.n950mg = new N950MG(b400mg.getN950mg());

	}
	public A000MG(T400MG t400mg) {
		this.id = t400mg.getId();
		this.listC920mg = t400mg.getListC920mg();
		this.listC500mg = t400mg.getListC500mg();
		this.hv101 = t400mg.getHv101();
		this.fo100 = t400mg.getFo100();
		this.cv802 = t400mg.getCv802();
		this.fc800 = t400mg.getFc800();
		this.cv807 = t400mg.getCv807();
		this.cv808 = t400mg.getCv808();
		this.cv809 = t400mg.getCv809();
		this.fa900 = t400mg.getFa900();
		try{
			this.n950mg = new N950MG(t400mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	public A000MG(A400MG a400mg) {
		this.listC920mg = a400mg.getListC920mg();
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
	public A000MG(T500MG t500mg) {
		this.id = t500mg.getId();
		this.listC920mg = t500mg.getListC920mg();
		this.listC500mg = t500mg.getListC500mg();
		this.hv101 = t500mg.getHv101();
		this.fo100 = t500mg.getFo100();
		this.cv802 = t500mg.getCv802();
		this.fc800 = t500mg.getFc800();
		this.cv807 = t500mg.getCv807();
		this.cv808 = t500mg.getCv808();
		this.cv809 = t500mg.getCv809();
		try{
			this.n950mg = new N950MG(t500mg.getN950mg());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	public A000MG(L400MG l400mg) {
		this.id = l400mg.getId();
		this.listC920mg = l400mg.getListC920mg();
		this.listC500mg = l400mg.getListC500mg();
		this.hv101 = l400mg.getHv101();
		this.fo100 = l400mg.getFo100();
		this.cv802 = l400mg.getCv802();
		this.fc800 = l400mg.getFc800();
		this.cv807 = l400mg.getCv807();
		this.cv808 = l400mg.getCv808();
		this.cv809 = l400mg.getCv809();
		this.n950mg = new N950MG(l400mg.getN950mg());

	}
	public A000MG() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getFriendlyUrl() {
		// TODO Auto-generated method stub
		if(getFriendlyUrlKey() != null)
			return ApplicationConstant.CONTEXT_PATH + "h"+fo100+"/"+getFriendlyUrlKey();
		return null;
	}
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
}
