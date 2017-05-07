package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.PokemonSendableModel;
import java.util.List;

/** Interface to the data access point related to Pokemon manipulation. */
public interface PokemonQueryController {
    public List<PokemonSendableModel> getAllPokemons();
    
}
