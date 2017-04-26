package be.ac.ulb.infof307.g01.server.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.InputStreamReader;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmailSender {
    /** The mail address of the GMail account used to send mails. */
    private static final String SERVER_ACCOUNT_MAIL_ADDRESS = "infof307.groupe01@gmail.com";
    
    /** The password of the GMail account used to send mails. */
    private static final String SERVER_ACCOUNT_PASSWORD = "I0y-9xis3En14Y4m9xP9dN3QNkD0cEAk";
    
    /** Application name. */
    private static final String APPLICATION_NAME = "Gotta Map Them All";
    
    /** Path to the secret key resource, used for authentication. */
    private static final String SECRET_KEY_PATH = "/APIs/GMailApiKey.json";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/gotta-map-them-all");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private final FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private final HttpTransport HTTP_TRANSPORT;
    
    private final Gmail GMAIL_SERVICE;
    
    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS);
    
    private static EmailSender _instance = null;

    private EmailSender() throws GeneralSecurityException, IOException {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        GMAIL_SERVICE = getGmailService();
    }
    
    static public EmailSender getInstance() throws GeneralSecurityException, IOException {
        if(_instance == null) {
            _instance = new EmailSender();
        }
        return _instance;
    }

    /**
     * Builds and returns an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    private Gmail getGmailService() throws IOException {
        Credential credential = authorize();
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    private Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in = EmailSender.class.getResourceAsStream(SECRET_KEY_PATH);
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }
    
    /**
     * Sends an email from the user's mailbox to its recipient.
     *
     * @param userId User's email address. The special value "me"
     * can be used to indicate the authenticated user.
     * @param email Email to be sent.
     * @throws java.io.IOException
     */
    public void sendSignUpMessage(String userId, String email) throws IOException {
      Message message = createMessageFromEmailString(email);
      GMAIL_SERVICE.users().messages().send(userId, message).execute();
    }

    /**
     * Create a Message from an email
     *
     * @param email Email to be set to raw of message
     * @return Message containing base64url encoded email.
     */
    private static Message createMessageFromEmailString(String email) {
      String encodedEmail = Base64.encodeBase64URLSafeString(email.getBytes());
      Message message = new Message();
      message.setRaw(encodedEmail);
      return message;
    }
    
    public static void main(String[] args) {
        try {
            EmailSender.getInstance().sendSignUpMessage("theo.verhelst@gmail.com", "Coucou Théo !");
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
