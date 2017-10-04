package com.ohhay.piepme.mongo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.piepme.mongo.dao.PieperDao;
import com.ohhay.piepme.mongo.nestedentities.PieperComment;
import com.ohhay.piepme.mongo.userprofile.N100PMG;

/**
 * @author ThoaiNH create Nov 11, 2016
 */
@Repository(value = SpringBeanNames.REPOSITORY_NAME_PIEPER)
@Scope("prototype")
public class PieperDaoImpl extends QbMongoDaoSupport implements PieperDao {

	@Override
	public int checkTabUserLike(String pnPiperType, int pnPiperId, int pnFO100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_CHECKTABUSERLIKE);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertTabLike(String pnPiperType, int pnPiperId, int pnFO100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_INSERTTAB_LIKE);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int stornoTabLike(String pnPiperType, int pnPiperId, int pnFO100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_STORNOTAB_LIKE);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertTabComment(String pnPiperType, int pnCommentID, int pnPiperId, int pnFO100, String pnCOMMENT) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_INSERTTAB_COMMENT);
			setParameterString(pnPiperType);
			setParameterNumber(pnCommentID);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			setParameterString(pnCOMMENT);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int stornoTabComment(String pnPiperType, int pnPiperId, int pnComentId, int pnFO100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_STORNOTAB_COMMENT);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnComentId);
			setParameterNumber(pnFO100);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<PieperComment> pieperListOfTabComment(String pnPiperType, int pnPiperId, int pnFO100, int offset, int limit) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_LISTOFTAB_COMMENT);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100).toString();
			if (kq != null) {
				List<PieperComment> listResult = new ArrayList<PieperComment>();
				JSONArray array = new JSONArray(kq);
				for (int i = 0; i < array.length(); i++) {
					JSONObject json = array.getJSONObject(i);
					PieperComment pieperComment = new PieperComment();
					pieperComment.setId(json.getInt("_id"));
					pieperComment.setFo100(json.getInt("FO100"));
					pieperComment.setPieperId(json.getInt("PIEPER_ID"));
					pieperComment.setComment(json.getString("COMMENT"));
					pieperComment.setPd378String(json.getString("PD378"));
					JSONObject m900 = json.getJSONObject("M900");
					pieperComment.setNv100(m900.getString("NV100"));
					pieperComment.setNv106(m900.getString("NV106"));
					pieperComment.setMv903(m900.getString("MV903"));
					pieperComment.setMv905(m900.getString("MV905"));
					pieperComment.setRegion(m900.getString("REGION"));
					if (m900.has("MV908"))
						pieperComment.setMv908(m900.getString("MV908"));
					if (m900.has("NV101"))
						pieperComment.setNv101(m900.getString("NV101"));
					if(json.has("AVARTA_URL"))
						pieperComment.setUrlAvarta(json.getString("AVARTA_URL"));
					listResult.add(pieperComment);
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
	public PieperComment pieperListOfTabCommentDetail(String pnPiperType, int pnPiperId, int pnFO100, int commentId) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_LISTOFTAB_COMMENT_DETAIL);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			setParameterNumber(commentId);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100).toString();
			if (kq != null) {
				JSONObject json = new JSONObject(kq);
				PieperComment pieperComment = new PieperComment();
				pieperComment.setId(json.getInt("_id"));
				pieperComment.setFo100(json.getInt("FO100"));
				pieperComment.setPieperId(json.getInt("PIEPER_ID"));
				pieperComment.setComment(json.getString("COMMENT"));
				pieperComment.setPd378String(json.getString("PD378"));
				JSONObject m900 = json.getJSONObject("M900");
				pieperComment.setNv100(m900.getString("NV100"));
				pieperComment.setNv106(m900.getString("NV106"));
				pieperComment.setMv903(m900.getString("MV903"));
				pieperComment.setMv905(m900.getString("MV905"));
				pieperComment.setRegion(m900.getString("REGION"));
				if (m900.has("MV908"))
					pieperComment.setMv908(m900.getString("MV908"));
				if (m900.has("NV101"))
					pieperComment.setNv101(m900.getString("NV101"));
				if(json.has("AVARTA_URL"))
					pieperComment.setUrlAvarta(json.getString("AVARTA_URL"));
				return pieperComment;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<N100PMG> pieperListOfTabUserLike(String pnPiperType, int pnPiperId, int pnFO100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<N100PMG> listResult = null;
		try{
			setFunctionName(QbMongoFunctionNames.PIEPER_LISTOFTAB_USER_LIKE);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnPiperId);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100).toString();
			if(resultData != null){
				listResult = new ArrayList<N100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for(int i = 0 ; i< jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					JSONObject n100 = jsonObject.getJSONObject("N100");
					n100pmg.setFo100(Integer.parseInt(n100.getString("FO100")));
					n100pmg.setNv101(n100.getString("NV101"));
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
	public int repiep(String pnPiperType, int pnPiperId, int pnFO100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_UPDATETAB_PA315);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			int result = (int) Double.parseDouble(
					executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int pieperUpdateTabPn306(String pnPiperType, int pnPiperId, int pnFO100, int pn306) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.PIEPER_UPDATETAB_PN306);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			setParameterNumber(pn306);
			int result = (int) Double.parseDouble(
						executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100)
							.toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<N100PMG> pieperListOfTabUserView(String pnPiperType, int pnPiperId, int pnFO100, int offset, int limit) {
		// TODO Auto-generated method stub
		List<N100PMG> listResult = null;
		try{
			setFunctionName(QbMongoFunctionNames.PIEPER_LISTOFTAB_USER_VIEW);
			setParameterString(pnPiperType);
			setParameterNumber(pnPiperId);
			setParameterNumber(pnFO100);
			setParameterNumber(offset);
			setParameterNumber(limit);
			String resultData = executeFunction(ApplicationConstant.DB_TYPE_PIEPME, pnFO100).toString();
			if(resultData != null){
				listResult = new ArrayList<N100PMG>();
				JSONArray jsonArray = new JSONArray(resultData);
				for(int i = 0 ; i< jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					N100PMG n100pmg = new N100PMG();
					JSONObject n100 = jsonObject.getJSONObject("N100");
					n100pmg.setFo100(Integer.parseInt(n100.getString("FO100")));
					n100pmg.setNv101(n100.getString("NV101"));
					n100pmg.setUrlAvarta(jsonObject.getString("AVARTA_URL"));
					listResult.add(n100pmg);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listResult;
	}

}
