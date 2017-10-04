package com.ohhay.piepme.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.mysql.dao.Q100Dao;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.dao.N100PMGDao;
import com.ohhay.piepme.mongo.entities.interaction.F150PMG;
import com.ohhay.piepme.mongo.entities.pieper.N100CAFMG;
import com.ohhay.piepme.mongo.entities.pieper.N100Status;
import com.ohhay.piepme.mongo.entities.pieper.N100Status2;
import com.ohhay.piepme.mongo.entities.pieper.N100Status3;
import com.ohhay.piepme.mongo.nestedentities.DeviceToken;
import com.ohhay.piepme.mongo.nestedentities.K100DetailPMG;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH create Oct 31, 2016
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_N100P)
@Scope("prototype")
public class N100PMGDaoImpl extends QbMongoDaoSupport implements N100PMGDao {
	Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<N100PMG> listOfTabN100(int fo100, String userNameStemmed) {
		// TODO Auto-generated method stub
		List<N100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100);
			setParameterNumber(fo100);
			setParameterString(userNameStemmed);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<N100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					n100pmg.setId(Integer.parseInt(jsonObject.getString("_id")));
					n100pmg.setNv101(jsonObject.getString("NV101"));
					n100pmg.setNv102(jsonObject.getString("NV102"));
					n100pmg.setNv106(jsonObject.getString("NV106"));
					n100pmg.setNv107(jsonObject.getString("NV107"));
					n100pmg.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					n100pmg.setFriendStt(Integer.parseInt(jsonObject.getString("FRIEND_STT")));
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listResult.add(n100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<DeviceToken> listOfTabN100Token(String fo100s) {
		// TODO Auto-generated method stub
		List<DeviceToken> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100_TOKEN);
			setParameterString(fo100s);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME,
					ApplicationConstant.FO100_SUPER_ADMIN).toString();
			if (resultData != null) {
				listResult = new ArrayList<DeviceToken>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					DeviceToken deviceToken = new DeviceToken();
					deviceToken.setToken(jsonObject.getString("NV111"));
					deviceToken.setDevice(jsonObject.getString("NV114"));
					listResult.add(deviceToken);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<N100PMG> listOfTabN100Sug(String search, int offset, int limit) {
		// TODO Auto-generated method stub
		List<N100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100_SUGGESTED);
			setParameterString(search);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME,
					ApplicationConstant.FO100_SUPER_ADMIN).toString();
			if (resultData != null) {
				listResult = new ArrayList<N100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					n100pmg.setId(Integer.parseInt(jsonObject.getString("_id")));
					n100pmg.setNv101(jsonObject.getString("NV101"));
					n100pmg.setNv102(jsonObject.getString("NV102"));
					n100pmg.setNv106(jsonObject.getString("NV106"));
					n100pmg.setNv107(jsonObject.getString("NV107"));
					n100pmg.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					if (jsonObject.has("FRIEND_STT")) {
						n100pmg.setFriendStt(Integer.parseInt(jsonObject.getString("FRIEND_STT")));
					}
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listResult.add(n100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public K100DetailPMG getBusinessInfo(int fo100, int fo100b) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.N100_GETBUSINESS_INFO);
			setParameterNumber(fo100);
			setParameterNumber(fo100b);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				K100DetailPMG k100DetailPMG = new K100DetailPMG();
				JSONObject jsonObject = new JSONObject(resultData);
				k100DetailPMG.setKv101(jsonObject.getString("KV101"));
				k100DetailPMG.setKv102(jsonObject.getString("KV102"));
				k100DetailPMG.setKv103(jsonObject.getString("KV103"));
				k100DetailPMG.setKv106(jsonObject.getString("KV106"));
				k100DetailPMG.setKv107(jsonObject.getString("KV107"));
				k100DetailPMG.setTotalFollower(jsonObject.getInt("FOLLOWS"));
				k100DetailPMG.setTotalLike(jsonObject.getInt("LIKES"));
				k100DetailPMG.setTotalPiep(jsonObject.getInt("PIEPS"));
				if (jsonObject.has("FOLLOWING_STT"))
					k100DetailPMG.setFollowingStt(jsonObject.getInt("FOLLOWING_STT"));
				return k100DetailPMG;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<N100PMG> listOfTabN100V2(int fo100, String nv106Steam, String nv107) {
		// TODO Auto-generated method stub
		List<N100PMG> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100V2);
			setParameterNumber(fo100);
			setParameterString(nv106Steam);
			setParameterString(nv107);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (resultData != null) {
				listResult = new ArrayList<N100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					n100pmg.setId(Integer.parseInt(jsonObject.getString("_id")));
					n100pmg.setNv101(jsonObject.getString("NV101"));
					n100pmg.setNv102(jsonObject.getString("NV102"));
					n100pmg.setNv106(jsonObject.getString("NV106"));
					n100pmg.setNv107(jsonObject.getString("NV107"));
					n100pmg.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					n100pmg.setFriendStt(Integer.parseInt(jsonObject.getString("FRIEND_STT")));
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					if (jsonObject.has("FC100"))
						n100pmg.setFc100(jsonObject.getInt("FC100"));
					listResult.add(n100pmg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public N100Status3 listOfTabN100LocV2(int fo100, double latitude, double longitude, int radius) {
		// TODO Auto-generated method stub
		N100Status3 n100Status3 = new N100Status3();
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100_LOCV2);
			setParameterNumber(fo100);
			setParameterNumber(latitude);
			setParameterNumber(longitude);
			setParameterNumber(radius);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, 0).toString();
			if (resultData != null) {
				JSONObject jsonObject = new JSONObject(resultData);
				JSONObject fo100sJson = jsonObject.getJSONObject("FO100S");
				JSONObject fo100FollowsJson = jsonObject.getJSONObject("FO100_FOLLOWS");
				List<Integer> usersOffFo100s = new ArrayList<Integer>();
				List<Integer> usersOnlFo100s = new ArrayList<Integer>();

				JSONArray arrFo100SOff = new JSONArray();
				arrFo100SOff = fo100sJson.getJSONArray("FO100S_OFF");
				for (int i = 0; i < arrFo100SOff.length(); i++) {
					usersOffFo100s.add(arrFo100SOff.getInt(i));
				}
				JSONArray arrFo100SOnl = new JSONArray();
				arrFo100SOnl = fo100sJson.getJSONArray("FO100S_ONL");
				for (int i = 0; i < arrFo100SOnl.length(); i++) {
					usersOnlFo100s.add(arrFo100SOnl.getInt(i));
				}

				N100Status n100Status = new N100Status();
				n100Status.setUsersOff(usersOffFo100s);
				n100Status.setUsersOnl(usersOnlFo100s);
				n100Status3.setFo100s(n100Status);

				JSONArray arrFollowsOff = new JSONArray();
				List<Integer> usersOffFollowOnl = new ArrayList<Integer>();
				List<Integer> usersOnlFollowOff = new ArrayList<Integer>();
				arrFollowsOff = fo100FollowsJson.getJSONArray("FO100S_OFF");
				for (int i = 0; i < arrFollowsOff.length(); i++) {
					usersOnlFollowOff.add(arrFollowsOff.getInt(i));
				}
				JSONArray arrFollowsOnl = new JSONArray();
				arrFollowsOnl = fo100FollowsJson.getJSONArray("FO100S_ONL");
				for (int i = 0; i < arrFollowsOnl.length(); i++) {
					usersOffFollowOnl.add(arrFollowsOnl.getInt(i));
				}
				N100Status n100Status2 = new N100Status();
				n100Status2.setUsersOff(usersOnlFollowOff);
				n100Status2.setUsersOnl(usersOffFollowOnl);
				n100Status3.setFo100follows(n100Status2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n100Status3;
	}

	@Override
	public N100Status3 listOfTabN100LocRV2(int fo100, int radius) {
		// TODO Auto-generated method stub
		N100Status3 n100Status3 = new N100Status3();
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100_LOCRV2);
			setParameterNumber(fo100);
			setParameterNumber(radius);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, 0).toString();
			if (resultData != null) {
				JSONObject jsonObject = new JSONObject(resultData);
				JSONObject fo100sJson = jsonObject.getJSONObject("FO100S");
				JSONObject fo100FollowsJson = jsonObject.getJSONObject("FO100_FOLLOWS");
				List<Integer> usersOffFo100s = new ArrayList<Integer>();
				List<Integer> usersOnlFo100s = new ArrayList<Integer>();

				JSONArray arrFo100SOff = new JSONArray();
				arrFo100SOff = fo100sJson.getJSONArray("FO100S_OFF");
				for (int i = 0; i < arrFo100SOff.length(); i++) {
					usersOffFo100s.add(arrFo100SOff.getInt(i));
				}
				JSONArray arrFo100SOnl = new JSONArray();
				arrFo100SOnl = fo100sJson.getJSONArray("FO100S_ONL");
				for (int i = 0; i < arrFo100SOnl.length(); i++) {
					usersOnlFo100s.add(arrFo100SOnl.getInt(i));
				}

				N100Status n100Status = new N100Status();
				n100Status.setUsersOff(usersOffFo100s);
				n100Status.setUsersOnl(usersOnlFo100s);
				n100Status3.setFo100s(n100Status);

				JSONArray arrFollowsOff = new JSONArray();
				List<Integer> usersOffFollowOnl = new ArrayList<Integer>();
				List<Integer> usersOnlFollowOff = new ArrayList<Integer>();
				arrFollowsOff = fo100FollowsJson.getJSONArray("FO100S_OFF");
				for (int i = 0; i < arrFollowsOff.length(); i++) {
					usersOnlFollowOff.add(arrFollowsOff.getInt(i));
				}
				JSONArray arrFollowsOnl = new JSONArray();
				arrFollowsOnl = fo100FollowsJson.getJSONArray("FO100S_ONL");
				for (int i = 0; i < arrFollowsOnl.length(); i++) {
					usersOffFollowOnl.add(arrFollowsOnl.getInt(i));
				}
				N100Status n100Status2 = new N100Status();
				n100Status2.setUsersOff(usersOnlFollowOff);
				n100Status2.setUsersOnl(usersOffFollowOnl);
				n100Status3.setFo100follows(n100Status2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n100Status3;
	}

	@Override
	public N100Status2 listOfTabN100LocR(int fo100, int radius) {
		// TODO Auto-generated method stub
		N100Status2 n100Status2 = new N100Status2();
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100_LOCR);
			setParameterNumber(fo100);
			setParameterNumber(radius);
			List<Integer> listN100Loc = null;
			List<Integer> listN100Follow = null;
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, 0).toString();
			if (resultData != null) {
				listN100Loc = new ArrayList<Integer>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					int fo100Loc = Integer.parseInt(jsonObject.getString("FO100"));
					listN100Loc.add(fo100Loc);
				}
			}
			/*
			 * list FO100 of account following fo100
			 */
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			List<F150PMG> listF150 = templateService.findDocumentsInc(fo100, F150PMG.class, "FO100", listN100Loc,
					new QbCriteria("FO100T", fo100));
			if (listF150 != null && listF150.size() > 0) {
				listN100Follow = new ArrayList<Integer>();
				for (F150PMG f150pmg : listF150)
					listN100Follow.add(f150pmg.getFo100());
			}

			n100Status2.setFo100s(listN100Loc);
			n100Status2.setFo100follows(listN100Follow);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
		}
		return n100Status2;
	}

	@Override
	public N100Status2 listOfTabN100Loc(int fo100, double latitude, double longitude, int radius) {
		// TODO Auto-generated method stub
		N100Status2 n100Status2 = new N100Status2();
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100_LOC);
			setParameterNumber(fo100);
			setParameterNumber(latitude);
			setParameterNumber(longitude);
			setParameterNumber(radius);
			List<Integer> listN100Loc = null;
			List<Integer> listN100Follow = null;
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, 0).toString();
			if (resultData != null) {
				listN100Loc = new ArrayList<Integer>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					int fo100Loc = Integer.parseInt(jsonObject.getString("FO100"));
					listN100Loc.add(fo100Loc);
				}
			}
			/*
			 * list FO100 of business fo100 is following
			 */
			TemplateService templateService = (TemplateService) ApplicationHelper
					.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			List<F150PMG> listF150 = templateService.findDocumentsInc(fo100, F150PMG.class, "FO100T", listN100Loc,
					new QbCriteria("FO100", fo100));
			if (listF150 != null && listF150.size() > 0) {
				listN100Follow = new ArrayList<Integer>();
				for (F150PMG f150pmg : listF150)
					listN100Follow.add(f150pmg.getFo100t());
			}

			n100Status2.setFo100s(listN100Loc);
			n100Status2.setFo100follows(listN100Follow);
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
		}
		return n100Status2;
	}

	@Override
	public List<N100CAFMG> listOfTabN100OCaf(int fo100) {
		// TODO Auto-generated method stub
		TemplateService templateService = (TemplateService) ApplicationHelper
				.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
		templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
		N100PMG n100 = templateService.findDocument(ApplicationConstant.FO100_SUPER_ADMIN_PIEPME, N100PMG.class,
				new QbCriteria(QbMongoFiledsName.FO100, fo100));
		List<Integer> fo100Caf = n100.getListFO100RE();
		/*
		 * find in List fo100Caf
		 */
		Query query = new Query();
		query.addCriteria(Criteria.where(QbMongoFiledsName.FO100).in(fo100Caf));
		try {
			List<N100CAFMG> listN100Caf = (List<N100CAFMG>) getMongoOperations(ApplicationConstant.DB_TYPE_PIEPME,
					ApplicationConstant.FO100_SUPER_ADMIN_PIEPME).find(query, N100CAFMG.class);
			if (listN100Caf != null) {
				Q100Dao q100Dao = (Q100Dao) ApplicationHelper.findBean(SpringBeanNames.REPOSITORY_NAME_Q100);
				for (N100CAFMG n100CafMG : listN100Caf) {
					String nv126tm = q100Dao.getValTabQv112(n100CafMG.getFo100(),
							ApplicationConstant.PVLOGIN_ANONYMOUS);
					n100CafMG.setPiepmeId(nv126tm);
				}
				return listN100Caf;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e);
		}
		return null;
	}

	@Override
	public double getDistanceFromEnterprise(int fo100, int fo100e) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.N100_GETDISTANCE_TO_ENTERPRISE);
			setParameterNumber(fo100);
			setParameterNumber(fo100e);
			return Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<N100SummaryInfo> listOfTabN100Summary(List<Integer> listFO100) {
		// TODO Auto-generated method stub
		List<N100SummaryInfo> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.N100_LISTOFTABN100_SUMMARY);
			setParameterNumber(listFO100);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, ApplicationConstant.FO100_SUPER_ADMIN_PIEPME).toString();
			if (resultData != null) {
				listResult = new ArrayList<N100SummaryInfo>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100SummaryInfo n100SummaryInfo = new N100SummaryInfo(jsonObject.getInt("_id"), jsonObject.getInt("FO100"), jsonObject.getString("AVARTA_URL"), jsonObject.getString("NV106"));
					listResult.add(n100SummaryInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

}
