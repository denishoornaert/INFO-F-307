package be.ac.ulb.infof307.g01.client.view.app;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A very minimalist abstract pop-up class defining the defaut pop-up 
 * layout and scene 
 * 
 * @author Groupe01
 */
public abstract class AbstractPopUp extends Stage {

    private final StackPane _layout;
    private final Scene _scene;
    
    private final PopUpController _controller;
    
    public AbstractPopUp() throws InstantiationException {
        this(new PopUpController());
    }
    
    public AbstractPopUp(PopUpController controller) {
        super.initStyle(StageStyle.TRANSPARENT);
        _layout = new StackPane();
        _scene = new Scene(_layout);
        _layout.setPrefSize(250, 150);
        _scene.setFill(Color.TRANSPARENT);
        setScene(_scene);
        _controller = controller;
        initOwner(Main.getStage());
        initStyle();
    }
    
    protected Button getCloseButton(String text, String style) {
        Button button = new Button(text);
        AbstractPopUp instance = this;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                _controller.close(instance);
            }
        });
        button.getStyleClass().add(style);
        return button;
    }
    
    protected void add(Node node) {
        ObservableList<Node> children = _layout.getChildren();
        children.add(node);
        
        centerOnParent();
    }

    private void initStyle() {
        _scene.getStylesheets().add(ClientConfiguration.getInstance().getStyleFileName());
        _layout.setStyle(""
                + "-fx-background-color: #d2d7dd;"
                + "-fx-padding: 10;-fx-spacing: 5;"
                + "-fx-background-radius: 5 5 5 5;"
                + "-fx-border-radius: 5 5 5 5;"
        );
    }

    protected void centerOnParent() {
        Stage stage = Main.getStage();

        this.setX(stage.getX() + (stage.getWidth() - _layout.getPrefWidth()) / 2);
        this.setY(stage.getY() + (stage.getHeight() - _layout.getPrefHeight()) / 2);
    }

    protected void setSize(int x, int y) {
        _layout.setPrefSize(x, y);
    }
    
}