package be.ac.ulb.infof307.g01.model;

/** Score of reputation of a marker, as down and upvotes. */
public class ReputationScoreModel {
    
    private int _upVotes;
    private int _downVotes;
    
    public ReputationScoreModel(int up, int down) {
        _upVotes = up;
        _downVotes = down;
    }

    public int getUpVotes() {
        return _upVotes;
    }

    public int getDownVotes() {
        return _downVotes;
    }   
    
    public int getScore() {
        return _upVotes - _downVotes;
    }

    public void vote(ReputationVoteModel reputationVote) {
        if(reputationVote == ReputationVoteModel.UP) {
            _upVotes++;
        } else if (reputationVote == ReputationVoteModel.DOWN) {
            _downVotes++;
        }
    } 
}
