/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.MapController;
import be.ac.ulb.infof307.g01.NewMarkerPopUpController;
import java.io.File;
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
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public class NewMarkerPopUp extends PopUp {
    
    private ImageView _selectedPokemonView;
    private ComboBox _pokemonName;
    private DatePicker _dateMonthYear;
    private ComboBox _dateHour;
    private ComboBox _dateMinute;
    private Label _dateHourLabel;
    private Label _dateMinuteLabel;
    private Spinner<Integer> _lifeSpinner;
    private Spinner<Integer> _attackSpinner;
    private Label _lifeLabel;
    private Label _attackLabel;
    private final int _hours = 24;
    private final int _minutes = 60;
    private Button _closeButton;
    private Button _okButton;
    
    public NewMarkerPopUp(NewMarkerPopUpController controller) {
        super();
        initWidget(controller);
        placeWidgets();
        initStyle();
        show();
    }

    private void initWidget(NewMarkerPopUpController controller) {
        initImage();
        initDatePicker();
        initSpinners();
        initComboBoxes();
        initLabels();
        initCloseButton(controller);
        initokButton(controller);
    }

    private void initImage() {
        String path = new File("assets/unknown_pokemon.png").toURI().toString();
        _selectedPokemonView = new ImageView(new Image(path));
        _selectedPokemonView.setFitHeight(150);
        _selectedPokemonView.setFitWidth(150);
    }
    
    private void initDatePicker() {
        _dateMonthYear = new DatePicker();
        setDatePicker(_dateMonthYear);
        HBox.setHgrow(_dateMonthYear, Priority.ALWAYS);
        _dateMonthYear.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initSpinners() {
        initLifeSpinner();
        initAttackSpinner();
    }
    
    private void initLifeSpinner() {
        _lifeSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        _lifeSpinner.setPrefWidth(100);
    }

    private void initAttackSpinner() {
        _attackSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        _attackSpinner.setPrefWidth(100);
    }
    
    private void initComboBoxes() {
        initComboBoxPokemonName();
        Calendar calendar = initCalendar();
        initComboBoxHour(calendar.get(Calendar.HOUR_OF_DAY));
        initComboBoxMinutes(calendar.get(Calendar.MINUTE));
    }
    
    private void initLabels() {
        _dateHourLabel = new Label(" h ");
        _dateMinuteLabel = new Label("min");
        _lifeLabel = new Label("life : ");
        _attackLabel = new Label("atk : ");
    }
    
    
    private void initComboBoxPokemonName() {
        _pokemonName = new ComboBox();
        _pokemonName.setPromptText("Pokemon name");
        _pokemonName.setEditable(true); 
        HBox.setHgrow(_pokemonName, Priority.ALWAYS);
        _pokemonName.setMaxWidth(Double.MAX_VALUE);
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
    
    private String getSelectedString(ComboBox combobox) {
        Object object = combobox.getValue();
        return object.toString();
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
    
    private void initokButton(NewMarkerPopUpController controller) {
        _okButton = new Button("save");
        _okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                Timestamp selectedDate = getSelectedTime();
                controller.endPopUpCreateMarker(getSelectedString(_pokemonName), selectedDate);
                // Only when cancel (and not create marker)
                // map.cancelPopUpCreateMarker();
            }
        });
        _okButton.getStyleClass().add("primary");
        HBox.setHgrow(_okButton, Priority.ALWAYS);
        _okButton.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initCloseButton(NewMarkerPopUpController controller) {
        _closeButton = new Button("cancel");
        _closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                // Only when cancel (and not create marker)
                controller.cancelPopUpCreateMarker();
            }
        });
        _closeButton.getStyleClass().add("danger");
        HBox.setHgrow(_closeButton, Priority.ALWAYS);
        _closeButton.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void placeWidgets() {
        HBox hboxDates = placeInRow(_dateHour, _dateHourLabel, _dateMinute, _dateMinuteLabel);
        HBox hboxSpinner = placeInRow(_lifeLabel, _lifeSpinner, _attackLabel, _attackSpinner);
        HBox hboxButtons = placeInRow(_closeButton, _okButton);
        VBox vboxDataEntries = placeInColumn(_pokemonName, hboxSpinner, _dateMonthYear, hboxDates, hboxButtons);
        HBox generalEntries = placeInRow(_selectedPokemonView, vboxDataEntries);
        add(generalEntries);
    }
    
    private VBox placeInColumn(Node... nodes) {
        VBox vbox = new VBox();
        ObservableList<Node> childrenV = vbox.getChildren();
        childrenV.addAll(nodes);
        vbox.setSpacing(10);
        return vbox;
    }
    
    private HBox placeInRow(Node... nodes) {
        HBox hbox = new HBox();
        ObservableList<Node> childrenHboxDates = hbox.getChildren();
        childrenHboxDates.addAll(nodes);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    private void initStyle() {
        setSize(500, 150);
    }
    
}