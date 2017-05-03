/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Groupe01
 */
public class UserDatabaseModelTest extends AbstractDatabaseTest {
    private static UserSendableModel _user;
    private int _index;
    
    public UserDatabaseModelTest() {
        _index = 0; // Arbitrary
    }
    
    private boolean insertCorrectUser(UserSendableModel user){
        boolean test = _database.signup(user, "42");
        if (test) test = _database.confirmAccount("42");
        return test;
    }
    
    @BeforeClass
    public static void setUpClass(){
        _user = new UserSendableModel("Test","test.bidon@testbidon.com","Bidon");
    }
    
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @After
    /**
     * Change username and email.
     * Avoid conflicts between two test users.
     */
    public void tearDown() {
        super.tearDown();
        _index += 1;
        _user.setUsername(_user.getUsername()+_index);
        _user.setEmail(_user.getEmail()+_index);
    }
    
    @Test
    public void test_signinUserNotInDatabase(){
        assertFalse(_database.signin(_user));
    }
    
    @Test
    public void test_signinUserInDatabase(){
        insertCorrectUser(_user);
        assertTrue(_database.signin(_user));
    }
    
    @Test
    public void test_signinWithTokenNotConfirmed(){
        _database.signup(_user, "42");
        assertFalse(_database.signin(_user));
    }
    
    @Test
    public void test_signupCorrectCreation(){
        assertTrue(_database.signup(_user, "42"));
    }
    
    @Test
    public void test_signupNotCorrectBecauseSameUsername(){
        insertCorrectUser(_user);
        _user.setEmail(_user.getEmail()+"hello");
        _user.setPassword(_user.getPassword()+"hello");
        assertFalse(insertCorrectUser(_user));
    }
    
    @Test
    public void test_signupNotCorrectBecauseSameEmail(){
        insertCorrectUser(_user);
        _user.setUsername(_user.getUsername()+"hello");
        _user.setPassword(_user.getPassword()+"hello");
        assertFalse(insertCorrectUser(_user));
    }
    
    @Test
    public void test_signupNotCorrectBecauseNullValues(){
        _user.setUsername(null);
        _user.setPassword(null);
        _user.setEmail(null);
        assertFalse(insertCorrectUser(_user));
    }
    
}
