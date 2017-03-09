/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Coordinate;
import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.Marker;
import java.sql.Timestamp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author hoornaert
 */
public class PinPopUp extends ContextMenu {
    
    private HBox _hbox;
    private Label _pokemonName;
    private Label _date;
    private Button _closeButton;
    
    public PinPopUp(Marker marker) {
        initWidgets(marker);
        placeWidgets();
        initStyle();
    }
    
    private void initWidgets(Marker marker) {
        _hbox = new HBox();
        _pokemonName = new Label("Name : "+marker.getPokemonName());
        Timestamp date = marker.getTimestamp();
        _date = new Label("Date : "+date.toString());
    }
    
    private void placeWidgets() {
        ObservableList<Node> children = _hbox.getChildren();
        children.add(_pokemonName);
        children.add(_date);
        children.add(_closeButton);
    }

    private void initStyle() {
        setStyle("-fx-background-color: #2f4f4f;-fx-padding: 15;-fx-spacing: 10;");
    }
    
}
