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
     * The content of the confirmation mail. For now, we just send the password
     * to the user. To insert the password in the mail, use String.format.
     */
    private final String CONFIRMATION_MAIL_CONTENT = "Hello dear user,\n"
            + "You will be able to connect to Gotta Map Them All after having"
            + " confirmed your account by clicking on this link : "
            + "http://localhost:8080/server/rest/query/user/confirm?token=%s";
    
    public EmailSender() {
        SMTP_SESSION = createSmtpSession();
    }
    
    /**
     * Creates a session suitable to send mails with the SMTP protocol.
     */
    private Session createSmtpSession() {
        Properties smtpProperties = new Properties();
        smtpProperties.put("mail.smtp.host", SERVER_SMTP_HOST);
        smtpProperties.put("mail.smtp.socketFactory.port", String.valueOf(SERVER_SMTP_PORT));
        smtpProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        smtpProperties.put("mail.smtp.auth", "true");
        smtpProperties.put("mail.smtp.port", String.valueOf(SERVER_SMTP_PORT));
        return Session.getInstance(smtpProperties, null);
    }
    
    /**
     * Sends a mail to the user in order to validate its account.
     * For now, the validation process is just a password sent into the mail.
     * @param userMailAddress The email address to which send the mail
     * @param userPassword The generated password for the user account
     * @throws MessagingException If the mail couldn't be sent.
     */
    public void sendConfirmationEmail(String userMailAddress, String token) throws MessagingException {
        Message message = new MimeMessage(SMTP_SESSION);
        message.setFrom(new InternetAddress(SERVER_MAIL_ADDRESS));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userMailAddress));
        message.setSubject(CONFIRMATION_MAIL_TITLE);
        message.setText(String.format(CONFIRMATION_MAIL_CONTENT, token));
        Transport.send(message, SERVER_MAIL_ADDRESS, SERVER_MAIL_PASSWORD);
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
