package ru.filin.HavachMVC.model.orderManagement.entities;

import lombok.Data;

@Data
public class Cart {
    private long id;

    private long userId;

    private int totalPrice;

    private int countItem;

    public Cart() {
    }

    public Cart(long userId, int totalPrice, int countItem) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.countItem = countItem;
    }

    public Cart(long id, long userId, int totalPrice, int countItem) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.countItem = countItem;
    }
}
