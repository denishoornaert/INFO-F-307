package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Represents a geographical coordinate, pointing to a location on the map.
 * The sendable keyword indicates the object can be serialized and sent 
 * over the network
 */
@XmlRootElement
public class CoordinateSendableModel {
    
    private double _latitude;
    private double _longitude;
    
    /**
     * Default constructor.
     * Required by Jersey.
     */
    public CoordinateSendableModel() {
        _latitude = 0;
        _longitude = 0;
    }
    
    public CoordinateSendableModel(double latitude, double longitude) {
        _latitude = latitude;
        _longitude = longitude;
    }
    
    /**
     * @return coordinate latitude
     */
    public double getLatitude() {
        return _latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this._latitude = latitude;
    }

    /**
     * @return coordinate longitude
     */
    public double getLongitude() {
        return _longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this._longitude = longitude;
    }
    
    @Override
    public boolean equals(Object object){
        if(!(object instanceof CoordinateSendableModel)) {
            return false;
        }
        CoordinateSendableModel other = (CoordinateSendableModel) object;
        return getLatitude() == other.getLatitude() && getLongitude() == other.getLongitude();
    }
    
    @Override
    public String toString() {
        return "(" + getLatitude() + ", " + getLongitude() + ")";
    }
    
    
}
