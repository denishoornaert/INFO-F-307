/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.MarkerController;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public class PinPopUp extends PopUp {
    
    private VBox _vbox;
    private Label _pokemonName;
    private Label _date;
    private Label _attackLabel;
    private Label _defenseLabel;
    private Label _lifeLabel;
    
    private HBox _voteBox;
    private Label _voteScoreLabel;
    private Button _upVoteButton;
    private Button _downVoteButton;
    
    private Button _closeButton;
        
    public PinPopUp(MarkerController marker) {
        initPosition(marker);
        initWidgets(marker);
        placeWidgets();
        initCloseButtonEvent();
        _vbox.setSpacing(10);
        _voteBox.setSpacing(10);
        show();
    }
    
    private void initPosition(MarkerController marker) {
        setX(marker.getLatitude());
        setY(marker.getLongitude());
    }
    
    private void initWidgets(MarkerController marker) {
        _vbox = new VBox();
        _vbox.setAlignment(Pos.CENTER);
        _pokemonName = new Label("Name : "+marker.getPokemonName());
        Timestamp date = marker.getTimestamp();
        String formatingDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
        _date = new Label("Date : "+formatingDate);
        _closeButton = new Button("Close");
        
        _voteBox = new HBox();
        _voteBox.setAlignment(Pos.CENTER);
        _lifeLabel = new Label("Life : " + marker.getPokemonLife());
        _attackLabel = new Label("Attack : " + marker.getPokemonAttack());
        _defenseLabel = new Label("Defense : " + marker.getPokemonDefense());
        
        _voteScoreLabel = new Label("Votes : " + marker.getVoteScore());
        _downVoteButton = new Button("👎");
        _downVoteButton.getStyleClass().add("primary");
        _upVoteButton = new Button("👍");
        _upVoteButton.getStyleClass().add("primary");
    }
    
    private void placeWidgets() {
        ObservableList<Node> children = _voteBox.getChildren();
        children.addAll(_downVoteButton, _voteScoreLabel, _upVoteButton);
        children = _vbox.getChildren();
        children.addAll(_pokemonName, _date, _lifeLabel, _attackLabel, _defenseLabel, _voteBox, _closeButton);
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
