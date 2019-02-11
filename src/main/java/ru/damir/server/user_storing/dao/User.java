package ru.damir.server.user_storing.dao;


import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(mappedBy = "user")
    Set<Account> accounts = new HashSet<>();

}
