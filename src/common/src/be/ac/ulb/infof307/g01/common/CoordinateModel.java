package be.ac.ulb.infof307.g01.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class CoordinateModel {

    private double _latitude;
    private double _longitude;
    
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
    
}
