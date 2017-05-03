package be.ac.ulb.infof307.g01.common.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test the interaction with the User
 *
 */

public class UserSendableModelTest {
    
    private final String PASSWORD = "12345";
    private final String USERNAME = "GLGP";
    private final String EMAIL = "GLGP@ulb.ac.be";
    private UserSendableModel _userSendable;
    
    @Before
    public void setUp() {
        _userSendable = new UserSendableModel(USERNAME, EMAIL, PASSWORD);
    }
    
    @Test
    public void test_getUsername() {
        assertEquals(_userSendable.getUsername(), USERNAME);
    }
    
    @Test
    public void test_getEmail() {
        assertEquals(_userSendable.getEmail(), EMAIL);
    }
    
    @Test
    public void test_getPassword() {
        String cryptPassword = _userSendable.cryptPassword(PASSWORD);
        assertEquals(_userSendable.getPassword(), cryptPassword);
        assertNotEquals(_userSendable.getPassword(), PASSWORD);
    }

}