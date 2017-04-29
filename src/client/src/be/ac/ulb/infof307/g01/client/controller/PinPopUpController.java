package be.ac.ulb.infof307.g01.client.controller;

import java.sql.Timestamp;

import be.ac.ulb.infof307.g01.common.model.CoordinateSendableModel;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.view.PinPopUp;

/**
 * 
 * Pop that shows the information of a marker to the user. Typically, this pop-up
 * is displayed when the user click on a marker that he has not created.
 * 
 */
public class PinPopUpController {
    private final MarkerModel _marker;
    private final PinPopUp _pinPopUp;
    
    public PinPopUpController(MarkerModel marker) {
        _marker = marker;
        _pinPopUp = new PinPopUp(this);
        _pinPopUp.setPokemonView(_marker.getImagePath());
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
        return "" + _marker.getPokemonLife();
    }
    
    public String getPokemonAttack() {
        return "" + _marker.getPokemonAttack();
    }
    
    public String getPokemonDefense() {
        return "" + _marker.getPokemonDefense();
    }
    
    public String getVoteScore() {
        return "" + _marker.getVoteScore();
    }

    public void addDownVote() {
        _marker.voteDown();
        _pinPopUp.disableVoteButtons();
        updateVoteView();
    }

    public void addUpVote() {
        _marker.voteUp();
        _pinPopUp.disableVoteButtons();
        updateVoteView();
    }
    
    private void updateVoteView() {
        int vote = _marker.getVoteScore();
        _pinPopUp.updateVoteView(vote);
    }
    
}
