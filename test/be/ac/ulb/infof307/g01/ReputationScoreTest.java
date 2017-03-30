package be.ac.ulb.infof307.g01;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author hoornaert
 */
public class ReputationScoreTest extends TestCase {
    
    private final int _upVotes = 42;
    private final int _downVotes = 69;
    private ReputationScore _score;
    
    public ReputationScoreTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        _score = new ReputationScore(_upVotes, _downVotes);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void test_getScore_normal() {
        assertEquals(_upVotes - _downVotes, _score.getScore());
    }
    
    @Test
    public void test_voteDown_normal() {
        int n = 7;
        for (int k = 0; k < n; k++) {
            _score.vote(ReputationVoteModel.DOWN);
        }
        assertEquals(_upVotes - _downVotes - n, _score.getScore());
        assertEquals(_downVotes + n, _score.getDownVotes());
    }
    
    @Test
    public void test_voteUp_normal() {
        int n = 19;
        for (int k = 0; k < n; k++) {
            _score.vote(ReputationVoteModel.UP);
        }
        assertEquals(_upVotes - _downVotes + n, _score.getScore());
        assertEquals(_upVotes + n, _score.getUpVotes());
    }
}
