package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.PokemonTypeSendableModel;
import java.util.List;

/** Interface to the data access point related to Pokemon types manipulation. */
public interface PokemonTypeQueryController {
    
    public List<PokemonTypeSendableModel> getAllPokemonTypes();
    
}
