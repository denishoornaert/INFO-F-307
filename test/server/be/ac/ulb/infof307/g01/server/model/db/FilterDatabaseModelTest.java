package be.ac.ulb.infof307.g01.server.model.db;

import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import java.security.InvalidParameterException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Groupe 1
 */
public class FilterDatabaseModelTest extends AbstractDatabaseTest {
    
    @Rule
    public ExpectedException _expected = ExpectedException.none();
    
    private FilterSendableModel _filterToInsert;
    private final String FILTER_NAME = "testFilter";
    
    public FilterDatabaseModelTest() {
        super();
    }
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
        _filterToInsert = new FilterSendableModel(FILTER_NAME, "ID(TYPE(TEST))");
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
        List<FilterSendableModel> listAllFilter = _database.getAllFilter();
        
        boolean find = false;
        for(FilterSendableModel filter : listAllFilter) {
            if(filter.getName().equals(_filterToInsert.getName())) {
                find = filter.getExpression().equals(_filterToInsert.getExpression());
            }
        }
        assertTrue(find);
    }
    
    /**
     * Test that when a filter is inserted with the same name that an existing 
     * filter throw an exception.
     */
    @Test
    public void test_insertFilter_insertFilterWithSameNameNotWorking() {
        _database.insertFilter(_filterToInsert);
        _expected.expect(InvalidParameterException.class);
        _database.insertFilter(new FilterSendableModel(FILTER_NAME, ""));
    }
    
}
