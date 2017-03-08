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
    
}
