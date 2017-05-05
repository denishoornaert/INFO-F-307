/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import java.security.InvalidParameterException;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Groupe01
 */
public class UserDatabaseModelTest extends AbstractDatabaseTest {
    
    @Rule
    public ExpectedException _expected = ExpectedException.none();
    
    private final UserSendableModel _user;
    private final String _token;
    
    public UserDatabaseModelTest() {
        super();
        _token = "42"; // arbitrary
        _user = new UserSendableModel("name", "mail", "pass");
    }
    
    private boolean insertCorrectUser(UserSendableModel user){
        boolean test = _database.signup(user, _token);
        if (test) test = _database.confirmAccount(_token);
        return test;
    }
    
    private UserSendableModel newUser(String subStr) {
        return new UserSendableModel(_user.getUsername()+subStr, _user.getEmail()+subStr, _user.getPassword()+subStr);
    }
    
    /**
     * Change username and email.
     * Make sure sure that there won't be two same account in the database.
     * Avoid conflicts between two test users.
     */
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @After
    @Override
    public void tearDown() {
        super.tearDown();
    }
    
    @Test
    public void test_signinUserNotInDatabase(){
        UserSendableModel user = newUser("6");
        _expected.expect(InvalidParameterException.class);
        _database.signin(user);
    }
    
    @Test
    public void test_signinUserInDatabase(){
        UserSendableModel user = newUser("5");
        insertCorrectUser(user);
        _database.signin(user);
    }
    
    @Test
    public void test_signinWithTokenNotConfirmed(){
        UserSendableModel user = newUser("4");
        _database.signup(user, _token);
        _expected.expect(InvalidParameterException.class);
        _database.signin(user);
    }
    
    @Test
    public void test_signupCorrectCreation(){
        UserSendableModel user = newUser("3");
        assertTrue(_database.signup(user, _token));
    }
    
    @Test
    public void test_signupNotCorrectBecauseSameUsername(){
        UserSendableModel user = newUser("");
        insertCorrectUser(user);
        user.setEmail(user.getEmail()+"2");
        user.setPassword(user.getPassword()+"2");
        assertFalse(insertCorrectUser(user));
    }
    
    @Test
    public void test_signupNotCorrectBecauseSameEmail(){
        UserSendableModel user = newUser("");
        insertCorrectUser(user);
        user.setUsername(user.getUsername()+"1");
        user.setPassword(user.getPassword()+"1");
        assertFalse(insertCorrectUser(user));
    }
    
    @Test
    public void test_signupNotCorrectBecauseNullValues(){
        UserSendableModel user = newUser("6");
        user.setUsername(null);
        user.setPassword(null);
        user.setEmail(null);
        assertFalse(insertCorrectUser(user));
    }
    
}
