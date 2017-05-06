package be.ac.ulb.infof307.g01.client.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import be.ac.ulb.infof307.g01.client.controller.NewMarkerPopUpController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

/**
 * Marker Pop-up for a new created Marker, must define the hours and minutes
 * when the pokemon was met
 * 
 * @author Groupe01
 */
public class NewMarkerPopUp extends AbstractMarkerPopUp {
    
    private static final int MAX_HOURS = 24;
    private static final int MAX_MINUTES = 60;
    
    public NewMarkerPopUp(NewMarkerPopUpController controller, ArrayList<String> pokemonsName) {
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
        Spinner spin = new Spinner<>(0, Integer.MAX_VALUE, 0);
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
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    @Override
    protected void initComboBoxHour(int hour) {
        _dateHour = new ComboBox();
        setComboBox(_dateHour, MAX_HOURS, hour);
        _dateHour.setPromptText(Integer.toString(hour));
        
    }

    @Override
    protected void initComboBoxMinutes(int minute) {
        _dateMinute = new ComboBox();
        setComboBox(_dateMinute, MAX_MINUTES, minute);
        _dateMinute.setPromptText(Integer.toString(minute));
    }
    
    @Override
    protected void setDatePicker(DatePicker picker) {
        picker.setValue(LocalDate.now());
    }
    
}