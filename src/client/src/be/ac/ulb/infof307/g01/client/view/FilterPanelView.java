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
import javafx.scene.control.Label;
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
    private ComboBox _savedExpressionsComboBox;
        
    private final String _basicFilterLabel = "Basic filter";
    private TextField _pokemonNameFilterEntry;
    private ComboBox _pokemonTypeCombobox1;
    private ComboBox _pokemonTypeCombobox2;
    private CheckBox _notPokemonNameFilterEntry;
    private CheckBox _notPokemonTypeCombobox1;
    private CheckBox _notPokemonTypeCombobox2;
    private ToggleGroup _buttonGroup;
    private ToggleButton _andButton;
    private ToggleButton _orButton;
    private Button _expressionToSaveBasicButton;
    private Button _applyBasicFilterButton;
    private TextField _nameOfTheBasicExpressionToSave;
    private VBox _applySendFilterBasicBox;
    
    private final String _advancedFilterLabel = "Advanced filter";
    private TextArea _advancedFilterTextArea;
    private Button _expressionToSaveAdvancedButton;
    private Button _applyAdvancedFilterButton;
    private TextField _nameOfTheAdvancedExpressionToSave;
    private VBox _applySendFilterAdvancedBox;
    
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
    
    private void initWidgets() {
        initSavedExpressionTab();
        initBasicFilterTab();
        initAdvancedFiltertab();
    }

    private void initSavedExpressionTab() {
        _savedExpressionsComboBox = new ComboBox();
        setXExpandPolicy(_savedExpressionsComboBox);
    }

    private void initBasicFilterTab() {
        _pokemonNameFilterEntry = new TextField();
        _pokemonTypeCombobox1 = new ComboBox();
        _pokemonTypeCombobox2 = new ComboBox();
        _notPokemonNameFilterEntry = new CheckBox();
        _notPokemonTypeCombobox1 = new CheckBox();
        _notPokemonTypeCombobox2 = new CheckBox();
        _buttonGroup = new ToggleGroup();
        _andButton = new ToggleButton("AND");
        _andButton.setToggleGroup(_buttonGroup);
        _orButton = new ToggleButton("OR");
        _orButton.setToggleGroup(_buttonGroup);
        _expressionToSaveBasicButton = new Button("🖫");
        initApplyButtonBasic();
        _nameOfTheBasicExpressionToSave = new TextField();
        _applySendFilterBasicBox = initApplySaveFilterBox(_applyBasicFilterButton, _expressionToSaveBasicButton, _nameOfTheBasicExpressionToSave);
        setXExpandPolicy(_pokemonNameFilterEntry, _pokemonTypeCombobox1, _pokemonTypeCombobox2);
    }
    
    private void initApplyButtonBasic() {
        _applyBasicFilterButton = new Button("🔍");
        _applyBasicFilterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean notName = _notPokemonNameFilterEntry.isSelected();
                String name = _pokemonNameFilterEntry.getText();
                boolean notType1 = _notPokemonTypeCombobox1.isSelected();
                String type1 = (String) _pokemonTypeCombobox1.getValue();
                boolean notType2 = _notPokemonTypeCombobox2.isSelected();
                String type2 = (String) _pokemonTypeCombobox2.getValue();
                boolean andIsSelected = _andButton.isSelected();
                boolean orIsSelected = _orButton.isSelected();
                _controller.applyFilter(notName, name, notType1, type1, notType2, type2, andIsSelected, orIsSelected);
            }
        });
    }

    private void initAdvancedFiltertab() {
        _advancedFilterTextArea = new TextArea();
        _expressionToSaveAdvancedButton = new Button("🖫");
        initApplyButtonAdvanced();
        _nameOfTheAdvancedExpressionToSave = new TextField();
        _applySendFilterAdvancedBox = initApplySaveFilterBox(_applyAdvancedFilterButton, _expressionToSaveAdvancedButton, _nameOfTheAdvancedExpressionToSave);
        setXExpandPolicy(_advancedFilterTextArea);
    }
    
    private void initApplyButtonAdvanced() {
        _applyAdvancedFilterButton = new Button("🔍");
        _applyAdvancedFilterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String expression = _advancedFilterTextArea.getText();
                _controller.applyFilter(expression);
            }
        });
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
    
    private Tab placeSavedExpressionWidgets() {
        Tab newTab = new Tab(_savedExpressionLabel);
        VBox savedExpressions = new VBox();
        savedExpressions.getChildren().add(_savedExpressionsComboBox);
        savedExpressions.setSpacing(10);
        newTab.setContent(savedExpressions);
        newTab.setClosable(false);
        return newTab;
    }
    
    private Tab placeInitBasicFilterWidgets() {
        Tab newTab = new Tab(_basicFilterLabel);
        VBox basicFilterBox = new VBox();
        basicFilterBox.getChildren().add(new HBox(_notPokemonNameFilterEntry, _pokemonNameFilterEntry));
        basicFilterBox.getChildren().add(new HBox(_notPokemonTypeCombobox1, _pokemonTypeCombobox1));
        basicFilterBox.getChildren().add(new HBox(_notPokemonTypeCombobox2, _pokemonTypeCombobox2));
        HBox operationsLine = new HBox(_andButton, _orButton);
        operationsLine.setAlignment(Pos.CENTER);
        operationsLine.setSpacing(20);
        basicFilterBox.getChildren().addAll(operationsLine);
        basicFilterBox.getChildren().add(_applySendFilterBasicBox);
        basicFilterBox.setSpacing(10);
        newTab.setContent(basicFilterBox);
        newTab.setClosable(false);
        return newTab;
    }
    
    private Tab placeInitAdvancedFilterWidgets() {
        Tab newTab = new Tab(_advancedFilterLabel);
        VBox advancedFilterBox = new VBox();
        advancedFilterBox.getChildren().add(_advancedFilterTextArea);
        advancedFilterBox.getChildren().add(_applySendFilterAdvancedBox);
        advancedFilterBox.setSpacing(10);
        newTab.setContent(advancedFilterBox);
        newTab.setClosable(false);
        return newTab;
    }

    private void initStyle() {
        setSide(Side.LEFT);
    }
    
}
