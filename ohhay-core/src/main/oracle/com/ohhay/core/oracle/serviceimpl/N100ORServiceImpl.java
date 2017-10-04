package com.ohhay.core.oracle.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.oracle.N100AddOR;
import com.ohhay.core.entities.oracle.N100OCAF;
import com.ohhay.core.entities.oracle.N100OR;
import com.ohhay.core.oracle.dao.N100ORDao;
import com.ohhay.core.oracle.service.N100ORService;
import com.ohhay.core.utils.DateHelper;
@Service(value = SpringBeanNames.SERVICE_NAME_N100OR)
@Scope("prototype")
public class N100ORServiceImpl implements N100ORService{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_N100OR)
	private N100ORDao n100orDao;
	@Override
	public int insertTabN100(int pnPN100, String pvNV101, String pvNV102, String pvNV103, String pvNV104, String pvNV105, String pvNV106, String pvNV107, Date pdND108, String pvNV109,String pvNV119,int pnFO100, int pnFK100, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n100orDao.insertTabN100(pnPN100, pvNV101, pvNV102, pvNV103, pvNV104, pvNV105, pvNV106, pvNV107, pdND108, pvNV109, pvNV119, pnFO100, pnFK100, pvLOGIN);
	}
	@Override
	public List<N100OR> listOfTabN100OH(int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.listOfTabN100OH(offset, limit, pvLogin);
	}
	@Override
	public int insertTabN100EVO(String pvNV101, String pvNV102, String pvNV103, String pvNV104, String pvNV105, String pvNV106, String pvNV107, Date pdND108, String pvNV109, String pvNV112, Date pdND114, double pnNN115, String pvNV119, String pvNV126, String pvNV127, String pvNV128, Date pvND129, String pvNV130, String pvNV131, Date pdND132, String pvNV133, String pvNV134, String pvNV135, String pvNV136, String pvNV137, String pvNV138, String pvNV139, int fo100, int pnFK100, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n100orDao.insertTabN100EVO(pvNV101, pvNV102, pvNV103, pvNV104, pvNV105, pvNV106, pvNV107, pdND108, pvNV109, pvNV112, pdND114, pnNN115, pvNV119, pvNV126, pvNV127, pvNV128, pvND129, pvNV130, pvNV131, pdND132, pvNV133, pvNV134, pvNV135, pvNV136, pvNV137, pvNV138, pvNV139, fo100, pnFK100, pvLOGIN);
	}
	@Override
	public String insertTabN100PIE(String pvNV101, String pvNV102, String pvNV106, int pnFO100, int pnFK100, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.insertTabN100PIE(pvNV101, pvNV102, pvNV106, pnFO100, pnFK100, pvLogin);
	}
	@Override
	public List<N100OR> listOfTabN100AFF(String pvNV126,int offset,int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n100orDao.listOfTabN100AFF(pvNV126,offset,limit, pvLOGIN);
	}
	@Override
	public List<N100OR> listOfTabN100CUS(String pvNV126,int offset,int limit, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n100orDao.listOfTabN100CUS(pvNV126, offset,limit,pvLOGIN);
	}
	@Override
	public List<N100OR> listOfTabN100OPIE(int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.listOfTabN100OPIE(offset, limit, pvLogin);
	}
	@Override
	public int updateTabN100BUS(int pnFO100, String pvNV107, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n100orDao.updateTabN100BUS(pnFO100, pvNV107, pvLOGIN);
	}
	@Override
	public int updateTabN100AFF(int pnFO100, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n100orDao.updateTabN100AFF(pnFO100, pvLOGIN);
	}
	@Override
	public List<N100OR> listOfTabN100IWA(String pvNV126, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.listOfTabN100IWA(pvNV126, offset, limit, pvLogin);
	}
	@Override
	public int updateTabN100IWA(int pnFO100, String pvLOGIN) {
		// TODO Auto-generated method stub
		return n100orDao.updateTabN100IWA(pnFO100, pvLOGIN);
	}
	@Override
	public int updateTabN100ADD(String nv101, String nv102, Date pdND108, String pvNV109, String pvNV127, String pvNV130, String pvNV131,
			int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return  n100orDao.updateTabN100ADD(nv101, nv102, pdND108, pvNV109, pvNV127, pvNV130, pvNV131, fo100, pvLogin);
	}
	@Override
	public List<N100AddOR> listOfTabN100OADD(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		List<N100AddOR> list = n100orDao.listOfTabN100OADD(fo100, pvLogin);
		for(N100AddOR n100AddOR: list){
			if(n100AddOR.getNd108() != null){
				String dateString = DateHelper.formatDateShort(n100AddOR.getNd108());
				n100AddOR.setNd108Str(dateString);
			}
		}
		return list;
	}
	@Override
	public int updateTabN100REG(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.updateTabN100REG(fo100, pvLogin);
	}
	@Override
	public int updateTabN100IMM(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.updateTabN100IMM(fo100, pvLogin);
	}
	@Override
	public List<N100OR> listOfTabN100RIM(String nv126, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.listOfTabN100RIM(nv126, offset, limit, pvLogin);
	}
	@Override
	public List<N100OR> listOfTabN100IMM(String nv126, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.listOfTabN100IMM(nv126, offset, limit, pvLogin);
	}
	@Override
	public int confirmTabN100IMM(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.confirmTabN100IMM(fo100, pvLogin);
	}
	@Override
	public List<N100OCAF> listOfTabN100OCAF(int fo100, int offset, int limit, String pvLogin) {
		// TODO Auto-generated method stub
		return n100orDao.listOfTabN100OCAF(fo100, offset, limit, pvLogin);
	}
	
}
