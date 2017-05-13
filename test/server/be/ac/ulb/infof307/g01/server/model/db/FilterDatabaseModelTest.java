package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import java.security.InvalidParameterException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Groupe 1
 */
public class FilterDatabaseModelTest extends AbstractDatabaseTest {
    
    private FilterSendableModel _filterToInsert;
    
    public FilterDatabaseModelTest() {
        super();
    }
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
        _filterToInsert = new FilterSendableModel("testFilter", "ID(TYPE(TEST))");
    }
    
    /**
     * Test that insertFilter increments the count of filter.
     */
    @Test
    public void test_insertFilter_incrementsAmountsOfFilter() {
        final int initialAmountOfFilter = _database.getAllFilter().size();
        try {
            _database.insertFilter(_filterToInsert);
        } catch (InvalidParameterException ex) {
            fail("Could not insert filter in Database");
        }
        assertEquals(initialAmountOfFilter + 1, _database.getAllFilter().size());
    }

    /**
     * Test that when a filter is inserted, it is returned by getAllFilter.
     */
    @Test
    public void test_getAllFilter_containsInsertedFilter() {
        _database.insertFilter(_filterToInsert);
        List<FilterSendableModel> filter = _database.getAllFilter();
        assertTrue(filter.contains(_filterToInsert));
    }
}
