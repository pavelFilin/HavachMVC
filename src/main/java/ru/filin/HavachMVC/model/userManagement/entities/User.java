package ru.filin.HavachMVC.model.userManagement.entities;

import lombok.Data;

@Data
public class User {
    private long id;

    private String email;

    private String firstName;

    private String secondName;

    private String password;
}
