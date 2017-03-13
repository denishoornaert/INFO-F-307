/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Main;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        _scene = new Scene(_layout);
        _layout.setPrefSize(150, 150);
        _scene.setFill(Color.TRANSPARENT);
        setScene(_scene);
        
        initOwner(Main.getStage());
        initStyle();
    }
    
    public void add(Node node, int one, int two, int w1, int w2) {
        _layout.add(node,one,two,w1,w2);
    }
    
    public void addConstraints(ArrayList<ColumnConstraints> col,ArrayList<RowConstraints> row) {
        for (int i=0; i<col.size(); i++) _layout.getColumnConstraints().add(col.get(i));
        for (int i=0; i<row.size(); i++) _layout.getRowConstraints().add(row.get(i));
    }
    
    private void initStyle() {
        _scene.getStylesheets().add(new File("assets/bootstrap.css").toURI().toString());
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