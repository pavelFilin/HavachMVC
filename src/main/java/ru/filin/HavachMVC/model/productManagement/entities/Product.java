package ru.filin.HavachMVC.model.productManagement.entities;

import lombok.Data;

@Data
public class Product {
    private long id;

    private String name;

    private String description;

    private int price;

    private long details_id;

    private int stock;

    private boolean active;

    private String photo;

    public Product(long id, String name, String description, int price, long details_id, int stock, boolean active, String photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.details_id = details_id;
        this.stock = stock;
        this.active = active;
        this.photo = photo;
    }
}
