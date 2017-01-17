/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.lajarus.model;

/**
 *
 * @author Vasilis Naskos
 */
public class PlayerOnlineForm {
    
    private long playerId;
    private boolean online;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public boolean isOnline() {
        return online;
    }

    public void setIsOnline(boolean isOnline) {
        this.online = isOnline;
    }
}
