package com.bugbusters.lajarus.model;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
public class PlayerItemForm {
    
    private long playerId;
    
    private long itemId;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
