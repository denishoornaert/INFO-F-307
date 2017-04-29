package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/** 
 * Represents a geographical coordinate, such as for Marker location on the map. 
 */
@XmlRootElement
public class CoordinateSendableModel {

    // TODO Check if it must be "final"
    private double _latitude;
    private double _longitude;
    
    public CoordinateSendableModel() { // Must exist !
        _latitude = 0;
        _longitude = 0;
    }
    
    public CoordinateSendableModel(double latitude, double longitude) {
        _latitude = latitude;
        _longitude = longitude;
    }
    
    /**
     * @return the _latitude
     */
    public double getLatitude() {
        return _latitude;
    }

    /**
     * @param _latitude the _latitude to set
     */
    public void setLatitude(double _latitude) {
        this._latitude = _latitude;
    }

    /**
     * @return the _longitude
     */
    public double getLongitude() {
        return _longitude;
    }

    /**
     * @param _longitude the _longitude to set
     */
    public void setLongitude(double _longitude) {
        this._longitude = _longitude;
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
