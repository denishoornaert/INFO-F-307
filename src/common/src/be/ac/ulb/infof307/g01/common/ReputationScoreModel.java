package be.ac.ulb.infof307.g01.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class ReputationScoreModel {
    
    private int _upVotes;
    private int _downVotes;
    
    /**
     * @return the _upVotes
     */
    public int getUpVotes() {
        return _upVotes;
    }

    /**
     * @param _upVotes the _upVotes to set
     */
    public void setUpVotes(int _upVotes) {
        this._upVotes = _upVotes;
    }

    /**
     * @return the _downVotes
     */
    public int getDownVotes() {
        return _downVotes;
    }

    /**
     * @param _downVotes the _downVotes to set
     */
    public void setDownVotes(int _downVotes) {
        this._downVotes = _downVotes;
    }

}
