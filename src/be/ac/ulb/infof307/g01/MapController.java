/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

import java.io.File;

/**
 *
 * @author Nathan
 */
public class MapController {
    
    private String _imagePath;
    
    public MapController() {
        _imagePath = new File("assets/Map.jpg").toURI().toString();
    }
    
    public String getImagePath() {
        return _imagePath;
    } 
    
}
