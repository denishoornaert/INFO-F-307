package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.server.model.DatabaseModel;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import static org.junit.Assert.fail;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DatabaseModelTest extends AbstractDatabaseTest{
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @Test
    public void test_getInstance_connectionIsSuccessful() throws SQLException, FileNotFoundException {
        // no exception should be thrown
        try {
            DatabaseModel.getInstance();
        } catch(IllegalAccessError ex) {
            fail("Database could not be load (" + ex.getMessage() + ")");
        }
    }
}
