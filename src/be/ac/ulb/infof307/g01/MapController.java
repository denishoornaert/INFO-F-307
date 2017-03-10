/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robin
 */
public class MapController {
    private List<Marker> _markers;
    
    public MapController() {
        _markers = new ArrayList<>();
    }
    
    public void createMarker() {
        Marker newMarker = new Marker();
        _markers.add(newMarker);
    }
    
}
