package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import java.util.List;

/**
 * Interface for the pokemon type-related, network-oriented queries.
 * A Query Controller defines an interface for client and server calls, 
 * that may or may not have different implementation for each.
 */
public interface PokemonTypeQueryController {
    
    public List<PokemonTypeSendableModel> getAllPokemonTypes();
    
}
