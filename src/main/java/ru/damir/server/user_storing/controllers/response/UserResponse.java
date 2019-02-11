package ru.damir.server.user_storing.controllers.response;

import lombok.Data;

@Data
public class UserResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String accounts;

    public UserResponse(String firstName, String middleName, String lastName, String email, String accounts) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.accounts = accounts;
    }

    public UserResponse() {
    }

}
