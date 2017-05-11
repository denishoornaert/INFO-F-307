package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.app.FilterPanelController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public class SavedFilterPanelView extends Tab {
    
    private ComboBox _savedExpressionsComboBox;
    private FilterPanelController _controller;

    public SavedFilterPanelView() {
        initWidgets();
        placeWidgets();
        initStyle();
    }

    public SavedFilterPanelView(FilterPanelController controller) {
        _controller = controller;
        initWidgets();
        placeWidgets();
        initStyle();
    }

    private void initWidgets() {
        _savedExpressionsComboBox = new ComboBox();
    }

    private void placeWidgets() {
        VBox savedExpressions = new VBox();
        savedExpressions.getChildren().add(_savedExpressionsComboBox);
        savedExpressions.setSpacing(10);
        setContent(savedExpressions);
        setClosable(false);
    }
    
    private void initStyle() {
        setXExpandPolicy(_savedExpressionsComboBox);
    }
    
    private void setXExpandPolicy(Control... controls) {
        for(Control control : controls) {
            HBox.setHgrow(control, Priority.ALWAYS);
            control.setMaxWidth(208);
        }
    }
    
}
