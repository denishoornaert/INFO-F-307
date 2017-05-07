package be.ac.ulb.infof307.g01.client.controller;

import java.sql.Timestamp;

import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.view.PinPopUp;
import be.ac.ulb.infof307.g01.common.model.ReputationVoteSendableModel;

/**
 * 
 * Class that manage and create a PinPopUp.
 * 
 */
public class PinPopUpController extends InformationPopUpController {
    
    private final PinPopUp _pinPopUp;
    
    public PinPopUpController(MarkerModel marker) {
        super(marker);
        _pinPopUp = new PinPopUp(this);
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

    public void addDownVote() {
        _marker.addVote(UserController.getInstance().getUsername(), false);
        _pinPopUp.enableUpVoteButton();
        _pinPopUp.disableDownVoteButton();
        updateVoteView();
    }

    public void addUpVote() {
        _marker.addVote(UserController.getInstance().getUsername(), true);
        _pinPopUp.enableDownVoteButton();
        _pinPopUp.disableUpVoteButton();
        updateVoteView();
    }
    
    private void updateVoteView() {
        int vote = _marker.getReputationScore();
        _pinPopUp.updateVoteView(vote);
    }
    
}
