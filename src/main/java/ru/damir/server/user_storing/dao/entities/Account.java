package ru.damir.server.user_storing.dao.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Account {
    @Id
    Integer number;

    String name;

    @ManyToOne
    @JoinColumn
    User user;
}
