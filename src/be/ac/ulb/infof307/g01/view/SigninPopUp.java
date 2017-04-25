package be.ac.ulb.infof307.g01.view;

import be.ac.ulb.infof307.g01.controller.SigninPopUpController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

/**
 * Login with username and password
 * @author Groupe01
 */
public class SigninPopUp extends PopUp {
    private final SigninPopUpController _controller;
    private VBox _vbox;
    private Label _message;
    private Button _loginButton;
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
        initMessageLabel();
        initCloseButton();
        _usernameField = new TextField();
        _passwordField = new PasswordField();
    }
    
    private void initMessageLabel() {
        _message = new Label("Enter your username, please.");
        _message.setMaxWidth(Region.USE_PREF_SIZE);
        _message.setWrapText(true);
    }
    
    private void initCloseButton() {
        _loginButton = getCloseButton("Login", "primary");
        _loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
            	tryLogin();
            }
        });
    }
    
    private void tryLogin() {
    	final String username = _usernameField.getText();
        final String password = _passwordField.getText();
    	try {
    		_controller.authenticate(username,password);
            close();
    	} catch(IllegalArgumentException exception) {
    		_message.setText(exception.getMessage());
    	}
    }

    private void placeWidgets() {
        _vbox.getChildren().addAll(_message, _usernameField,_passwordField, _loginButton);
        super.add(_vbox);
    }

    private void setStyle() {
        _vbox.setAlignment(Pos.CENTER);
        _vbox.setSpacing(10);
    }
}
