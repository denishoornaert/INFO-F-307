package be.ac.ulb.infof307.g01.common.controller;

import be.ac.ulb.infof307.g01.common.model.FilterSendableModel;
import java.util.List;

/**
 * Interface for the filter-related, network-oriented queries.
 * A Query Controller defines an interface for client and server calls, 
 * that may or may not have different implementation for each.
 */
public interface FilterQueryController {
    
    public List<FilterSendableModel> getAllFilter();
    public void insertFilter(FilterSendableModel filter);
    
}
