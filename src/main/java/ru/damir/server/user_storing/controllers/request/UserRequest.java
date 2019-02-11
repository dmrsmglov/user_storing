package ru.damir.server.user_storing.controllers.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequest {

    private String firstName;
    private String middleName;
    private String lastName;

    @NotNull
    @Email
    private String email;

}