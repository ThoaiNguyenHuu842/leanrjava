package com.ohhay.web.core.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.web.core.mongo.dao.E920MGDao;
import com.ohhay.web.lego.entities.mongo.web.E920MG;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_E920MG)
@Scope("prototype")
public class E920MGDaoImpl extends QbMongoDaoSupport implements E920MGDao {
	Logger logger = Logger.getLogger(E920MGDaoImpl.class);
	@Autowired
	TemplateService templateservice;

	@Override
	public List<E920MG> listOfTabLibrary(int fo100, int libType, String pvSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.EVO_LISTOFTAB_LIB);
			setParameterNumber(fo100);
			setParameterNumber(libType);
			setParameterString(pvSearch);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (kq != null) {
				List<E920MG> listE920MG = new ArrayList<E920MG>();
				JSONArray array = new JSONArray(kq);
				/*
				 * "_id" : NumberLong(2147), "WEBID" : NumberLong(297), "FO100"
				 * : 629, "LIB_TYPE" : 2, "BL946" : "25-12-2015", "BL949" :
				 * "14-1-2016", "OTAGS" : [ "list view", "list" ], "NV100" :
				 * "Hằng Tăng", "EV405" : { "_id" : 297 }, "TOTAL_USE" : 1
				 */
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					E920MG e920mg = new E920MG();
					e920mg.setId(Long.parseLong(obj.getString("_id")));
					e920mg.setWebId(Long.parseLong(obj.getString("WEBID")));
					e920mg.setLibType(Integer.parseInt(obj.getString("LIB_TYPE")));
					if (obj.has("TOTAL_ADDED"))
						e920mg.setTotalAdded(Integer.parseInt(obj.getString("TOTAL_ADDED")));
					if (obj.has("LIB_THUMB"))
						e920mg.setLibThumb(obj.getString("LIB_THUMB"));
					if (obj.has("BL946"))
						e920mg.setBl946String(obj.getString("BL946"));
					if (obj.has("BL949"))
						e920mg.setBl949String(obj.getString("BL949"));
					e920mg.setOwnerName(obj.getString("NV100"));
					e920mg.setWebName(obj.getString("EV405"));
					try {
						e920mg.setLibTitle(obj.getString("LIB_TITLE"));
					} catch (Exception e) {
						// TODO: handle exception
						logger.info(e);
					}
					// e920mg.setTotalUsed(Integer.parseInt(obj.getString("TOTAL_USE")));
					e920mg.setFo100(Integer.parseInt(obj.getString("FO100")));
					if (obj.has("OTAGS")) {
						List<String> listOtags = new ArrayList<String>();
						JSONArray otags = obj.getJSONArray("OTAGS");
						for (int j = 0; j < otags.length(); j++) {
							listOtags.add(otags.getString(j));
						}
						e920mg.setOtags(listOtags);
					}
					if (obj.has("EDITABLE"))
						e920mg.setEditAble(Integer.parseInt(obj.getString("EDITABLE")));
					if(obj.has("VOTE_STAR_AVG"))
						e920mg.setVoteStarAvg(Float.parseFloat(obj.getString("VOTE_STAR_AVG")));
					if(obj.has("VOTE_TOTAL"))
						e920mg.setTotalRate(obj.getInt("VOTE_TOTAL"));
					if(obj.has("RE_VOTE"))
						e920mg.setReVote(obj.getInt("RE_VOTE"));
					listE920MG.add(e920mg);
				}
				return listE920MG;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int incTotalAdd(int pe920, int fo100) {
		logger.info("get lib to web from " + pe920);
		Query query = new Query();
		query.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(pe920));
		try {
			E920MG e920MGResult = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).findOne(query,
					E920MG.class);
			return templateservice.updateOneField(fo100, E920MG.class, (int) e920MGResult.getId(), "TOTAL_ADDED",
					e920MGResult.getTotalAdded() + 1, null);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public String e920UpdateVote(int pe920, int fo100, int star) {
		try {
			setFunctionName(QbMongoFunctionNames.E920_UPDATE_VOTE);
			setParameterNumber(pe920);
			setParameterNumber(fo100);
			setParameterNumber(star);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int e920ListOfTabVote(int pe920, int fo100) {
		try {
			setFunctionName(QbMongoFunctionNames.E920_LISTOFTAB_VOTE);
			setParameterNumber(pe920);
			setParameterNumber(fo100);
			int result = Integer.parseInt(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.toString());
			e.printStackTrace();
			return 0;
		}
	}

}
