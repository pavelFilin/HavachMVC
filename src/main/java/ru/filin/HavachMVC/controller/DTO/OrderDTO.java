package ru.filin.HavachMVC.controller.DTO;

import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.constants.PaymentStatus;
import ru.filin.HavachMVC.constants.PaymentType;
import ru.filin.HavachMVC.model.orderManagement.entities.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {

    private long id;

    private Date timeCreated;

    private PaymentType paymentType;

    private int quantity;

    private int finalPrice;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private List<OrderItemDTO> orderItems = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(Date timeCreated, PaymentType paymentType, int quantity, int finalPrice, OrderStatus orderStatus, PaymentStatus paymentStatus, List<OrderItemDTO> orderItems) {
        this.timeCreated = timeCreated;
        this.paymentType = paymentType;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.orderItems = orderItems;
    }

    public OrderDTO(long id, Date timeCreated, PaymentType paymentType, int quantity, int finalPrice, OrderStatus orderStatus, PaymentStatus paymentStatus, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.timeCreated = timeCreated;
        this.paymentType = paymentType;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.orderItems = orderItems;
    }

    public OrderDTO(Order order, List<OrderItemDTO> orderItems) {
        this(order.getId(),
                order.getTimeCreated(),
                order.getPaymentType(),
                order.getQuantity(),
                order.getFinalPrice(),
                order.getOrderStatus(),
                order.getPaymentStatus(),
                orderItems
        );
    }
}
