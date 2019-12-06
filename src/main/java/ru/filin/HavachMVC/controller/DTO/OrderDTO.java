package ru.filin.HavachMVC.controller.DTO;

import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.constants.PaymentType;
import ru.filin.HavachMVC.model.orderManagement.entities.OrderItem;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private List<OrderItem> orderItems;

    private Date timeCreated;

    private PaymentType paymentType;

    private int quantity;

    private int finalPrice;

    private OrderStatus orderStatus;
}
