package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.options.SigninPopUpController;
import be.ac.ulb.infof307.g01.client.view.app.AbstractPopUp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

/**
 * Login with username and password
 * @author Groupe01
 */
public class SigninPopUp extends AbstractPopUp {
    
    private final SigninPopUpController _controller;
    private VBox _vbox;
    private HBox _hbox;
    private Label _username;
    private Label _password;
    private Button _submit;
    private Button _cancel;
    private TextField _usernameField;
    private PasswordField _passwordField;
    private Label _remarks;
    

    public SigninPopUp(final SigninPopUpController controller) {
        super(controller);
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
        _remarks = new Label("");
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
        _hbox.setSpacing(5);
        _vbox.getChildren().addAll(_hbox, _remarks);
        super.add(_vbox);
    }

    private void setStyle() {
        _vbox.setSpacing(10);
    }
    
    private void setXExpandPolicy(final Control control) {
        HBox.setHgrow(control, Priority.ALWAYS);
        control.setMaxWidth(150);
        control.setMaxHeight(10);
    }
    
    private void initLoginButton() {
        _submit = new Button("Submit");
        _submit.getStyleClass().add("primary");
        _submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent t) {
            	final String username = _usernameField.getText();
                final String password = _passwordField.getText();
                _controller.authenticate(username,password);
            }
        });
    }
    
    private void initCancelButton() {
        _cancel = getCloseButton("cancel", "danger");
    }
    
    public void showError(final String msg) {
        _remarks.setText(msg);
        _remarks.setTextFill(Color.web("#FF3333"));
    }
    
}
