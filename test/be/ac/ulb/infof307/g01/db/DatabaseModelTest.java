package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.db.DatabaseModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DatabaseModelTest {
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @Test
    public void test_connectionThrowsFileNotFoundIfFileDoesNotExist()
            throws SQLException, FileNotFoundException {
        expected.expect(FileNotFoundException.class);
        new DatabaseModel("non-existing-file.db");
    }
    
    @Test
    public void test_connectionIsSuccessful() throws SQLException, FileNotFoundException {
        // no exception should be thrown
        DatabaseModel database = new DatabaseModel(Main.getTestDatabasePath());
        database.close();
    }
    
    @Test
    public void test_severalConnectionsToDatabaseThrowIllegalStateException()
            throws SQLException, FileNotFoundException {
        new DatabaseModel(Main.getTestDatabasePath());
        expected.expect(IllegalStateException.class);
        new DatabaseModel(Main.getTestDatabasePath());
    }
    
}
