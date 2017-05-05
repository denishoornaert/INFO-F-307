package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/** 
 * A user's vote on the reputation of a marker, can be up or down.
 */
@XmlRootElement
public class ReputationVoteSendableModel {
    
    private String _username;
    private boolean _isUpVote;
    
    public ReputationVoteSendableModel() { } // Could not be remove
    
    /** Constructor
     * 
     * @param username the user
     * @param isUpVote True if it's a positive vote
     */
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

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this._username = username;
    }

    /**
     * @param isUpVote the isUpVote to set
     */
    public void setIsUpVote(boolean isUpVote) {
        this._isUpVote = isUpVote;
    }
}
