/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.view;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author hoornaert
 */
public class InformationPopUp extends PopUp {
    
    private ImageView _selectedPokemonView;
    private HBox _widgets;

    
    public InformationPopUp() {
        super();
        initWidgets();
        placeWidgets();
        initSize();
    }

    private void initWidgets() {
        initImage();
    }

    private void placeWidgets() {
        _widgets = placeInRow(_selectedPokemonView);
        super.add(_widgets);
    }
    
    private void initImage() {
        _selectedPokemonView = new ImageView();
        _selectedPokemonView.setFitHeight(150);
        _selectedPokemonView.setFitWidth(150);
    }
    
    public void setPokemonView(String imagePath) {
        String path = new File(imagePath).toURI().toString();
        _selectedPokemonView.setImage(new Image(path));
    }
    
    protected VBox placeInColumn(Node... nodes) {
        VBox vbox = new VBox();
        ObservableList<Node> childrenV = vbox.getChildren();
        childrenV.addAll(nodes);
        vbox.setSpacing(10);
        return vbox;
    }
    
    protected HBox placeInRow(Node... nodes) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(nodes);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }
    
    @Override
    protected void add(Node node) {
        ObservableList<Node> children = _widgets.getChildren();
        children.addAll(node);
    }
    
    /**
     * Set max width to widget
     * 
     * @param control the current gui element
     */
    protected void setXExpandPolicy(Control control) {
        HBox.setHgrow(control, Priority.ALWAYS);
        control.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void initSize() {
        setSize(500, 150);
    }
    
}
