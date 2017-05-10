package be.ac.ulb.infof307.g01.client.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

/**
 *
 * @author hoornaert
 */
public class AdvancedFilterPanelView extends AbstractFilterPanelView{
    
    private TextArea _advancedFilterTextArea;

    public AdvancedFilterPanelView() {
        super();
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
        _applyFilterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String expression = _advancedFilterTextArea.getText();
                _controller.applyFilter(expression);
            }
        });
    }
    
    @Override
    protected void initExpressionToSaveButtonEvent() {
        //
    }

    @Override
    protected void initFilterStyle() {
        setXExpandPolicy(_advancedFilterTextArea);
    }
    
}
