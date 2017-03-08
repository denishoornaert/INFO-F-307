/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author hoornaert
 */
public class MapView extends ScrollPane {
    
    public MapView() {
        super();
        init();
    }
    
    private void init() {
        StackPane layout = new StackPane();
        layout.getChildren().add(
                new ImageView(new Image(
                    "https://www.evl.uic.edu/pape/data/Earth/4096/PathfinderMap.jpg")));
        createScrollPane(layout);
        
    }
    
    /** @return a ScrollPane which scrolls the layout. */
    private void createScrollPane(Pane layout) {
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setPannable(true);
        setPrefSize(800, 600);
        setContent(layout);
    }
    
    public void adatpToScene(ReadOnlyDoubleProperty property) {
        // bind the preferred size of the scroll area to the size of the scene.
        prefWidthProperty().bind(property);
        prefHeightProperty().bind(property);

        // center the scroll contents.
        setHvalue(getHmin()+(getHmax()-getHmin())/2);
        setVvalue(getVmin()+(getVmax()-getVmin())/2);
    }

}
