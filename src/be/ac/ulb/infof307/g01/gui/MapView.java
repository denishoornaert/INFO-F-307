/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.MapController;
import java.io.File;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
    
    private MapController _map;
    private ImageView _imageView;
    
    public MapView() {
        super();
        _map = new MapController();
        setImageView();
        initLayout();
    }
    
    private void setImageView() {
        String imageUri = _map.getImagePath();
        _imageView = new ImageView(new Image(imageUri));
    }
    
    private void initLayout() {
        StackPane layout = Main.getStackPane();
        ObservableList<Node> child = layout.getChildren();
        child.add(_imageView);
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
    
    public void adaptToScene(ReadOnlyDoubleProperty property) {
        // bind the preferred size of the scroll area to the size of the scene.
        prefWidthProperty().bind(property);
        prefHeightProperty().bind(property);

        // center the scroll contents.
        setHvalue(getHmin()+(getHmax()-getHmin())/2);
        setVvalue(getVmin()+(getVmax()-getVmin())/2);
    }

}
