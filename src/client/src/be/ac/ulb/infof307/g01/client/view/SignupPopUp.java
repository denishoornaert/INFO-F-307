/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nathan
 */
public class SignupPopUp extends PopUp{
    //private final SignupPopUpController _controller;
    private VBox _containerV;
    private HBox _containerH;
    private Label _usernameLabel;
    private Label _passwordLabel;
    private TextField _username;
    private PasswordField _password;
    private Button _submit;
    private Button _cancel;
    
    public SignupPopUp (/*SignupPopUpController controller*/){
        //_controller = controller;
        initWidgets();
        placeWidgets();
        setStyle();
        show();
    }
    
    private void initWidgets() {
        _containerV = new VBox();
        _containerH = new HBox();
        _usernameLabel = new Label("Username :");
        _passwordLabel = new Label("Password :");
        _submit = new Button("Submit");
        _cancel = new Button("Cancel");
        _username = new TextField();
        _password = new PasswordField();
    }
    
    private void placeWidgets() {
        _containerV.getChildren().addAll(_usernameLabel, _username, _passwordLabel, _password);
        _containerH.getChildren().addAll(_cancel,_submit);
        _containerV.getChildren().addAll(_containerH);
        super.add(_containerV);
    }
    
    private void setStyle() {
        
    }
    
}
