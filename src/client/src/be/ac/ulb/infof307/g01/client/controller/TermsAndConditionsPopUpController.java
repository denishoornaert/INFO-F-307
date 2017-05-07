/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.client.controller;

import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.view.TermsAndConditionsPopUp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoornaert
 */
public class TermsAndConditionsPopUpController extends AbstractPopUpController {

    TermsAndConditionsPopUp _popUp;
    
    public TermsAndConditionsPopUpController() throws InstantiationException {
        super();
        _popUp = new TermsAndConditionsPopUp(this);
        _popUp.setText(getText());
    }

    private String getText() {
        FileReader fr = null;
        BufferedReader br = null;
        String res = "";
        try {
            fr = new FileReader(ClientConfiguration.getInstance().getTermsAndContionsPath());
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                    res += sCurrentLine;
            }
        } catch (IOException e) {
            Logger.getLogger(TermsAndConditionsPopUpController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(fr != null) {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(TermsAndConditionsPopUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
    
    @Override
    protected boolean acceptMultiplePopUps() {
        return true;
    }
}
