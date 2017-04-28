package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.ClusterPopUp;
import java.util.ArrayList;

/**
 * TODO description
 * 
 * @author Groupe01
 */
public class ClusterPopUpController {
    
    private final ClusterPopUp _view;
    private final MarkerController _markerController;
    private ArrayList<PokemonModel> _pokemonList;
    
    public ClusterPopUpController(MarkerController controller, ArrayList<Integer> markersIds) {
        _view = new ClusterPopUp(this);
        _markerController = controller;
        initPokemonList(markersIds);
        _view.setPokemons(_pokemonList);
    }
    
    private void initPokemonList(ArrayList<Integer> markersIds) {
        _pokemonList = new ArrayList<>();
        for (Integer id : markersIds) {
            MarkerModel marker = _markerController.getMarkerModelFromId(id);
            _pokemonList.add(marker.getPokemon());
        }
    }
    
}
