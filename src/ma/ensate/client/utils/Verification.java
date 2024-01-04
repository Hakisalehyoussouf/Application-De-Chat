package ma.ensate.client.utils;

import javax.mail.*;
import java.util.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

public class Verification {
	
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
	            message.setSubject("Random Number");
	            message.setText("Your random number is: " + num);
	            System.out.println("nn");

	            // Send the message
	            Transport.send(message);

	            System.out.println("Email sent successfully.");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return num;
	    }

}
