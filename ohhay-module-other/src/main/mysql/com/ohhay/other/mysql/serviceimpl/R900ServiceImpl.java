package com.ohhay.other.mysql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.ChartItemInfo2;
import com.ohhay.other.mysql.dao.R900Dao;
import com.ohhay.other.mysql.service.R900Service;
@Service(value = SpringBeanNames.SERVICE_NAME_R900)
@Scope("prototype")
public class R900ServiceImpl implements R900Service{
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_R900)
	private R900Dao r900Dao;
	@Override
	public int insertTabR900(int fo100, String pvRV902, String pvRV904, String pvRV907, String pvRV908, String pvRV958, int pnREFID, int pnFO100T, String pvLogin){
		// TODO Auto-generated method stub
		return r900Dao.insertTabR900(fo100, pvRV902, pvRV904, pvRV907, pvRV908, pvRV958, pnREFID, pnFO100T, pvLogin);
	}
	@Override
	public int insertTabR900V1(int fo100, String pvRV902, String pvRV904, String pvRV907, String pvRV908, String pvRV958,String pvRV959,String pvRV960, int pnREFID, int pnFO100T, String pvLogin){
		// TODO Auto-generated method stub
		return r900Dao.insertTabR900V1(fo100, pvRV902, pvRV904, pvRV907, pvRV908, pvRV958,pvRV959,pvRV960, pnREFID, pnFO100T, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9001(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9001(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9002(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9002(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9003(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9003(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9004(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9004(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9005(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9005(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9006(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9006(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9007(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9007(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9008(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9008(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9009(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9009(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9010(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9010(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9011(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9011(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9012(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9012(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9013(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9013(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9014(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9014(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9015(int fo100, String pvRV901, String pvRV907, int pnINTER, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9015(fo100, pvRV901, pvRV907, pnINTER, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9001(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9001(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9002(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9002(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9003(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9003(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9004(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9004(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9005(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9005(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9006(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9006(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9007(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9007(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9008(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9008(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9009(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9009(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9010(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9010(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9011(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9011(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9012(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9012(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9013(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9013(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9014(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9014(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public List<ChartItemInfo2> reportTabR9015(int fo100, String pvRV901, String pvRV907, String pvRV957, int pnINTER, int pnREFID, String pvLogin) {
		// TODO Auto-generated method stub
		return r900Dao.reportTabR9015(fo100, pvRV901, pvRV907, pvRV957, pnINTER, pnREFID, pvLogin);
	}
	@Override
	public int insertTabR900a(int fo100, String pvRV901, String pvRV902, String pvRV904, int pnRN905, String pvRV907, String pvRV908, String pvRV913, String pvRV914, String pvRV915, String pvLOGIN) {
		// TODO Auto-generated method stub
		return r900Dao.insertTabR900a(fo100, pvRV901, pvRV902, pvRV904, pnRN905, pvRV907, pvRV908, pvRV913, pvRV914, pvRV915, pvLOGIN);
	}
}
