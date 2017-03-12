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
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author hoornaert
 */
public class PinPopUp extends PopUp {
    
    private Label _pokemonLabel;
    private Label _dateLabel;
    private Label _hourLabel;
    private Label _pokemon;
    private Label _date;
    private Label _hour;
    private Button _closeButton;
        
    public PinPopUp(Marker marker) {
        initPosition(marker);
        initWidgets(marker);
        placeWidgets();
        initCloseButtonEvent();
        show();
    }
    
    private void initPosition(Marker marker) {
        Coordinate coord = marker.getCoordinate();
        setX(coord.getX());
        setY(coord.getY());
    }
    
    private void initWidgets(Marker marker) {
        Timestamp timestamp = marker.getTimestamp();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(timestamp);
        String hour = new SimpleDateFormat("HH:mm").format(timestamp);
        
        _pokemonLabel = new Label("Name");
        _pokemon = new Label(marker.getPokemonName());
        _dateLabel = new Label("Date");
        _date = new Label(date);
        _hourLabel = new Label("Hour");
        _hour = new Label(hour);
        _closeButton = new Button("Close");
    }
    
    private void placeWidgets() {
        add(_pokemonLabel,0,0,1,1);
        add(_pokemon,1,0,1,1);
        add(_dateLabel,0,1,1,1);
        add(_date,1,1,1,1);
        add(_hourLabel,0,2,1,1);
        add(_hour,1,2,1,1);
        add(_closeButton,0,3,2,1);
        setConstraints();
    }
    
    private void setConstraints() {
        ArrayList<ColumnConstraints> col = new ArrayList<>();
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(40);
        col.add(column0);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        col.add(column2);
        ArrayList<RowConstraints> row = new ArrayList<>();
        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(25);
        row.add(row0);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(25);
        row.add(row1);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25);
        row.add(row2);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(25);
        row.add(row3);
        addConstraints(col,row);
    }

    private void initCloseButtonEvent() {
        _closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                close();
            }
        });
    }

}
