package be.ac.ulb.infof307.g01;

/** Score of reputation of a marker, as down and upvotes. */
public class ReputationScore {
    
    private int _upVotes;
    private int _downVotes;
    
    public ReputationScore(int up, int down) {
        _upVotes = up;
        _downVotes = down;
    }

    int getUpVotes() {
        return _upVotes;
    }

    int getDownVotes() {
        return _downVotes;
    }   
    
    public int getScore() {
        return _upVotes - _downVotes;
    }

    void vote(ReputationVoteModel reputationVote) {
        if(reputationVote == ReputationVoteModel.UP) {
            _upVotes++;
        } else if (reputationVote == ReputationVoteModel.DOWN) {
            _downVotes++;
        }
    } 
}
