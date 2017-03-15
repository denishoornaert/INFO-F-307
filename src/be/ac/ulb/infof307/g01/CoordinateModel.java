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
    
    private final int _x;
    private final int _y;
    
    public CoordinateModel(int x, int y) {
        _x = x;
        _y = y;
    }
    
    public int getX() {
        return _x;
    }
    
    public int getY() {
        return _y;
    }
    
    public CoordinateModel add(CoordinateModel other) {
        return new CoordinateModel(getX() + other.getX(), getY() + other.getY());
    }
    
    public CoordinateModel multiply(double factor) {
        return new CoordinateModel((int) (getX() * factor), (int) (getY() * factor));
    }
    
    public boolean equals(Object object){
        CoordinateModel other = (CoordinateModel) object;
        return getX() == other.getX() && getY() == other.getY();
    }
    
}
