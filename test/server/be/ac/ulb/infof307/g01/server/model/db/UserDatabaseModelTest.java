package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import java.security.InvalidParameterException;
import org.junit.After;
import static org.junit.Assert.assertFalse;
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
        try {
            _database.signup(user);
            _database.addTokenToUser(user, _token);
            _database.confirmAccount(user.getUsername(), _token);
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }
    
    private UserSendableModel newUser(String subStr) {
        return new UserSendableModel(_user.getUsername() + subStr,
                _user.getEmail() + subStr, _user.getPassword() + subStr);
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
    public void test_signin_userNotInDatabase(){
        UserSendableModel user = newUser("6");
        _expected.expect(InvalidParameterException.class);
        _database.signin(user);
    }
    
    @Test
    public void test_signin_userInDatabase(){
        UserSendableModel user = newUser("5");
        insertCorrectUser(user);
        _database.signin(user);
    }
    
    @Test
    public void test_signin_withTokenNotConfirmed(){
        UserSendableModel user = newUser("4");
        _database.signup(user);
        _database.addTokenToUser(user, _token);
        _expected.expect(InvalidParameterException.class);
        _database.signin(user);
    }
    
    @Test
    public void test_signup_correctCreation(){
        UserSendableModel user = newUser("3");
        _database.signup(user);
        _database.addTokenToUser(user, _token);
    }
    
    @Test
    public void test_signup_notCorrectBecauseSameUsername(){
        UserSendableModel user = newUser("");
        insertCorrectUser(user);
        user.setEmail(user.getEmail()+"2");
        user.setPassword(user.getPassword()+"2");
        assertFalse(insertCorrectUser(user));
    }
    
    @Test
    public void test_signup_notCorrectBecauseSameEmail(){
        UserSendableModel user = newUser("");
        insertCorrectUser(user);
        user.setUsername(user.getUsername()+"1");
        user.setPassword(user.getPassword()+"1");
        assertFalse(insertCorrectUser(user));
    }
    
    @Test
    public void test_signup_notCorrectBecauseNullValues(){
        UserSendableModel user = newUser("6");
        user.setUsername(null);
        user.setPassword(null);
        user.setEmail(null);
        assertFalse(insertCorrectUser(user));
    }
    
}
