package com.ohhay.core.mail;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;

/**
 * @author ThoaiNH
 * create 10/12/2014
 * s3 email sender
 */
@Service(value = SpringBeanNames.SERVICE_NAME_AWSEMAIL)
@Scope("prototype")
public class AWSEmail {
	private Logger log = Logger.getLogger(AWSEmail.class);
	private AmazonSimpleEmailServiceClient client;

	public AWSEmail() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(
				ApplicationConstant.AWS_KEY,
				ApplicationConstant.AWS_KEY_SECRECT);
		client = new AmazonSimpleEmailServiceClient(awsCreds);
	}
	/*
	 * confirm when change email
	 */
	public int sendEmail(String [] emails, String emailBody, String emailSubject, String sender) {
		// Construct an object to contain the recipient address.
		Destination destination = new Destination().withToAddresses(emails);
		// Create the subject and body of the message.
		Content subject = new Content().withData(emailSubject);
		Content textBody = new Content().withData(emailBody);
		Body body = new Body().withHtml(textBody);
		// Create a message with the specified subject and body.
		Message message = new Message().withSubject(subject).withBody(body);
		// Assemble the email.
		SendEmailRequest request = new SendEmailRequest()
				.withSource(sender)
				.withDestination(destination).withMessage(message);
		try {
			Region REGION = Region.getRegion(Regions.EU_WEST_1);
			client.setRegion(REGION);
			// Send the email.
			client.sendEmail(request);
			log.info("Email sent!");
			return 1;
		} catch (Exception ex) {
			log.info("The email was not sent.");
			log.info("Error message: " + ex.getMessage());
			return 0;
		}
	}
	/*
	 * confirm when change email
	 */
	public int sendEmail(String newEmail, String emailBody, String emailSubject, String sender) {
		// Construct an object to contain the recipient address.
		Destination destination = new Destination()
				.withToAddresses(new String[] { newEmail });
		// Create the subject and body of the message.
		Content subject = new Content().withData(emailSubject);
		Content textBody = new Content().withData(emailBody);
		Body body = new Body().withHtml(textBody);
		// Create a message with the specified subject and body.
		Message message = new Message().withSubject(subject).withBody(body);
		// Assemble the email.
		SendEmailRequest request = new SendEmailRequest()
				.withSource(sender)
				.withDestination(destination).withMessage(message);
		try {
			Region REGION = Region.getRegion(Regions.EU_WEST_1);
			client.setRegion(REGION);
			// Send the email.
			client.sendEmail(request);
			log.info("Email sent!");
			return 1;
		} catch (Exception ex) {
			log.info("The email was not sent.");
			log.info("Error message: " + ex.getMessage());
			return 0;
		}
	}
}
