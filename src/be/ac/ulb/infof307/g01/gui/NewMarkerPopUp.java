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
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public class NewMarkerPopUp extends PopUp {
    
    private VBox _vbox;
    private TextField _pokemonName;
    private DatePicker _dateMonthYear;
    private HBox _hboxDates;
    private ComboBox _dateHour;
    private ComboBox _dateMinute;
    private Label _dateHourLabel;
    private Label _dateMinuteLabel;
    private final int _hours = 24;
    private final int _minutes = 60;
    private Button _closeButton;
    private Button _okButton;
    private HBox _hboxButtons;
    
    public NewMarkerPopUp(MapController map) {
        super();
        initWidget(map);
        placeWidgets();
        initStyle();
        show();
    }

    private void initWidget(MapController map) {
        initTextField();
        initDatePicker();
        initComboBoxes();
        initLabels();
        initCloseButton(map);
        initokButton(map);
    }

    private void initTextField() {
        _pokemonName = new TextField();
        _pokemonName.setPromptText("Pokemon Name");
    }

    private void initDatePicker() {
        _dateMonthYear = new DatePicker();
        setDatePicker(_dateMonthYear);
        HBox.setHgrow(_dateMonthYear, Priority.ALWAYS);
        _dateMonthYear.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initComboBoxes() {
        Calendar calendar = initCalendar();
        initComboBoxHour(calendar.get(Calendar.HOUR_OF_DAY));
        initComboBoxMinutes(calendar.get(Calendar.MINUTE));
    }
    
    private void initLabels() {
        _dateHourLabel = new Label(" h ");
        _dateMinuteLabel = new Label("min");
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
        HBox.setHgrow(combo, Priority.ALWAYS);
        combo.setMaxWidth(Double.MAX_VALUE);
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
    
    private void initokButton(MapController map) {
        _okButton = new Button("ok");
        _okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                Timestamp selectedDate = getSelectedTime();
                map.endPopUpCreateMarker(_pokemonName.getText(), selectedDate);
                // Only when cancel (and not create marker)
                // map.cancelPopUpCreateMarker();
            }
        });
        _okButton.getStyleClass().add("primary");
        HBox.setHgrow(_okButton, Priority.ALWAYS);
        _okButton.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initCloseButton(MapController map) {
        _closeButton = new Button("cancel");
        _closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                // Only when cancel (and not create marker)
                map.cancelPopUpCreateMarker();
            }
        });
        _closeButton.getStyleClass().add("danger");
        HBox.setHgrow(_closeButton, Priority.ALWAYS);
        _closeButton.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void placeWidgets() {
        HBox hboxDates = placeInLine(_dateHour, _dateHourLabel, _dateMinute, _dateMinuteLabel);
        HBox hboxButtons = placeInLine(_closeButton, _okButton);
        VBox vbox = placeInColumn(_pokemonName, _dateMonthYear, hboxDates, hboxButtons);
        add(vbox);
    }
    
    private VBox placeInColumn(Node... nodes) {
        VBox vbox = new VBox();
        ObservableList<Node> childrenV = vbox.getChildren();
        childrenV.addAll(nodes);
        vbox.setSpacing(10);
        return vbox;
    }
    
    private HBox placeInLine(Node... nodes) {
        HBox hbox = new HBox();
        ObservableList<Node> childrenHboxDates = hbox.getChildren();
        childrenHboxDates.addAll(nodes);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    private void initStyle() {
        //
    }
    
}