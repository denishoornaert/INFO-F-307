/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.view;

import be.ac.ulb.infof307.g01.model.PokemonModel;
import java.io.File;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author hoornaert
 */
public class PokemonLabel extends BorderPane {
    
    private ImageView _image;
    private Label _pokemonName;
    
    public PokemonLabel(PokemonModel pokemon) {
        super();
        initWidgets(pokemon);
        //initStyle();
        placeWidgets();
    }

    private void initWidgets(PokemonModel pokemon) {
        String path = new File(pokemon.getImagePath()).toURI().toString();
        _image = new ImageView(new Image(path));
        _pokemonName = new Label(pokemon.getName());
    }

    private void initStyle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void placeWidgets() {
        setCenter(_image);
        setBottom(_pokemonName);
    }
    
}
