package be.ac.ulb.infof307.g01.client.controller.options;

import be.ac.ulb.infof307.g01.client.controller.app.PopUpController;
import be.ac.ulb.infof307.g01.client.model.ClientConfiguration;
import be.ac.ulb.infof307.g01.client.view.options.TermsAndConditionsPopUp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoornaert
 */
public class TermsAndConditionsPopUpController extends PopUpController {

    TermsAndConditionsPopUp _popUp;
    
    public TermsAndConditionsPopUpController() throws InstantiationException {
        super();
        _popUp = new TermsAndConditionsPopUp(this);
        _popUp.setText(getText());
    }

    private String getText() {
        InputStreamReader fr = null;
        BufferedReader br = null;
        String res = "";
        try {
            URL path = new URL(ClientConfiguration.getInstance().getTermsAndConditionsPath());
            fr = new InputStreamReader(path.openStream());
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
