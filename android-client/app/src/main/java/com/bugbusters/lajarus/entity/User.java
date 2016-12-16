package com.bugbusters.lajarus.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vasilis on 11/28/16.
 */

public class User {

    private long id;
    private String username;
    private Map<Long, Player> players;

    private static long firstPlayerId;

    public User() {
        players = new HashMap<>();
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.players = builder.players;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Long, Player> getPlayers() {
        return players;
    }

    public Player getPlayer(long pid) {
        return players.get(pid);
    }

    public Player getFirstPlayer() { return players.get(firstPlayerId); }

    public static User createFromJson(JSONObject json) throws JSONException {
        Builder builder = new Builder();
        builder.id(1); //TODO: get the actual id from the server
        builder.username(json.getString("username"));

        JSONArray players = json.getJSONArray("players");
        for(int i=0; i<players.length(); i++) {
            Player p = Player.createFromJson(players.getJSONObject(i));
            builder.player(p);
        }

        return builder.build();
    }

    public static class Builder {
        private long id;
        private String username;
        private Map<Long, Player> players;

        public Builder() {
            players = new HashMap<>();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder player(Player player) {
            players.put(player.getId(), player);
            User.firstPlayerId = player.getId();
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
