package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/** 
 * A user's vote on the reputation of a marker, can be up or down.
 */
@XmlRootElement
public class ReputationVoteSendableModel {
    
    private String _username;
    private boolean _isUpVote;
    private int _markerId;
    
    public ReputationVoteSendableModel() { } // Could not be remove
    
    /** Constructor
     * 
     * @param username the user
     * @param isUpVote True if it's a positive vote
     * @param markerId the id of the pokemon
     */
    public ReputationVoteSendableModel(final String username, 
            final boolean isUpVote, final int markerId) {
        _username = username;
        _isUpVote = isUpVote;
        _markerId = markerId;
    }
    
    public String getUsername() {
        return _username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(final String username) {
        this._username = username;
    }

    public boolean getIsUpVote() {
        return _isUpVote;
    }
    
    /**
     * @param isUpVote the getIsUpVote to set
     */
    public void setIsUpVote(final boolean isUpVote) {
        this._isUpVote = isUpVote;
    }

    /**
     * @return the _markerId
     */
    public int getMarkerId() {
        return _markerId;
    }

    /**
     * @param markerId the markerId to set
     */
    public void setMarkerId(final int markerId) {
        this._markerId = markerId;
    }

}
