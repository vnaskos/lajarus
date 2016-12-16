package com.bugbusters.lajarus.entity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vasilis on 11/28/16.
 */

public class Player {
    private long id;
    private String name;

    public Player(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  static Player createFromJson(JSONObject json) throws JSONException {
        Builder builder = new Builder();
        builder.id(json.getLong("id"));
        builder.name(json.getString("name"));
        return builder.build();
    }

    public static class Builder {
        private long id;
        private String name;

        public Builder() {}

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
