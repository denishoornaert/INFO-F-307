package be.ac.ulb.infof307.g01.client.view.map;

import be.ac.ulb.infof307.g01.client.controller.map.ClusterPopUpController;
import be.ac.ulb.infof307.g01.client.model.map.PokemonModel;
import be.ac.ulb.infof307.g01.client.view.app.AbstractPopUp;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Popup used to display a cluster's markers.
 * Used when the user clicks on a marker cluster.
 */
public class ClusterPopUp extends AbstractPopUp {
    
    private VBox _mainBox;
    private HBox _bottomBox;
    private PokemonPanel _pokemonPanel;
    private Label _message;
    private Button _closeButton;
    
    public ClusterPopUp(final ClusterPopUpController controller) {
        super(controller);
        initWidgets();
        placeWidgets();
        setStyle();
        show();
    }
    
    private void initWidgets() {
        _mainBox = new VBox();
        _bottomBox = new HBox();
        initPokemonPanel();
        initMessageLabel();
        initCloseButton();
    }
    
    private void initPokemonPanel() {
        _pokemonPanel = new PokemonPanel();
    }
    
    private void initMessageLabel() {
        _message = new Label("Zoom on the map to see all the pokemons.");
        _message.setMaxWidth(Region.USE_PREF_SIZE);
        _message.setWrapText(true);
    }
    
    private void initCloseButton() {
        _closeButton = getCloseButton("ok", "primary");
    }

    private void placeWidgets() {
        _bottomBox.getChildren().addAll(_message, _closeButton);
        _mainBox.getChildren().addAll(_pokemonPanel, _bottomBox);
        super.add(_mainBox);
    }

    private void setStyle() {
        _bottomBox.setAlignment(Pos.CENTER);
        _bottomBox.setSpacing(10);
        VBox.setVgrow(_mainBox, Priority.ALWAYS);
        _mainBox.setMaxWidth(Double.MAX_VALUE);
        super.setSize(350, 350);
    }
    
    public void setPokemons(final ArrayList<PokemonModel> pokemons) {
        _pokemonPanel.setPokemons(pokemons);
    }
    
}
