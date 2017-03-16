/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.db;

import be.ac.ulb.infof307.g01.MarkerModel;

/**
 *
 * @author Nathan
 */
public interface MarkerDatabaseModel {
    
    public MarkerModel getMarkerByName(String name);
    
}
