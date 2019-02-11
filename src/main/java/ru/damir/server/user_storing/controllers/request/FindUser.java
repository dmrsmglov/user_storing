package ru.damir.server.user_storing.controllers.request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FindUser {
    @NotNull
    private int accountNumber;
    private String email;
}
