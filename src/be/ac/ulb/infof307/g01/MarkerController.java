package be.ac.ulb.infof307.g01;

import java.sql.Timestamp;

/**
 * TODO: add description
 */
public class MarkerController {
    
    MarkerModel _marker; // TODO shouldn't it be named _model or markerModel ?
    
    public MarkerController(PokemonModel pokemon, CoordinateModel _newMarkerCoordinate) {
        _marker = new MarkerModel(pokemon, _newMarkerCoordinate);
    }

    MarkerController(MarkerModel markerModel) {
        _marker = markerModel;
    }
    
    public double getLatitude() {
        return _marker.getCoordinate().getLatitude();
    }
    
    public double getLongitude() {
        return _marker.getCoordinate().getLongitude();
    }

    public Timestamp getTimestamp() {
        return _marker.getTimestamp();
    }

    public String getPokemonName() {
        return _marker.getPokemonName();
    }
    
    public int getPokemonAttack() {
        return _marker.getPokemonAttack();
    }
    
    public int getPokemonDefense() {
        return _marker.getPokemonDefense();
    }
    
    public int getPokemonLife() {
        return _marker.getPokemonLife();
    }
    
    public int getVoteScore() {
        return _marker.getVoteScore();
    } 
    
}
