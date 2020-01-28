package com.yuk.cspcli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Login {
    private final String id;
    private final String password;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    Login(@JsonProperty("id") String id,
          @JsonProperty("password") String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
