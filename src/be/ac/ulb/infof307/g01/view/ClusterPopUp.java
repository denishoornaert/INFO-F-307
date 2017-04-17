package be.ac.ulb.infof307.g01.view;

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
    
    private VBox _mainBox;
    private HBox _bottomBox;
    private PokemonPanel _pokemonPanel;
    private Label _message;
    private Button _closeButton;
    
    public ClusterPopUp() {
        super();
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
        _message = new Label("You have clicked on a pokemon cluster. To see "
                + "which pokmon is contained in the pokemon cluster please zoom"
                + ".");
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
        super.setSize(300, 300);
    }
    
}
