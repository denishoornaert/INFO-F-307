package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.controller.PanelController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Label _title1;
    private Label _title2;
    private Label _userLabel;
    private Label _emailLabel;
    private Label _location;
    private Button _signin;
    private Button _signup;
    private Separator _separation1;
    private Separator _separation2;
    private PanelController _controller;
    
    public PanelView(PanelController panel) {
        _controller = panel;
        
        initWidgets();
        placeWidgets();
        initStyle();
    }
    
    private void initWidgets(){
        _title1 = new Label("Connection");
        _title2 = new Label("Informations");
        _separation1 = new Separator();
        _separation2 = new Separator();
        _signin = new Button("Sign In");
        _signup = new Button("Sign Up");
        initSignInButton();
        initSignUpButton();
    }
    
    private void placeWidgets(){
        getChildren().add(_title1);
        getChildren().add(_separation1);
        getChildren().add(_signin);
        getChildren().add(_signup);
        getChildren().add(_separation2);
    }
    
    private void initStyle(){
        _title1.setStyle("-fx-font-size:20px Tahoma");
        _title1.setPadding(new Insets(10,45,0,45));
        setXExpandPolicy(_signin);
        setXExpandPolicy(_signup);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(5);
    }
    
    private void initSignInButton() {
        _signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	_controller.openLogin();
            }
        });
    }
    
    private void initSignUpButton() {
        _signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	_controller.openSignUp();
            }
        });
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
    
    public void setUser(String username, String email) {
        _userLabel = new Label("User : "+username);
        _emailLabel = new Label("Email : "+email);
        _location = new Label("Your location");
        getChildren().addAll(_title2,_userLabel,_emailLabel,_location);
    }
    
}
