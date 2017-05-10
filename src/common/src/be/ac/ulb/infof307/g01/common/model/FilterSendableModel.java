package be.ac.ulb.infof307.g01.common.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Groupe 1
 */
@XmlRootElement
public class FilterSendableModel {
    
    private String _filter;
    
    public FilterSendableModel() {}
    
    public FilterSendableModel(String filter) {
        _filter = filter;
    }
    
    /**
     * @return the filter
     */
    public String getFilter() {
        return _filter;
    }

    /**
     * @param filter the filter to set
     */
    public void setFilter(String filter) {
        _filter = filter;
    }
    
}
