package com.ohhay.piepme.mongo.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.mongo.util.QbCriteria;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.dao.G100PMGDao;
import com.ohhay.piepme.mongo.entities.interaction.C100PMG;
import com.ohhay.piepme.mongo.entities.interaction.G100PMG;
import com.ohhay.piepme.mongo.entities.pieper.G100Status;
import com.ohhay.piepme.mongo.entities.pieper.N100Status;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH
 * create Sep 21, 2016
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_G100P)
@Scope("prototype")
public class G100PMGDaoImpl extends QbMongoDaoSupport implements G100PMGDao{
	private Logger log=Logger.getLogger(G100PMGDaoImpl.class);
	@Override
	public int addFriendToGroup(int fo100, int fg100, String fo100String) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.G100_ADD_FRIENDS_TO_GROUP);
			setParameterNumber(fo100);
			setParameterNumber(fg100);
			setParameterString(fo100String);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeFriendFromGroup(int fo100, int fg100, int fo100r) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.G100_REMOVE_FRIEND_FROM_GROUP);
			setParameterNumber(fo100);
			setParameterNumber(fg100);
			setParameterNumber(fo100r);
			int result = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString());
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<G100PMG> listOfTabG100(int fo100, int offset, int limit, String type) {
		// TODO Auto-generated method stub
		List<G100PMG> listResult = null;
		try{
			setFunctionName(QbMongoFunctionNames.G100_LISTOFTAB_G100);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			setParameterString(type);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if(resultData != null){
				listResult = new ArrayList<G100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for(int i = 0 ; i< jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					G100PMG g100pmg = new G100PMG();
					g100pmg.setId(Integer.parseInt(jsonObject.getString("_id")));;
					g100pmg.setGv101(jsonObject.getString("GV101"));
					if(jsonObject.has("GV102"))
						g100pmg.setGv102(jsonObject.getString("GV102"));
					if(jsonObject.has("TYPE"))
						g100pmg.setType(jsonObject.getString("TYPE"));
					listResult.add(g100pmg);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<N100PMG> listOfTabG100Friends(int fo100, int pg100, int offset, int limit, String kv105) {
		// TODO Auto-generated method stub
		List<N100PMG> listResult = null;
		try{
			setFunctionName(QbMongoFunctionNames.G100_LISTOFTABG100_FRIEND);
			setParameterNumber(fo100);
			setParameterNumber(pg100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			setParameterString(kv105);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if(resultData != null){
				listResult = new ArrayList<N100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for(int i = 0 ; i< jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					n100pmg.setId(Integer.parseInt(jsonObject.getString("_id")));
					n100pmg.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					if(jsonObject.has("NICKNAME"))
						n100pmg.setNickName(jsonObject.getString("NICKNAME"));
					if(jsonObject.has("FC100"))
						n100pmg.setFc100(Integer.parseInt(jsonObject.getString("FC100")));
					n100pmg.setNv101(jsonObject.getString("NV101"));
					n100pmg.setNv102(jsonObject.getString("NV102"));
					n100pmg.setFriendStt(jsonObject.getInt("FRIEND_STT"));
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					n100pmg.setNn119(jsonObject.getInt("NN119"));
					if(jsonObject.has("TIME_AGO"))
						n100pmg.setTimeAgo(Long.parseLong(jsonObject.getString("TIME_AGO")));
					listResult.add(n100pmg);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<C100PMG> listOfTabFriendsToAdd(int fo100, int pg100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<C100PMG> listResult = null;
		try{
			setFunctionName(QbMongoFunctionNames.G100_LISTOFTABFRIENDS_TO_ADD);
			setParameterNumber(fo100);
			setParameterNumber(pg100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if(resultData!=null){
				log.info("--listOfTabFriendsToAdd: "+resultData);
				listResult = new ArrayList<C100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					C100PMG c100pmg = new C100PMG();
					c100pmg.setId(jsonObject.getInt("_id"));
					c100pmg.setFo100(jsonObject.getInt("FO100"));
					c100pmg.setFo100f(jsonObject.getInt("FO100F"));
//					SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss.SSSXXX");
//					JSONObject jsonCD166 = jsonObject.getJSONObject("CD166");
//					JSONObject jsonCD168 = jsonObject.getJSONObject("CD168");
//					log.info("--cd166 : "+jsonCD166.getString("$date"));
//					log.info("--cd168 : "+jsonCD168.getString("$date"));
//					c100pmg.setCd166(df.parse(jsonCD166.getString("$date")));
//					c100pmg.setCd168(df.parse(jsonCD168.getString("$date")));
					c100pmg.setCv101(jsonObject.getString("CV101"));
					c100pmg.setNv101(jsonObject.getString("NV101"));
					c100pmg.setNv102(jsonObject.getString("NV102"));
					if(jsonObject.has("NICKNAME"))
						c100pmg.setNickName(jsonObject.getString("NICKNAME"));
					List<G100PMG> group = new ArrayList<G100PMG>();
					JSONArray arrGroup = jsonObject.getJSONArray("GROUPS");
					for (int j = 0; j < arrGroup.length(); j++) {
						JSONObject jsonGroup = arrGroup.getJSONObject(j);
						G100PMG g100pmg = new G100PMG();
						g100pmg.setId(jsonGroup.getInt("_id"));
						g100pmg.setGv101(jsonGroup.getString("GV101"));
						g100pmg.setGv102(jsonGroup.getString("GV102"));
						group.add(g100pmg);
					}
					c100pmg.setGroup(group);
					c100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listResult.add(c100pmg);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public List<N100PMG> listOfTabG100FriendsDis(int fo100, String fg100String, int offset, int limit, String kv105) {
		// TODO Auto-generated method stub
		List<N100PMG> listResult = null;
		try{
			setFunctionName(QbMongoFunctionNames.G100_LISTOFTABG100_FRIENDDIS);
			setParameterNumber(fo100);
			setParameterString(fg100String);
			setParameterNumber(offset);
			setParameterNumber(limit);
			setParameterString(kv105);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if(resultData != null){
				listResult = new ArrayList<N100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for(int i = 0 ; i< jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					n100pmg.setId(Integer.parseInt(jsonObject.getString("_id")));
					n100pmg.setFo100(Integer.parseInt(jsonObject.getString("FO100")));
					n100pmg.setNv101(jsonObject.getString("NV101"));
					n100pmg.setNv102(jsonObject.getString("NV102"));
					n100pmg.setFriendStt(jsonObject.getInt("FRIEND_STT"));
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listResult.add(n100pmg);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listResult;
	}

	@Override
	public N100Status getFO100OfLoyaltyCustomer(int fo100) {
		// TODO Auto-generated method stub
		N100Status n100OnlineStatus = new N100Status();
		try {
			TemplateService templateService = (TemplateService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_TEMPLATE);
			templateService.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
			G100PMG g100pmg = templateService.findDocument(fo100, G100PMG.class, new QbCriteria(QbMongoFiledsName.FO100,fo100), new QbCriteria(QbMongoFiledsName.TYPE, G100PMG.TYPE_COM));
			if(g100pmg != null)
			{
				/*
				 * get N100 info
				 */
				List<Integer> listUserOff = new ArrayList<Integer>();
				Iterator<Integer> iterator = g100pmg.getListFO100R().iterator();
				while(iterator.hasNext()){
					int fo100F = iterator.next();
					N100PMG n100pmg = templateService.findDocument(fo100F, N100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, fo100F));
					T100PMG t100Off = templateService.findDocument(fo100, T100PMG.class, new QbCriteria("FO100E", fo100), new QbCriteria("FO100", fo100F));
					if(t100Off == null || T100PMG.EOM_STT_OFF_NOTI.equals(t100Off.getEomSTT()))
						iterator.remove();
					else if(n100pmg == null || n100pmg.getNn119() == 0)
					{
						listUserOff.add(fo100F);
						iterator.remove();
					}

				}
				n100OnlineStatus.setUsersOff(listUserOff);
				n100OnlineStatus.setUsersOnl(g100pmg.getListFO100R());
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return n100OnlineStatus;
	}

	@Override
	public G100Status listOfTabG100Ids(int fo100) {
		// TODO Auto-generated method stub
		try{
			setFunctionName(QbMongoFunctionNames.G100_LISTOFTAB_IDS);
			setParameterNumber(fo100);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if(resultData != null){
				JSONObject jsonObject = new JSONObject(resultData);
				G100Status g100Status = new G100Status();
				if(jsonObject.has("FG100S")){
					List<Integer> listFG100S = new ArrayList<Integer>();
					JSONArray fg100s = jsonObject.getJSONArray("FG100S");
					for(int i = 0; i< fg100s.length(); i++)
						listFG100S.add(fg100s.getInt(i));
					g100Status.setFg100s(listFG100S);
				}
				if(jsonObject.has("FG100_OWNERS")){
					List<Integer> listFG100S = new ArrayList<Integer>();
					JSONArray fg100s = jsonObject.getJSONArray("FG100_OWNERS");
					for(int i = 0; i< fg100s.length(); i++)
						listFG100S.add(fg100s.getInt(i));
					g100Status.setFg100Owners(listFG100S);
				}
				return g100Status;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
