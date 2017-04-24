/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.view;

import be.ac.ulb.infof307.g01.controller.Main;
import be.ac.ulb.infof307.g01.controller.PanelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author Groupe01
 */
public class PanelView extends VBox{
    private Label _title;
    private Button _signin;
    private Button _signup;
    private Separator _separation1;
    private Separator _separation2;
    private PanelController _controller;
    
    public PanelView(PanelController panel) {
        initWidgets();
        placeWidgets();
        initStyle();
        Main.getStackPane().setLeft(this);
        _controller = panel;
    }
    
    private void initWidgets(){
        _title = new Label("Connection");
        _separation1 = new Separator();
        _separation2 = new Separator();
        _signin = new Button("Sign In");
        _signup = new Button("Sign Up");
    }
    
    private void placeWidgets(){
        getChildren().add(_title);
        getChildren().add(_separation1);
        getChildren().add(_signin);
        getChildren().add(_signup);
        getChildren().add(_separation2);
    }
    
    private void initStyle(){
        _title.setStyle("-fx-font-size:20px Tahoma");
        _title.setPadding(new Insets(10,45,0,45));
        setXExpandPolicy(_signin);
        setXExpandPolicy(_signup);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(5);
    }
    
    /**
     * Set max width to widget
     * 
     * @param control the current gui element
     */
    private void setXExpandPolicy(Control control) {
        HBox.setHgrow(control, Priority.ALWAYS);
        control.setMaxWidth(185);
        control.setMaxHeight(5);
    }
    
}
