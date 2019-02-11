package ru.damir.server.user_storing.controllers.response;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import ru.damir.server.user_storing.dao.entities.Account;

import java.util.Set;


@Relation(value = "user", collectionRelation = "users")
public class UserResponse extends ResourceSupport{
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
