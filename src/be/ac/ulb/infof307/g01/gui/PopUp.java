/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Main;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
//import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hoornaert
 */
public class PopUp extends Stage {
    
    private final GridPane _layout;
    private final Scene _scene;
    
    public PopUp() {
        super.initStyle(StageStyle.TRANSPARENT);
        _layout = new GridPane();
        _layout.setPrefSize(150, 150);
        _scene = new Scene(_layout);
        _scene.setFill(Color.TRANSPARENT);
        setScene(_scene);
        
        initOwner(Main.getStage());
        initStyle();
    }
    
    public void add(Node node, int one, int two, int w1, int w2) {
        _layout.add(node,one,two,w1,w2);
        
        centerOnParent();
    }
    
    public void addConstraints(ArrayList<ColumnConstraints> col,ArrayList<RowConstraints> row) {
        for (int i=0; i<col.size(); i++) 
            _layout.getColumnConstraints().add(col.get(i));
        
        for (int i=0; i<row.size(); i++) 
            _layout.getRowConstraints().add(row.get(i));
    }
    
    private void initStyle() {
        _layout.setStyle(""
                + "-fx-background-color: #d2d7dd;"
                + "-fx-padding: 5;-fx-spacing: 5;"
                + "-fx-background-radius: 5 5 5 5;"
                + "-fx-border-radius: 5 5 5 5;"
        );
        final int HORIZONTAL_GAP = 8;
        final int VERTICAL_GAP = 4;
        
        _layout.setHgap(HORIZONTAL_GAP);
        _layout.setVgap(VERTICAL_GAP);
    }
    
    protected void centerOnParent() {
        Stage stage = Main.getStage();
        
        this.setX(stage.getX() + (stage.getWidth() - _layout.getPrefWidth()) / 2);
        this.setY(stage.getY() + (stage.getHeight() - _layout.getPrefHeight()) / 2);
    }
    
    public void setSize(int x, int y) {
        _layout.setPrefSize(x, y);
    }
    
}
