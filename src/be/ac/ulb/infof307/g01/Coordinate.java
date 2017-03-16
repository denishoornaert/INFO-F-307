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
public class Coordinate {
    
    private final double _x;
    private final double _y;
    
    public Coordinate(double x, double y) {
        _x = x;
        _y = y;
    }
    
    public double getX() {
        return _x;
    }
    
    public double getY() {
        return _y;
    }
    
    public Coordinate add(Coordinate other) {
        return new Coordinate(getX() + other.getX(), getY() + other.getY());
    }
    
    public Coordinate multiply(double factor) {
        return new Coordinate(getX() * factor, getY() * factor);
    }
    
    public boolean equals(Object object){
        Coordinate other = (Coordinate) object;
        return getX() == other.getX() && getY() == other.getY();
    }
    
}
