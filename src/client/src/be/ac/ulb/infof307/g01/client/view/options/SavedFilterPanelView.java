package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.options.FilterPanelController;
import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Saved view of the filter panel.
 * This view allows the user to select filters that were previously saved
 * by him or other users.
 */
public class SavedFilterPanelView extends Tab {
    
    private ComboBox _savedExpressionsComboBox;
    private FilterPanelController _controller;

    public SavedFilterPanelView(FilterPanelController controller) {
        _controller = controller;
        initWidgets();
        placeWidgets();
        initStyle();
    }

    private void initWidgets() {
        _savedExpressionsComboBox = new ComboBox();
        initSavedExpressionComboBoxEvent();
    }

    private void placeWidgets() {
        VBox savedExpressions = new VBox();
        savedExpressions.getChildren().add(_savedExpressionsComboBox);
        savedExpressions.setSpacing(10);
        setContent(savedExpressions);
        setClosable(false);
    }
    
    private void initSavedExpressionComboBoxEvent() {
        _savedExpressionsComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object object = _savedExpressionsComboBox.getSelectionModel().getSelectedItem();
                if (object != null) {
                    _controller.applyFilterByName((String)object, null);
                }
            }
        });
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
    
    public void setSavedFilters(Collection<String> filtersName) {
        _savedExpressionsComboBox.getItems().addAll(filtersName);
    }
    
}
