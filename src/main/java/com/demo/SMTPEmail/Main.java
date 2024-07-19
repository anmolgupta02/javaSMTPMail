package com.demo.SMTPEmail;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.email.EmailPopulatingBuilder;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class Main{
	
	
	public static final String Email_From = "anmol.gupta@rsk-bsl.com";
	public static final String EMAIL_SECRET = "351ce9a2eac430ca77b9b8f71a03f74968bd4470a8bf74c45eca1e17a955ef48";
    public static final String EMAIL_SALT = "66cc:3e32:c7e8:550c:c317:07a1:60e5:afc5";
    public static final String EMAIL_TOKEN = "S5CjZCrvRxqR3uIrVyhYIw==";
    public static final String SMTP_HOST = "smtp.office365.com";
    public static final int SMTP_PORT = 587;
    public static final String Email_to="rashamdeep.singh@rsk-bsl.com,anmolgupta02@gmail.com";
    public static final String Email_cc="rashamdeep.singh@rsk-bsl.com,anmolgupta02@gmail.com";
    
    String emailSubject="Sending this from a Java Code Program";
    String emailBody="<h1>This is a sample email sent from a java program.</h1><h2>To and CC TEST</h2>";
    
    
	AesDesDemo aesHelper = new AesDesDemo();
	
	
	public void sendEmail() {
		EmailPopulatingBuilder emailPopulatingBuilder = EmailBuilder.startingBlank();
		
		emailPopulatingBuilder.from(Email_From);
		emailPopulatingBuilder.to(Email_to);
		emailPopulatingBuilder.withSubject(emailSubject);
		emailPopulatingBuilder.withHTMLText(emailBody);
		emailPopulatingBuilder.cc(Email_cc);
		
		Email emailtoSend = emailPopulatingBuilder.buildEmail();
		
		String password = AesDesDemo.decrypt(EMAIL_TOKEN);
		
		Mailer mailer = MailerBuilder.withSMTPServer(SMTP_HOST, SMTP_PORT,Email_From,password)
				.withTransportStrategy(TransportStrategy.SMTP_TLS).withDebugLogging(false).buildMailer();
		
		mailer.sendMail(emailtoSend);
		System.out.println("Mail Sent Successfully!!");
		
	}
	
	
	public static void main(String args[]) {
		
	
		
		Main obj = new Main();
		obj.sendEmail();
		
		
		
		
	}
	
	
}

