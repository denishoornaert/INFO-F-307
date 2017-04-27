package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.SigninPopUpController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Login with username and password
 * @author Groupe01
 */
public class SigninPopUp extends PopUp {
    private final SigninPopUpController _controller;
    private VBox _vbox;
    private HBox _hbox;
    private Label _username;
    private Label _password;
    private Button _submit;
    private Button _cancel;
    private TextField _usernameField;
    private PasswordField _passwordField;
    

    public SigninPopUp(SigninPopUpController controller) {
        super();
	_controller = controller;
        initWidgets();
        placeWidgets();
        setStyle();
        show();
    }
    
    private void initWidgets() {
        _vbox = new VBox();
        _hbox = new HBox();
        _username = new Label("Username :");
        _password = new Label("Password :");
        _usernameField = new TextField();
        _passwordField = new PasswordField();
        initLoginButton();
        initCancelButton();
    }
    
    private void placeWidgets() {
        _vbox.getChildren().addAll(_username, _usernameField, _password, _passwordField);
        _hbox.getChildren().addAll(_cancel,_submit);
        setXExpandPolicy(_cancel);
        setXExpandPolicy(_submit);
        _hbox.setAlignment(Pos.CENTER);
        _hbox.setPadding(new Insets(5, 0, 0, 0));
        _vbox.getChildren().addAll(_hbox);
        super.add(_vbox);
    }

    private void setStyle() {
        _vbox.setAlignment(Pos.CENTER);
        _vbox.setSpacing(10);
    }
    
    private void setXExpandPolicy(Control control) {
        HBox.setHgrow(control, Priority.ALWAYS);
        control.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initLoginButton() {
        _submit = new Button("Submit");
        _submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	final String username = _usernameField.getText();
                final String password = _passwordField.getText();
                _controller.authenticate(username,password);
                _controller.cancel();
            }
        });
    }
    
    private void initCancelButton() {
        _cancel = new Button("Cancel");
        _cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	_controller.cancel();
            }
        });
    }
    
    
}
