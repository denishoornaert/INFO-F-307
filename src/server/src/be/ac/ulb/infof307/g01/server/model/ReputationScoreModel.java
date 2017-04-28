package be.ac.ulb.infof307.g01.server.model;

import be.ac.ulb.infof307.g01.common.model.ReputationScoreSendableModel;

/** Score of reputation of a marker, as down and upvotes. */
public class ReputationScoreModel extends ReputationScoreSendableModel {
    
    public ReputationScoreModel(int up, int down) {
        _upVotes = up;
        _downVotes = down;
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
