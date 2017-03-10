/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.Coordinate;
import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.Marker;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author remy
 */
public class Pin extends ImageView implements EventHandler<MouseEvent> {

    private final Marker _marker;


    public Pin(Marker marker) {
        super();

        _marker = marker;

        initImage();
        drawPin();
        movePin();

        initEvent();
    }

    private void initImage() {
        String imagePath = new File("assets/pin.png").toURI().toString();
        Image image = new Image(imagePath);
        setImage(image);
    }

    private void drawPin() {
        StackPane stackPane = Main.getStackPane();
        ObservableList<Node> children = stackPane.getChildren();
        children.add(this);
    }

    private void movePin() {
        // Find the offset because the image is center automatically
        double heighOffset = this.getImage().getHeight()/2;
        // Move the picture
        Coordinate markerCoord = _marker.getCoordinate();
        setTranslateX(markerCoord.getX());
        setTranslateY(markerCoord.getY() - heighOffset);
        // TODO : voir si on utiliserai un système d'ancrage au lieu de décaller

    }

    private void initEvent() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }


    //////////////////// EVENT ////////////////////

    @Override
    public void handle(MouseEvent e) {
        System.out.println("MouseEvent on pin");
        Stage stage = new Stage(StageStyle.UNDECORATED);
        ///stage.initOwner(Main.getStackPane());

        stage.setX(e.getScreenX());
        stage.setY(e.getScreenY());

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: paleturquoise;");
        layout.setPrefSize(40, 40);
        //layout.setOnMouseClicked(e-> stage.close());
        stage.setScene(new Scene(layout));

        stage.show();
    }

}