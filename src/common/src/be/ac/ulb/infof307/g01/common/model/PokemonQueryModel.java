package be.ac.ulb.infof307.g01.common.model;

import java.util.List;

/** Interface to the data access point related to Pokemon manipulation. */
public interface PokemonQueryModel {
    public List<PokemonSendableModel> getAllPokemons();
    
}
