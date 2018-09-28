package com.candelalabs.emailService.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	public boolean sendMail() {

		final String username = "awplpresales6@gmail.com";
		final String password = "awplawpl";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("vitkaryallappa@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("padmakant.baloji@candelalabs.io"));
			message.setSubject("Health Insurance Tax Benefit+Family Protection");
	
		  	 MimeMultipart multipart = new MimeMultipart("related");
			 BodyPart messageBodyPart = new MimeBodyPart();
			 String htmlText = "<img src=\"cid:image\">"
			 		+ "<H1 style=\" color:blue\">Save up to &#8377; 16,500 in taxes under section 80D</H1> \n"
			 		+ "\n"
			 		+ "<p>While health insurance helps protect you & your"
			 		+ " family from unforeseen & sudden medical expense, "
			 		+ "<br> it also helps you get tax benefit under Section 80D of the Income "
			 		+ "Tax Act 1961.<br> This makes health insurance an important component of a good financial plan<br><br></p>"
			 		+ "<a href=\"www.google.com\">Get Health Insurance to Save Tax</a>";
			 messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);
	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource("C:resources/logo.jpg");
	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");
	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);
	         // put everything together
	         message.setContent(multipart);
			
			Transport.send(message);
			logger.info("Email sent successfully");
			return true;
		} catch (MessagingException e) {
			logger.info("Email sent successfully"+e);
			return false;
		}

	}

}
