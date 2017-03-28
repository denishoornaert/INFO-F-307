package be.ac.ulb.infof307.g01;

/**
 * TODO: add description
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
