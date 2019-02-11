package ru.damir.server.user_storing.dao.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer number;

    String name;

    @JoinColumn(name = "fk_user")
    User user;

}
