package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.client.controller.UserController;
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the interaction with the User
 *
 */

public class UserControllerTest {
    
    private final String PASSWORD = "12345";
    private final String USERNAME = "GLGP";
    private final String EMAIL = "GLGP@ulb.ac.be";
    private final boolean TERMS = true;
    
    @Before
    public void setUp() {
        UserController.getInstance().register(USERNAME, EMAIL, PASSWORD, TERMS);
    }
    
    @Test
    public void test_getUsername() {
        assertEquals(UserController.getInstance().getUsername(), USERNAME);
    }
    
    @Test
    public void test_getEmail() {
        assertEquals(UserController.getInstance().getEmail(), EMAIL);
    }
    
    @Test
    public void test_getPassword() {
        assertEquals(UserController.getInstance().getPassword(), PASSWORD);
    }

}