package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.NewMarkerPopUp;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Controller the NewMarkerPopUp that enable the user to create a new Marker.
 */
public class NewMarkerPopUpController {
    
    private PokemonListModel _pokemonListModel;
    private NewMarkerPopUp _newMarkerPopUp;
    /** Coordinates associated to the current pop-up. */
    private CoordinateModel _newMarkerCoordinate;
    private MapController _mapController;
    private final static String _defaultImagePath = "assets/unknown_pokemon.png";
 
    public NewMarkerPopUpController(MarkerController markerController) {
        _pokemonListModel = new PokemonListModel();
        _mapController = map;
    }
    
    /**
     * Call when the entry of pokemon name is modified
     * 
     * @param newPokemonNameEntry the new pokemon name entry
     */
    public void newPokemonNameUpdate(String newPokemonNameEntry) {
        ArrayList<String> pokemonNames = getPokemonByName(newPokemonNameEntry);
        if(pokemonNames.size() == 1) {
            selectedPokemonName(pokemonNames.get(0));
        } else {
            _newMarkerPopUp.setPokemonView(_defaultImagePath);
        }
        _newMarkerPopUp.setComboBoxPokemonNameContent(this, pokemonNames);
    }
    
    /*
    * Method that aims to return the set of pokemon for which the name match the
    * pattern given in parameters
    *
    * @parameters the pattern
    * @return the list of pokemon names that matches the pattern
    */
    private ArrayList<String> getPokemonByName(String researchPattern) {
        ArrayList<String> res;
        if(researchPattern.length() > 1) {
            res = _pokemonListModel.findPokemonFromPattern(researchPattern);
        }
        else {
            res = _pokemonListModel.getAllNames();
        }
        return res;
    }
    
    public void askForCreateMarker(double coordinateX, double coordinateY) {
        if(_newMarkerPopUp == null) {
            // Converts from event coordinate (centered in the upper left corner)
            // to marker coordinate (centered in the middle of the image)
            _newMarkerCoordinate = new CoordinateModel(coordinateX, coordinateY);
            _newMarkerPopUp = new NewMarkerPopUp(this);
            newPokemonNameUpdate("");
        }
    }
    
    public void cancelPopUpCreateMarker() {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
    }
    
    public void endPopUpCreateMarker(String pokemonName, int lifePoint, int attack, int defense, Timestamp dateView) {
        _newMarkerPopUp.close();
        _newMarkerPopUp = null;
        // TODO relier popUp pokemon avec la creation des pokemons 
        PokemonModel pokemon = PokemonModel.getPokemonByName(pokemonName);
        _newMarkerCoordinate = null;
        _markerController.createMarker(pokemon, _newMarkerCoordinate);
        //_mapController.addMarkerController(newMarker);
        //_mapController.getMapView().createPin(newMarker);
    }
    
    /*
    * Method called when the user has selected a pokemon name listed among the combobox.
    */
    public void selectedPokemonName(String selectedString) {
        PokemonModel pokemon = PokemonModel.getPokemonByName(selectedString);
        String path = pokemon.getPathImage();
        _newMarkerPopUp.setPokemonView(path);
    }
    
}