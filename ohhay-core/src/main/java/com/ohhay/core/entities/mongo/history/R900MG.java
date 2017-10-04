package com.ohhay.core.entities.mongo.history;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ohhay.core.constant.QbMongoCollectionsName;
/**
 * @author ThoaiNH
 * create 12/10/2014
 * lich su hoat dong cua user
 */
@Document(collection = QbMongoCollectionsName.R900)
public class R900MG implements Serializable, Comparable<R900MG>{
	@Id
	private int id;//po100
	@Field("RA901") 
	private List<R910MG> listR910mgs;// danh sach voted
	@Field("RA902")
	private List<R920MG> listR920mgs;//danh sach bookmarked
	@Field("RA903")
	private List<R930MG> listR930mgs;//danh sach share
	@Field("RA902_NEW")
	private List<R920NEWMG> listR920NEWmgs;//danh sach nhung nguoi moi bookmark
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<R910MG> getListR910mgs() {
		return listR910mgs;
	}
	public void setListR910mgs(List<R910MG> listR910mgs) {
		this.listR910mgs = listR910mgs;
	}
	public List<R920MG> getListR920mgs() {
		return listR920mgs;
	}
	public void setListR920mgs(List<R920MG> listR920mgs) {
		this.listR920mgs = listR920mgs;
	}
	public List<R930MG> getListR930mgs() {
		return listR930mgs;
	}
	public void setListR930mgs(List<R930MG> listR930mgs) {
		this.listR930mgs = listR930mgs;
	}
	
	public List<R920NEWMG> getListR920NEWmgs() {
		return listR920NEWmgs;
	}
	public void setListR920NEWmgs(List<R920NEWMG> listR920NEWmgs) {
		this.listR920NEWmgs = listR920NEWmgs;
	}
	@Override
	public int compareTo(R900MG r900MG) {
		// TODO Auto-generated method stub
		if(this.getListR920mgs()!= null && this.getListR920mgs().size()>0 && r900MG.getListR920mgs()!=null && r900MG.getListR920mgs().size()>0)
		{
			R920MG r920mgThis = this.getListR920mgs().get(0);
			R920MG r920mg = r900MG.getListR920mgs().get(0);
			if(r920mg.getRl946().after(r920mgThis.getRl946()))
				return 1;
			else if(r920mg.getRl946().before(r920mgThis.getRl946()))
				return -1;
			else
				return 0;
		}
		return 0;
	}
}
