package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.PinPopUpController;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * TODO: add description
 */
public class PinPopUp extends PopUp {
    private final PinPopUpController _controller;
    
    private VBox _vbox;
    private Label _pokemonName;
    private Label _date;
    private Label _attackLabel;
    private Label _defenseLabel;
    private Label _lifeLabel;
    
    private HBox _voteBox;
    private Label _voteScoreLabel;
    private Button _upVoteButton;
    private Button _downVoteButton;
    
    private Button _closeButton;
        
    public PinPopUp(PinPopUpController controller) {
        _controller = controller;
        initPosition();
        initWidgets();
        placeWidgets();
        initCloseButtonEvent();
        _vbox.setSpacing(10);
        _voteBox.setSpacing(10);
        show();
    }
    
    private void initPosition() {
        setX(_controller.getCoordinates().getLatitude());
        setY(_controller.getCoordinates().getLongitude());
    }
    
    private void initWidgets() {
        _vbox = new VBox();
        _vbox.setAlignment(Pos.CENTER);
        _pokemonName = new Label("Name : "+ _controller.getPokemonName());
        Timestamp date = _controller.getTimestamp();
        String formatingDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
        _date = new Label("Date : "+formatingDate);
        _closeButton = new Button("Close");
        
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
    
    private void initUpVoteButton() {
        _upVoteButton = new Button("👍");
        _upVoteButton.getStyleClass().add("primary");
        _upVoteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _controller.addUpVote();
            }
        });
    }
    
    private void initDownVoteButton() {
        _downVoteButton = new Button("👎");
        _downVoteButton.getStyleClass().add("primary");
        _downVoteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _controller.addDownVote();
            }
        });
    }
    
    private void placeWidgets() {
        ObservableList<Node> children = _voteBox.getChildren();
        children.addAll(_downVoteButton, _voteScoreLabel, _upVoteButton);
        children = _vbox.getChildren();
        children.addAll(_pokemonName, _date, _lifeLabel, _attackLabel, _defenseLabel, _voteBox, _closeButton);
        add(_vbox);
    }

    private void initCloseButtonEvent() {
        _closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                close();
            }
        });
        _closeButton.getStyleClass().add("danger");
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
        _upVoteButton.setDisable(true);
        _downVoteButton.setDisable(true);
    }
    
}
