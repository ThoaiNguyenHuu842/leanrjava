package com.ohhay.web.other.mongo.daoimpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.mongo.QbMongoDaoSupport;
import com.ohhay.core.constant.QbMongoFiledsName;
import com.ohhay.core.constant.QbMongoFunctionNames;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.mongo.videomarketing.V910MG;
import com.ohhay.web.other.mongo.dao.V910MGDao;

@Repository(value = SpringBeanNames.REPOSITORY_NAME_V910MG)
@Scope("prototype")
public class V910MGDaoImpl extends QbMongoDaoSupport implements V910MGDao {
	@Override
	public int changeM940Index(int fo100, int pv910, int videoId, int newIndex) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.V910_UPDATETABVIDEO_INDEX);
			setParameterNumber(fo100);
			setParameterNumber(pv910);
			setParameterNumber(videoId);
			setParameterNumber(newIndex);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteVideo(int fo100, int pv910, int videoId) {
		// TODO Auto-generated method stub
		try {
			setFunctionName(QbMongoFunctionNames.V910_STORNOTABVIDEO);
			setParameterNumber(fo100);
			setParameterNumber(pv910);
			setParameterNumber(videoId);
			int kq = (int) Double.parseDouble(executeFunction(ApplicationConstant.DB_TYPE_OHHAY,fo100).toString());
			return kq;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<V910MG> loadListV910(int fo100) {
		// TODO Auto-generated method stub
		try {
			Query query2 = new Query();
			query2.fields().exclude(QbMongoFiledsName.VIDEOS);
			query2.addCriteria(Criteria.where(QbMongoFiledsName.FO100).is(fo100));
			query2.addCriteria(Criteria.where(QbMongoFiledsName.DATE_DELETE).exists(false));
			List<V910MG> list = getMongoOperations(ApplicationConstant.DB_TYPE_OHHAY, fo100).find(query2, V910MG.class);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	

}
