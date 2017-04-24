package be.ac.ulb.infof307.g01.client.model;

import junit.framework.TestCase;
import org.junit.Test;

import be.ac.ulb.infof307.g01.client.model.ReputationScoreModel;
import be.ac.ulb.infof307.g01.client.model.ReputationVoteModel;

public class ReputationScoreModelTest extends TestCase {
    
    private final int _upVotes = 42;
    private final int _downVotes = 69;
    private ReputationScoreModel _score;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        _score = new ReputationScoreModel(_upVotes, _downVotes);
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
