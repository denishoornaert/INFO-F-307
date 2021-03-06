package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Represents a user's vote on the reputation of a marker.
 * A vote can be positive of negative.
 * The sendable keyword indicates the object can be serialized and sent 
 * over the network.
 */
@XmlRootElement
public class ReputationVoteSendableModel {
    
    private String _username;
    private boolean _isUpVote;
    private int _markerId;
    
    /**
     * Default constructor.
     * Required by Jersey.
     */
    public ReputationVoteSendableModel() {}
    
    /** 
     * @param username the user that votes
     * @param isUpVote true if it's a positive vote, false otherwise
     * @param markerId the id of the marker
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
