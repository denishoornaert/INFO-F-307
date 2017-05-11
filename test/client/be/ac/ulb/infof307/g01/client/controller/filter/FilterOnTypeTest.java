/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller.filter;

import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * @author robin
 */
public class FilterOnTypeTest extends AbstractFilterTest {
    
    @Test
    public void test_evaluateFilter_FIRE() {
        FilterOnType filter = new FilterOnType("FIRE");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 2);
    }
    
    @Test
    public void test_evaluateFilter_WATER() {
        FilterOnType filter = new FilterOnType("WATER");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 5);
    }
    
    @Test
    public void test_evaluateFilter_BUG() {
        FilterOnType filter = new FilterOnType("BUG");
        assertEquals(filter.evaluateFilter(_allMarkers).size(), 6);
    }
}
