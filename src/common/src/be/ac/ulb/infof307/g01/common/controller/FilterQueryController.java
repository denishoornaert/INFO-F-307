package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import java.util.List;

/**
 *
 * @author Groupe 1
 */
public interface FilterQueryController {
    
    public List<FilterSendableModel> getAllFilter();
    public void insertFilter(FilterSendableModel filter);
    
}
