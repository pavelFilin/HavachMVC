package ru.filin.HavachMVC.model.productManagement.entities;

import lombok.Data;

@Data
public class Product {
    private long id;

    private String name;

    private String description;

    private int price;

    private long details_id;

    private long warehouse_id;

    private String photo;
}
