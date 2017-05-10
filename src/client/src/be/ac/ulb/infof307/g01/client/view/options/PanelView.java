package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.options.PanelController;
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
    private Label _userInfo;   
    private Label _emailLabel;
    private Label _emailInfo;
    private Button _signin;
    private Button _signup;
    private Separator _separation1;
    private Separator _separation2;
    private Separator _separation3;
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
        _separation3 = new Separator();
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
        _title2.setStyle("-fx-font-size:18px Tahoma");
        setXExpandPolicy(_signin);
        setXExpandPolicy(_signup);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(5);
    }
    
    private void initSignInButton() {
        _signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	_controller.openSignin();
            }
        });
        _signin.getStyleClass().add("primary");
    }
    
    private void initSignUpButton() {
        _signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	_controller.openSignUp();
            }
        });
        _signup.getStyleClass().add("primary");
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
        _userLabel = new Label("User : ");
        _userLabel.setPadding(new Insets(0, 0, 0, 8));
        _emailLabel = new Label("Email : ");
        _emailLabel.setPadding(new Insets(0, 0, 0, 8));
        _userInfo = new Label(username);
        _emailInfo = new Label(email);
        _userLabel.setStyle("-fx-font-size:12px Tahoma");
        _userInfo.setStyle("-fx-font-size:11px Tahoma");
        _emailLabel.setStyle("-fx-font-size:12px Tahoma");
        _emailInfo.setStyle("-fx-font-size:11px Tahoma");
        VBox box1 = new VBox();
        box1.getChildren().add(_userLabel);
        box1.setAlignment(Pos.BASELINE_LEFT);
        VBox box2 = new VBox();
        box2.getChildren().add(_emailLabel);
        box2.setAlignment(Pos.BASELINE_LEFT);
        getChildren().add(_title2);
        getChildren().add(box1);
        getChildren().add(_userInfo);
        getChildren().add(box2);
        getChildren().add(_emailInfo);
        getChildren().add(_separation3);
    }
    
}