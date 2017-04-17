/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.view;

import be.ac.ulb.infof307.g01.model.PokemonModel;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author hoornaert
 */
public class PokemonPanel extends StackPane {
    
    ScrollPane _scroll;
    FlowPane _flow;
    
    public PokemonPanel(ArrayList<PokemonModel> pokemons) {
        super();
        initWidgets(pokemons);
        initStyle();
        placeWidgets();
    }

    private void initWidgets(ArrayList<PokemonModel> pokemons) {
        _scroll = new ScrollPane();
        _flow = new FlowPane();
        setPokemons(pokemons); // temp
    }

    private void initStyle() {
        _flow.setAlignment(Pos.CENTER);
        _flow.setHgap(10);
        _flow.setVgap(10);
        _flow.setStyle("-fx-background-color: rgba(255,255,  255, 1.0);");
        _scroll.setFitToWidth(true);
        _scroll.setFitToHeight(true);
    }

    private void placeWidgets() {
        _scroll.setContent(_flow);
        this.getChildren().add(_scroll);
    }
    
    public void setPokemons(ArrayList<PokemonModel> pokemons) {
        for (PokemonModel pokemon : pokemons) {
            _flow.getChildren().add(new PokemonLabel(pokemon));
        }
    }
    
}
