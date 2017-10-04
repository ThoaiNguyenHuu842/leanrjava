package com.ohhay.piepme.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.C100PMGDao;
import com.ohhay.piepme.mongo.entities.interaction.C100PMG;
import com.ohhay.piepme.mongo.entities.interaction.G100PMG;
import com.ohhay.piepme.mongo.nestedentities.C100ACPMG;

/**
 * @author ThoaiNH
 * create Sep 21, 2016
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_C100P)
@Scope("prototype")
public class C100PMGDaoImpl extends QbMongoDaoSupport implements C100PMGDao{

	@Override
	public List<C100PMG> listOfTabC100Friend(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_FRIEND);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.ID)));
					c100pmg.setFo100(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100)));
					c100pmg.setFo100f(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100F)));
					c100pmg.setCv101(jsonObject.getString(QbMongoFiledsName.CV101));
					c100pmg.setNv101(jsonObject.getString(QbMongoFiledsName.NV101));
					c100pmg.setNv102(jsonObject.getString(QbMongoFiledsName.NV102));
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					if(jsonObject.has("NICK_NAME"))
						c100pmg.setNickName(jsonObject.getString("NICK_NAME"));
					if(jsonObject.has("CV104"))
						c100pmg.setCv104(jsonObject.getString("CV104"));
					if(jsonObject.has("CV104F"))
						c100pmg.setCv104f(jsonObject.getString("CV104F"));
					if(jsonObject.has("NN119"))
						c100pmg.setNn119(jsonObject.getInt("NN119"));
					List<G100PMG> listG100 = new ArrayList<G100PMG>();
					JSONArray groupsArr = jsonObject.getJSONArray("GROUPS");
					for (int j = 0; j < groupsArr.length(); j++) {
						JSONObject groupsObj = groupsArr.getJSONObject(j);
						G100PMG g100pmg = new G100PMG();
						g100pmg.setId(Integer.parseInt(groupsObj.getString("_id")));
						g100pmg.setGv101(groupsObj.getString("GV101"));
						if(groupsObj.has("GV102")){
							g100pmg.setGv102(groupsObj.getString("GV102"));
						}
						listG100.add(g100pmg);
					}
					c100pmg.setGroup(listG100);
					listResult.add(c100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<C100PMG> listOfTabC100Request(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_REQUEST);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(Integer.parseInt(jsonObject.getString("_id")));
					c100pmg.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					c100pmg.setFo100f(Integer.parseInt(jsonObject.getString("FO100F")));
					c100pmg.setCv101(jsonObject.getString("CV101"));
					if(jsonObject.has("NV101"))
						c100pmg.setNv101(jsonObject.getString("NV101"));
					c100pmg.setNv102(jsonObject.getString("NV102"));
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					if(jsonObject.has("NICKNAME"))
						c100pmg.setNickName(jsonObject.getString("NICKNAME"));
					listResult.add(c100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<C100PMG> listOfTabC100SentRequest(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_SENT_REQUEST);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME,fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.ID)));
					c100pmg.setFo100(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100)));
					c100pmg.setFo100f(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100F)));
					c100pmg.setCv101(jsonObject.getString(QbMongoFiledsName.CV101));
					if(jsonObject.has("NV101"))
						c100pmg.setNv101(jsonObject.getString(QbMongoFiledsName.NV101));
					if(jsonObject.has("NICKNAME"))
						c100pmg.setNickName(jsonObject.getString("NICKNAME"));
					c100pmg.setNv102(jsonObject.getString(QbMongoFiledsName.NV102));
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listResult.add(c100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public int updateTabC100Nick(int fo100DB, int fo100, int fo100f, String cv103) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.C100_UPDATETABC100_NICK);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterString(cv103);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100DB).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<C100ACPMG> listOfTabC100RecentAc(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100ACPMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_RECENT_AC);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME,fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100ACPMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100ACPMG c100acpmg = new C100ACPMG();
					c100acpmg.setId(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.ID)));
					c100acpmg.setFo100f(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100F)));
					c100acpmg.setType(jsonObject.getString(QbMongoFiledsName.TYPE));
					if(jsonObject.has("NICKNAME"))
						c100acpmg.setNickName(jsonObject.getString("NICKNAME"));
					if(jsonObject.has("NV101"))
						c100acpmg.setNv101(jsonObject.getString(QbMongoFiledsName.NV101));
					c100acpmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listResult.add(c100acpmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public int updateTabSecureContact(int fo100, List<Double> fc100s) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.C100_UPDATETAB_SECURE);
			setParameterNumber(fo100);
			setParameterArrayNumber(fc100s);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<C100PMG> listOfTabC100Secure(int fo100) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_SECURE);
			setParameterNumber(fo100);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.ID)));
					c100pmg.setFo100(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100)));
					c100pmg.setFo100f(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100F)));
					c100pmg.setCv101(jsonObject.getString(QbMongoFiledsName.CV101));
					if(jsonObject.has(QbMongoFiledsName.NV101))
						c100pmg.setNv101(jsonObject.getString(QbMongoFiledsName.NV101));
					c100pmg.setNv102(jsonObject.getString(QbMongoFiledsName.NV102));
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					if(jsonObject.has("NICKNAME"))
						c100pmg.setNickName(jsonObject.getString("NICKNAME"));
					List<G100PMG> listG100 = new ArrayList<G100PMG>();
					JSONArray groupsArr = jsonObject.getJSONArray("GROUPS");
					for (int j = 0; j < groupsArr.length(); j++) {
						JSONObject groupsObj = groupsArr.getJSONObject(j);
						G100PMG g100pmg = new G100PMG();
						g100pmg.setId(Integer.parseInt(groupsObj.getString("_id")));
						g100pmg.setGv101(groupsObj.getString("GV101"));
						g100pmg.setGv102(groupsObj.getString("GV102"));
						listG100.add(g100pmg);
					}
					c100pmg.setGroup(listG100);
					listResult.add(c100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public int updateTabSecureContactAuto(int fc100) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.C100_UPDATETAB_SECURE_AUTO);
			setParameterNumber(fc100);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, ApplicationConstant.FO100_SUPER_ADMIN).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<C100PMG> listOfTabC100FriendExcludeSecure(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_FRIEND_EXCLUDE_SECURE);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.ID)));
					c100pmg.setFo100(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100)));
					c100pmg.setFo100f(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100F)));
					c100pmg.setCv101(jsonObject.getString(QbMongoFiledsName.CV101));
					c100pmg.setNv101(jsonObject.getString(QbMongoFiledsName.NV101));
					c100pmg.setNv102(jsonObject.getString(QbMongoFiledsName.NV102));
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					if(jsonObject.has("NICK_NAME"))
						c100pmg.setNickName(jsonObject.getString("NICK_NAME"));
					List<G100PMG> listG100 = new ArrayList<G100PMG>();
					JSONArray groupsArr = jsonObject.getJSONArray("GROUPS");
					for (int j = 0; j < groupsArr.length(); j++) {
						JSONObject groupsObj = groupsArr.getJSONObject(j);
						G100PMG g100pmg = new G100PMG();
						g100pmg.setId(Integer.parseInt(groupsObj.getString("_id")));
						g100pmg.setGv101(groupsObj.getString("GV101"));
						if(groupsObj.has("GV102")){
							g100pmg.setGv102(groupsObj.getString("GV102"));
						}
						listG100.add(g100pmg);
					}
					c100pmg.setGroup(listG100);
					listResult.add(c100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<C100PMG> listOfTabC100FriendHis(int fo100, int fo100f, long timeStamp, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_FRIEND_HISTORY);
			setParameterNumber(fo100);
			setParameterNumber(fo100f);
			setParameterNumber(timeStamp);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.ID)));
					c100pmg.setFo100(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100)));
					c100pmg.setFo100f(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100F)));
					c100pmg.setCv101(jsonObject.getString(QbMongoFiledsName.CV101));
					c100pmg.setNv101(jsonObject.getString(QbMongoFiledsName.NV101));
					c100pmg.setNv102(jsonObject.getString(QbMongoFiledsName.NV102));
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					if(jsonObject.has("NICK_NAME"))
						c100pmg.setNickName(jsonObject.getString("NICK_NAME"));
					if(jsonObject.has("CV104"))
						c100pmg.setCv104(jsonObject.getString("CV104"));
					if(jsonObject.has("KV105"))
						c100pmg.setKv105(jsonObject.getString("KV105"));
					if(jsonObject.has("CV104F"))
						c100pmg.setCv104f(jsonObject.getString("CV104F"));
					if(jsonObject.has("NN119"))
						c100pmg.setNn119(jsonObject.getInt("NN119"));
					if(jsonObject.has("DATE_DELETE"))
						c100pmg.setDateDelete(Long.parseLong(jsonObject.getString("DATE_DELETE")));
					List<G100PMG> listG100 = new ArrayList<G100PMG>();
					JSONArray groupsArr = jsonObject.getJSONArray("GROUPS");
					for (int j = 0; j < groupsArr.length(); j++) {
						JSONObject groupsObj = groupsArr.getJSONObject(j);
						G100PMG g100pmg = new G100PMG();
						g100pmg.setId(Integer.parseInt(groupsObj.getString("_id")));
						g100pmg.setGv101(groupsObj.getString("GV101"));
						if(groupsObj.has("GV102")){
							g100pmg.setGv102(groupsObj.getString("GV102"));
						}
						listG100.add(g100pmg);
					}
					c100pmg.setGroup(listG100);
					listResult.add(c100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<Integer> listOfTabC100FriendDel(int fo100, long time) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABFO100_DEL);
			setParameterNumber(fo100);
			setParameterNumber(time);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();

			if (resultData != null) {
				JSONArray jsonArray = new JSONArray(resultData);
				List<Integer> arFo100 = new ArrayList<Integer>();
				for (int i = 0; i < jsonArray.length(); i++) {
					arFo100.add(jsonArray.getInt(i));
				}
				return arFo100;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<C100PMG> listOfTabC100FriendEdu(int fo100, int ft100, int ft110, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_FRIEND_EDU);
			setParameterNumber(fo100);
			setParameterNumber(ft100);
			setParameterNumber(ft110);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.ID)));
					c100pmg.setFo100(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100)));
					c100pmg.setFo100f(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100F)));
					c100pmg.setCv101(jsonObject.getString(QbMongoFiledsName.CV101));
					c100pmg.setNv101(jsonObject.getString(QbMongoFiledsName.NV101));
					c100pmg.setNv102(jsonObject.getString(QbMongoFiledsName.NV102));
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					if(jsonObject.has("NICK_NAME"))
						c100pmg.setNickName(jsonObject.getString("NICK_NAME"));
					if(jsonObject.has("CV104"))
						c100pmg.setCv104(jsonObject.getString("CV104"));
					if(jsonObject.has("CV104F"))
						c100pmg.setCv104f(jsonObject.getString("CV104F"));
					if(jsonObject.has("NN119"))
						c100pmg.setNn119(jsonObject.getInt("NN119"));
					List<G100PMG> listG100 = new ArrayList<G100PMG>();
					JSONArray groupsArr = jsonObject.getJSONArray("GROUPS");
					for (int j = 0; j < groupsArr.length(); j++) {
						JSONObject groupsObj = groupsArr.getJSONObject(j);
						G100PMG g100pmg = new G100PMG();
						g100pmg.setId(Integer.parseInt(groupsObj.getString("_id")));
						g100pmg.setGv101(groupsObj.getString("GV101"));
						if(groupsObj.has("GV102")){
							g100pmg.setGv102(groupsObj.getString("GV102"));
						}
						listG100.add(g100pmg);
					}
					c100pmg.setGroup(listG100);
					listResult.add(c100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<C100PMG> listOfTabC100FriendAdm(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.C100_LISTOFTABC100_FRIEND_ADM);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.ID)));
					c100pmg.setFo100(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100)));
					c100pmg.setFo100f(Integer.parseInt(jsonObject.getString(QbMongoFiledsName.FO100F)));
					c100pmg.setCv101(jsonObject.getString(QbMongoFiledsName.CV101));
					c100pmg.setNv101(jsonObject.getString(QbMongoFiledsName.NV101));
					c100pmg.setNv102(jsonObject.getString(QbMongoFiledsName.NV102));
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					if(jsonObject.has("NICK_NAME"))
						c100pmg.setNickName(jsonObject.getString("NICK_NAME"));
					if(jsonObject.has("CV104"))
						c100pmg.setCv104(jsonObject.getString("CV104"));
					if(jsonObject.has("CV104F"))
						c100pmg.setCv104f(jsonObject.getString("CV104F"));
					if(jsonObject.has("NN119"))
						c100pmg.setNn119(jsonObject.getInt("NN119"));
					List<G100PMG> listG100 = new ArrayList<G100PMG>();
					JSONArray groupsArr = jsonObject.getJSONArray("GROUPS");
					for (int j = 0; j < groupsArr.length(); j++) {
						JSONObject groupsObj = groupsArr.getJSONObject(j);
						G100PMG g100pmg = new G100PMG();
						g100pmg.setId(Integer.parseInt(groupsObj.getString("_id")));
						g100pmg.setGv101(groupsObj.getString("GV101"));
						if(groupsObj.has("GV102")){
							g100pmg.setGv102(groupsObj.getString("GV102"));
						}
						listG100.add(g100pmg);
					}
					c100pmg.setGroup(listG100);
					listResult.add(c100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

}
