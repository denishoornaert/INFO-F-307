package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.options.FilterPanelController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Abstract view of the filter panel.
 * The filter panel allows the user to use filters in order to select
 * which markers are displayed on the map.
 * This class is mainly a placeholder for all filter-specific widgets and
 * methods.
 */
public abstract class AbstractFilterPanelView extends Tab {
    
    protected FilterPanelController _controller;
    
    protected VBox _vbox;
    protected Button _expressionToSaveButton;
    protected Button _applyFilterButton;
    protected TextField _nameOfTheExpressionToSave;
    protected Label _remarks;

    public AbstractFilterPanelView(FilterPanelController controller) {
        super();
        _controller =controller;
        initWidgets();
        placeWidgets();
        initStyle();
    }
    
    protected void setXExpandPolicy(Control... controls) {
        for(Control control : controls) {
            HBox.setHgrow(control, Priority.ALWAYS);
            control.setMaxWidth(208);
        }
    }

    private void initWidgets() {
        _vbox = new VBox();
        initFilterWidgets();
        initBotomWidgets();
        _remarks = new Label("");
    }

    protected abstract void initFilterWidgets();
    
    private void initBotomWidgets() {
        _expressionToSaveButton = new Button("🖫");
        initExpressionToSaveButtonEvent();
        _applyFilterButton = new Button("🔍");
        initApplyFilterButtonEvent();
        _nameOfTheExpressionToSave = new TextField();
    }
    
    protected void initExpressionToSaveButtonEvent() {
        _expressionToSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String expressionName = _nameOfTheExpressionToSave.getText();
                saveFilter(expressionName);
            }
        });
    }
    
    protected abstract void saveFilter(String expressionName);
    protected abstract void initApplyFilterButtonEvent();
    
    protected void placeWidgets() {
        placeFilterWidgets();
        placeBottomWidgets();
        setContent(_vbox);
        setClosable(false);
    }

    protected abstract void placeFilterWidgets();
    
    private void placeBottomWidgets() {
        HBox saveBox = new HBox(_nameOfTheExpressionToSave, _expressionToSaveButton);
        _vbox.getChildren().addAll(saveBox, _applyFilterButton, _remarks);
    }
    
    private void initStyle() {
        initFilterStyle();
        initBottomStyle();
        _vbox.setSpacing(10);
    }

    protected abstract void initFilterStyle();

    private void initBottomStyle() {
        setXExpandPolicy(_nameOfTheExpressionToSave, _expressionToSaveButton, 
            _applyFilterButton);
    }
    
    public void showError(String msg) {
        _remarks.setText(msg);
        _remarks.setTextFill(Color.web("#FF3333"));
    }
}
