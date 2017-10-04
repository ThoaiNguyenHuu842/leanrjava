package com.ohhay.core.mail;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

/**
 * @author ThoaiNH
 * create 25/07/2015
 * softlayer email sender
 */
@Service(value = SpringBeanNames.SERVICE_NAME_SENDGRIDEMAIL)
@Scope("prototype")
public class SendGridEmail {
	private static Logger log = Logger.getLogger(SendGridEmail.class);
	public int sendEmail(String [] emails, String emailBody, String emailSubject, String sender)
	{
		SendGrid sendgrid = new SendGrid(ApplicationConstant.SENDGIRD_KEY);
	    SendGrid.Email email = new SendGrid.Email();
	    email.addTo(emails);
	    email.setFrom(sender);
	    email.setSubject(emailSubject);
	    email.setHtml(emailBody);
	    try {
			SendGrid.Response response = sendgrid.send(email);
			if(response.getMessage().indexOf("success") >= 0)
				return 1;
		} catch (SendGridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int sendEmail(String mail, String emailBody, String emailSubject, String sender)
	{
		SendGrid sendgrid = new SendGrid(ApplicationConstant.SENDGIRD_KEY);
	    SendGrid.Email email = new SendGrid.Email();
	    email.addTo(new String[]{mail});
	    email.setFrom(sender);
	    email.setSubject(emailSubject);
	    email.setHtml(emailBody);
	    try {
			SendGrid.Response response = sendgrid.send(email);
			log.info("---SendGrid response:"+response.getMessage());
			if(response.getMessage().indexOf("success") >= 0)
				return 1;
		} catch (SendGridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
