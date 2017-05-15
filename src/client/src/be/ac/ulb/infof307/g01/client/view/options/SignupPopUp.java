package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.options.SignupPopUpController;
import be.ac.ulb.infof307.g01.client.view.app.AbstractPopUp;
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
import javafx.scene.paint.Color;

/**
 *
 * @author Groupe01
 */
public class SignupPopUp extends AbstractPopUp {
    
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
    
    public SignupPopUp (final SignupPopUpController controller) {
        super(controller);
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
        _termsAndConditionBox = new CheckBox();
        _username = new TextField();
        _email = new TextField();
        _password = new PasswordField();
        _remarks = new Label("");
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
        _containerHClose.setSpacing(5);
        _containerHClose.setPadding(new Insets(5, 0, 0, 0));
        _containerV.getChildren().addAll(_containerHTerms, _containerHClose, _remarks);
        super.add(_containerV);
    }
    
    private void setStyle() {
        _containerV.setSpacing(5);
    }
    
    private void setXExpandPolicy(final Control control) {
        HBox.setHgrow(control, Priority.ALWAYS);
        control.setMaxWidth(150);
        control.setMaxHeight(10);
    }
    
    private void initCancelButton() {
        _cancel = getCloseButton("Cancel", "danger");
    }
    
    private void initSubmitButton() {
        _submit = new Button("Submit");
        _submit.getStyleClass().add("primary");
        _submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent t) {
                final String username = _username.getText();
                final String password = _password.getText();
                final String email = _email.getText();
                final boolean terms = _termsAndConditionBox.isSelected();
                _controller.submit(email, username, password, terms);
            }
        });
    }

    private void initTermsAndConditionLabel() {
        _termsAndConditionLabel.setOnAction((ActionEvent e) -> {
           _controller.openTermsAndconditionPopUp(); 
        });
    }

    public void showError(final String msg) {
        _remarks.setText(msg);
        _remarks.setTextFill(Color.web("#FF3333"));
    }
}
