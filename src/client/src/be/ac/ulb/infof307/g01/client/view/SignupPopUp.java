package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.SignupPopUpController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author Groupe01
 */
public class SignupPopUp extends PopUp {
    
    private final SignupPopUpController _controller;
    private VBox _containerV;
    private HBox _containerHTerms;
    private HBox _containerHClose;
    private Label _usernameLabel;
    private Label _emailLabel;
    private Hyperlink _termsAndConditionLabel;
    private TextField _username;
    private TextField _email;
    private CheckBox _termsAndConditionBox;
    private Label _passwordLabel;
    private PasswordField _password;
    private Button _submit;
    private Button _cancel;
    private Label _remarks;
    
    public SignupPopUp (SignupPopUpController controller) {
        super();
        _controller = controller;
        initWidgets();
        placeWidgets();
        setStyle();
        show();
    }
    
    private void initWidgets() {
        _containerV = new VBox();
        _containerHTerms = new HBox();
        _containerHClose = new HBox();
        _usernameLabel = new Label("Username :");
        _emailLabel = new Label("Email :");
        _termsAndConditionLabel = new Hyperlink("I accept the terms and conditions.");
        _passwordLabel = new Label("Password : ");
        _submit = new Button("Submit");
        _cancel = new Button("Cancel");
        _termsAndConditionBox = new CheckBox();
        _username = new TextField();
        _email = new TextField();
        _password = new PasswordField();
        _remarks = new Label();
        initCancelButton();
        initSubmitButton();
        initTermsAndConditionLabel();
    }
    
    private void placeWidgets() {
        _containerHTerms.getChildren().addAll(_termsAndConditionBox, _termsAndConditionLabel);
        _containerHClose.getChildren().addAll(_cancel,_submit);
        _containerV.getChildren().addAll(_emailLabel, _email, _usernameLabel, _username, _passwordLabel, _password);
        setXExpandPolicy(_cancel);
        setXExpandPolicy(_submit);
        _containerHClose.setAlignment(Pos.CENTER);
        _containerHClose.setPadding(new Insets(5, 0, 0, 0));
        _containerV.getChildren().addAll(_containerHTerms, _containerHClose);
        super.add(_containerV);
    }
    
    private void setStyle() {
        _containerV.setSpacing(5);
    }
    
    private void setXExpandPolicy(Control control) {
        HBox.setHgrow(control, Priority.ALWAYS);
        control.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initCancelButton() {
        _cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                _controller.cancel();
            }
        });
    }
    
    private void initSubmitButton() {
        _submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                try {
                    _controller.submit(_email.getText(),_username.getText(), _password.getText(), _termsAndConditionBox.isSelected());
                    _controller.cancel();
                } catch (IllegalArgumentException ex) {
                    Logger logger = Logger.getLogger(SignupPopUpController.class.getName());
                    logger.log(Level.WARNING,ex.getMessage());
                }
            }
        });
    }

    private void initTermsAndConditionLabel() {
        _termsAndConditionLabel.setOnAction((ActionEvent e) -> {
           _controller.openTermsAndconditionPopUp(); 
        });
    }

    public void showError(String msg) {
        _remarks.setText(msg);
        _containerV.getChildren().add(_remarks);
    }
}
