package com.ohhay.piepme.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.dao.TemplateDao;
import com.ohhay.piepme.mongo.channel.T100PMG;
import com.ohhay.piepme.mongo.dao.T100PMGDao;
import com.ohhay.piepme.mongo.nestedentities.COMInfo;
import com.ohhay.piepme.mongo.nestedentities.N100SummaryInfo;

/**
 * @author ThoaiNH
 * create Mar 27, 2017
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_T100P)
@Scope("prototype")
public class T100PMGDaoImpl extends QbMongoDaoSupport implements T100PMGDao{
	@Autowired
	private TemplateDao templateDao;
	@Override
	public List<T100PMG> getListT100(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T100_LISTOFTABT100);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<T100PMG> listResult = new ArrayList<T100PMG>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					T100PMG t100pmg = new T100PMG();
					t100pmg.setId(obj.getInt("_id"));
					t100pmg.setFo100(obj.getInt("FO100"));
					if(obj.has("FO100E"))
						t100pmg.setFo100e(obj.getInt("FO100E"));
					t100pmg.setTv101(obj.getString("TV101"));
					t100pmg.setTv102(obj.getString("TV102"));
					t100pmg.setTv103(obj.getString("TV103"));
					if(obj.has("TV104"))
						t100pmg.setTv104(obj.getString("TV104"));
					t100pmg.setTotalPieper(obj.getInt("TOTAL_PIEPER"));
					if(obj.has("TV106"))
						t100pmg.setTv106(obj.getString("TV106"));
					if(obj.has("EOM_STT"))
						t100pmg.setEomSTT(obj.getString("EOM_STT"));
					/*if(t100pmg.getFo100e() > 0){
						templateDao.setOperation(ApplicationConstant.DB_TYPE_PIEPME);
						T100PMG t100COM = templateDao.findDocument(fo100, T100PMG.class, new QbCriteria(QbMongoFiledsName.FO100, t100pmg.getFo100e()), new QbCriteria("TV104", T100PMG.TYPE_COM));
						if(t100COM != null)
							t100pmg.setComInfo(t100COM.getComInfo());
					}*/
					if(obj.has("CHANNEL_TIT"))
						t100pmg.setChannelTit(obj.getString("CHANNEL_TIT"));
					if(obj.has("COM_INFO"))
					{
						JSONObject comInfoJson = obj.getJSONObject("COM_INFO");
						COMInfo comInfo = new COMInfo(comInfoJson.getString("COLOR_CODE"), comInfoJson.getString("CHANNEL_TIT"),
											          comInfoJson.getString("SHOP_TIT"), comInfoJson.getInt("SHOP_STT"));
						t100pmg.setComInfo(comInfo);
					}
					if(obj.has("COM_TYPE"))
						t100pmg.setComType(obj.getString("COM_TYPE"));
					if(obj.has("NN110"))
						t100pmg.setNn110(obj.getInt("NN110"));
					if(obj.has("KV101"))
						t100pmg.setKv101(obj.getString("KV101"));
					listResult.add(t100pmg);
				}
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T100PMG> getListT100Def(int fo100, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T100_LISTOFTABT100DEF);
			setParameterNumber(fo100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<T100PMG> listResult = new ArrayList<T100PMG>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					T100PMG t100pmg = new T100PMG();
					t100pmg.setId(obj.getInt("_id"));
					t100pmg.setFo100(obj.getInt("FO100"));
					t100pmg.setTv101(obj.getString("TV101"));
					t100pmg.setTv102(obj.getString("TV102"));
					t100pmg.setTv103(obj.getString("TV103"));
					t100pmg.setTotalPieper(obj.getInt("TOTAL_PIEPER"));
					listResult.add(t100pmg);
				}
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Integer> getListFO100EOM(int fo100, String vv308) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.T100_LISTOFTABFO100_EOM);
			setParameterNumber(fo100);
			setParameterString(vv308);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, fo100).toString();
			if (kq != null) {
				List<Integer> listResult = new ArrayList<Integer>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					listResult.add(obj.getInt("FO100"));
				}
				return listResult;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<N100SummaryInfo> listOfTabUserADM(int pnFO100, int pnOffset, int pnLimit) {
		// TODO Auto-generated method stub
		List<N100SummaryInfo> listResult = null;
		try {
			setFunctionName(QbMongoFunctionNames.T100_LISTOFTABUSER_ADM);
			setParameterNumber(pnFO100);
			setParameterNumber(pnOffset);
			setParameterNumber(pnLimit);
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
