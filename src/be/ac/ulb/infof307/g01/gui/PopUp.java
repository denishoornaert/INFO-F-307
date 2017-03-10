/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hoornaert
 */
public class PopUp extends Stage {
    
    private final StackPane _layout;
    
    public PopUp() {
        super(StageStyle.UNDECORATED);
        _layout = new StackPane();
        _layout.setPrefSize(150, 150);
        setScene(new Scene(_layout));
        initStyle();
    }
    
    public void add(Node node) {
        ObservableList<Node> children = _layout.getChildren();
        children.add(node);
    }
    
    private void initStyle() {
        _layout.setStyle("-fx-background-color: #d2d7dd;-fx-padding: 15;-fx-spacing: 10;-fx-background-radius: 10 10 10 10;");
    }
    
}
