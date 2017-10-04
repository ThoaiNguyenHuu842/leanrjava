package com.ohhay.other.api.serviceimpl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.other.api.dao.CalendarDao;
import com.ohhay.other.api.service.CalendarService;

@Service(value = SpringBeanNames.SERVICE_NAME_CALENDAR_SERVICE)
@Scope("prototype")
public class CalendarServiceImpl implements CalendarService {
	@Autowired
	private CalendarDao dao;
		
	public String lich_convertsol2Lu(Date pdDATUM, String pvIPADD, String pvLOGIN) {
		return dao.lich_convertsol2Lu(pdDATUM, pvIPADD, pvLOGIN);
	}

}
