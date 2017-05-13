package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.controller.app.MessagePopUpController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.view.map.MarkerDetailsPopUp;
import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.logging.Level;

/**
 * Creates and controls a MarkerDetailsPopUp used to display a marker's informations.
 * Used when the user clicks on a marker he did not create (and can not edit).
 */
public class MarkerDetailsPopUpController extends AbstractMarkerPopUpController {
    
    private final MarkerDetailsPopUp _detailsPopUp;
    
    public MarkerDetailsPopUpController(MarkerModel marker) throws InstantiationException {
        super(marker);
        _detailsPopUp = new MarkerDetailsPopUp(this);
        _detailsPopUp.setPokemonView(_marker.getImagePath());
        initVoteControls();
        updateVoteView();
    }
        
    /**
     * Initializes the vote controls for the marker.
     * Disables both vote buttons for visitors, and either vote button
     * if the user already made the matching vote on this marker.
     */
    private void initVoteControls() {
        if(! UserController.getInstance().isConnected()) {
            _detailsPopUp.disableVoteButtons();
            
        } else {
            String username = UserController.getInstance().getUsername();
            ReputationVoteSendableModel reputationVote = _marker.getUserVote(username);
            
            if(reputationVote != null) {
                if(reputationVote.getIsUpVote()) {
                    _detailsPopUp.disableUpVoteButton();
                } else {
                    _detailsPopUp.disableDownVoteButton();
                }
            }
        }
    }
    
    /**
     * Updates the marker reputation in the view.
     */
    private void updateVoteView() {
        int vote = _marker.getReputationScore();
        _detailsPopUp.updateVoteView(vote);
    }
    
    /**
     * Adds the user's vote to the marker.
     * Calls updateVoteView to and update the view accordingly
     * @param isUpVote true if the vote is positive, false if negative.
     */
    public void addVote(boolean isUpVote) {
        try {
            _marker.addUserVote(UserController.getInstance().getUsername(), isUpVote);
        } catch(InvalidParameterException exception){
            MessagePopUpController.createPopUpOrLog(Level.SEVERE, exception.getMessage());
        }
        _detailsPopUp.enableUpVoteButton();
        _detailsPopUp.disableDownVoteButton();
        updateVoteView();
    }
    
    public CoordinateSendableModel getCoordinates() {
        return _marker.getCoordinate();
    }
    
    public String getPokemonName() {
        return _marker.getPokemonName();
    }
    
    public String getUsername() {
    	return _marker.getUsername();
    }
    
    public Timestamp getTimestamp() {
        return _marker.getTimestamp();
    }
    
    public String getPokemonLife() {
        return "" + _marker.geMarkerLife();
    }
    
    public String getPokemonAttack() {
        return "" + _marker.getMarkerAttack();
    }
    
    public String getPokemonDefense() {
        return Integer.toString(_marker.getMarkerDefense());
    }
    
    public String getVoteScore() {
        return Integer.toString(_marker.getReputationScore());
    }
}
