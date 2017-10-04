package com.ohhay.core.mongo.daoimpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.history.R900MG;
import com.ohhay.core.mongo.dao.R900MGDao;


@Repository(value = SpringBeanNames.REPOSITORY_NAME_R900MG)
@Scope("prototype")
public class R900MGDaoImpl extends QbMongoDaoSupport implements R900MGDao {

	@Override
	public int insertTabR900Vote(int fo100, int fo100Voted) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R900_INSERTABR900VOTE);
			setParameterNumber(fo100);
			setParameterNumber(fo100Voted);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertTabR900Bookmark(int fo100, int fo100Bookmarked, String rv921 , String rv922) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R900_INSERTABR900BOOKMARK);
			setParameterNumber(fo100);
			setParameterNumber(fo100Bookmarked);
			setParameterString(rv921);
			setParameterString(rv922);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int stornoTabR900Bookmark(int fo100, int fo100Bookmark) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R900_STORNOABR900BOOKMARK);
			setParameterNumber(fo100);
			setParameterNumber(fo100Bookmark);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public String getAllHisotry(int fo100) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R900_GETTOTALALL);
			setParameterNumber(fo100);
			String kq = executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString();
			return kq;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public List<R900MG> findPeopleBookmarkMe(int fo100, int limit) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.addCriteria(Criteria.where("RA902."+QbMongoFiledsName.FO100).is(fo100));
			query2.fields().include("RA902."+QbMongoFiledsName.FO100).include("RA902.RL946");
			if(limit > 0)
				query2.limit(limit);
			query2.with(new Sort(Sort.Direction.DESC, "RA902.RL946"));
			List<R900MG> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).find(query2, R900MG.class);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertTabR900Share(int fo100, int fo100s, int fo100f, String content) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.R900_INSERTABR900SHARE);
			setParameterNumber(fo100);
			setParameterNumber(fo100s);
			setParameterNumber(fo100f);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY, fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
