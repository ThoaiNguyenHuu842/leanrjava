package com.ohhay.rest.bonevo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.ohhay.base.rest.QbRestUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.entities.mongo.showtime.W150MG;
import com.ohhay.other.entities.mongo.showtime.W160MG;
import com.ohhay.other.mongo.service.W150MGService;

/**
 * @author ThoaiNH
 *
 */
@Path("w150WebService")
public class W150WebService {
	/*
	 * inser lich chieu webinar
	 */
	@POST
	@Path("insertw150")
	@Produces("application/json")
	public String chayInsertL900(String postString) {
		try {
			JSONObject jsonObject = new JSONObject(postString);
			String hv101 = ApplicationHelper.convertToMD5(jsonObject.get("hv101").toString());
			String wv161 = jsonObject.get("wv161").toString();
			String wv162 = jsonObject.get("wv162").toString();
			String wv163 = jsonObject.get("wv163").toString();
			String wv164 = jsonObject.get("wv164").toString();
			String wv165 = jsonObject.get("wv165").toString();
			String wv166 = jsonObject.get("wv166").toString();
			W150MGService w150mgService = (W150MGService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_W150MG);
			//find exist w150mg
			W150MG w150mg = w150mgService.findOne(hv101);
			//create w160
			W160MG w160mg = new W160MG();
			w160mg.setWv161(wv161);
			w160mg.setWv162(wv162);
			w160mg.setWv163(wv163);
			w160mg.setWv164(wv164);
			w160mg.setWv165(wv165);
			w160mg.setWv166(wv166);
			//isnert new
			if(w150mg!=null){
				w150mg.getListW160mgs().add(w160mg);
				//update to mongo
				w150mgService.updateW150(w150mg);
			}
			//add
			else
			{
				//create w150
				W150MG w150 = new W150MG();
				w150.setHv101(hv101);
				//add to list
				List<W160MG> listW160mgs = new ArrayList<W160MG>();
				listW160mgs.add(w160mg);
				w150.setListW160mgs(listW160mgs);
				//insert to mongo
				w150mgService.insertW150(w150);
			}
			return QbRestUtils.convertToJsonStringForFunc(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return QbRestUtils.getErrorStatus();
		}

	}
}
