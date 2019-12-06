package ru.filin.HavachMVC.model.orderManagement.entities;

import lombok.Data;
import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.constants.PaymentType;

import java.util.Date;

@Data
public class Order {
    private long id;

    private long userId;

    private Date timeCreated;

    private PaymentType paymentType;

    private String address;

    private int finalPrice;

    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(long id, long userId, Date timeCreated, PaymentType paymentType, long paymentId, String address, int finalPrice, OrderStatus orderStatus) {
        this.id = id;
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.paymentType = paymentType;
        this.address = address;
        this.finalPrice = finalPrice;
        this.orderStatus = orderStatus;
    }

    public Order(long userId, Date timeCreated, PaymentType paymentType, String address, int finalPrice, OrderStatus orderStatus) {
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.paymentType = paymentType;
        this.address = address;
        this.finalPrice = finalPrice;
        this.orderStatus = orderStatus;
    }
}
