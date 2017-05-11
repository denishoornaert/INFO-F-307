package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 *
 * @author davidabraham
 */
public class PokemonCacheTest {
    private static final PokemonCache POKEMON_CACHE = PokemonCache.getInstance();
    private static final PokemonTypeSendableModel POKEMON_TYPE_SENDABLE_TEST = new PokemonTypeSendableModel("FIRE");
    private static final PokemonSendableModel POKEMON_SENDABLE_TEST = new PokemonSendableModel("Arceus","/assets/client/sprites/arceus.png", 
            POKEMON_TYPE_SENDABLE_TEST);
     
    private static void loadCachePokemonSendableTest(){
        List<PokemonSendableModel> pokemonList = new ArrayList<>();
        pokemonList.add(POKEMON_SENDABLE_TEST);
        POKEMON_CACHE.loadAllPokemons(pokemonList);
    }
    
    private static void loadCachePokemonTypeSendableTest(){
        List<PokemonTypeSendableModel> PokemonList = new ArrayList<>();
        PokemonList.add(POKEMON_TYPE_SENDABLE_TEST);
        POKEMON_CACHE.loadAllPokemonTypes(PokemonList);
    }
    
    @Test
    public void loadAllPokemonsTest(){
        loadCachePokemonSendableTest();
        assertEquals(POKEMON_CACHE.getNumberLoadedPokemons(),1);// load 1 pokemon in the cache by function loadCachePokemonSendableTest
        assertTrue(POKEMON_CACHE.getAllPokemonNames().contains("Arceus"));
    }
    
    @Test
    public void getPokemonByNameTest(){
        loadCachePokemonSendableTest();
        PokemonModel pokemonModelTest = new PokemonModel(POKEMON_SENDABLE_TEST);
        assertEquals(POKEMON_CACHE.getPokemonByName("Arceus"),pokemonModelTest);
        
        
    }
    
    @Test
    public void loadAllPokemonTypesTest(){
        loadCachePokemonTypeSendableTest();
        PokemonTypeModel pokemonTypeModelTest = new PokemonTypeModel(POKEMON_TYPE_SENDABLE_TEST);
        assertEquals(POKEMON_CACHE.getAllPokemonTypes().size(),1);// load 1 Type with the function loadCachePokemonTypeSendableTest
        assertEquals(POKEMON_CACHE.getAllPokemonTypes().get(0).getTypeName(),POKEMON_TYPE_SENDABLE_TEST.getTypeName());
    }
    
    @Test
    public void getPokemonTypeByTypeNameTest(){
        loadCachePokemonTypeSendableTest();
        PokemonTypeModel pokemonTypeModelTest = new PokemonTypeModel(POKEMON_TYPE_SENDABLE_TEST);
        assertEquals(POKEMON_CACHE.getPokemonTypeByTypeName("FIRE").getTypeName(),pokemonTypeModelTest.getTypeName());
    }
}
