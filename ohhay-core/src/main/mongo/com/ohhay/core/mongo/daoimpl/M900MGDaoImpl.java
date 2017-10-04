package com.ohhay.core.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.profile.LanguageMG;
import com.ohhay.core.entities.mongo.profile.M900DesMG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.entities.mongo.profile.M930MG;
import com.ohhay.core.mongo.dao.M900MGDao;
import com.ohhay.core.mongo.service.M150MGService;
import com.ohhay.core.mongo.service.R900MGService;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_M900MG)
@Scope("prototype")
public class M900MGDaoImpl extends QbMongoDaoSupport implements M900MGDao {
	private static Logger log = Logger.getLogger(M900MGDaoImpl.class);

	@Override
	public List<M900MG> findM900All(int limit) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.limit(limit);
			List<M900MG> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, 0).find(query2, M900MG.class);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int getMaxId() {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.limit(1);
			query2.with(new Sort(Sort.Direction.DESC, QbMongoFiledsName.ID));
			List<M900MG> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, 0).find(query2, M900MG.class);
			return list.get(0).getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<M900MG> findM900Index(int limit) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where(QbMongoFiledsName.MN909).is(1));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			query2.with(new Sort(Sort.Direction.ASC, QbMongoFiledsName.ID));
			query2.limit(limit);
			List<M900MG> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY,
					ApplicationConstant.FO100_SUPER_ADMIN).find(query2, M900MG.class);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int checkTabPrivacy(String mv907) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M900_CHECKPRIVACY);
			setParameterString(mv907);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("restriction")
	@Override
	public void loadHistory(M900MG m900mg) {
		// TODO Auto-generated method stub
		if (m900mg != null) {
			R900MGService r900mgService = (R900MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_R900MG);
			M150MGService m150mgService = (M150MGService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_M150MG);
			TemplateService templateMGService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			String s = r900mgService.getAllHisotry(m900mg.getId());
			try {
				JSONObject jsonObject = new JSONObject(s);
				int vote = (int) Double.parseDouble(jsonObject.get("votes").toString());
				int bookmark = (int) Double.parseDouble(jsonObject.get("bookmarks").toString());
				int mn910 = (int) Double.parseDouble(jsonObject.get("mn910").toString());
				int mn915 = (int) Double.parseDouble(jsonObject.get("mn915").toString());
				String mv916 = m150mgService.getNewTopic(m900mg.getId());
				M930MG m930mg = templateMGService.findDocumentById(m900mg.getId(), m900mg.getId(), M930MG.class);
				if (m930mg != null)
					m900mg.setM930mg(m930mg);
				// ScriptEngineManager factory = new ScriptEngineManager();
				// ScriptEngine engine = factory.getEngineByName("JavaScript");
				if (mv916 != null)
					/*
					 * String result = (String) engine.eval("unescape('" + mv916
					 * + "')");
					 */
					m900mg.setMv916(mv916);
				m900mg.setMn911(vote);
				m900mg.setMn914(bookmark);
				m900mg.setMn910(mn910);
				m900mg.setMn915(mn915);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getMaxIndexM940(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M900_VIDEO_MARKETING_GET_NEWINDEX);
			setParameterNumber(fo100);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getMaxIdM940(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M900_VIDEO_MARKETING_GET_NEWID);
			setParameterNumber(fo100);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int changeM940Index(int fo100, int id, int newIndex) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M900_VIDEO_MARKTING_UPDATE_INDEX);
			setParameterNumber(fo100);
			setParameterNumber(id);
			setParameterNumber(newIndex);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public M900MG loadUserProfile(String hv101) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.fields().include(QbMongoFiledsName.ID).include("FN100").include(QbMongoFiledsName.HV101)
					.include("NV100").include("MV901").include("MV902").include("MV903").include("MD904")
					.include("MV905").include("MN906").include("MV907").include("MV908").include("MN909")
					.include("MN912").include("MV913").include("ML946").include("ML948").include("ML950")
					.include("LANGUAGE").include("JOB").include("RLANGUAGE").include("VIDEO_MARKETING")
					.include("ROLE_TOPIC").include("TOPIC_BG").include("MN918").include("REGION").include("MV922")
					.include("TOPIC_INFO");
			query2.addCriteria(Criteria.where(QbMongoFiledsName.HV101).is(hv101));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			// must add fo100
			M900MG m900mg = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, 0).findOne(query2, M900MG.class);
			return m900mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int storNotTabM900(int po100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M900_STORNOTABM900);
			setParameterNumber(po100);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, po100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public M900MG loadUserProfileMerian(int fo100) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.fields().include(QbMongoFiledsName.ID).include("FN100").include(QbMongoFiledsName.HV101)
					.include("NV100").include("MV901").include("MV902").include("MV903").include("MV905")
					.include("MV907").include("MV908").include("LANGUAGE").include("REGION");
			query2.addCriteria(Criteria.where(QbMongoFiledsName.ID).is(fo100));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			M900MG m900mg = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).findOne(query2, M900MG.class);
			return m900mg;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int storNotTabM900Center(int po100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.M900_STORNOTABM900);
			setParameterNumber(po100);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, 0).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc) ThoaiVt create 17/03/2016 get list account and search
	 * account
	 */
	@Override
	public List<M900MG> getListAccount(String content, int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<M900MG> listM900 = null;
		try {
			setFunctionName(QbMongoFunctionNames.M900_REPORTTAB01);
			setParameterString(content);
			setParameterString((content.equals("") ? "" : AESUtils.encrypt(content)));
			setParameterNumber(offset);
			setParameterNumber((content.equals("") ? limit : 1000));

			String resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (resultData != null) {
				listM900 = new ArrayList<M900MG>();
				JSONArray jsonArray = new JSONArray(resultData);
				log.info("INFFO ACCOUNT : - " + resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
					M900MG m900mg = new M900MG();
					m900mg.setNv100(jsonObject.getString("NV100"));
					m900mg.setId(Integer.parseInt(jsonObject.getString("_id")));
					m900mg.setMv908(jsonObject.getString("AVARTA"));
					m900mg.setDateCreate(jsonObject.getString("DATE_CREATE"));
					m900mg.setMv903(jsonObject.getString("MV903"));
					if (i == 0) {
						if (content.trim().equals(""))
							m900mg.setRowss((int) Double.parseDouble(jsonObject.getString("ROWSS")));
						else
							m900mg.setRowss(jsonArray.length());
					}

					listM900.add(m900mg);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.toString());
		}
		return listM900;
	}

	@Override
	public List<M900MG> listOfTabDesigner(int fo100, int fe400, String pvSearch, int offset, int limit) {
		// TODO Auto-generated method stub
		List<M900MG> listM900 = null;
		try{
			setFunctionName(QbMongoFunctionNames.M900_LISTOFTAB_DESIGNER);
			setParameterNumber(fo100);
			setParameterNumber(fe400);
			setParameterString(pvSearch);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (resultData != null) {
				log.info("LIST DESIGNER : - " + resultData);
				listM900 = new ArrayList<M900MG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
					M900MG m900mg = new M900MG();
					//
					m900mg.setId(jsonObject.getInt("_id"));
					m900mg.setNv100(jsonObject.getString("NV100"));
					m900mg.setMv903(jsonObject.getString("MV903"));
					m900mg.setMv905(jsonObject.getString("MV905"));
					m900mg.setMv908(jsonObject.getString("MV908"));
					if(jsonObject.has("REGION"))
						m900mg.setRegion(jsonObject.getString("REGION"));
					listM900.add(m900mg);
				}
			}
		}catch(Exception e){
			log.info(e);
			e.printStackTrace();
		}
		return listM900;
	}

	@Override
	public M900DesMG listOfTabDesignerOne(int fo100) {
		// TODO Auto-generated method stub
		M900DesMG result = null;
		try{
			setFunctionName(QbMongoFunctionNames.M900_LISTOFTABM900_DESIGNER_ONE);
			setParameterNumber(fo100);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			if (resultData != null) {
				log.info("DESIGNER INFO: - " + resultData);
				org.json.JSONObject jsonObject = new org.json.JSONObject(resultData);
				result = new M900DesMG();
				result.setId(fo100);
				result.setNv100(jsonObject.getString("NV100"));
				result.setMv903(jsonObject.getString("MV903"));
				result.setMv905(jsonObject.getString("MV905"));
				result.setMv907(jsonObject.getString("MV907"));
				result.setMv908(jsonObject.getString("MV908"));
				result.setRegion(jsonObject.getString("REGION"));
				LanguageMG rLanguage = new LanguageMG();
				org.json.JSONObject jsonLang = jsonObject.getJSONObject("RLANGUAGE");
				rLanguage.setCode(jsonLang.getString("CODE"));
				rLanguage.setText(jsonLang.getString("TEXT"));
				result.setrLanguage(rLanguage);
				result.setTotalStie(jsonObject.getInt("TOTAL_SITE"));
				result.setMd904String(jsonObject.getString("MD904String"));
				result.setMl948String(jsonObject.getString("ML948String"));
			}
		}
		catch(Exception e){
			log.info(e);
			e.printStackTrace();
		}
		return result;
	}

}
