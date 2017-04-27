//package be.ac.ulb.infof307.g01.client.model.db;
//
//import be.ac.ulb.infof307.g01.client.model.DatabaseModel;
//import be.ac.ulb.infof307.g01.client.model.PokemonModel;
//import be.ac.ulb.infof307.g01.client.model.PokemonTypeModel;
//import static junit.framework.TestCase.assertFalse;
//import org.junit.After;
//import org.junit.Test;
//
//public class PokemonDatabaseModelTest extends AbstractDatabaseTest  {
//    
//    @After
//    public void tearDown() {
//        DatabaseModel.closeDatabase();
//        PokemonModel.clearAllPokemon();
//        PokemonTypeModel.resetAllPokemonType();
//    }
//    
//    @Test
//    public void test_loadPokemonNotEmpty(){
//        _database.loadAllPokemons();
//        int size = PokemonModel.getSizePokemonModel();
//        assertFalse(size == 0);
//        // size must be > 0
//    }
//    
//}
