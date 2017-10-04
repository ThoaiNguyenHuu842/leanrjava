package com.ohhay.core.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.WebSendMailCriteria;
import com.ohhay.core.entities.N100;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;

/**
 * @author ThoaiNH
 * date create: 06/07/2015
 */
@Service(value = SpringBeanNames.SERVICE_NAME_SMTPEMAIL)
@Scope("prototype")
public class SMTPEmail {
	private Logger log = Logger.getLogger(SMTPEmail.class);
	@Autowired
	TemplateService templateService;	
	/**
	 * confirm that email and password is valid and can using to send mail
	 * @param email
	 * @param password
	 * @return 1: email account is ok, 0: is invalid
	 */
	public static int confirmEmailAccount(final String email, final String password){
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email,password);
					}
			});
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("oohhay@queenb.vn"));
			message.setSubject("Confirm email smtp o!hay");
			message.setText("Confirm my smtp email");
			Transport.send(message);
		} catch (MessagingException e) {
			return 0;
		}
		return 1;
	}
}
