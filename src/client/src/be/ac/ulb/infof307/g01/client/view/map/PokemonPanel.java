package be.ac.ulb.infof307.g01.client.view.map;

import be.ac.ulb.infof307.g01.client.model.map.PokemonModel;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 * Represents a set of Pokemons.
 * Each Pokemon is represented by a PokemonLabel.
 */
public class PokemonPanel extends StackPane {
    
    ScrollPane _scroll;
    FlowPane _flow;
    
    public PokemonPanel() {
        super();
        initWidgets();
        initStyle();
        placeWidgets();
    }

    private void initWidgets() {
        _scroll = new ScrollPane();
        _flow = new FlowPane();
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
    
    public void setPokemons(final ArrayList<PokemonModel> pokemons) {
        for (final PokemonModel pokemon : pokemons) {
            _flow.getChildren().add(new PokemonLabel(pokemon));
        }
    }
    
}
