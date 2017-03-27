/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.ac.ulb.infof307.g01.ReputationScore;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hoornaert
 */
public class ReputationScoreTest extends TestCase {
    
    public ReputationScoreTest(String testName) {
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
    public void test_getScore() {
        int i = 42;
        int j = 69;
        ReputationScore score = new ReputationScore(i, j);
        assertEquals(i-j, score.getScore());
    }
    
    @Test
    public void test_voteDown() {
        int i = 42;
        int j = 69;
        ReputationScore score = new ReputationScore(i, j);
        int n = 7;
        for (int k = 0; k < n; k++) {
            score.voteDown();
        }
        assertEquals(i-j-n, score.getScore());
    }
}
