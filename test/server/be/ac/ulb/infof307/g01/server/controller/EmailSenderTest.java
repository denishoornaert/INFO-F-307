package be.ac.ulb.infof307.g01.server.controller;

import java.util.Properties;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class EmailSenderTest {
    
    private final EmailSender _emailSender = new EmailSender();
    private String _testAccountEmailAddress;
    private String _testAccountPassword;
    private final String TEST_IMAP_HOST = "imap.gmail.com";
    private final int TEST_IMAP_PORT = 993;
    private final long MAIL_RECEIPTION_TIMEOUT = 2000;
    private Store _store;
    private Folder _mailBox;
    
    @Before
    public void setUp() throws NoSuchProviderException, MessagingException {
        // We also use the server mail account as test mail box
        _testAccountEmailAddress = _emailSender.getServerAccountMailAddress();
        _testAccountPassword = _emailSender.getServerAccountPassword();
        
        // Connect to the server
        Session imapSession = createImapSession();
        _store = imapSession.getStore("imap");
        _store.connect(TEST_IMAP_HOST, _testAccountEmailAddress, _testAccountPassword);
        
        // Get the inbox folder, but do not open it yet. The test will have to
        // open it, in order for the received mail to be properly seen.
        _mailBox = _store.getFolder("INBOX");
    }
    
    private Session createImapSession() {
        Properties imapProperties = new Properties();
	imapProperties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	imapProperties.setProperty("mail.imap.socketFactory.fallback", "false");
	imapProperties.setProperty("mail.imap.socketFactory.port", String.valueOf(TEST_IMAP_PORT));
        return Session.getInstance(imapProperties, null);
    }
    
    @After
    public void tearDown() throws MessagingException {
        // Close the mail box after each test, to refresh the message list
        _mailBox.close(true);
        _store.close();
    }

    @Test
    public void test_sendConfirmationMailMailIsReceived() throws MessagingException, InterruptedException {
        // The email sender requires the generated user password, it can be any string
        final String generatedPassword = "abcdef";
        _emailSender.sendConfirmationEmail(_testAccountEmailAddress, generatedPassword);
        
        // Wait a little bit to let the mail make his way through the internet
        Thread.sleep(MAIL_RECEIPTION_TIMEOUT);
        
        // Open the mail box. We cannot do that in setUp, since the mail is
        // received in the test itself
        _mailBox.open(Folder.READ_WRITE);
        
        // We search for a mail that was sent by the server
        final SearchTerm senderSearchTerm = new FromTerm(new InternetAddress(_emailSender.getServerAccountMailAddress()));
        // And that is not yet read
        final SearchTerm notReadSearchTerm = new FlagTerm(new Flags(Flag.SEEN), false);
        // And that has the title of a confirmation mail
        final SearchTerm titleSearchTerm = new SubjectTerm(_emailSender.getConfirmationMailTitle());
        // Put all the requirements together
        final SearchTerm searchTerms = new AndTerm(new SearchTerm[]{senderSearchTerm, notReadSearchTerm, titleSearchTerm});
        
        Message messages[] = _mailBox.search(searchTerms);
        
        int nbrReceive = messages.length;
        
        // Delete the message, so that we can re-run the test
        for(Message msg : messages) {
            msg.setFlag(Flags.Flag.DELETED, true);
        }
        
        assertEquals(1, nbrReceive);
    }
}