package be.ac.ulb.infof307.g01.client.view.map;

import be.ac.ulb.infof307.g01.client.controller.map.AbstractMarkerChangePopUpController;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Abstract class that will serve as a template of Pop-Up over a PokemonMaker for
 * both its creation and update (by the user who created it) as they share the
 * same layout and information
 * 
 * @author Groupe01
 */
public abstract class AbstractMarkerChangePopUp extends AbstractMarkerPopUp {
    
    protected ComboBox _pokemonName;
    protected DatePicker _dateMonthYear;
    protected ComboBox _dateHour;
    protected ComboBox _dateMinute;
    private Label _dateHourLabel;
    private Label _dateMinuteLabel;
    protected Spinner<Integer> _lifeSpinner;
    protected Spinner<Integer> _attackSpinner;
    protected Spinner<Integer> _defenseSpinner;
    private Label _lifeLabel;
    private Label _attackLabel;
    private Label _defenseLabel;
    private Button _closeButton;
    private Button _okButton;
    
    private static final int DEFAULT_MARKER_ID = 0;
    private static String DATE_HOUR_LABEL_STR = " h ";
    private static String DATE_MINUTE_LABEL_STR = "min";
    private static String LIFE_LABEL_STR = "Life";
    private static String ATTACK_LABEL_STR = "Attack";
    private static String DEFENSE_LABEL_STR = "Defense";
    
    private final ArrayList<String> _pokemonNames;
    protected final AbstractMarkerChangePopUpController _controller;
    
    public AbstractMarkerChangePopUp(final AbstractMarkerChangePopUpController controller, 
            final ArrayList<String> pokemonsName) {
        this(controller, pokemonsName, DEFAULT_MARKER_ID); 
    }
    
    public AbstractMarkerChangePopUp(final AbstractMarkerChangePopUpController controller, 
            final ArrayList<String> pokemonsName, final int markerId) {
        super(controller, markerId);
        _pokemonNames = new ArrayList<>(pokemonsName);
        Collections.sort(_pokemonNames);
        _controller = controller;
        initWidget();
        placeWidgets();
        show();
    }

    private void initWidget() {
        initDatePicker();
        initSpinners();
        initComboBoxes();
        initLabels();
        initCloseButton();
        initokButton();
    }
    
    protected void initDatePicker() {
        _dateMonthYear = new DatePicker();
        setDatePicker(_dateMonthYear);
        setXExpandPolicy(_dateMonthYear);
    }
    
    protected abstract void initSpinners();
    
    private int getSpinnerValue(final Spinner spin) {
        return (Integer) spin.getValue();
    }
        
    protected Spinner createSpinner() {
        final Spinner spin = new Spinner<>(0, Integer.MAX_VALUE, 0);
        spin.setPrefWidth(100);
        return spin;
    }
    
    private void initComboBoxes() {
        initComboBoxPokemonName();
        final Calendar calendar = initCalendar();
        initComboBoxHour(calendar.get(Calendar.HOUR_OF_DAY));
        initComboBoxMinutes(calendar.get(Calendar.MINUTE));
    }
    
    protected abstract void initComboBoxHour(int value);
    
    protected abstract void initComboBoxMinutes(int value);
    
    private void initLabels() {
        _dateHourLabel = new Label(DATE_HOUR_LABEL_STR);
        _dateMinuteLabel = new Label(DATE_MINUTE_LABEL_STR);
        _lifeLabel = new Label(LIFE_LABEL_STR);
        _attackLabel = new Label(ATTACK_LABEL_STR);
        _defenseLabel = new Label(DEFENSE_LABEL_STR);
    }
    
    
    protected void initComboBoxPokemonName() {
        _pokemonName = new ComboBox<>();
        _pokemonName.setPromptText("Pokemon name");
        setComboBoxPokemonNameContent();
        initComboBoxPokemonNameEventItemSelected();
        setXExpandPolicy(_pokemonName);
    }
    
    protected void setComboBoxPokemonNameContent() {
        _pokemonName.getItems().addAll(_pokemonNames);

    }
    
    protected void initComboBoxPokemonNameEventItemSelected() {
        _pokemonName.setOnAction((event) -> {
            final Object object = _pokemonName.getSelectionModel().getSelectedItem();
            if (object != null) {
                _controller.selectPokemon(object.toString());
            }
        });
    }
    
    protected Calendar initCalendar() {
        final Date date = new Date();
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    protected void setComboBox(final ComboBox combo, final int limit,
            final int value) {
        final ObservableList items = combo.getItems();
        for (int i = 1; i < limit+1; i++) {
            items.add(""+i);
        }
        if (value != -1) combo.setValue(value);
        setXExpandPolicy(combo);
    }
    
    protected abstract void setDatePicker(DatePicker picker);
    
    private Integer getSelection(final ComboBox combobox) {
        final Object object = combobox.getValue();
        final String value = object.toString();
        return Integer.parseInt(value);
    }
    
    private String getSelectedString(final ComboBox combobox) {
        final Object object = combobox.getValue();
        String res = "";
        if(object != null) {
            res = object.toString();
        }
        return res;
    }
    
    private Timestamp getSelectedTime() {
        final LocalDate localDate = _dateMonthYear.getValue();
        final Integer hours = getSelection(_dateHour);
        final Integer minute = getSelection(_dateMinute);
        final Calendar currentDate = Calendar.getInstance();
        // offset value for month
        // Calendar class starts at 0
        currentDate.set(localDate.getYear(), localDate.getMonthValue()-1, 
                localDate.getDayOfMonth(), hours, minute, 0);
        final Timestamp resTimestamp = new Timestamp(currentDate.getTimeInMillis());
        return resTimestamp;
    }
    
    private void initokButton() {
        _okButton = new Button("save");
        _okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent t) {
                final Timestamp selectedDate = getSelectedTime();
                final String name = getSelectedString(_pokemonName);
                final int life = getSpinnerValue(_lifeSpinner);
                final int attack = getSpinnerValue(_attackSpinner);
                final int defense = getSpinnerValue(_defenseSpinner);
                _controller.endPopUpMarker(name, life, attack, defense, selectedDate);
                // Only when cancel (and not create marker)
                // map.cancelPopUpCreateMarker();
            }
        });
        _okButton.getStyleClass().add("primary");
        setXExpandPolicy(_okButton);
    }
    
    private void initCloseButton() {
        _closeButton = getCloseButton("Cancel", "danger");
        setXExpandPolicy(_closeButton);
    }
    
    private void placeWidgets() {
        final HBox boxDates = placeInRow(_dateHour, _dateHourLabel, _dateMinute, 
                _dateMinuteLabel);
        final VBox boxLife = placeInColumn(_lifeLabel, _lifeSpinner);
        final VBox boxAttack = placeInColumn(_attackLabel, _attackSpinner);
        final VBox boxDefense = placeInColumn(_defenseLabel, _defenseSpinner);
        final HBox boxStats = placeInRow(boxLife, boxAttack, boxDefense);
        final HBox boxButtons = placeInRow(_closeButton, _okButton);
        final VBox boxDataEntries = placeInColumn(_pokemonName, boxStats,
               _dateMonthYear, boxDates, boxButtons);
        super.add(boxDataEntries);
    }

    public void errorInPokemonName() {
        _pokemonName.setPromptText("Please select an item");
    }
    
}
