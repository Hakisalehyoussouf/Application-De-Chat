package ma.ensate.client.utils;


import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.*;


public class generer {
    

	 public int verify(String email) {
		 
		 
	        Scanner sc = new Scanner(System.in);
	        Random rand = new Random();
	        int num = rand.nextInt(1000) + 1;


	        // Sender's email address and password
	        String senderEmail = "soukaina.mzabi@etu.uae.ac.ma";
	        String senderPassword = "soukaina2002";

	        // Recipient's email address
	        String recipientEmail = email;
	                //String recipientEmail = "mzabisoukaina941@gmail.com";



	        // Email properties
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");



	        // Create a session with the email server
	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, senderPassword);
	            }
	        });


	        try {
	            // Create a message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	            message.setSubject("Certificate");
	            
	            MimeMultipart multipart = new MimeMultipart();
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText("Please find the attached certificate.");

	            multipart.addBodyPart(messageBodyPart);
	            
	            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
	            attachmentBodyPart.attachFile("C:\\Users\\hp\\Desktop\\Chat_App\\src\\lacalkey.cer");
	            
	            multipart.addBodyPart(attachmentBodyPart);
	            
	            message.setContent(multipart);
	            System.out.println("yyyyyyy");
	            
	            Transport.send(message);
	            

	            // Send the message
	          

	            System.out.println("Email sent successfully.");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return num;


	    }
         
}