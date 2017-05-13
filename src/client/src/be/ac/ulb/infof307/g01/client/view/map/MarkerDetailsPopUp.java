package be.ac.ulb.infof307.g01.client.view.map;

import be.ac.ulb.infof307.g01.client.controller.map.MarkerDetailsPopUpController;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Popup used to display a marker's informations. 
 * This pop-up is used when the user click on a marker that he has not created.
 */
public class MarkerDetailsPopUp extends AbstractMarkerPopUp {
    
    private final MarkerDetailsPopUpController _controller;
    
    private VBox _vbox;
    private Label _pokemonName;
    private Label _date;
    private Label _username;
    private Label _attackLabel;
    private Label _defenseLabel;
    private Label _lifeLabel;
    
    private HBox _voteBox;
    private Label _voteScoreLabel;
    private Button _upVoteButton;
    private Button _downVoteButton;
    
    private Button _closeButton;
        
    public MarkerDetailsPopUp(MarkerDetailsPopUpController controller) {
        super(controller, 0);
        _controller = controller;
        initWidgets();
        placeWidgets();
        _vbox.setSpacing(10);
        _voteBox.setSpacing(10);
        setSize(400, 100);
        show();
    }
    
    private void initWidgets() {
        _vbox = new VBox();
        _vbox.setAlignment(Pos.CENTER);
        _pokemonName = new Label("Name : "+ _controller.getPokemonName());
        Timestamp date = _controller.getTimestamp();
        String formatingDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
        _date = new Label("Date : "+formatingDate);
        _username = new Label("User : " + _controller.getUsername());
        _closeButton = getCloseButton("Close", "danger");
        
        _lifeLabel = new Label("Life : " + _controller.getPokemonLife());
        _attackLabel = new Label("Attack : " + _controller.getPokemonAttack());
        _defenseLabel = new Label("Defense : " + _controller.getPokemonDefense());
        
        initVoteWidgets();
    }
    
    private void initVoteWidgets() {
        _voteBox = new HBox();
        _voteBox.setAlignment(Pos.CENTER);
        
        _voteScoreLabel = new Label();
        initDownVoteButton();
        initUpVoteButton();
    }
    
    private void initVoteButton(Button button, boolean isUpVote) {
        button.getStyleClass().add("primary");
        button.setOnAction((ActionEvent event) -> {
            _controller.addVote(isUpVote);
        });
    }
    
    private void initUpVoteButton() {
        _upVoteButton = new Button("\uD83D\uDC4D"); // Unicode char for thumb-up
        initVoteButton(_upVoteButton, true);
    }
    
    private void initDownVoteButton() {
        _downVoteButton = new Button("\uD83D\uDC4E"); // Unicode char for thumb-down
        initVoteButton(_downVoteButton, false);
    }
    
    private void placeWidgets() {
        ObservableList<Node> children = _voteBox.getChildren();
        children.addAll(_downVoteButton, _voteScoreLabel, _upVoteButton);
        children = _vbox.getChildren();
        children.addAll(_pokemonName, _date, _username, _lifeLabel, _attackLabel, _defenseLabel, _voteBox, _closeButton);
        add(_vbox);
    }
    
    /**
     * Update the view of vote
     * 
     * @param score the new score
     */
    public void updateVoteView(int score) {
        _voteScoreLabel.setText("Votes : " + score);
    }
    
    /**
     * Disable vote buttons when user has voted
     */
    public void disableVoteButtons() {
        disableUpVoteButton();
        disableDownVoteButton();
    }
    
    public void enableUpVoteButton() {
        _upVoteButton.setDisable(false);
    }
    
    public void disableUpVoteButton() {
        _upVoteButton.setDisable(true);
    }
    
    public void enableDownVoteButton() {
        _downVoteButton.setDisable(false);
    }
    
    public void disableDownVoteButton() {
        _downVoteButton.setDisable(true);
    }
    
    
}