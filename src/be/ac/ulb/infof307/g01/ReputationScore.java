package be.ac.ulb.infof307.g01;

/**
 * TODO: add description
 */
public class ReputationScore {
    
    private int _upVote;
    private int _downVote;

    public ReputationScore() {
        this(33, 17);
    }
    
    public ReputationScore(int up, int down) {
        _upVote = up;
        _downVote = down;
    }
    
    public void voteUp() {
        _upVote++;
    }
        
    public void voteDown() {
        _downVote++;
    }
    
    public int getScore() {
        return _upVote-_downVote;
    }
    
}
