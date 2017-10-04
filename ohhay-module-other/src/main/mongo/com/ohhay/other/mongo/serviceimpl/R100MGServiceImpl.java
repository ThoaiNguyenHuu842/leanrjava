package com.ohhay.other.mongo.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.QbMongoCollectionsName;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.mongo.report.R100MG;
import com.ohhay.other.entities.mongo.report.R110MG;
import com.ohhay.other.entities.mongo.report.R120MG;
import com.ohhay.other.mongo.service.R100MGService;


@Service(value = SpringBeanNames.SERVICE_NAME_R100MG)
@Scope("prototype")
public class R100MGServiceImpl implements R100MGService {
	@Override
	public int insertTabR100(int fo100, int colno) {
		// TODO Auto-generated method stub
		try{
			TemplateService mgService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String ss = dateFormat.format(date);
			String s[]= ss.split("/");
			int day = Integer.parseInt(s[0]);
			int month = Integer.parseInt(s[1]);
			int year = Integer.parseInt(s[2]);
			R100MG r100mg = mgService.findDocumentById(fo100, fo100, R100MG.class);
			//insert new r100
			if(r100mg == null){
				r100mg = new R100MG();
				r100mg.setId(fo100);
				//create r120mg
				R120MG r120mg = new R120MG(colno,1);
				//merge
				List<R120MG> listR120mgs = new ArrayList<R120MG>();
				listR120mgs.add(r120mg);
				//create r110mg			
				R110MG r110mg = new R110MG(year,month,day,listR120mgs);
				List<R110MG> listR110mgs = new ArrayList<R110MG>();
				listR110mgs.add(r110mg);
				r100mg.setListR110mgs(listR110mgs);
			}
			else
			{
				boolean foundDateRep = false;
				//find current date report
				for(R110MG r110mg:r100mg.getListR110mgs()){
					//if found to day report
					if(r110mg.getMonth() == month && r110mg.getDay() == day && r110mg.getYear() == year){
						foundDateRep = true;
						//if find report of current day
						boolean foundColnoReport = false;
						for(R120MG r120mg:r110mg.getListR120mgs()){
							if(r120mg.getColno() == colno)
							{
								foundColnoReport = true;
								r120mg.setTotal(r120mg.getTotal()+1);
								break;
							}
						}
						//else - not found colno report
						if(foundColnoReport == false){
							//create r120mg
							R120MG r120mg = new R120MG(colno, 1);
							r110mg.getListR120mgs().add(r120mg);
						}
						break;
					}
				}
				//else not found day report
				if(foundDateRep == false){
					//create r120mg
					R120MG r120mg = new R120MG(colno,1);
					//merge
					List<R120MG> listR120mgs = new ArrayList<R120MG>();
					listR120mgs.add(r120mg);
					//create r110mg			
					R110MG r110mg = new R110MG(year,month,day,listR120mgs);
					r100mg.getListR110mgs().add(r110mg);
				}
			}
			mgService.saveDocument(fo100, r100mg, QbMongoCollectionsName.R100);
			return 1;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}

	

}
