/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.db.DatabaseModel;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author remy
 */
public class DatabaseModelTest {
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    public DatabaseModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

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
