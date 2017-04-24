/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.model.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.ClusterPopUp;
import java.util.ArrayList;

/**
 *
 * @author hoornaert
 */
public class ClusterPopUpController {
    
    private ClusterPopUp _view;
    private MarkerController _markerController;
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
