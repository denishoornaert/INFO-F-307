package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.app.FilterPanelController;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 *
 * @author hoornaert
 */
public class BasicFilterPanelView extends AbstractFilterPanelView {
    
    private TextField _pokemonNameFilterEntry;
    private ComboBox _pokemonTypeCombobox1;
    private ComboBox _pokemonTypeCombobox2;
    private CheckBox _notPokemonNameFilterEntry;
    private CheckBox _notPokemonTypeCombobox1;
    private CheckBox _notPokemonTypeCombobox2;
    private ToggleGroup _buttonGroup;
    private ToggleButton _andButton;
    private ToggleButton _orButton;

    public BasicFilterPanelView(FilterPanelController controller) {
        super(controller);
    }

    @Override
    protected void initFilterWidgets() {
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
    }

    @Override
    protected void placeFilterWidgets() {
        _vbox.getChildren().add(new HBox(_notPokemonNameFilterEntry, _pokemonNameFilterEntry));
        _vbox.getChildren().add(new HBox(_notPokemonTypeCombobox1, _pokemonTypeCombobox1));
        _vbox.getChildren().add(new HBox(_notPokemonTypeCombobox2, _pokemonTypeCombobox2));
        HBox operationsLine = new HBox(_andButton, _orButton);
        operationsLine.setAlignment(Pos.CENTER);
        operationsLine.setSpacing(20);
        _vbox.getChildren().addAll(operationsLine);
    }
    
    @Override
    protected void initApplyFilterButtonEvent() {
        _applyFilterButton.setOnAction(new EventHandler<ActionEvent>() {
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
    
    @Override
    protected void initExpressionToSaveButtonEvent() {
        _expressionToSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String expressionName = _nameOfTheExpressionToSave.getText();
                boolean notName = _notPokemonNameFilterEntry.isSelected();
                String name = _pokemonNameFilterEntry.getText();
                boolean notType1 = _notPokemonTypeCombobox1.isSelected();
                String type1 = (String) _pokemonTypeCombobox1.getValue();
                boolean notType2 = _notPokemonTypeCombobox2.isSelected();
                String type2 = (String) _pokemonTypeCombobox2.getValue();
                boolean andIsSelected = _andButton.isSelected();
                boolean orIsSelected = _orButton.isSelected();
                _controller.saveFilter(expressionName, notName, name, notType1, type1, notType2, type2, andIsSelected, orIsSelected);
            }
        });
    }

    @Override
    protected void initFilterStyle() {
        setXExpandPolicy(_pokemonNameFilterEntry, _pokemonTypeCombobox1, _pokemonTypeCombobox2);
    }
    
    public void setComboBoxesContent(ArrayList<String> pokemonTypes) {
        ObservableList<String> pokemonTypesList = FXCollections.observableArrayList(pokemonTypes);
        _pokemonTypeCombobox1.setItems(pokemonTypesList);
        _pokemonTypeCombobox2.setItems(pokemonTypesList);
    }
    
}
