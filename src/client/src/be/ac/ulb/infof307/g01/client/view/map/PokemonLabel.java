package be.ac.ulb.infof307.g01.client.view.map;

import be.ac.ulb.infof307.g01.client.model.app.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.model.map.PokemonModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Represents a pokemon, with its picture and name.
 * Used by the PokemonPanel class
 */
public class PokemonLabel extends BorderPane {
    
    private ImageView _image;
    private HBox _pokemonNameBox;
    private Label _pokemonName;
    
    public PokemonLabel(PokemonModel pokemon) {
        super();
        initWidgets(pokemon);
        initStyle();
        placeWidgets();
    }

    private void initWidgets(PokemonModel pokemon) {
        String imagePath = pokemon.getImagePath();
        imagePath = ClientConfiguration.addFilePrefix(imagePath);
        _image = new ImageView(new Image(imagePath));
        _pokemonNameBox = new HBox();
        _pokemonName = new Label(pokemon.getName());
    }

    private void initStyle() {
        _pokemonName.setStyle("-fx-font-size: 17px Tahoma;");
        _pokemonName.setTextFill(Color.web("#FFFFFF"));
        _pokemonNameBox.setAlignment(Pos.CENTER);
        _pokemonNameBox.setPadding(new Insets(2));
        _pokemonNameBox.setStyle("-fx-background-color: rgba(0, 6, 6, 0.5);");
    }

    private void placeWidgets() {
        setCenter(_image);
        _pokemonNameBox.getChildren().add(_pokemonName);
        setBottom(_pokemonNameBox);
    }
    
}
