package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.app.FilterPanelController;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public abstract class AbstractFilterPanelView extends Tab {
    
    protected FilterPanelController _controller;
    
    protected VBox _vbox;
    protected Button _expressionToSaveButton;
    protected Button _applyFilterButton;
    protected TextField _nameOfTheExpressionToSave;

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
        _vbox =new VBox();
        initFilterWidgets();
        initBotomWidgets();
    }

    protected abstract void initFilterWidgets();
    
    private void initBotomWidgets() {
        _expressionToSaveButton = new Button("🖫");
        initExpressionToSaveButtonEvent();
        _applyFilterButton = new Button("🔍");
        initApplyFilterButtonEvent();
        _nameOfTheExpressionToSave = new TextField();
    }
    
    protected abstract void initExpressionToSaveButtonEvent();
    
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
        _vbox.getChildren().addAll(saveBox, _applyFilterButton);
    }
    
    private void initStyle() {
        initFilterStyle();
        initBottomStyle();
        _vbox.setSpacing(10);
    }

    protected abstract void initFilterStyle();

    private void initBottomStyle() {
        setXExpandPolicy(_nameOfTheExpressionToSave, _expressionToSaveButton, _applyFilterButton);
    }
    
}
