package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.model.app.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.view.options.TermsAndConditionsPopUp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates and controls a popup containing the terms and conditions of the app.
 */
public class TermsAndConditionsPopUpController extends PopUpController {

    TermsAndConditionsPopUp _popUp;
    
    public TermsAndConditionsPopUpController() throws InstantiationException {
        super();
        _popUp = new TermsAndConditionsPopUp(this);
        _popUp.setText(getText());
    }

    /**
     * Gets the terms and conditions text.
     * @return a string containing the terms and conditions
     */
    private String getText() {
        InputStreamReader fr = null;
        BufferedReader br;
        String res = "";
        try {
            URL path = new URL(ClientConfiguration.getInstance().getTermsAndConditionsPath());
            fr = new InputStreamReader(path.openStream());
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                    res += sCurrentLine;
            }
        } catch (IOException error) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, error.getMessage());
        } finally {
            if(fr != null) {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
                }
            }
        }
        return res;
    }
    
    /**
     * Indicates that this popup allows other popups to be opened at the same time.
     * @return true
     */
    @Override
    protected boolean acceptMultiplePopUps() {
        return true;
    }
}
