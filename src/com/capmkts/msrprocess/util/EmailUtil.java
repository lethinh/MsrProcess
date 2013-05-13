package com.capmkts.msrprocess.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import microsoft.exchange.webservices.data.EmailMessage;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.ExchangeVersion;
import microsoft.exchange.webservices.data.MessageBody;
import microsoft.exchange.webservices.data.WebCredentials;


import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.data.CommitmentLetter;
import com.capmkts.msrprocess.generator.CommitmentLetterGenerator;
import com.capmkts.msrprocess.test.GoogleTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {
	
	public boolean sendSSLMessage(String recipients[], String subject,
			String message, String from, String attachment, byte[] pdfByteArray, String fileName){
		boolean debug = false;
		boolean isEmailSent = false;
		
		try{
			Properties props = new Properties();
			props.put("mail.smtp.host", MsrConstants.SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.port", MsrConstants.SMTP_PORT);
			
			//SSL settings
			props.put("mail.smtp.socketFactory.port", MsrConstants.SMTP_PORT);
			props.put("mail.smtp.socketFactory.class", MsrConstants.SSL_FACTORY);
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			//TSL setting
//			props.put("mail.smtp.starttls.enable","true");
			
			//Try this
//			Properties prop = new Properties();
//			props.setProperty("mail.imap.starttls.enable", "true");
//			props.setProperty("ssl.SocketFactory.provider", "my.package.name.ExchangeSSLSocketFactory");
//			props.setProperty("mail.imap.socketFactory.class", "my.package.name.ExchangeSSLSocketFactory");
//			session = Session.getDefaultInstance(prop, null);
//			session.setDebug(true);
//			store = session.getStore("imap");
//			store.connect("myHost","myUser","myPassword");
			
			Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MsrConstants.FROM_EMAIL, MsrConstants.EMAIL_PASS);
				}
			});
		
				session.setDebug(debug);
				
				Message msg = new MimeMessage(session);
				InternetAddress addressFrom = new InternetAddress(from);
				msg.setFrom(addressFrom);
				
				InternetAddress[] addressTo = new InternetAddress[recipients.length];
				for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
			}
				
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			
			// Setting the Subject and Content Type
			msg.setSubject(subject);
			
			Multipart mp = new MimeMultipart();

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(message);
			
			DataSource dataSource = new ByteArrayDataSource(pdfByteArray, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			//pdfBodyPart.setHeader("Content-Type", "application/pdf");
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName(fileName);
			
			mp.addBodyPart(mbp1);
			mp.addBodyPart(pdfBodyPart);
			
			//Use if attaching local file
//			MimeBodyPart mbp2 = new MimeBodyPart();
//			try
//			{
//			//attatch the file
//			mbp2.attachFile(attachment);
//			}
//			catch (IOException ioex)
//			{
//			ioex.printStackTrace();
//			}
//			mp.addBodyPart(mbp2);

			msg.setContent(mp);
			
			Transport.send(msg);
			
			isEmailSent = true;
		}catch(MessagingException e) {
			e.printStackTrace();
		};
		return isEmailSent;
	}
	
	public static boolean sendEmail(byte[] pdfByteArray, String fileName) throws Exception {
		boolean isEmailSent;
		System.out.println("\nSending Email to Patron.\n");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		FileUtil fileUtil = new FileUtil();
		String[] emailList = fileUtil.getEmailList("\\\\srv-file-01.capmkts.local\\ServicingAcquisition\\ServicingAcquisition\\FNMACoIssue\\EmailList\\EmailList.txt");
		
		EmailUtil emailUtil = new EmailUtil();
		isEmailSent = emailUtil.sendSSLMessage(
				emailList, 
				MsrConstants.EMAIL_SUBJECT, 
				MsrConstants.EMAIL_BODY, 
				MsrConstants.FROM_EMAIL,
				MsrConstants.ATTACHMENT,
				pdfByteArray,
				fileName);
		System.out.println("\nEmail Sucessfully Sent!\n");
		
		return isEmailSent;
    }
	
	//Send email with Attachment
	public boolean sendEWSEmail(byte[] pdfByteArray, String fileName){
		boolean isEmailSent;
		System.out.println("\nSending Email to Patron.\n");
		
		FileUtil fileUtil = new FileUtil();
		String[] emailList = null;
		try {
			emailList = fileUtil.getEmailList("\\\\srv-file-01.capmkts.local\\ServicingAcquisition\\ServicingAcquisition\\FNMACoIssue\\EmailList\\EmailCMCCommitLetterList.txt");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
        ExchangeCredentials credentials = new WebCredentials(MsrConstants.FROM_EMAIL, MsrConstants.EMAIL_PASS);
        service.setCredentials( credentials );    
        try {
			service.setUrl( new URI("https://srv-mail-01.capmkts.local/ews/exchange.asmx") );
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
        try{
	        FileOutputStream fileOuputStream = new FileOutputStream(fileName); 
		    fileOuputStream.write(pdfByteArray);
		    fileOuputStream.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        try{
	        EmailMessage message = new EmailMessage(service);
	        for (int i=0; i<emailList.length; i++){
		    	message.getToRecipients().add(emailList[i]);
	        }
		    message.setSubject("CMC Commitment Letter");
		    message.setBody(MessageBody.getMessageBodyFromText("Email attachements"));
		    message.getAttachments().addFileAttachment(fileName);
		    message.send();
	    	isEmailSent = true;
        }catch(Exception e){
        	isEmailSent = false;
        }

		System.out.println("\nEmail Sucessfully Sent!\n");
		
		return isEmailSent;
    }
	
	//Send email with NO attachments
	public boolean sendEWSEmail(String subject, String body){
		boolean isEmailSent;
		System.out.println("\nSending Email to Patron.\n");
		
		FileUtil fileUtil = new FileUtil();
		String[] emailList = null;
		try {
			emailList = fileUtil.getEmailList("\\\\srv-file-01.capmkts.local\\ServicingAcquisition\\ServicingAcquisition\\FNMACoIssue\\EmailList\\NotificationList.txt");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
        ExchangeCredentials credentials = new WebCredentials(MsrConstants.FROM_EMAIL, MsrConstants.EMAIL_PASS);
        service.setCredentials( credentials );    
        try {
			service.setUrl( new URI("https://srv-mail-01.capmkts.local/ews/exchange.asmx") );
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        
        try{
	        EmailMessage message = new EmailMessage(service);
	        for (int i=0; i<emailList.length; i++){
		    	message.getToRecipients().add(emailList[i]);
	        }
		    message.setSubject(subject);
		    message.setBody(MessageBody.getMessageBodyFromText(body));
		    message.send();
	    	isEmailSent = true;
        }catch(Exception e){
        	isEmailSent = false;
        }

		System.out.println("\nEmail Sucessfully Sent!\n");
		
		return isEmailSent;
    }
	
	//Send email if there are errors
		public boolean sendErrorEmail(String subject, String body){
			boolean isEmailSent;
			System.out.println("\nSending Email to Patron.\n");
			
			FileUtil fileUtil = new FileUtil();
			String[] emailList = null;
			try {
				emailList = fileUtil.getEmailList("\\\\srv-file-01.capmkts.local\\ServicingAcquisition\\ServicingAcquisition\\FNMACoIssue\\EmailList\\EmailFailedList.txt");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
	        ExchangeCredentials credentials = new WebCredentials(MsrConstants.FROM_EMAIL, MsrConstants.EMAIL_PASS);
	        service.setCredentials( credentials );    
	        try {
				service.setUrl( new URI("https://srv-mail-01.capmkts.local/ews/exchange.asmx") );
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
	        
	        try{
		        EmailMessage message = new EmailMessage(service);
		        for (int i=0; i<emailList.length; i++){
			    	message.getToRecipients().add(emailList[i]);
		        }
			    message.setSubject(subject);
			    message.setBody(MessageBody.getMessageBodyFromText(body));
			    message.send();
		    	isEmailSent = true;
	        }catch(Exception e){
	        	isEmailSent = false;
	        }

			System.out.println("\nEmail Sucessfully Sent!\n");
			
			return isEmailSent;
	    }
		
	
	public static void main(String[] args) throws Exception{
		
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		
		byte[] test = {23, 42, 32};
		FileUtil fileUtil = new FileUtil();
		String[] emailList = fileUtil.getEmailList("C:\\emailList.txt");
		for (int j=0; j<emailList.length; j++){
			System.out.println("\n\nEMAIL LIST: " + emailList[j]+ "\n\n");
			}
		
//		sendEWSEmail(test, "TestAttachment");
//		sendEmail(test);
//		new EmailUtil().sendSSLMessage(
//				emailList, 
//				MsrConstants.EMAIL_SUBJECT, 
//				MsrConstants.EMAIL_BODY, 
//				MsrConstants.FROM_EMAIL,
//				MsrConstants.ATTACHMENT,
//				test,
//				"Test.pdf");
		System.out.println("Sucessfully Sent mail to All Users");
	}
}
