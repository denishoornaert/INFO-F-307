package be.ac.ulb.infof307.g01.client.view;

import be.ac.ulb.infof307.g01.client.controller.UpdateMarkerPopUpController;
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
 * Pop-up which appeared when a user want to update/change a marker that he
 * already created
 * 
 * @author Groupe01
 */
public class UpdateMarkerPopUp extends AbstractMarkerPopUp {
    
    public UpdateMarkerPopUp(UpdateMarkerPopUpController controller, ArrayList<String> pokemonsName, int markerId) {
        super(controller, pokemonsName, markerId);
    }
    
    private Calendar getCalendar(Timestamp time) {
        Calendar calendar = Calendar.getInstance();
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
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    @Override
    protected void initComboBoxHour(int hour) {
        _dateHour = new ComboBox();
        Calendar calendar = getCalendar(_markerModel.getTimestamp());
        setComboBox(_dateHour, calendar.get(Calendar.HOUR), hour);
        _dateHour.setPromptText(Integer.toString(hour));
        
    }

    @Override
    protected void initComboBoxMinutes(int minute) {
        _dateMinute = new ComboBox();
        Calendar calendar = getCalendar(_markerModel.getTimestamp());
        setComboBox(_dateMinute, calendar.get(Calendar.MINUTE), minute);
        _dateMinute.setPromptText(Integer.toString(minute));
    }
    
    @Override
    protected void setDatePicker(DatePicker picker) {
        Timestamp date1 = _markerModel.getTimestamp();
        LocalDateTime date2 = date1.toLocalDateTime();
        LocalDate date3 = date2.toLocalDate();
        picker.setValue(date3);
    }
    
}
