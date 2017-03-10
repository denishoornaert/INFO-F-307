/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.MapController;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public class NewMarkerPopUp extends PopUp {
    
    private VBox _vbox;
    private TextField _pokemonName;
    private DatePicker _dateMonthYear;
    private HBox _hbox;
    private ComboBox _dateHour;
    private ComboBox _dateMinute;
    private final int _hours = 24;
    private final int _minutes = 60;
    private Button _closeButton;
    
    public NewMarkerPopUp(MapController map) {
        super();
        setSize(250, 150);
        initWidget(map);
        placeWidgets();
        show();
    }

    private void initWidget(MapController map) {
        initTextField();
        // TODO set to current time
        initDatePicker();
        initComboBoxes();
        initCloseButton(map);
    }

    private void initTextField() {
        _pokemonName = new TextField();
        _pokemonName.setPromptText("Pokemon Name");
    }

    private void initDatePicker() {
        _dateMonthYear = new DatePicker();
    }
    
    private void initComboBoxes() {
        initComboBoxHour();
        initComboBoxMinutes();
    }
    
    private void initComboBoxHour() {
        _dateHour = new ComboBox();
        setComboBox(_dateHour, _hours);
        _dateHour.setPromptText("Hour");
    }

    private void initComboBoxMinutes() {
        _dateMinute = new ComboBox();
        setComboBox(_dateMinute, _minutes);
        _dateMinute.setPromptText("Min");
    }
    
    private void setComboBox(ComboBox combo, int limit) {
        ObservableList items = combo.getItems();
        for (int i = 1; i < limit+1; i++) {
            items.add(""+i);
        }
    }
    
    private Integer getSelection(ComboBox combobox) {
        Object object = combobox.getValue();
        String value = object.toString();
        return Integer.parseInt(value);
    }
    
    private Timestamp getSelectedTime() {
        LocalDate localDate = _dateMonthYear.getValue();
        Integer hours = getSelection(_dateHour);
        Integer minute = getSelection(_dateMinute);
        
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(localDate.getYear(), localDate.getMonth().getValue(), 
                localDate.getDayOfMonth(), hours, minute);
        
        Timestamp resTimestamp = new Timestamp(currentDate.getTimeInMillis());
        
        return resTimestamp;
    }
    
    private void initCloseButton(MapController map) {
        _closeButton = new Button("close");
        _closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                Timestamp selectedDate = getSelectedTime();
                map.endPopUpCreateMarker(_pokemonName.getText(), selectedDate);
                // Only when cancel (and not create marker)
                // map.cancelPopUpCreateMarker();
            }
        });
    }
    
    private void placeWidgets() {
        _vbox = new VBox();
        _hbox = new HBox();
        ObservableList<Node> childrenV = _vbox.getChildren();
        childrenV.add(_pokemonName);
        childrenV.add(_dateMonthYear);
        ObservableList<Node> childrenH = _hbox.getChildren();
        childrenH.add(_dateHour);
        childrenH.add(_dateMinute);
        childrenV.add(_hbox);
        childrenV.add(_closeButton);
        add(_vbox);
    }
    
}
