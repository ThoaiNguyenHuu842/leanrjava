package com.ohhay.cronjob;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.entities.M350;
import com.ohhay.core.mail.AWSEmail;
import com.ohhay.core.mail.SendGridEmail;
import com.ohhay.core.mysql.service.M350Service;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * create: 02/05/2015
 * update: 11/08/2015 - using SendGird to send mail
 */
@Service
public class CronJobSendEmail implements ICronJob {
	private static Logger log = Logger.getLogger(CronJobSendEmail.class);
	public void run() {
		M350Service m350Service = (M350Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350);
		List<M350> listM350s = m350Service.listOfTabM350Send(ApplicationConstant.PVLOGIN_ANONYMOUS);
		for(M350 m350:listM350s){
			SendGridEmail sendGridEmail = (SendGridEmail) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_SENDGRIDEMAIL);
			log.info("--send email to:"+m350.getMv363());
			String sender = ApplicationConstant.AWS_MAIL_SENDDER;
			//add name of user send this mail
			if(m350.getMv367() != null && m350.getMv367().trim().length() > 0)
				sender = m350.getMv367().trim() +"<"+ApplicationConstant.AWS_MAIL_SENDDER+">";
			log.info("--sender:"+sender);
			int kq = sendGridEmail.sendEmail(m350.getMv363(), m350.getMv373(), m350.getMv375(), sender);
			log.info("--kq:"+kq);
		}
	}
}
