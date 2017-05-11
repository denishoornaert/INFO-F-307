package be.ac.ulb.infof307.g01.client.view.map;

import be.ac.ulb.infof307.g01.client.controller.map.InformationPopUpController;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.model.MarkerModel;
import be.ac.ulb.infof307.g01.client.view.app.AbstractPopUp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Abstract pop-up which will serve as a minimalist template for a Pokemon view
 * 
 * @author Groupe01
 */
public abstract class InformationPopUp extends AbstractPopUp {
    
    private ImageView _selectedPokemonView;
    private Button _twitterButton;
    private HBox _widgets;
    private StackPane _stackPane;
    
    private InformationPopUpController _controller;
    protected final MarkerModel _markerModel;
    
    public InformationPopUp(InformationPopUpController controller, int markerId) {
        super(controller);
        _controller = controller;
        _markerModel = _controller.getMarker();
        initWidgets();
        placeWidgets();
        initSize();
    }

    private void initWidgets() {
        initImage();
        initTwitterButton();
        initStack();
    }

    private void placeWidgets() {
        _stackPane.getChildren().add(_selectedPokemonView);
        _stackPane.getChildren().add(_twitterButton);
        StackPane.setAlignment(_twitterButton, Pos.TOP_LEFT);
        _widgets = placeInRow(_stackPane);
        super.add(_widgets);
    }
    
    private void initImage() {
        _selectedPokemonView = new ImageView();
        _selectedPokemonView.setFitHeight(150);
        _selectedPokemonView.setFitWidth(150);
    }
    
    private void initStack() {
        _stackPane = new StackPane();
    }
    
    private void initTwitterButton() {
        _twitterButton = new Button("🌍");
        _twitterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                _controller.sendTwitterPost();
            }
        });
    }
    
    protected void hideTwitterButton() {
        _stackPane.getChildren().remove(_twitterButton);
    }
    
    public void setPokemonView(String imagePath) {
        imagePath = ClientConfiguration.addJarOrFileBeforePath(imagePath);
        _selectedPokemonView.setImage(new Image(imagePath));
    }
    
    protected VBox placeInColumn(Node... nodes) {
        VBox vbox = new VBox();
        ObservableList<Node> childrenV = vbox.getChildren();
        childrenV.addAll(nodes);
        vbox.setSpacing(10);
        return vbox;
    }
    
    protected HBox placeInRow(Node... nodes) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }
    
    @Override
    protected void add(Node node) {
        ObservableList<Node> children = _widgets.getChildren();
        children.addAll(node);
    }
    
    /**
     * Set max width to widget
     * 
     * @param control the current gui element
     */
    protected void setXExpandPolicy(Control control) {
        HBox.setHgrow(control, Priority.ALWAYS);
        control.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initSize() {
        setSize(550, 150);
    }
    
}