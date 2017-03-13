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
    
    private final int _x;
    private final int _y;
    
    public Coordinate(int x, int y) {
        _x = x;
        _y = y;
    }
    
    public int getX() {
        return _x;
    }
    
    public int getY() {
        return _y;
    }
    
    public Coordinate add(Coordinate other) {
        return new Coordinate(getX() + other.getX(), getY() + other.getY());
    }
    
    public Coordinate multiply(double factor) {
        return new Coordinate((int) (getX() * factor), (int) (getY() * factor));
    }
    
    public boolean equals(Object object){
        Coordinate other = (Coordinate) object;
        return getX() == other.getX() && getY() == other.getY();
    }
    
}
