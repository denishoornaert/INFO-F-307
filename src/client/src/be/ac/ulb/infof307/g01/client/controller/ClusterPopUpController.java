package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.ClusterPopUp;
import java.util.ArrayList;

/**
 * Simple popUp that shows the different pokemons contained in a cluster of pokemons.
 * This pop-up is displayed when the user click on a cluster displayed on the MapView.
 * 
 * @author Groupe01
 */
public class ClusterPopUpController extends AbstractPopUpController {
    
    private final ClusterPopUp _view;
    private final MarkerController _markerController;
    private ArrayList<PokemonModel> _pokemonList;
    
    public ClusterPopUpController(MarkerController controller, ArrayList<Integer> markersIds) throws InstantiationException {
        super();
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
