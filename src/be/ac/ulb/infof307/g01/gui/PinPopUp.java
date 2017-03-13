/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Coordinate;
import be.ac.ulb.infof307.g01.Marker;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public class PinPopUp extends PopUp {
    
    private VBox _vbox;
    private Label _pokemonName;
    private Label _date;
    private Button _closeButton;
        
    public PinPopUp(Marker marker) {
        initPosition(marker);
        initWidgets(marker);
        placeWidgets();
        initCloseButtonEvent();
        _vbox.setSpacing(10);
        show();
    }
    
    private void initPosition(Marker marker) {
        Coordinate coord = marker.getCoordinate();
        setX(coord.getX());
        setY(coord.getY());
    }
    
    private void initWidgets(Marker marker) {
        _vbox = new VBox();
        _vbox.setAlignment(Pos.CENTER);
        _pokemonName = new Label("Name : "+marker.getPokemonName());
        Timestamp date = marker.getTimestamp();
        String formatingDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
        _date = new Label("Date : "+formatingDate);
        _closeButton = new Button("Close");
    }
    
    private void placeWidgets() {
        ObservableList<Node> children = _vbox.getChildren();
        children.add(_pokemonName);
        children.add(_date);
        children.add(_closeButton);
        add(_vbox);
    }

    private void initCloseButtonEvent() {
        _closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                close();
            }
        });
        _closeButton.getStyleClass().add("danger");
    }

}