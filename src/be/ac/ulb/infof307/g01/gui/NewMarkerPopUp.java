package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.NewMarkerPopUpController;
import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.input.KeyEvent;
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
    
    public NewMarkerPopUp(NewMarkerPopUpController controller) {
        super();
        System.out.println("1");
        initWidget(controller);
        System.out.println("2");
        placeWidgets();
        System.out.println("3");
        initStyle();
        System.out.println("4");
        show();
    }

    private void initWidget(NewMarkerPopUpController controller) {
        initImage();
        System.out.println("1.1");
        initDatePicker();
        System.out.println("1.2");
        initSpinners();
        System.out.println("1.3");
        initComboBoxes(controller);
        System.out.println("1.4");
        initLabels();
        System.out.println("1.5");
        initCloseButton(controller);
        System.out.println("1.6");
        initokButton(controller);
    }

    private void initImage() {
        _selectedPokemonView = new ImageView();
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
    
    private void initComboBoxes(NewMarkerPopUpController controller) {
        initComboBoxPokemonName(controller);
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
    
    
    private void initComboBoxPokemonName(NewMarkerPopUpController controller) {
        _pokemonName = new ComboBox();
        _pokemonName.setPromptText("Pokemon name");
        _pokemonName.setEditable(true);
        initComboBoxPokemonNameEvent(controller);
        //setComboBoxPokemonNameContent(controller, "");
        HBox.setHgrow(_pokemonName, Priority.ALWAYS);
        _pokemonName.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initComboBoxPokemonNameEvent(NewMarkerPopUpController controller) {
        initComboBoxPokemonNameEventCharTyped(controller);
        initComboBoxPokemonNameEventItemSelected(controller);
    }
    
    private void initComboBoxPokemonNameEventCharTyped(NewMarkerPopUpController controller) {
        _pokemonName.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            String text = _pokemonName.getEditor().getText();
            ObservableList items = _pokemonName.getItems();
            items.clear();
            controller.newPokemonNameUpdate(text);
            //setComboBoxPokemonNameContent(controller, text);
        });
    }
    
    private void initComboBoxPokemonNameEventItemSelected(NewMarkerPopUpController controller) {
        _pokemonName.setOnAction((event) -> {
            Object object = _pokemonName.getSelectionModel().getSelectedItem();
            if (object != null) {
                controller.selectedPokemonName(object.toString());
            }
        });
    }
    
    public void setComboBoxPokemonNameContent(NewMarkerPopUpController controller, 
            ArrayList<String> pokemonsName) {
        // ArrayList<String> pokemonsName = controller.getPokemonByName(text);
        ObservableList items = _pokemonName.getItems();
        for (String item : pokemonsName) {
            items.add(item);
        }
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
                String name = getSelectedString(_pokemonName);
                int life = getSpinnerValue(_lifeSpinner);
                int attack = getSpinnerValue(_attackSpinner);
                int defense = getSpinnerValue(_defenseSpinner);
                controller.endPopUpCreateMarker(name, life, attack, defense, selectedDate);
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

    private void initStyle() {
        setSize(500, 150);
    }
    
    public void setPokemonView(String imagePath) {
        String path = new File(imagePath).toURI().toString();
        _selectedPokemonView.setImage(new Image(path));
    }
    
}