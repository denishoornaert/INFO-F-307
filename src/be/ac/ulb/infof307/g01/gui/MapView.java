/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Coordinate;
import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.MapController;
import be.ac.ulb.infof307.g01.Marker;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author hoornaert
 */
public class MapView extends ScrollPane implements EventHandler<MouseEvent> {
    
    private MapController _mapController;
    private ImageView _imageView;
    private List<Pin> _pins;
    
    
    public MapView(MapController mapController) {
        super();
        _mapController = mapController;
        _pins = new ArrayList<>();
        
        setImageView();
        initLayout();
        initEvent();
    }
    
    private void initEvent() {
        _imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }
    
    private void setImageView() {
        String imageUri = _mapController.getImagePath();
        _imageView = new ImageView(new Image(imageUri));
    }
    
    private void initLayout() {
        StackPane layout = Main.getStackPane();
        ObservableList<Node> children = layout.getChildren();
        children.add(_imageView);
        children.add(this);
        setupScrollPane();
    }
    
    private void setupScrollPane() {
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setPannable(true);
        setPrefSize(800, 600);
    }
    
    public void adaptToScene(ReadOnlyDoubleProperty property) {
        // bind the preferred size of the scroll area to the size of the scene.
        prefWidthProperty().bind(property);
        prefHeightProperty().bind(property);

        // center the scroll contents.
        setHvalue(getHmin()+(getHmax()-getHmin())/2);
        setVvalue(getVmin()+(getVmax()-getVmin())/2);
    }
    
    /** Returns the size of the map */
    public Coordinate getSize() {
        return new Coordinate((int) _imageView.getImage().getWidth(), (int) _imageView.getImage().getHeight());
    }
    
    public Pin createPin(Marker marker) {
        Pin newPin = new Pin(marker);
        _pins.add(newPin);
    
        getChildren().add(newPin);
        
        return newPin;
    }
    
    ///////// EVENT ///////// 
    
    @Override
    public void handle(MouseEvent event) {
        if(event.getButton().equals(MouseButton.SECONDARY)) {
            System.out.println("Clic on Map " +
                    "(" + event.getX()+ ", " + event.getY() + ")");
            _mapController.askForCreateMarker(event.getX(), event.getY());
        }
    }
    
    
}
