package be.ac.ulb.infof307.g01.client.view.map;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import be.ac.ulb.infof307.g01.client.controller.map.MarkerCreationPopUpController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

/**
 * Popup used to create a new marker with custom information. 
 * This pop-up is used when the user click on the map.
 */
public class MarkerCreationPopUp extends AbstractMarkerChangePopUp {
    
    private static final int MAX_HOURS = 24;
    private static final int MAX_MINUTES = 60;
    
    public MarkerCreationPopUp(final MarkerCreationPopUpController controller,
            final ArrayList<String> pokemonsName) {
        super(controller, pokemonsName);
        hideTwitterButton();
    }
    
    @Override
    protected void initSpinners() {
        _lifeSpinner = createSpinner();
        _defenseSpinner = createSpinner();
        _attackSpinner = createSpinner();
    }
    
    @Override
    protected Spinner createSpinner() {
        final Spinner spin = new Spinner<>(0, Integer.MAX_VALUE, 0);
        spin.setPrefWidth(100);
        return spin;
    }
    
    @Override
    protected void initComboBoxPokemonName() {
        _pokemonName = new ComboBox();
        _pokemonName.setPromptText("Pokemon name");
        setComboBoxPokemonNameContent();
        initComboBoxPokemonNameEventItemSelected();
        setXExpandPolicy(_pokemonName);
    }
    
    @Override
    protected Calendar initCalendar() {
        final Date date = new Date();
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    @Override
    protected void initComboBoxHour(final int hour) {
        _dateHour = new ComboBox();
        setComboBox(_dateHour, MAX_HOURS, hour);
        _dateHour.setPromptText(Integer.toString(hour));
        
    }

    @Override
    protected void initComboBoxMinutes(final int minute) {
        _dateMinute = new ComboBox();
        setComboBox(_dateMinute, MAX_MINUTES, minute);
        _dateMinute.setPromptText(Integer.toString(minute));
    }
    
    @Override
    protected void setDatePicker(final DatePicker picker) {
        picker.setValue(LocalDate.now());
    }
    
}