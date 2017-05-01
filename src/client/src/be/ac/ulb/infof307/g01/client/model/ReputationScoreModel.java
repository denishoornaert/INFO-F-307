package be.ac.ulb.infof307.g01.client.model;

import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
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

    public void vote(ReputationVoteSendableModel reputationVote) {
        if(reputationVote.isUpVote()) {
            _upVotes++;
        } else {
            _downVotes++;
        }
    } 
}
