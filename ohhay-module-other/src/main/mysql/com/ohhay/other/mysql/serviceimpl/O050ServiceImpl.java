package com.ohhay.other.mysql.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.mongo.service.M900MGService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.nexmo.SmsService;
import com.ohhay.other.mysql.dao.O050Dao;
import com.ohhay.other.mysql.service.O050Service;

@Service(value = SpringBeanNames.SERVICE_NAME_O050)
@Scope("prototype")
public class O050ServiceImpl implements O050Service {
	public static final String ERROR_CODE = "000000";
	private static Logger log = Logger.getLogger(O050Service.class);
	@Autowired
	@Qualifier(value = SpringBeanNames.REPOSITORY_NAME_O050)
	O050Dao o050Dao;
	@Autowired
	M900MGService m900mgService;
	@Override
	public String ohayInsertTabO050(String pvOV051, String pvOV061, String pvLogin) {
		// TODO Auto-generated method stub
		return o050Dao.ohayInsertTabO050(pvOV051, pvOV061, pvLogin);
	}

	@Override
	public int ohayConfirmTabO050(String pvOV051, String pvOV054, String pvOV061, String pvLogin) {
		// TODO Auto-generated method stub
		return o050Dao.ohayConfirmTabO050(pvOV051, pvOV054, pvOV061, pvLogin);
	}

	@Override
	public int sendSMSCodeRegister(String phone, String countryCode) {
		// TODO Auto-generated method stub
		String processedPhone = ApplicationHelper.processDummiesPhones(phone, countryCode);
		log.info("---fullphone:"+ processedPhone);
		if (ApplicationHelper.validatePhoneNumberReal(processedPhone) == false)
			return -1;
		else if (m900mgService.checkUserExist(processedPhone) == 1)
			return -2;
		else {
			String smsPhone = "+"+countryCode + processedPhone.substring(1, processedPhone.length());
			log.info("---smsphone:"+smsPhone);
			String code = ohayInsertTabO050(phone, countryCode, ApplicationConstant.PVLOGIN_ANONYMOUS);
			log.info("---code:"+code);
			if(ERROR_CODE.equals(code))
				return -3;
			else {
				int kq = 0;
				if(countryCode.equals("1") || countryCode.equals("+1"))
				{
					SmsService smsService = (SmsService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SMS);
					kq = smsService.sendMessageNexmo2(smsPhone,code);
				}
				else {
					String text = "Your OTP Code to activate your O!hay Account is: "+code+". Thank you for your interest in our services.";
					switch (countryCode) {
					case "84":
						text= "Ma OTP kich hoat tai khoan O!hay cua ban la: "+code+". Cam on ban da su dung dich vu cua chung toi.";
						break;
					case "49": 
					case "43":
					case "41":
						text = "Dein OTP-Code zum Aktivieren Deines O!hay-Konto lautet: "+code+". Vielen Dank für Dein Interesse an unseren Produkten!";
					break;
					default:
						break;
					}
					log.info("--sms text:"+text);
					SmsService smsService = (SmsService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SMS);
					kq = smsService.sendMessageNexmo(smsPhone, text);
				}
				return kq;
			}
		}
	}
}
