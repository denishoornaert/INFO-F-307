package be.ac.ulb.infof307.g01.model;

/** A vote on the reputation of a marker, can be up or down. */
public enum ReputationVoteModel {
    UP(1), DOWN(-1);
    
    private final int _value;
    
    /** Constructor */
    private ReputationVoteModel(int value) {
        _value = value;
    }
    
    public int getValue() {
        return _value;
    }
}
