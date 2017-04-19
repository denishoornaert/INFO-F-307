package be.ac.ulb.infof307.g01.view;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import be.ac.ulb.infof307.g01.controller.NewMarkerPopUpController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * TODO: add description
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
    private Spinner<Integer> _defenseSpinner;
    private Label _lifeLabel;
    private Label _attackLabel;
    private Label _defenseLabel;
    private final int _hours = 24;
    private final int _minutes = 60;
    private Button _closeButton;
    private Button _okButton;
    
    private final ArrayList<String> _pokemonNames;
    private final NewMarkerPopUpController _controller;
    
    public NewMarkerPopUp(NewMarkerPopUpController controller, ArrayList<String> pokemonsName) {
        super();
        _pokemonNames = new ArrayList<String>(pokemonsName);
        Collections.sort(_pokemonNames);
        _controller = controller;
        initWidget();
        placeWidgets();
        initSize();
        show();
    }

    private void initWidget() {
        initImage();
        initDatePicker();
        initSpinners();
        initComboBoxes();
        initLabels();
        initCloseButton();
        initokButton();
    }

    private void initImage() {
        _selectedPokemonView = new ImageView();
        _selectedPokemonView.setFitHeight(150);
        _selectedPokemonView.setFitWidth(150);
    }
    
    private void initDatePicker() {
        _dateMonthYear = new DatePicker();
        setDatePicker(_dateMonthYear);
        setXExpandPolicy(_dateMonthYear);
    }
    
    private void initSpinners() {
        _lifeSpinner = createSpinner();
        _defenseSpinner = createSpinner();
        _attackSpinner = createSpinner();
    }
    
    private int getSpinnerValue(Spinner spin) {
        return (Integer) spin.getValue();
    }
    
    private Spinner createSpinner() {
        Spinner spin = new Spinner<>(0, Integer.MAX_VALUE, 0);
        spin.setPrefWidth(100);
        return spin;
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
        _defenseLabel = new Label("def : ");
    }
    
    
    private void initComboBoxPokemonName() {
        _pokemonName = new ComboBox();
        _pokemonName.setPromptText("Pokemon name");
        setComboBoxPokemonNameContent();
        initComboBoxPokemonNameEventItemSelected();
        setXExpandPolicy(_pokemonName);
    }
    
    private void setComboBoxPokemonNameContent() {
        _pokemonName.getItems().addAll(_pokemonNames);

    }
    
    private void initComboBoxPokemonNameEventItemSelected() {
        _pokemonName.setOnAction((event) -> {
            Object object = _pokemonName.getSelectionModel().getSelectedItem();
            if (object != null) {
                _controller.selectedPokemonName(object.toString());
            }
        });
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
        setXExpandPolicy(combo);
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
        String res = "";
        if(object != null) {
            res = object.toString();
        }
        return res;
    }
    
    private Timestamp getSelectedTime() {
        LocalDate localDate = _dateMonthYear.getValue();
        Integer hours = getSelection(_dateHour);
        Integer minute = getSelection(_dateMinute);
        Calendar currentDate = Calendar.getInstance();
        // offset value for month
        // Calendar class starts at 0
        currentDate.set(localDate.getYear(), localDate.getMonthValue()-1, 
                localDate.getDayOfMonth(), hours, minute, 0);
        Timestamp resTimestamp = new Timestamp(currentDate.getTimeInMillis());
        return resTimestamp;
    }
    
    private void initokButton() {
        _okButton = new Button("save");
        _okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                Timestamp selectedDate = getSelectedTime();
                String name = getSelectedString(_pokemonName);
                int life = getSpinnerValue(_lifeSpinner);
                int attack = getSpinnerValue(_attackSpinner);
                int defense = getSpinnerValue(_defenseSpinner);
                _controller.endPopUpCreateMarker(name, life, attack, defense, selectedDate);
                // Only when cancel (and not create marker)
                // map.cancelPopUpCreateMarker();
            }
        });
        _okButton.getStyleClass().add("primary");
        setXExpandPolicy(_okButton);
    }
    
    private void initCloseButton() {
        _closeButton = new Button("cancel");
        _closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                // Only when cancel (and not create marker)
                _controller.cancelPopUpCreateMarker();
            }
        });
        _closeButton.getStyleClass().add("danger");
        setXExpandPolicy(_closeButton);
    }
    
    private void placeWidgets() {
        HBox hboxDates = placeInRow(_dateHour, _dateHourLabel, _dateMinute, _dateMinuteLabel);
        HBox hboxSpinner = placeInRow(_lifeLabel, _lifeSpinner, _attackLabel, _attackSpinner, _defenseLabel, _defenseSpinner);
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
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    private void initSize() {
        setSize(500, 150);
    }
    
    /**
     * Set max width to widget
     * 
     * @param control the current gui element
     */
    private void setXExpandPolicy(Control control) {
        HBox.setHgrow(control, Priority.ALWAYS);
        control.setMaxWidth(Double.MAX_VALUE);
    }
    
    public void setPokemonView(String imagePath) {
        String path = new File(imagePath).toURI().toString();
        _selectedPokemonView.setImage(new Image(path));
    }

    public void errorInPokemonName() {
        _pokemonName.setPromptText("Please select an item");
    }
    
}