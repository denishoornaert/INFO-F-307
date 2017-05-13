package be.ac.ulb.infof307.g01.client.view.app;

import be.ac.ulb.infof307.g01.client.Main;
import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Minimal abstract class defining the default popup layout and scene.
 */
public abstract class AbstractPopUp extends Stage {

    private final StackPane _layout;
    private final Scene _scene;
    
    private final PopUpController _controller;
    
    public AbstractPopUp() throws InstantiationException {
        this(new PopUpController());
    }
    
    public AbstractPopUp(PopUpController controller) {
        super.initStyle(StageStyle.TRANSPARENT);
        _layout = new StackPane();
        _scene = new Scene(_layout);
        _layout.setPrefSize(250, 150);
        _scene.setFill(Color.TRANSPARENT);
        setScene(_scene);
        _controller = controller;
        initOwner(Main.getStage());
        initStyle();
    }
    
    /**
     * Creates and returns the JavaFX Button used to close the popup.
     * @param text the text displayed in the button widget
     * @param style the style of the button widget
     * @return the created button
     */
    protected Button getCloseButton(String text, String style) {
        Button button = new Button(text);
        AbstractPopUp instance = this;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                _controller.close(instance);
            }
        });
        button.getStyleClass().add(style);
        return button;
    }
    
    /**
     * Adds a JavaFX node to the popup layout.
     * @param node the Node to add
     */
    protected void add(Node node) {
        ObservableList<Node> children = _layout.getChildren();
        children.add(node);        
    }

    private void initStyle() {
        _scene.getStylesheets().add(ClientConfiguration.getInstance().getStyleFileName());
        _layout.setStyle(""
                + "-fx-background-color: #d2d7dd;"
                + "-fx-padding: 10;-fx-spacing: 5;"
                + "-fx-background-radius: 5 5 5 5;"
                + "-fx-border-radius: 5 5 5 5;"
        );
    }

    protected void setSize(int x, int y) {
        _layout.setPrefSize(x, y);
    }
    
}
