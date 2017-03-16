/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.MarkerController;
import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author remy
 */

public class Pin extends ImageView implements EventHandler<MouseEvent> {

    private final MarkerController _marker;


    public Pin(MarkerController marker) {
        super();

        _marker = marker;

        initImage();
        movePin();

        initEvent();
    }

    private void initImage() {
        String imagePath = new File("assets/pin.png").toURI().toString();
        Image image = new Image(imagePath);
        setImage(image);
    }

    private void movePin() {
        // Find the offset because the image is center automatically
        double heighOffset = this.getImage().getHeight()/2;
        // Move the picture
        setTranslateX(_marker.getLatitude());
        setTranslateY(_marker.getLongitude() - heighOffset);
        // TODO : voir si on utiliserai un système d'ancrage au lieu de décaller

    }

    private void initEvent() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }


    //////////////////// EVENT ////////////////////

    @Override
    public void handle(MouseEvent e) {
        System.out.println("MouseEvent on pin");
        PinPopUp popUp = new PinPopUp(_marker);
        popUp.show();
    }

}
