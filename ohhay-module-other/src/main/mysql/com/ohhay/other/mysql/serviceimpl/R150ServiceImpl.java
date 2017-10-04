package com.ohhay.other.mysql.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.other.entities.R150;
import com.ohhay.other.mongo.service.M150SMGService;
import com.ohhay.other.mysql.dao.R150Dao;
import com.ohhay.other.mysql.service.R150Service;

@Service(value = SpringBeanNames.SERVICE_NAME_R150)
@Scope("prototype")
public class R150ServiceImpl implements R150Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R150)
	private R150Dao r150Dao;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private M150SMGService m150smgService;
	@Override
	public List<R150> rhayReportTabR150(int pnFO100, Date pdRD154, int pnOFFSET, int pnLIMIT, String pvLOGIN){
		return r150Dao.rhayReportTabR150(pnFO100, pdRD154, pnOFFSET, pnLIMIT, pvLOGIN);
	}

	@Override
	public List<R150> rhayReportTabR150hist(int pnFO100, int pnOFFSET, int pnLIMIT, String pvLOGIN){
		return r150Dao.rhayReportTabR150hist(pnFO100, pnOFFSET, pnLIMIT, pvLOGIN);
	}

	@Override
	public int updateTabR150(int pr150, String rv153, String pvLogin) {
		// TODO Auto-generated method stub
		return r150Dao.updateTabR150(pr150, rv153, pvLogin);
	}

	@Override
	public int acceptShare(int pr150, int fm150, int fo100, String pvlogin) {
		// TODO Auto-generated method stub
		int kq = updateTabR150(pr150, "Y", pvlogin);
		if(kq > 0)
		{
			kq = m150smgService.updateTabMn211(fo100, fm150, 1);
		}
		return kq;
	}

	@Override
	public int denyShare(int pr150, int fm150, int fo10, String pvLogin) {
		// TODO Auto-generated method stub
		return updateTabR150(pr150, "N", pvLogin);
	}

	@Override
	public int getRowTabR150(int pnFO100, Date pdRD154, String pvLOGIN) {
		// TODO Auto-generated method stub
		return r150Dao.getRowTabR150(pnFO100, pdRD154, pvLOGIN);
	}
}
