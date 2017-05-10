/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.common.model;

import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * @author davidabraham
 */
public class MarkerSendableModelTest {
    private static final String USER_NAME = "Test";
    private static final int DATABASE_ID = 3;
    private static final PokemonSendableModel POKEMON = new PokemonSendableModel();
    private static final CoordinateSendableModel COORDINATE = new CoordinateSendableModel();
    private static final Long TIMESTAMP = System.currentTimeMillis();
    private static final ArrayList<ReputationVoteSendableModel> REPUTATION = new ArrayList<>();
    private static final int LIFEPOINT = 9;
    private static final int ATTACK = 10;
    private static final int DEFENSE = 6;
    @Test
    public void test_MakerSendableModelEquals() {
        MarkerSendableModel original = new MarkerSendableModel();
        MarkerSendableModel copy = new MarkerSendableModel(original);
        assertEquals(original, copy);
    }
}
