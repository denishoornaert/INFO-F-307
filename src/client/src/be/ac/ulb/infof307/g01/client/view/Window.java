package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.WindowController;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import java.awt.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * Main window
 * 
 */
public class Window extends Scene {
    
    private BorderPane _borderPane;
    private StackPane _stackPane;
    private Button _showHidePanelButton;
    
    private final WindowController _controller;
    
    public Window(WindowController controller, BorderPane pane) {
        super(pane);
        _controller = controller;
        initWidgets(pane);
        placeWidgets();
    }
    
    private void initWidgets(BorderPane pane) {
        _borderPane = pane;
        _stackPane = new StackPane();
        initShowHidePanelButton();
    }

    private void initShowHidePanelButton() {
        _showHidePanelButton = new Button("⚙");
        _showHidePanelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                _controller.buttonPressed();
            }
        });
        _showHidePanelButton.getStyleClass().add("primary");
    }

    private void placeWidgets() {
        _borderPane.setCenter(_stackPane);
    }
    
    public void placeWidget(MapView map) {
        _stackPane.getChildren().add(map);
    }
    
    public void placeButton() {
        _stackPane.getChildren().add(_showHidePanelButton);
        StackPane.setAlignment(_showHidePanelButton, Pos.TOP_LEFT);
    }

    public void showPanel(PanelView panel) {
        _borderPane.setLeft(panel);
    }

    public void hidePanel() {
        _borderPane.setLeft(null);
    }

    public void simulateCLick() {
        _showHidePanelButton.fire();
    }
    
}
