package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Main;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * TODO: add description
 */
public class PopUp extends Stage {

    private final StackPane _layout;
    private final Scene _scene;

    public PopUp() {
        super.initStyle(StageStyle.TRANSPARENT);
        _layout = new StackPane();
        _scene = new Scene(_layout);
        _layout.setPrefSize(250, 150);

        _scene.setFill(Color.TRANSPARENT);
        setScene(_scene);

        initOwner(Main.getStage());
        initStyle();
    }
    
    public void add(Node node) {
        ObservableList<Node> children = _layout.getChildren();
        children.add(node);
        
        centerOnParent();
    }

    private void initStyle() {
        _scene.getStylesheets().add(new File("assets/bootstrap.css").toURI().toString());
        _layout.setStyle(""
                + "-fx-background-color: #d2d7dd;"
                + "-fx-padding: 10;-fx-spacing: 5;"
                + "-fx-background-radius: 5 5 5 5;"
                + "-fx-border-radius: 5 5 5 5;"
        );
    }

    public void centerOnParent() {
        Stage stage = Main.getStage();

        this.setX(stage.getX() + (stage.getWidth() - _layout.getPrefWidth()) / 2);
        this.setY(stage.getY() + (stage.getHeight() - _layout.getPrefHeight()) / 2);
    }

    public void setSize(int x, int y) {
        _layout.setPrefSize(x, y);
    }
    
}
