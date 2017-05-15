package be.ac.ulb.infof307.g01.client.view.options;

import be.ac.ulb.infof307.g01.client.controller.options.FilterPanelController;
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
 * Basic view of the filter panel.
 * This view allows the user to select graphical options that represent
 * simple yet useful filters.
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

    public BasicFilterPanelView(final FilterPanelController controller) {
        super(controller);
    }

    @Override
    protected void initFilterWidgets() {
        initDataInputs();
        initCheckBoxes();
        initButtons();
    }

    private void initDataInputs() {
        _pokemonNameFilterEntry = new TextField();
        _pokemonTypeCombobox1 = new ComboBox();
        _pokemonTypeCombobox2 = new ComboBox();
    }
    
    private void initCheckBoxes() {
        _notPokemonNameFilterEntry = new CheckBox();
        _notPokemonNameFilterEntry.setSelected(true);
        _notPokemonTypeCombobox1 = new CheckBox();
        _notPokemonTypeCombobox1.setSelected(true);
        _notPokemonTypeCombobox2 = new CheckBox();
        _notPokemonTypeCombobox2.setSelected(true);
    }
    
    private void initButtons() {
        _buttonGroup = new ToggleGroup();
        _andButton = new ToggleButton("AND");
        _andButton.setToggleGroup(_buttonGroup);
        _andButton.setSelected(true);
        _orButton = new ToggleButton("OR");
        _orButton.setToggleGroup(_buttonGroup);
    }
    
    @Override
    protected void placeFilterWidgets() {
        _vbox.getChildren().add(new HBox(_notPokemonNameFilterEntry, _pokemonNameFilterEntry));
        _vbox.getChildren().add(new HBox(_notPokemonTypeCombobox1, _pokemonTypeCombobox1));
        _vbox.getChildren().add(new HBox(_notPokemonTypeCombobox2, _pokemonTypeCombobox2));
        final HBox operationsLine = new HBox(_andButton, _orButton);
        operationsLine.setAlignment(Pos.CENTER);
        operationsLine.setSpacing(20);
        _vbox.getChildren().addAll(operationsLine);
    }
    
    @Override
    protected void initApplyFilterButtonEvent() {
        final BasicFilterPanelView currentInstance = this;
        _applyFilterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                final boolean notName = _notPokemonNameFilterEntry.isSelected();
                final String name = _pokemonNameFilterEntry.getText();
                final boolean notType1 = _notPokemonTypeCombobox1.isSelected();
                final String type1 = (String) _pokemonTypeCombobox1.getValue();
                final boolean notType2 = _notPokemonTypeCombobox2.isSelected();
                final String type2 = (String) _pokemonTypeCombobox2.getValue();
                final boolean andIsSelected = _andButton.isSelected();
                _controller.applyFilter(notName, name, notType1, type1, notType2, 
                        type2, andIsSelected, currentInstance);
            }
        });
    }
    
    @Override
    protected void saveFilter(final String expressionName) {
        final boolean notName = _notPokemonNameFilterEntry.isSelected();
        final String name = _pokemonNameFilterEntry.getText();
        final boolean notType1 = _notPokemonTypeCombobox1.isSelected();
        final String type1 = (String) _pokemonTypeCombobox1.getValue();
        final boolean notType2 = _notPokemonTypeCombobox2.isSelected();
        final String type2 = (String) _pokemonTypeCombobox2.getValue();
        final boolean andIsSelected = _andButton.isSelected();
        final boolean orIsSelected = _orButton.isSelected();
        _controller.saveFilter(expressionName, notName, name, notType1, 
                type1, notType2, type2, andIsSelected, orIsSelected);
    }

    @Override
    protected void initFilterStyle() {
        setXExpandPolicy(_pokemonNameFilterEntry, _pokemonTypeCombobox1, _pokemonTypeCombobox2);
    }
    
    public void setComboBoxesContent(final ArrayList<String> pokemonTypes) {
        final ObservableList<String> pokemonTypesList = FXCollections.observableArrayList(pokemonTypes);
        _pokemonTypeCombobox1.setItems(pokemonTypesList);
        _pokemonTypeCombobox2.setItems(pokemonTypesList);
    }
    
}
