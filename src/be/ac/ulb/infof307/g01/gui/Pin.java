/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Coordinate;
import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.Marker;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author remy
 */
public class Pin extends ImageView {
    
    private final Marker _marker;
    
    public Pin(Marker marker) {
        super();
        
        _marker = marker;
        
        initImage();
        drawPin();
        movePin();
    }
    
    private void initImage() {
        String imagePath = new File("assets/pin.png").toURI().toString();
        Image image = new Image(imagePath);
        setImage(image);
    }
    
    private void drawPin() {
        StackPane stackPane = Main.getStackPane();
        ObservableList<Node> children = stackPane.getChildren();
        children.add(this);
    }
    
    private void movePin() {
        // Currently not adapted to pin anchor
        Coordinate markerCoord = _marker.getCoordinate();
        setTranslateX(markerCoord.getX());
        setTranslateY(markerCoord.getY());
    }
    
    
}
