package be.ac.ulb.infof307.g01.server.controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is responsible of sending mails to the users, such as registering
 * confirmation mail.
 */
public class EmailSender {
    
    private final String SERVER_MAIL_ADDRESS = "infof307.groupe01@gmail.com";
    // We could make this more secure, later.
    private final String SERVER_MAIL_PASSWORD = "I0y-9xis3En14Y4m9xP9dN3QNkD0cEAk";
    private final String SERVER_SMTP_HOST = "smtp.gmail.com";
    private final int SERVER_SMTP_PORT = 465;
    
    /**
     * A Session object holds the configuration used to send mails.
     */
    private final Session SMTP_SESSION;
    
    private final String CONFIRMATION_MAIL_TITLE = "Welcome to Gotta Map Them All!";
    
    /**
     * The content of the confirmation mail. You can insert the token and the
     * username into the mail content by using String.format.
     */
    private final String CONFIRMATION_MAIL_CONTENT = "Hello dear user,\n"
            + "You will be able to connect to Gotta Map Them All after having"
            + " confirmed your account by clicking on this link : "
            + "http://localhost:8080/server/rest/user/confirm?username=%s&token=%s";
    
    public EmailSender() {
        SMTP_SESSION = createSmtpSession();
    }
    
    /**
     * Creates a session suitable to send mails with the SMTP protocol.
     */
    private Session createSmtpSession() {
        Properties smtpProperties = new Properties();
        smtpProperties.put("mail.smtp.host", SERVER_SMTP_HOST);
        smtpProperties.put("mail.smtp.auth", "true");
        smtpProperties.put("mail.smtp.port", String.valueOf(SERVER_SMTP_PORT));
        smtpProperties.put("mail.smtp.user", SERVER_MAIL_ADDRESS);
        smtpProperties.put("mail.smtp.starttls.enable","true");
        smtpProperties.put("mail.smtp.socketFactory.port", String.valueOf(SERVER_SMTP_PORT));
        smtpProperties.put("mail.smtp.socketFactory.fallback", "false");
        smtpProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return Session.getInstance(smtpProperties, null);
    }
    
    /**
     * Sends a mail to the user in order to validate its account.
     * For now, the validation process is just a password sent into the mail.
     * @param userMailAddress The email address to which send the mail
     * @param username The username.
     * @param token The confirmation token.
     * @throws MessagingException If the mail couldn't be sent.
     */
    public void sendConfirmationEmail(String userMailAddress, String username, String token) throws MessagingException {
        // Create the message
        Message message = new MimeMessage(SMTP_SESSION);
        message.setFrom(new InternetAddress(SERVER_MAIL_ADDRESS));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userMailAddress));
        message.setSubject(CONFIRMATION_MAIL_TITLE);
        message.setText(String.format(CONFIRMATION_MAIL_CONTENT, username, token));
        
        // Instanciates a connection used to send the message
        Transport transport = SMTP_SESSION.getTransport("smtps");
        transport.connect(SERVER_SMTP_HOST, SERVER_SMTP_PORT, SERVER_MAIL_ADDRESS, SERVER_MAIL_PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public String getServerAccountMailAddress() {
        return SERVER_MAIL_ADDRESS;
    }

    public String getServerAccountPassword() {
        return SERVER_MAIL_PASSWORD;
    }

    public String getConfirmationMailTitle() {
        return CONFIRMATION_MAIL_TITLE;
    }
}
