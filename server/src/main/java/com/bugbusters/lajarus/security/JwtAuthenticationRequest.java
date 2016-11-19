package com.bugbusters.lajarus.security;

import java.io.Serializable;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Authentication credentials")
public class  JwtAuthenticationRequest implements Serializable {

    @ApiObjectField(description = "Registered username", required = true)
    private String username;
    
    @ApiObjectField(description = "Password associated with the username", required = true)
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this();
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
