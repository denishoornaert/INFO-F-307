/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.PokemonTypeModel;
import be.ac.ulb.infof307.g01.db.DatabaseModel;
import be.ac.ulb.infof307.g01.db.PokemonTypeDatabaseModel;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author remy
 */
public class PokemonTypeDatabaseModelTest extends TestCase {
    
    private PokemonTypeDatabaseModel _database;
    
    public PokemonTypeDatabaseModelTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        _database = (PokemonTypeDatabaseModel) new DatabaseModel(Main.getTestDatabasePath());
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void test_getAllPokemonTypesDefaultValue() {
        ArrayList<PokemonTypeModel> allPokemonTypes = PokemonTypeModel.getAllPokemonTypes();
        assertEquals(allPokemonTypes.size(), 1);
        assertEquals(allPokemonTypes.get(0).getTypeName(), "NONE");
    }
    
}
