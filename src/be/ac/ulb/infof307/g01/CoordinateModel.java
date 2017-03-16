/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

/**
 *
 * @author hoornaert
 */
public class CoordinateModel {
    
    private final double _latitude;
    private final double _longitude;
    
    public CoordinateModel(double latitude, double longitude) {
        _latitude = latitude;
        _longitude = longitude;
    }
    
    public double getLatitude() {
        return _latitude;
    }
    
    public double getLongitude() {
        return _longitude;
    }
    
    public boolean equals(Object object){
        CoordinateModel other = (CoordinateModel) object;
        return getLatitude() == other.getLatitude() && getLongitude() == other.getLongitude();
    }
    
}
