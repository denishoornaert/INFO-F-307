/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.gui;

import be.ac.ulb.infof307.g01.CoordinateModel;
import be.ac.ulb.infof307.g01.Main;
import be.ac.ulb.infof307.g01.MapController;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author hoornaert
 */
public class MapView extends BorderPane implements EventHandler<MouseEvent> {
    
    private MapController _mapController;
    /** This StackPane will contain all elements that the map needs to display.
     * It is in this layout that we will add the map image or pins.
     */
    private StackPane _contentLayout;
    private ScrollPane _scrollPane;
    private ImageView _imageView;
    private Slider _imageSlider;
    private List<Pin> _pins;
    
    public MapView(MapController mapController) {
        super();
        _mapController = mapController;
        _contentLayout = new StackPane();
        _pins = new ArrayList<>();
        
        setupScrollPane();
        setImageView();
        setImageSlider();
        initLayout();
        initEvent();
        
        this.setCenter(_scrollPane);
        this.setBottom(_imageSlider);
    }
    
    private void setupScrollPane() {
        _scrollPane = new ScrollPane();
        _scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        _scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        _scrollPane.setPannable(true);
        setPrefSize(800, 600);
    }
    
    private void setImageView() {
        String imagePath = _mapController.getImagePath();
        _imageView = new ImageView(new Image(imagePath));
        _imageView.setPreserveRatio(true);
    }
    
    private void setImageSlider() {
        _imageSlider = new Slider();
        _imageSlider.setMin(1.0);
        _imageSlider.setMax(2.0);
        
        _imageSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                onImageSliderChanged();
            }
        });
        _imageSlider.setValue(1.0);
    }
    
    private double clamp(double value, double min, double max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }
    
    // convert any coordinates in the imageView to coordinates in the actual image
    private Point2D imageViewToImage(Point2D imageViewCoordinates) {
        double xProportion = imageViewCoordinates.getX() / _imageView.getBoundsInLocal().getWidth();
        double yProportion = imageViewCoordinates.getY() / _imageView.getBoundsInLocal().getHeight();
        
        Point2D imageCoordinates = new Point2D(
            _imageView.getBoundsInLocal().getMinX() + xProportion * _imageView.getBoundsInLocal().getWidth(), 
            _imageView.getBoundsInLocal().getMinY() + yProportion * _imageView.getBoundsInLocal().getHeight());
        
        return imageCoordinates;
    }
    
    //return coordinates to the center of the image view
    private Point2D imageViewCenterToImage() {
        double x = _imageView.getBoundsInLocal().getMinX() + _imageView.getBoundsInLocal().getWidth()/2;
        double y = _imageView.getBoundsInLocal().getMinY() + _imageView.getBoundsInLocal().getHeight()/2;
        return imageViewToImage(new Point2D(x, y));
    }
    
    private void onImageSliderChanged() {
        double scale = _imageSlider.getValue();
        Point2D center = imageViewCenterToImage();
        
        double newWidth = _scrollPane.getWidth() * scale;
        double newHeight = _scrollPane.getHeight() * scale;

        double newMinX = clamp(center.getX() - (center.getX() - _imageView.getBoundsInLocal().getMinX()) * scale, 
                0, _scrollPane.getWidth() - newWidth);
        double newMinY = clamp(center.getY() - (center.getY() - _imageView.getBoundsInLocal().getMinY()) * scale, 
                0, _scrollPane.getWidth() - newHeight);
        System.out.println("Zoomed to scale " + scale + " from center " + center);
        _imageView.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
    }
    
    private void initLayout() {
        // Set the stack pane as the internal container for _scrollPane
        _scrollPane.setContent(_contentLayout);
        
        // Add the image view to the stack pane
        _contentLayout.getChildren().add(_imageView);
        
        // Add the ourselves to the main layout
        StackPane mainLayout = Main.getStackPane();
        mainLayout.getChildren().add(this);
    }
    
    private void initEvent() {
        _imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }
    
    public void adaptToScene(ReadOnlyDoubleProperty property) {
        // bind the preferred size of the scroll area to the size of the scene.
        prefWidthProperty().bind(property);
        prefHeightProperty().bind(property);

        // center the scroll contents.
        _scrollPane.setHvalue(_scrollPane.getHmin()+(_scrollPane.getHmax()-_scrollPane.getHmin())/2);
        _scrollPane.setVvalue(_scrollPane.getVmin()+(_scrollPane.getVmax()-_scrollPane.getVmin())/2);
    }
    
    /** Returns the size of the map */
    public CoordinateModel getSize() {
        return new CoordinateModel((int) _imageView.getImage().getWidth(), (int) _imageView.getImage().getHeight());
    }
    
    public Pin addPin(Pin newPin) {
        _pins.add(newPin);
        _contentLayout.getChildren().add(newPin);
        return newPin;
    }
    
    ///////// EVENT ///////// 
    
    @Override
    public void handle(MouseEvent event) {
        if(event.getButton().equals(MouseButton.SECONDARY)) {
            System.out.println("Clic on Map " +
                    "(" + event.getX()+ ", " + event.getY() + ")");
            _mapController.askForCreateMarker(event.getX(), event.getY());
        }
    }
    
}
