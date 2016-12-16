package com.bugbusters.lajarus.entity;

/**
 * Created by vasilis on 11/28/16.
 */

public class Session {

    private User user;
    private String token;
    private long activePlayerId;

    public Session(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getActivePlayerId() {
        return activePlayerId;
    }

    public void setActivePlayerId(long activePlayerId) {
        this.activePlayerId = activePlayerId;
    }

    public Player getActivePlayer() {
        return user.getFirstPlayer();
    }
}
