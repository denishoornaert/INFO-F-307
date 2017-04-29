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
    private final String SERVER_ACCOUNT_MAIL_ADDRESS = "infof307.groupe01@gmail.com";
    private final String SERVER_ACCOUNT_PASSWORD = "I0y-9xis3En14Y4m9xP9dN3QNkD0cEAk";
    private final String SERVER_SMTP_HOST = "smtp.gmail.com";
    private final int SERVER_SMTP_PORT = 465;
    
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
    
    public EmailSender() {
        configureSmtp(SMTP_PROPERTIES);
        CURRENT_SESSION = authenticate();
    }
    
    /**
     * Configures the SMTP options.
     * @param properties The propertie object in which put the configuration
     * options.
     */
    private void configureSmtp(Properties properties) {
        // All these properties are usual for SMTP connection. We could put
        // them in variables, but the gain is marginal, and just adds more code.
        properties.put("mail.smtp.host", SERVER_SMTP_HOST);
        properties.put("mail.smtp.socketFactory.port", String.valueOf(SERVER_SMTP_PORT));
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", String.valueOf(SERVER_SMTP_PORT));
    }
    
    /**
     * Authenticates this instance with the SMTP protocol to the server mail
     * account. Make sure that configureSmtp has been previously called.
     * @return The session object representing the authentication.
     */
    private Session authenticate() {
        return Session.getDefaultInstance(SMTP_PROPERTIES, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SERVER_ACCOUNT_MAIL_ADDRESS, SERVER_ACCOUNT_PASSWORD);
            }
        });
    }
    
    /**
     * Sends a mail to the user in order to validate its account.
     * For now, the validation process is just a password sent into the mail.
     * @param userMailAddress The email address to which send the mail
     * @param userPassword The generated password for the user account
     * @throws MessagingException If the mail couldn't be sent.
     */
    public void sendConfirmationMail(String userMailAddress, String userPassword) throws MessagingException {
        Message message = new MimeMessage(CURRENT_SESSION);
        message.setFrom(new InternetAddress(SERVER_ACCOUNT_MAIL_ADDRESS));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userMailAddress));
        message.setSubject(CONFIRMATION_MAIL_TITLE);
        message.setText(String.format(CONFIRMATION_MAIL_CONTENT, userPassword));
        Transport.send(message);
    }

    public String getServerAccountMailAddress() {
        return SERVER_ACCOUNT_MAIL_ADDRESS;
    }

    public String getServerAccountPassword() {
        return SERVER_ACCOUNT_PASSWORD;
    }

    public String getConfirmationMailTitle() {
        return CONFIRMATION_MAIL_TITLE;
    }
}
