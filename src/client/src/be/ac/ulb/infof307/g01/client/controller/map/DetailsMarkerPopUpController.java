package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.controller.app.MessagePopUpController;
import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import java.sql.Timestamp;

import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.view.map.DetailsMarkerPopUp;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;
import java.security.InvalidParameterException;
import java.util.logging.Level;

/**
 * 
 * Class that manage and create a DetailsMarkerPopUp.
 * 
 */
public class DetailsMarkerPopUpController extends InformationPopUpController {
    
    private final DetailsMarkerPopUp _pinPopUp;
    
    public DetailsMarkerPopUpController(MarkerModel marker) throws InstantiationException {
        super(marker);
        _pinPopUp = new DetailsMarkerPopUp(this);
        _pinPopUp.setPokemonView(_marker.getImagePath());
        updateVoteView();
        
        String username = UserController.getInstance().getUsername();
        if(username.isEmpty()) {
            _pinPopUp.disableVoteButtons();
            
        } else {
            ReputationVoteSendableModel reputationVote = _marker.getReputationVote(
                username);
            if(reputationVote != null) {
                if(reputationVote.getIsUpVote()) {
                    _pinPopUp.disableUpVoteButton();
                } else {
                    _pinPopUp.disableDownVoteButton();
                }
            }
        }
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
    // TODO Refactor addVote
    public void addDownVote() {
        try {
            _marker.addVote(UserController.getInstance().getUsername(), false);
        } catch(InvalidParameterException exception){
            MessagePopUpController.createPopUpOrLog(Level.SEVERE, exception.getMessage());
        }
        _pinPopUp.enableUpVoteButton();
        _pinPopUp.disableDownVoteButton();
        updateVoteView();
    }

    public void addUpVote() {
        try {
            _marker.addVote(UserController.getInstance().getUsername(), true);
        } catch(InvalidParameterException exception){
            MessagePopUpController.createPopUpOrLog(Level.SEVERE, exception.getMessage());
        }
        _pinPopUp.enableDownVoteButton();
        _pinPopUp.disableUpVoteButton();
        updateVoteView();
    }
    
    private void updateVoteView() {
        int vote = _marker.getReputationScore();
        _pinPopUp.updateVoteView(vote);
    }
    
}
