package be.ac.ulb.infof307.g01.server.controller;

import org.junit.After;
import org.junit.Test;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class EmailSenderTest {
    EmailSender _emailSender = new EmailSender();
    private String _testAccountEmailAddress;
    private String _testAccountPassword;
    private final String TEST_IMAP_HOST = "imap.gmail.com";
    Session _session;
    Store _store;
    Folder _mailBox;
    final int MAIL_RECEIPTION_TIMEOUT = 2000;
    
    @Before
    public void setUp() {
        // We also use the server mail account as test mail box
        _testAccountEmailAddress = _emailSender.getServerAccountMailAddress();
        _testAccountPassword = _emailSender.getServerAccountPassword();
    }
    
    private void openMailbox() throws MessagingException {
        // Connect to the server
        _session = Session.getInstance(new Properties(), null);
        _store = _session.getStore("imap");
        _store.connect(TEST_IMAP_HOST, _testAccountEmailAddress, _testAccountPassword);
        
        // Open the inbox folder
        _mailBox = _store.getFolder("INBOX");
        _mailBox.open(Folder.READ_ONLY);
    }
    
    @After
    public void tearDown() throws MessagingException {
        _mailBox.close(true);
        _store.close();
    }

    @Test
    public void test_sendConfirmationMailMailIsReceived() throws MessagingException, InterruptedException {
        // The email sender requires the generated user password, it can be any string
        final String generatedPassword = "abcdef";
        _emailSender.sendConfirmationMail(_testAccountEmailAddress, generatedPassword);
        
        // Wait a little bit to let the mail make his way through the internet
        Thread.sleep(MAIL_RECEIPTION_TIMEOUT);
        
        openMailbox();
        
        // We search for a mail that was sent by the server
        final SearchTerm senderSearchTerm = new FromTerm(new InternetAddress(_emailSender.getServerAccountMailAddress()));
        // And that is not yet read
        final SearchTerm notReadSearchTerm = new FlagTerm(new Flags(Flag.SEEN), false);
        // And that has the title of a confirmation mail
        final SearchTerm titleSearchTerm = new SubjectTerm(_emailSender.getConfirmationMailTitle());
        // Put all the requirements together
        final SearchTerm searchTerms = new AndTerm(new SearchTerm[]{senderSearchTerm, notReadSearchTerm, titleSearchTerm});
        
        Message messages[] = _mailBox.search(searchTerms);
        
        assertEquals(messages.length, 1);
        // Delete the message, so that we can re-run the test
        messages[0].setFlag(Flags.Flag.DELETED, true);
    }
}