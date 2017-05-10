package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.FilterPanelController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author Groupe01
 */
public class FilterPanelView extends TabPane {
    
    FilterPanelController _controller;
    
    private final String _savedExpressionLabel = "Saved filter";
    private final String _basicFilterLabel = "Basic filter";
    private final String _advancedFilterLabel = "Advanced filter";
    
    public FilterPanelView(FilterPanelController controller) {
        super();
        _controller = controller;
        initWidgets();
        placeWidgets();
        initStyle();
    }

    private void setXExpandPolicy(Control... controls) {
        for(Control control : controls) {
            HBox.setHgrow(control, Priority.ALWAYS);
            control.setMaxWidth(208);
        }
    }
    
    // TODO split the method @Denis
    private VBox initApplySaveFilterBox(Button applyFilterButton, Button expressionToSaveButton, TextField nameOfTheExpressionToSave) {
        VBox applySendFilterBox = new VBox();
        setXExpandPolicy(nameOfTheExpressionToSave, expressionToSaveButton, applyFilterButton);
        HBox saveBox = new HBox(nameOfTheExpressionToSave, expressionToSaveButton);
        applySendFilterBox.getChildren().addAll(saveBox, applyFilterButton);
        return applySendFilterBox;
    }
    
    private void placeWidgets() {
        this.getTabs().add(placeSavedExpressionWidgets());
        this.getTabs().add(placeInitBasicFilterWidgets());
        this.getTabs().add(placeInitAdvancedFilterWidgets());
    }

    private void initStyle() {
        setSide(Side.LEFT);
    }
    
}
