/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.lajarus.model;

/**
 *
 * @author Karen
 */

/*
    Logic: Every player has got a list of quests that he can do it
    So we want an Entity Many to Many, it is the model that the descripte
    ths entity.
*/
public class PlayerQuestForm {
    
    private long playerid;
    private long questid;

    /**
     * @return the playerid
     */
    public long getPlayerid() {
        return playerid;
    }

    /**
     * @param playerid the playerid to set
     */
    public void setPlayerid(long playerid) {
        this.playerid = playerid;
    }

    /**
     * @return the questid
     */
    public long getQuestid() {
        return questid;
    }

    /**
     * @param questid the questid to set
     */
    public void setQuestid(long questid) {
        this.questid = questid;
    }
    
    
    
}
