package ru.filin.HavachMVC.model.userManagement.entities;

import lombok.Data;

@Data
public class Role {
    private long id;
    private String name;

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
