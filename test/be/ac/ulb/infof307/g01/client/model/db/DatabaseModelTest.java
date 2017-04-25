package be.ac.ulb.infof307.g01.client.model.db;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.model.Configuration;
import be.ac.ulb.infof307.g01.client.model.DatabaseModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DatabaseModelTest {
    
    @AfterClass
    public static void tearDownClass() {
        DatabaseModel.closeDatabase();
        PokemonModel.clearAllPokemon();
        PokemonTypeModel.resetAllPokemonType();
    }
    
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
        DatabaseModel database = new DatabaseModel(Configuration.getInstance().getTestDataBasePath());
        database.close();
    }
    
    @Test
    public void test_severalConnectionsToDatabaseThrowIllegalStateException()
            throws SQLException, FileNotFoundException {
        new DatabaseModel(Configuration.getInstance().getTestDataBasePath());
        expected.expect(IllegalStateException.class);
        new DatabaseModel(Configuration.getInstance().getTestDataBasePath());
    }
    
}
