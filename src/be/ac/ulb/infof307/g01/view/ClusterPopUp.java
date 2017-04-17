package be.ac.ulb.infof307.g01.view;

import be.ac.ulb.infof307.g01.controller.MarkerController;
import be.ac.ulb.infof307.g01.model.MarkerModel;
import be.ac.ulb.infof307.g01.model.PokemonModel;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * Pop-up showed when the user clicks on a pokemon cluster.
 */
public class ClusterPopUp extends PopUp {
    
    private MarkerController _markerController;
    private ArrayList<PokemonModel> _pokemonList;
    
    private VBox _mainBox;
    private HBox _bottomBox;
    private PokemonPanel _pokemonPanel;
    private Label _message;
    private Button _closeButton;
    
    public ClusterPopUp(MarkerController controller, ArrayList<Integer> markersIds) {
        super();
        _markerController = controller;
        initPokemonList(markersIds);
        initWidgets();
        placeWidgets();
        setStyle();
        show();
    }
    
    private void initPokemonList(ArrayList<Integer> markersIds) {
        _pokemonList = new ArrayList<>();
        for (Integer id : markersIds) {
            MarkerModel marker = _markerController.getMarkerModelFromId(id);
            _pokemonList.add(marker.getPokemon());
        }
    }
    
    private void initWidgets() {
        _mainBox = new VBox();
        _bottomBox = new HBox();
        initPokemonPanel();
        initMessageLabel();
        initCloseButton();
    }
    
    private void initPokemonPanel() {
        _pokemonPanel = new PokemonPanel(_pokemonList);
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
        super.setSize(350, 350);
    }
    
}
