package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.PinPopUp;
import java.sql.Timestamp;

/**
 * TODO: add description
 */
public class PinPopUpController {
    private final MarkerModel _marker;
    private final PinPopUp _pinPopUp;
    
    public PinPopUpController(MarkerModel marker) {
        _marker = marker;
        _pinPopUp = new PinPopUp(this);
        updateVoteView();
    }
    
    public CoordinateModel getCoordinates() {
        return _marker.getCoordinate();
    }
    
    public String getPokemonName() {
        return _marker.getPokemonName();
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
