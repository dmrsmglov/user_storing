package ru.damir.server.user_storing.dao.entities;


import lombok.Getter;
import lombok.Setter;
import ru.damir.server.user_storing.dao.entities.Account;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "user")
    Set<Account> accounts = new HashSet<>();

}
