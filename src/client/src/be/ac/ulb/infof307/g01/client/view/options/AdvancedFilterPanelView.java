package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.options.FilterPanelController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

/**
 * Advanced view of the filter panel.
 * This view allows the user to manually input filters with a specific
 * syntax in prefixed notation.
 */
public class AdvancedFilterPanelView extends AbstractFilterPanelView{
    
    private TextArea _advancedFilterTextArea;

    public AdvancedFilterPanelView(final FilterPanelController controller) {
        super(controller);
    }

    @Override
    protected void initFilterWidgets() {
        _advancedFilterTextArea = new TextArea();
    }

    @Override
    protected void placeFilterWidgets() {
        _vbox.getChildren().add(_advancedFilterTextArea);
        _vbox.setSpacing(10);
    }
    
    @Override
    protected void initApplyFilterButtonEvent() {
        final AdvancedFilterPanelView currentInstance = this;
        _applyFilterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                final String expression = _advancedFilterTextArea.getText();
                _controller.applyFilter(expression, currentInstance);
            }
        });
    }
    
    @Override
    protected void saveFilter(final String expressionName) {
        final String expression = _advancedFilterTextArea.getText();
        _controller.saveFilter(expressionName, expression);
    }

    @Override
    protected void initFilterStyle() {
        setXExpandPolicy(_advancedFilterTextArea);
        _advancedFilterTextArea.setWrapText(true);
    }
    
}
