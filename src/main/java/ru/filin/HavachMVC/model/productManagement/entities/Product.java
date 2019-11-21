package ru.filin.HavachMVC.model.productManagement.entities;

import lombok.Data;

@Data
public class Product {
    public long id;

    public String name;

    public String description;

    public int price;

    public long details_id;

    public long warehouse_id;

    public String photo;
}
