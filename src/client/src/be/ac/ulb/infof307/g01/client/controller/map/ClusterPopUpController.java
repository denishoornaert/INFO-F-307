package be.ac.ulb.infof307.g01.client.controller.map;

import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.model.map.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.map.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.map.ClusterPopUp;
import java.util.ArrayList;

/**
 * Controls the popup that displays the markers contained in a marker cluster.
 * Used when the user clicks on a cluster from MapView.
 */
public class ClusterPopUpController extends PopUpController {
    
    private final ClusterPopUp _view;
    private final MarkerController _markerController;
    private ArrayList<PokemonModel> _pokemonList;
    
    public ClusterPopUpController(final MarkerController controller,
            final ArrayList<Integer> markersIds) throws InstantiationException {
        super();
        _view = new ClusterPopUp(this);
        _markerController = controller;
        initPokemonList(markersIds);
        _view.setPokemons(_pokemonList);
    }
    
    /**
     * Initializes the markers contained in the cluster from their IDs.
     * @param markersIds the list of IDs identifying the cluster's markers
     */
    private void initPokemonList(final ArrayList<Integer> markersIds) {
        _pokemonList = new ArrayList<>();
        for (final Integer id : markersIds) {
            final MarkerModel marker = _markerController.getMarkerModelFromId(id);
            _pokemonList.add(marker.getPokemon());
        }
    }
    
}
