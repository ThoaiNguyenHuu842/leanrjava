package com.ohhay.nexmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;

@Service(value = SpringBeanNames.SERVICE_NAME_SMS)
@Scope("prototype")
public class SmsServiceImpl implements SmsService {
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_SMS)
	SmsDao smsDao;

	@Override
	public int sendMessageNexmo(String to, String text) {
		// TODO Auto-generated method stub
		return smsDao.sendMessageNexmo(ApplicationConstant.NEXMO_API_KEY, ApplicationConstant.NEXMO_SECRET_KEY, ApplicationConstant.NEXMO_FROM_NAME, to, text);
	}

	@Override
	public int sendMessageNexmo2(String to, String o050Code) {
		// TODO Auto-generated method stub
		return smsDao.sendMessageNexmo2(ApplicationConstant.NEXMO_API_KEY, ApplicationConstant.NEXMO_SECRET_KEY, ApplicationConstant.NEXMO_FROM_NAME, to, o050Code);
	}
}
