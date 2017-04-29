package be.ac.ulb.infof307.g01.server.controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is responsible of sending mails to the users, such as registering
 * confirmation mail.
 */
public class EmailSender {

    /**
     * The mail address of the GMail account used to send mails.
     */
    private final String SERVER_ACCOUNT_MAIL_ADDRESS = "infof307.groupe01@gmail.com";

    /**
     * The password of the GMail account used to send mails.
     */
    private final String SERVER_ACCOUNT_PASSWORD = "I0y-9xis3En14Y4m9xP9dN3QNkD0cEAk";
    
    /**
     * Configuration needed to connect with SMTP protocol.
     */
    private final Properties SMTP_PROPERTIES = new Properties();
    
    /**
     * An authentication session, while this object is alive we don't need to
     * authenticate again.
     */
    private final Session CURRENT_SESSION;
    
    private final String CONFIRMATION_MAIL_TITLE = "Welcome to Gotta Map Them All!";
    
    /**
     * The content of the confirmation mail. For now, we just send the password
     * to the user. To insert the password in the mail, use String.format.
     */
    private final String CONFIRMATION_MAIL_CONTENT = "Hello dear user,\n"
            + "You can now connect to Gotta Map Them All with the following password:\n"
            + "%s\n";
    
    /**
     * Instance of the singleton.
     */
    private static EmailSender _instance = null;
    
    private EmailSender() {
        // All these properties are usual for SMTP connection. We could put
        // them in variables, but the gain is marginal, and just adds more code.
        SMTP_PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
        SMTP_PROPERTIES.put("mail.smtp.socketFactory.port", "465");
        SMTP_PROPERTIES.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        SMTP_PROPERTIES.put("mail.smtp.auth", "true");
        SMTP_PROPERTIES.put("mail.smtp.port", "465");
        
        CURRENT_SESSION = Session.getDefaultInstance(SMTP_PROPERTIES, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SERVER_ACCOUNT_MAIL_ADDRESS, SERVER_ACCOUNT_PASSWORD);
            }
        });
    }
        
    public static EmailSender getInstance() {
        if(_instance == null) {
            _instance = new EmailSender();
        }
        return _instance;
    }
    
    public void sendMessage(String userMailAddress, String userPassword) throws MessagingException {
        Message message = new MimeMessage(CURRENT_SESSION);
        message.setFrom(new InternetAddress(SERVER_ACCOUNT_MAIL_ADDRESS));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userMailAddress));
        message.setSubject(CONFIRMATION_MAIL_TITLE);
        message.setText(String.format(CONFIRMATION_MAIL_CONTENT, userPassword));
        Transport.send(message);
    }
}
