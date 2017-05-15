package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.app.UserController;
import be.ac.ulb.infof307.g01.client.controller.options.PanelController;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

public class PanelView extends VBox {
    
    private Label _title1;
    private Label _title2;
    private Label _userLabel;
    private Label _userInfo;   
    private Label _emailLabel;
    private Label _emailInfo;
    private Button _signin;
    private Button _signup;
    private VBox _userBox;
    private VBox _emailBox;
    private ArrayList<Separator> separations;

    private final PanelController _controller;
    
    public PanelView(PanelController panel) {
        _controller = panel;
        
        initWidgets();
        placeWidgets();
        initStyle();
    }
    
    private void add(Node... nodes) {
        getChildren().addAll(nodes);
        separations.add(new Separator());
        getChildren().add(separations.get(separations.size()-1));
    }
    
    private void initWidgets(){
        _title1 = new Label("Connection");
        _title2 = new Label("Informations");
        _signin = new Button("Sign In");
        _signup = new Button("Sign Up");
        _userBox = new VBox();
        _emailBox = new VBox();
        separations = new ArrayList<>();
        initSignInButton();
        initSignUpButton();
    }
    
    private void placeWidgets(){
        add(_title1);
        add(_signin, _signup);
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
    
    private void initSignOutButton() {
        _signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	   UserController.getInstance().logout();
                   _signup.setDisable(false);
                   _signin.setText("Sign In");
                   removeUser();
                   initSignInButton();
            }
        });
        _signin.getStyleClass().add("primary");
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
        Insets defaultPadding = new Insets(0, 0, 0, 8);
        _userLabel = new Label("User : ");
        _userLabel.setPadding(defaultPadding);
        _emailLabel = new Label("Email : ");
        _emailLabel.setPadding(defaultPadding);
        _userInfo = new Label(username);
        _emailInfo = new Label(email);
        String titleFont = "-fx-font-size:12px Tahoma";
        String textFont = "-fx-font-size:11px Tahoma";
        _userLabel.setStyle(titleFont);
        _userInfo.setStyle(textFont);
        _emailLabel.setStyle(titleFont);
        _emailInfo.setStyle(textFont);
        _userBox = addAndDefinAlignementBaseLeft(_userLabel);
        _emailBox = addAndDefinAlignementBaseLeft(_emailLabel);
        _signup.setDisable(true);
        _signin.setText("Sign Out");
        initSignOutButton();
        add(_title2, _userBox, _userInfo, _emailBox, _emailInfo);
    }
    
    public void removeUser() {
        getChildren().removeAll(_title2,_userBox,_userInfo,_emailBox,_emailInfo,
                separations.get(separations.size()-1));
    }
    
    private VBox addAndDefinAlignementBaseLeft(Node node) {
        VBox box = new VBox();
        box.getChildren().add(node);
        box.setAlignment(Pos.BASELINE_LEFT);
        return box;
    }
    
    public void setWidget(FilterPanelView filterView) {
        add(filterView);
        Button reset = new Button("Reset");
        setXExpandPolicy(reset);
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	_controller.resetResearch();
            }
        });
        reset.getStyleClass().add("primary");
        add(reset);
    }
}