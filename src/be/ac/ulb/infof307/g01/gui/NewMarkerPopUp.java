/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.MapController;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author hoornaert
 */
public class NewMarkerPopUp extends PopUp {
    
    private TextField _pokemonName;
    private DatePicker _dateMonthYear;
    private ComboBox _dateHour;
    private ComboBox _dateMinute;
    private Button _cancelButton;
    private Button _saveButton;
    private Label _nameLabel;
    private Label _dateLabel;
    private Label _hourLabel;
    
    private final int _hours = 24;
    private final int _minutes = 60;
    
    
    public NewMarkerPopUp(MapController map) {
        super();
        setSize(250, 150);
        initWidget(map);
        placeWidgets();
        show();
    }

    private void initWidget(MapController map) {
        initLabel();
        initTextField();
        initDatePicker();
        initComboBoxes();
        initButton(map);
    }
    
    private void initLabel() {
        _nameLabel = new Label("Name : ");
        _dateLabel = new Label("Date : ");
        _hourLabel = new Label("Hour : ");
    }

    private void initTextField() {
        _pokemonName = new TextField();
        _pokemonName.setPromptText("Pokemon Name");
    }

    private void initDatePicker() {
        _dateMonthYear = new DatePicker();
        setDatePicker(_dateMonthYear);
    }
    
    private void initComboBoxes() {
        Calendar calendar = initCalendar();
        initComboBoxHour(calendar.get(Calendar.HOUR_OF_DAY));
        initComboBoxMinutes(calendar.get(Calendar.MINUTE));
    }
    
    private Calendar initCalendar() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    private void initComboBoxHour(int hour) {
        _dateHour = new ComboBox();
        setComboBox(_dateHour, _hours, hour);
        _dateHour.setPromptText(Integer.toString(hour));
        
    }

    private void initComboBoxMinutes(int minute) {
        _dateMinute = new ComboBox();
        setComboBox(_dateMinute, _minutes, minute);
        _dateMinute.setPromptText(Integer.toString(minute));
    }
    
    private void setComboBox(ComboBox combo, int limit, int value) {
        ObservableList items = combo.getItems();
        for (int i = 1; i < limit+1; i++) {
            items.add(""+i);
        }
        if (value != -1) combo.setValue(value);
    }
    
    private void setDatePicker(DatePicker picker) {
        picker.setValue(LocalDate.now());
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
    
    private void initButton(MapController map) {
        _saveButton = new Button("Save");
        _saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                Timestamp selectedDate = getSelectedTime();
                if (!"".equals(_pokemonName.getText())){
                    map.endPopUpCreateMarker(_pokemonName.getText(), selectedDate);
                }
            }
        });
        _cancelButton = new Button("Cancel");
        _cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                // Only when cancel (and not create marker)
                map.cancelPopUpCreateMarker();
            }
        });
    }
    
    private void placeWidgets() {
        add(_nameLabel,0,0,1,1);
        add(_dateLabel,0,1,1,1);
        add(_hourLabel,0,2,1,1);
        add(_pokemonName,1,0,2,1);
        add(_dateMonthYear,1,1,2,1);
        add(_dateHour,1,2,1,1);
        add(_dateMinute,2,2,1,1);
        add(_cancelButton,1,3,1,1);
        add(_saveButton,2,3,1,1);
        setConstraints();
    }
    
    private void setConstraints() {
        ArrayList<ColumnConstraints> col = new ArrayList<>();
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(26);
        col.add(column0);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(37);
        col.add(column1);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(37);
        col.add(column2);
        ArrayList<RowConstraints> row = new ArrayList<>();
        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(25);
        row.add(row0);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(25);
        row.add(row1);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25);
        row.add(row2);
        addConstraints(col,row);
    }
    
}
