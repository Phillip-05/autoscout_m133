package dev.phill.autoscout.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * a user for authentication / authorization
 */
@Getter
@Setter
public class User {
    private String userUUID;
    private String username;
    private String password;
    private String role;
    private List<String> words;

    public User(){
        setUsername("guest");
        setRole("guest");
    }
}
