package ru.filin.HavachMVC.model.orderManagement.entities;

import lombok.Data;

@Data
public class Category {
    long id;

    private String title;

    private long parentId;

    public Category(long id, String title, long parentId) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
    }
}
