package ru.filin.HavachMVC.model.userManagement.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private long id;

    private String email;

    private String firstName;

    private String secondName;

    private String password;

    private boolean active;

    List<Role> roles = new ArrayList<>();
}
