/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.PokemonListModel;
import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hoornaert
 */
public class PokemonListModelTest extends TestCase {
    
    public PokemonListModelTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void test_findPokemonFromPattern() {
        PokemonListModel tmp = new PokemonListModel("ami", "amitié", "bien", "bienvenue", "Jean");
        assertArrayEquals(tmp.findPokemonFromPattern("ami"),new String[]{"ami", "amitié"});
    }
}
