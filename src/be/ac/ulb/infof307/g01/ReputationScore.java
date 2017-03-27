/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01;

/**
 *
 * @author hoornaert
 */
public class ReputationScore {
    
    private int _upVote;
    private int _downVote;

    public ReputationScore() {
        this(33, 17);
    }
    
    public ReputationScore(int up, int down) {
        _upVote = up;
        _downVote = down;
    }
    
    public void voteUp() {
        _upVote++;
    }
        
    public void voteDown() {
        _downVote++;
    }
    
    public int getScore() {
        return _upVote-_downVote;
    }
    
}
