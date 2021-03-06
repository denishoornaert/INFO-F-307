package be.ac.ulb.infof307.g01.client.view.map;

import be.ac.ulb.infof307.g01.client.controller.map.MarkerUpdatePopUpController;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * Popup used to display a marker's informations and allow the user to modify them. 
 * This pop-up is used when the user click on a marker that he created.
 */
public class MarkerUpdatePopUp extends AbstractMarkerChangePopUp {
    
    public MarkerUpdatePopUp(final MarkerUpdatePopUpController controller,
            final ArrayList<String> pokemonsName, final int markerId) {
        super(controller, pokemonsName, markerId);
    }
    
    private Calendar getCalendar(final Timestamp time) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());
        return calendar;
    }
    
    @Override
    protected void initSpinners() {
        _lifeSpinner = createSpinner();
        _lifeSpinner.getValueFactory().setValue(_markerModel.geMarkerLife());
        _defenseSpinner = createSpinner();
        _defenseSpinner.getValueFactory().setValue(_markerModel.getMarkerDefense());
        _attackSpinner = createSpinner();
        _attackSpinner.getValueFactory().setValue(_markerModel.getMarkerAttack());
    }
        
    @Override
    protected void initComboBoxPokemonName() {
        _pokemonName = new ComboBox();
        _pokemonName.setPromptText("Pokemon name");
        _pokemonName.setValue(_markerModel.getPokemonName());
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
    
    /**
     * Factorize the initialization code between initComboBoxHour and
     * initComboBoxMinutes.
     * @param timeWidget The comboBox to initialize
     * @param value The value to put in the comboBox
     * @param limit The maximum value the comboBox can have, as an enum value
     * in Calendar.
     */
    private void initComboBoxTime(final ComboBox timeWidget, final int limit,
            final int value) {
        final Calendar calendar = getCalendar(_markerModel.getTimestamp());
        setComboBox(timeWidget, calendar.get(limit), value);
        timeWidget.setPromptText(Integer.toString(value));
    }
    
    @Override
    protected void initComboBoxHour(final int hour) {
        _dateHour = new ComboBox();
        initComboBoxTime(_dateHour, Calendar.HOUR, hour);
        
    }

    @Override
    protected void initComboBoxMinutes(final int minute) {
        _dateMinute = new ComboBox();
        initComboBoxTime(_dateMinute, Calendar.MINUTE, minute);
    }
    
    @Override
    protected void setDatePicker(final DatePicker picker) {
        final Timestamp date1 = _markerModel.getTimestamp();
        final LocalDateTime date2 = date1.toLocalDateTime();
        final LocalDate date3 = date2.toLocalDate();
        picker.setValue(date3);
    }
    
}
