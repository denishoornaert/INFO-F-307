package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.FilterPanelController;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Groupe01
 */
public class FilterPanelView extends TabPane {
    
    private final String _savedExpressionLabel = "Saved filter";
    private ComboBox _savedExpressions;
    
    private VBox _expressionToSaveBox;
    private final String _expressionToSaveLabel = "Save";
    private TextField _nameOfTheExpressionToSave;
    private Button _expressionToSaveButton;
    
    private HBox _basicFilterBox;
    private final String _basicFilterLabel = "Basic filter";
    private TextField _pokemonNameFilterEntry;
    private ComboBox _pokemonTypeCombobox1;
    private ComboBox _pokemonTypeCombobox2;
    private CheckBox _notPokemonNameFilterEntry;
    private CheckBox _notPokemonTypeCombobox1;
    private CheckBox _notPokemonTypeCombobox2;
    private CheckBox _applyAndOperator;
    private CheckBox _applyOrOperator;
    
    private VBox _advancedFilterBox;
    private final String _advancedFilterlabel = "Advanced filter";
    private TextArea _advancedFilterTextArea;
    
    private Button _applyFilter;
    
    public FilterPanelView(FilterPanelController controller) {
        initWidgets();
        placeWidgets();
    }

    private void initWidgets() {
        _applyFilter = new Button("Apply");
        initSavedExpressionTab();
        initExpressionToSaveTab();
        initBasicFilterTab();
        initAdvancedFiltertab();
    }

    private void initSavedExpressionTab() {
        _savedExpressions = new ComboBox();
    }

    private void initExpressionToSaveTab() {
        _nameOfTheExpressionToSave = new TextField();
        _expressionToSaveButton = new Button("🖫");
    }

    private void initBasicFilterTab() {
        _pokemonNameFilterEntry = new TextField();
        _pokemonTypeCombobox1 = new ComboBox();
        _pokemonTypeCombobox2 = new ComboBox();
        _notPokemonNameFilterEntry = new CheckBox();
        _notPokemonTypeCombobox1 = new CheckBox();
        _notPokemonTypeCombobox2 = new CheckBox();
        _applyAndOperator = new CheckBox();
        _applyOrOperator = new CheckBox();
    }

    private void initAdvancedFiltertab() {
        _advancedFilterTextArea = new TextArea();
    }
    
    private void placeWidgets() {
        this.getTabs().add(placeSavedExpressionWidgets());
        this.getTabs().add(placeExpressionToSaveWidgets());
        this.getTabs().add(placeInitBasicFilterWidgets());
        this.getTabs().add(placeInitAdvancedFilterWidgets());
    }
    
    private Tab placeSavedExpressionWidgets() {
        Tab newTab = new Tab(_savedExpressionLabel);
        return newTab;
    }
    
    private Tab placeExpressionToSaveWidgets() {
        Tab newTab = new Tab(_expressionToSaveLabel);
        return newTab;
    }
    
    private Tab placeInitBasicFilterWidgets() {
        Tab newTab = new Tab(_basicFilterLabel);
        return newTab;
    }
    
    private Tab placeInitAdvancedFilterWidgets() {
        Tab newTab = new Tab(_advancedFilterlabel);
        return newTab;
    }
    
}
