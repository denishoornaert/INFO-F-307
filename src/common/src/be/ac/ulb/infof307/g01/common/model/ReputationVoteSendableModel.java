package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/** 
 * A user's vote on the reputation of a marker, can be up or down.
 */
@XmlRootElement
public class ReputationVoteSendableModel {
    private final String _username;
    private final boolean _isUpVote;
    
    /** Constructor */
    public ReputationVoteSendableModel(String username, boolean isUpVote) {
        _username = username;
        _isUpVote = isUpVote;
    }
    
    public String getUsername() {
        return _username;
    }
    
    public boolean isUpVote() {
        return _isUpVote;
    }
}
