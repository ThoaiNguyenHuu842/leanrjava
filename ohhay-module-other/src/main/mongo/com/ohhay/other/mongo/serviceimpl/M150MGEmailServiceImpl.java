package com.ohhay.other.mongo.serviceimpl;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.amazonaws.services.elastictranscoder.model.Job;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.base.util.AESUtils;
import com.ohhay.core.authentication.AuthenticationHelper;
import com.ohhay.core.constant.RegexConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.criteria.WebSendMailCriteria;
import com.ohhay.core.entities.N100;
import com.ohhay.core.entities.Q100;
import com.ohhay.core.entities.mongo.other.M150MG;
import com.ohhay.core.entities.mongo.profile.M900MG;
import com.ohhay.core.mongo.service.TemplateService;
import com.ohhay.core.utils.ApplicationHelper;
import com.ohhay.other.api.service.M350OrelService;
import com.ohhay.other.mongo.service.M150MGEmailService;
import com.ohhay.other.mysql.service.N100Service;

@Service(value = SpringBeanNames.SERVICE_NAME_M150MGEMAIL)
@Scope("prototype")
public class M150MGEmailServiceImpl implements M150MGEmailService {
	private static Logger log = Logger.getLogger(M150MGEmailServiceImpl.class);
	@Autowired
	TemplateService templateService;
	@Autowired
	VelocityEngine ve;
	public int send(int fo100, String [] listMails, int fm150) {
		try {
			N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
			N100 n100 = null;
			try{
				log.info("---nhayListOfTabN100Smtp:"+fo100);
				n100 = n100Service.nhayListOfTabN100Smtp(fo100,ApplicationConstant.PVLOGIN_ANONYMOUS).get(0);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			if(n100 != null && n100.getNv120() != null && n100.getNv120().isEmpty() == false){
				log.info("---version 1");
				log.info(listMails);
				log.info("---fo100:"+fo100);
				log.info("---fm150"+fm150);
				if(listMails != null && listMails.length > 0)
				{
					/*
					 * 0) validate email
					 */
					for(String mail: listMails)
					{
						if(ApplicationHelper.validateRegex(mail.trim(),RegexConstant.EMAIL_PATTERN) == false)
						{
							return -2;
						}
					}
					templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
					M150MG m150mg = templateService.findDocumentById(0, fm150, M150MG.class);
					
					/*
					 * 1) load email template
					 */
					VelocityContext context = new VelocityContext();
					/*
					 * 2) set content to template
					 */
					context.put("fo100",m150mg.getFo100());
					context.put("topicTitle", m150mg.getMv151());
					context.put("topicBody",  m150mg.getMv152());
					
					templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
					//sender profile
					M900MG m900mgSender = null;
					if(fo100 == 0)
					{
						Q100 q100 = AuthenticationHelper.getUserLogin();
						fo100 = q100.getM900mg().getId();
						m900mgSender = q100.getM900mg();
					}
					else{
						m900mgSender = templateService.findDocumentById(fo100, fo100, M900MG.class);
					}
					if(m900mgSender != null){
						//topic user profile
						M900MG m900mg = templateService.findDocumentById(m150mg.getFo100(), m150mg.getFo100(), M900MG.class);
						context.put("topicName", m900mgSender.getNv100Decrypt());
						context.put("topicTime", new Date());
						context.put("topicUrlUser", "https://topic.oohhay.com/"+m900mgSender.getUrlOhhay());
						context.put("topicMailLink1", "<a style='color: #666;font-weight: bold;font-style: italic;text-decoration: none;' target='_blank' href='mailto:"+m900mgSender.getMv903Decrypt()+"'>"+m900mgSender.getNv100Decrypt()+"</a>");
						context.put("topicMailLink2", "<a style='color: #0099ff;font-weight: bold;text-decoration: none;' target='_blank' href='mailto:"+m900mgSender.getMv903Decrypt()+"'>"+m900mgSender.getNv100Decrypt()+"</a>");
						context.put("topicUrl", "https://topic.oohhay.com/"+ m900mg.getUrlOhhay() +"/showdetail-"+ fm150);
						context.put("topicOhhay","<a href='https://www.oohhay.com/"+m900mgSender.getUrlOhhay()+"' style='display: block;'><img src='https://oohhay.com/html/images/email/ohay.png' style='width: 31px;'/></a>");
						context.put("topicFacebook","<a style='display: block;'><img src='https://oohhay.com/html/images/email/facebook.png' style='width: 31px;'/></a>");
						context.put("topicTwitter","<a style='display: block;'><img src='https://oohhay.com/html/images/email/twitter.png' style='width: 31px;'/></a>");
						context.put("topicGoogle","<a style='display: block;'><img src='https://oohhay.com/html/images/email/google.png' style='width: 31px;'/></a>");
						context.put("topicHome","<a href='https://www.oohhay.com'><img src='https://oohhay.com/html/images/email/ohay-05.png' style='width: 42px;' /></a>");
						
						// topic with img
						if (m150mg.getListM170mgs() != null
								&& m150mg.getListM170mgs().size() > 0)
							context.put("topicImg", "<img style='width: 100%;' src='https://oohhay.s3.amazonaws.com/"
									+ m150mg.getListM170mgs().get(0).getImg() + "'></img>");
						// topic with iframe
						else if (m150mg.getM180mg() != null)
							context.put("topicImg", "<a href='https://topic.oohhay.com/"+ m900mg.getUrlOhhay() +"/showdetail-"+fm150+"'><img style='width: 100%;' src='"
									+ "https://topic.oohhay.com/html/images/iframe.png"
									+ "'></img></a>");
						
						else
							context.put("topicImg", "");
						
						
						Template t = ve.getTemplate("email_topic_template.vm", "UTF-8");
						StringWriter writer = new StringWriter();
						for(String destination: listMails)
						{
							context.put("email", destination);
							/*
							 * 3) merge with content
							 */
							t.merge(context, writer);
							/*
							 * 4) send mail
							 */
							try{
								M350OrelService m350OrelService = (M350OrelService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350OREL);
								m350OrelService.sendMailTabM350Topic(fo100, destination, 
										n100.getNv120(), ApplicationHelper.removeAccent(m900mgSender.getNv100Decrypt()),
										n100.getNv116(), n100.getNv117(),  
										n100.getNv118(), n100.getNv119(), 
										ApplicationHelper.decodeTopicString(m150mg.getMv151()), 
										ApplicationHelper.decodeTopicString(writer.toString()), 
										ApplicationConstant.PVLOGIN_ANONYMOUS);
							}
							catch(Exception ex){
								ex.printStackTrace();
							}
						}
						/*AWSEmail awsEmail = (AWSEmail) ApplicationHelper
								.findBean(SpringBeanNames.SERVICE_NAME_AWSEMAIL);
						awsEmail.sendEmail(listMails, writer.toString(), decodeTopicString(m150mg
								.getMv151()), senderName);*/
						/*log.info(writer.toString());*/
					}
					else
						return 0;
				}
				else
					return -2;
			}else
			{
				return -3;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return 1;
	}
	@Override
	public String getTopicMailHtml(int fo100, int fm150) {
		// TODO Auto-generated method stub
		try {
			templateService.setOperation(ApplicationConstant.DB_TYPE_TOPIC);
			M150MG m150mg = templateService.findDocumentById(0, fm150, M150MG.class);
			/*
			 * 1) load email template
			 */
			VelocityContext context = new VelocityContext();
			/*
			 * 2) set content to template
			 */
			context.put("fo100", m150mg.getFo100());
			context.put("topicTitle", m150mg.getMv151());
			context.put("topicBody", m150mg.getMv152());

			templateService.setOperation(ApplicationConstant.DB_TYPE_OHHAY);
			// sender profile
			M900MG m900mgSender = null;
			if (fo100 == 0) {
				Q100 q100 = AuthenticationHelper.getUserLogin();
				fo100 = q100.getM900mg().getId();
				m900mgSender = q100.getM900mg();
			}
			else {
				m900mgSender = templateService.findDocumentById(fo100, fo100, M900MG.class);
			}
			if (m900mgSender != null) {
				// topic user profile
				M900MG m900mg = templateService.findDocumentById(m150mg.getFo100(), m150mg.getFo100(), M900MG.class);
				context.put("topicName", m900mgSender.getNv100Decrypt());
				context.put("topicTime", new Date());
				context.put("topicUrlUser", "https://topic.oohhay.com/"
						+ m900mgSender.getUrlOhhay());
				context.put("topicMailLink1", "<a style='color: #666;font-weight: bold;font-style: italic;text-decoration: none;' target='_blank' href='mailto:"
						+ m900mgSender.getMv903Decrypt() + "'>"
						+ m900mgSender.getNv100Decrypt() + "</a>");
				context.put("topicMailLink2", "<a style='color: #0099ff;font-weight: bold;text-decoration: none;' target='_blank' href='mailto:"
						+ m900mgSender.getMv903Decrypt() + "'>"
						+ m900mgSender.getNv100Decrypt() + "</a>");
				context.put("topicUrl", "https://topic.oohhay.com/"
						+ m900mg.getUrlOhhay() + "/showdetail-" + fm150);
				context.put("topicOhhay", "<a href='https://www.oohhay.com/"
						+ m900mgSender.getUrlOhhay()
						+ "' style='display: block;'><img src='https://oohhay.com/html/images/email/ohay.png' style='width: 31px;'/></a>");
				context.put("topicFacebook", "<a style='display: block;'><img src='https://oohhay.com/html/images/email/facebook.png' style='width: 31px;'/></a>");
				context.put("topicTwitter", "<a style='display: block;'><img src='https://oohhay.com/html/images/email/twitter.png' style='width: 31px;'/></a>");
				context.put("topicGoogle", "<a style='display: block;'><img src='https://oohhay.com/html/images/email/google.png' style='width: 31px;'/></a>");
				context.put("topicHome", "<a href='https://www.oohhay.com'><img src='https://oohhay.com/html/images/email/ohay-05.png' style='width: 42px;' /></a>");

				// topic with img
				if (m150mg.getListM170mgs() != null
						&& m150mg.getListM170mgs().size() > 0)
					context.put("topicImg", "<img style='width: 100%;' src='https://oohhay.s3.amazonaws.com/"
							+ m150mg.getListM170mgs().get(0).getImg()
							+ "'></img>");
				// topic with iframe
				else if (m150mg.getM180mg() != null)
					context.put("topicImg", "<a href='https://topic.oohhay.com/"
							+ m900mg.getUrlOhhay() + "/showdetail-" + fm150
							+ "'><img style='width: 100%;' src='"
							+ "https://topic.oohhay.com/html/images/iframe.png"
							+ "'></img></a>");

				else
					context.put("topicImg", "");
				Template t = ve.getTemplate("email_topic_template.vm", "UTF-8");
				context.put("email", "YourDestinationEmail");
				StringWriter writer = new StringWriter();
				t.merge(context, writer);
				return ApplicationHelper.decodeTopicString(writer.toString());
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public int sendWebStmp(WebSendMailCriteria webSendMailCriteria) {
		// TODO Auto-generated method stub
		try {
			int fo100 = Integer.parseInt(AESUtils.decrypt(webSendMailCriteria.getFo100()));
			if(webSendMailCriteria.getEmailReceive() == null || webSendMailCriteria.getEmailReceive().isEmpty())
				return -1;
			else {
				N100Service n100Service = (N100Service) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_N100);
				N100 n100 = null;
				try{
					log.info("---nhayListOfTabN100Smtp:"+fo100);
					n100 = n100Service.nhayListOfTabN100Smtp(fo100,ApplicationConstant.PVLOGIN_ANONYMOUS).get(0);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				if(n100 != null && n100.getNv120() != null && n100.getNv120().isEmpty() == false)
				{
					//send mail by orel topic ---> will change
					int kq = 0;
					M350OrelService m350OrelService = (M350OrelService) ApplicationHelper.findBean(SpringBeanNames.SERVICE_NAME_M350OREL);
					kq = m350OrelService.sendMailTabM350Topic(fo100, webSendMailCriteria.getEmailReceive(), 
								n100.getNv120(), ApplicationHelper.removeAccent(webSendMailCriteria.getUserSend()),
								n100.getNv116(), n100.getNv117(),  
								n100.getNv118(), n100.getNv119(), 
								"Message From Your O!hay Website - "+ApplicationHelper.removeAccent(webSendMailCriteria.getUserSend()),
								webSendMailCriteria.getContentSend(),
								ApplicationConstant.PVLOGIN_ANONYMOUS);
					return kq;
				}
				else
					return -2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}
}
