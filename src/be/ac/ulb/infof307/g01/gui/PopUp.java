/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Main;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hoornaert
 */
public class PopUp extends Stage {
    
    private final StackPane _layout;
    private final Scene _scene;
    
    public PopUp() {
        super.initStyle(StageStyle.TRANSPARENT);
        _layout = new StackPane();
        _scene = new Scene(_layout);
        _layout.setPrefSize(150, 150);
        _scene.setFill(Color.TRANSPARENT);
        setScene(_scene);
        
        initOwner(Main.getStage());
        initStyle();
    }
    
    public void add(Node node) {
        ObservableList<Node> children = _layout.getChildren();
        children.add(node);
    }
    
    private void initStyle() {
        _layout.setStyle(""
                + "-fx-background-color: #d2d7dd;"
                + "-fx-padding: 10;-fx-spacing: 5;"
                + "-fx-background-radius: 5 5 5 5;"
                + "-fx-border-radius: 5 5 5 5;"
        );
        setSize(250, 150);
    }
    
    public void setSize(int x, int y) {
        _layout.setPrefSize(x, y);
    }
    
}
