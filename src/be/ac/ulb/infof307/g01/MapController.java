package be.ac.ulb.infof307.g01;

import be.ac.ulb.infof307.g01.gui.MapView;

/**
 * TODO: add description
 */
public class MapController {
    private MarkerController _markerController;
    private MapView _mapView;
    
    /** Instance of the current popup. Is equal to null if no popup is open,
     *  and is set to null when the popup is closed.
     */
    private NewMarkerPopUpController _newMarkerPopUpController;    
    private PinPopUpController _pinPopUpController;
    
    public MapController() {
        _mapView = new MapView(this);
        _markerController = new MarkerController(_mapView);
    }
    
    public MapView getMapView() {
        return _mapView;
    }

    public void askForCreateMarker(double latitude, double longitude) {
        _newMarkerPopUpController = new NewMarkerPopUpController(_markerController);
        _newMarkerPopUpController.askForCreateMarker(latitude, longitude);
    }
    
    public void displayPinPopUp(int markerId) {
        MarkerModel marker = _markerController.getMarkerModelFromId(markerId);
        _pinPopUpController = new PinPopUpController(marker);
    }
}