package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import java.util.List;

/**
 * Interface for the pokemon-related, network-oriented queries.
 * A Query Controller defines an interface for client and server calls, 
 * that may or may not have different implementation for each.
 */
public interface PokemonQueryController {
    public List<PokemonSendableModel> getAllPokemons();
    
}
