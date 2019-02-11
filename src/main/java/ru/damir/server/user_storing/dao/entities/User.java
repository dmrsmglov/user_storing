package ru.damir.server.user_storing.dao.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String firstName;

    String middleName;

    String lastName;

    String email;

    public User() {
    }

    public User(String firstName, String middleName, String lastName, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }

    @OneToMany(targetEntity = Account.class, mappedBy = "user")
    Set<Account> accounts = new HashSet<>();

}
