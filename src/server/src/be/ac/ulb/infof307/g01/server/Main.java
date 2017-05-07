package be.ac.ulb.infof307.g01.server;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;

/**
 *
 * @author Groupe01
 */

@Startup
public class Main {
    
    @PostConstruct
    public void init() {
        System.out.println("kikoo");
    }
    
}
