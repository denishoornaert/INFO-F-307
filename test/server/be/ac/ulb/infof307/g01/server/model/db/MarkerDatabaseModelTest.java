package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.MarkerSendableModel;
import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import be.ac.ulb.infof307.g01.common.model.UserSendableModel;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.ArrayList;
import static junit.framework.TestCase.fail;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MarkerDatabaseModelTest extends AbstractDatabaseTest {
    
    private final String _markerUsername = "bidon";
    private final String _markerPokemonName = "Arceus";
    private final UserSendableModel _markerUser;
    private MarkerSendableModel _markerToInsert;
    
    public MarkerDatabaseModelTest() {
        super();
        _markerUser = new UserSendableModel(_markerUsername, "mail", "pass");
    }
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
        PokemonSendableModel arceus = _database.getPokemonByName(_markerPokemonName);
        final int id = 0;
        final double latitude = 250;
        final double longitude = 500;
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final int lifePoint = 0;
        final int attack = 0;
        final int defense = 0;
        
        _markerToInsert = new MarkerSendableModel(id, _markerUsername, arceus,
                latitude, longitude, timestamp.getTime(), 
                new ArrayList<ReputationVoteSendableModel>(), lifePoint, attack, defense);
        _database.signup(_markerUser);
    }
    
    /**
     * Test that insertMarker increments the count of markers.
     */
    @Test
    public void test_insertMarker_incrementsAmountsOfMarkers() {
        final int initialAmountOfMarkers = _database.getAllMarkers().size();
        try {
            _database.insertMarker(_markerToInsert);
        } catch (InvalidParameterException ex) {
            fail("Could not insert marker in Database");
        }
        assertEquals(initialAmountOfMarkers + 1, _database.getAllMarkers().size());
    }

    /**
     * Test that when a marker is inserted, it is returned by getAllMarkers.
     */
    @Test
    public void test_getAllMarkers_containsInsertedMarker() {
        _database.insertMarker(_markerToInsert); // TODO (Loan & Stan) may be refactored with fail up
        ArrayList<MarkerSendableModel> markers = _database.getAllMarkers();        
        assertTrue(markers.contains(_markerToInsert));
    }
    
}