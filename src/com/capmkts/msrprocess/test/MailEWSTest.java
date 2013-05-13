package com.capmkts.msrprocess.test;

import java.io.IOException;
import java.net.URI;

import com.sun.jndi.toolkit.url.Uri;

import microsoft.exchange.webservices.data.EmailMessage;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.ExchangeVersion;
import microsoft.exchange.webservices.data.Folder;
import microsoft.exchange.webservices.data.MessageBody;
import microsoft.exchange.webservices.data.WebCredentials;
import microsoft.exchange.webservices.data.WellKnownFolderName;



public class MailEWSTest {
	 public static void main(String[] args) throws Exception
	    {
//	        	ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
//
//	        	ExchangeCredentials credentials = new WebCredentials("tle@capmkts.org","Passw0rd1");
//	        	service.setCredentials(credentials);
////	        	service.autodiscoverUrl("tle@capmkts.org");
//	        	URI uri = new URI("https://srv-mail-01.capmkts.local/ews/exchange.asmx");
	        	
	        	ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
	            ExchangeCredentials credentials = new WebCredentials("tle", "Passw0rd1");
	            service.setCredentials( credentials );    
	            service.setUrl( new URI("https://srv-mail-01.capmkts.local/ews/exchange.asmx") );
	        	System.out.println(service.getCredentials());
//	        	Folder inbox = Folder.bind(service, WellKnownFolderName.Inbox);
	        	
	        	//Send plain message
//	        	EmailMessage msg= new EmailMessage(service);
//	        	msg.setSubject("Hello world!"); 
//	        	msg.setBody(MessageBody.getMessageBodyFromText("Sent using the EWS Managed API."));
//	        	msg.getToRecipients().add("thinh.n.le@gmail.com");
//	        	msg.send();
	        	
	        	//Send message with attachment
	        	EmailMessage message = new EmailMessage(service);
	        	message.getToRecipients().add("thinh.n.le@gmail.com");
	        	message.setSubject("attachements");
	        	message.setBody(MessageBody.getMessageBodyFromText("Email attachements"));
	        	message.getAttachments().addFileAttachment("C:\\1023_cun.pdf");
	        	message.send();


	        	
	            
	        }

//    public static void main(String[] args)
//    {
//        try
//        {
//            Service service = new Service("https://srv-mail-01.capmkts.local/ews/exchange.asmx", "tle", "Passw0rd1");
//
//            Contains restriction = new Contains(MessagePropertyPath.SUBJECT, "Email Relay", ContainmentMode.PREFIXED, ContainmentComparison.IGNORE_CASE);
//
//            FindItemResponse response = service.findItem(StandardFolder.INBOX, MessagePropertyPath.getAllPropertyPaths(), restriction);
//
//            for (int i = 0; i < response.getItems().size(); i++)
//            {
//                if (response.getItems().get(i) instanceof Message)
//                {
//                    Message message = (Message) response.getItems().get(i);
//
//                    System.out.println("Subject = " + message.getSubject());
//                    System.out.println("ReceivedTime = " + message.getReceivedTime());
//
//                    if (message.getFrom() != null)
//                    {
//                        System.out.println("From = " + message.getFrom().getName());
//                    }
//
//                    System.out.println("Body Preview = " + message.getBodyPlainText());
//                    System.out.println("----------------------------------------------------------------");
//                }
//            }
//        }
//        catch (ServiceException e)
//        {
//            System.out.println(e.getMessage());
//            System.out.println(e.getXmlMessage());
//
//            e.printStackTrace();
//        }
//    }
}